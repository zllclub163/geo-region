package com.ww.geo.region.lookup;

import com.alibaba.fastjson.JSONObject;
import com.github.luben.zstd.ZstdInputStream;
import com.ww.geo.region.util.ByteUtil;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.ww.geo.region.lookup.GeoTilesSearch.geoHash2long;


/**
 * 根据GeoHash(WGS84坐标)判断经纬度是否在哪个国家
 * Antarctica
 * American Samoa
 * Aruba
 * Åland
 * Saint Barthélemy
 * Bonaire
 * Bahamas
 * Bouvet Island
 * Cocos [Keeling] Islands
 * Republic of the Congo
 * Curacao
 * Christmas Island
 * Western Sahara
 * French Guiana
 * Guadeloupe
 * Guam
 * Hong Kong
 * Heard Island and McDonald Islands
 * Saint Martin
 * Macao
 * Northern Mariana Islands
 * Martinique
 * New Caledonia
 * Norfolk Island
 * French Polynesia
 * Saint Pierre and Miquelon
 * Puerto Rico
 * Réunion
 * Saint Helena
 * Svalbard and Jan Mayen
 * Sint Maarten
 * Swaziland
 * French Southern Territories
 * U.S. Minor Outlying Islands
 * U.S. Virgin Islands
 * Wallis and Futuna
 * Mayotte
 * CountryEnum中以上国家暂时没有数据,如有需要,请联系
 * zllclub@163.com
 *
 * @author Zhanglele
 * @version 2022/11/7
 */
public class GlobalMapHelper {
    private static final Logger log = LoggerFactory.getLogger(GlobalMapHelper.class);

    private static final String SOURCE = "GEO_DATA_COUNTRY.tar.zstd";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String GEOS = "geos";

    private static final int geoTilesLength = 1247367;
    private static final long[] geoArr = new long[geoTilesLength];
    private static final int[] followArr = new int[geoTilesLength];
    public Map<Short, String> isoMap;


    private GlobalMapHelper() {
        init();
    }

    private static class GlobalMapHelperHolder {
        private static final GlobalMapHelper INSTANCE = new GlobalMapHelper();
    }

    public static GlobalMapHelper getInstance() {
        return GlobalMapHelperHolder.INSTANCE;
    }

    public String getISOName(double lat, double lng) {
        return isoMap.get(GeoTilesSearch.getAreaId(lat, lng, geoArr, followArr, false));
    }


    private void init() {
        List<JSONObject> geoSourceData = readFileFromZSTD();
        long start = System.currentTimeMillis();
        log.info("GlobalMapHelper init....");
        AtomicInteger areaCount = new AtomicInteger(0);
        geoSourceData.forEach(geoAreaPolygonsBean -> {
            String[] geos = geoAreaPolygonsBean.getString(GEOS).split(",");
            for (String geo : geos) {
                String[] split2 = geo.split(":");
                String hex8 = StringUtils.leftPad(split2[1], 8, "0");
                long geoK = geoHash2long(split2[0]);
                int index = areaCount.get();
                geoArr[index] = geoK | geoAreaPolygonsBean.getShort(ID);
                followArr[index] = ByteUtil.byte2int(ByteUtil.getPrintBytesFromString(hex8), 0);
                areaCount.incrementAndGet();
            }
        });
        GeoTilesSearch.quickSort(geoArr, followArr, 0, geoArr.length - 1);
        isoMap = geoSourceData.stream().collect(Collectors
                .toMap(jsonObject -> jsonObject.getShort(ID), jsonObject -> jsonObject.getString(NAME)));
        log.info("GlobalMapHelper init completed, time consuming" + (System.currentTimeMillis() - start) + "ms...");
    }

    private List<JSONObject> readFileFromZSTD() {
        try (InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(GlobalMapHelper.SOURCE)) {
            try (ZstdInputStream unzipStream = new ZstdInputStream(resourceAsStream)) {
                try (BufferedInputStream bufferedStream = new BufferedInputStream(unzipStream)) {
                    try (TarArchiveInputStream shapeInputStream = new TarArchiveInputStream(bufferedStream)) {
                        Spliterator<TarArchiveEntry> tarArchiveEntrySpliterator = makeSpliterator(shapeInputStream);
                        AtomicInteger id = new AtomicInteger(0);
                        return StreamSupport.stream(tarArchiveEntrySpliterator, false).map(n -> {
                            try {
                                if (n != null) {
                                    byte[] e = new byte[(int) n.getSize()];
                                    shapeInputStream.read(e);
                                    JSONObject jsonObject = new JSONObject();
                                    jsonObject.put(GEOS, new String(e, StandardCharsets.UTF_8));
                                    jsonObject.put(NAME, n.getName());
                                    jsonObject.put(ID, id.getAndIncrement());
                                    return jsonObject;
                                } else {
                                    throw new RuntimeException("Data entry is not found in file");
                                }
                            } catch (NullPointerException | IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        }).collect(Collectors.toList());
                    }
                }
            }
        } catch (NullPointerException | IOException e) {
            log.error("Unable to read resource file", e);
            throw new RuntimeException(e);
        }
    }

    private static Spliterator<TarArchiveEntry> makeSpliterator(TarArchiveInputStream f) {
        return new Spliterators.AbstractSpliterator<TarArchiveEntry>(Long.MAX_VALUE, 0) {
            @Override
            public boolean tryAdvance(Consumer<? super TarArchiveEntry> action) {
                try {
                    TarArchiveEntry entry = f.getNextTarEntry();
                    if (entry != null) {
                        action.accept(entry);
                        return true;
                    } else {
                        return false;
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }

    private List<JSONObject> readFile() {
        List<JSONObject> geoSourceData = new ArrayList<>();
        InputStream file = Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream(GlobalMapHelper.SOURCE));
        BufferedReader br = new BufferedReader(new InputStreamReader(file));
        String line;
        try {
            while ((line = br.readLine()) != null) {
                geoSourceData.add(JSONObject.parseObject(line));
            }
        } catch (Exception e) {
            log.error("GlobalMapHelper.SOURCE文件读取失败", e);
        } finally {
            try {
                br.close();
                file.close();
            } catch (IOException e) {
                log.error("GlobalMapHelper.SOURCE文件关闭失败", e);
            }
        }
        short i = 0;
        for (JSONObject geoAreaPolygonsBean : geoSourceData) {
            geoAreaPolygonsBean.put(ID, i);
            i++;
        }
        return geoSourceData;
    }


    public static void main(String[] args) throws InterruptedException {
        long l = System.currentTimeMillis();
        GlobalMapHelper.getInstance().getISOName(50.580368, 15.150155);
        System.out.println(System.currentTimeMillis() - l);
        for (int j = 0; j < 1000; j++) {
            long start = System.currentTimeMillis();
            for (int k = 0; k < 700000; k++) {
                double lat = Math.random() * 180 - 90;
                double lng = Math.random() * 360 - 180;
                String isoName = GlobalMapHelper.getInstance().getISOName(lat, lng);
            }

            long end = System.currentTimeMillis();
            System.out.println("================");
            System.out.println(end - start);
        }
    }

}
