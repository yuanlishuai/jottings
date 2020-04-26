package com.ryan.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 *
 * @author yuanls
 * @createdate 2020/4/21 21:13
 * @vesion 1.0
 */

public class DequeTest {

    public static void main(String[] args) {
        Deque<String> deque = new LinkedList<>();
        deque.addFirst("a");
        deque.add("b");
        deque.addLast("c");
        System.out.println(deque);

        String s = deque.peekFirst();
        System.out.println(s);
        System.out.println(deque);

        while (deque.size() > 0) {
            System.out.print(deque.pop());
        }
        System.out.println();
        System.out.println(deque);
    }
}
