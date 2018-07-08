package com.predicate.shiro.factoryMap;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author:付风松
 * @Description:
 * @Date:Created in  16:56 2018/5/17
 * @ModefiedBy:
 */

public class FailterChainDefinitionMapBuilder {
    public LinkedHashMap<String, String> buildFailterChainDefinitionMap() {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("/jsps/login.jsp", "anon");
        map.put("/user/login.action", "anon");
        map.put("/countdown/**", "anon");
        map.put("/export/**", "anon");
        map.put("/js/**", "anon");
        map.put("/user/kickout.action", "anon");
        map.put("/pagehelper/**", "anon");
        map.put("/sound/**", "anon");
        map.put("/static_login/**", "anon");
        map.put("/temp/**", "anon");
        map.put("/upload/**", "anon");
        map.put("/cloud/**", "anon");
       /* map.put("/user/roleDelete.action","authfilter[\"user:delete\",\"user:add\"]");*/
        /*这样表示拦截所有请求，但是不能实现记住我功能*/
        map.put("/**", "kickout,authc");
      /*  map.put("*//**", "authc");*/
        return map;
    }
}
