<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Ajax异步上传图片</title>
    <-- 引入jQuery异步上传js文件 -->
    <script src="../js/jquery-1.7.1.min.js"></script>
    <script src="//oss.maxcdn.com/jquery.form/3.50/jquery.form.min.js"></script>
    <!-- Ajax异步上传图片 -->
    <script type="text/javascript">
        function uploadPic() {
            alert("ook")
            // 上传设置
            var options = {
                // 规定把请求发送到那个URL
                url: "/user/uploadPic.action",
                // 请求方式
                type: "post",
                // 服务器响应的数据类型
                dataType: "json",
                // 请求成功时执行的回调函数
                success: function (data, status, xhr) {
                    // 图片显示地址
                    $("#allUrl").attr("src", data.path);
                }
            };

            $("#jvForm").ajaxSubmit(options);
        }
    </script>
</head>
<body>
<form id="jvForm" action="${pageContext.request.contextPath}/user/uploadPic.action" method="post" enctype="multipart/form-data">
    <table>
        <tbody>
        <tr>
            <td width="20%">
                <span>*</span>
                上传图片(90x150尺寸):
            </td>
            <td width="80%">
                注:该尺寸图片必须为90x150。
            </td>
        </tr>
        <tr>
            <td width="20%"></td>
            <td width="80%">
                <img width="100" height="100" id="allUrl"/>
                <!-- 在选择图片的时候添加事件，触发Ajax异步上传 -->
                <input name="pic" type="file" onchange="uploadPic()"/>
            </td>
        </tr>
        </tbody>
    </table>
</form>
</body>
</html>