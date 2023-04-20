package com.atball.der.member.controller;

import cn.hutool.core.bean.BeanUtil;
import com.atball.der.common.constant.RedisKeyConstant;
import com.atball.der.common.pojo.Restaurant;
import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
@Api(tags = "TestController", description = "TestController")
@RequestMapping("/sso")
@Slf4j
public class TestController {


    @Resource
    private RedisTemplate redisTemplate;

//    @Resource
//    private RestaurantMapper restaurantMapper;

    // 逐行插入
    public void testSyncForHash() {
//            List<Restaurant> restaurants = restaurantMapper.findAll();
        List<Restaurant> restaurants = Collections.emptyList();
        long start = System.currentTimeMillis();
        restaurants.forEach(restaurant -> {
            Map<String, Object> restaurantMap = BeanUtil.beanToMap(restaurant);
            String key = RedisKeyConstant.restaurants.getKey() + restaurant.getId();
            redisTemplate.opsForHash().putAll(key, restaurantMap);
        });
        long end = System.currentTimeMillis();
        log.info("执行时间：{}", end - start); // 执行时间：118957
    }

    // Pipeline 管道插入
    public void testSyncForHashPipeline() {
//            List<Restaurant> restaurants = restaurantMapper.findAll();
        List<Restaurant> restaurants = null;
        long start = System.currentTimeMillis();
        List<Long> list = redisTemplate.executePipelined((RedisCallback<Long>) connection -> {
            for (Restaurant restaurant : restaurants) {
                try {
                    String key = RedisKeyConstant.restaurants.getKey() + restaurant.getId();
                    Map<String, Object> restaurantMap = BeanUtil.beanToMap(restaurant);
                    StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
                    Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
                    Map<byte[], byte[]> restaurantStringMap = Maps.newHashMap();
                    restaurantMap.forEach((k, v) -> {
                        restaurantStringMap.put(stringRedisSerializer.serialize(k), jackson2JsonRedisSerializer.serialize(v));
                    });
                    connection.hMSet(stringRedisSerializer.serialize(key), restaurantStringMap);
                } catch (Exception e) {
                    log.info(restaurant.toString());
                    continue;
                }
            }
            return null;
        });
        long end = System.currentTimeMillis();
        log.info("执行时间：{}", end - start); // 执行时间：35606
    }

}
