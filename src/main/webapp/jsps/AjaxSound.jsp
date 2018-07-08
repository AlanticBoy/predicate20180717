<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/2
  Time: 19:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="http://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
    <script type="text/javascript">
        var time = 3000;
        function getMsgNum() {
            var info = null;
            $.ajax({
                url: '/quartz/playSound.action',
                type: 'post',
                async: false,
                dataType: 'json',
                success: function (data) {
                    info = data.record;
                }
            });
            if ((info != null) && (info != "")) {
                $('#exampleModal').modal({
                    keyboard: false
                });
                $('#exampleModal').on('show.bs.modal', function () {
                    // 执行一些动作...
                    /*time = 1000 * 60 * 60 * 24;
                     */

                });
            }
        }
        setInterval(function () {
            getMsgNum();
        }, time);

    </script>

</head>
<body>

</body>
</html>
