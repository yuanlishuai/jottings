package com.ryan.leetcode;

/**
 *
 * 7. 整数反转
 *
 * https://leetcode-cn.com/problems/reverse-integer/
 *
 * @author yuanls
 * @createdate 2020/4/26 14:40
 * @modifier yuanls
 * @updatedate 2020/4/26 14:40
 * @vesion 1.0
 */

public class ReverseNum {

    /**
     * 复杂度分析
     * <p>
     * 时间复杂度：O(log(x))，xx 中大约有 log10(x)位数字。
     * 空间复杂度：O(1)。
     */
    public static int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x = x / 10;
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > Integer.MAX_VALUE % 10)) {
                rev = 0;
                break;
            } else if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && x < Integer.MIN_VALUE % 10)) {
                rev = 0;
                break;
            }
            rev = rev * 10 + pop;
        }
        return rev;
    }

    public static void main(String[] args) {
        System.out.println(reverse(123));
    }
}
