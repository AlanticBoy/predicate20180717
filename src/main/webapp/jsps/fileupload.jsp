<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/4/21
  Time: 20:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="multipart/form-data; charset=utf-8"/>
    <script src="../js/jquery-1.7.1.min.js"></script>
    <script src="http://malsup.github.com/jquery.form.js"></script>
    <title>Title</title>
    <script>
        /*文件校验*/
        function validation() {
            var file = "";
            var suffix = "";
            file = document.getElementById("excel").value;

            if (file != null && file != "")
                suffix = file.substring(file.length - 4);

            if (suffix == "" || suffix == null) {
                alert("请选择上传文件！");
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
<div align="center" style="margin-top: 10%;">
    <form action="${pageContext.request.contextPath}/user/importMedicalInfo.action" method="post"
          onsubmit="validation()" enctype="multipart/form-data">
        <div align="center" style="margin-top: 10%;">
            <input type="file" name="file" id="excel"/><br/>
            <input type="submit" class="btn btn-primary" value="导入"/>
        </div>
    </form>
</div>
</body>
</html>
