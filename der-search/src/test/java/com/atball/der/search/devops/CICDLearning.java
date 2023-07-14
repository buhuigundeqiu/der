package com.atball.der.search.devops;

public class CICDLearning {


    // CI/CD 环境搭建与调优
    /**
     *  CI 持续集成,开发人员Dev分支开发 bug修复 提交代码Dev分支，合并分支Master主分支
     *  CD 持续部署 Jenkins/tecmCity
     *  CM 持续监控Zabbix/等
     *
     *  GitLab开源的管理软件 （注意 默认的redis密码问题 容易被攻击）
     *   禁用自带的Redis和Nginx
     */

    // GitLab安装
    /**
     * 1)、在线安装的的方式
     *      主机规划
     *          主机名称hostname  内网IP    外网IP
     *
     *      禁用自带的Redis和Nginx
     *
     * N）、安装完成后
     *      创建组
     *      new project
     *      根据用户的角色分配不同的权限
     *      Dev开发工程师/初级 中级 高级
     *      初级我们通过gitlab 控制没有合并主分支的权限等
     *
     *      运维
     *      测试
     *      产品
     *      项目经理
     *
     *      多个部门协同开发时 创建多个用户
     */
    // Jenkins 安装
    /**
     *
     */

    // Jenkins 自动化构建
    /**
     * Jenkins 插件安装
     *      Maven Integration
     *      maven 推荐版本（3.6.3 稳定)
     *      单独配置 Maven 环境变量
     *
     * Jdk没安装也要安装
     *
     * 全局工具配置
     *      配置 git maven jdk
     *      whereis git
     * Gitlab Hook 插件 （效果：push代码的时候构建发布）
     *
     * 准备工作完成
     *      创建一个Maven的项目 进行自动构建、发布
     *      General （）
     *      源码管理（项目地址 方式一：ssh 开放22端口 方式二 http ）
     *      build
     *          Root POM (pom.xml)
     *          Goals and options (clean install -Dmaven.test.skip=true)
     *      构建后操作
     */

//    jenkins pipeline harbor docker ansible-playbook部署maven项目实战
//    https://blog.csdn.net/lsnow8624/article/details/114983404
}
