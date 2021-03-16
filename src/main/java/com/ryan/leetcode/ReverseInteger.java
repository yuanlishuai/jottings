package com.ryan.leetcode;

import java.util.ArrayList;
import java.util.List;

public class ReverseInteger {
    public static int reverse(int x) {
        if (x == Integer.MIN_VALUE) {
            return 0;
        }
        int neg = x < 0 ? -1 : 1;
        x *= neg;
        int ret = 0;
        while (x > 0) {
            int n = ret;
            n *= 10;
            n += x % 10;
            x /= 10;
            if (n / 10 != ret) {
                return 0;
            }
            ret = n;
        }
        return ret * neg;
    }

    public static void main(String[] args) {


        System.out.println(inc(100));
    }

    public static Integer inc(Integer n) {
        if (n > 1) {
            return n + inc(n - 1);
        } else {
            return n;
        }
    }

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < (1 << nums.length); ++i) {
            List<Integer> subset = new ArrayList<>();
            for (int j = 0; j < nums.length; j++) {
                if ((i & (1 << j)) != 0)
                    subset.add(nums[j]);
            }
            ans.add(subset);
        }
        return ans;
    }

}
