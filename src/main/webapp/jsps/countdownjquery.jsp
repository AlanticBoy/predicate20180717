<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/4/24
  Time: 20:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8" />
    <title>倒计时秒针特效</title>

    <!-- Our CSS stylesheet file -->
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans+Condensed:300" />
    <link rel="stylesheet" href="../countdown/assets/css/styles.css" />
    <link rel="stylesheet" href="../countdown/assets/countdown/jquery.countdown.css" />

    <!--[if lt IE 9]>
    <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
</head>
<body>

<div id="countdown"></div>

<p id="note"></p>

<shiro:user>
    欢迎<shiro:principal/>登录
</shiro:user><br><br>
<shiro:hasPermission name="user:add">
    用户<shiro:principal/>有user:add权限
</shiro:hasPermission><br><br>

<shiro:hasPermission name="user:delete">
    用户<shiro:principal/>有user:delete权限
</shiro:hasPermission><br><br>

<shiro:hasPermission name="user:update">
    用户<shiro:principal/>有user:update权限
</shiro:hasPermission><br><br>

<shiro:hasPermission name="user:expire">
    用户<shiro:principal/>有user:expire权限
</shiro:hasPermission><br><br>


<div style="text-align:center;clear:both; font-size:12px; margin-top:100px;">
    <script src="http://www.jq22.com/jquery/jquery-1.7.1.js"></script>
    <script src="../countdown/assets/countdown/jquery.countdown.js"></script>
    <script src="../countdown/assets/js/script.js"></script>

</body>
</html>
