package com.atball.der.member.controller.arrays;

import com.atball.der.member.controller.binarySearch.BinarySearch;
import org.junit.jupiter.api.Test;


import java.util.Arrays;

/**
 *  二维数组的查找
 */
public class SearchMatrix {




    private int[] searchMatrix(int[][] matrix, int key) {



        // 二位数组的行数、列数
        int col = matrix[0].length;
        int row = matrix.length;

        // 二维数组转换成一维数组
        int[] nums = new int[row * col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                nums[i * col + j] = matrix[i][j];
            }
        }



        // 二分法从一维数组中找数
        int index = BinarySearch.binarySearch(nums, key);

        if (index == -1) return new int[]{-1, -1};
        else return new int[]{index / col, index % col};

    }

    @Test
    public void test() {
        int[][] matrix = new int[][]{
                {1, 2, 3},
                {4, 5, 6}
        };

        int[] result = searchMatrix(matrix, 3);
        System.out.println(Arrays.toString(result));
    }

}



