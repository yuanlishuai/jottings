package com.ryan.leetcode;

/**
 * 283. 移动零
 * 难度简单
 * https://leetcode-cn.com/problems/move-zeroes/
 * <p>
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * <p>
 * 示例:
 * <p>
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 说明:
 * <p>
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/move-zeroes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class MoveZeroes {


    //----------------------------

    //双指针  左右夹遍
    //时间  O(n)
    //空间  O(1）
    public static void moveZeroes(int[] nums) {
        //zero 记录下一个非零元素
        int zero = 0;
        for (int i = 0; i < nums.length; i++) {
            // 判断该索引上的元素非零
            if (nums[i] != 0) {
                //则将该元素放到 非零的索引zero 上
                nums[zero] = nums[i];

                // 上一步 已经将非零元素移走了，接下来将空出来的  设为0 =》 即 上边不空的元素占了空的元素的位置，后边要把移走后的位置补上
                if (zero != i) {
                    nums[i] = 0;
                }
                zero++;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {2, 4, 0, 8, 0, 1, 0, 5, 0, 0, 8};
        moveZeroes(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i]);
        }
    }
}
