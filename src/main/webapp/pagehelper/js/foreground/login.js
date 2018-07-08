//login.jsp页面所需的jQuery

$(function() {

	// 用户登录表单提交验证
	$("#loginForm").submit(function() {
		
		var userName = $.trim($("#userName").val());
		var userPwd = $.trim($("#userPwd").val());
		if (userName == null || userName == "") {
			alert("请输入身份证号！");
			return false;
		} else if (userPwd == null || userPwd == "") {
			alert("请输入登录密码！");
			return false;
		} 
		return true;

	});

});
