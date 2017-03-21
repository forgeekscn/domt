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
  <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>更改管理员</strong></div>
  <div class="body-content">
    <form method="post" class="form-x" action="${ctx}/man/update.action">  
      <input type="hidden" name="managerId" value="${obj.managerId}"/>
       <div class="form-group">
        <div class="label">
          <label>姓名：</label>
        </div>
           <div class="field">
          <input type="text"  class="input" style="float:left; width:250px;" name="managerName" value="${obj.managerName}"/>
        </div>
      </div>
      <div class="form-group">
        <div class="label" >
          <label>联系方式：</label>
        </div>
           <div class="field">
          <input type="text"  class="input" style="width:250px;" name="managerCall" value="${obj.managerCall}"/>
        </div>
      </div>
    
		<div>
		</div>
     
     	<div>
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