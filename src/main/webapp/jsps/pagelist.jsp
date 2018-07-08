<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta name="renderer" content="webkit"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>在线考试系统-考生列表</title>

    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" type="text/css" href="../pagehelper/bootstrap/bootstrap-datetimepicker.min.css">
    <!-- 引入模板的css文件 -->
    <link rel="stylesheet" type="text/css" href="../pagehelper/css/template/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="../pagehelper/css/template/ionicons.min.css">
    <link rel="stylesheet" type="text/css" href="../pagehelper/css/template/AdminLTE2.css">
    <!-- 引入datetimepicker -->
    <link rel="stylesheet" type="text/css" href="../pagehelper/bootstrap/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet" type="text/css" href="../pagehelper/layer/skin/layer.css">

    <style type="text/css">
        th, td {
            text-align: center;
            vertical-align: middle;
        }
    </style>
</head>
<body>
<div class="input-group-btn" align="center">
    <button class="btn btn-primary" type="button" onclick="queryList('1')">&nbsp;查询&nbsp;</button>
</div>
<div class="container-fluid" align="center">
    <aside class="right-side">
        <section class="content row">
            <table class="table table-bordered table-striped">
                <tr>
                    <th class="col-md-1">编号</th>
                    <th class="col-md-1">考生姓名</th>
                    <th class="col-md-1">性别</th>
                    <th class="col-md-1">电子邮箱</th>
                    <th class="col-md-1">密码</th>
                    <th class="col-md-1">家庭住址</th>
                </tr>
                <c:forEach var="bean" items="${list}" varStatus="status">
                    <tr>
                        <th><input type="checkbox" name="check" id="check" value="${bean.id }"/></th>
                        <th>${bean.id }</th>
                        <th>${bean.name }</th>
                        <th>${bean.sex}</th>
                        <th>${bean.email}</th>
                        <th>${bean.password}</th>
                        <th>${bean.address}</th>
                    </tr>
                </c:forEach>
            </table>
        </section>
        <div style="text-align: center;">
            <ul class="pagination">
                <c:if test="${!page.isFirstPage}">
                    <li><a href="javascript:queryList(${page.firstPage});">首页</a></li>
                    <li><a href="javascript:queryList(${page.prePage});">上一页</a></li>
                </c:if>
                <c:forEach items="${page.navigatepageNums}" var="navigatepageNum">
                    <c:if test="${navigatepageNum==page.pageNum}">
                        <li class="active"><a
                                href="javascript:queryList(${navigatepageNum});">${navigatepageNum}</a></li>
                    </c:if>
                    <c:if test="${navigatepageNum!=page.pageNum}">
                        <li><a href="javascript:queryList(${navigatepageNum});">${navigatepageNum}</a></li>
                    </c:if>
                </c:forEach>
                <c:if test="${!page.isLastPage}">
                    <li><a href="javascript:queryList(${page.nextPage});">下一页</a></li>
                    <li><a href="javascript:queryList(${page.lastPage});">最后一页</a></li>
                </c:if>
            </ul>
        </div>
    </aside>
</div>
<script type="text/javascript">
    function queryList(pageNum) {
        window.location.href = "${pageContext.request.contextPath}/user/showAllUnit.action?pageNum=" + pageNum;
    }
</script>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script type="text/javascript" src="../pagehelper/js/template/jquery-1.12.0.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script type="text/javascript" src="../pagehelper/bootstrap/bootstrap.min.js"></script>
<!-- 引入身份证合法性验证的js -->
<script type="text/javascript" src="../pagehelper/js/template/verificationID.js"></script>
<!-- 引入datetimepicker -->
<script type="text/javascript" src="../pagehelper/bootstrap/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="../pagehelper/bootstrap/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="../pagehelper/js/back/list.js"></script>
<script type="text/javascript" src="../pagehelper/layer/layer.js"></script>
</body>
</html>