package com.atball.der.member.controller.window;

import com.atball.der.member.controller.DerMemberApplicationTests;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * 给你一个整数数组 nums，有一个大小为 k的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 *
 * 返回 滑动窗口中的最大值 。
 *
 * 来源：力扣（LeetCode）239
 *
 * 示例1：
 *
 * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
 * 输出：[3,3,5,5,6,7]
 * 解释：
 * 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 * 示例 2：
 *
 *  输入：nums = [1], k = 1
 *  输出：[1]
 *
 */
public class SlidingWindowMaxinum extends DerMemberApplicationTests {


    // 暴力法  遍历每一个窗口，对每个窗口遍历每个元素求最大值
    private int[] violentSlidingWindowMaxinum(int[] nums, int k) {

        // 定义结果数组，总大小为 nums.length - k + 1
        int[] result = new int[nums.length - k + 1];

        for (int i = 0; i < nums.length - k + 1; i++) {
            int number = 0;
            for (int j = i; j < i + k; j++) {
                number = Math.max(number, nums[j]);
            }
            result[i] = number;
        }
        return result;
    }

    // 左右扫描法
    private int[] leftRightSlidingWindowMaxinum(int[] nums, int k) {
        // 定义结果数组，总大小为 nums.length - k + 1
        int[] result = new int[nums.length - k + 1];

        // 定义存放块内最大值的left和right数组
        int[] left = new int[nums.length];
        int[] right = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            // 从左到右
            if (i % k == 0) {
                // 如果能整除k 就是块的起始位置
                left[i] = nums[i];

            } else {
                // 如果不是起始位置，就直接跟left[i - 1]比较，取最大的
                left[i] = Math.max(left[i - 1], nums[i]);

            }

            // 从右到左
            int j = nums.length - i - 1;
            if (j % k == k - 1 || i == 0) {
                right[j] = nums[j];
            } else {
                right[nums.length - i - 1] = Math.max(nums[nums.length - i - 1], nums[nums.length - i]);
            }
        }

        // 对每个窗口计算最大值
        for (int i = 0; i < nums.length - k + 1; i++) {
            result[i] = Math.max(left[i + k - 1], right[i]);
        }
        return result;
    }



    @Test
    public void test() {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int[] result = leftRightSlidingWindowMaxinum(nums, 3);
        System.out.println(Arrays.toString(result));
    }

}
