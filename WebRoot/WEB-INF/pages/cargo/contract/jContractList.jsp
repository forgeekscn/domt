<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
</head>

<body>
<form name="icform" method="post">

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
<li id="view"><a href="#" onclick="formSubmit('toview.action','_self');this.blur();">查看</a></li>
<li id="new"><a href="#" onclick="formSubmit('tocreate.action','_self');this.blur();">新增</a></li>
<li id="update"><a href="#" onclick="formSubmit('toupdate.action','_self');this.blur();">修改</a></li>
<li id="delete"><a href="#" onclick="formSubmit('delete.action','_self');this.blur();">删除</a></li>
<li id="new"><a href="#" onclick="formSubmit('submit.action','_self');this.blur();">上报</a></li>
<li id="new"><a href="#" onclick="formSubmit('cancel.action','_self');this.blur();">取消</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
<div class="textbox" id="centerTextbox">
  <div class="textbox-header">
  <div class="textbox-inner-header">
  <div class="textbox-title">
    购销合同列表
  </div> 
  </div>
  </div>
  
<div>

<div class="eXtremeTable" >
<table id="ec_table" class="tableRegion" width="98%" >
	<thead>
	<tr>
		<td class="tableHeader"><input type="checkbox" name="selid" onclick="checkAll('id',this)"></td>
		<td class="tableHeader">序号</td>
		
		<td class="tableHeader">总金额</td>
		<td class="tableHeader">货物数</td>
		<td class="tableHeader">附件数</td>
		
		<td class="tableHeader">客户名称</td>
		<td class="tableHeader">合同号</td>
		<td class="tableHeader">制单人</td>
		<td class="tableHeader">审单人</td>
		<td class="tableHeader">验货员</td>
		<td class="tableHeader">签单日期</td>
		<td class="tableHeader">交货期限</td>
		<td class="tableHeader">船期</td>
		<td class="tableHeader">状态</td>
		<td class="tableHeader">操作</td>
	</tr>
	</thead>
	<tbody class="tableBody" >
	
	<c:forEach items="${dataList}" var="o" varStatus="status">
	<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
		<td><input type="checkbox" name="id" value="${o.id}"/></td>
		<td>${status.index+1}</td>
		
		<td>${o.totalAmount}</td>
		<td>${o.cpNum}</td>
		<td>${o.extNum}</td>
		
		<td>${o.customName}</td>
		<td><a href="toview.action?id=${o.id}">${o.contractNo}</a></td>
		<td>${o.inputBy}</td>
		<td>${o.checkBy}</td>
		<td>${o.inspector}</td>
		<td><fmt:formatDate value="${o.signingDate}" pattern="yyyy-MM-dd"/></td>
		<td><fmt:formatDate value="${o.deliveryPeriod}" pattern="yyyy-MM-dd"/></td>
		<td><fmt:formatDate value="${o.shipTime}" pattern="yyyy-MM-dd"/></td>
		<td>
			<c:if test="${o.state==1}"><font color="green">已上报</font></c:if>
			<c:if test="${o.state==0}">草稿</c:if>
		</td>
		<td><a href="${ctx}/cargo/contractproduct/tocreate.action?contractId=${o.id}" title="新增货物信息">[货物]</a></td>
	</tr>
	</c:forEach>
	
	</tbody>
</table>
</div>
 
</div>
 
 
</form>
</body>
</html>

