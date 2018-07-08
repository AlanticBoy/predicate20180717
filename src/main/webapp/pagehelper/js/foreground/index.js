$(function(){
	
	var clickNum = 0;//导航栏的隐藏显示
	
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
	
});