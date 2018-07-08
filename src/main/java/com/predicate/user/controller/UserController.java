package com.predicate.user.controller;

import com.fusong.utils.ExcelUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.predicate.user.POJO.InputObject;
import com.predicate.user.model.PageUnit;
import com.predicate.user.model.User;
import com.predicate.user.service.UserService;


import com.predicate.utils.ResponseUtil;
import net.sf.json.JSONObject;
import org.apache.commons.io.FilenameUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author:付风松
 * @Description:
 * @Date:Created in  15:08 2018/4/21
 * @ModefiedBy:
 */
@Controller
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

    private Integer counts = 0;
    /*这里证明了服务器启动后，无论是本机客户端还是其他客户端访问某个路径，count是持续曾的，而不是从0开始.
    我猜或许是这样，UserController被加载后。变量存储到了非静态域里。然后因为这个类在服务器启动时被加载过了，所以
    我们在访问这个类下的路径。不会在加载。
    */
    private Integer count2 = 0;

    @RequestMapping("/view")
    public void view(HttpServletResponse response, HttpServletRequest request, InputObject inputObject) throws IOException {
        if (request.getAttribute("counts") == null) {
            counts++;
        }
        request.setAttribute("counts", counts);
        System.out.println(" count  " + counts);
        response.getWriter().write("ok，lets go, and code " + request.getParameter("code"));
    }

    @RequestMapping("/view2")
    public void view2(HttpServletResponse response, HttpServletRequest request, InputObject inputObject) throws IOException {
        count2++;
        System.out.println(" count2  " + count2);
        response.getWriter().write("ok，lets go, and code " + request.getParameter("code"));
    }


    @RequestMapping(value = "/getData", method = {RequestMethod.POST, RequestMethod.GET})
    public String getData(HttpServletRequest request, HttpServletResponse response, int num) {
        Map<String, Object> map = new HashMap<>();
        String name = request.getParameter("name");
        String number = request.getParameter("num");
        String age = request.getParameter("age");

        if (num == 1) {
            map.put("num", "You are good");
        } else {
            map.put("num", "you are not good");
        }
        map.put("name", name);
        map.put("age", age);
        JSONObject object=JSONObject.fromObject(map);
        return object.toString();
    }

    /*#################################测试根据key进行缓存和缓存清空#################################*/
    @RequestMapping("/showAllUser")
    public void showAllUser(HttpServletResponse response) throws Exception {
        System.out.println(" 进入该方法 ");

        PrintWriter writer = response.getWriter();
        List<User> users = userService.selectAllUser();
        JSONObject object = new JSONObject();
        object.accumulate("user", users);
        writer.write(object.toString());
    }


    @RequestMapping("/selectAUser")
    public void selectAUser(HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();
        int id = 13;
        Map<String, Object> map = userService.selectUserById(id);
        JSONObject object = JSONObject.fromObject(map);
        writer.write(object.toString());
        writer.close();
    }

    @RequestMapping("/updateAUser")
    public void updateAUser() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", 13);
        int id = 13;
        map.put("email", "1725387373@qq.com");
        userService.updateAUserById(13, map);
    }
/*#################################测试根据key进行缓存和缓存清空完毕#################################*/

    @RequestMapping("/getAndGive")
    public void getAndGive(HttpServletRequest request, HttpServletResponse response) {
        System.out.println(" has enter way ");
        userService.insertAUser(request, response);
    }

    @RequestMapping("/showAllUnit")
    public ModelAndView showAllUnit(HttpServletRequest request, int pageNum) throws Exception {
        System.out.println(" entere way ");
        PageHelper.startPage(pageNum, 30);
        List<PageUnit> pageUnits = userService.selectAllUnit("hahaha");
        PageInfo<PageUnit> pageInfo = new PageInfo<PageUnit>(pageUnits);
        request.setAttribute("page", pageInfo);
        request.setAttribute("list", pageUnits);

        ModelAndView view = new ModelAndView();
        view.addObject("page", pageInfo);
        view.addObject("list", pageUnits);
        view.setViewName("pagelist");
        return view;
    }


    @RequestMapping("deleteAllUnits")
    public void deleteAllUnits() {
        userService.deleteAllUnits("hahaha");
    }

    @RequestMapping("/exportUser")
    public void exportUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ExcelUtils excelUtils = new ExcelUtils();
        excelUtils.exportUserInfo(request, response, userService);
    }

    @RequestMapping("/downloadTemplate")
    public void downloadTemplate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ExcelUtils excelUtils = new ExcelUtils();
        excelUtils.downloadTemplate(request, response);
    }

    /*封装成实体类导入*/
    @RequestMapping("/importUserInfo")
    public void importUserInfo(HttpServletRequest request, HttpServletResponse response, MultipartFile file) throws Exception {
        ExcelUtils excelUtils = new ExcelUtils();
        excelUtils.importUserInfo(file, response, userService);
    }

    /*封装成Map导入*/
    @RequestMapping("/importUserInfoByMap")
    public void importUserInfoByMap(HttpServletRequest request, HttpServletResponse response, MultipartFile file) throws Exception {
        ExcelUtils excelUtils = new ExcelUtils();
        excelUtils.importUserInfoByMap(file, response, userService);
    }

    @RequestMapping("importMedicalInfo")
    public void importMedicalInfo(HttpServletResponse response, MultipartFile file) throws Exception {
        ExcelUtils excelUtils = new ExcelUtils();
        excelUtils.importMedicalInfoByMap(file, userService);
    }

    /*上传图片*/
    @RequestMapping("/uploadPic")
    public void uploadPic(MultipartFile pic, HttpServletRequest request, HttpServletResponse response) throws IllegalStateException, IOException {
        try {
            // 获取图片原始文件名
            String originalFilename = pic.getOriginalFilename();
            System.out.println(originalFilename);
            // 文件名使用当前时间
            String name = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
            // 获取上传图片的扩展名(jpg/png/...)
            String extension = FilenameUtils.getExtension(originalFilename);
            // 图片上传的相对路径（因为相对路径放到页面上就可以显示图片）
            String path = "/upload/" + name + "." + extension;
            // 图片上传的绝对路径
            String url = request.getSession().getServletContext().getRealPath("") + path;
            File dir = new File(url);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            // 上传图片
            pic.transferTo(new File(url));
            // 将相对路径写回（json格式）
            JSONObject jsonObject = new JSONObject();
            // 将图片上传到本地
            jsonObject.put("path", path);
            // 设置响应数据的类型json
            response.setContentType("application/json; charset=utf-8");
            // 写回
            response.getWriter().write(jsonObject.toString());

        } catch (Exception e) {
            throw new RuntimeException("服务器繁忙，上传图片失败");
        }
    }
    /*不用shiro实现的用户登录*/
   /*

   @RequestMapping("/login")
    public void userlogin(HttpServletRequest request, HttpServletResponse response, String userName, String pwd, String rememberPass) throws IOException {
        JSONObject object = new JSONObject();
        System.out.println(" 收到用户名密码  "+userName+"  密码  "+pwd);
        PrintWriter writer = response.getWriter();
        Map<String, String> map = new HashMap<>();
        map.put("phone", userName);
        *//*DigestUtils.md5DigestAsHex(pwd.getBytes()),用于密码加密*//*
        map.put("password", DigestUtils.md5DigestAsHex(pwd.getBytes()));
        User user = null;
        try {
            user = userService.selectUserByIdAndPass(map);
            *//*如果用户存在，判断是否选中记住密码，这是message等于success，回写给ajax*//*
            if (user != null) {
                *//*如果选中的话存放到cookie里*//*
                if (rememberPass.equals("selected")) {
                    String loginInfo = userName + "," + pwd;
                    Cookie userCookie = new Cookie("loginInfo", loginInfo);
                    userCookie.setMaxAge(30 * 24 * 60 * 60);   //存活期为一个月 30*24*60*60
                    userCookie.setPath("/");
                    response.addCookie(userCookie);
                }
                request.setAttribute("user", user);
                object.accumulate("messages", "success");
                writer.write(object.toString());
                writer.close();
            } else {
                *//*如果用户不存在直接结束方法*//*
                object.accumulate("messages", "failure");
                writer.write(object.toString());
                writer.close();
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }*/

    @RequestMapping("/kickout")
    public String kickout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("loginInfo", "你在别的地方登陆");
        System.out.println(" 进入到被踢出的方法。。 ");
        return "login";
    }

    @RequestMapping("/login")
    public void userLogin(HttpServletRequest request, HttpServletResponse response, String userName, String pwd) throws IOException {
        PrintWriter writer = response.getWriter();
        JSONObject object = new JSONObject();
        UsernamePasswordToken token = new UsernamePasswordToken(userName, pwd);
        Subject subject = SecurityUtils.getSubject();
        try {
            System.out.println(" 进入到  login 方法，下一步将执行subject.login(token)方法 ");
            subject.login(token);
            System.out.println(" 从 自定义realm方法里出来，下一步执行subject.isAuthenticated()方法   ");
            if (subject.isAuthenticated()) {
                //don something
                object.accumulate("mesg", "success");
            }
        } catch (AuthenticationException e) {
            object.accumulate("mesg", "error");
            System.out.println(" 进入到catch ");
            System.out.println(e.getMessage());
        } finally {

            writer.write(object.toString());
            writer.close();
        }

    }

    /*以下都是测试shiro的权限注解*/
    @RequiresPermissions(value = {"user:create", "user:delete", "user:expire"})
    @RequestMapping("/roleCreate")
    public void roleCreate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();
        System.out.println(" ........ ");
    }

    @RequiresPermissions(value = {"user:delete", "user:update"})
    @RequestMapping("/roleDelete")
    public void roleDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();
        System.out.println(" ........ ");
        JSONObject object = new JSONObject();
        object.accumulate("message", "可以通过权限认证");
        writer.write(object.toString());
        writer.close();
    }


}
