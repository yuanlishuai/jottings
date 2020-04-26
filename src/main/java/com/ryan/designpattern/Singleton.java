package com.ryan.designpattern;

import java.util.Objects;

/**
 * 单例模式 --单线程
 * 单例是一种创建型设计模式， 让你能够保证一个类只有一个实例， 并提供一个访问该实例的全局节点。
 *
 *  隐藏构造函数  ->  实现一个静态的构造方法
 *
 * @author yuanls
 * @createdate 2020/4/26 11:04
 * @vesion 1.0
 */

public final class Singleton {

    private static Singleton instance;
    private String  value;

    private Singleton(String value){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
           e.printStackTrace();
        }
        this.value = value;
    }

    public static Singleton getInstance(String value){
        if (Objects.isNull(instance)){
            instance = new Singleton(value);
        }
        return instance;
    }

   /* public static void main(String[] args) {
        System.out.println("If you see the same value, then singleton was reused (yay!)" + "\n" +
                "If you see different values, then 2 singletons were created (booo!!)" + "\n\n" +
                "RESULT:" + "\n");
        Singleton singleton = Singleton.getInstance("FOO");
        Singleton anotherSingleton = Singleton.getInstance("BAR");
        Singleton threeSingleton = Singleton.getInstance("YLS");
        System.out.println(singleton  +"    "+ singleton.value);
        System.out.println(anotherSingleton  +"    "+ anotherSingleton.value);
        System.out.println(threeSingleton  +"    "+ threeSingleton.value);
     }*/

    public static void main(String[] args) {
        System.out.println("If you see the same value, then singleton was reused (yay!)" + "\n" +
                "If you see different values, then 2 singletons were created (booo!!)" + "\n\n" +
                "RESULT:" + "\n");
        Thread threadFoo = new Thread(new ConcurrentSingleton.ThreadFoo());
        Thread threadBar = new Thread(new ConcurrentSingleton.ThreadBar());
        threadFoo.start();
        threadBar.start();
    }

    static class ThreadFoo implements Runnable {
        @Override
        public void run() {
            Singleton singleton = Singleton.getInstance("FOO");
            System.out.println(singleton.value);
        }
    }

    static class ThreadBar implements Runnable {
        @Override
        public void run() {
            Singleton singleton = Singleton.getInstance("BAR");
            System.out.println(singleton.value);
        }
    }
}
