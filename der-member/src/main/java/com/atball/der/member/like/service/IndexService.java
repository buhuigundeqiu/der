package com.atball.der.member.like.service;
/*
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

import java.util.List;
import java.util.Set;

public interface IndexService {
    /***
     * 根据关键字查询结果
     * @param word
     * @return
     */
    public List<TlIndex> search(String word);

    /***
     * jia'zhai加载数据
     * @return
     */
    public List<TlIndex> all();

    public Set<TlIndex> search2(String word);
}
