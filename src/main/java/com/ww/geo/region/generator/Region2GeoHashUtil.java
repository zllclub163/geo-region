package com.ww.geo.region.generator;

import ch.hsr.geohash.GeoHash;
import com.ww.geo.region.util.GeoHashHelper;
import com.ww.geo.region.util.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.util.CollectionUtils;

import java.awt.Polygon;
import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 将地理区域设置转换为GeoHash字符串的工具类
 *
 * @author Zhanglele
 * @version 2023/5/15
 */
public class Region2GeoHashUtil {

    //由于java.awt.Polygon只支持整数,用K把经纬度放大为整数
    private static final int K = 1000000;

    /*
     * 生成的GeoHash的最大长度,数值越大,精度越高,但同时意味着要占用更多的存储空间,最大值9(由于后续查询时存储位分配了6*8位,一个长度占5位)
     * 不同长度的GeoHash所对应的矩形边长,和精度误差如下
     * 9 |  4.8  *  4.0  | 4.8
     * 8 |  37.9 *  19.1 | 21.2
     * 7 |  153  *  152  | 107.8
     * 6 |  1213 *  611  | 679.1
     */
    private static final int MAX_LENGTH = 8;

    /**
     * 将地理区域设置转换为合并后的GeoHash字符串列表
     *
     * @param regionSetting 地理区域设置的字符串表示（格式：经度1 纬度1,经度2 纬度2,...）
     * @param level         GeoHash的级别个数(如:3,表示生成最多包含3种不同长度的GeoHash),数值越大,精度越高
     * @return GeoHash字符串列表
     */
    public static List<String> regionSetting2GeoStr(String regionSetting, int level) {
        /* 把多边形围栏的字符串格式lng lat,lng lat,..转换为集合数组[[lng,lat],[lng,lat],[...]] */
        /* 并判断是否为封闭区间,不是的话自动首尾相连 */
        List<double[]> lngLatPolygon = RegionFormatUtil.getLng2Lat4Polygon(regionSetting);

        /* 获取最大值经纬度和最小值经纬度,从而判断出能包含此多边形的最大的GeoHash */
        double[] around4Polygon = RegionFormatUtil.getAround4Polygon(lngLatPolygon);
        String maxGeoHash = RegionFormatUtil.getMaxGeoHash(around4Polygon);
        if (maxGeoHash.length() >= 8) {
            //多边形区域太小,没有必要继续继续计算生成GeoHash
            return Collections.emptyList();
        }

        /* 多边形细化,目的降低判断GeoHash矩形与围栏的位置关系的时间复杂度及空间复杂度 */
        /* 把多边形根据maxGeoHash小两个等级的GeoHash区域切分,目的是为了对多边形抽稀,但局部仍保留原始形状从而不丢失计算的精确度 */
        /* 事先构造好多个局部精确的区域,具体计算原始多边形与GeoHash矩形位置关系时
           先根据GeoHash矩形是否在局部精确的的矩形内来匹配用于计算的局部精确的多边形 */
        int groupLength = maxGeoHash.length() + 2;
        Map<String, Area> areaMap = localThinning(lngLatPolygon, around4Polygon, groupLength);

        /* 生成GeoHash集合的长度 */
        Set<Integer> geoHashLengths = new HashSet<>();
        List<String> result = new ArrayList<>();
        List<String> furtherJudgmentGeoHashList = new ArrayList<>();
        rectangleRelation(result, maxGeoHash, furtherJudgmentGeoHashList, areaMap, groupLength);
        doRegionSetting2GeoStr(result, furtherJudgmentGeoHashList, geoHashLengths, areaMap, groupLength, level);
        return margeGeos(result);
    }


    /**
     * GeoHash字符串合并为较短的表示形式
     * 把[u3sd1,u3sd2,u3sdd,u3sds,u3sdf]->u3sd:12dsf合并
     */
    private static List<String> margeGeos(Collection<String> geos) {
        Map<Integer, List<String>> collect = geos.stream().collect(Collectors.groupingBy(String::length));
        List<String> result = new ArrayList<>();
        for (List<String> value : collect.values()) {
            Map<String, List<String>> collect1 = value.stream().collect(Collectors.groupingBy(s -> s.substring(0, s.length() - 1)));
            for (Map.Entry<String, List<String>> entry : collect1.entrySet()) {
                String key = entry.getKey();
                List<String> list = entry.getValue();
                String collect2 = list.stream().map(s -> s.substring(s.length() - 1)).collect(Collectors.joining());
                result.add(key + ":" + collect2);
            }
        }
        return result;
    }

    private static void doRegionSetting2GeoStr(List<String> result, List<String> furtherJudgmentGeoHashList,
                                               Set<Integer> geoHashLengths, Map<String, Area> areaMap, int length, Integer level) {

        List<String> oneStepFurther = new ArrayList<>();
        //进一步判断该围栏覆盖的下一级GeoHash矩形有哪些
        furtherJudgmentGeoHashList.forEach(geoHash ->
                rectangleRelation(result, geoHash, oneStepFurther, areaMap, length)
        );

        if (result.size() > 0) {
            geoHashLengths.addAll(result.stream().map(String::length).collect(Collectors.toSet()));
        }
        if (geoHashLengths.stream().anyMatch(i -> i >= MAX_LENGTH)) {
            result.removeAll(result.stream().filter(s -> s.length() > MAX_LENGTH).collect(Collectors.toList()));
            return;
        }
        if (geoHashLengths.size() >= level - 1 || geoHashLengths.stream().anyMatch(i -> i >= MAX_LENGTH - 1)
                || CollectionUtils.isEmpty(oneStepFurther)) {
            if (CollectionUtils.isEmpty(oneStepFurther)) {
                return;
            }
            for (String geoHash : oneStepFurther) {
                for (String c : GeoHashHelper.base32Lookup) {
                    String nextLevelGeoHash = geoHash + c;
                    List<double[]> spaceCoordinate = GeoHashHelper.geoHash2Area(nextLevelGeoHash);
                    double lat = (spaceCoordinate.get(1)[0] + spaceCoordinate.get(0)[0]) / 2;
                    double lng = (spaceCoordinate.get(1)[1] + spaceCoordinate.get(0)[1]) / 2;
                    //最后根据最小层级的GeoHash矩形的中心点与围栏的位置来决定是否采用这个GeoHash矩形
                    Area partArea = selectArea(areaMap, nextLevelGeoHash, length);
                    if (pointRelation(lat, lng, partArea) > 0) {
                        result.add(nextLevelGeoHash);
                    }
                }
            }
            return;
        }
        doRegionSetting2GeoStr(result, oneStepFurther, geoHashLengths, areaMap, length, level);
    }

    /**
     * 判断给定的经纬度点与区域的位置关系。如果点在区域内部，返回1；如果点在区域外部，返回-1
     */
    private static int pointRelation(double lat, double lng, Area area) {
        double x = lng * K;
        double y = lat * K;
        if (area.contains(x, y)) {
            return 1;
        }
        return -1;
    }


    private static void rectangleRelation(List<String> result, String geoHash, List<String> nextGeoHash,
                                          Map<String, Area> areaMap, int length) {
        for (String c : GeoHashHelper.base32Lookup) {
            String nextLevelGeoHash = geoHash + c;
            int relation = rectangleRelation(areaMap, nextLevelGeoHash, length);
            if (relation == 1) {
                result.add(nextLevelGeoHash);
            } else if (relation == 0) {
                nextGeoHash.add(nextLevelGeoHash);
            }
        }
    }


    private static int rectangleRelation(Map<String, Area> areaMap, String geoHash, int length) {
        List<double[]> spaceCoordinate = GeoHashHelper.geoHash2Area(geoHash);
        return rectangleRelation(spaceCoordinate, selectArea(areaMap, geoHash, length));
    }

    /**
     * 判断GeoHash矩形与区域的位置关系。根据矩形的边界和区域的相交情况，返回1（完全包含）、0（相交）或-1（不相交）
     */
    private static int rectangleRelation(List<double[]> spaceCoordinate, Area area) {
        double x = spaceCoordinate.get(0)[1] * K;
        double y = spaceCoordinate.get(0)[0] * K;
        double w = (spaceCoordinate.get(1)[1] - spaceCoordinate.get(0)[1]) * K;
        double h = (spaceCoordinate.get(1)[0] - spaceCoordinate.get(0)[0]) * K;
        if (area.contains(x, y, w, h)) {
            return 1;
        }
        return area.intersects(x, y, w, h) ? 0 : -1;
    }

    /**
     * 根据给定的GeoHash字符串选择相应的区域。如果匹配，则返回对应的区域；否则返回原始多边形区域
     */
    private static Area selectArea(Map<String, Area> areaMap, String geoHash, int length) {
        Area partArea = null;
        if (geoHash.length() >= length) {
            partArea = areaMap.get(geoHash.substring(0, length));
            if (partArea == null && geoHash.length() > length) {
                partArea = areaMap.get(geoHash.substring(0, length + 1));
            }
        }
        partArea = Optional.ofNullable(partArea).orElse(areaMap.get(StringUtils.EMPTY));
        return partArea;
    }

    /**
     *  多边形细化 的过程以降低多边形的复杂性,它基于 GeoHash 网格将多边形划分为更小的区域,并构建局部区域以进行高效计算
     * 对多边形进行抽稀处理，生成局部精确的区域集合。根据多边形的边界计算出各个局部区域，并将其与初始区域进行匹配
     */
    private static Map<String, Area> localThinning(List<double[]> lngLatPolygon, double[] around4Polygon, int groupLength) {
        List<AdaptAreaBean> adaptAreaBeans = getAdaptAreaBeans(lngLatPolygon, groupLength, true);
        /*事先构造好多个局部精确的区域,具体计算原始围栏与GeoHash矩形位置关系时
        先根据GeoHash矩形是否在局部精确的的矩形内来匹配用于计算的局部精确的围栏*/
        Area initArea = new Area(new Polygon(lngLatPolygon.stream().map(d -> (int) (d[0] * K)).mapToInt(Integer::intValue).toArray(),
                lngLatPolygon.stream().map(d -> (int) (d[1] * K)).mapToInt(Integer::intValue).toArray(), lngLatPolygon.size()));
        adaptAreaBeans.forEach(adaptFenceBean -> {
            List<double[]> bounds = adaptFenceBean.getAround4Points();
            Area cloneArea = (Area) initArea.clone();
            int[] lng = Lists.newArrayList(bounds.get(0)[1], bounds.get(1)[1], bounds.get(1)[1], bounds.get(0)[1], bounds.get(0)[1]).stream().mapToInt(d -> (int) (d * K)).toArray();
            int[] lat = Lists.newArrayList(bounds.get(0)[0], bounds.get(0)[0], bounds.get(1)[0], bounds.get(1)[0], bounds.get(0)[0]).stream().mapToInt(d -> (int) (d * K)).toArray();
            cloneArea.intersect(new Area(new Polygon(lng, lat, 5)));
            adaptFenceBean.setArea(cloneArea);
        });
        //把原始围栏放在最后,作为兜底
        AdaptAreaBean initFenceBean = new AdaptAreaBean(Lists.newArrayList(new double[]{around4Polygon[0], around4Polygon[1]}, new double[]{around4Polygon[2], around4Polygon[3]}));
        initFenceBean.setArea(initArea);
        initFenceBean.setGeoHash(StringUtils.EMPTY);
        adaptAreaBeans.add(initFenceBean);
        return adaptAreaBeans.stream().collect(Collectors.toMap(AdaptAreaBean::getGeoHash, AdaptAreaBean::getArea));
    }


    /**
     * 将经纬度点列表转换为适应性区域的集合。根据GeoHash值对点进行分组，构建对应的区域对象，并对过多的点进行下一级的处理
     */
    private static List<AdaptAreaBean> getAdaptAreaBeans(List<double[]> lngLatPolygon, int groupLength, boolean first) {
        Map<String, List<Pair<String, double[]>>> map = lngLatPolygon.stream().map(point -> {
            String geoHash = GeoHash.geoHashStringWithCharacterPrecision(point[1], point[0], groupLength);
            return Pair.of(geoHash, point);
        }).collect(Collectors.groupingBy(Pair::getLeft));
        List<AdaptAreaBean> adaptAreaBeanNextLevel = new ArrayList<>();
        List<AdaptAreaBean> adaptAreaBeans = map.entrySet().stream().map(e -> {
            String key = e.getKey();
            AdaptAreaBean adaptAreaBean = new AdaptAreaBean(GeoHashHelper.geoHash2Area(key));
            adaptAreaBean.setGeoHash(key);
            List<double[]> points = e.getValue().stream().map(Pair::getRight).collect(Collectors.toList());
            if (points.size() > 4000 && first) {
                adaptAreaBeanNextLevel.addAll(getAdaptAreaBeans(points, groupLength + 1, false));
                return null;
            }
            adaptAreaBean.setPoints(points);
            return adaptAreaBean;
        }).filter(Objects::nonNull).collect(Collectors.toList());
        adaptAreaBeans.addAll(adaptAreaBeanNextLevel);
        return adaptAreaBeans;
    }

    public static class AdaptAreaBean {
        private Area area;
        private String geoHash;
        private List<double[]> around4Points;//矩形内所有原始点最大经纬度和最小经纬度
        private List<double[]> points = new ArrayList<>();


        public AdaptAreaBean(List<double[]> around4Points) {
            this.around4Points = around4Points;
        }

        public Area getArea() {
            return area;
        }

        public void setArea(Area area) {
            this.area = area;
        }

        public String getGeoHash() {
            return geoHash;
        }

        public void setGeoHash(String geoHash) {
            this.geoHash = geoHash;
        }

        public List<double[]> getAround4Points() {
            return around4Points;
        }

        public void setAround4Points(List<double[]> around4Points) {
            this.around4Points = around4Points;
        }

        public List<double[]> getPoints() {
            return points;
        }

        public void setPoints(List<double[]> points) {
            this.points = points;
        }
    }

}
