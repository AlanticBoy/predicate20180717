<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/1
  Time: 12:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="http://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>

    <script src="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">

    <script type="text/javascript">
        var data=1;
        if (data==1)
        {
            /*页面一加载。执行该方法。
             */
            var timer= window.setInterval(function () {
                getMsgNum();
            }, 6000);
        }
        function getMsgNum() {
            var info = null;
            $.ajax({
                url: '/quartz/backMess.action',
                type: 'post',
                async: false,
                dataType: 'json',
                success: function (data) {
                    info = data.record;
                }
            });
            if ((info != null) && (info != "")) {
                $("#messDiv").css("display","none");
                $('#exampleModal').modal({
                    keyboard: false
                });
                $('#exampleModal').on('show.bs.modal', function () {
                    // 执行一些动作...
                    /*当模态框出现时，把setInterval清除*/
                    window.clearInterval(timer);
                });
            }
        }
        /*当关闭模态框时，重新加载setInterval*/
        function closeModel(){
            $('#exampleModal').modal('hide');
            timer= window.setInterval(function () {
                getMsgNum();
            }, 6000);

        }

    </script>
</head>
<body>
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div id="messDiv" class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">×</span></button>
                <h4 class="modal-title" id="exampleModalLabel">New message</h4>
            </div>
            <div class="modal-body">
                <form>
                    <div class="form-group">
                        <label for="recipient-name" class="control-label">Recipient:</label>
                        <input type="text" class="form-control" id="recipient-name">
                    </div>
                    <div class="form-group">
                        <label for="message-text" class="control-label">Message:</label>
                        <textarea class="form-control"   id="message-text"></textarea>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" onclick="closeModel()">Send message</button>
            </div>
        </div>
    </div>
</div>
坐等信息

</body>
</html>
