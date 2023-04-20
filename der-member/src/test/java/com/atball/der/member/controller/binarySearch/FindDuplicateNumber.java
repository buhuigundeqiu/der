package com.atball.der.member.controller.binarySearch;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 找出唯一重复数 LeetCode 287
 */
public class FindDuplicateNumber {

    // 方法一: 使用Hashmap保存每个数出现的次数
    private int findDuplicateNumber(int[] nums) {

        Map<Integer, Integer> countMap = new HashMap<>();

        // 遍历数组,统计数字出现次数
        for (int num : nums) {
            // 判断num是否在map出现
            if (countMap.containsKey(num)) {
                return num; // 如果出现，num就是重复数
            } else {
                countMap.put(num, 1);
            }
        }

        return -1;
    }

    // 方法二: 使用Hashset保存数据，找到重复数据
    // 类似HashMap


    // 方法三: 先排序，再遍历找重复数


    // 方法四: 巧妙解法
    // 1-N 自然序列 找目标target
    // 目标数 target
    private int findDuplicateNumberByMath(int[] nums) {

        int left = 1;
        int right = nums.length - 1;

        while (left < right) {

            // 计算中间值
            int mid = (left + right) / 2;

            // 对当前的mid计算count值
            int count = 0;
            for (int j = 0; j < nums.length; j++) {
                if (nums[j] <= mid) count++;
            }

            // 判断count和mid本身的大小关系
            if (count <= mid) {
                left = mid + 1;
            } else {
                right = mid;
            }

            if (left == right) return left;

        }

        return -1;


    }

    public int findDuplicateNumberByMath() {
        return -1;
    }




    @Test
    public void test() {
        int[] temp = {1, 2, 3, 4, 5, 5, 5, 7};
        int result = findDuplicateNumberByMath(temp);
        System.out.println(result);
    }



}