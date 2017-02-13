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
<div class="panel admin-panel">
  <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>增加管理员</strong></div>
  <div class="body-content">
    <form method="post" class="form-x" action="${ctx}/man/create.action">  
     
		<div>
		
				<!--根据学院取所属班级 http://localhost:8080/domt/cla/getdata.action?collegeId=2f11518b -->
				<select id="college" class="input" 
					style="margin-left:60px;width:220px;float:left; line-height:17px;" onchange="ejld1(this.value)">
						<option value="">选择学院</option>
						 <c:forEach var="s" items="${sList1}">
                             <option value="${s.collegeId}">${s.collegeName}</option>
                         </c:forEach>
				</select>
				<select id="class" class="input" name="classId" onchange="ejld2(this.value)"
					style="margin-left:30px;width:200px; float:left; line-height:17px;">
						<option value="">选择班级</option>
				</select>
				 
	<select id="stu" name="stuId" class="input" onchange="ejld22()"
					style="margin-left:30px;width:200px; float:left; line-height:17px;">
						<option value="">选择学生</option>
				</select>
				<br/>
				<br/>
				
				<script type="text/javascript">
					
				 
					function ejld1(apmId){
						$.ajax({
							 type: "GET",
				             url: "${ctx}/cla/getdata.action",
				             data: {collegeId: apmId },
				             dataType: "json",
				             success:function(data){
				             	$("#class").empty();
			             		$("#class").append('<option>选择班级</option>');
				             	$.each(data,function(index,item){
				             		$("#class").append('<option value="'+item["classId"]+'">'+item["className"]+'</option>');
				             	});
				             },
				             error:function(){
				             	alert("eeror json");
				             }
						});
						}
					function ejld2(cla){
// 						var cla=$("#class").val();
						$.ajax({
							 type: "GET",
				             url: "${ctx}/stu/getdata.action",
				             data: {"cla":cla},
				             dataType: "json",
				             success:function(data){
				             
				             	$("#stu").empty();
				             	
			             		$("#stu").append('<option>选择学生</option>');
				             	$.each(data,function(index,item){
				             		$("#stu").append('<option value="'+item["studentId"]+'">'+item["studentName"]+'</option>');
				             	});
				             },
				             error:function(){
				             	alert("eeror json");
				             }
						});
						}
						
				 
						
						
				</script>      
		</div>

     
				<br/>            
				<br/>
				
    
	 





     
      <div class="form-group">
        <div class="label">
          <label></label>
        </div>
        <div class="field">
          <button style="margin-left:100px;margin-top:30px;width:200px;" class="button bg-main icon-check-square-o" type="submit">为给学生分配 普通管理员权限</button>
        </div>
      </div>
    </form>
    
    
  </div>
</div>

</body></html>