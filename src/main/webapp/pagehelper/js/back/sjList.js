$(function(){
	
	//初始化时间控件
	$(".year").datetimepicker({
		format: 'yyyy-mm-dd hh:ii:00',//自定义日期格式
	    language:  "zh-CN",//语言
	    weekStart: true,//显示日期
	    todayBtn:  true,//显示今天按钮
		autoclose: true,//选择日期后，自动关闭日期框
		todayHighlight: true,
		startView: 0,//开始视图
		minView: 0,//最小视图
		forceParse: 0
	});
	
	//全选和取消全选
	$("input[name='checkAll']").click(function(){
		if($("input[name='checkAll']").is(":checked") == true){//全选
			$("input[name='check']").each(function(){
				this.checked = true;
			});
		}else{//取消全选
			$("input[name='check']").removeAttr("checked");
		}
	});
	
	$("input[name='check']").click(function(){
		var len = $("input[name='check']").length;
		var checklen = $("input[name='check']:checked").length;
		if(checklen < len){//没有被选中的
			$("input[name='checkAll']").removeAttr("checked");
		}else if(checklen == len){//全部都被选中
			$("input[name='checkAll']").each(function(){
				this.checked = true;
			});
		}
	});
	
	//批量删除
	$("#delSJ").click(function(){
		
		var len = $("input[name='check']:checked").length;
		if(len == 0){
			alert("请选择需要删除的记录！");
			return false;
		}else{
			if(confirm("确定要删除这" + len + "条记录吗？")){
				var ids = "";
				$("input[name='check']:checked").each(function(){
					ids += $(this).val() + ",";
				});
				ids = ids.substring(0, ids.length - 1);
				window.location.href="delSJ?ids=" + ids;
			}
		}
		
	});
	
});
