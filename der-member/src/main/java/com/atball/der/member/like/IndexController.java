package com.atball.der.member.like;/*
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RequestMapping
@Controller
public class IndexController {

    @Autowired
    IndexService indexService;


    @RequestMapping("/data.json")
    @ResponseBody
    public Set<TlIndex> data(@RequestParam String word) {
        return indexService.search2(word);
    }

    @RequestMapping("/data2.json")
    @ResponseBody
    public void data2(@RequestParam String word) {
        List<TlIndex> search = indexService.search(word);
        List<String> all = new ArrayList<String>();
        if (null != all && !search.isEmpty()) {
            for (TlIndex index : search) {
                all.add(index.getCompany_name());
            }
        }
    }


}
