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
  <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>更改内容</strong></div>
  <div class="body-content">
    <form method="post" class="form-x" action="${ctx}/cla/update.action">  
          <input type="hidden"  name="classId" value="${obj.classId}"/>
      
      <div class="form-group">
        <div class="label">
          <label>班级名称：</label>
        </div>
        <div class="field">
          <input type="text" class="input" name="className" value="${obj.className}"/>
        </div>
      </div>
      
		<div>
		
				<select id="yjlb" class="input" name="collegeId"
					style="margin-left:30px;width:350px; line-height:17px;">
						 <c:forEach var="s" items="${sList}">
                             <option value="${s.collegeId}"     
                             <c:if test="${obj.collegeId == s.collegeId}"> selected</c:if> >${s.collegeName}</option>
                         </c:forEach>
				</select>
				<br/>      
				<select id="floor" class="input" name="grade"
					style="margin-left:30px;width:350px; line-height:17px;">
						<option value="">选择年级</option>
						<option value="大一">大一</option>
						<option value="大二">大二</option>
						<option value="大三">大三</option>
						<option value="大四">大四</option>
						<option value="研一">研一</option>
						<option value="研二">研二</option>
						<option value="研三">研三</option>
				</select>      
				<br/>      
		</div>
				<script type="text/javascript">
					$("#floor").val("${obj.grade}");
				</script>
     
     
     
     
     <div class="form-group">
        <div class="label">
          <label>辅导员：</label>
        </div>
        <div class="field">
          <input type="text" class="input" name="coach" value="${obj.coach}"/>
        </div>
      </div>
      
     <div class="form-group">
        <div class="label">
          <label>辅导员电话：</label>
        </div>
        <div class="field">
          <input type="text" class="input" name="coachCall" value="${obj.coachCall}"/>
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