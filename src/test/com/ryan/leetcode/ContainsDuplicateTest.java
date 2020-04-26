package com.ryan.leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

public class ContainsDuplicateTest {

    @Test
    public void containsDuplicate() {
        boolean b = ContainsDuplicate.containsDuplicate(new int[]{1, 2});
        assertFalse(b);
    }

    @Test
    public void containsDuplicateTest() {
        boolean b = ContainsDuplicate.containsDuplicate(new int[]{1, 2, 2});
        assertTrue(b);
    }
}