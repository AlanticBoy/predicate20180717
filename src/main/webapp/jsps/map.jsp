<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no"/>
    <style type="text/css">
        body, html, #allmap {
            width: 100%;
            height: 100%;
            overflow: hidden;
            margin: 0;
            font-family: "微软雅黑";
        }
    </style>
    <script type="text/javascript"
            src="http://api.map.baidu.com/api?v=2.0&ak=LHTaSBIi7XLzYzD5aj4RK5cO4zrcAxtk"></script>
    <script type="text/javascript"
            src="../js/map.js"></script>
    <script type="text/javascript"
            src="http://api.map.baidu.com/library/DistanceTool/1.2/src/DistanceTool_min.js"></script>

    <script src="../js/jquery-1.7.1.min.js"></script>

    <title>批量转换</title>

</head>
<body>
<div id="allmap"></div>
</body>
</html>
<script type="text/javascript">
    var map = new BMap.Map("allmap");//在container容器中创建一个地图,参数container为div的id属性;
    map.centerAndZoom(new BMap.Point(113.646604, 34.755857), 15); //将point移到浏览器中心，并且地图大小调整为15;
    map.enableScrollWheelZoom(); //启用滚轮放大缩小，默认禁用
    map.enableContinuousZoom(); //启用地图惯性拖拽，默认禁用
    map.addControl(new BMap.NavigationControl());
    map.addControl(new BMap.MapTypeControl());
    map.addControl(new BMap.ScaleControl());
    map.addControl(new BMap.OverviewMapControl());

    var mappoints = new Array();
    var potNums = new Array();
    $(document).ready(function () {
        $.ajax({
            type: "post",
            url: "Monitor/getMapList",
            dataType: "json",
            success: function (data) {
                var len = data.lat.length;
                for (var i = 0; i < len; i++) {
                    mappoints.push(new BMap.Point(data.lon[i], data.lat[i]));
                    potNums.push(data.no[i]);
                }
                BMap.Convertor.transMore(mappoints, 0, callback);
            },
            error: function (request) {
                alert("Connection error");
            }
        });
    });

    /* 回调函数 */
    function callback(xyResults) {
        var xyResult = null;
        var dex = 0;
        for (var index in xyResults) {

            xyResult = xyResults[index];
            if (xyResult.error != 0) {
                continue;
            }//出错就直接返回;
            var point = new BMap.Point(xyResult.x, xyResult.y);
            var marker = new BMap.Marker(point);
            marker.no = potNums[index];
            map.addOverlay(marker);
            addClickHandler(marker);  //为每一个点添加点击事件
            map.setCenter(point);// 由于写了这句，每一个被设置的点都是中心点的过程
        }
    }
    function addClickHandler(marker) {

        //为marker添加点击事件。
        marker.addEventListener("click", function (e) {
            $.ajax({
                cache: true,
                type: "POST",
                url: "Monitor/getMarkMapList",
                data: {
                    no: this.no
                },
                async: false,
                error: function (request) {
                    alert("Connection error");
                },
                success: function (data) {
                    jsonObj = JSON.parse(data);

                }
            });
            showInfo(this, jsonObj);
        });
    }

    function showInfo(thisMaker, point) {
        var sContent = '<ul style="margin:0 0 5px 0;padding:0.2em 0">'
            + '<li style="line-height: 26px;font-size: 15px;">'
            + '<img src="../upload/20180422202156962.jpg" "></img>'
            + '</li>'
            + '<li style="line-height: 26px;font-size: 15px;">'
            + '<span style="width: 75px;display: inline-block;">设备编号：</span>'
            + point.no
            + '</li>'
            + '<li style="line-height: 26px;font-size: 15px;">'
            + '<span style="width: 50px;display: inline-block;">经度：</span>'
            + point.longitude
            + '</li>'
            + '<li style="line-height: 26px;font-size: 15px;">'
            + '<span style="width: 50px;display: inline-block;">纬度：</span>'
            + point.latitude
            + '</li>'
            + '<li style="line-height: 26px;font-size: 15px;">'
            + '<span style="width: 50px;display: inline-block;">余量：</span>'
            + point.remain
            + '</li>'
            + '<li style="line-height: 26px;font-size: 15px;">'
            + '<span style="width: 75px;display: inline-block;">更新时间：</span>'
            + point.atime + '</li>'
            //+'<li style="line-height: 26px;font-size: 15px;"><span style="width: 50px;display: inline-block;">查看：</span><a href="'+point.url+'">详情</a></li>'
            + '</ul>';
        var infoWindow = new BMap.InfoWindow(sContent); // 创建信息窗口对象
        thisMaker.openInfoWindow(infoWindow); //图片加载完毕重绘infowindow
    };

</script>


