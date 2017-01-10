<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="basic/base.jsp"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title></title>  
    <link rel="stylesheet" href="css/pintuer.css">
    <link rel="stylesheet" href="css/admin.css">
    <script src="js/jquery.js"></script>   
</head>
<body style="background-color:#f2f9fd;">
<div class="header bg-main">
  <div class="logo margin-big-left fadein-top">
    <h1><img src="images/y.jpg" class="radius-circle rotate-hover" height="50" alt="" />
    <c:if test="${sessionScope.name!=null and  sessionScope.type!=null}">
	    ${sessionScope.name}
    </c:if>
    <c:if test="${sessionScope.name==null or sessionScope.type==null}">
	    	请先登录
    </c:if>
      </h1>
  </div>
  <div class="head-l"><a class="button button-little bg-green" href="#" target="_blank">
	  <span class="icon-home"></span> 前台首页</a> &nbsp;&nbsp;
	  <a href="#" class="button button-little bg-blue">
	  <span class="icon-wrench"></span> 清除缓存</a> &nbsp;&nbsp;
	  <a class="button button-little bg-red" href="${ctx}/logout.action">
	  <span class="icon-power-off"></span> 退出登录</a> 
	  </div>
</div>
<div class="leftnav">
		
     <c:if test="${sessionScope.type=='root'}">
     	<div>
			  <div class="leftnav-title"><strong><span class="icon-list">&nbsp&nbsp操作菜单</span></strong></div>
			  
			  <h2><span class="icon-user"></span>基本管理</h2>
			<!--   <ul style="display:block"> -->
			  <ul>
			    <li><a href="${ctx}/anno/list.action" target="right"><span class="icon-caret-right"></span>公告管理</a></li>
			    <li><a href="${ctx}/apm/list.action" target="right"><span class="icon-caret-right"></span>公寓管理</a></li>
			    <li><a href="${ctx}/br/list.action" target="right"><span class="icon-caret-right"></span>宿舍管理</a></li>
			    <li><a href="${ctx}/stu/list.action" target="right"><span class="icon-caret-right"></span>学生管理</a></li>  
			    <li><a href="${ctx}/cl/list.action" target="right"><span class="icon-caret-right"></span>学院管理</a></li>  
			    <li><a href="${ctx}/cla/list.action" target="right"><span class="icon-caret-right"></span>班级管理</a></li>  
			    <li><a href="${ctx}/visit/list.action" target="right"><span class="icon-caret-right"></span>访客登记管理</a></li>
			    
			  </ul>
			  
			  <h2><span class="icon-pencil-square-o"></span>分配宿舍</h2>
			  <ul>
			    <li><a href="${ctx}/statis/todisbycla.action" target="right"><span class="icon-caret-right"></span>按班级分配</a></li>
			    <li><a href="${ctx}/statis/todisbycollege.action" target="right"><span class="icon-caret-right"></span>按学院分配</a></li>
			  </ul>
			  
			  <h2><span class="icon-pencil-square-o"></span>腾空宿舍</h2>
			  <ul>
			    <li><a href="${ctx}/statis/toempbyclass.action" target="right"><span class="icon-caret-right"></span>按班级腾空</a></li>
			    <li><a href="${ctx}/statis/toempbycollege.action" target="right"><span class="icon-caret-right"></span>按学院腾空</a></li>
			  </ul>
			       
			  <h2><span class="icon-pencil-square-o"></span>系统管理</h2>
			  
			  <ul>
			    <li><a href="${ctx}/man/list.action" target="right"><span class="icon-caret-right"></span>查看管理员</a></li>
			    <li><a href="add.html" target="right"><span class="icon-caret-right"></span>角色管理</a></li>
			    <li><a href="list.html" target="right"><span class="icon-caret-right"></span>权限管理</a></li>
			    <li><a href="cate.html" target="right"><span class="icon-caret-right"></span>数据管理</a></li>        
			  </ul>  
			       
			  <h2><span class="icon-pencil-square-o"></span>统计打印</h2>
			  <ul>
			    <li><a href="${ctx}/statis/tostatisstubycla.action" target="right"><span class="icon-caret-right"></span>统计班级学生名单</a></li>
			    <li><a href="${ctx}/statis/tostatisstubycollege.action" target="right"><span class="icon-caret-right"></span>统计学院学生名单</a></li>
			    <li><a href="${ctx}/statis/toviewdisbycla.action" target="right"><span class="icon-caret-right"></span>统计班级宿舍分配</a></li>
			    <li><a href="${ctx}/statis/toviewdisbycollege.action" target="right"><span class="icon-caret-right"></span>统计学院宿舍分配</a></li>
			    <li><a href="${ctx}/toprintstu.action" target="right"><span class="icon-caret-right"></span>打印所有学生信息</a></li>
			    <li><a href="${ctx}/toprintbr.action" target="right"><span class="icon-caret-right"></span>打印所有宿舍信息</a></li>
			  </ul>  
			</div>
			</div>
</c:if>



     <c:if test="${sessionScope.type=='manager'}">
     	<div>
			  <div class="leftnav-title"><strong><span class="icon-list">&nbsp&nbsp操作菜单</span></strong></div>
			  <h2><span class="icon-user"></span>基本管理</h2>
			<!--   <ul style="display:block"> -->
			  <ul>
			    <li><a href="${ctx}/anno/list.action" target="right"><span class="icon-caret-right"></span>公告管理</a></li>
			    <li><a href="${ctx}/apm/list.action" target="right"><span class="icon-caret-right"></span>公寓管理</a></li>
			    <li><a href="${ctx}/br/list.action" target="right"><span class="icon-caret-right"></span>宿舍管理</a></li>
			    <li><a href="${ctx}/stu/list.action" target="right"><span class="icon-caret-right"></span>学生管理</a></li>  
			    <li><a href="${ctx}/cl/list.action" target="right"><span class="icon-caret-right"></span>学院管理</a></li>  
			    <li><a href="${ctx}/cla/list.action" target="right"><span class="icon-caret-right"></span>班级管理</a></li>  
			    <li><a href="${ctx}/visit/list.action" target="right"><span class="icon-caret-right"></span>访客登记管理</a></li>
			    
			  </ul>
			  
			  <h2><span class="icon-pencil-square-o"></span>分配宿舍</h2>
			  <ul>
			    <li><a href="${ctx}/statis/todisbycla.action" target="right"><span class="icon-caret-right"></span>按班级分配</a></li>
			    <li><a href="${ctx}/statis/todisbycollege.action" target="right"><span class="icon-caret-right"></span>按学院分配</a></li>
			  </ul>
			  
			  <h2><span class="icon-pencil-square-o"></span>腾空宿舍</h2>
			  <ul>
			    <li><a href="${ctx}/statis/toempbyclass.action" target="right"><span class="icon-caret-right"></span>按班级腾空</a></li>
			    <li><a href="${ctx}/statis/toempbycollege.action" target="right"><span class="icon-caret-right"></span>按学院腾空</a></li>
			  </ul>
			       
			  <h2><span class="icon-pencil-square-o"></span>统计打印</h2>
			  <ul>
			    <li><a href="${ctx}/statis/tostatisstubycla.action" target="right"><span class="icon-caret-right"></span>统计班级学生名单</a></li>
			    <li><a href="${ctx}/statis/tostatisstubycollege.action" target="right"><span class="icon-caret-right"></span>统计学院学生名单</a></li>
			    <li><a href="${ctx}/statis/toviewdisbycla.action" target="right"><span class="icon-caret-right"></span>统计班级宿舍分配</a></li>
			    <li><a href="${ctx}/statis/toviewdisbycollege.action" target="right"><span class="icon-caret-right"></span>统计学院宿舍分配</a></li>
			    <li><a href="${ctx}/toprintstu.action" target="right"><span class="icon-caret-right"></span>打印所有学生信息</a></li>
			    <li><a href="${ctx}/toprintbr.action" target="right"><span class="icon-caret-right"></span>打印所有宿舍信息</a></li>
			  </ul>  
			</div>
			</div>
</c:if>




     <c:if test="${sessionScope.type=='student'}">
     	<div>
			  <div class="leftnav-title"><strong><span class="icon-list">&nbsp&nbsp操作菜单</span></strong></div>
			  <h2><span class="icon-user"></span>基本信息</h2>
			<!--   <ul style="display:block"> -->
			  <ul>
			    <li><a href="${ctx}/stu/toupdate.action?stuId=${sessionScope.user.studentId}" target="right">
			    		<span class="icon-caret-right"></span>个人信息</a></li>
			    <li><a href="${ctx}/anno/list.action" target="right"><span class="icon-caret-right"></span>公告查看</a></li>
			    <li><a href="${ctx}/stu/findbybedroomid.action?bedroomId=${sessionScope.user.bedroomId}" target="right">
			    		<span class="icon-caret-right"></span>我的室友</a></li>
			    <li><a href="${ctx}/stu/findbyclassid.action?classId=${sessionScope.user.classId}" target="right"><span class="icon-caret-right"></span>我的同班同学</a></li>  
			    <li><a href="${ctx}/visit/list.action?arg2=${sessionScope.user.bedroomName}" target="right"><span class="icon-caret-right"></span>我的宿舍访客记录</a></li>
			    
			  </ul>
			</div>
			</div>
</c:if>






























<script type="text/javascript">
$(function(){
  $(".leftnav h2").click(function(){
	  $(this).next().slideToggle(200);	
	  $(this).toggleClass("on"); 
  })
  $(".leftnav ul li a").click(function(){
	    $("#a_leader_txt").text($(this).text());
  		$(".leftnav ul li a").removeClass("on");
		$(this).addClass("on");
  })
});
</script>
<ul class="bread">
  <li><a href="default.action" target="right" class="icon-home"> 首页</a></li>
  <li><a href="##" id="a_leader_txt">网站信息</a></li>
</ul>
<div class="admin">

  <iframe scrolling="auto" rameborder="0" src="default.action" name="right" width="100%" height="100%">
  
  </iframe>
</div>
<div style="text-align:center;">
</div>
</body>
</html>