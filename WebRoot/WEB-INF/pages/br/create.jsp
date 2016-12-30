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
  <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>增加可用宿舍</strong></div>
  <div class="body-content">
    <form method="post" class="form-x" action="${ctx}/br/create.action">  
      
      <div class="form-group">
        <div class="label">
        </div>
      </div>

		<div>
		
				<select id="yjlb" class="input" name="apartmentId"
					style="margin-left:30px;width:350px; line-height:17px;" onchange="ejld(this.value)">
						<option value="">选择公寓</option>
						 <c:forEach var="s" items="${sList}">
                             <option value="${s.apartmentId}">${s.apartmentName}</option>
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
				<select id="floor" class="input" name="floor"
					style="margin-left:30px;width:350px; line-height:17px;">
						<option value="">选择楼层</option>
				</select>      
				<br/>      
		</div>
     
     

      <div class="form-group">
        <div class="label">
          <label>公寓名称：</label>
        </div>
           <div class="field">
          <input type="text"  class="input" name="bedroomName" value=""/>
        </div>
      </div>
      <div class="form-group">
        <div class="label" >
          <label>已住人数：</label>
        </div>
           <div class="field">
          <input type="text"  class="input" name="totalBed" value=""/>
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