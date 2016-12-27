<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>附件新增</title>
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
	<input type="text" name="contractProductId" value="${contractProductId}"/>

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
    <div id="navMenubar">
<ul>
<li id="save"><a href="#" onclick="formSubmit('${ctx}/cargo/extcproduct/insert.action','_self');">确定</a></li>
<li id="back"><a href="${ctx}/cargo/extcproduct/tocreate.action?contractProductId=${contractProductId}">返回</a></li>
</ul>
    </div>
</div>
</div>
</div>
     
<div class="textbox" id="centerTextbox">
    
    <div class="textbox-header">
    <div class="textbox-inner-header">
    <div class="textbox-title">
		新增附件信息
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
	            	<input type="hidden" id="factoryName" name="factoryName" value="factoryName"/>
	            </td>
	            <td class="columnTitle_mustbe">货号：</td>
	            <td class="tableContent"><input type="text" name="productNo"/></td>
	        </tr>
	        <tr>
	            <td class="columnTitle_mustbe">货物照片：</td>
	            <td class="tableContent"><input type="text" name="productImage"/></td>
	            <td class="columnTitle_mustbe">数量：</td>
	            <td class="tableContent"><input type="text" name="cnumber"/></td>
	            
	        </tr>
	        <tr>
	            <td class="columnTitle_mustbe">单价：</td>
	            <td class="tableContent"><input type="text" name="price"/></td>
	            <td class="columnTitle_mustbe">排序号：</td>
	            <td class="tableContent"><input type="text" name="orderNo"/></td>
	        </tr>
	        <tr>
	            <td class="columnTitle_mustbe">ctype</td>
	            <td class="tableContent"><input type="text" name="ctype"/></td>
	            <td class="columnTitle_mustbe">productRequest</td>
	            <td class="tableContent"><input type="text" name="productRequest"/></td>
	        </tr>
	        <tr>
	            <td class="columnTitle_mustbe">数量：</td>
	            <td class="tableContent"><input type="text" name="amount"/></td>
	            <td class="columnTitle_mustbe">货物描述：</td>
	            <td class="tableContent"><textarea name="productDesc" style="height:120px;"></textarea></td>
	        </tr>
		</table>
	</div>
</div>
 
<div class="textbox" id="centerTextbox">
  <div class="textbox-header">
  <div class="textbox-inner-header">
  <div class="textbox-title">
    附件列表
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
		<td class="tableHeader">厂家名称</td>
		<td class="tableHeader">货号</td>
		<td class="tableHeader">数量</td>
		<td class="tableHeader">单价</td>
		<td class="tableHeader">总金额</td>
	</tr>
	</thead>
	<tbody class="tableBody" >
	
	<c:forEach items="${dataList}" var="o" varStatus="status">
	<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
		<td><input type="checkbox" name="id" value="${o.id}"/></td>
		<td>${status.index+1}</td>
		<td>${o.factoryName}</td>
		<td>${o.productNo}</td>
		<td>${o.cnumber}</td>
		<td>${o.ctype}</td>
		<td>${o.productRequest}</td>
		<td>${o.price}</td>
		<td>${o.amount}</td>
		<td><a href="${ctx}/cargo/extcproduct/toupdate.action?contractProductId=${contractProductId}&id=${o.id}">修改</a></td>
		<td><a href="${ctx}/cargo/extcproduct/deletebyid.action?contractProductId=${contractProductId}&id=${o.id}">删除</a></td>
	</tr>
	</c:forEach>
	
	</tbody>
</table>
</div>
 
</div>


 
</form>
</body>
</html>

