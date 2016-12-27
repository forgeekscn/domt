<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
</head>

<body>

	<if test="${dataList!=null}">
	<div class="eXtremeTable">
		<table id="ec_table" class="tableRegion" width="98%">
			<thead>
				<tr>
					<td class="tableHeader"><input type="checkbox" name="selid"
						onclick="checkAll('id',this)"></td>
					<td class="tableHeader">序号</td>
					<td class="tableHeader">总金额</td>
					<td class="tableHeader">货物数</td>
					<td class="tableHeader">附件数</td>
					<td class="tableHeader">客户名称</td>
					<td class="tableHeader">合同号</td>
					<td class="tableHeader">制单人</td>
				</tr>
			</thead>
			<tbody class="tableBody">

				<c:forEach items="${dataList}" var="o" varStatus="status">
					<tr class="odd" onmouseover="this.className='highlight'"
						onmouseout="this.className='odd'">
						<td><input type="checkbox" name="id" value="${o.contractId}" /></td>
						<td>${o.id}</td>
						<td>${o.contractProductId}</td>
						<td>${o.cpFactoryId}</td>

						<td>${o.extContractProductId}</td>
						<td>${o.extFactoryId}</td>
						<td><a
							href="${ctx}/cargo/contractproduct/tocreate.action?contractId=${o.id}"
							title="新增货物信息">[货物]</a></td>
					</tr>
				</c:forEach>

			</tbody>
		</table>
	</div>

	</if>
</body>
</html>

