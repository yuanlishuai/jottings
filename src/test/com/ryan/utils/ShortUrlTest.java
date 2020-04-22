package com.ryan.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class ShortUrlTest {

    @Test
    public void toBase62() {
        String str = "yuanlishuai";
        long l = ShortUrl.toBase10(str);
        System.out.println(l);
    }

}