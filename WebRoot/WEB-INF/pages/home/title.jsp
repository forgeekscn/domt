<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../base.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:useBean id="now" class="java.util.Date" />
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>

    <!-- 调用样式表 -->
    <link rel="stylesheet" rev="stylesheet" type="text/css" href="${ctx}/skin/default/css/default.css" media="all"/>
    <link rel="stylesheet" rev="stylesheet" type="text/css" href="${ctx}/skin/default/css/title.css" media="all"/>

	<script type="text/javascript" src="${ctx}/components/jquery-ui/jquery-1.2.6.js"></script>    	
	<script language="javascript" src="${ctx}/js/pngfix_map.js"></script>
	<script language="javascript" src="${ctx}/js/common.js"></script>
    <!-- 调用外部 JavaScript 脚本语言 -->
	
<script language="javascript">

	function CustomTitle(){
		var Me = document.getElementById('memos');
		var Loin = document.getElementById('logins');
		var Lout = document.getElementById('logout');
		Me.onmouseover = function(){document.getElementById('memo').style.background='url(${ctx}/skin/default/images/title/memo2.gif) no-repeat'};
		Me.onmouseout = function(){document.getElementById('memo').style.background='url(${ctx}/skin/default/images/title/memo.gif) no-repeat'};
		
		Loin.onmouseover = function(){document.getElementById('small_login').style.background='url(${ctx}/skin/default/images/title/small_login2.gif) no-repeat'};
		Loin.onmouseout = function(){document.getElementById('small_login').style.background='url(${ctx}/skin/default/images/title/small_login.gif) no-repeat'};
		
		Lout.onmouseover = function(){document.getElementById('small_login_out').style.background='url(${ctx}/skin/default/images/title/login_out2.gif) no-repeat left -55px;'};
		Lout.onmouseout = function(){document.getElementById('small_login_out').style.background='url(${ctx}/skin/default/images/title/login_out.gif) no-repeat'};
	}
	
	function linkHighlightMenu(obj){
		var links=document.getElementsByTagName('span');
		for(var i=0;i<links.length;i++){
			if(links[i].id.indexOf('topmenu')!=-1){
				links[i].style.background = 'url("${ctx}/skin/default/images/title/li_dot.gif1") no-repeat right 8px';
				links[i].style.color = '';
				links[i].style.fontWeight  = '';
				links[i].style.borderTop = '';
				links[i].style.borderLeft = '';
				links[i].style.borderRight = '';
				links[i].style.padding = '';
			}
		}
		obj.style.background ='url("${ctx}/skin/default/images/title/button_bg.jpg") no-repeat';
		obj.style.color = "#fff";                                                                                                                                                                                                                                                   
		obj.style.fontWeight  = 'bold';
		obj.style.padding= '6px 11x 5px 13px;';
		obj.blur();		//去掉图片的焦点框,使界面看起来漂亮 updated by tony
	}

	//sendRequest('000001');	
	var y = -5; 		//个人信息栏初始Y坐标
	var dy = -40; 		//显示后Y坐标
	
	function doLoginDiv(){
		ShowLoginDiv();
	}
	
	function HideLoginDiv(){
		 dy = dy - 5;
		 MoveHideLoginDiv();
	}	
	function MoveHideLoginDiv(){
		if( dy > -40){setTimeout("HideLoginDiv()",10);}else{ y = -40; dy = -40;}
		document.getElementById("userInfo").style.top = dy;
		
	}
	function ShowLoginDiv(){
		 y = y + 5;
		 MoveShowLoginDiv();
	}	
	function MoveShowLoginDiv(){			
			if( y < -5){setTimeout("ShowLoginDiv()",10);}else{ dy = -5; y = -1 }
			document.getElementById("userInfo").style.top = y;
	}
	function ShowFrameDiv(queryString){
			return false;		//暂时屏蔽
		top.middle.switches.loading.style.display = 'block';
	//	top.middle.switches.note_iframe.location.href="../home/empmemo/empMemoExpressCreate.jsp";
		setTimeout(ShowFrameMain(queryString),10);
	}
	function ShowFrameMain(passValue){
			
			var url = "../home/doConsoleListAction.do";	//../home/empMemoCreateAction.do
			var topFrame = top.middle.switches;

			document.getElementById('PositionFrame').style.display = "block";
			top.middle.contents.left_frame.style.border = "none";
			top.middle.contents.left_frame.style.overflow = "hidden";
			
			topFrame.PositionFrame_main.style.display ="block";
			topFrame.PositionFrame.style.display ="block";
			topFrame.PositionFrame_notebook.style.display = 'block';
			topFrame.PositionFrame_notebook2.style.display = 'none';	
			topFrame.PositionFrame_my_note.style.display = 'block';
			//topFrame.note_iframe.location.href=url;
			form1.action = url;
			form1.method = "post";
			form1.target = "note_iframe";
//			alert(passValue);
			if(passValue){
				form1.innerHTML='<input type="hidden"  name="passTitle" value="' + passValue[0] + '"/>';
				form1.innerHTML=form1.innerHTML + '<input type="hidden"  name="passContent" value="' +passValue[1]+ '"/>';
			}
			form1.submit();
			
			top.middle.contents.PositionFrame.style.display ="block";
			topFrame.loading.style.display = 'none';
			topFrame.PositionFrame_main.style.visibility = 'visible';
			topFrame.visibility.style.display = 'block';
	}
	
   
	function offset(place){
	
		var mask = $('#mask');
		var targetObj = $('#menuContent');
		
		var maxOffset = targetObj.width()-mask.width();
		
		var currLeft = targetObj.css('left');
//		alert(maxOffset + " " + currLeft);
		var currLeft = Number(currLeft.substring(0,currLeft.length-2));
		if(place=="right" && (0-currLeft) <= maxOffset){
			targetObj.css('left', currLeft - 5);
		} else if(place=="left" && currLeft < 0){
				targetObj.css('left', currLeft + 5);
		}
		
	}
	function periodOffset(thisObj, place){
		var intervalId = window.setInterval(function(){offset(place)}, 1);
		$(thisObj).mouseout(function(){window.clearInterval(intervalId)});
	}
	
		function checkDirectionKey(){
			var mask = $('#mask');
			
			var bodyWidth = $('body').width();
			
			//alert(bodyWidth - 450);
			mask.width(bodyWidth - 250);	//450
			//alert(mask.width());
			var targetObj = $('#menuContent');
			
			var maxOffset = targetObj.width()-mask.width();
			
			var currLeft = targetObj.css('left');
//			alert(maxOffset + " " + currLeft);
			var currLeft = Number(currLeft.substring(0,currLeft.length-2));
//			alert((0-currLeft) <= maxOffset);
			if(!(0-currLeft) <= maxOffset || currLeft < 0) {
				$("#rightKey").show("slow");
				$("#leftKey").show("slow");
				if(!isShow){
					$("#prompt_div").show("slow", function(){window.setTimeout(function(){$("#prompt_div").hide("slow")}, 10000);isShow = true;});
				}
			} else {
				$("#rightKey").hide();
				$("#leftKey").hide();
				$("#prompt_div").hide();
			}
		}
		var isShow = false;
		$(function(){
			window.onresize = checkDirectionKey;
			$("#rightKey").hide();
			$("#leftKey").hide();
			$("#prompt_div").hide();
			checkDirectionKey();
		});
		
		function logout(){
			return formSubmit("${ctx}/home.action", "_top");
		}
</script>
	
</head>

<body onSelectStart="return true"><!-- 文档主题部分开始 -->

<div class="PositionFrame_black" id="PositionFrame"></div>
	<div id="userInfo" style="z-index:999;" onclick="HideLoginDiv()" title="点击关闭">
		<img src="${ctx}/skin/default/images/title/avataronline.gif" border="0" style="margin-top:-1px;"/>
		您好：<strong>${_CURRENT_USER.realName}</strong>&nbsp;&nbsp;|
		您所属单位：<strong title="${_CURRENT_USER.dept.deptNameFull}">${_CURRENT_USER.dept.deptName}</strong>&nbsp;&nbsp;
		<img src="${ctx}/skin/default/images/title/close.gif" border="0" />
	</div>

	<a id="memos"  style="cursor:pointer;" href="${ctx}/fmain.action" target="_top" title="点击切换到系统首页"><div id="memo" class="memo" title="点击切换到系统首页"></div></a>
	<a id="logins" style="cursor:pointer;" onclick="doLoginDiv();" title="点击显示您的登录信息"><div id="small_login" class="small_login" title="点击显示您的登录信息"></div></a>
	<a id="logout" style="cursor:pointer;" onclick="logout();" target="_top" title="点击退出系统"><div id="small_login_out" class="small_login_out" title="点击退出系统"></div></a>

<div class="headerBg">
	<div class="top_logo">
	    <div class="navMenu"  style="float:left;text-align:left;">
	    		<div class="titleDate" style="float:left;"><fmt:formatDate value="${now}" pattern="yyyy年M月d日 E" /> </div>
	    		<div style="height:29px;">
		    		<span id="leftKey"  onmouseover="periodOffset(this, 'left')"><img src="${ctx}/skin/default/images/title/left_arrow.png"/></span>
			    	<div class="mavMeau_top"></div>
			    	<div id="mask">
			    		<div id="menuContent"><span id="topmenu"
onclick="top.location.href='fmain.action';linkHighlightMenu(this);">系统首页</span><span id="tm_separator"></span><span id="topmenu" 
onclick="top.leftFrame.location.href='cargoLeft.action';top.main.location.href='cargoMain.action';linkHighlightMenu(this);">货运管理</span><span id="tm_separator"></span><span id="topmenu"
onclick="top.leftFrame.location.href='baseinfoLeft.action';top.main.location.href='baseinfoMain.action';linkHighlightMenu(this);">基础信息</span><span id="tm_separator"></span><span id="topmenu" 
onclick="top.leftFrame.location.href='sysadminLeft.action';top.main.location.href='sysadminMain.action';linkHighlightMenu(this);">系统管理</span></div>
					</div>
					<span id="rightKey" onmouseover="periodOffset(this, 'right')"><img src="${ctx}/skin/default/images/title/right_arrow.png"/></span>
				</div>
		</div>
	</div>
</div>

<div id="prompt_div"><img src="${ctx}/skin/default/images/title/prompt.png"/><span style="position:absolute;top:2px;left:35px;z-index: 99999;width:100%;color:#FFFFFF;text-align: left; ">鼠标指向箭头位置<br/>可显示更多菜单项</span></div>

<form name="form1" style="display: none;"></form>	<%//备忘录等使用%>
</body>
</html>