package com.ww.geo.region.lookup;

import com.alibaba.fastjson.JSONObject;
import com.github.luben.zstd.ZstdInputStream;
import com.ww.geo.region.bean.GeoAreaPolygonsBean;
import com.ww.geo.region.util.ByteUtil;
import com.ww.geo.region.util.GeoHashHelper;
import com.ww.geo.region.util.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.ww.geo.region.lookup.GeoTilesSearch.geoHash2long;

/**
 * @author Zhanglele
 * @version 2022/11/7
 */
public class ChinaAreaHelper {

    private static class ChinaAreaHelperHolder {
        private static final ChinaAreaHelper INSTANCE = new ChinaAreaHelper();
    }

    private static final String SOURCE = "GEO_DATA_CHINA.zstd";
    private static final int geoTilesLength = 5369151;
    private static final long[] geoArr = new long[geoTilesLength];
    private static final int[] followArr = new int[geoTilesLength];
    public Map<Short, GeoAreaPolygonsBean> cityIdMap;
    public List<GeoAreaPolygonsBean> cityData;

    public Map<Short, GeoAreaPolygonsBean> getCityIdMap() {
        return cityIdMap;
    }

    public List<GeoAreaPolygonsBean> getCityData() {
        return cityData;
    }


    private ChinaAreaHelper() {
        init();
    }

    private void init() {
        List<GeoAreaPolygonsBean> geoAreaPolygonsBeans = readFileFromZSTD();
        long start = System.currentTimeMillis();
        System.out.println("开始初始化数据....");
        //省
        cityData = geoAreaPolygonsBeans.stream().filter(geoAreaPolygonsBean -> geoAreaPolygonsBean.getCityId() % 10000 == 0).collect(Collectors.toList());
        //市
        List<GeoAreaPolygonsBean> second = geoAreaPolygonsBeans.stream().filter(geoAreaPolygonsBean -> geoAreaPolygonsBean.getCityId() % 10000 != 0 && geoAreaPolygonsBean.getCityId() % 100 == 0).collect(Collectors.toList());
        //区
        Map<Integer, List<GeoAreaPolygonsBean>> areaMap = geoAreaPolygonsBeans.stream().filter(geoAreaPolygonsBean -> geoAreaPolygonsBean.getCityId() % 100 != 0).collect(Collectors.groupingBy(geoAreaPolygonsBean -> geoAreaPolygonsBean.getCityId() / 100));
        second.forEach(geoAreaPolygonsBean -> {
            List<GeoAreaPolygonsBean> sub = areaMap.get(geoAreaPolygonsBean.getCityId() / 100);
            if (CollectionUtils.isNotEmpty(sub)) {
                sub.forEach(s -> s.setParent(geoAreaPolygonsBean));
                geoAreaPolygonsBean.setSub(sub);
            }
        });
        Map<Integer, List<GeoAreaPolygonsBean>> cityMap = second.stream().collect(Collectors.groupingBy(geoAreaPolygonsBean -> geoAreaPolygonsBean.getCityId() / 10000));
        cityData.forEach(geoAreaPolygonsBean -> {
            List<GeoAreaPolygonsBean> sub = cityMap.get(geoAreaPolygonsBean.getCityId() / 10000);
            if (CollectionUtils.isNotEmpty(sub)) {
                sub.forEach(s -> s.setParent(geoAreaPolygonsBean));
                geoAreaPolygonsBean.setSub(sub);
            }
        });
        //自治区县处理
        //419001,469022,469002,820006,820008,820001,820007,820004,820005,659009,659001,469006,469030,469028,429006,469025,469007,429005,429004,469021,469026,469027,469023,469029,469001,659010,659011,659003,469024,469005,659008,429021,659005,659007,659006,659004,659002,810013,810014,810015,810016,810011,810008,810007,810006,810009,810005,810018,810003,810001,810002,810004,810012,810010,810017,820002,820003
        //济源市,屯昌县,琼海市,嘉模堂区,圣方济各堂区,花地玛堂区,路凼填海区,大堂区,风顺堂区,昆玉市,石河子市,万宁市,琼中黎族苗族自治县,陵水黎族自治县,天门市,白沙黎族自治县,东方市,潜江市,仙桃市,定安县,昌江黎族自治县,乐东黎族自治县,澄迈县,保亭黎族苗族自治县,五指山市,胡杨河市,新星市,图木舒克市,临高县,文昌市,可克达拉市,神农架林区,北屯市,双河市,铁门关市,五家渠市,阿拉尔市,北区,大埔区,西贡区,沙田区,屯门区,黄大仙区,九龙城区,深水埗区,观塘区,油尖旺区,离岛区,东区,中西区,湾仔区,南区,元朗区,荃湾区,葵青区,花王堂区,望德堂区
        geoAreaPolygonsBeans.stream().filter(geoAreaPolygonsBean -> Objects.isNull(geoAreaPolygonsBean.getParent()) && geoAreaPolygonsBean.getCityId() % 100 != 0).forEach(geoAreaPolygonsBean -> {
            cityData.stream().filter(province -> province.getCityId() / 10000 == geoAreaPolygonsBean.getCityId() / 10000).findFirst().ifPresent(province -> {
                List<GeoAreaPolygonsBean> sub = province.getSub();
                geoAreaPolygonsBean.setParent(province);
                if (Objects.nonNull(sub)) {
                    sub.add(geoAreaPolygonsBean);
                } else {
                    province.setSub(Lists.newArrayList(geoAreaPolygonsBean));
                }
            });
        });

        AtomicInteger areaCount = new AtomicInteger(0);
        geoAreaPolygonsBeans.stream().filter(geoAreaPolygonsBean -> CollectionUtils.isEmpty(geoAreaPolygonsBean.getSub()))
                .forEach(geoAreaPolygonsBean -> {
                    String[] geos = geoAreaPolygonsBean.getGeos().split(",");
                    for (String geo : geos) {
                        String[] split2 = geo.split(":");
                        String hex8 = StringUtils.leftPad(split2[1], 8, "0");
                        long geoK = geoHash2long(split2[0]);
                        int index = areaCount.get();
                        geoArr[index] = geoK | geoAreaPolygonsBean.getId();
                        followArr[index] = ByteUtil.byte2int(ByteUtil.getPrintBytesFromString(hex8), 0);
                        areaCount.incrementAndGet();
                    }
                });
        System.out.println(areaCount.get());
        long sortStart = System.currentTimeMillis();
        System.out.println("开始排序...");
        try {
            GeoTilesSearch.quickSort(geoArr, followArr, 0, geoArr.length - 1);
        } catch (StackOverflowError e) {
            System.out.println("geoArr参数过大导致栈内存溢出,请优化该排序算法,或者修改VM参数: -Xss2m");
            System.exit(0);
        }
        System.out.println("排序完成,耗时" + (System.currentTimeMillis() - sortStart) + "ms...");
        cityIdMap = geoAreaPolygonsBeans.stream().collect(Collectors.toMap(GeoAreaPolygonsBean::getId, Function.identity()));
        geoAreaPolygonsBeans.forEach(geoAreaPolygonsBean -> {
            geoAreaPolygonsBean.setGeos(null);
            geoAreaPolygonsBean.setGeoPolygons(null);
        });
        System.out.println("加载完成,耗时" + (System.currentTimeMillis() - start) + "ms...");
    }

    private List<GeoAreaPolygonsBean> readFileFromZSTD() {
        try (InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(SOURCE)) {
            try (ZstdInputStream unzipStream = new ZstdInputStream(resourceAsStream)) {
                try (BufferedInputStream bufferedStream = new BufferedInputStream(unzipStream)) {
                    List<GeoAreaPolygonsBean> geoAreaPolygonsBeans = new ArrayList<>();
                    BufferedReader br = new BufferedReader(new InputStreamReader(bufferedStream));
                    String line;
                    try {
                        while ((line = br.readLine()) != null) {
                            geoAreaPolygonsBeans.add(JSONObject.parseObject(line, GeoAreaPolygonsBean.class));
                        }
                    } catch (Exception e) {
                        System.out.println();
                    } finally {
                        try {
                            br.close();
                        } catch (IOException e) {
                            System.out.println();
                        }
                    }

                    short i = 0;
                    for (GeoAreaPolygonsBean geoAreaPolygonsBean : geoAreaPolygonsBeans) {
                        geoAreaPolygonsBean.setId(i);
                        i++;
                    }
                    return geoAreaPolygonsBeans;
                }
            }
        } catch (NullPointerException | IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static ChinaAreaHelper getInstance() {
        return ChinaAreaHelperHolder.INSTANCE;
    }

    public static void main(String[] args) {
        ChinaAreaHelper.getInstance();
        for (int j = 0; j < 1000; j++) {
            long start = System.currentTimeMillis();
            for (int k = 0; k < 900000; k++) {
                double lat = Math.random() * 5 + 25;
                double lng = Math.random() * 10 + 105;
                ChinaAreaHelper.getInstance().getChinaAreaStr(lat, lng);
            }

            long end = System.currentTimeMillis();
            System.out.println("================");
            System.out.println(end - start);
        }

    }


    public Integer getCityId(double lat, double lng) {
        return getCityId(GeoHashHelper.getGeoHash(lat, lng));
    }

    public Integer getCityId(String geoHash) {
        return Optional.ofNullable(cityIdMap.get(GeoTilesSearch.getAreaId(geoHash, geoArr, followArr, true)))
                .map(GeoAreaPolygonsBean::getCityId).orElse(null);
    }

    public String getChinaAreaStr(double lat, double lng) {
        Short id = GeoTilesSearch.getAreaId(lat, lng, geoArr, followArr, true);
        if (Objects.nonNull(id)) {
            GeoAreaPolygonsBean geoAreaPolygonsBean = cityIdMap.get(id);
            return getFullName(geoAreaPolygonsBean);
        }
        return null;
    }

    private static String getFullName(GeoAreaPolygonsBean geoAreaPolygonsBean) {
        GeoAreaPolygonsBean parent = geoAreaPolygonsBean.getParent();
        if (Objects.nonNull(parent)) {
            if (Objects.nonNull(parent.getParent())) {
                return parent.getParent().getName() + parent.getName() + geoAreaPolygonsBean.getName();
            }
            return parent.getName() + geoAreaPolygonsBean.getName();
        }
        return geoAreaPolygonsBean.getName();
    }

}
