package com.ww.geo.region.util;


import ch.hsr.geohash.GeoHash;

import com.ww.geo.region.enums.PrecisionEnum;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GeoHashHelper {

    private static final Logger logger = LoggerFactory.getLogger(GeoHashHelper.class);

    private static final int precision = 10;

    public static String getGeoHash(String lat, String lon) {
        try {
            return GeoHash
                    .geoHashStringWithCharacterPrecision(Double.parseDouble(lat), Double.parseDouble(lon), precision);
        } catch (IllegalArgumentException e) {
            logger.warn("==错误的经纬度, lat:" + lat + ", lng:" + lon);
        }
        return StringUtils.EMPTY;
    }

    /**
     * 自定义geohash精度
     *
     * @param lat
     * @param lon
     * @param precision
     * @return
     */
    public static String getGeoHash(String lat, String lon, Integer precision) {
        try {
            return GeoHash
                    .geoHashStringWithCharacterPrecision(Double.parseDouble(lat), Double.parseDouble(lon), precision);
        } catch (IllegalArgumentException e) {
            logger.warn("==错误的经纬度, lat:" + lat + ", lng:" + lon);
        }
        return StringUtils.EMPTY;
    }

    /**
     * 错误的经纬度参数会返回空值
     *
     * @param lat
     * @param lon
     * @return
     */
    public static String getGeoHash(double lat, double lon) {
        return getGeoHash(lat, lon, PrecisionEnum._10);
    }

    /**
     * geohash长度	Lat位数	Lng位数	Lat误差	Lng误差	km误差
     * 1	2	3	±23	±23	±2500
     * 2	5	5	± 2.8	±5.6	±630
     * 3	7	8	± 0.70	± 0.7	±78
     * 4	10	10	± 0.087	± 0.18	±20
     * 5	12	13	± 0.022	± 0.022	±2.4
     * 6	15	15	± 0.0027	± 0.0055	±0.61
     * 7	17	18	±0.00068	±0.00068	±0.076
     * 8	20	20	±0.000086	±0.000172	±0.01911
     * 9	22	23	±0.000021	±0.000021	±0.00478
     * 10	25	25	±0.00000268	±0.00000536	±0.0005971
     * 11	27	28	±0.00000067	±0.00000067	±0.0001492
     * 12	30	30	±0.00000008	±0.00000017	±0.0000186
     * <p>
     * 错误的经纬度参数会返回空值
     *
     * @param lat       纬度
     * @param lon       经度
     * @param precision 精确度，最大12，越大越精确，方块越小
     * @return 计算后的geoHash
     */
    public static String getGeoHash(double lat, double lon, PrecisionEnum precision) {
        try {
            return GeoHash.geoHashStringWithCharacterPrecision(lat, lon, precision.getPrecision());
        } catch (IllegalArgumentException e) {
            logger.warn("==错误的经纬度, lat:" + lat + ", lng:" + lon);
        }
        return StringUtils.EMPTY;
    }


    /***********************获取九个的矩形编码****************************************/
    public static String BASE32 = "0123456789bcdefghjkmnpqrstuvwxyz";
    public static Map<String, String> BORDERS = new HashMap<String, String>() {{
        put("right:even", "bcfguvyz");
        put("left:even", "0145hjnp");
        put("top:even", "prxz");
        put("bottom:even", "028b");
        put("right:odd", "prxz");
        put("left:odd", "028b");
        put("top:odd", "bcfguvyz");
        put("bottom:odd", "0145hjnp");
    }};

    public static Map<String, String> NEIGHBORS = new HashMap<String, String>() {{
        put("right:even", "bc01fg45238967deuvhjyznpkmstqrwx");
        put("left:even", "238967debc01fg45kmstqrwxuvhjyznp");
        put("top:even", "p0r21436x8zb9dcf5h7kjnmqesgutwvy");
        put("bottom:even", "14365h7k9dcfesgujnmqp0r2twvyx8zb");

        put("right:odd", "p0r21436x8zb9dcf5h7kjnmqesgutwvy");
        put("left:odd", "14365h7k9dcfesgujnmqp0r2twvyx8zb");
        put("top:odd", "bc01fg45238967deuvhjyznpkmstqrwx");
        put("bottom:odd", "238967debc01fg45kmstqrwxuvhjyznp");
    }};

    /**
     * 获取九个点的矩形编码
     *
     * @param geohash 中间方块的geoHash
     * @return 包含中间和周围八个方块的九个hash值
     */
    public static String[] getGeoHashExpand(String geohash) {
        try {
            String geohashTop = calculateAdjacent(geohash, "top");
            String geohashBottom = calculateAdjacent(geohash, "bottom");
            String geohashRight = calculateAdjacent(geohash, "right");
            String geohashLeft = calculateAdjacent(geohash, "left");
            String geohashTopLeft = calculateAdjacent(geohashLeft, "top");
            String geohashTopRight = calculateAdjacent(geohashRight, "top");
            String geohashBottomRight = calculateAdjacent(geohashRight, "bottom");
            String geohashBottomLeft = calculateAdjacent(geohashLeft, "bottom");

            return new String[]{geohash, geohashTop, geohashBottom, geohashRight, geohashLeft, geohashTopLeft, geohashTopRight, geohashBottomRight, geohashBottomLeft};
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 分别计算每个点的矩形编码
     *
     * @param srcHash
     * @param dir
     * @return
     */
    public static String calculateAdjacent(String srcHash, String dir) {
        srcHash = srcHash.toLowerCase();
        char lastChr = srcHash.charAt(srcHash.length() - 1);
        int a = srcHash.length() % 2;
        String type = (a > 0) ? "odd" : "even";
        String base = srcHash.substring(0, srcHash.length() - 1);
        if (BORDERS.get(dir + ":" + type).indexOf(lastChr) != -1) {
            base = calculateAdjacent(base, dir);
        }
        base = base + BASE32.toCharArray()[(NEIGHBORS.get(dir + ":" + type).indexOf(lastChr))];
        return base;
    }

    /***********************geoHash反转经纬度****************************************/

    public final static double Max_Lat = 90;
    public final static double Min_Lat = -90;
    public final static double Max_Lng = 180;
    public final static double Min_Lng = -180;

    public final static String[] base32Lookup = {
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "b", "c", "d", "e", "f", "g", "h", "j", "k",
            "m", "n", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"
    };

    /**
     * 将geoHash转换为经纬度区间
     * @return 该geoHash所在区域最大最小经纬度值 [[latMin,lngMin],[latMax,lngMax]]
     */
    public static List<double[]> geoHash2Area(String geoHashCode) {
        List<Integer> list = base32Decode(geoHashCode);
        String str = convertToIndex(list);
        Pair<List<String>, List<String>> latAndLng = splitLatAndLng(str);
        Pair<Double, Double> lat = revert(Min_Lat, Max_Lat, latAndLng.getLeft());
        Pair<Double, Double> lng = revert(Min_Lng, Max_Lng, latAndLng.getRight());
        List<double[]> result = new ArrayList<>();
        result.add(new double[]{lat.getLeft(), lng.getLeft()});
        result.add(new double[]{lat.getRight(), lng.getRight()});
        return result;
    }

    /**
     * 将geoHash转换为经纬度值
     * @return 该geoHash所在区域的中心点经纬度 [lat,lng]
     */
    public static double[] geoHash2LatLng(String geoHashCode) {
        List<double[]> spaceCoordinate = geoHash2Area(geoHashCode);
        return new double[]{(spaceCoordinate.get(0)[0] + spaceCoordinate.get(1)[0]) / 2,
                (spaceCoordinate.get(0)[1] + spaceCoordinate.get(1)[1]) / 2};
    }

    /**
     * 将二值串转换为经纬度值
     *
     * @param min  区间最小值
     * @param max  区间最大值
     * @param list 二值串列表
     */
    private static Pair<Double, Double> revert(double min, double max, List<String> list) {
        double mid;
        if (list.size() <= 0) {
            return Pair.of(min, max);
        }
        for (String flag : list) {
            mid = (max + min) / 2;
            if ("0".equals(flag)) {
                max = mid;
            }
            if ("1".equals(flag)) {
                min = mid;
            }
        }
        return Pair.of(min, max);
    }

    /**
     * 分离经度与纬度串
     *
     * @param latAndLngStr 经纬度二值串
     */
    private static Pair<List<String>, List<String>> splitLatAndLng(String latAndLngStr) {
        // 纬度二值串
        List<String> latList = new ArrayList<>();
        // 经度二值串
        List<String> lngList = new ArrayList<>();
        for (int i = 0; i < latAndLngStr.length(); i++) {
            // 奇数位，纬度
            if (i % 2 == 1) {
                latList.add(String.valueOf(latAndLngStr.charAt(i)));
            } else {
                // 偶数位，经度
                lngList.add(String.valueOf(latAndLngStr.charAt(i)));
            }
        }
        return Pair.of(latList, lngList);
    }

    /**
     * 将十进制数转为五个二进制数
     *
     * @param nums 十进制数
     * @return 五个二进制数
     */
    private static String convertToIndex(List<Integer> nums) {
        StringBuilder str = new StringBuilder();
        for (Integer num : nums) {
            StringBuilder sb = new StringBuilder(Integer.toBinaryString(num));
            int length = sb.length();
            if (length < 5) {
                for (int i = 0; i < 5 - length; i++) {
                    sb.insert(0, "0");
                }
            }
            str.append(sb);
        }
        return str.toString();
    }

    /**
     * 将base32串转为合并的二值串
     *
     * @param str base32串
     * @return 合并的二值串
     */
    private static List<Integer> base32Decode(String str) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            String ch = String.valueOf(str.charAt(i));
            for (int j = 0; j < base32Lookup.length; j++) {
                if (base32Lookup[j].equals(ch)) {
                    list.add(j);
                }
            }
        }
        return list;
    }


}
