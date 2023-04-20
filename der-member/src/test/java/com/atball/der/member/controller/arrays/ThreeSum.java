package com.atball.der.member.controller.arrays;

import com.atball.der.member.controller.DerMemberApplicationTests;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

/**
 * 三数之和
 */
public class ThreeSum extends DerMemberApplicationTests {

    // 暴力法
    private List<List<Integer>> violent(int[] nums) {

        int length = nums.length;

        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                for (int m = j + 1; m < length; m++) {
                    if (nums[i] + nums[j] + nums[m] == 0) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[m]));
                    }
                }
            }
        }
        // nums 重复数据 所以
        // result 結果去重

        return result;
    }

    // 双指针法
    private List<List<Integer>> doublePoint(int[] nums) {

        int length = nums.length;
        // 排序
        Arrays.sort(nums);
//        [-4, -1, -1, 0, 1, 2]



        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < length - 2; i++) {

            if (nums[i] > 0) break;

            // 注意点
            if (i > 0 && nums[i] == nums[i - 1])  continue;


            // 定义双指针
                int leftPoint = i + 1;

            int rightPoint = length - 1;

            while (leftPoint < rightPoint) {
                // 等于0 找到一组解
                if (nums[i] + nums[leftPoint] + nums[rightPoint] == 0) {
                    result.add(Arrays.asList(nums[i], nums[leftPoint], nums[rightPoint]));
                    leftPoint++;
                    rightPoint--;

                    // 如果移动之后的元素相同，直接跳过
                    while (leftPoint < rightPoint && nums[leftPoint] == nums[leftPoint - 1]) leftPoint++;
                    while (leftPoint < rightPoint && nums[rightPoint] == nums[rightPoint + 1]) rightPoint--;


                }
                // 小于0 较小的数增大 ----》 leftPoint++;
                if (nums[i] + nums[leftPoint] + nums[rightPoint] < 0) {
                    leftPoint++;
                }
                // 大于0 较大的数减小 ----》 rightPoint --;

                if (nums[i] + nums[leftPoint] + nums[rightPoint] > 0) {
                    rightPoint--;
                }

                // 注意点 (省略与52行效果相同)
                // if (nums[i] == nums[i + 1])  i ++;
            }

        }
        return result;

    }


    @Test
    public void test() {
        int[] inPut = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> result = this.doublePoint(inPut);
        System.out.println(result);

    }
}