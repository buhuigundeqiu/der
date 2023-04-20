package com.atball.der.search.sentinel;

/**
 * 熔断降级处理
 */
public class SentinelLearning {

    // 什么是熔断

    /**
     *  A服务调用 B服务 的某个功能，由于网络不稳定问题，或者B服务卡机，导致功能时间超长。如果这样子的次数过多。
     *  我们就可以直接将B断路（A不再请求B接口），凡是调用B的直接返回降级数据，不必等待B的超长执行。这样B的故障问题，
     *  就不会级联影响到A。
     */

    // 什么是降级

    /**
     *  整个网站处于流量高峰期，服务器压力剧增，根据当前业务情况及流量，对一些服务和页面进行有策略的降级和停止服务，所有
     *  的调用直接返回降级数据。以此缓解服务器资源的压力，以保证核心业务正常运行，同时也保持了客户和大部分客户的得到正常的e响应。
     */

    // 异同：
        // 相同点：
                // 1、为了保护集群大部分服务的可用性和可靠性 防止崩溃，牺牲小我
                // 2、用户最终都是体验到某个功能不可用
        // 不同点：
                // 1、熔断是被调用方故障，触发的系统主动规则
                // 2、降级是基于全局考虑，停止一切正常服务，释放资源



    // 什么是限流

    /**
     * 对打入服务的请求流量进行控制，使服务能够承担不超过自己能力的流量压力
     */


    // 整合sentinel

    /**1、
     *    1）、导入依赖 spring-cloud-starter-alibaba-sentinel
     *    2）、下载 sentinel 控制台
     *    3）、配置 sentinel 控制台地址信息
     *          application.yml
     *              spring:
     *                cloud:
     *                  sentinel:
     *                    transport:
     *                      port: 8719
     *                      dashboard: localhost:8080
     *    4）、在控制台调整参数。【默认所有的流控设置保存在内存中，重启失效】
     * 2、每个微服务都导入 actuator；并配合 management.endpoints.web.exposure.include=*
     * 3、自定义 sentinel 流控返回数据
     *      2.2.0之前的版本基于sentinel-web-servlet原生servlet能力实现
     *      2.2.0及之后版本是依赖 sentinel-spring-webmvc-dapter，基于 Spring 的 Interceptor 拦截资源，不再是 CommonFilter。
     *
     *      // 之前版本
     *      @Configuration
     *      public class SentinelConfig {
     *
     *          public SentinelConfig() {
     *              WebCallbackManager.setUrlBlockHandler(new UrlBlockHandler() {
     *                  @Override
     *                  public void blocked(HttpServletRequest request, HttpServletResponse response, BlockException ex) throws IOException {
     *                      // 降级业务处理
     *                      CommonResult<Object> failed = CommonResult.failed();
     *                      response.getWriter().write(JSON.toJSONString(failed));
     *                  }
     *              });
     *          }
     *      }
     *
     *      // 2.2.0及之后版本
     *      @Component
     *      public class UrlBlockHandler implements BlockExceptionHandler {
     *
     *         @Override
     *         public void handle(HttpServletRequest request, HttpServletResponse response, BlockException e) throws Exception {
     *          // 降级业务处理
     *         }
     *      }
     *
     *
     * 4、使用 sentinel 来保护 feign 远程调用：熔断；
     *      1）、调用方的熔断保护：feign.sentinel.enabled = true
     *      2）、调用方手动指定远程服务的降级策略。远程服务被降级处理。触发我们的熔断回调方法
     *      3）、超大浏览的时候，必须牺牲一些远程服务。在服务的提供方（远程服务）指定降级策略；
     *          提供方是在运行。但是不运行自己的业务逻辑，返回的是默认的熔断数据（限流的数据）
     *
     * 5、自定义受保护资源
     *      1）、代码
     *      try(Entry entry = SphU.entry("自定义资源名")){
     *          // 业务逻辑
     *      }catch(catch){
     *
     *      }
     *
     *      2）、基于注解
     *
     *
     *
     */



}
