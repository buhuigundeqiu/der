package com.atball.der.search.utils;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import org.apache.commons.codec.digest.DigestUtils;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class SignUtil {
    public static void main(String[] args) {
        testOrgQuery();
    }



    public static void testOrgQuery() {
        Map paramMap = new HashMap();
        Map dataMap = new HashMap();
        dataMap.put("PHONE", "13958879139");
        paramMap.put("DATA", JSONUtil.toJsonStr(dataMap));
        paramMap.put("RESOURCES", "customer");
        paramMap.put("ACTION", "getvin");
//        paramMap.put("VIN","VIN-202203021442");
        // 新增token校验
        Long timeStamp = Calendar.getInstance().getTime().getTime();
//        timeStamp = 1577331489539L;
        paramMap.put("TIMESTAMP", timeStamp+"");
        StringBuilder sb = new StringBuilder();

//        sb.append(paramMap.toString()).append("&");
        sb.append("TIMESTAMP=").append(timeStamp).append("&");
        sb.append("TOKEN=").append("d4dd67b1-9072-4f13-b7fe-59ae95f25a27"); // token固定
        String checksign = DigestUtils.md5Hex(sb.toString().getBytes()).toUpperCase(); // 先MD5,再HEX
        paramMap.put("SIGN", checksign);
        System.out.println(checksign);
        System.out.println(timeStamp);
//        return JettyHttpsClients.doPost(url, paramMap);
    }
}
