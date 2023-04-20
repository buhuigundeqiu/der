package com.atball.der.member.controller.binarySearch;

import com.atball.der.member.controller.DerMemberApplicationTests;
import org.junit.jupiter.api.Test;

/**
 * 二分查找（有序的数组）
 */
public class BinarySearch extends DerMemberApplicationTests {


    public static int binarySearch(int[] nums,int key) {

        // 定义初始查询范围
        int low = 0;
        int high = nums.length - 1;

        // 排除特殊情况
        if(key < nums[0] || key > nums[high]) return -1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (nums[mid] == key) return mid;

            if (nums[mid] > key) high = mid - 1;

            if (nums[mid] < key) low = mid + 1;
        }

        return -1;

    }


    // 递归实现二分查找
    private int binarySearch(int[] nums, int key, int from, int to) {

        if (nums[from] > key || nums[to] < key || from > to) return  -1;

        int mid = (from + to) / 2;

        if (nums[mid] == key) return mid;
        if (nums[mid] < key) return binarySearch(nums, key, mid + 1, to);
        if (nums[mid] > key) return binarySearch(nums, key, from, mid - 1);
        return  -1;

    }




    @Test
    public void test() {
            int[] ints = new int[]{1, 2, 3, 8, 9, 58};
            System.out.println(binarySearch(ints,9,0,5));
    }
}
