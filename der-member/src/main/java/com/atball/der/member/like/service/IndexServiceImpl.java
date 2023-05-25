package com.atball.der.member.like.service;/*
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

import com.atball.der.member.like.mapper.IndexMapper;
import com.atball.der.member.like.init.*;
import com.atball.der.member.like.pojo.TlIndex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    IndexMapper indexMapper;

    public List<TlIndex> search(String word) {
        // return indexMapper.search(word);
        return indexMapper.search(word);

    }


    public Set<TlIndex> search2(String word) {
        return (Set<TlIndex>) CacheInit.likeSearch.search(word, 20);

    }


    @Override
    public List<TlIndex> all() {
        TlIndex tlIndex1 = new TlIndex(443001l, "长沙市华悦汽车维修服务有限公司");
        TlIndex tlIndex2 = new TlIndex(452004l, "长沙市理信汽车服务有限公司");
        TlIndex tlIndex3 = new TlIndex(213007l, "长沙市际通汽车销售有限公司");
        TlIndex tlIndex4 = new TlIndex(441009l, "长沙市东驰汽车销售服务有限公司(安心工场)");
        TlIndex tlIndex5 = new TlIndex(234006l, "长沙市佰利汇春际新能源汽车销售有限公司");

        List<TlIndex> tlIndices = new ArrayList<>();
        tlIndices.add(tlIndex1);
        tlIndices.add(tlIndex2);
        tlIndices.add(tlIndex3);
        tlIndices.add(tlIndex4);
        tlIndices.add(tlIndex5);
        return tlIndices;
//        return indexMapper.listAll();

    }

}
