<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/22
  Time: 20:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="../js/jquery-1.7.1.min.js"></script>
    <script type="text/javascript">


        function submitMesg() {
            var phone = $("#phone").val();
            var name = $("#name").val();
            var email = $("#email").val();
            var password = $("#password").val();

            $.ajax({
                type: "post",
                url: "/user/getAndGive.action",
                dataType: 'json',
                data: {phone: phone, name: name, email: email, password: password},
                success: function (result) {
                    alert(" 添加成功 " + result.mseg)
                }
            });
        }
    </script>
</head>
<body>
<div>
    手机： <input id="phone" type="text">
    邮箱：<input id="email" type="text">
    密码： <input id="password" type="text">
    姓名：<input id="name" type="text">
    <button type="button" id="clickMe" onclick="submitMesg()">点我啊</button>
</div>

</body>
</html>
