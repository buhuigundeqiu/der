package com.atball.der.member.controller.window;

import org.apache.tomcat.util.net.SSLUtilBase;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * leetcode: 76
 * 最小覆盖子串
 *
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 */
public class MinWindowSubString {

    // 方法一: 暴力法，枚举s中所有子串
    private String violentMinWindowsSubString(String s, String t) {

        // 定义最小子串 保存结果 初始为空字符串
        String minSubString = "";

        // 统计t中字符频次
        Map<Character, Integer> tCharFrequency = new HashMap<>();

        for (int index = 0; index < t.length(); index++) {
            char c = t.charAt(index);
            int count = tCharFrequency.getOrDefault(c, 0);
            tCharFrequency.put(c, count + 1);
        }

        // 接下来在s中搜索覆盖子串
        // 遍历所有字符，作为当前子串的起始位置
        for (int index = 0; index < s.length(); index++) {
            // 遍历i之后不小于t长度的位置，作为子串的结束位置

            for (int j = index + t.length(); j < s.length(); j++) {

                Map<Character, Integer> subStringCharFrequency = new HashMap<>();

                // 统计s子串中字符频次
                for (int k = index; k < j + 1; k++) {
                    char c = s.charAt(k);
                    int count = subStringCharFrequency.getOrDefault(c, 0);
                    subStringCharFrequency.put(c, count + 1);
                }

                boolean flag = true;
                for (Character character : tCharFrequency.keySet()) {
                    if (tCharFrequency.get(character) > subStringCharFrequency.getOrDefault(character,0)) {
                        flag = false;
                    }
                }
                if (flag && (minSubString.length() > j - index + 1 || minSubString.length() == 0)) minSubString = s.substring(index, j +1);

            }


        }
        return minSubString;
    }



    // 方法一: 左右指针法，枚举s中所有子串
    private String minWindowsSubString(String s, String t) {

        // 定义最小子串 保存结果 初始为空字符串
        String minSubString = "";

        // 统计t中字符频次
        Map<Character, Integer> tCharFrequency = new HashMap<>();

        for (int index = 0; index < t.length(); index++) {
            char c = t.charAt(index);
            int count = tCharFrequency.getOrDefault(c, 0);
            tCharFrequency.put(c, count + 1);
        }

        // 定义左右指针，指向滑动窗口的起始和结束位置
        int start = 0, end = t.length();


        return null;

    }


    private boolean check(Map<Character, Integer> source, Map<Character, Integer> target) {
        boolean flag = true;
        for (Character character : target.keySet()) {
            if (target.get(character) > source.getOrDefault(character,0)) {
                flag = false;
            }
        }
        return flag;
    }

    @Test
    public void test() {
        String result = violentMinWindowsSubString("ADOBECODEBANC", "ABC");
        System.out.println(result);

    }
}
