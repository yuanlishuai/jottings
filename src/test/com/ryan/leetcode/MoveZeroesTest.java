package com.ryan.leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

public class MoveZeroesTest {

    @Test
    public void moveZeroes() {
        int[] nums = {2, 4, 0, 8, 0, 1, 0, 5, 0, 0, 8};
        MoveZeroes.moveZeroes(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i]);
        }
    }
}