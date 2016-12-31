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
  <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>修改学生信息</strong></div>
  <div class="body-content">
    <form method="post" class="form-x" action="${ctx}/stu/update.action">  
      
      
      
       <div class="form-group">
        <div class="label">
          <label>姓名：</label>
        </div>
           <div class="field">
          <input type="text"  class="input" style="float:left; width:250px;" name="studentName" value="${obj.studentName}"/>
        </div>
      </div>
      <div class="form-group">
        <div class="label" >
          <label>学号：</label>
        </div>
           <div class="field">
          <input type="text"  class="input" style="width:250px;" name="studentNo" value="${obj.studentNo}"/>
        </div>
      </div>
				<input name="studentId" type="hidden" value="${obj.studentId}"/>
				<input id="sex" name="sex" type="hidden" value="${obj.sex}"/>
				<input id="className" name="className" type="hidden"value="${obj.className}"/>
				<input id="bedroomName" name="bedroomName" type="hidden" value="${obj.bedroomName}"/>
				<input id="collegeName" name="collegeName" type="hidden" value="${obj.collegeName}"/>
    
		<div>
		
				<!--根据学院取所属班级 http://localhost:8080/domt/cla/getdata.action?collegeId=2f11518b -->
				<select id="college" class="input" name="collegeId"
					style="margin-left:60px;width:220px;float:left; line-height:17px;" onchange="ejld1(this.value)">
						 <c:forEach var="s" items="${sList1}">
                             <option onclick="ejld1(this.value)" value="${s.collegeId}"     <c:if test="${obj.collegeId == s.collegeId}"> selected</c:if>     >${s.collegeName}</option>
                         </c:forEach>
				</select>
				<select id="class" class="input" name="classId" onchange="setvalue1()"
					style="margin-left:30px;width:200px; float:left; line-height:17px;">
                             <option value="${obj.classId}" >${obj.className}</option>
				</select>
			  <select  class="input" name="grade"  
							style="margin-left:30px; float:left; width:200px; line-height:17px;">
								<option onclick="selectdate(this.value)" value="大一">大一</option>
								<option value="大二" onclick="selectdate(this.value)">大二</option>
								<option value="大三" onclick="selectdate(this.value)">大三</option>
								<option value="大四" onclick="selectdate(this.value)">大四</option>
								<option value="研一" onclick="selectdate(this.value)">研一</option>
								<option value="研二" onclick="selectdate(this.value)">研二</option>
								<option value="研三" onclick="selectdate(this.value)">研三</option>
						</select>
				<script type="text/javascript">
					$("select[name='grade']").val("${obj.grade}");
					function setvalue1(){
		             	$("#className").val($("#class option:selected").text());
					}
					function setvalue2(){
		             	$("#bedroomName").val($("#bedroom option:selected").text());
					}
					function ejld1(apmId){
		             	$("#collegeName").val($("#college option:selected").text());
						$.ajax({
							 type: "GET",
				             url: "${ctx}/cla/getdata.action",
				             data: {collegeId: apmId },
				             dataType: "json",
				             success:function(data){
				             	alert(data);
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
     
     	<div>
				<br/>            
				<br/>            
				<br/>            
				<br/>            
				<br/>
				
				
				
<!-- 			三级联动 根据性别选择合适公寓  再选择合适宿舍 	-->

				
				<script type="text/javascript">
				
					function ejld2(sex){
		             	$("#sex").val($("#sex1 option:selected").text());
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
				<select id="bedroom" class="input" name="bedroomId" onchange="setvalue2()"
					style="margin-left:30px;width:250px; line-height:17px;float:left;" >
						
						<c:forEach var="s" items="${sList2}">
                             <option value="${s.bedroomId}"     <c:if test="${obj.bedroomId == s.bedroomId}"> selected</c:if>     >${s.bedroomName}</option>
                         </c:forEach>

				</select>      
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