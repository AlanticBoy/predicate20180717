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
	
	//批量移出
	$("#removeST").click(function(){
		
		var len = $("input[name='check']:checked").length;
		if(len == 0){
			alert("请选择需要移除的记录！");
			return false;
		}else{
			if(confirm("确定要将这" + len + "道题目移出测评吗？")){
				var pid = $.trim($("#pid").val());
				var ids = "";
				$("input[name='check']:checked").each(function(){
					ids += $(this).val() + ",";
				});
				ids = ids.substring(0, ids.length - 1);
				window.location.href="removeST?ids=" + ids + "&pid=" + pid;
			}
		}
		
	});
	
	//批量设置试题
	$("#bacthST").click(function(){
		
		var len = $("input[name='check']:checked").length;
		if(len == 0){
			alert("请选择需要操作的题目！");
			return false;
		}else{
			var ids = "";
			$("input[name='check']:checked").each(function(){
				ids += $(this).val() + ",";
			});
			ids = ids.substring(0, ids.length - 1);
			$("#p_id").val($("#pid").val())
			$("#t_ids").val(ids);
			$.ajax({
				type: "POST",
				url: "getDictByType",
				data: {
					type: "course"
				},
				dataType: "json",
				success: function(data){
					$("#t_subject").empty();
					for(var i = 0; i < data.length; i++){
						$("#t_subject").append("<option value='" + data[i].d_id + "'>" + data[i].d_name + "</option>");
					}
				}
			});
		}
		
	});
	
	//剩余试题
	$("#remainST").click(function(){
		
		var pid = $.trim($("#pid").val());
		window.location.href = "remainST?pid=" + pid;
		
	});
	
	//批量提交
	$("#batchFrm").submit(function(){
		
		var tids = $.trim($("#t_ids").val());
		var tpoint = $.trim($("#t_score").val());
		if(tids == null || tids == ""){
			alert("获取参数失败！请重试！");
			return false;
		}else if(tpoint == null || tpoint == ""){
			alert("请输入分数！");
			return false;
		}else{
			if(confirm("确定批量更新这几道题目的属性吗?")){
				return true;
			}
			return false;
		}
		
	});
	
});