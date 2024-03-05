package com.ww.geo.region.lookup;

import com.ww.geo.region.util.ByteUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

import static com.ww.geo.region.lookup.GeoTilesSearch.geoHash2long;


/**
 * 根据GeoHash(WGS84坐标)判断经纬度是否在澳门
 * zllclub@163.com
 *
 * @author Zhanglele
 * @version 2023/7/24
 */

public class MacaoMapUtil {

    private static final int geoTilesLength = 335;
    private static final long[] geoHashLongArr = new long[geoTilesLength];
    private static final int[] followArr = new int[geoTilesLength];

    //例u3w:f0f00000代表前缀都是u2w的GeoHash,冒号后为16进制的四个字节(默认去掉前面0)四个字节为32bit分别对应base32字符的索引
    private static final String geoHashCompressStr = "weby:1,weby1:3700ff,weby2:f1f,webwp:fe00c800,webwr:cc00,webv8:ff00a000,webv9:f30020,webvb:fffffffe,webvc:ff00ff,webtzf:ffff0000,webtzg:ffff0000,weby1t:1,webtzb:e8000000,webtzc:fffe0000,weby1r:1f7f,weby1s:f7f,weby32:5,weby1q:51fffff,weby30:7fffffff,weby31:51fffff,webv97:f04,weby1m:7fffffff,webwpk:80000000,webtzy:faff0000,webv8r:ffffffc0,webtzv:ffff0000,webv8p:c8000000,webv8q:ffff0000,webtzu:ffff0000,webwpe:fffff8f0,webvc9:5f5fffff,webwpd:ffffa000,webvc8:f05ffff,webv8m:ffff0000,webwpb:fffffee0,webv8k:ffff0000,webwr9:fffff0f0,webwr8:fffff2fa,webwps:fffffffe,webwpq:f2fa0000,webwpr:20f00000,webwpm:f8e00000,webtzz:fffa0000,webv9m:4f0dffff,webv9k:400ff5f,weby18:5f5fffff,weby19:5f5fffff,webvce:5f5fffff,webvcd:5f5fffff,webv9w:d00,webv9x:df4f,webwre:b3ff32fa,webvcw:5f5fffff,webvcx:5f5fffff,webwrd:fffff0f0,webvcs:5f5fffff,webvct:5f5fffff,webwp9:fae00000,weby25:3f37ff,weby26:1f5f,webwru:10033,webv87:ffff0000,weby1d:5f5fffff,weby1e:10fffff,webvb0:ffffffe8,webv8mg:cc00cc00,webtzue:cc00cc00,webtzf7:ff00ff00,webtzf5:ff00ff4c,webv8me:cc00cc00,webtzug:cc00cc00,weby26j:ffff,weby26p:ffff,weby26n:ffff,weby1et:ff37ff,webtzv5:c00,weby1eq:3007f,weby26h:ffff,weby26g:33,weby26f:3fffffff,weby1ev:13,weby26e:7f00ff,weby1eu:3fffffff,weby1en:37ffffff,weby25s:77fffff,weby274:7,weby25r:77fff,weby25q:5fffffff,weby272:7,weby25w:1,weby25t:11fff,webv9mx:330000,webv8m7:cc00cc00,webv9mw:fffffbff,weby271:77fff,weby270:7fffffff,webv9mz:f3ff20ff,weby25c:1fffffff,webtzu5:cc00cc00,webv9mq:30ff00b3,webv9mn:200000,weby25g:11f,webv8m5:cc00cc00,webtzcg:ff00ff00,weby25f:77fff,webtzch:fffffffe,webtzu7:cc00cc00,webtzce:fc00cc00,webv8kg:cc00cc00,webv8ke:cc00cc00,weby2fj:ffff,weby2fp:ffff,weby2h1:1,webv9mj:ffff32ff,weby2h0:77f,weby2fn:ffff,webtzbr:fee00000,webtzbs:80000000,weby2fh:ffff,weby267:ff00ff,webtzbw:fffffee0,webtzbt:fef80000,weby265:ffffff,webtzbu:ffccffc8,weby1tb:1,webwr8c:ffccffdf,webwpeb:ffc8fe00,webwr93:cc00cf00,webwr99:c00,webtzc7:cc000000,webv9kv:f30020,webv8k7:cc00cc00,weby1sh:1301ff,webv8k5:cc00cc00,webwr91:ff00ff4c,weby2f4:ffff,weby2dp:ffff,weby2f1:ffff,weby1sf:3703ff,weby1se:13,webv9ks:b3ff00fb,weby1sd:3fffffff,weby2f5:ffff,weby2dj:ffff,weby2dh:ffff,weby1t8:3703ff,weby2f0:ffff,weby2dn:ffff,webv9kk:300000,weby1t3:37,weby1t2:7fffffff,weby1t1:3ff7fff,webwr82:cc00ff04,webwr88:4c00,webwpdf:ffccff00,webwpdd:cc000000,weby34h:ffff,weby1rh:7f37ff,webwpe9:fffffffe,weby1rf:1ff37ff,webwpe8:c8000000,webwr80:ffdfffff,webv87g:cc00cc00,weby1rk:3,webv87e:cc00cc00,weby34j:1,webwpe3:ffccff00,webv86z:f0f00000,webwpe1:cc000000,webv86y:f0f00000,weby1re:3007f,weby1qw:1,weby1qv:3007f,weby1s7:1ff3fff,webv86v:f0f00000,webv86u:f0f00000,weby1qp:1003f,weby1qt:13ffffff,weby1qq:3f13ff,webv9k7:fffff3ff,webv9k5:20ff0032,webwpd7:ffccff00,webv86g:c0000000,weby2d1:ffff,weby2d0:ffff,weby2d5:ffff,webwpd5:cc000000,weby2d4:ffff,webwprw:cf00ffcd,weby1r7:37ffffff,webv877:cc00cc00,webwprz:ff0cffff,webv875:cc00cc00,webwpry:400,weby341:ffff,webv8fz:f0f00000,weby340:ffff,webwprm:c00de00,webv8fy:f0f00000,weby345:ffff,weby344:ffff,webwrey:400cf0c,webwrev:107,webv8fv:f0f00000,webv97f:fbff30ff,webwprj:80004c00,webv97d:b30000,webv8fu:f0f00000,webwreu:1f7f,weby32b:13,weby31v:3703ff,webv8fb:f0f00000,webv9xs:320000,webwpqv:ff04ffcc,webv8fc:f0f00000,weby31t:7fffffff,webwreg:ffff,webwref:ffdf,webv8ff:f0f00000,webv8fg:f0f00000,webv9xu:b3ff20ff,weby31w:37,webwruq:5,webwrup:10fffff,webwps0:fffffffe,webwrun:7fffffff,webwpqs:4c00,weby31q:3ff7fff,weby31p:3703ff,webwrec:4f04,webwpb8:fffcfc00,webvc8y:fffffbff,webv96b:f00000,webvc8w:32ff00f3,webwrum:330432,webwpqk:cc00cc00,webwruk:5f0dffff,webv9xe:fffff3ff,webwruj:ff5fffff,webwpqh:cc00cc00,webvc8q:200000,weby30z:7fffffff,webwpb4:ffffffc8,webwpb3:fffcfe80,webwpb1:c0000000,webvc8j:fb0030,webv973:32ff00f3,webv970:ffff33ff,webv971:200000,weby324:13,webvc8m:ffff33ff,weby328:1ff3fff,webv8dy:f0f00000,webv8dv:f0f00000,weby323:1301ff,webwrdc:ffecffcc,weby321:3fffffff,webwr2p:cd00,webv8dz:f0f00000,webwre8:4f04ffc8,webv9wf:f30020,webv8du:f0f00000,webwre0:c8000000,webv94u:f0f00000,weby1mz:13ffffff,webv8df:f0f00000,webv94v:f0f00000,webv8dg:f0f00000,webv94y:f0f00000,webwre2:ff00ec00,webv94z:f0f00000,webv9x4:fbff30ff,webwru7:50fffff,webwru6:70fffff,webv9x7:20ff00b3,webv8db:f0f00000,webv8dc:f0f00000,webv94g:f0f00000,webv94f:f0f00000,webwrsr:cd00,webwru3:f1fffff,webwru2:5f4dffff,webwrsp:ff04ffcd,webv94c:f0f00000,webwrd9:fc000000,webv94b:f0f00000,webv9w9:32ff00fb,webv9w2:ffffb3ff,webv9w3:300000,webv9w0:fb0032,webwpmu:c0000000,webwpmt:fffcffcc,webtzzk:ffecffcc,webwpmj:fc008000,webtzzh:ffccffcc,webwpmn:fffffffe,webwpmm:ffc0ff00,webtzzf:ec000000,webtzzg:fffffffc,webtzze:ff80c000,webtzyu:ffccffcc,webtzys:ffccffff,webv8qe:cc00cc00,webv8qg:cc00cc00,webv8pt:f0800000,webwpky:ffc0ec00,webv8pu:fffff880,webwpkx:fffeffc0,webv8r4:fff88000,webv8pr:e0000000,webv8r5:fffffff8,webv8px:fffffffe,webv8pw:fffee000,webwpkp:c8000000,webv8r2:fffee000,webv8r3:fffffffe,webv8r1:e0000000,webwpkr:ff00fe00,webwp9u:ffccff80,webv8pg:f8800000,webwp9n:ffc0e800,webwp9m:ffc0c000,webwp9s:ec008000,webv8q5:cc00cc00,webtzgg:cc00cc00,webtzge:cc00cc00,webv8q7:cc00cc00,webwp8z:ffe8e800,webtzfg:ff00ff00,webtzfe:ff00ff00,webtzg7:cf00ff00,webtzg5:ff00ff00,webvb02:fffffee0,webvb01:f8e00000,webvb04:fffff880";

    static {
        String[] geoHashCompressArr = geoHashCompressStr.split(",");
        int geoHashCount = 0;
        for (String geoHashCompress : geoHashCompressArr) {
            String[] samePrefixGeoHashes = geoHashCompress.split(":");
            String hex8 = StringUtils.leftPad(samePrefixGeoHashes[1], 8, "0");
            long geoK = geoHash2long(samePrefixGeoHashes[0]);
            geoHashLongArr[geoHashCount] = geoK;
            followArr[geoHashCount] = ByteUtil.byte2int(ByteUtil.getPrintBytesFromString(hex8), 0);
            geoHashCount++;
        }
        GeoTilesSearch.quickSort(geoHashLongArr, followArr, 0, geoHashLongArr.length - 1);
    }

    public static boolean isInsideMacao(double lat, double lng) {
        return Objects.nonNull(GeoTilesSearch.getAreaId(lat, lng, geoHashLongArr, followArr, false));
    }

    public static boolean isInsideMacao(String geoHash) {
        return Objects.nonNull(GeoTilesSearch.getAreaId(geoHash, geoHashLongArr, followArr, false));
    }
}
