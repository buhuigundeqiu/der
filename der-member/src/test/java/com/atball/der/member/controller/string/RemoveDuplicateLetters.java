package com.atball.der.member.controller.string;

import com.atball.der.member.controller.DerMemberApplicationTests;
import org.junit.jupiter.api.Test;


import java.util.HashSet;
import java.util.Set;

/**
 *  给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
 *  LeetCode 316
 *
 * 示例 1：
 *      输入：s = "bcabc"
 *      输出："abc"
 *
 * 示例 2：
 *      输入：s = "cbacdcbc"
 *      输出："acdb"
 */
public class RemoveDuplicateLetters extends DerMemberApplicationTests {

    private String removeDuplicateLetters(String s) {


        if (s.length() == 0) return "";

        int left = 0;

        StringBuffer result = new StringBuffer();

        while (left < s.length()) {

            StringBuffer minResult = new StringBuffer();
            Set<Character> set = new HashSet<>();
            int findLen = 0;

            for (int i = left; i < s.length(); i++) {
                char c = s.charAt(i);
                if (!set.contains(c)) {
                    set.add(c);
                    minResult.append(c);
                    findLen++;

                }
            }
            left ++;
            if (findLen > result.length() || (findLen == result.length() && minResult.toString().compareTo(result.toString()) < 0))
                result = minResult;

        }

        return result.toString();

    }

    /**
     *
     * 数组计数
     *
     */
    private String removeDuplicateLetters() {
        return "";
    }


    @Test
    public void test() {
        System.out.println(removeDuplicateLetters("ccbcccacccccccb"));
    }
}
