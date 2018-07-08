package com.predicate.utils;

import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

/**
 * @Author:付风松
 * @Description:
 * @Date:Created in  19:55 2018/5/22
 * @ModefiedBy:
 */
public class ResponseUtil {

    public static void response2Client(HttpServletResponse response, Map<String, Object> map) {

        try {
            PrintWriter writer = response.getWriter();
            JSONObject object = JSONObject.fromObject(map);
            writer.write(object.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void response2ClientMap(HttpServletResponse response, List<Map<String, Object>> map) {

        try {
            PrintWriter writer = response.getWriter();
            JSONObject object = JSONObject.fromObject(map);
            writer.write(object.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
