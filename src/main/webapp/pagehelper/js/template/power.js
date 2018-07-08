	
//得到用户权限
var str = "";
$.ajax({
	url: "getAdminPower",
	type: "post",
	dataType: "json",
	success: function(data){
		for(var i = 0; i < data.length; i ++){
			if(data[i].indexOf("考生") >= 0)
				$("[pid='考生管理']").show();
			if(data[i].indexOf("试题") >= 0)
				$("[pid='题库管理']").show();
			if(data[i].indexOf("成绩") >= 0)
				$("[pid='成绩管理']").show();
			$("[pid=" +data[i] + "]").show();
			
			str += data[i];
		}
		
		if(str.indexOf("考生查询") >= 0){
			$("#mainIframe").prop("src", "ceTree?flag=1");
		}else if(str.indexOf("考生导入") >= 0){
			$("#mainIframe").prop("src", "jumpImport?type=1");
		}else if(str.indexOf("考生录入") >= 0){
			$("#mainIframe").prop("src", "jumpKSEntry");
		}else if(str.indexOf("试题查询") >= 0){
			$("#mainIframe").prop("src", "stList?pageNo=1&subject=0&type=0");
		}else if(str.indexOf("试题导入") >= 0){
			$("#mainIframe").prop("src", "jumpImport?type=2");
		}else if(str.indexOf("试题录入") >= 0){
			$("#mainIframe").prop("src", "jumpSTEntry");
		}else if(str.indexOf("成绩查询") >= 0){
			$("#mainIframe").prop("src", "ceTree?flag=2");
		}else if(str.indexOf("成绩统计") >= 0){
			$("#mainIframe").prop("src", "jumpAnalysis");
		}else if(str.indexOf("系统设置") >= 0){
			$("#mainIframe").prop("src", "jumpSystemSetting?id=1");
		}else if(str.indexOf("模板下载") >= 0){
			$("#mainIframe").prop("src", "jumpDownload");
		}
		
		
	}
});