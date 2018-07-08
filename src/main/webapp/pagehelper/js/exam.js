function AutoTime() {
	if(-1 == ExamS && (ExamM -= 1, ExamS = 59), -1 == ExamM || 1 == ExamEnter) {
		alert("不好意思，时间到了，您将返回登录页面!");
		location.href = "login.html";
		return -1;
	}
	var a, b;
	a = "0" + ExamM, b = "0" + ExamS, 3 == a.length && (a = ExamM), 3 == b.length && (b = ExamS), $("#ViewTime").html("<b>" + a + ":" + b + "</b>"), ExamS -= 1, setTimeout("AutoTime()", 1e3)
}

function TimeStop(a) {
	var a = a || "00:00";
	Wink("ViewTime", "<i>" + a + "</i>", 300)
}

function vEO(a) {
	if(1 == ExamEnter) return !1;
	if(orderTmp > 0) {
		var b = ExamDaSel[orderTmp];
		"&nbsp;" != b && " " != b && "" != b ? $("#EOV" + orderTmp).attr("class", "ExamOrderViewVisited") : $("#EOV" + orderTmp).attr("class", "ExamOrderViewWait")
	}
	return $("#EOV" + a).attr("class", "ExamOrderViewHover"), orderTmp == a ? !1 : (orderTmp = a, void 0)
}

function gotoExam(a) {
	a = Math.abs(a), vEO(a), orderT = Math.abs(a - 1), 0 == orderT && (orderT = 1);
	var b = $("#LI" + orderT).offset().top;
	b -= 20, $("html,body").animate({
		scrollTop: b
	}, 200)
}

function ExamSubmitCfm() {
	if(confirm("您确定要提交试卷吗？")) {
		location.href = "login.html";
	} else {
		alert("您将返回考试界面！");  
	}
}


function gotoKEY(a) {
	if(order = $(a).parent().attr("id").replace(/LI/i, ""), order = Math.abs(order), DaT = $(a).html(), "正确" == DaT) DaT = "R";
	else if("错误" == DaT) DaT = "E";
	else if(DaT = DaT.substr(0, 1), ExamDa[order].length > 1) {
		
		"A" == DaT && (b.indexOf("A") >= 0 ? (b = b.replace(/A/g, ""), $(a).removeClass("sel")) : (b += "A", $(a).addClass("sel"))), "B" == DaT && (b.indexOf("B") >= 0 ? (b = b.replace(/B/g, ""), $(a).removeClass("sel")) : (b += "B", $(a).addClass("sel"))), "C" == DaT && (b.indexOf("C") >= 0 ? (b = b.replace(/C/g, ""), $(a).removeClass("sel")) : (b += "C", $(a).addClass("sel"))), "D" == DaT && (b.indexOf("D") >= 0 ? (b = b.replace(/D/g, ""), $(a).removeClass("sel")) : (b += "D", $(a).addClass("sel"))), b = formatKey(b), DaT = b, b = ""
	}
	ExamDaSel[order] = DaT, 1 == ExamDa[order].length && ($("#LI" + order + " b").removeClass("sel"), $(a).addClass("sel")), vEO(order), ExamCount > order && 1 == ExamDa[order].length /*&& gotoExam(order + 1)*/
}

function formatKey(a) {
	return a ? (a = a.toUpperCase(), svT = "", a.indexOf("A") >= 0 && (svT += "A"), a.indexOf("B") >= 0 && (svT += "B"), a.indexOf("C") >= 0 && (svT += "C"), a.indexOf("D") >= 0 && (svT += "D"), svT) : ""
}

function ExamStart() {
	$(".ExamBtn").html('<u class="btnJJ">提交试卷</u>'), $(".Exam ul li strong img").bind("click", function() {
		MaxEmTp(this)
	}), $(".Exam ul li b").bind("click", function() {
		gotoKEY(this);
	}), $(".btnJJ").bind("click", function() {
		ExamSubmitCfm();
	});
	try {
		ExamStart_Fun()
	} catch(a) {}
}



function JDKTop() {
	var a = $("#JDKck").offset().top;
	xzval = 0, $("#JDKck").css("height", xzval + "px"), a = parseFloat(Math.abs(a + xzval));
	var b = parseFloat($(window).scrollTop());
	if(b > a) {
		$("#JDK").css("top", xzval + "px");
		var c = Math.abs(ExamCount - 1),
			d = $("#LI" + c).offset().top,
			e = $(".ExamBtn").offset().top;
		b > d && e > b ? ($("#EOVArea").show(), $("#JDKAD").hide()) : b > e ? ($("#EOVArea").hide(), $("#JDKAD").hide()) : ($("#EOVArea").show(), $("#JDKAD").show())
	} else $("#JDK").css("top", parseFloat(a - b) + "px")
}
var ExamKssj =20;
"kms" == km && (ExamKssj = 20);
var ExamM = ExamKssj,
	ExamS = 0,
	ExamEnter = 0,
	LastTime = new Date("3000/12/25 00:00:00"),
	orderTmp = 1,
	ExamID = new Array(ExamCount),
	ExamDa = new Array(ExamCount),
	ExamDaSel = new Array(ExamCount),
	EovCnt = "";
for(i = 1; ExamCount >= i; i++) obj = "#LI" + i, $(obj).prepend('<i onclick="gotoExam(' + i + ');">' + i + "</i>"), ExamID[i] = $(obj).attr("c"), ExamDa[i] = $(obj).attr("k"), ExamDaSel[i] = "", EovCnt += '<li id="EOV' + i + '" class="ExamOrderViewWait" onclick="gotoExam(' + i + ');">' + i + "</li>";
if(dhList) {
	var w = "300",
		h = "189",
		h1 = "200",
		dhList = $.parseJSON(dhList);
	$.each(dhList, function(a, b) {
		$("#LI" + b.i + " strong u").html('<iframe scrolling="no" src="/play.php?v=' + dhFLV(b.t) + "&w=" + w + "&h=" + h + '" frameborder="0" style="margin-top:5px;width:' + w + "px;height:" + h1 + 'px;"></iframe>')
	})
}
setTimeout("ExamStart();", 100), setTimeout("JDKout();", 1e3), $(window).bind("scroll", function() {
	JDKTop()
}), "" != setRC && document.writeln('<script type="text/javascript" src="' + document.location.href + "/RC" + Math.floor(1e7 * Math.random()) + '.js"></script>');