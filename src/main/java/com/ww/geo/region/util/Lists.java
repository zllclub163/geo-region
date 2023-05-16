package com.ww.geo.region.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lists {
    @SafeVarargs
    public static <T> List<T> newArrayList(T... elements) {
        ArrayList<T> list = new ArrayList<>(elements.length);
        Collections.addAll(list, elements);
        return list;
    }
}