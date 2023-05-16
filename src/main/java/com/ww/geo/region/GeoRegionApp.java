package com.ww.geo.region;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Zhanglele
 * @version 2023/5/15
 */
@SpringBootApplication(scanBasePackages = {"com.ww.geo.region.*"})
public class GeoRegionApp {
    public static void main(String[] args) {
        SpringApplication.run(GeoRegionApp.class, args);
    }
}
