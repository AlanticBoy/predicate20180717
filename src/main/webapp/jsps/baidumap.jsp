<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/4/24
  Time: 19:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>适应屏幕</title>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=1.5&ak=YWdGplhYjUGQ3GtpKNeuTM2S"></script>

</head>
<body>
<style type="text/css">
    html {
        height: 100%
    }

    body {
        height: 100%;
        margin: 0;
        padding: 0px
    }

    #container {
        height: 80%;
        width: 80%;
        left: 250px;
        margin: 20px;
    }

    .info_ul {
        margin: 0 0 5px 0;
        padding: 0.2em 0;
    }

    .info_li {
        line-height: 26px;
        font-size: 15px;
    }

    .info_span {
        width: 50px;
        display: inline-block;
    }
</style>
<div>
    起始位置:<input type="text" id="start">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;终点位置:<input type="text" id="end">
</div>
<div id="container"></div>
<script type="text/javascript">
    var mappoints = new Array();
    mappoints.push("aaa");
    mappoints.push("bbb");
    mappoints.push("ccc");
    mappoints.push("ddd");
    mappoints.push("eee");


    //数据准备,模拟数据
    var points = [
        {"lng": 113.517652, "lat": 34.818929, "status": 1, "id": 50},
        {"lng": 113.517821, "lat": 34.816873, "status": 1, "id": 2},
        {"lng": 113.516357, "lat": 34.813965, "status": 0, "id": 3},
        {"lng": 113.508883, "lat": 34.818173, "status": 0, "id": 4},
        {"lng": 113.527047, "lat": 34.820795, "status": 1, "id": 5}
    ];

    var map = new BMap.Map("container");//初始化地图
    setZoom(points);//设置中心点和缩放级别。
    addMarker(points);//把原始数据的轨迹点添加到地图上。
    map.enableScrollWheelZoom();//滚轮放大缩小
    //下面是用到的函数
    //根据原始数据计算中心坐标和缩放级别，并为地图设置中心坐标和缩放级别。
    function setZoom(points) {
        if (points.length > 0) {
            var maxLng = points[0].lng;
            var minLng = points[0].lng;
            var maxLat = points[0].lat;
            var minLat = points[0].lat;
            var res;
            for (var i = points.length - 1; i >= 0; i--) {
                res = points[i];
                if (res.lng > maxLng) maxLng = res.lng;
                if (res.lng < minLng) minLng = res.lng;
                if (res.lat > maxLat) maxLat = res.lat;
                if (res.lat < minLat) minLat = res.lat;
            }
            ;
            var cenLng = (parseFloat(maxLng) + parseFloat(minLng)) / 2;
            var cenLat = (parseFloat(maxLat) + parseFloat(minLat)) / 2;
            var zoom = getZoom(maxLng, minLng, maxLat, minLat);
            /*
             其中map.centerAndZoom(new BMap.Point(cenLng,cenLat), 14)
             cenLng和cenLat是经纬度，在new BMap.Point()是中心位置，14是比例尺,看到地图的缩放
             113.646604, 34.755857是郑州的某处坐标
             */
            //map.centerAndZoom(new BMap.Point(113.646604, 34.755857), 14);
            map.centerAndZoom(new BMap.Point(cenLng, cenLat), zoom);
        } else {
            //没有坐标，显示全中国。
            //map.centerAndZoom(new BMap.Point(103.388611,35.563611), 5);
            //没有坐标，显示整个郑州。
            map.centerAndZoom(new BMap.Point(113.646604, 34.755857));
        }
    }
    //根据经纬极值计算绽放级别。本例核心代码。
    function getZoom(maxLng, minLng, maxLat, minLat) {
        var zoom = ["50", "100", "200", "500", "1000", "2000", "5000", "10000", "20000", "25000", "50000", "100000", "200000", "500000", "1000000", "2000000"]//级别18到3。
        var pointA = new BMap.Point(maxLng, maxLat);  // 创建点坐标A
        var pointB = new BMap.Point(minLng, minLat);  // 创建点坐标B
        var distance = map.getDistance(pointA, pointB).toFixed(1);  //获取两点距离,保留小数点后1位
        for (var i = 0, zoomLen = zoom.length; i < zoomLen; i++) {
            if (zoom[i] - distance > 0) {
                return 18 - i + 3;//之所以会多3，是因为地图范围常常是比例尺距离的10倍以上。所以级别会增加3。
            }
        }
        ;
    }
    //在轨迹点上创建图标，并添加点击事件
    function addMarker(points) {
        var point, marker;
        // 创建标注对象并添加到地图
        for (var i = 0, pointsLen = points.length; i < pointsLen; i++) {
            point = new BMap.Point(points[i].lng, points[i].lat);
            marker = new BMap.Marker(point);
            map.addOverlay(marker);
            //给标注点添加点击事件。使用立即执行函数和闭包
            (function () {
                var thePoint = points[i];
                marker.addEventListener("click", function () {
                    showInfo(this, thePoint);
                });
            })();
        }
    }


    //显示信息窗口，显示标注点的信息。
    function showInfo(thisMaker, point) {
        var sContent = '<div style="widht:600px;height: auto">'
            + '<div style="float:left;width:180px;">'
            + '<img src="../upload/pot.jpg" style="width:150px;height:150px;" ></img>'
            + '</div>'
            + '<div style=" width:300px;">'
            + '<ul class="info_ul">'
            + '<li class="info_li">'
            + '<span class="info_span">id：</span>' + point.id + '</li>'
            + '<li class="info_li">'
            + '<span class="info_span">级别：</span>' + point.status + '</li>'
            + '<li class="info_li"><span class="info_span">查看：</span><a href="' + point.url + '" target="_blank">详情</a></li>'
            + '</ul>'
            + '</div>'
            + '</div>';

        var infoWindow = new BMap.InfoWindow(sContent);// 创建信息窗口对象
        thisMaker.openInfoWindow(infoWindow);//图片加载完毕重绘infowindow
    }
    //显示信息窗口，显示标注点的信息。
    /*function showInfo(thisMaker, point) {
     var sContent =
     '<ul class="info_ul">'
     + '<li style="line-height: 26px;font-size: 15px;">'
     + '<img src="../upload/pot.jpg" "></img>'
     + '</li>'
     + '<li class="info_li">'
     + '<span class="info_span">id：</span>' + point.id + '</li>'
     + '<li class="info_li">'
     + '<span class="info_span">级别：</span>' + point.status + '</li>'
     + '<li class="info_li"><span class="info_span">查看：</span><a href="' + point.url + '" target="_blank">详情</a></li>'
     + '</ul>';
     var infoWindow = new BMap.InfoWindow(sContent);// 创建信息窗口对象
     thisMaker.openInfoWindow(infoWindow);//图片加载完毕重绘infowindow
     }*/
</script>


</body>
</html>
