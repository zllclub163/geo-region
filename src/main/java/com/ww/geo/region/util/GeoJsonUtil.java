package com.ww.geo.region.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Zhanglele
 * @version 2023/1/12
 */
public class GeoJsonUtil {
    private static final Logger log = LoggerFactory.getLogger(GeoJsonUtil.class);

    private final static String MULTI_POLYGON = "MultiPolygon";
    private final static String POLYGON = "Polygon";
    private final static String COORDINATES = "coordinates";
    private final static String TYPE = "type";

    public static void main(String[] args) {
        List<String> strings = geoJsonPolygonFile2Settings("C:\\Users\\zllclub\\Downloads\\geo\\word-bound\\-270056.json");
        for (String string : strings) {
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println(string);
        }
    }

    public static List<String> geoJsonPolygonFile2Settings(String path) {
        try {
            String geoJson = FileUtils.readFileToString(new File(path), "utf-8");
            return geoJsonPolygon2Settings(geoJson);
        } catch (IOException e) {
           log.error("geoJsonPolygonFile2Settings", e);
        }
        return Collections.emptyList();
    }


    public static List<String> geoJsonPolygon2Settings(String geoJson) {
        JSONObject json = JSONObject.parseObject(geoJson);
        String type = json.getString(TYPE);
        JSONArray jsonArray = json.getJSONArray(COORDINATES);
        if (POLYGON.equalsIgnoreCase(type)) {
            return Stream.of(parserPolygon(jsonArray)).filter(Objects::nonNull).collect(Collectors.toList());
        }
        if (MULTI_POLYGON.equalsIgnoreCase(type)) {
            if (CollectionUtils.isNotEmpty(jsonArray)) {
                List<String> settings = new ArrayList<>();
                for (int i = 0; i < jsonArray.size(); i++) {
                    settings.add(parserPolygon(jsonArray.getJSONArray(i)));
                }
                return settings.stream().filter(Objects::nonNull).collect(Collectors.toList());
            }
        }
        return Collections.emptyList();
    }

    private static String parserPolygon(JSONArray jsonArray) {
        if (CollectionUtils.isNotEmpty(jsonArray)) {
            JSONArray points = jsonArray.getJSONArray(0);
            if (CollectionUtils.isNotEmpty(points)) {
                List<String> pointList = new ArrayList<>();
                for (int i = 0; i < points.size(); i++) {
                    JSONArray lngLat = points.getJSONArray(i);
                    pointList.add(lngLat.getDouble(0) + " " + lngLat.getDouble(1));
                }
                return String.join(",", pointList);
            }
        }
        return null;
    }

}
