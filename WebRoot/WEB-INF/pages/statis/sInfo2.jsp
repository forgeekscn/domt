<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../basic/base.jsp"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
<title></title>
</head>
<body>
	<div style="float:right;">
		<a href="#" class="button border-main icon-search" style="margin-right:60px;"	onclick="Print()">导出Excel</a><br/>
	</div>
	
	<script type="text/javascript">
		function Print(){
				window.location.href="${ctx}/"+"${code}"+".action";
		}
	</script>
	
	<div>
		<br/><label id="info" style="font-size:16px;color:#00c;">${info}</label><br/>
	</div>
	<hr/>
	<hr/>
</body></html>