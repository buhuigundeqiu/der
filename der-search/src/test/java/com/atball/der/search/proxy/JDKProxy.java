package com.atball.der.search.proxy;

import com.atball.der.search.DerSearchApplicationTests;
import com.atball.der.search.dao.Dao;
import org.junit.Test;
import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class JDKProxy extends DerSearchApplicationTests {

    // JDK动态代理为什么要基于接口
    /**
     *  代理模式： 1、继承 2、聚合（接口） 3、动态代理
     *
     *  Java中的代理类已经继承了Proxy类，不能再继承目标对象的类，否则就违背了Java的单继承的语法规则，所以只能基于接口
     */

    @Test
    public void test() {
        Class<?>[] interfaces = {Dao.class};
        byte[] bytes = ProxyGenerator.generateProxyClass("DerDao", interfaces);
        File file = new File(System.getProperty("user.dir") + "\\Test.class");
        try {
            FileOutputStream os = new FileOutputStream(file);
            os.write(bytes);
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test1() {
        String property = System.getProperty("user.dir");
    }
}
