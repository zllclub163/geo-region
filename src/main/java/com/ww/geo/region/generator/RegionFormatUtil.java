package com.ww.geo.region.generator;


import com.ww.geo.region.util.GeoHashHelper;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Zhanglele
 * @version 2023/5/15
 */
public class RegionFormatUtil {

    /**
     * 把多边形围栏的字符串格式lng lat,lng lat,..转换为集合数组,并判断是否为封闭区间,不是的话自动首尾相连
     * @param setting lng lat,lng lat,..
     * @return [[lng,lat],[lng,lat],[...]]
     */
    public static List<double[]> getLng2Lat4Polygon(String setting) {
        List<double[]> lng2Lat4Polygon = Arrays.stream(StringUtils.split(setting, ","))
                .map(lng2LatStr -> {
                    String[] lng2LatArr = StringUtils.split(lng2LatStr, " ");
                    return new double[]{Double.parseDouble(lng2LatArr[0]), Double.parseDouble(lng2LatArr[1])};
                }).collect(Collectors.toList());
        double[] first = lng2Lat4Polygon.get(0);
        double[] end = lng2Lat4Polygon.get(lng2Lat4Polygon.size() - 1);
        if (first[0] != end[0] || first[1] != end[1]) {
            lng2Lat4Polygon.add(first);
        }
        return lng2Lat4Polygon;
    }

    /**
     * 获取多边形各顶点的最大/最小经纬度
     *
     * @param lngAndLat4Polygon 多边形各顶点经纬度：List<double[]{lng, lat}>
     * @return {minLat, minLng, maxLat, maxLng}
     */
    public static double[] getAround4Polygon(List<double[]> lngAndLat4Polygon) {
        List<Double> polygonLatList = new ArrayList<>(lngAndLat4Polygon.size());
        List<Double> polygonLngList = new ArrayList<>(lngAndLat4Polygon.size());
        for (double[] lng2LatArr : lngAndLat4Polygon) {
            polygonLatList.add(lng2LatArr[1]);
            polygonLngList.add(lng2LatArr[0]);
        }
        double[] minAndMaxLat = getMinAndMax(polygonLatList);
        double[] minAndMaxLng = getMinAndMax(polygonLngList);
        return new double[]{minAndMaxLat[0], minAndMaxLng[0], minAndMaxLat[1], minAndMaxLng[1]};
    }

    private static double[] getMinAndMax(List<Double> latOrLngList) {
        Double min = latOrLngList.get(0);
        Double max = latOrLngList.get(0);
        for (int i = 1; i < latOrLngList.size() - 1; i++) {
            min = Math.min(min, latOrLngList.get(i));
            max = Math.max(max, latOrLngList.get(i));
        }
        return new double[]{min, max};
    }

    public static String getMaxGeoHash(double[] around4Polygon) {
        String geoHashMin = GeoHashHelper.getGeoHash(around4Polygon[0], around4Polygon[1]);
        String geoHashMax = GeoHashHelper.getGeoHash(around4Polygon[2], around4Polygon[3]);
        String maxGeoHash = StringUtils.EMPTY;
        for (int i = 0; i < 10; i++) {
            if (geoHashMin.charAt(i) == geoHashMax.charAt(i)) {
                maxGeoHash = geoHashMax.substring(0, i + 1);
            } else {
                break;
            }
        }
        return maxGeoHash;
    }
}
