package com.ryan.demo;


public class JolClassDemo {
    private volatile static String str = "ll";

    public static void main(String[] args) {
        System.out.println(str);
        ThreadLocal l = new ThreadLocal();
    }
}
