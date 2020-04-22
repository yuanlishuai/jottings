package com.ryan.leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

public class TwoSumTest {

    @Test
    public void twoSum() {
        int[] ints = TwoSum.twoSum(new int[]{2, 7, 11, 15}, 17);
        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }
}