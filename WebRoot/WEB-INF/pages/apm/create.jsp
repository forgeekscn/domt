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
  <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>增加公寓</strong></div>
  <div class="body-content">
    <form method="post" class="form-x" action="${ctx}/apm/create.action">  
      
      <div class="form-group">
        <div class="label">
          <label>公寓名称：</label>
        </div>
        <div class="field">
          <input type="text" class="input" name="apartmentName" value=""/>
        </div>
      </div>
     <div class="form-group">
        <div class="label">
          <label>公寓类型：</label>
        </div>
        <div class="field">
          <input type="text" class="input" name="sex" value=""/>
        </div>
      </div>
     <div class="form-group">
        <div class="label">
          <label>入住人数：</label>
        </div>
        <div class="field">
          <input type="text" class="input" name="totalPeople" value=""/>
        </div>
      </div>
     <div class="form-group">
        <div class="label">
          <label>最高楼层：</label>
        </div>
        <div class="field">
          <input type="text" class="input" name="totalFloor" value=""/>
        </div>
      </div>
     <div class="form-group">
        <div class="label">
          <label>管理员：</label>
        </div>
        <div class="field">
          <input type="text" class="input" name="managerId" value=""/>
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