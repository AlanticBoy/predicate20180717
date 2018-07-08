<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/4/23
  Time: 18:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>一款基于JQuery做的动画背景后台管理登录模板 - </title>

    <link href="../static_login/css/main.css" rel="stylesheet" type="text/css"/>

</head>
<body>

<div class="login">
    <div class="box png">
        <div class="logo png"></div>
        <div class="input">
            <div class="log">
                <div class="name">
                    <label>用户名</label><input type="text" class="text" id="userName" placeholder="用户名" name="value_1"
                                             tabindex="1">
                </div>
                <div class="pwd">
                    <label>密 码</label><input type="password" class="text" id="pwd" placeholder="密码" name="value_2"
                                             tabindex="2">
                </div>
                <div class="pwd">
                    <c:if test="${sessionScope.loginInfo!=null}">
                        ${sessionScope.loginInfo}
                    </c:if>
                </div>
                <div class="pwd">
                    <input type="checkbox"><label>记住密码</label>
                    <input type="button" class="submit" tabindex="3" value="登录" onclick="userlogin()">
                    <div class="check"></div>
                </div>
                <div class="tip"></div>
            </div>
        </div>
    </div>
    <div class="air-balloon ab-1 png"></div>
    <div class="air-balloon ab-2 png"></div>
    <div class="footer"></div>
</div>

<script type="text/javascript" src="../static_login/js/jQuery.js"></script>
<script type="text/javascript" src="../static_login/js/fun.base.js"></script>
<script type="text/javascript" src="../static_login/js/script.js"></script>


<!--[if IE 6]>
<script src="../static_login/js/DD_belatedPNG.js" type="text/javascript"></script>
<script>DD_belatedPNG.fix('.png')</script>
<![endif]-->
<script>
    /*一加载页面就执行这个方法*/
    $(document).ready(function () {
        //去用户名和密码
        var str = getCookie("loginInfo");
        str = str.substring(1, str.length - 1);
        var username = str.split(",")[0];
        var password = str.split(",")[1];
        //自动填充用户名和密码
        $("#userName").val(username);
        $("#pwd").val(password);
    })
    //获取cookie
    function getCookie(cname) {
        var name = cname + "=";
        var ca = document.cookie.split(';');
        for (var i = 0; i < ca.length; i++) {
            var c = ca[i];
            while (c.charAt(0) == ' ') c = c.substring(1);
            if (c.indexOf(name) != -1) return c.substring(name.length, c.length);
        }
        return "";
    }
    //记住密码功能
    function remember() {
        var remFlag = $("input[type='checkbox']").is(':checked');
        if (remFlag === true) { //如果选中设置remFlag为1
            //cookie存用户名和密码,回显的是真实的用户名和密码,存在安全问题.
            var conFlag = confirm("记录密码功能不宜在公共场所(如网吧等)使用,以防密码泄露.您确定要使用此功能吗?");
            if (conFlag) { //确认标志
                return "selected";
            } else {
                $("input[type='checkbox']").removeAttr('checked');
                return "unselected";
            }
        } else { //如果没选中设置remFlag为""
            return "unselected";
        }
    }

    function userlogin() {
        var userName = $("#userName").val();
        var pwd = $("#pwd").val();
        var rememberPass = remember();
        $.ajax({
            url: "/user/login.action",
            // 请求方式
            type: "post",
            // 服务器响应的数据类型
            dataType: "json",
            data: {
                userName: userName,
                pwd: pwd,
                rememberPass: rememberPass
            },
            // 请求成功时执行的回调函数
            success: function (result) {
                if (result.mesg == "success") {
                    window.location.href = "imageload.jsp";
                } else {
                    alert("用户名或密码错误！");
                    /*这时把用户名和密码还有复选框置为空*/
                    $("#userName").val("");
                    $("#pwd").val("");
                    $("input[type='checkbox']").removeAttr('checked');
                }
            }
        })
    }
</script>
<div style="text-align:center;margin:50px 0; font:normal 14px/24px 'MicroSoft YaHei';">
    <p>适用浏览器：360、FireFox、Chrome、Safari、Opera、傲游、搜狗、世界之窗. 不支持IE8及以下浏览器。</p>
    <p>More Templates <a href="http://www.cssmoban.com/" target="_blank" title="模板之家">模板之家</a> - Collect from <a
            href="http://www.cssmoban.com/" title="网页模板" target="_blank">网页模板</a></p>
</div>

</body>
</html>
