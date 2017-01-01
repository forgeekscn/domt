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
  <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>来访登记</strong></div>
  <div class="body-content">
    <form method="post" class="form-x" action="${ctx}/visit/update.action">  
      
      <input type="hidden" name="visitorId" value="${obj.visitorId}"/>
       <div class="form-group">
        <div class="label">
          <label>访客姓名：</label>
        </div>
           <div class="field">
          <input type="text"  class="input"  style="width:100px;" name="visitorName" value="${obj.visitorName}"/>
        </div>
      </div>
      <div class="form-group">
        <div class="label" >
          <label>电话：</label>
        </div>
           <div class="field">
          <input type="text"  class="input" style="width:200px;" name="visitorCall" value="${obj.visitorCall}"/>
        </div>
      </div>
		<select id="sex1" class="input"  name="visitorSex"
			style="margin-left:100px;width:100px; line-height:17px;"  >
				<option value="">选择性别</option>
				<option value="男">男</option>
				<option value="女">女</option>
		</select>
    
    <script type="text/javascript">
    	$("#sex1").val("${obj.visitorSex}");
    </script>
		<br/>

  <div class="form-group">
        <div class="label" >
          <label>来访宿舍：</label>
        </div>
           <div class="field">
          <input type="text"  class="input" style="width:250px;" name="bedroomName" value="${obj.bedroomName}"/>
        </div>
      </div>
      
      <div class="form-group">
        <div class="label">
          <label>备注：</label>
        </div>
        <div class="field">
          <textarea name="reason" class="input" style="height:100px; border:1px solid #ddd;">${obj.reason}</textarea>
          <div class="tips"></div>
        </div>
      </div>
			</div>
     
     	<div>

		<div>
     </div>            
     
      <div class="form-group">
        <div class="label">
          <label></label>
        </div>
        <div class="field">
          <button style="margin-left:120px;margin-top:30px;width:200px;" class="button bg-main icon-check-square-o" type="submit"> 提交</button>
        </div>
      </div>
    </form>
  </div>
</div>

</body></html>