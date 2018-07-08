package com.predicate.quartz.controller;

import com.predicate.quartz.mapper.QuartzMapper;
import net.sf.json.JSONObject;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author:付风松
 * @Description:
 * @Date:Created in  12:48 2018/5/1
 * @ModefiedBy:
 */
@Controller
@RequestMapping("/quartz")
public class QuartzController {


    @Autowired
    private QuartzMapper  quartzMapper;
    @RequestMapping("/backMess")
    public void backMess(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("   进入该方法    ");
        String record=quartzMapper.selectFirstRecord();
        JSONObject object=new JSONObject();
        object.accumulate("record",record);
        PrintWriter writer=response.getWriter();
        quartzMapper.deleteRecord(record);
        writer.write(object.toString());
        writer.flush();
        writer.close();
    }

    @RequestMapping("/playSound")
    public void playSound(HttpServletRequest request,HttpServletResponse response) throws IOException {
        System.out.println("  has enter method ");
        /** 得到文件保存目录的真实路径* */
        String logoRealPathDir = request.getSession().getServletContext()
                .getRealPath("/sound/4031.wav");
        try {
            FileInputStream fileau=new FileInputStream(logoRealPathDir);
            AudioStream as=new AudioStream(fileau);
            AudioPlayer.player.start(as);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
