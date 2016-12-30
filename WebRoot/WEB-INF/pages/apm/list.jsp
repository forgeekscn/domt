<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<%@ include file="../basic/base.jsp"%>
<!DOCTYPE html>

<html lang="zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
<title></title>
</head>
<body>
	<form method="post" action="${ctx}/apm/list.action" id="listform">
		<div class="panel admin-panel">
			<div class="padding border-bottom">
				<ul class="search" style="padding-left:10px;">
					<li><a class="button border-main icon-plus-square-o"
						href="${ctx}/apm/tocreate.action"> 添加内容</a></li>
					<if condition="$iscid eq 1">
					<li>
						<select name="date" class="input"
							style="margin-left:30px;width:250px; line-height:17px;">
								<option value="">按入住率筛选</option>
								<option value="3" onclick="selectdate(this.value)">入住率大于80%</option>
								<option value="7" onclick="selectdate(this.value)">入住率大于50%</option>
								<option value="30" onclick="selectdate(this.value)">入住率小于50%</option>
						</select>
					</li>
					</if>
					<li><input type="text" placeholder="请输入搜索关键字" id="key"
						class="input"	style="width:250px; margin-left:20px;line-height:17px;display:inline-block" /> 
						<a href="#" class="button border-main icon-search"
						onclick="Sear()"> 搜索</a></li>
				</ul>
			</div>
			<table class="table table-hover text-center">
				<tr>
					<th width="20"></th>
					<th width="15%">公寓名称</th>
					<th width="15%">类型</th>
					<th width="100">入住人数</th>
					<th width="100">最高楼层</th>
					<th width="100">管理员id</th>
				</tr>

				<c:forEach items="${dataList}" var="obj" varStatus="status">
					<tr class="leirong">
						<td style="text-align:left; padding-left:20px;"><input
							type="checkbox" name="announcementId"
							value="${obj.apartmentId}" class="sb" /></td>
						<td>${obj.apartmentName}</td>
						<td>${obj.sex}</td>
						<td width="10%">${obj.totalPeople}</td>
						<td>${obj.totalFloor}</td>
						<td>${obj.managerId}</td>
						<td><div class="button-group">
								<a class="button border-main"
									href="${ctx}/apm/toupdate.action?apmId=${obj.apartmentId}"><span
									class="icon-edit"></span>修改</a> <a  id="deleteBtn" class="button border-red"
									href="${ctx}/apm/deletebyid.action?apmId=${obj.apartmentId}&pageNo=${page.pageNo}&totalPage=${page.totalPage}"
									onclick="return del(1,1,1)"><span class="icon-trash-o"></span>
									删除</a>
									
									
							</div></td>
					</tr>
				</c:forEach>
	
				<tr>
					<td style="text-align:left; padding:19px 0;padding-left:20px;">
					<input	type="checkbox" id="checkall" /></td>
					<td colspan="7" style="text-align:left;padding-left:20px;"><a
						href="" class="button border-red icon-trash-o"
						style="padding:5px 15px;" onclick="DelSelect()"> 删除</a>
				</tr>
				<tr>
				
					<td colspan="8">
						<div class="pagelist">
							<c:if test="${page.totalPage==4}"> 
									<a href="#" class="shangye" onclick="Shangye(${page.totalPage},${page.pageNo})">上一页</a> 
									<a href="${ctx}/apm/list.action?totalPage=4&pageNo=1">1</a> 
									<a href="${ctx}/apm/list.action?totalPage=4&pageNo=2">2</a>
									<a href="${ctx}/apm/list.action?totalPage=4&pageNo=3">3</a>
									<a href="${ctx}/apm/list.action?totalPage=4&pageNo=4">4</a>
									<a href="#" class="xiaye" onclick="Xiaye(${page.totalPage},${page.pageNo})">下一页</a>
						     </c:if>
						     <c:if test="${page.totalPage == 3}"> 
									<a class="shangye" href="${ctx}/apm/list.action?totalPage=3&pageNo=${page.pageNo-1}">上一页</a> 
									<a href="${ctx}/apm/list.action?totalPage=3&pageNo=1">1</a> 
									<a href="${ctx}/apm/list.action?totalPage=3&pageNo=2">2</a>
									<a href="${ctx}/apm/list.action?totalPage=3&pageNo=3">3</a>
									<a class="xiaye" href="${ctx}/apm/list.action?totalPage=3&pageNo=${page.pageNo+1}">下一页</a>
								
						     </c:if>
						     <c:if test="${page.totalPage == 2}"> 
									<a class="shangye" href="${ctx}/apm/list.action?totalPage=2&pageNo=${page.pageNo-1}">上一页</a>
									<a href="${ctx}/apm/list.action?totalPage=2&pageNo=1">1</a> 
									<a href="${ctx}/apm/list.action?totalPage=2&pageNo=2">2</a>
									<a class="xiaye" href="${ctx}/apm/list.action?totalPage=2&pageNo=${page.pageNo+1}">下一页</a>
								
						     </c:if>
						     <c:if test="${page.totalPage == 1}"> 
									<a href="${ctx}/apm/list.action?totalPage=1&pageNo=1">1</a> 
								
						     </c:if>
						     <c:if test="${page.totalPage == 0}"> 
									<span class="current">空</span>
								
						     </c:if>
						     <c:if test="${page.totalPage > 4}"> 
									<a class="shangye" href="${ctx}/apm/list.action?totalPage=${page.totalPage}&pageNo=${page.pageNo-1}">上一页</a> 
									<a href="${ctx}/apm/list.action?totalPage=${page.totalPage}&pageNo=${page.pageNo}">${page.pageNo}</a>
									<a href="${ctx}/apm/list.action?totalPage=${page.totalPage}&pageNo=${page.pageNo+1}">${page.pageNo+1}</a>
									<a href="${ctx}/apm/list.action?totalPage=${page.totalPage}&pageNo=${page.pageNo+2}">${page.pageNo+2}</a>......
									<a href="${ctx}/apm/list.action?totalPage=${page.totalPage}&pageNo=${page.totalPage}">${page.totalPage}</a>
									<a class="xiaye" href="${ctx}/apm/list.action?totalPage=${page.totalPage}&pageNo=${page.pageNo+1}">下一页</a>
								
						     </c:if>
						</div></td>
				</tr>
			</table>
		</div>
	</form>



	<script type="text/javascript">
		$(".pagelist a").each(function() {
			if (${page.pageNo}==this.text) {
				$(this).css({ 
					"color":"#FFF",
					"background-color":"#09F",
					"border-color": "#09F"
  				});
			}
			if(${page.totalPage}<this.text) {
				 $(this).attr('href', '#');   
				$(this).css({ 
					display:"none"
  				});
			}
		});

		if(${page.pageNo}==${page.totalPage}) {
			$(".pagelist .xiaye").css({ 
				display:"none"
 			});
		}
		if(${page.pageNo}==1) {
			$(".pagelist .shangye").css({ 
				display:"none"
 			});
		}

	
		function Shangye(totalPage,pageNo){
			window.location.href="${ctx}/apm/list.action?pageNo="+(pageNo-1)+"&totalPage="+totalPage;
			return false;
		}	
		function Xiaye(totalPage,pageNo){
			window.location.href="${ctx}/apm/list.action?pageNo="+(pageNo+1)+"&totalPage="+totalPage;
			return false;
		}	
		
	</script>
	
	<script type="text/javascript">
	
	/* $.ajax({
                cache: true,
                type: "POST",
                url:ajaxCallUrl,
                data:$('#yourformid').serialize(),// 你的formid
                async: false,
                error: function(request) {
                    alert("Connection error");
                },
                success: function(data) {
                    $("#commonLayout_appcreshi").parent().html(data);
                }
            }); */
	
		//下拉列表 分类
		function selectdate(arg){
			window.location.href='${ctx}/apm/list.action?date='+arg; 
		/* 	if(arg!=null){
				$.ajax({
					type : 'post',
					url : '${ctx}/anno/list.action', 
					data : {
						date:arg		
					},
					success : function(msg) {
					}
				});
			
			}
		 */		
		}									
		
	
		//搜索
		function Sear(){
			var key=$("#key").val();
			window.location.href='${ctx}/apm/list.action?key='+encodeURI(encodeURI(key));
			return false;
		}

		//单个删除
		function del(id, mid, iscid) {
			if (confirm("您确定要删除吗?")) {
				var a=0;
				$(".leirong").each(function(){
					a++;
				});
				if(a==1){
					var id=$(".sb").val();
					window.location.href="${ctx}/apm/deletebyid.action?apmId="+id+"&pageNo=${page.pageNo-1}&totalPage=${page.totalPage-1}";
					return false;
				}
					
			}else {
				return false;
			}
		}

		//全选
		$("#checkall").click(function() {
			$("input[name='announcementId']").each(function() {
				if (this.checked) {
					this.checked = false;
				} else {
					this.checked = true;
				}
			});
		})

		//批量删除
		function DelSelect() {
			var Checkbox = false;
			var quanshan=true; 
			var ff = "";
			$("input[name='announcementId']").each(function(x, y) {
				if (this.checked == true) {
					Checkbox = true;
					ff += $(this).val() + ",";
				}
				else quanshan=false;
			});
			if (Checkbox) {
				var t = confirm("您确认要删除选中的内容吗？");
				if (t == false) {
					return false;
				}
				$.ajax({
					type : 'post',
					url : '${ctx}/apm/delete.action',
					dateTye:'text',
					data : {
						sb : ff,
						pageNo : ${page.pageNo-1},
						totalPage : ${page.totalPage-1}
					},
					success:function(msg){
					},error:function(){
							if(quanshan)
								window.location.href="${ctx}/apm/list.action?pageNo=${page.pageNo-1}&totalPage=${page.totalPage-1}";
							else	
							window.location.href="${ctx}/apm/list.action?pageNo=${page.pageNo}&totalPage=${page.totalPage}";
					}
					
				});
				
			} else {
				alert("请选择您要删除的内容!");
				return false;
			}
		}

		//批量排序
		function sorts() {
			var Checkbox = false;
			$("input[name='id[]']").each(function() {
				if (this.checked == true) {
					Checkbox = true;
				}
			});
			if (Checkbox) {

				$("#listform").submit();
			} else {
				alert("请选择要操作的内容!");
				return false;
			}
		}

		//批量首页显示
		function changeishome(o) {
			var Checkbox = false;
			$("input[name='id[]']").each(function() {
				if (this.checked == true) {
					Checkbox = true;
				}
			});
			if (Checkbox) {

				$("#listform").submit();
			} else {
				alert("请选择要操作的内容!");

				return false;
			}
		}

		//批量推荐
		function changeisvouch(o) {
			var Checkbox = false;
			$("input[name='id[]']").each(function() {
				if (this.checked == true) {
					Checkbox = true;
				}
			});
			if (Checkbox) {

				$("#listform").submit();
			} else {
				alert("请选择要操作的内容!");

				return false;
			}
		}

		//批量置顶
		function changeistop(o) {
			var Checkbox = false;
			$("input[name='id[]']").each(function() {
				if (this.checked == true) {
					Checkbox = true;
				}
			});
			if (Checkbox) {

				$("#listform").submit();
			} else {
				alert("请选择要操作的内容!");

				return false;
			}
		}

		//批量移动
		function changecate(o) {
			var Checkbox = false;
			$("input[name='id[]']").each(function() {
				if (this.checked == true) {
					Checkbox = true;
				}
			});
			if (Checkbox) {

				$("#listform").submit();
			} else {
				alert("请选择要操作的内容!");

				return false;
			}
		}

		//批量复制  meibaobing 没看到穿进去的checkbox 没毛病吗你post请求的怎么看的到  只能在后台看 
		function changecopy(o) {
			var Checkbox = false;
			$("input[name='id[]']").each(function() {
				if (this.checked == true) {
					Checkbox = true;
				}
			});
			if (Checkbox) {
				var i = 0;
				$("input[name='id[]']").each(function() {
					if (this.checked == true) {
						i++;
					}
				});
				if (i > 1) {
					alert("只能选择一条信息!");
					$(o).find("option:first").prop("selected", "selected");
				} else {

					$("#listform").submit();
				}
			} else {
				alert("请选择要复制的内容!");
				$(o).find("option:first").prop("selected", "selected");
				return false;
			}
		}
	</script>
</body>
</html>