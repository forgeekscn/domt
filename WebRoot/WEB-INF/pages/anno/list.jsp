<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

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
	<form method="post" action="" id="listform">
		<div class="panel admin-panel">
			<div class="padding border-bottom">
				<ul class="search" style="padding-left:10px;">
					<li><a class="button border-main icon-plus-square-o"
						href="${ctx}/anno/tocreate.action"> 添加内容</a></li>
					<li>&nbsp;搜索：</li>
					</li>
					<if condition="$iscid eq 1">
					<li><select name="cid" class="input"
						style="width:200px; line-height:17px;" onchange="changesearch()">
							<option value="">请选择分类</option>
							<option value="">产品分类</option>
							<option value="">产品分类</option>
							<option value="">产品分类</option>
							<option value="">产品分类</option>
					</select></li>
					</if>
					<li><input type="text" placeholder="请输入搜索关键字" name="keywords"
						class="input"
						style="width:250px; line-height:17px;display:inline-block" /> <a
						href="javascript:void(0)" class="button border-main icon-search"
						onclick="changesearch()"> 搜索</a></li>
				</ul>
			</div>
			<table class="table table-hover text-center">
				<tr>
					<th width="20"></th>
					<th width="20%">时间</th>
					<th width="25%">标题</th>
					<th width="30%">内容</th>
					<th width="200">操作</th>
				</tr>

				<c:forEach items="${dataList}" var="obj" varStatus="status">
					<tr>
						<td style="text-align:left; padding-left:20px;">
						<input
							type="checkbox" name="announcementId" value="${obj.announcementId}" class="sb"/></td>
						<td>${obj.createTime}</td>
						<td width="10%">${obj.title}</td>
						<td>${obj.content}</td>
						<td><div class="button-group">
								<a class="button border-main" href="${ctx}/anno/toupdate.action?annoId=${obj.announcementId}"><span
									class="icon-edit"></span> 修改</a> <a class="button border-red"
									href="${ctx}/anno/deletebyid.action?annoId=${obj.announcementId}"
									 onclick="return del(1,1,1)"><span
									class="icon-trash-o"></span> 删除</a>
							</div></td>
					</tr>
				</c:forEach>


				<tr>
					<td style="text-align:left; padding:19px 0;padding-left:20px;"><input
						type="checkbox" id="checkall" /> </td>
					<td colspan="7" style="text-align:left;padding-left:20px;"><a
						href="" class="button border-red icon-trash-o"
						style="padding:5px 15px;" onclick="DelSelect()"> 删除</a> 
				</tr>
				<tr>
					<td colspan="8"><div class="pagelist">
							<a href="">上一页</a> <span class="current">1</span><a href="">2</a><a
								href="">3</a><a href="">下一页</a><a href="">尾页</a>
						</div></td>
				</tr>
			</table>
		</div>
	</form>
	<script type="text/javascript">
		//搜索
		function changesearch() {

		}

		//单个删除
		function del(id, mid, iscid) {
			if (confirm("您确定要删除吗?")) {

			}
		}

		//全选
		$("#checkall").click(function() {
			$("input[name='id[]']").each(function() {
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
			var ff="";
			$("input[name='announcementId']").each(function(x,y) {
				if (this.checked == true) {
					Checkbox = true;
					ff+=$(this).val()+",";  //你要不在后台分割演 恩 [1,2,3]zhe
						//
					 //等下  想想。。你每一行也有删除的操作吗？ 恩 那都是用的这个函数？不是 url get 所以这个函数只是批量的删除？恩
				}
				//.....是不是我刚刚服务器没重启啊 
				else{
				}//shishi
				//keyile
				//那哪里还有问题
				
			});
			
			if (Checkbox) {
				var t = confirm("您确认要删除选中的内容吗？");
				if (t == false){//这个我经常用 
					return false;}
					
					
					//你先把错误改掉好不好  看着烦。。。nmb 还没搞好 日你大爷 你这个框架问题打了。。。
					//你如果用ajax做 代码多不多
				//$("${ctx}/anno/delete.action").submit(); //这是啥  这里必须要按这种格式？不知道别人都这么些。。。
				//${ctx}/anno/delete.action  我就搞不懂这是啥 那你说怎么写 我就换一参数没带到请求去
			//。。。不知道 模板这是
				//这里直接传到后台 没问题吗  这个submit()函数  我也没见 就是提交是吧..。。。
				//怎么才能看下这个请求的post参数 你要干啥 试试 人呢似是什么
	
				
				/* $.ajax({
				   type:"POST",
				   url:"",
				   data:Checkbox   
				     //我突然发现一个问题  你这个Checkbox是循环里的  你传这个干嘛？再说 你传的参数 参数在哪里  .
				     //上面啊 annoouncementid ${obj.announcementId}这个啊恩
				}); */ 
				//我直接复制过来 等下
		$.ajax({
            type: 'post',
            url: '${ctx}/anno/delete.action', //是不是这个路径 宝贝没毛病darly
            data:{sb:ff},  //利用getContent()获取到的内容，传给name="con_text",提交到数据库里 ok
            
            success:function (msg) {
                
            }
        }); //好了 取出sb和msg看看。。。。
        
				
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