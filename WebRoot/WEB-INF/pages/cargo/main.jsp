<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../base.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>模块介绍</title>
  	<link rel="stylesheet" rev="stylesheet" type="text/css" href="${ctx}/skin/default/css/main.css" media="all"/>
</head>

<body>
<form>
<div class="textbox"></div>

	<div class="modelDiv">

        <table class="modelTable" cellspacing="1">
        	<tr>
        		<td colspan="2" class="modelTitle">货运模块介绍</td>
        	</tr>
        	<tr>
        		<td class="subModelTitle">购销合同</td>
        		<td class="model_intro_right">客户签单后，公司向厂家下达购销合同，包括货物的具体要求和交期。合同按不同厂家打印购销合同单，附件单独打印，由公司驻当地销售人员分发到各工厂。<br>
				归档：标识彻底完成的项目，方便统计。在报运时也不能在选这些合同。<br>
				</td>
        	</tr>
        	<tr>
        		<td class="subModelTitle">出货表</td>
        		<td class="model_intro_right">根据合同和指定的船期月份，统计当月的出货情况。<br></td>
        	</tr>
        	<tr>
        		<td class="subModelTitle">出口报运单</td>
        		<td class="model_intro_right">根据购销合同制定出口商品报运单。报运时可以将多个购销合同形成一单报运；也可以只走部分货物。<br>
        		分批走货：合同可以多个一起报运; 而一个合同可以分多次走货; 根据合同和合同货物的走货状态可以查看合同的走货情况。
        		<!-- 修改走货状态：1)合同新增货物、修改货物 2)报运货物修改、删除货物、增补货物、删除报运 -->
        		</td>
        	</tr>
        	<tr>
        		<td class="subModelTitle">HOME装箱单</td>
        		<td class="model_intro_right">根据出口报运单制定HOME装箱单，先制作HOME装箱单给客户看，客人同意，则直接制定相应装箱单；如有调整，则重新复制修改出口报运单，可能拆成多个报运。<br></td>
        	</tr>
        	<tr>
        		<td class="subModelTitle">装箱单</td>
        		<td class="model_intro_right">根据出口报运单制定装箱单，填写发票号、发票时间，以及客人等相关信息。<br></td>
        	</tr>
        	<tr>
        		<td class="subModelTitle">委托书</td>
        		<td class="model_intro_right">根据装箱制定海运或空运委托书。<br></td>
        	</tr>
        	<tr>
        		<td class="subModelTitle">发票</td>
        		<td class="model_intro_right">根据装箱制定发票。<br></td>
        	</tr>
        	<tr>
        		<td class="subModelTitle" nowrap>财务出口报运单</td>
        		<td class="model_intro_right">根据报运制定财务出口报运单。<br></td>
        	</tr> 	
			
			<tfoot>
				<tr>
					<td colspan="2" class="tableFooter"></td>
				</tr>
			</tfoot>
        </table>
 
	</div>
</form>
</body>

</html>