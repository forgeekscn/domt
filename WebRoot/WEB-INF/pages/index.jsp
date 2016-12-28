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
    <title>纺大宿舍管理平台</title>  
    <link rel="stylesheet" href="css/pintuer.css">
    <link rel="stylesheet" href="css/admin.css">
    <script src="js/jquery.js"></script>   
</head>
<body style="background-color:#f2f9fd;">
<div class="header bg-main">
  <div class="logo margin-big-left fadein-top">
    <h1><img src="images/y.jpg" class="radius-circle rotate-hover" height="50" alt="" />纺大宿舍管理平台</h1>
  </div>
  <div class="head-l"><a class="button button-little bg-green" href="" target="_blank"><span class="icon-home"></span> 前台首页</a> &nbsp;&nbsp;<a href="##" class="button button-little bg-blue"><span class="icon-wrench"></span> 清除缓存</a> &nbsp;&nbsp;<a class="button button-little bg-red" href="login.html"><span class="icon-power-off"></span> 退出登录</a> </div>
</div>
<div class="leftnav">

  <div class="leftnav-title"><strong><span class="icon-list"></span></strong></div>
  <h2><span class="icon-user"></span>基本管理</h2>
  <ul style="display:block">
    <li><a href="${ctx}/anno/list.action" target="right"><span class="icon-caret-right"></span>公告管理</a></li>
    <li><a href="${ctx}/anno/list.action" target="right"><span class="icon-caret-right"></span>宿舍管理</a></li>
    <li><a href="${ctx}/student/get.action" target="right"><span class="icon-caret-right"></span>学生管理</a></li>  
    <li><a href="${ctx}/anno/list.action" target="right"><span class="icon-caret-right"></span>出入管理</a></li>   
    <li><a href="${ctx}/anno/list.action" target="right"><span class="icon-caret-right"></span>统计查询</a></li>
    
  </ul>   
  <h2><span class="icon-pencil-square-o"></span>系统管理</h2>
  <ul>
    <li><a href="add.html" target="right"><span class="icon-caret-right"></span>角色管理</a></li>
    <li><a href="list.html" target="right"><span class="icon-caret-right"></span>权限管理</a></li>
    <li><a href="cate.html" target="right"><span class="icon-caret-right"></span>数据管理</a></li>        
  </ul>  
</div>
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
  <li><b>当前语言：</b><span style="color:red;">中文</php></span>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;切换语言：<a href="##">中文</a> &nbsp;&nbsp;<a href="##">英文</a> </li>
</ul>
<div class="admin">

  <iframe scrolling="auto" rameborder="0" src="default.action" name="right" width="100%" height="100%">
  
  </iframe>
</div>
<div style="text-align:center;">
</div>
</body>
</html>