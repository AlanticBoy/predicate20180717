$(function(){
	
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
	
	//批量添加到试卷
	$("#addToSJ").click(function(){
		
		var len = $("input[name='check']:checked").length;
		if(len == 0){
			alert("请选择需要添加到测评的记录！");
			return false;
		}else{
			if(confirm("确定要将这" + len + "道题目添加到测评中吗？")){
				var pid = $.trim($("#pid").val());
				var ids = "";
				$("input[name='check']:checked").each(function(){
					ids += $(this).val() + ",";
				});
				ids = ids.substring(0, ids.length - 1);
				window.location.href="addToST?ids=" + ids + "&pid=" + pid;
			}
		}
		
	});
	
});