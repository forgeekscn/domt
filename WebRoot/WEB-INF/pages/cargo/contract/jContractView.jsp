<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
</head>
<body>
<form method="post">

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
    <div id="navMenubar">
<ul>
<li id="back"><a href="list.action">返回</a></li>
</ul>
    </div>
</div>
</div>
</div>
     
<div class="textbox" id="centerTextbox">
    
    <div class="textbox-header">
    <div class="textbox-inner-header">
    <div class="textbox-title">
		浏览购销合同信息
    </div> 
    </div>
    </div>
<div>
    <div>
		<table class="commonTable" cellspacing="1">
	        <tr>
	            <td class="columnTitle">客户名称：</td>
	            <td class="tableContent">${obj.customName}</td>
	            <td class="columnTitle">收购方：</td>
	            <td class="tableContent">${obj.offeror}</td>
	        </tr>
	        <tr>
	            <td class="columnTitle">合同号：</td>
	            <td class="tableContent">${obj.contractNo}</td>
	            <td class="columnTitle">打印版式：</td>
	            <td class="tableContentAuto">
	            	<c:if test="${obj.printStyle=='2'}">两款</c:if>
	            	<c:if test="${obj.printStyle=='1'}">一款</c:if>
	            </td>
	        </tr>
	        <tr>
	            <td class="columnTitle">签单日期：</td>
	            <td class="tableContent"><fmt:formatDate value="${obj.signingDate}" pattern="yyyy-MM-dd"/></td>
	            <td class="columnTitle">重要程度：</td>
	            <td class="tableContentAuto">
	            	<c:if test="${obj.importNum==3}">★★★</c:if>
	            	<c:if test="${obj.importNum==2}">★★</c:if>
	            	<c:if test="${obj.importNum==1}">★</c:if>
	            </td>
	        </tr>
	        <tr>
	            <td class="columnTitle">交货期限：</td>
	            <td class="tableContent"><fmt:formatDate value="${obj.deliveryPeriod}" pattern="yyyy-MM-dd"/></td>
	            <td class="columnTitle">船期：</td>
	            <td class="tableContent"><fmt:formatDate value="${obj.shipTime}" pattern="yyyy-MM-dd"/></td>
	        </tr>
	        <tr>
	            <td class="columnTitle">贸易条款：</td>
	            <td class="tableContent">${obj.tradeTerms}</td>
	            <td class="columnTitle">验货员：</td>
	            <td class="tableContent">${obj.inspector}</td>
	        </tr>
	        <tr>
	            <td class="columnTitle">制单人：</td>
	            <td class="tableContent">${obj.inputBy}</td>
	            <td class="columnTitle">审单人：</td>
	            <td class="tableContent">${obj.checkBy}</td>
	        </tr>
	        <tr>
	            <td class="columnTitle">要求：</td>
	            <td class="tableContent"><pre>${obj.crequest}</pre></td>
	            <td class="columnTitle">说明：</td>
	            <td class="tableContent"><pre>${obj.remark}</pre></td>
	        </tr>
		</table>
	</div>
</div>
 
 
</form>
</body>
</html>

