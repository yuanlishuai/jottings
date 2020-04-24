package com.ryan.utils;

/**
 * @author yuanls
 * @createdate 2020/4/22 16:47
 * @modifier yuanls
 * @updatedate 2020/4/22 16:47
 * @vesion 1.0
 */

public class ShortUrl {

    private static final String BASE = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";


    /**
     * 将62进制转换为十进制
     *
     * @param input 62进制字符串
     * @return
     */
    public static long toBase10(String input) {
        int srcBase = BASE.length();
        long id = 0;
        String r = new StringBuilder(input).reverse().toString();

        for (int i = 0; i < r.length(); i++) {
            int charIndex = BASE.indexOf(r.charAt(i));
            id += charIndex * (long) Math.pow(srcBase, i);
        }

        return id;
    }


}
