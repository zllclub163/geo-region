package com.ww.geo.region.util;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;

public class ByteUtil {

    public static byte xor(byte[] bytes, int start, int end) {
        int result = bytes[start];
        for (int i = start + 1; i < end; i++) {
            result = result ^ (int) bytes[i];
        }
        return (byte) result;
    }

    /**
     * 得到无符号的整数, unsgined short
     *
     * @param high
     * @param low
     * @return
     */
    public static int byte2short(byte high, byte low) {
        int val = (((high & 0xFF) << 8) | (low & 0xFF));
        return val;
    }

    /**
     * 带符号的整数, signed short
     *
     * @param high
     * @param low
     * @return
     */
    public static int byte2signedshort(byte high, byte low) {
        int val = high << 8 | (low & 0xFF);
        return val;
    }

    public static int byte2short(byte[] bytes) {
        return byte2short(bytes[0], bytes[1]);
    }

    public static byte[] int2byte(int value) {
        return new byte[]{
                (byte) (value >>> 24),
                (byte) (value >>> 16),
                (byte) (value >>> 8),
                (byte) value};
    }

    public static byte[] short2byte(int value) {
        return new byte[]{
                (byte) (value >> 8),
                (byte) (value)};
    }

    public static int byte2int(byte[] bytes, int start) {
        return (bytes[start] << 24) & 0xff000000 |
                (bytes[start + 1] << 16) & 0x00ff0000 |
                (bytes[start + 2] << 8) & 0x0000ff00 |
                (bytes[start + 3]) & 0x000000ff;
    }

    public static int byte2signedint(byte[] bytes, int start) {
        return ((bytes[start] << 24) | (bytes[start + 1] & 0xFF) << 16
                | (bytes[start + 2] & 0xFF) << 8 | bytes[start + 3] & 0xFF);
    }


    public static String getPrintString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public static byte[] getTimeInBytes(long timestamp, ZoneOffset zoneOffset) {
        LocalDateTime ldt = LocalDateTime.ofEpochSecond(timestamp, 0, zoneOffset);
        return new byte[]{(byte) (ldt.getYear() - 2000), (byte) ldt.getMonthValue(),
                (byte) ldt.getDayOfMonth(), (byte) ldt.getHour(), (byte) ldt.getMinute(),
                (byte) ldt.getSecond()};
    }


    public static byte[] getCurrentTimeInNumber() {
        int time = (int) (System.currentTimeMillis() / 1000);
        return int2byte(time);
    }


    private static final Map<Character, Byte> charByteMap = new HashMap<>();

    static {
        charByteMap.put('0', (byte) 0x00);
        charByteMap.put('1', (byte) 0x01);
        charByteMap.put('2', (byte) 0x02);
        charByteMap.put('3', (byte) 0x03);
        charByteMap.put('4', (byte) 0x04);
        charByteMap.put('5', (byte) 0x05);
        charByteMap.put('6', (byte) 0x06);
        charByteMap.put('7', (byte) 0x07);
        charByteMap.put('8', (byte) 0x08);
        charByteMap.put('9', (byte) 0x09);
        charByteMap.put('a', (byte) 0x0a);
        charByteMap.put('b', (byte) 0x0b);
        charByteMap.put('c', (byte) 0x0c);
        charByteMap.put('d', (byte) 0x0d);
        charByteMap.put('e', (byte) 0x0e);
        charByteMap.put('f', (byte) 0x0f);
    }

    /**
     * 根据hex字符串的字符来生成对应的byte
     * 比如 b9a8 生成byte b9a8
     *
     * @param s 字符串
     * @return byte[]
     */
    public static byte[] getPrintBytesFromString(String s) {
        byte[] byteReuslt = new byte[s.length() / 2];
        for (int i = 0; i < s.length() - 1; i += 2) {
            byte result = (byte) (((charByteMap.get(s.charAt(i)) << 4) + charByteMap.get(s.charAt(i + 1))) & 0xff);
            byteReuslt[i / 2] = result;
        }
        return byteReuslt;
    }

    //每次截取8位，然后左移8,
    public static byte[] longToBytes(long val) {
        byte[] b = new byte[8];
        for (int i = 7; i > 0; i--) {
            //强制转型，后留下长整形的低8位
            b[i] = (byte) val;
            //向右移动8位，则第二次循环则计算第二个8位数
            val >>>= 8;
        }
        b[0] = (byte) val;
        return b;
    }

    public static long bytes2Long(byte[] bytes) {
        int length = bytes.length;
        for (int i = 0; i < length / 2; i++) {
            byte a = bytes[i];
            bytes[i] = bytes[length - i - 1];
            bytes[length - i - 1] = a;
        }
        switch (length) {
            case 1:
                return bytes[0] & 0xff;
            case 2:
                return ((bytes[1] & 0xff) << 8)
                        | ((bytes[0] & 0xff));
            case 3:
                return ((bytes[2] & 0xff) << 16)
                        | ((bytes[1] & 0xff) << 8)
                        | ((bytes[0] & 0xff));
            case 4:
                return ((long) (bytes[3] & 0xff) << 24)
                        | ((bytes[2] & 0xff) << 16)
                        | ((bytes[1] & 0xff) << 8)
                        | ((bytes[0] & 0xff));
            case 5:
                return ((long) (bytes[4] & 0xff) << 32)
                        | ((long) (bytes[3] & 0xff) << 24)
                        | ((bytes[2] & 0xff) << 16)
                        | ((bytes[1] & 0xff) << 8)
                        | ((bytes[0] & 0xff));
            case 6:
                return ((long) (bytes[5] & 0xff) << 40)
                        | ((long) (bytes[4] & 0xff) << 32)
                        | ((long) (bytes[3] & 0xff) << 24)
                        | ((bytes[2] & 0xff) << 16)
                        | ((bytes[1] & 0xff) << 8)
                        | ((bytes[0] & 0xff));

            case 7:
                return ((long) (bytes[6] & 0xff) << 48)
                        | ((long) (bytes[5] & 0xff) << 40)
                        | ((long) (bytes[4] & 0xff) << 32)
                        | ((long) (bytes[3] & 0xff) << 24)
                        | ((bytes[2] & 0xff) << 16)
                        | ((bytes[1] & 0xff) << 8)
                        | ((bytes[0] & 0xff));

            case 8:
                return ((long) (bytes[7] & 0xff) << 56)
                        | ((long) (bytes[6] & 0xff) << 48)
                        | ((long) (bytes[5] & 0xff) << 40)
                        | ((long) (bytes[4] & 0xff) << 32)
                        | ((long) (bytes[3] & 0xff) << 24)
                        | ((bytes[2] & 0xff) << 16)
                        | ((bytes[1] & 0xff) << 8)
                        | ((bytes[0] & 0xff));
        }
        return 0;
    }

}