package com.atball.der.member.controller.string;

import com.atball.der.member.controller.DerMemberApplicationTests;
import org.junit.jupiter.api.Test;


/**
 * 两大数相加
 */
public class AddBigNumber extends DerMemberApplicationTests {


    private String addStrings(String num1, String num2) {

        StringBuffer result = new StringBuffer();


        // 定义遍历连个字符串的初始位置
        int len1 = num1.length() - 1;
        int len2 = num2.length() - 1;

        int carry = 0; // 用一个变量保存当前进位

        //
        while (len1 >= 0 || len2 >= 0 || carry != 0) {

            int a = len1 >= 0 ? num1.charAt(len1) - '0' : 0;
            int b = len2 >= 0 ? num2.charAt(len2) - '0' : 0;

            int sum = a + b + carry;

            result.append(sum % 10);

            carry = sum / 10;

            len1--;
            len2--;

        }

        return result.reverse().toString();

    }

    @Test
    public void test() {
        System.out.println(addStrings("123","5000"));
    }
}
