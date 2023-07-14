package com.atball.der.member.service.impl;

import com.atball.der.common.api.CommonResult;
import com.atball.der.common.api.TokenInfo;
import com.atball.der.member.model.UmsMember;
import com.atball.der.member.service.UmsMemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 会员管理Service实现类
 * Created by macro on 2018/8/3.
 */
@Service
public class UmsMemberServiceImpl implements UmsMemberService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UmsMemberServiceImpl.class);


    @Override
    public UmsMember getByUsername(String username) {
        return null;
    }

    @Override
    public UmsMember getById(Long id) {
        return null;
    }

    @Override
    public CommonResult register(String username, String password, String telephone, String authCode) {
        // 从redis中获取验证码与authCode比较
        // 数据库member表中插入一条记录

        return null;
    }

    @Override
    public CommonResult generateAuthCode(String telephone) {
        // 查询当前用户有没有注册
        // 校验60s内有没有发送过验证码
        // 生成随机校验码
        return null;
    }

    @Override
    public CommonResult updatePassword(String telephone, String password, String authCode) {
        return null;
    }

    @Override
    public UmsMember getCurrentMember() {
        return null;
    }

    @Override
    public void updateIntegration(Long id, Integer integration) {

    }

    @Override
    public TokenInfo login(String username, String password) {
        return null;
    }

    @Override
    public String refreshToken(String token) {
        return null;
    }

    @Override
    public int updateUmsMember(UmsMember umsMember) {
        return 0;
    }
}