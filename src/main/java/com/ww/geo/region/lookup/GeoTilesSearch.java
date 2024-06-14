package com.ww.geo.region.lookup;

import com.ww.geo.region.util.ByteUtil;
import com.ww.geo.region.util.GeoHashHelper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class GeoTilesSearch {
    private static final Logger log = LoggerFactory.getLogger(GeoTilesSearch.class);

    private static final Map<Character, Byte> baseIndex = new HashMap<>();
    public final static String[] base32 = {
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "b", "c", "d", "e", "f", "g", "h", "j", "k",
            "m", "n", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"
    };


    static {
        for (int i = 0; i < base32.length; i++) {
            baseIndex.put(base32[i].charAt(0), (byte) i);
        }
    }

    private final static char ZERO = '0';

    static Short getAreaId(double lat, double lng, long[] geoArr, int[] followArr, boolean nearby) {
        return getAreaId(GeoHashHelper.getGeoHash(lat, lng), geoArr, followArr, nearby);
    }

    static Short getAreaId(String geoHash, long[] geoArr, int[] followArr, boolean nearby) {
        if (StringUtils.length(geoHash) < 8) {
            return null;
        }
        if (geoHash.charAt(0) == ZERO) {
            //geoHash2Byte()不处理0开头的GeoHash,但是0区域在南极州不影响
            return null;
        }
        long geoHashLong = ByteUtil.bytes2Long(geoHash2Byte(geoHash)) >> 10;
        for (int i = 8; i > 1; i--) {
            geoHashLong = geoHashLong >> 5;
            int index = binarySearch(geoHashLong << 16, geoHash.charAt(i - 1), geoArr, followArr);
            if (!Objects.equals(index, -1)) {
                return (short) (geoArr[index] & 0xffff);
            }
        }
        return findNearby(geoHash, geoArr, followArr, nearby);
    }

    private static Short findNearby(String geoHash, long[] geoArr, int[] followArr, boolean nearby) {
        if (!nearby) {
            return null;
        }
        //下面逻辑是处理没有找到点,而查看附近距离最近的区域(误差最大为5位GeoHash的边长)
        //如国内省市区边界可能极小概率没有覆盖的情况
        boolean insideChina = ChinaMapUtil.isInsideChina(geoHash);
        if (!insideChina) {
            return null;
        }
        //判断8~5位geoHah附近的8个地区
        for (int k = 8; k >= 5; k--) {
            String[] geoHashExpand = GeoHashHelper.getGeoHashExpand(geoHash.substring(0, k));
            if (Objects.isNull(geoHashExpand)) {
                return null;
            }
            for (int i = 1; i < 8; i++) {
                int index = binarySearch(geoHash2long(geoHashExpand[i].substring(0, geoHashExpand[i].length() - 1)), geoHashExpand[i].charAt(geoHashExpand[i].length() - 1), geoArr, followArr);
                if (!Objects.equals(index, -1)) {
                    if (k != 8) {
                        log.warn("没有定位到地区,返回最接近的区域:geoHah:{},near:{}", geoHash, geoHashExpand[i]);
                    }
                    return (short) (geoArr[index] & 0xffff);
                }
            }
        }
        return null;
    }

    public static long geoHash2long(String geoHash) {
        return ByteUtil.bytes2Long(geoHash2Byte(geoHash)) << 16;
    }


    /**
     * 二分查找
     *
     * @return 索引
     */
    private static int binarySearch(long key, char lastChar, long[] geoArr, int[] followArr) {
        int low = 0;
        int high = geoArr.length - 1;
        int middle = 0;

        if (Boolean.FALSE.equals(compareByte(key, geoArr[low])) || Boolean.TRUE.equals(compareByte(key, geoArr[high]))) {
            return -1;
        }

        while (low <= high) {
            middle = (low + high) / 2;
            Boolean keyLarge = compareByte(key, geoArr[middle]);
            if (Boolean.FALSE.equals(keyLarge)) {
                //比关键字大则关键字在左区域
                high = middle - 1;
            } else if (Boolean.TRUE.equals(keyLarge)) {
                //比关键字小则关键字在右区域
                low = middle + 1;
            } else {
                Byte index = baseIndex.get(lastChar);
                //判断geoHash最后一位
                if (((followArr[middle] >> index) & 1) == 1) {
                    return middle;
                }

                int diffHigh = high - middle;
                for (int i = 1; i <= diffHigh; i++) {
                    int pos = middle + i;
                    if (compareByte(key, geoArr[pos]) != null) {
                        //说明前几位都不相同跳出这个for
                        break;
                    }
                    if (((followArr[pos] >> index) & 1) == 1) {
                        return pos;
                    }
                }
                int diffLow = middle - low;
                for (int i = 1; i <= diffLow; i++) {
                    int pos = middle - i;
                    if (compareByte(key, geoArr[pos]) != null) {
                        //说明前几位都不相同跳出这个for
                        break;
                    }
                    if (((followArr[pos] >> index) & 1) == 1) {
                        return pos;
                    }
                }
                return -1; //最后仍然没有找到，则返回-1
            }
        }
        return -1; //最后仍然没有找到，则返回-1
    }

    public static void main(String[] args) {
        String w = suffix2Hex("w");
        String hex8 = StringUtils.leftPad(w, 8, "0");

        int i = ByteUtil.byte2int(ByteUtil.getPrintBytesFromString(hex8), 0);
        Byte index = baseIndex.get('w');
        boolean b = ((i >> index) & 1) == 1;

    }

    /**
     * 自定义比较两个long后六个字节大小大小
     * max > min 返回true
     * max > min 返回false
     * max = min 返回null
     */
    private static Boolean compareByte(long max, long min) {
        min &= 0xffffffffffff0000L;
        if (max > min) {
            return true;
        } else if (max < min) {
            return false;
        }
        return null;
    }


    /**
     * geoHash转换为字节(一个字节8位bit,一个geoHash长度占5位)
     * 如:9位长的geoHash字符串占9个字节,转换为byte[]占6个字节
     * 00088888 77777666 66555554 44443333 32222211 11100000(123只是代表不同字符的bit在byte中所占位置)
     * 00001000 00111001 10001010 01000001 10001000 00100000  <-- geoHash2Byte("876543210")
     * 000 01000 00111 00110 00101 00100 00011 00010 00001 00000
     * 8       7    6     5    4      3     2     1     0
     * 此方法一定程度上节约内存,且方便比较大小,按顺序排序,方便后续二分查找
     */
    private static byte[] geoHash2Byte(String geoHash) {
        if (StringUtils.isBlank(geoHash)) {
            return new byte[0];
        }
        int length = geoHash.length();
        byte[] bytes = new byte[8];
        if (length > 0) {
            //length-i-1是为了反转字符串,否则会认为 如"ww00"=="ww"
            //反转后影响的是"0ww"=="ww",所以geoHash2Byte不能处理0开头geoHash,但是0区域在南极,所以不影响
            byte b0 = baseIndex.get(geoHash.charAt(length - 1));
            bytes[7] = b0;
        }
        if (length > 1) {
            byte b1 = baseIndex.get(geoHash.charAt(length - 2));
            bytes[7] |= (b1 << 5);
            bytes[6] = (byte) (b1 >> 3);
        }
        if (length > 2) {
            byte b2 = baseIndex.get(geoHash.charAt(length - 3));
            bytes[6] |= (b2 << 2);
        }
        if (length > 3) {
            byte b3 = baseIndex.get(geoHash.charAt(length - 4));
            bytes[6] |= (b3 << 7);
            bytes[5] = (byte) (b3 >> 1);
        }
        if (length > 4) {
            byte b4 = baseIndex.get(geoHash.charAt(length - 5));
            bytes[5] |= (b4 << 4);
            bytes[4] = (byte) (b4 >> 4);
        }
        if (length > 5) {
            byte b5 = baseIndex.get(geoHash.charAt(length - 6));
            bytes[4] |= (b5 << 1);
        }
        if (length > 6) {
            byte b6 = baseIndex.get(geoHash.charAt(length - 7));
            bytes[4] |= (b6 << 6);
            bytes[3] = (byte) (b6 >> 2);
        }
        if (length > 7) {
            byte b7 = baseIndex.get(geoHash.charAt(length - 8));
            bytes[3] |= (b7 << 3);
        }
        if (length > 8) {
            byte b8 = baseIndex.get(geoHash.charAt(length - 9));
            bytes[2] = b8;
        }
        if (length > 9) {
            byte b9 = baseIndex.get(geoHash.charAt(length - 10));
            bytes[2] |= (b9 << 5);
            bytes[1] = (byte) (b9 >> 3);
        }
        return bytes;
    }

    /**********************************base32字符与int转换***********************************************/
    // 后缀转int字节并去除字符串左侧的0值
    public static String suffix2Hex(String suffix) {
        int result = 0;
        for (int i = 0; i < suffix.length(); i++) {
            Byte aByte = baseIndex.get(suffix.charAt(i));
            result |= (0x01 << aByte);
        }
        String printString = ByteUtil.getPrintString(ByteUtil.int2byte(result));
        // 去除字符串左侧的0值
        // "000000ff"
        return printString.replaceAll("^(0+)", "");
    }

    // 后缀转int字节并去除字符串左侧的0值
    public static String hex2Suffix(String hexStr) {
        String hex8 = StringUtils.leftPad(hexStr, 8, "0");
        StringBuilder s = new StringBuilder();
        int hexInt = ByteUtil.byte2int(ByteUtil.getPrintBytesFromString(hex8), 0);
        for (int i = 0; i < 32; i++) {
            if (((hexInt >> i) & 0x01) == 0x01) {
                s.append(base32[i]);
            }

        }
        if (StringUtils.isBlank(s.toString())) {
            System.out.println("error");
        }
        return s.toString();
    }

    /**********************************自定义经纬度转long值***********************************************/

    private static long getGeoHashLongValue(double lat, double lng, int length) {
        long latBits = getLatBits(lat, length);
        long lngBits = getLngBits(lng, length);

        return reverseBit(latBits | lngBits, length * 5);
    }

    private static long getLngBits(double lng, int length) {
        double floor = -180;
        double ceiling = 180;
        long lngGeoHashLong = 0;
        for (int i = 0; i < 5 * length; i += 2) {

            double mid = (floor + ceiling) / 2;
            if (lng >= mid) {
                lngGeoHashLong |= (1L << i);
                floor = mid;
            } else {
                ceiling = mid;
            }
        }
        return lngGeoHashLong;
    }

    private static long getLatBits(double lng, int length) {
        long lngGeoHashLong = 0;
        double floor = -90;
        double ceiling = 90;
        for (int i = 1; i < 5 * length; i += 2) {
            double mid = (floor + ceiling) / 2;
            if (lng >= mid) {
                lngGeoHashLong |= (1L << i);
                floor = mid;
            } else {
                ceiling = mid;
            }
        }
        return lngGeoHashLong;
    }

    private static long reverseBit(Long l, int length) {
        long newL = 0;
        for (int i = 0; i < length; i++) {
            newL |= (l & 1);
            if (i != length - 1) {
                newL <<= 1;
                l >>= 1;
            }

        }
        return newL;
    }


    //快排实现方法
    public static void quickSort(long[] array, int[] followArray, int low, int high) {
        int i, j;
        //结束条件
        if (low >= high) {
            return;
        }
        i = low;
        j = high;
        //选择的节点，这里选择的数组的第一数作为节点
        long pivot = array[low];
        while (i < j) {
            //从右往左找比节点小的数，循环结束要么找到了，要么i=j
            while (array[j] >= pivot && i < j) {
                j--;
            }
            //从左往右找比节点大的数，循环结束要么找到了，要么i=j
            while (array[i] <= pivot && i < j) {
                i++;
            }
            //如果i!=j说明都找到了，就交换这两个数
            if (i < j) {
                long temp = array[i];
                array[i] = array[j];
                array[j] = temp;

                //并交互关联数组的下标
                int followTemp = followArray[i];
                followArray[i] = followArray[j];
                followArray[j] = followTemp;
            }
        }
        //i==j一轮循环结束，交换节点的数和相遇点的数
        array[low] = array[i];
        array[i] = pivot;
        //并交互关联数组的下标
        int followPivot = followArray[low];
        followArray[low] = followArray[i];
        followArray[i] = followPivot;

        //数组“分两半”,再重复上面的操作
        quickSort(array, followArray, low, i - 1);
        quickSort(array, followArray, i + 1, high);
    }

    /**********************************打印字节bit***********************************************/
    public static String bytes2BitStr(byte[] bytes) {
        List<String> bitStr = new ArrayList<>();
        for (byte aByte : bytes) {
            String bit = byte2BitStr(aByte);
            bitStr.add(bit);
        }
        Collections.reverse(bitStr);
        return String.join(" ", bitStr);
    }

    public static String byte2BitStr(byte byt) {
        boolean bit7 = (byt & 0b10000000) == 0b10000000;
        boolean bit6 = (byt & 0b01000000) == 0b01000000;
        boolean bit5 = (byt & 0b00100000) == 0b00100000;
        boolean bit4 = (byt & 0b00010000) == 0b00010000;
        boolean bit3 = (byt & 0b00001000) == 0b00001000;
        boolean bit2 = (byt & 0b00000100) == 0b00000100;
        boolean bit1 = (byt & 0b00000010) == 0b00000010;
        boolean bit0 = (byt & 0b00000001) == 0b00000001;
        String s = "";
        if (bit7) {
            s += 1;
        } else {
            s += 0;
        }
        if (bit6) {
            s += 1;
        } else {
            s += 0;
        }
        if (bit5) {
            s += 1;
        } else {
            s += 0;
        }
        if (bit4) {
            s += 1;
        } else {
            s += 0;
        }
        if (bit3) {
            s += 1;
        } else {
            s += 0;
        }
        if (bit2) {
            s += 1;
        } else {
            s += 0;
        }
        if (bit1) {
            s += 1;
        } else {
            s += 0;
        }
        if (bit0) {
            s += 1;
        } else {
            s += 0;
        }
        return s;
    }
}
