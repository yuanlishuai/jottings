package com.ryan.leetcode;

import java.util.Arrays;

/**
 * 217. 存在重复元素
 *
 * https://leetcode-cn.com/problems/contains-duplicate/
 * @author yuanls
 * @createdate 2020/4/26 16:01
 * @vesion 1.0
 */

public class ContainsDuplicate {

    /**
     * 先排序 ，然后判断两个相邻元素是否相等
     *
     * 时间 复杂度 O(n log n)  空间复杂度 O(1)
     */
    public static boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
        for(int i = 0; i< nums.length -1; i++) {
            if(nums[i] == nums[i + 1]) {
                return true;
            }
        }
        return false;
    }

}
