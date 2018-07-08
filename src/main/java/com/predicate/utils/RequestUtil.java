package com.predicate.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author:付风松
 * @Description:
 * @Date:Created in  19:45 2018/5/22
 * @ModefiedBy:
 */
public class RequestUtil {

    /**
    　　* @Description: RequestUtil把前台传来的参数封装到Map
    　　* @param ${tags}
    　　* @return ${return_type}
    　　* @throws
    　　* @author 付风松
    　　* @date 2018/5/22 19:48
    　　*/
    public static Map<String, Object> getParamMap(HttpServletRequest request) {

        Map<String, Object> map = new HashMap<>();
        Map<String, String[]> paramMap = request.getParameterMap();
        for (Map.Entry<String, String[]> entry : paramMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue()[0];
            map.put(key, value);
        }
        return map;
    }

}
