package com.ww.geo.region.enums;

import java.util.Arrays;
import java.util.Comparator;

/**
 * GeoHash长度和对应的误差范围
 */

public enum PrecisionEnum {

    _1(1, 2, 3, 23, 23, 2500),
    _2(2, 5, 5, 2.8, 5.6, 630),
    _3(3, 7, 8, 0.70, 0.7, 78),
    _4(4, 10, 10, 0.087, 0.18, 20),
    _5(5, 12, 13, 0.022, 0.022, 2.4),
    _6(6, 15, 15, 0.0027, 0.0055, 0.61),
    _7(7, 17, 18, 0.00068, 0.00068, 0.076),
    _8(8, 20, 20, 0.000086, 0.000172, 0.01911),
    _9(9, 22, 23, 0.000021, 0.000021, 0.00478),
    _10(10, 25, 25, 0.00000268, 0.00000536, 0.0005971),
    _11(11, 27, 28, 0.00000067, 0.00000067, 0.0001492),
    _12(12, 30, 30, 0.00000008, 0.00000017, 0.0000186),

    ;
    //geohash长度
    private final int precision;
    //Lat位数
    private final int latLength;
    //Lng位数
    private final int lngLength;
    //lat误差绝对值（实际上误差范围是 ±N）
    private final double lagDeviationAbs;
    //lng误差绝对值（实际上误差范围是 ±N）
    private final double lngDeviationAbs;
    //km误差绝对值（实际上误差范围是 ±N）
    private final double kmDeviationAbs;

    PrecisionEnum(int precision, int latLength, int lngLength, double lagDeviationAbs, double lngDeviationAbs, double kmDeviationAbs) {
        this.precision = precision;
        this.latLength = latLength;
        this.lngLength = lngLength;
        this.lagDeviationAbs = lagDeviationAbs;
        this.lngDeviationAbs = lngDeviationAbs;
        this.kmDeviationAbs = kmDeviationAbs;
    }

    public int getPrecision() {
        return precision;
    }

    public int getLatLength() {
        return latLength;
    }

    public int getLngLength() {
        return lngLength;
    }

    public double getLagDeviationAbs() {
        return lagDeviationAbs;
    }

    public double getLngDeviationAbs() {
        return lngDeviationAbs;
    }

    public double getKmDeviationAbs() {
        return kmDeviationAbs;
    }

    public static PrecisionEnum matchPrecisionByDeviation(int miles) {
        return Arrays.stream(values()).sorted(Comparator.comparing(PrecisionEnum::getPrecision, Comparator.reverseOrder()))
                .filter(v -> {
                    //因为枚举存的是绝对值，例如当kmDeviationAbs为0.61的时候，实际上误差范围是±0.61km，±610m
                    return (v.getKmDeviationAbs() * 1000) > miles;
                }).findFirst()
                .orElseThrow(() -> new RuntimeException("can't match any precision value."));
    }
}
