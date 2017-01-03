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
			if("${infoList.size()}"<50){
				window.location.href="${ctx}/print.action?code=statisbycla&arg="+"${infoList[0].classId}";
			}else {
				window.location.href="${ctx}/print.action?code=statisbycol&arg="+"${infoList[0].collegeId}";
			}
		}
	</script>
	
	<div>
		<br/><label id="info" style="font-size:16px;color:#00c;">${info}</label><br/>
	</div>
	<div style="float:center;">
		<label id="tbname" style="font-size:16px;font-weight:bold;margin-left:395px;">学生信息名单</label>
	</div>
	<hr/>
	<hr/>
	<table>
		<tr style="color:#888;margin:30px,20px,30px,20px;">
			<th width="10%">姓名</th>
			<th width="10%">学号</th>
			<th width="10%">性别</th>
			<th width="10%">班级</th>			
			<th width="10%">年级</th>			
			<th width="10%">学院</th>
		</tr>
		<c:forEach items="${infoList}" var="stu">
			<tr style="color:#222;margin:30px,20px,30px,20px;">
				<th>${stu.studentName}</th>
				<th>${stu.studentNo}</th>
				<th>${stu.sex}</th>
				<th>${stu.className}</th>
				<th>${stu.grade}</th>
				<th>${stu.collegeName}</th>
			</tr>
		</c:forEach>
		
	</table>
	<hr/>
	<hr/>
</body></html>