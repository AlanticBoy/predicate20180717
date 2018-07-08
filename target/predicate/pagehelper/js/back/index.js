$(function(){
	
	var clickNum = 0;//导航栏的隐藏显示
	var kslrClickCount = 0;//考生录入下拉菜单的下拉收起
	var stlrClickCount = 0;//试题录入下拉菜单的下拉收起
	var sjglClickCount = 0;//试卷管理下拉菜单的下拉收起
	var dictClickCount = 0;//字段管理下拉菜单的下拉收起
	var testClickCount = 0;//试后统计下拉菜单的下拉收起
	
	//自适应iframe高度
	$("#mainIframe, #mainIframe2").load(function(){
		
		var height = $("body").height() - 50;
		var userAgent = navigator.userAgent.toLowerCase();
		var ie = /msie/.test(userAgent)&&!/opera/.test(userAgent);
		if(ie) { 
			var width = $("body").parent().width() - $(".left-side").width() - 30;
			$(this).css("float", "right");
			$(this).width(width);
		}else{
			var width = $("body").parent().width();
			$(this).css("float", "right");
			$(this).width(width);
		}
		$(this).height(height);
		
	});
	
	//iframe宽度自适应侧边栏的隐藏和显示
	$("[data-toggle='offcanvas']").click(function(){
		
		clickNum++;
		if(clickNum % 2 == 0){//显示侧边栏
			$("#mainIframe").css("margin-left", "0px");
			$("#mainIframe").width($("#mainIframe").width() - 220);
			
			$("#mainIframe2").css("margin-left", "0px");
			$("#mainIframe2").width($("#mainIframe2").width() - 220);
		}else{//隐藏侧边栏
			$("#mainIframe").css("margin-left", "-220px");
			$("#mainIframe").width($("#mainIframe").width() + 220);
			
			$("#mainIframe2").css("margin-left", "-220px");
			$("#mainIframe2").width($("#mainIframe2").width() + 220);
		}
		
	});
	
	//设置点击的导航处于活动状态
	$(".nav").click(function(){
		
		$(".nav").each(function(){
			$(this).removeClass("active");
		});
		$(this).addClass("active");
		
	});
	
	//考生录入下拉框的下拉收起
	$("#kslr").click(function(){
		
		kslrClickCount++;
		if(kslrClickCount % 2 == 0){//合并
			$("#ks").removeClass("glyphicon glyphicon-chevron-up");
			$("#ks").addClass("glyphicon glyphicon-chevron-down");
		}else{//下拉
			$("#ks").removeClass("glyphicon glyphicon-chevron-down");
			$("#ks").addClass("glyphicon glyphicon-chevron-up");
		}
		
	});
	
	//试题录入下拉框的下拉收起
	$("#stlr").click(function(){
		
		stlrClickCount++;
		if(stlrClickCount % 2 == 0){//合并
			$("#st").removeClass("glyphicon glyphicon-chevron-up");
			$("#st").addClass("glyphicon glyphicon-chevron-down");
		}else{//下拉
			$("#st").removeClass("glyphicon glyphicon-chevron-down");
			$("#st").addClass("glyphicon glyphicon-chevron-up");
		}
		
	});
	
	//试卷管理下拉框的下拉收起
	$("#sjgl").click(function(){
		
		sjglClickCount++;
		if(sjglClickCount % 2 == 0){//合并
			$("#sj").removeClass("glyphicon glyphicon-chevron-up");
			$("#sj").addClass("glyphicon glyphicon-chevron-down");
		}else{//下拉
			$("#sj").removeClass("glyphicon glyphicon-chevron-down");
			$("#sj").addClass("glyphicon glyphicon-chevron-up");
		}
		
	});
	
	//字典管理下拉框的下拉收起
	$("#dictManager").click(function(){
		
		dictClickCount++;
		if(dictClickCount % 2 == 0){//合并
			$("#dict").removeClass("glyphicon glyphicon-chevron-up");
			$("#dict").addClass("glyphicon glyphicon-chevron-down");
		}else{//下拉
			$("#dict").removeClass("glyphicon glyphicon-chevron-down");
			$("#dict").addClass("glyphicon glyphicon-chevron-up");
		}
		
	});
	
	//试后统计下拉框的下拉收起
	$("#testManager").click(function(){
		
		dictClickCount++;
		if(dictClickCount % 2 == 0){//合并
			$("#test").removeClass("glyphicon glyphicon-chevron-up");
			$("#test").addClass("glyphicon glyphicon-chevron-down");
		}else{//下拉
			$("#test").removeClass("glyphicon glyphicon-chevron-down");
			$("#test").addClass("glyphicon glyphicon-chevron-up");
		}
		
	});
	
});