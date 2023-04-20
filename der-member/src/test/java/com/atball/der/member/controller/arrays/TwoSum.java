package com.atball.der.member.controller.arrays;


import com.atball.der.member.controller.DerMemberApplicationTests;
import org.junit.jupiter.api.Test;


import java.util.HashMap;
import java.util.Map;

/**
 * 两数之和
 *
 */
public class TwoSum extends DerMemberApplicationTests {


    // 方法一: 暴力法，穷举所有两数组合
    // Arrays.sort(); 快排序时间复杂度O(lgn)
    @Test
    public int[] twoSum(int[] nums, int targart) throws IllegalAccessException {

        int length = nums.length;
        // 双重for循环
        for (int i = 0; i < length - 1; i++) {
            for (int j = i + 1; j < length; j++) {
                if (nums[i] + nums[j] == targart) {
                    return new int[]{i, j};
                }
            }
        }


        // 如果没有找到，抛出异常
        throw new IllegalAccessException("no solution");

    }

    // 方法二: 哈希表保存所有信息
    @Test
    public int[] twoSumByHashMap(int[] nums, int targart) throws IllegalAccessException {

        // 定义一个哈希表
        Map<Integer, Integer> map = new HashMap<>();
        // 1.遍历nums，将数据全部保存在hash表中
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        // 2. 再次遍历数组，寻找 targart - nums[i] 在哈希表中是否存在
        for (int i = 0; i < nums.length; i++) {
            // 如果哈希表中存在 targart - nums[i] ，并且不是当前数本身，返回结果
            if (map.containsKey(targart - nums[i]) && map.get(targart - nums[i]) != i) {
                return new int[]{i, map.get(targart - nums[i])};
            }
        }

        // 如果没有找到，抛出异常
        throw new IllegalAccessException("no solution");
    }

    // 方法三: 优化仅仅遍历一次hashmap
    @Test
    public int[] twoSumByTraverseOneHashMap(int[] nums, int targart) throws IllegalAccessException {

        // 定义一个哈希表
        Map<Integer, Integer> map = new HashMap<>();
        // 1.遍历nums，将数据全部保存在hash表中
//        for (int i = 0; i < nums.length; i++) {
//            map.put(nums[i], i);
//        }

        // 2. 再次遍历数组，寻找 targart - nums[i] 在哈希表中是否存在
        for (int i = 0; i < nums.length; i++) {
            // 如果哈希表中存在 targart - nums[i] ，并且不是当前数本身，返回结果
            if (map.containsKey(targart - nums[i]) && map.get(targart - nums[i]) != i) {
                return new int[]{i, map.get(targart - nums[i])};
            }
        }

        // 如果没有找到，抛出异常
        throw new IllegalAccessException("no solution");
    }

}
