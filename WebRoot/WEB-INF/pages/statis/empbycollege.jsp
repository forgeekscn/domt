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
  <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>按学院快速腾空宿舍</strong></div>
  <div class="body-content">
    <form method="post" class="form-x" action="${ctx}/statis/empbycollege.action">  
        <div class="label" >
          <label>选择待腾空宿舍的学院：</label>
        </div>
		<div>
		
				<!--根据学院取所属班级 http://localhost:8080/domt/cla/getdata.action?collegeId=2f11518b -->
				<select id="college" class="input" name="classId"
					style="margin-left:60px;width:220px;float:left; line-height:17px;">
						<option value="">选择学院</option>
						 <c:forEach var="s" items="${collegeList}">
                             <option value="${s.collegeId}">${s.collegeName}</option>
                         </c:forEach>
				</select>
				<br/>
				<br/>
				<br/>

				<script type="text/javascript">
					function setvalue1(){
// 		             	$("#className").val($("#class option:selected").text());
					}
					function setvalue2(){
// 		             	$("#bedroomName").val($("#bedroom option:selected").text());
					}
					function ejld1(apmId){
// 		             	$("#collegeName").val($("#college option:selected").text());
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