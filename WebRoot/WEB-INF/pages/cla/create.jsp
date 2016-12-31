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
  <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>增加班级</strong></div>
  <div class="body-content">
    <form method="post" class="form-x" action="${ctx}/cla/create.action">  
      
	
			<div>
		
				<select id="yjlb" class="input" name="collegeId"
					style="margin-left:30px;width:350px; line-height:17px;" >
						<option value="">选择学院</option>
						 <c:forEach var="s" items="${sList}">
                             <option value="${s.collegeId}">${s.collegeName}</option>
                         </c:forEach>
				</select>
				<script type="text/javascript">
				
					function ejld(apmId){
						$.ajax({
							 type: "GET",
				             url: "${ctx}/apm/gettotalfloor.action",
				             data: {apartmentId: apmId },
				             dataType: "json",
				             success:function(data){
				             	var floor=data['floor'];
				             	if(floor<1 || floor==null) floor=1;
									$("#floor").empty();
								  for(var i=1;i<=floor;i++){  
										$("#floor").append('<option value="'+i+'">'+i+'楼'+'</option>');					             	
						        } 
				             },
				             error:function(json){
				             	alert(json);
				             }
						});
						}
					
				</script>
				<br/>      
				<select id="floor" class="input" name="grade"
					style="margin-left:30px;width:350px; line-height:17px;">
						<option value="">选择年级</option>
						<option value="d1">大一</option>
						<option value="d2">大二</option>
						<option value="d3">大三</option>
						<option value="d4">大四</option>
						<option value="y1">研一</option>
						<option value="y2">研二</option>
						<option value="y3">研三</option>
				</select>      
				<br/>      
		</div>
     
	
	
	

      <div class="form-group">
        <div class="label">
          <label>班级名称：</label>
        </div>
        <div class="field">
          <input type="text" class="input" name="className" value=""/>
        </div>
      </div>
     <div class="form-group">
        <div class="label">
          <label>辅导员：</label>
        </div>
        <div class="field">
          <input type="text" class="input" name="coach" value=""/>
        </div>
      </div>
      
     <div class="form-group">
        <div class="label">
          <label>辅导员电话：</label>
        </div>
        <div class="field">
          <input type="text" class="input" name="coachCall" value=""/>
        </div>
      </div>
            <div class="form-group">
        <div class="label">
          <label></label>
        </div>
        <div class="field">
          <button class="button bg-main icon-check-square-o" type="submit"> 提交</button>
        </div>
      </div>
    </form>
  </div>
</div>

</body></html>