<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <script type="text/javascript">
    	//设置冗余的生产厂家名称
    	function setFactoryName(val){
    		var ele = document.getElementById("factoryName");
    		ele.value = val;
    	}
    </script>
</head>
<body>
<form method="post">
	<input type="text" name="contractProductId" value="${obj.contractProductId}"/>

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
    <div id="navMenubar">
<ul>
<li id="save"><a href="#" onclick="formSubmit('${ctx}/cargo/extcproduct/update.action','_self');">确定</a></li>
<li id="back"><a href="">返回</a></li>
</ul>
    </div>
</div>
</div>
</div>
     
<div class="textbox" id="centerTextbox">
    
    <div class="textbox-header">
    <div class="textbox-inner-header">
    <div class="textbox-title">
		修改附件信息
    </div> 
    </div>
    </div>
<div>
    <div>
		<table class="commonTable" cellspacing="1">
	        <tr>
	            <td class="columnTitle_mustbe">厂家名称：</td>
	            <td class="tableContent">
	            	<select name="factoryId" onchange="setFactoryName(this.options[this.selectedIndex].text);">
		            	<option value="">--请选择--</option>
	            		<c:forEach items="${factoryList}" var="f">
	            			<option value="${f.id}" >${f.factoryName}</option>
	            		</c:forEach>
	            	</select>
	            	<input type="hidden" id="factoryName" name="factoryName" value="${obj.factoryName}"/>
	            </td>
	            <td class="columnTitle_mustbe">货号：</td>
	            <td class="tableContent"><input type="text" name="productNo" value="${obj.productNo}"/></td>
	        </tr>
	        <tr>
	            <td class="columnTitle_mustbe">货物照片：</td>
	            <td class="tableContent"><input type="text" name="productImage" value="${obj.productImage}"/></td>
	            <td class="columnTitle_mustbe">数量：</td>
	            <td class="tableContent"><input type="text" name="cnumber" value="${obj.cnumber}"/></td>
	            
	        </tr>
	        <tr>
	            <td class="columnTitle_mustbe">单价：</td>
	            <td class="tableContent"><input type="text" name="price" value="${obj.price}"/></td>
	            <td class="columnTitle_mustbe">排序号：</td>
	            <td class="tableContent"><input type="text" name="orderNo"  value="${obj.orderNo}"/></td>
	        </tr>
	        <tr>
	            <td class="columnTitle_mustbe">ctype</td>
	            <td class="tableContent"><input type="text" name="ctype"  value="${obj.ctype}"/></td>
	            <td class="columnTitle_mustbe">productRequest</td>
	            <td class="tableContent"><input type="text" name="productRequest"  value="${obj.productRequest}"/></td>
	        </tr>
	        <tr>
	            <td class="columnTitle_mustbe">数量：</td>
	            <td class="tableContent"><input type="text" name="amount"  value="${obj.amount}"/></td>
	            <td class="columnTitle_mustbe">货物描述：</td>
	            <td class="tableContent"><textarea name="productDesc"  value="${obj.productDesc}" style="height:120px;"></textarea></td>
	        </tr>
		</table>
	</div>
</div>


 
</form>
</body>
</html>

