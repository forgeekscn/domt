<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
</head>
<body>
<form method="post">
	<input type="hidden" name="id" value="${obj.id}"/>

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
    <div id="navMenubar">
<ul>
<li id="save"><a href="#" onclick="formSubmit('update.action','_self');">确定</a></li>
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
		修改生产厂家信息
    </div> 
    </div>
    </div>
<div>
    <div>
		<table class="commonTable" cellspacing="1">
	        <tr>
	            <td class="columnTitle_mustbe">厂家名称：</td>
	            <td class="tableContent"><input type="text" name="fullName" value="${obj.fullName}"/></td>
	            <td class="columnTitle_mustbe">简称：</td>
	            <td class="tableContent"><input type="text" name="factoryName" value="${obj.factoryName}"/></td>
	        </tr>
	        <tr>
	            <td class="columnTitle_mustbe">联系人：</td>
	            <td class="tableContent"><input type="text" name="contacts" value="${obj.contacts}"/></td>
	            <td class="columnTitle_mustbe">电话：</td>
	            <td class="tableContent"><input type="text" name="phone" value="${obj.phone}"/></td>
	        </tr>
	        <tr>
	            <td class="columnTitle_mustbe">手机：</td>
	            <td class="tableContent"><input type="text" name="mobile" value="${obj.mobile}"/></td>
	            <td class="columnTitle_mustbe">传真：</td>
	            <td class="tableContent"><input type="text" name="fax" value="${obj.fax}"/></td>
	        </tr>
	        <tr>
	            <td class="columnTitle_mustbe">验货员：</td>
	            <td class="tableContent"><input type="text" name="inspector" value="${obj.inspector}"/></td>
	            <td class="columnTitle_mustbe">排序号：</td>
	            <td class="tableContent"><input type="text" name="orderNo" value="${obj.orderNo}"/></td>
	        </tr>
	        <tr>
	            <td class="columnTitle_mustbe">备注：</td>
	            <td class="tableContent"><textarea name="cnote" style="height:120px;">${obj.cnote}</textarea></td>
	        </tr>
		</table>
	</div>
</div>
 
 
</form>
</body>
</html>

