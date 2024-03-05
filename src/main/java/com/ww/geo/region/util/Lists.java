package com.ww.geo.region.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Lists {
    @SafeVarargs
    public static <T> List<T> newArrayList(T... elements) {
        ArrayList<T> list = new ArrayList<>(elements.length);
        Collections.addAll(list, elements);
        return list;
    }

}