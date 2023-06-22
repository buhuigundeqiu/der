package com.atball.der.search.spring;

public class SpringUsing {

    /**
     * BeanFactory registerSingleton
     * 通过工厂后置处理器注册Bean
     */

//
//          先上代码，定义一个普通Bean。
//          @Data
//          public class User {
//               private Integer id;
//               private String name;
//               private String password;
//               private Integer age;
//          }
//
//          定义一个被spring可以扫描的类，这个类要实现
//          BeanFactoryPostProcessor。里面调用registerSingleton注册一个对象。
//
//          @Component
//          public class MyBeanFacoty implements BeanFactoryPostProcessor {
//               @Override
//               public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
//                   User user1 = new User();
//                   user1.setId(1);
//                   user1.setName("贾元春");
//                   user1.setAge(27);
//                   configurableListableBeanFactory.registerSingleton("user", user1);
//          }




}
