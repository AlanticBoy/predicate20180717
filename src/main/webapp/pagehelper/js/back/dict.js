$(function(){
	
	//设置th,td
	$("th, td").each(function(){
		
		$(this).css("text-align", "center");//水平居中
		$(this).css("vertical-align", "middle");//垂直居中
		
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
	
	//增加提交验证
	$("#dictForm").submit(function(){
		
		var type = $("#d_type").val().trim();//字典类型
		var code = $("#d_code").val().trim();//字典代码
		var name = $("#d_name").val().trim();//字典中文名称
		if(type == null || type == ""){
			alert("保存出错！请刷新重试！");
			return false;
		}else if(code == null || code == ""){
			alert("请输入学科或校区代码！");
			return false;
		}else if(code == 0){
			alert("学科或校区代码不能为0！");
			return false;
		}else if(name == null || name == ""){
			alert("请输入学科或校区名称！")
			return false;
		}
		return true;
		
	});
	
	//修改提交验证
	$("#dictForm2").submit(function(){
		
		var id = $("#d_id2").val().trim();//主键
		var type = $("#d_type2").val().trim();//字典类型
		var code = $("#d_code2").val().trim();//字典代码
		var name = $("#d_name2").val().trim();//字典中文名称
		if(type == null || type == "" || id == null || id == ""){
			alert("保存出错！请刷新重试！");
			return false;
		}else if(code == null || code == ""){
			alert("请输入学科或校区代码！");
			return false;
		}else if(code == 0){
			alert("学科或校区代码不能为0！");
			return false;
		}else if(name == null || name == ""){
			alert("请输入学科或校区名称！")
			return false;
		}
		return true;
		
	});
	
	//批量删除
	$("#delCheck").click(function(){
		
		var len = $("input[name='check']:checked").length;
		var type = $("#d_type").val().trim();
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
				window.location.href="delDict?ids=" + ids + "&type=" + type;
			}
		}
		
	});
	
	//清除缓存数据
	$("addButton").click(function(){
		
		$("#d_code").val("");
		$("#d_name").val("");
		
	});
	
});
