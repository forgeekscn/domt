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
  <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>按个人快速分配宿舍</strong></div>
  <div class="body-content">
    <form method="post" class="form-x" action="${ctx}/statis/disbystu.action">  
        <div class="label" >
          <label>选择待分配宿舍的班级：</label>
        </div>
		<div>
		
				<!--根据学院取所属班级 http://localhost:8080/domt/cla/getdata.action?collegeId=2f11518b -->
				<select id="college" class="input" 
					style="margin-left:60px;width:220px;float:left; line-height:17px;" onchange="ejld1(this.value)">
						<option value="">选择学院</option>
						 <c:forEach var="s" items="${collegeList}">
                             <option value="${s.collegeId}">${s.collegeName}</option>
                         </c:forEach>
				</select>
				<select id="class" class="input" name="classId" onchange="setvalue1(this.value)"
					style="margin-left:30px;width:200px; float:left; line-height:17px;">
						<option value="">选择班级</option>
				</select>
				<select id="stu" class="input" name="stuId" 
					style="margin-left:30px;width:200px; float:left; line-height:17px;">
						<option value="">选择学生</option>
				</select>
				<br/>
				<br/>
				<br/>
				 

				<script type="text/javascript">
					function setvalue1(cla){
					$.ajax({
							 type: "GET",
				             url: "${ctx}/stu/getdata.action",
				             data: {cla:cla},
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
					
				</script>      
		</div>

     
				<br/>            
				<br/>
				
				
<!-- 			三级联动 根据性别选择合适公寓  再选择合适宿舍 	-->
		<div class="label" >
          <label>选择给该班男生分配宿舍的公寓：</label>
        </div>		
		<div>
				<select id="sex1" class="input"  name="sex1"
					style="margin-left:60px;width:100px;float:left; line-height:17px;" onchange="ejld2(this.value)">
						<option value="">选择性别</option>
						<option value="b">男</option>
						<option value="g">女</option>
				</select>
     </div>            
				<select id="apartment" class="input" name="apartmentId"   onchange="ejld3(this.value)"
					style="margin-left:60px;width:250px; float:left; line-height:17px;">
						<option value="">选择公寓</option>
				</select>
				
				<select id="bedroom" class="input" name="bedroomId"
					style="margin-left:60px;width:250px; float:left; line-height:17px;">
						<option value="">选择宿舍</option>
				</select>
				<br/>
				<br/>
				<br/>
				<br/>

	 
				
				<script type="text/javascript">
				
					function ejld22(sex){
// 		             	$("#sex").val($("#sex1 option:selected").text());
						$.ajax({
							 type: "GET",
				             url: "${ctx}/apm/getdata.action",
				             data: {"sex":sex},
				             dataType: "json",
				             success:function(data){
				             
				             	$("#apartment2").empty();
				             	
			             		$("#apartment2").append('<option>选择公寓</option>');
				             	$.each(data,function(index,item){
				             		$("#apartment2").append('<option value="'+item["apartmentId"]+'">'+item["apartmentName"]+'</option>');
				             	});
				             },
				             error:function(){
				             	alert("eeror json");
				             }
						});
						}
				
					function ejld2(sex){
// 		             	$("#sex2").val($("#sex2 option:selected").text());
						$.ajax({
							 type: "GET",
				             url: "${ctx}/apm/getdata.action",
				             data: {"sex":sex},
				             dataType: "json",
				             success:function(data){
				             
				             	$("#apartment").empty();
				             	
			             		$("#apartment").append('<option>选择公寓</option>');
				             	$.each(data,function(index,item){
				             		$("#apartment").append('<option value="'+item["apartmentId"]+'">'+item["apartmentName"]+'</option>');
				             	});
				             },
				             error:function(){
				             	alert("eeror json");
				             }
						});
						}
				
					function ejld3(apmId){
						$.ajax({
							 type: "GET",
				             url: "${ctx}/br/getdata.action",
				             data: {"apmId":apmId},
				             dataType: "json",
				             success:function(data){
				             	
				             	
				             	$("#bedroom").empty();
			             		$("#bedroom").append('<option>选择宿舍</option>');
				             	$.each(data,function(index,item){
				             		$("#bedroom").append('<option value="'+item["bedroomId"]+'">'+item["bedroomName"]+'</option>');
				             	});
				             },
				             error:function(){
				             	alert("eeror json");
				             }
						});
						}
					
				</script>
				<br/>      
		</div>
     

     
      <div class="form-group">
        <div class="label">
          <label></label>
        </div>
        <div class="field">
          <button style="margin-left:100px;margin-top:30px;width:200px;" class="button bg-main icon-check-square-o" type="submit"> 提交</button>
        </div>
      </div>
    </form>
  </div>
</div>

</body></html>