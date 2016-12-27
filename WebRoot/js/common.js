/* 防止过快访问 */
var isCannotSubmit = 0;
window.onload = function () {
//					alert("ddd");
					var windowFrames = this.top.frames;
					iteratorFrames(windowFrames);
//					alert(windowFrames.length);
//					for(var i=0; i<windowFrames.length; i++) {
//						var childwindowFrames[i]
//						alert(windowFrames[i].name + " : " + windowFrames[i].isCanSubmit);
//						if (windowFrames[i].isCanSubmit) {
//							alert(windowFrames[0].isCanSubmit);
//							windowFrames[i].isCanSubmit = 1;				
//							alert(windowFrames[0].isCanSubmit);
//						}
//					}
//					createMask();
					//作用:如果页面上含有名称为need_popu_date的元素,那么说明此页面的日期输入方式采用(弹出控件)
//						processCalendar();
				}


String.prototype.replaceAll  = function(s1,s2){    
	return this.replace(new RegExp(s1,"gm"),s2);    
} 


/* create by tony 20100121 option:实现yyyy-mm-dd或yyyy-m-d数据转换为中文字符串yyyy年mm月dd日*/ 
function getCNDateStr( datestr ){
	var s = "";
	s = datestr.replace("-0","-");
	s = s.replace("-0","-");
	s = s.replace("-","年");
	s = s.replace("-","月")+"日";
	return s;
}

/* create by tony 20110207 option:在线预览swf文件 */
function viewSwfFile( fileName ){
	var swfFileName = encodeURI(encodeURI(fileName)); 
	formSubmit('/common/viewSwfFile.jsp?swfFileName='+swfFileName,'_self')
}
		
  function formatDateTimeCN( date ){
	  var s ="";
	  if(date.indexOf(".")>-1){
		  date = date.substring(0, date.indexOf("."));
	  }
	  if(date.length==4){			//yyyy
		  s = date+"年";
	  }else if(date.length==7){	//yyyy-MM
		  s = date.replace("-0", "-").replace("-", "年")+"月";
	  }else if(date.length==10){	//yyyy-MM-dd
		  s = date.replace("-0", "-").replace("-", "年").replace("-", "月")+"日";
	  }else if(date.length==2){	//HH
		  s = date+"时";
	  }else if(date.length==5){	//HH:mm
		  s = date.replace(":0", ":").replace(":", "时")+"分";
	  }else if(date.length==8){	//HH:mm:ss
		  s = date.replace(":0", ":").replace(":", "时").replace(":", "分")+"秒";
	  }else if(date.length==13){	//yyyy-MM-dd HH
		  s = date.replace("-0", "-").replace("-", "年").replace("-", "月").replace(" 0", " ").replace(" ", "日")+"时";
	  }else if(date.length==16){	//yyyy-MM-dd HH:mm
		  s = date.replace("-0", "-").replace("-", "年").replace("-", "月").replace(" 0", " ").replace(" ", "日").replace(":0", ":").replace(":", "时")+"分";
	  }else if(date.length==19){	//yyyy-MM-dd HH:mm:ss
		  s = date.replace("-0", "-").replace("-", "年").replace("-", "月").replace(" 0", " ").replace(" ", "日").replace(":0", ":").replace(":", "时").replace(":", "分")+"秒";
	  }
	  //s = s.replace("0[时分秒]", "");	//正则 0时0分0秒的都替换为空
	  
	  return s;
  }

/* create by tony 201000301 option:拼写日期+中文的标题 */
function spellTitle( date,title){
	$("#title").val(formatDateTimeCN(date)+title);
}
    
String.prototype.endWith = function(oString){   
  var reg=new RegExp(oString+"$"); 
  return reg.test(this); 
}

function iteratorFrames(framesArray) {	
		for(var i=0; i<framesArray.length; i++) {			
			var childWindowFrames = framesArray[i];

//			alert("1 --" + childWindowFrames.name + " : " + childWindowFrames.isCannotSubmit);
			if (childWindowFrames.isCannotSubmit) {
//			alert(childWindowFrames.isCannotSubmit);
//							alert(windowFrames[0].isCanSubmit);
				childWindowFrames.isCannotSubmit = 0;				
//							alert(windowFrames[0].isCanSubmit);
//			alert("2 --" + childWindowFrames.name + " : " + childWindowFrames.isCanSubmit);

			}
			if (childWindowFrames.PageLoading) {
				childWindowFrames.PageLoading = false;
			}

			if (childWindowFrames.frames.length > 0) {
				iteratorFrames(childWindowFrames.frames);
			} 				

		}
}

								
function createMask() {
	var aArrays = document.getElementsByTagName("a");
//	alert(aArrays.length);
	for (i=0;i<aArrays.length;i++) {
		aArrays[i].onmouseup = function(){
//									alert(isCanSubmit);									
									linkArray = document.getElementsByTagName("a");
									for (i=0;i<linkArray.length;i++) {
										linkArray[i].onmousedown = function() {
//											alert(isCannotSubmit);
											if (isCannotSubmit == 1) {
												alert("页面正在加载中，请等待...");	
												isCannotSubmit = 0;	
												return;										
											} else {
												isCannotSubmit = 1;
											}											
										}										
									}
								};
	}
}

/* 单击全部选择复选框或者全部取消复选框(针对form) */
function checkAllBox(formObject) {
    var checkOrNot = false;
    var element = formObject.elements.checkAll;

    if (element.type.toUpperCase() == 'CHECKBOX') {
        checkOrNot = element.checked;
    }

    for (i = 0; i < formObject.elements.length; i++) {
        element = formObject.elements[i];
        if ((element.type.toUpperCase() == 'CHECKBOX')) {
            element.checked = checkOrNot;
        }
    }
}

/* 全选*/
function checkAll(who, obj){
	var curCheckBox = document.getElementsByName(who);
	for(i = 0; i < curCheckBox.length; i++){
		curCheckBox.item(i).checked = obj.checked;
	}
}

/* add by tony 2007.12.27 */
/* 单击某个chekbox ,选择一组checkbox，全部选择或全部取消 */
/* Group checkbox 的名称为ck_开头。例如：Group ck_xxx，checkbox 为xxx */
function checkGroupBox(checkGroupName) {
	var sName = checkGroupName.name.substring(3);
    var checkOrNot = false;
    var element = eval("document.forms[0].elements."+checkGroupName.name);		//取得点击checkbox的选择值

    if (element.type.toUpperCase() == 'CHECKBOX') {
        checkOrNot = element.checked;
    }
    
    var checkGroup = eval("document.forms[0]."+sName);
    if(checkGroup!=null){	//如果没有选择框
	    for (i = 0; i < checkGroup.length; i++) {
	        element = checkGroup[i];
	        if ((element.type.toUpperCase() == 'CHECKBOX')) {
	            element.checked = checkOrNot;
	        }
	    }
    }
}

/* add by tony 2009.11.17 Option: checkGroup选中的checkbox.value进行拼接 */
function checkJoinValue( name , joinStr ) {
	var _str = "";
	var _elements = document.forms[0].elements;
	for(var i=0;i<_elements.length;i++){
		var e = _elements[i];
        if (_elements[i].type=='checkbox' && _elements[i].name==name) {
	        if(e.checked){
				_str += e.value + joinStr;
			}
		}
    }
    delLastChar(_str);
	return _str;
}

/* add by tony 2010.02.14 Option: 链接字符串,并链接前缀和后缀 */
function checkFixJoinValue( name , joinStr, prefix, suffix ) {
	var _str = "";
	var _elements = document.forms[0].elements;
	for(var i=0;i<_elements.length;i++){
		var e = _elements[i];
        if (_elements[i].type=='checkbox' && _elements[i].name==name) {
	        if(e.checked){
				_str += prefix + e.value + suffix + joinStr;
			}
		}
    }
    _str = delLastChar(_str);
	return _str;
}

/* add by tony 2010.02.14 Option: del last char */
function delLastChar( s ){
	return delLast(s,1);
}
function delLast( s , len ){
	if(s!=""){
		s = s.substring(0,s.length-len);
	}
	return s;
}


/* add by tony 2009.10.307 Option: find_div的显示和不显示 */
function platArrow() {
  var _s = document.getElementById('parrow');
  if(_s.className=='show_arrow'){
    _s.className='hide_arrow';
  }else{
    _s.className='show_arrow';
  }
}

/* add by tony 2009.10.307 Option: find_div的显示和不显示 */
function divDisplay( divName ) {
  var _s = document.getElementById(divName);
  if(_s.style.display=='none'){
    _s.style.display='block';
  }else{
    _s.style.display='none';
  }
}

/* add by tony 2009.10.307 Option: find_div的显示和不显示 */
function findDisplay() {
  var _s = document.getElementById('find_div');
  if(_s.style.display=='none'){
    _s.style.display='block';
  }else{
    _s.style.display='none';
  }
}

/* add by tony 2009.10.30 清空所有以f_开头的文本框,下拉框,选择框 */
function findReset()  {
  for(var i=0;i<document.forms[0].elements.length;i++){
	//s = eval("document.forms[0].find_"
	var e = document.forms[0].elements[i];
	if(document.forms[0].elements[i].type=='text'||document.forms[0].elements[i].type=='hidden'){
		var s = new String();
		s = e.name;
		if(s.indexOf("f_")!=-1){
			e.value='';
		}
	}
	if(document.forms[0].elements[i].type=='select-one'){
		var e = document.forms[0].elements[i];
		var s = new String();
		s = e.name;
		if(s.indexOf("f_")!=-1){
			e.selectedIndex = 0;	// set the select first element
		}
	}
	if(document.forms[0].elements[i].type=='checkbox'){
		var s = new String();
		s = e.name;
		if(s.indexOf("f_")!=-1){
			e.checked=false;
		}
	}
  }
}

/* add by tony 2011.08.19 由选择的值得到对应的index */
function getComboListIndex( id, value ){
	var selectObj = document.getElementById(id);

	var optionArray = selectObj.options;	
	for(i=0; i < optionArray.length; i++) {
		if(optionArray[i].value == value) {
			return i;
		}
	}
}

function changeSelected(selectId, value) {
	var selectObj = document.getElementById(selectId);	
	var optionArray = selectObj.options;	
	for(i=0; i < optionArray.length; i++) {
		if(optionArray[i].value == value) {
			optionArray[i].selected = true;
		}
	}
}


/* 弹出选择网页对话框 */
function popupWindow(url, windowObject) {

    dialogBoxWidth  = 600 //window.screen.width / 2;
    dialogBoxHeight = 480 //window.screen.height / 2;
    dialogBoxLeft   = 100 //dialogBoxWidth / 2;
    dialogBoxTop    = 100 //dialogBoxHeight /2;

	timer = new Date();

	if(url.indexOf('?') > 0) {
		url = url + "&timer=" + timer.getTime();
	} else {
		url = url + "?timer=" + timer.getTime();
	}
    var returnValues = showModalDialog(url, windowObject, 'dialogWidth:' + dialogBoxWidth + 'px;dialogHeight:' + dialogBoxHeight + 'px;dialogLeft:' + dialogBoxLeft + 'px;dialogTop:' + dialogBoxTop + 'px;center:yes;help:no;resizable:yes;status:no');
    if (returnValues) {
		return returnValues;
	}
	//window.open(url, "请选择", "top=" + dialogBoxTop + ",left=" + dialogBoxLeft + ",width=" + dialogBoxWidth + ",height=" + dialogBoxHeight + ",alwaryRaised=yes,dependent=yes,location=no,menubar=no,resizable=yes,scrollbars=yes,toolbar=no");
}

/* 弹出选择网页对话框 */
function popupWindowModeless(url, windowObject) {
    dialogBoxWidth  = 600 //window.screen.width / 2;
    dialogBoxHeight = 480 //window.screen.height / 2;
    dialogBoxLeft   = 100 //dialogBoxWidth / 2;
    dialogBoxTop    = 100 //dialogBoxHeight /2;

	timer = new Date();

	if(url.indexOf('?') > 0) {
		url = url + "&timer=" + timer.getTime();
	} else {
		url = url + "?timer=" + timer.getTime();
	}
    window.showModelessDialog(url, windowObject, 'dialogWidth:' + dialogBoxWidth + 'px;dialogHeight:' + dialogBoxHeight + 'px;dialogLeft:' + dialogBoxLeft + 'px;dialogTop:' + dialogBoxTop + 'px;center:yes;help:no;resizable:yes;status:no');
    //window.open(url, "请选择", "top=" + dialogBoxTop + ",left=" + dialogBoxLeft + ",width=" + dialogBoxWidth + ",height=" + dialogBoxHeight + ",alwaryRaised=yes,dependent=yes,location=no,menubar=no,resizable=yes,scrollbars=yes,toolbar=no");
}

/* 弹出窗口回传参数 */
function selectCheckedRadio(openerId, openerName, id, name, obj) {
	var dialogParentWin = window.dialogArguments; 
	dialogParentWin.document.getElementById(openerId).value = id;
	dialogParentWin.document.getElementById(openerName).value = name;
	if (obj) {
		var val = obj.parentElement.parentElement.getElementsByTagName("TD");
		window.returnValue = val[1].innerText;
	}
	this.close();
}

/* 验证表单是否有复选框或者单选框 */
function checkIsExist (objectName) {
	objectArray = document.getElementsByName(objectName);
	if (objectArray.length <= 0) {
		return("对不起! 现在没有任何数据!");
	}
}
/* 验证表单是否有复选框或者单选框(页面中使用公用check框)使用 */
function checkHasSelect (objectName, stringValue) {
	if (!document.forms[0].elements(objectName)) {
		return("<br>" + stringValue + ":必须选择");
	}	
}
/* 控制frame滑动开关 */
var xCols = 200;
var yRows = 108;
//var currentCols= xCols + ',' + '*';
function collapseFrame(){
	xCols = xCols - 35;
	collapseFr();			
}
function collapseFrame_back(){
	xCols = xCols + 35;
	collapseFr_back();
}
function collapseFr(){
	if (xCols > 1) {
		top.middle.contents.left_frame.style.display ="none";
		setTimeout("collapseFrame();",5);
		top.middle.middleFrameSet.cols = xCols + ',' + '*';
		document.getElementById('switchPoint').className = "navPoint_back";
	}else{
		top.middle.middleFrameSet.cols ="1,*";	
		document.getElementById('switchPoint').className = "navPoint_back";
		document.getElementById('switchPoint').style.display = "none";
		document.getElementById('switchPoint2').style.display = "block";
	}
}
function collapseFr_back(){
	if (xCols < 200) {
		setTimeout("collapseFrame_back();",5);
		top.middle.middleFrameSet.cols = xCols + ',' + '*';
		document.getElementById('switchPoint').className = "navPoint";
	}else{
		top.middle.middleFrameSet.cols ="200,*";
		top.middle.contents.left_frame.style.display ="";	
		document.getElementById('switchPoint').className = "navPoint";
		document.getElementById('switchPoint').style.display = "block";
		document.getElementById('switchPoint2').style.display = "none";
	}
}

/*上下开关*/
function RowsFrame(){
	yRows = yRows - 35;
	RowsFr();			
}
function RowsFrame_back(){
	yRows = yRows + 35;
	RowsFr_back();
}
function RowsFr(){
	if (yRows > 1) {
		//top.middle.contents.left_frame.style.display ="none";
		setTimeout("RowsFrame();",5);
		top.topFrameset.rows = yRows + ',' + '*';
		document.getElementById('switchPointTop').className = "navPointTop_back";
	}else{
		top.topFrameset.rows ="1,*";	
		document.getElementById('switchPointTop').className = "navPointTop_back";
		document.getElementById('switchPointTop').style.display = "none";
		document.getElementById('switchPointTop2').style.display = "block";
	}
}
function RowsFr_back(){
	if (yRows < 108) {
		setTimeout("RowsFrame_back();",5);
		top.topFrameset.rows = yRows + ',' + '*';
		document.getElementById('switchPointTop').className = "navPointTop";
	}else{
		top.topFrameset.rows ="125,*";
		//top.middle.contents.left_frame.style.display ="";	
		document.getElementById('switchPointTop').className = "navPointTop";
		document.getElementById('switchPointTop').style.display = "block";
		document.getElementById('switchPointTop2').style.display = "none";
	}
}

/* 列表表格 */
function listTableColor(tableId){
    var oTable = document.getElementById(tableId);
	oTable.className="listTable";
	oTable.tHead.className="listTableThead";
	if(oTable.tHead.rows.length > 1) {
		for(j=0;j<oTable.tHead.rows.length-1;j++) {
			oTable.tHead.rows[j].className="topRow";
		}
	}
	oTable.tHead.rows[oTable.tHead.rows.length-1].className="listTableRowTitle";
	oTable.tHead.rows[oTable.tHead.rows.length-1].cells[0].className="checkBox";
	for(var i = 0; i < oTable.tBodies[0].rows.length; i++){
		if(oTable.tBodies[0].rows[i].className != 'errorMessage'){			
	    	oTable.tBodies[0].rows[i].className = (i%2 == 0) ? 'oddTr' : 'evenTr' ;
	    }
	} 
}

function computeStatusColor(tableId) {
	var oTable = document.getElementById(tableId);	
	for(i=0;i<oTable.rows.length;i++) {
		if(oTable.rows[i].status) {
			currentStatus = oTable.rows[i].status;
			switch(currentStatus) {
				case "1":
					changeTdCss(oTable.rows[i], "advanceStatus");
					break;
				case "2":
					changeTdCss(oTable.rows[i], "overStatus");					
					break;
				default:
					break;
			}
		}
	}
}

function changeTdCss(trObj, className) {
	for (var i=0; i<trObj.cells.length; i++) {
		if (i != 0) {
			trObj.cells[i].className = className;
		}
	}
}

function DivSetVisible(state, divObj, ifraObj)
{
	var DivRef = document.getElementById(divObj);
	var IfrRef = document.getElementById(ifraObj);
	if(state)
	{
	DivRef.style.display = "block";
	IfrRef.style.width = document.body.clientWidth;
	IfrRef.style.height = document.body.clientHeight;
	IfrRef.style.top = 0;
	IfrRef.style.left = 0;
	IfrRef.style.display = "block";
	}
	else
	{
	DivRef.style.display = "none";
	IfrRef.style.display = "none";
	}
}
function listColor(tableId) {
	var oTable = document.getElementById(tableId);
	for(var i = 0; i < oTable.tBodies[0].rows.length; i++){
		if(oTable.tBodies[0].rows[i].className != 'errorMessage'){			
	    	oTable.tBodies[0].rows[i].className = (i%2 == 0) ? 'oddTr' : 'evenTr' ;
	    }
	} 
}


/*
 *	复制table最后一个tr的内容
 *  并创建新tr
 *  objId:table 的id属性
 */

function addRecord(objId, isClear) {
	var i = 1
	var tableObj = document.getElementById(objId);
	var rowLength = tableObj.rows.length;
//	alert(rowLength);
	var lastTr = tableObj.rows[rowLength-1];
	//alert(lastTr.rowIndex);	
    var newTR = lastTr.cloneNode(true);
    newTR.id="a" + (++i);
    lastTr.parentNode.insertAdjacentElement("beforeEnd", newTR);
    
    if (isClear) {
    	for (j=0; j<newTR.cells.length; j++) {
    		var inputArray = newTR.cells[j].getElementsByTagName('input');
    		if (inputArray.length > 0) {
	   			for (m=0; m<inputArray.length; m++) {
	   				inputArray[m].value="";
	   			}
   			} else {
   				newTR.cells[j].innerHTML = "";
   			}
    	}
    }
//    alert(tableObj.rows.length);
    try {
	    document.getElementsByName('number')[i-1].value = i;
	} catch (e){
	}
	processCalendar();
	return tableObj.rows.length;
}

/*
 *	删除table复选框指定的tr 主要结合 addRecord使用
 *	tableId:table 的 id 属性
 *	topRow:table顶端有几行不参与删除操作
 *	clearValueObjName: 在移除的时候哪一个值需要清空。
 *	isDeleteAll:能否删除所有，一般顶端需保留一行。
 */

function delRecord(tableId, topRow, clearValueObjName ,isDeleteAll) {  
	var clearValuesObjs = document.getElementsByName(clearValueObjName);
	var tableObj = document.getElementById(tableId).tBodies[0];
	var trTotal = tableObj.rows.length - topRow;
	var delCheckboxs = document.getElementsByName('del');
	var delNumber = new Array(); 
	if(delCheckboxs.length > 0){
			var rowLength = delCheckboxs.length;
			for(var i=0;i<rowLength;i++){
				if(delCheckboxs[i].checked){
					if (clearValuesObjs[i]) {
						clearValuesObjs[i].name="_disabledOrderId_";
						clearValuesObjs[i].value="";
					}
					delNumber.push(i+topRow);										
				}
			}
	
	}else{
		alert("对不起，请至少选择一条需要移除的数据");
		return false;
	}
	
	if(delNumber.length <= 0){
		alert("对不起，请至少选择一条需要移除的数据");
		return false;
	}
	if(!confirm("确实要移除指定内容吗?单击【确定】将移除指定内容！单击【取消】将终止移除操作！")){
		return false;
	}	
	for(var i=0;i<delNumber.length;i++){

		if(isDeleteAll == false){
			if (i + 1 >= trTotal) {
	//		alert("break");
				break;
			}		
		}
//		alert(delNumber[i] - i);
		tableObj.deleteRow(delNumber[i] - i);
	}
}

/*
 * 2006/10/12 @xiongtao
 * 功能：超链接高亮显示．
 * 用法：<a id="aa_3" onclick="linkHighlighted(this)">
 * id 命名必需以 aa_开头，后边填写数字， 超链接id名称不能重复。
 * 
*/

 function linkHighlighted(obj){
     	var linka=document.getElementsByTagName('A');
     		for(var i=0;i<linka.length;i++){
     			if(linka[i].id.indexOf('aa_')!=-1){
     				linka[i].className = '';
     			}
     		}
     		obj.className ='focus';
		
     }

/*
 * 2006/10/20 @oasis
 * 功能：MainMenu超链接高亮显示．
 * 用法：<a id="aa_3" onclick="linkHighlighted(this)">
 * id 命名必需以 aa_开头，后边填写数字， 超链接id名称不能重复。
 * 
*/
function menuLinkHighlighted(obj){
	var linka=document.getElementsByTagName('A');
	for(var i=0;i<linka.length;i++){
		if(linka[i].id.indexOf('aa_')!=-1){
			linka[i].style.backgroundImage = '';
			linka[i].style.borderColor='#b6bccc #b6bccc #adc9d4 #b6bccc';
		}
	}
	obj.style.background='fff';
	obj.style.borderColor='#b6bccc #b6bccc #fff #b6bccc';
}
     
function textareasize(obj) {
	if(obj.scrollHeight > 70) {
		obj.style.height = obj.scrollHeight + 2 + 'px';
	}		
} 
	
function textareasizeNew(obj,length) {
	if(obj.scrollHeight > length) {
		obj.style.height = obj.scrollHeight + 2 + 'px';
	}
}

/*  设置隐藏层
	objMain: 	设置隐藏层ID； PS:  test1
	objss :  	设置未隐藏后DIV ID； PS:  test_visibility1 比如点击后为 -
	objother:	设置隐藏前DIV ID； test_own1 比如点击前为 +
	PS:ShowFloatDiv('testObj',1,5)  obj,第几个,个数；
	2008.11.05 by Vicco
*/
    function ShowFloatDiv(obj,num,length){
	    for(var id = 1; id<=length; id++){
		    objMain = obj + id;
		    objss = obj + '_own' + id;
		    objother = obj + '_visibility' + id;
			if(id == num && objMain){
	    	try{document.getElementById(objMain).style.display = "block";}catch(e){};
	    	try{document.getElementById(objss).style.display = "none";}catch(e){};
	    	try{document.getElementById(objother).style.display = "block";}catch(e){};
	    	}
	    }
	}
    function HideFloatDiv(obj,num,length){
	    for(var id = 1; id<=length; id++){
		    objMain = obj + id;
		    objss = obj + '_own' + id;
		    objother = obj + '_visibility' + id;
			if(id == num){
	    	try{document.getElementById(objMain).style.display = "none";}catch(e){};
	    	try{document.getElementById(objss).style.display = "block";}catch(e){};
	    	try{document.getElementById(objother).style.display = "none";}catch(e){};
	    	}
	    }
	}

/* 清空内容区与计算字符数量 */

    function clearcontent(obj,length){
	    var ss = document.getElementById(obj);
	    if(ss.value != ''){
	   		var warning = confirm("确认要清空内容吗?");
	   		if(warning == true){
	   		clearcon(obj,length);
	   		}else{
	   		return false;
	   		}
   		}
   	}
	function clearcon(obj,length){
	    var ss = document.getElementById(obj);
	    var Maxnum =  document.getElementById(obj).maxLength;
	    ss.value = '';
	    if(!length){
	    	ss.style.height = 70;
	    }else{
	    	ss.style.height = length;
	    }
	    ss.focus();
    	var message = "num_" + obj;
	    if(!Maxnum){
	    	document.getElementById(message).innerHTML = " <font color=#009900>" + "0" + "&nbsp;" + "/" + "&nbsp;" + "无上限" + "</font>";
	    }else{
	    	document.getElementById(message).innerHTML = "<font color=#009900>" + "0" + "&nbsp;" + "/" + "&nbsp;" + Maxnum +  "</font>";
	    }
    }

/*
*add by AAA 2011-04-27
*清空当前text框内容
*/
	function clearconText(obj,length){
	    var ss = document.getElementById(obj);
	    var Maxnum =  document.getElementById(obj).maxLength;
	    ss.value = '';
	    ss.focus();
    	var message = "num_" + obj;
	    if(!Maxnum){
	    	document.getElementById(message).innerHTML = " <font color=#009900>" + "0" + "&nbsp;" + "/" + "&nbsp;" + "无上限" + "</font>";
	    }else{
	    	document.getElementById(message).innerHTML = "<font color=#009900>" + "0" + "&nbsp;" + "/" + "&nbsp;" + Maxnum +  "</font>";
	    }
    }
/*
*update by AAA 2011-04-26
*检验页面文本框等内容长度，汉字占两个字符
*/
function getMaxlength(id) {
    var txtobj = document.getElementById(id);
    if(txtobj!=null){
    	var fData = txtobj.value;
    	var intLength=0;
	    for (var i=0; i<fData.length; i++) {    //fData的长度(Unicode长度为2，非Unicode长度为1)
	        if ((fData.charCodeAt(i) < 0) || (fData.charCodeAt(i) > 255))
	            intLength=intLength+2
	        else
	            intLength=intLength+1    
	    }
	    var Maxnum =  txtobj.maxLength;
	    var shownum = 'num_'+id;
	    if(!Maxnum){
			document.getElementById(shownum).innerHTML = " <font color=#009900>" +  intLength + "&nbsp;" + "/" + "&nbsp;" + "无上限" + "</font>";
		}else if( intLength == Maxnum ){
			document.getElementById(shownum).innerHTML = "<font color=#009900>已达到上限</font>";
		}else if( intLength > Maxnum ){
			document.getElementById(shownum).innerHTML = "<font color=red>已超过限制</font>";
		}else{
			document.getElementById(shownum).innerHTML = "<font color=#009900>" +  intLength + "&nbsp;" + "/" + "&nbsp;" + Maxnum + "</font>";
		}
    }
    return intLength
}


	/*输出当前TEXTAREA区域字符数量*/
	function getNownum(id){
		var txtobj = document.getElementById(id);
		if(txtobj != null){
			var message = txtobj.value.length;
			if(message == 0){
				document.write("0");
			}else{
				var fData = txtobj.value;
		    	var intLength=0;
			    for (var i=0; i<fData.length; i++) {    //fData的长度(Unicode长度为2，非Unicode长度为1)
			        if ((fData.charCodeAt(i) < 0) || (fData.charCodeAt(i) > 255))
			            intLength=intLength+2
			        else
			            intLength=intLength+1    
			    }
			    var Maxnum =  txtobj.maxLength;
				document.write(intLength);
			}
		}
	}

	/*输出当前最大字符数量*/
	function getMaxnum(id){
		var Maxnum =  document.getElementById(id).maxLength;
		if(!Maxnum){
			document.write("无上限");
		}else{
			document.write(Maxnum);
		}
	}  

/*
 * 功能：跟linkHighlighted类似，用于恢复页面初始超链接高亮显示．
 * 用法：<a onclick="resumeLinkHighlighted()">
 * 需恢复的超链A标签的 id 命名必需以 aa_开头，超链接id名称不能重复。
 * 
 */
 function resumeLinkHighlighted(){
	var linka=document.getElementsByTagName('A');
	for(var i=0;i<linka.length;i++){
		if(linka[i].id.indexOf('aa_')!=-1){
			linka[i].style.color = '#000066';
		}
	}
 }
/*
 * 2006/10/16
 * 功能：取消当前页面所有dTree高亮显示和当前选中项．
 * 用法：<a onclick="unSelectedAlldTree()">
 * 
 */
 function unSelectedAlldTree(dTreeObj){
 	var linka=document.getElementsByTagName('a');
	for(var i=0;i<linka.length;i++){
		if(linka[i].id.indexOf(dTreeObj)!=-1){
			linka[i].className = "node";
			eval(dTreeObj+'.selectedNode = null;');
		}
	}
 }
/*
 * 2006/10/23 @laukin
 * 功能：url重定向。
 * 用法：<a onclick="urlDispatch('index.html')">首页</a>
 */
function urlDispatch(urlString) {
	document.location.href = urlString;
}

/*
 * 2006/10/23 @laukin
 * 功能：判断表单元素是否修改，改变不同的样式。
 *
 */

 function isChanged(obj) {
	var objType = obj.type;
	if (obj.parentNode.className != "isChanged" && !obj.parentNode.oldClassName) {
		obj.parentNode.oldClassName = obj.parentNode.className;
	}
	switch (objType) {
		case 'text' :
				if (obj.value != obj.defaultValue) {
					obj.parentNode.className="isChanged";
				} else if (obj.parentNode.oldClassName){
					obj.parentNode.className=obj.parentNode.oldClassName;	
				}
				break;
		case 'select-one' :
				for (var i = 0; i < obj.length; i++) {
				      if (obj.options[i].defaultSelected == true && i == obj.selectedIndex) {
					  		if (obj.parentNode.oldClassName) {
					        	obj.parentNode.className=obj.parentNode.oldClassName;
					        	break;
							}
				      } else {
				      		obj.parentNode.className="isChanged";
				      		break;
				      }
				}
				break;
	}

 }
 

function checkCompareDate(big_id,small_id){

	var big_Date = document.getElementById(big_id).value;
	var big_Name = document.getElementById(big_id).dispName;
	
	var small_Date = document.getElementById(small_id).value;
	var small_Name = document.getElementById(small_id).dispName;
	
	if(big_Date){
		if(big_Date<small_Date){
			return ("[" + big_Name+"] 不能小于 ["+small_Name + "]");	
		}
	}else{
		return;
	}
}

function checkCompareDateArrays(big_Name,small_Name){
	var big_DateArray = document.getElementsByName(big_Name);	
	var small_DateArray = document.getElementsByName(small_Name);
	
	var alertMessage;
//	alert(big_DateArray + "--------" + small_DateArray);
//	alert(big_DateArray && small_DateArray);
//		alert("2 " + small_DateArray[0]);
	if (big_DateArray && small_DateArray) { 
		//		alert(big_DateArray.length);
		alertMessage="";
		for(var i=0; i<big_DateArray.length; i++) {
			
			var isTrue = big_DateArray[i].value < small_DateArray[i].value;		
			if(isTrue){		
				alertMessage += "第" + (i + 1) + "条【" + big_DateArray[i].dispName + "】不能小于【" + small_DateArray[i].dispName + "】</br>";
			}
		}
	}	
	if (alertMessage) {
		return alertMessage;
	}	
}

function checkCompareDateArraysByValue(small_value, big_value, i, dispStr){
	var alertMessage = "";
	
	if (big_value && small_value) { 
		var isTrue = big_value < small_value;
		if(isTrue){
	    	if(null != i && "" != i){
	    		alertMessage += dispStr + ":第" + i + "条【结束时间】不能小于【开始时间】</br>";
	    	} else {
	    		alertMessage += dispStr + ":【结束时间】不能小于【开始时间】</br>";
	    	}
		}
	}

	return alertMessage;
}

function checkCompareDatenoteqel(big_id,small_id){

	var big_Date = document.getElementById(big_id).value;
	var big_Name = document.getElementById(big_id).dispName;
	
	var small_Date = document.getElementById(small_id).value;
	var small_Name = document.getElementById(small_id).dispName;
	
	if(big_Date){
		if(big_Date==small_Date){
			return (big_Name+"不能等于"+small_Name);	
		}
		if(big_Date < small_Date){
			return (big_Name+"不能小于"+small_Name);	
		}
	}else{
		return;
	}
}

/**
 * 用来判断何种状态下，可以进行操作！
 * serviceName:操作名；
 * selectObjId:复选框id；
 * canNotStatus：不可操作的状态；
 * 注：使用此方法必须在页面selectObjId复选框增加自定义属性：currentStatus。存放当前状态。
 * 	
 */
function isCanOperate(serviceName, selectObjId, canNotStatus){
	alertString = checkIsExist(selectObjId);    		
//	alert(alertString);
	if (alertString) {
		return alertString;
	} else {
		alertString = "";
		objectArray = document.getElementsByName(selectObjId);
//		alert(objectArray.length);
		var selectObj;
		for(i=0; i<objectArray.length; i++){
			if(objectArray[i].checked){
//				alert(objectArray[i].currentStatus);
				statusHidden = objectArray[i].currentStatus;						
				if(statusHidden == canNotStatus) {
//					alert(statusHidden);
					alertString += "第" + (i+1) + "条记录，归档状态不符合进行【" + serviceName + "】操作的条件，请核查！<br>";
				}
			}
		}
	}
	return alertString;
}


/**
 * only use for checkbox
 */
function onlySelect(serviceName, selectObjName, quantity){
	alertString = checkIsExist(selectObjName);    		
	if (alertString) {
		return alertString;		
	} else {
		var checkBoxArray = document.getElementsByName(selectObjName);
			
		for(var index=0, count=0; index<checkBoxArray.length; index++) {
			if (checkBoxArray[index].checked) {
				count++;
			}
			if (count > quantity) {
				return "最多只可选择" + quantity + "条记录进行【" + serviceName + "】操作。";
			}
		}
	}
}

function checkReport(serviceName, selectObjName){
	var checkBoxArray = document.getElementsByName(selectObjName);
	for(i=0; i<objectArray.length; i++){
		if(objectArray[i].checked){
			statusText = objectArray[i].statusText;
			if(statusText != null && statusText != ""){
				return statusText;
			}else{
				return null;
			}
		}
	}
}
	
//控制光标选中光标后一字符
function objFocus(n){
	click();
    if (arguments.length == 0) var n=0;
    var e = window.event.srcElement;
    var r = e.createTextRange();
    r.moveStart('character', n);
    r.collapse(true);
    r.select();
    var s = document.selection.createRange().duplicate().duplicate();
    s.moveStart('character', 1);
    s.setEndPoint("EndToEnd", r);
    s.select();
}

 function returnFalseResult(){
 	return false;
 }

 function pipeKeyPress(){
 	return keyPress()
 }

//手工输入日期
function keyPress(){
	var e= window.event.srcElement;;
    var k       = String.fromCharCode(window.event.keyCode);
    if(!/^\d/.test(k)) return false; //不准输入非法字符

    e.focus();
    var range   = e.createTextRange();
    var select1 = document.selection.createRange().duplicate();
    select1.setEndPoint("StartToStart",range);

    var s1      = select1.text;                 //得到光标左边的字串
    var s2      = e.value.substr(s1.length);    //得到光标右边的字串
    e.value     = s1.substring(0, s1.length-1) + k + s2; //给文本框赋值

    var n       = s1.length;
    if (n==4 || n==7 || n==10 || n==13) n++;
    objFocus(n);

    window.event.keyCode = 0;
    event.returnValue = false;
}
function pipekeyDown(){
 	return keyDown()
}
function keyDown()  //删除回退按方向键时的处理
{
	var e= window.event.srcElement;;
    var k   = window.event.keyCode;
    if (!(k>=48 && k<=57    //数字0-9
    	||k>=96 && k<=105   //小键盘数字0-9
        || k==46            //删除键 Del
        || k==8             //回删链 Backspace
        || k==37            //方向链 ←
        || k==39            //方向键 →
        || k==9             //制表键 Tab
        || k==13            //回车键 Enter
        )) return false;    //屏蔽非上面所列的键
        e.focus();

    var range   = e.createTextRange();
    var select1 = document.selection.createRange().duplicate();
    select1.setEndPoint("StartToStart",range);

    var s1      = select1.text;                 //得到光标左边的字串
    var s2      = e.value.substr(s1.length);    //得到光标右边的字串
    var n       = s1.length;

    switch(k)
    {
        case 8 :
            e.value = s1.substring(0, n-1) + "0" + s2;
            if (n==6 || n==9 || n==12 || n==15) n--;
            objFocus(n-2);
            window.event.keyCode = 0;
            event.returnValue = false;
            break;
        case 46 :
            e.value = s1.substring(0, n-1) + "0" + s2;
            if (n==4 || n==7 || n==10 || n==13) n++;
            objFocus(n);
            window.event.keyCode = 0;
            event.returnValue = false;
            break;
        case 37 :
            if (n==6 || n==9 || n==12 || n==15) n--;
            if (n == e.value.length)
            {
                if (document.selection.createRange().text == "")
                    objFocus(n-1);
                else
                 objFocus(n - 2);
            }
            else
                objFocus(n - 2);
            window.event.keyCode = 0;
            event.returnValue = false;
            break;
        case 39 :
            if (n==4 || n==7 || n==10 || n==13) n++;
            objFocus(n);
            window.event.keyCode = 0;
            event.returnValue = false;
            break;
    }
}

function click(){  //点击 input时附上日期默认值
		var obj = window.event.srcElement;
		if(obj&&obj.value==""){
			obj.value=getDateRule();
		    objFocus(0);
		}
	}
/**
*	判断如果 输入的日期为0000/00/00 和默认值相等，或者不符合0000/00/00格式，那么清空内容。
*/

function clearThis(){
		var obj = window.event.srcElement;
		var rul = /^(\d{4})\/(\d{2})\/(\d{2})$/;  //如果更改 格式"0000/00/00" 则需要更改此正则表达式
		if(obj.value.match(rul)){
			if(obj.value==getDateRule()){
				obj.value="";
			}
		}else{
			obj.value="";
		}
}
function getDateRule(){
	return "0000/00/00";
}

function copyTemplate(target, source){
	var spanArray = document.getElementsByTagName(source);
	var dynamicDataObj = parent.document.getElementById(target);
	for (i=0; i<spanArray.length; i++) {
//  			alert(spanArray[i].innerHTML);
//				alert(spanArray.length);
		var trObj = dynamicDataObj.insertRow();
		var tdObj = trObj.insertCell();
		tdObj.innerHTML = spanArray[i].innerHTML;
	}
//	alert(window.parent.processCalendar);
	if (window.parent && window.parent.processCalendar) {
			window.parent.processCalendar();
	}
}
//----------------------------------------------------------------------------------------------------------------
function makeWindowFull(){	
	window.moveTo(0, 0);
	window.resizeTo(screen.width, screen.height - 30);
}


/* 打开一个新的窗口 */
function newWindow(page,condition) {
    window.open(page, '选择',condition);
    return;
}

/* 当前页面打开新网址，全屏展示 add by tony 20100317 */
function openWindowFull(url){
	top.moveTo(0, 0);
	top.resizeTo(screen.width, screen.height - 30);
	top.location = url;
}
function openNewWindowFull(url){
	window.target = "_blank";
	top.moveTo(0, 0);
	top.resizeTo(screen.width, screen.height - 30);
	top.location = url;
}

function gotoUrl( url , target ){
	window.target = target;
	target.src = url;
}

/* 打开一个新页面：调用时不加第二个参数 add by tony */
function formSubmit (url,sTarget){
    document.forms[0].target = sTarget
    document.forms[0].action = url;
    document.forms[0].submit();
    return true;
}

function selectToInput(selectObj) {
	var value = selectObj.options[selectObj.selectedIndex].value;
//	alert(selectObj.parentElement.firstChild.tagName);
	selectObj.parentElement.firstChild.value=value;
}


/**---isCanSign----
 *---Oscar---2007-08-02---------
 * 判断是否可以进行签订合同操作
 * serviceName:操作名；
 * selectObjId:单选框id；
 * canNotStatus：不可操作的状态；
 * 注：使用此方法必须在页面selectObjId复选框增加自定义属性：currentStatus。存放当前状态。
 * 	
 */
function isCanSign(serviceName, selectObjId, canNotStatus){
	alertString = checkIsExist(selectObjId);
	if (alertString) {
		return alertString;
	} else {
		alertString = "";
		objectArray = document.getElementsByName(selectObjId);
		var selectObj;
		for(i=0; i<objectArray.length; i++){
			if(objectArray[i].checked){
				statusHidden = objectArray[i].currentStatus;						
				if(statusHidden == canNotStatus) {
					alertString += "第" + (i+1) + "条记录，合同签订状态不符合进行【" + serviceName + "】操作的条件，请核查！<br>";
				}
			}
		}
	}
	return alertString;
}


/**---isArchLocle----
 *---Oscar---2007-08-08---------
 * 判断是否可以进行签订合同操作
 * serviceName:操作名；
 * selectObjId:单选框id；
 * canNotStatus：不可操作的状态；
 * 注：使用此方法必须在页面selectObjId复选框增加自定义属性：archLocle。存放当前状态。
 * 	
 */
function isArchLocle(serviceName, selectObjId, canNotStatus){
	alertString = checkIsExist(selectObjId);
	if (alertString) {
		return alertString;
	} else {
		alertString = "";
		objectArray = document.getElementsByName(selectObjId);
		var selectObj;
		for(i=0; i<objectArray.length; i++){
			if(objectArray[i].checked){
				statusHidden = objectArray[i].archLocle;						
				if(statusHidden == canNotStatus) {
					alertString += "第" + (i+1) + "条记录，档案未确定(未归档)不符合进行【" + serviceName + "】操作的条件，请核查！<br>";
				}
			}
		}
	}
	return alertString;
}

/**---isCanStop----
 *---Oscar---2007-09-24---------
 * 判断是否可以进行停用操作
 * serviceName:操作名；
 * selectObjId:单选框id；
 * canNotStatus：不可操作的状态；
 **/
function isCanStop(serviceName, selectObjId, canNotStatus){
	alertString = checkIsExist(selectObjId);    		
	if (alertString) {
		return alertString;
	} else {
		alertString = "";
		objectArray = document.getElementsByName(selectObjId);
		var selectObj;
		for(i=0; i<objectArray.length; i++){
			if(objectArray[i].checked){
				statusHidden = objectArray[i].currentStatus;						
				if(statusHidden == canNotStatus) {
					alertString += "第" + (i+1) + "条记录，人员信息状态不符合进行【" + serviceName + "】操作的条件，请核查！<br>";
				}
			}
		}
	}
	return alertString;
}


/**-----ifCanOpt-----
 *------LiuFan--------------
 * 判断是否可以进行[修改、删除]操作
 * serviceName:         操作名；
 * selectObjId:       单选框id；
 * stateAble：     可以操作的状态；
 **/
function ifCanOpt(serviceName, selectObjId, stateAble){

	errorMessage = checkIsExist(selectObjId);    		
	if (errorMessage) {
		return errorMessage;
	} else {
		errorMessage = "";
		objectArray = document.getElementsByName(selectObjId);
		var currentStatus;
		for(i = 0; i < objectArray.length; i++){
			if(objectArray[i].checked){
			    currentStatus = objectArray[i].currentStatus;
			    if(currentStatus != stateAble) {
				    errorMessage = "此条工作已上报,不能进行【" + serviceName + "】操作!!!<br/>";
				}
			}
		}
	}
	return errorMessage;
}

/* 
	create by tony 2008-11-15 
	para: objName=属性所属对象; propertyName=属性名; stateAble=可通过的值; msg=出错信息;
*/
function canOpt(objName, propertyName, stateAble, msg, isCan){

	errorMessage = checkIsExist(objName);
	if (errorMessage) {
		return errorMessage;
	} else {
		errorMessage = "";
		objectArray = document.getElementsByName(objName);
		var currentStatus;
		for(i = 0; i < objectArray.length; i++){
			if(objectArray[i].checked){
			    currentStatus = eval("objectArray[i]."+propertyName);	//动态传入的属性名
			    if(isCan){
				    if(currentStatus != stateAble) {
					    errorMessage = msg;
					}
				}else{
				    if(currentStatus == stateAble) {
					    errorMessage = msg;
					}
				}
			}
		}
	}
	return errorMessage;
}

function isCanOpt(objName, propertyName, stateAble, msg){
	errorMessage = canOpt(objName, propertyName, stateAble, msg, true);
	return errorMessage;
}
function isDontCanOpt(objName, propertyName, stateAble, msg){
	errorMessage = canOpt(objName, propertyName, stateAble, msg, false);
	return errorMessage;
}




/***
 * 2008-12-04  Oscar
 * 由状态判断是否能进行操作
***/
function isCanEdit(serviceName, selectObjId, canStatus){
	alertString = checkIsExist(selectObjId);    		
	if (alertString) {
		return alertString;
	} else {
		alertString = "";
		objectArray = document.getElementsByName(selectObjId);
		var selectObj;
		for(i=0; i<objectArray.length; i++){
			if(objectArray[i].checked){
				statusHidden = objectArray[i].statusLocked;						
				if(statusHidden == canStatus) {
					alertString += "第" + (i+1) + "条工作任务是由计划生成，不能进行【" + serviceName + "】操作！<br>";
				}
			}
		}
	}
	return alertString;
}

// 调整编辑器的大小
function sizeChange(name,size){
	var obj=document.getElementById(name);
	obj.style.height = (parseInt(obj.style.height.replace("px","")) + size);
	if (parseInt(obj.style.height.replace("px",""))< 200 ){obj.style.height ="100%"}
}

function delHtmlTag(str) { 
	return str.replace(/<[^>]+>/g,"");	//去掉所有的html标记 
}


/*页面加载后检查是否已加入个人关注列表，如果有则添加对应样式，并取消当前鼠标事件以及A标签；
  如果未增加到关注列表，则添加对应鼠标事件；
  给指定ID的TD添加鼠标事件;
  2008.11.27 By Vicco
*/
	function CheckAtts(){
		var DarkTR = document.getElementsByTagName('td');
			for(i=0;i<DarkTR.length;i++){
				if(attentionArray.indexOf(DarkTR[i].parentNode.parentNode.parentNode.id)!=-1){
					DarkTR[i].style.background = '#dfefec url(../css/default/images/document_hot.gif) no-repeat 3px 4px';
					DarkTR[i].style.cursor = 'normal';
					DarkTR[i].title = "此项工作已加入个人关注";
					DarkTR[i].parentNode.parentNode.parentNode.style.background = "#dfefec";
					if(DarkTR[i].parentNode.tagName == 'A'){
						var DarkTDa = DarkTR[i].parentNode;
						var DarkHtml = DarkTDa.innerHTML;
						DarkTDa.removeNode(false); //删除A标签；
						DarkTR[i].innerHTML = DarkHtml; //删除后将原有内容传回给当前TD.		
						//alert(DarkHtml);					
					}
					var TEXT = DarkTR[i].innerText;
					top.middle.switches.loading2.style.display = 'none'; /*加载完毕后，关闭LOADING图示*/
					DarkTR[i].innerHTML = TEXT + '<img style=\"margin-left:10px;\" src=\"../skin/default/images/notice.gif\">'; /*自动为已加入关注的内容后增加对应文字或图示*/
					//alert('YES');					
					}
			}
			/*右上角增加 当前加入关注列表的数目，并连接至关注列表*/
			var imptt = document.getElementById('Myimportant');
				if((attentionArray.push('${empAtt}')-1) == 0){
					//alert(attentionArray.push('${empAtt}'))
					imptt.innerHTML = '';
					}else{
					imptt.innerHTML = '<a href=\"empAttentionListAction.do\" title=\"点击查看详细列表\" >' + '<img style=\"position:relative;top:-1px;margin-right:8px;\" src=\"../skin/default/images/notice.gif\">' + "我已有" + "<span>" + "<font color=#990000>" + (attentionArray.push('${empAtt}')-2) +  "</font>" + "</span>" + "条关注的工作" + "</a>";
					}
			
	}
		
	/*定义鼠标事件*/
	function CustomMouse(obj){
		var DardTD = document.getElementsByTagName('td');
			for(i=0; i< DardTD.length;i++){
				if(attentionArray.indexOf(DardTD[i].parentNode.parentNode.parentNode.id)!=-1 || attentionArray.indexOf(DardTD[i].parentNode.id)!=-1){
					// 前面用于判断点击录入前是否存在ID，后面用于判断录入后是否存在ID（因为录入后A属性被删除）；
					DardTD[i].onclick = "";
					DardTD[i].onmouseover = "";
					DardTD[i].onmouseout = "";			
				}else{
					DardTD[i].onclick = ShowChangeTD;
					DardTD[i].onmouseover = ShowMouseOver;
					DardTD[i].onmouseout = ShowMouseOut;
					
					}
			}
	}
	
	/*定义鼠标点击事件
	 *无需判断任何条件，只做点击后的事件处理；
	 *点击后转由CheckAtts()函数处理点击后的样式；
	*/
	function ShowChangeTD(obj){	
		obj = window.event.srcElement;
			if(obj.className == "CTM_row_dark_focus"){
				top.middle.switches.loading2.style.display = 'block'; /*定义点击未增加关注的TD后，弹出LOADING图示*/
				//alert(top.middle.switches.loading2);
				}
	}

	/*定义鼠标移动事件*/
	function ShowMouseOver(obj){
		obj = window.event.srcElement;
		var DarkTD = obj.parentNode;
			if(obj.className == "CTM_row_dark_focus"){
			obj.style.background = "#e5f0ee url(../css/default/images/document_add.gif) no-repeat 3px 4px";
			obj.style.cursor = "pointer";
			DarkTD.parentNode.parentNode.style.background = "#e5f0ee";
		}
	}
	
	/*定义鼠标移出事件*/
	function ShowMouseOut(obj){
		obj = window.event.srcElement;
		var DarkTD = obj.parentNode;
			if(obj.className == "CTM_row_dark_focus"){
			obj.style.background = "#eef4f3 url(../css/default/images/document.gif) no-repeat 3px 4px";
			DarkTD.parentNode.parentNode.style.background = "#eef4f3";
		}
	}
	
	/*自动截取EC_TABLE中类名为OPPP的TD中字符数，document.onload =  interceptTD(length)*/
	/*2008-10-08 by Vicco*/
	function interceptTD(elmID,length,Tag){
		if(Tag){
			var tds = document.getElementsByTagName(Tag);
		}else {
			var tds = document.getElementsByTagName('td');
		}	
		for(i=0;i<tds.length;i++){
			if(tds[i].className == elmID){
				//alert(tds[i].innerText.length);
				if(tds[i].innerText.length > length){
					var ss = tds[i].innerText.substring(0,length);
					tds[i].innerHTML = ss + '...';
				}
			}
		}
	}
	/*弹出被截取后的内容，并隐藏截取内容*/
	/*2008-10-08 by Vicco*/
	function ChangePosition(obj,length){
		obj.style.position  = 'relative';
		if(obj.firstChild.className == '3A' && obj.firstChild.innerText.length > length){
			obj.firstChild.style.visibility = 'hidden';
			obj.lastChild.style.display = 'block';
		}else{
			obj.onmouseenter = '';
			obj.onmouseleave = '';
			obj.style.position  = 'static';
		}
	}
	function rePosition(obj){
		obj.firstChild.style.visibility = 'visible';
		obj.lastChild.style.display = 'none';
		obj.style.position  = 'static';
	}
	
	/*过短的TEXTAREA进行浮动处理，并在输入时自动收缩*/
	/*2008-12-10 by Vicco*/
	function interceptTextarea(obj,Width){
		obj.parentNode.style.position = "relative";	//加入样式：父对象设为RELATIVE，当前设为ABSOLUTE，并设置PADDING值，边框颜色和左右浮动以及宽度。
		obj.style.position = "absolute";
		obj.style.background = "#fff";
		obj.style.padding = "5px";
		obj.style.border = "1px solid #e2994a";
		obj.style.lineHeight = "130%";
		obj.style.left = 0;
		obj.style.height = "100%";
			if(!Width){
				obj.style.width = document.body.offsetWidth / 3.8;
				}else{
				obj.style.width = Width;
				}
		obj.style.zIndex = 9999;
			if(!obj.previousSibling){
				//alert(obj.previousSibling.tagName);
				obj.insertAdjacentHTML("beforeBegin",'<iframe class="iframeC" style="position:absolute;z-index:9997;width:expression(this.nextSibling.offsetWidth);height:expression(this.nextSibling.offsetHeight);top:expression(this.nextSibling.offsetTop);left:expression(this.nextSibling.offsetLeft);filter:progid:DXImageTransform.Microsoft.Alpha(opacity=0);"></iframe>');
			}	//在该对象前加入IFRAME，如果已含有则略过,加入IFRAME的目的是为了遮盖SELECT选择框
		//obj.onkeyup = function(){this.scrollHeight > 10 ? this.style.height = this.scrollHeight + 'px' : ''};	//输入时如果滚动高度大于10则自动扩大到有效高度
		//alert(document.getElementById('test').innerHTML);
		}
		
	function interceptTextarea_blur(obj){
		obj.parentNode.style.position = "";	//还原当前对象（TEXTAREA）
		obj.style.position = "";
		obj.style.width = "";
		obj.style.height = 22;
		obj.style.zIndex = 1;
			var iframe = document.getElementsByTagName('iframe');
			for(i=0;i<iframe.length;i++){
				if(iframe[i].className == "iframeC"){	
					iframe[i].removeNode(false);	//删除类名为iframeC的IFRAME
				}
			}
		obj.style.border = "1px solid #369e92";
	}

/*根据鼠标点击位置确定弹出窗口位置并防止溢出屏幕
	function positionXY(elmId,h){
		var divID = document.getElementsByTagName('div');
		for(i=0;i<divID.length;i++){
			if(divID[i].id.indexOf(elmId) != -1){
				obj = window.event.srcElement;
				objTop = obj.offsetTop;
				objParent = obj.offsetParent;
					while(objParent.tagName.toUpperCase() != "BODY"){
						objTop   += objParent.offsetTop;
						objParent = objParent.offsetParent;
					}
				var ss =document.body.clientHeight - objTop;
					if(ss < h){
						divID[i].style.top = objTop - h + 'px'; //h 为鼠标点击位置距离页面底边的高；这里为如果鼠标点击位置局里底边的高小于该层（h）的高度，则该层的位置为鼠标点击位置减去次高度，即向上方弹出；
					}else{
					divID[i].style.top = objTop + 20 +'px'; //20 为鼠标点击处向下定位40像素
					}
				//alert(h);
			}
		}

		//alert(o.style.top);
	}
*/


/* add by tony reason:返回文件名 */
function getFileName( fileName ){
   try{
   		fileName = fileName.substring(fileName.lastIndexOf("/")+1);
   		return fileName;
   }catch(e){
	    return fileName;
   }
}
function getExtName( fileName ){
   try{
   		fileName = fileName.substring(fileName.lastIndexOf(".")+1);
   		return fileName;
   }catch(e){
	    return "";
   }
}

/*设置普通输入框鼠标样式事件，排除带有特殊图标的输入框，如日期等。排除选择框等INPUT
2008.11.25 by Vicco
*/
function reInput(){
var inputs = document.getElementsByTagName('input');
 for(i = 0; i < inputs.length; i++){
 	if(inputs[i].type != "checkbox" && inputs[i].type != "radio" && inputs[i].className != "hand_date" && inputs[i].className != "hand" && inputs[i].className != "hand_choose" && inputs[i].type != "button" && inputs[i].dataType != "复选框" && inputs[i].name != "addDept_txt" && inputs[i].id != "percent"){
 		inputs[i].onfocus = function(){this.style.border="1px solid #208073";this.style.background="#edfaf9"}; //激活事件边框与背景
 		inputs[i].onblur = function(){this.style.background="#fff"}; //非激活背景
 		inputs[i].onmouseover = function(){this.style.border="1px solid #e2994a"}; // 鼠标事件
 		inputs[i].onmouseout = function(){this.style.border="1px solid #369e92"};
 		}else if(inputs[i].className == "hand_date" || inputs[i].className == "hand_choose"){
	 		inputs[i].onmousedown = function(){this.style.border="1px solid #208073"}; //激活事件边框与背景
	 		inputs[i].onmouseover = function(){this.style.border="1px solid #e2994a"}; // 鼠标事件
	 		inputs[i].onmouseout = function(){this.style.border="1px solid #369e92"};
 		}
		}
	var textArea = document.getElementsByTagName('textarea');
	 for(i = 0; i < textArea.length; i++){
	 	if(textArea[i].id.indexOf("textarea") != -1){
	 		textArea[i].onfocus = function(){this.style.border="1px solid #208073"}; //激活事件边框与背景
	 		textArea[i].onmouseover = function(){this.style.border="1px solid #e2994a"}; // 鼠标事件
	 		textArea[i].onmouseout = function(){this.style.border="1px solid #369e92"};
	 	}
	 }
}

/*系统加载input样式*/
window.onload = reInput;


