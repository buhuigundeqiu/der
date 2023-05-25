package com.atball.der.member.like.init;/*
 * ━━━━━━如来保佑━━━━━━
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　┻　　　┃
 * 　　┗━┓　　　┏━┛
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┗┻┛　┗┻┛
 * ━━━━━━永无BUG━━━━━━
 * 图灵学院-悟空老师
 * www.jiagouedu.com
 * 悟空老师QQ：245553999
 */
import com.atball.der.member.like.pojo.TlIndex;
import com.atball.der.member.like.service.IndexService;
import com.atball.der.member.like.service.LikeSearch;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class CacheInit implements InitializingBean, ApplicationContextAware {

    @Autowired
    IndexService indexService;

    ApplicationContext applicationContext;

    public static LikeSearch<TlIndex> likeSearch = new LikeSearch<>();

    @Override
    public void afterPropertiesSet() throws Exception {
        List<TlIndex> all = indexService.all();
        for (TlIndex index : all) {
            likeSearch.put(index,index.getCompany_name());
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
