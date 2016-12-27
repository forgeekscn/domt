/* created by tony 20081215
   updated by tony 20100311
   option: 实现表格的动态新增、删除、行位置交换、行可上下拖动 
    固定内容：第二列为排序列,删除ID记录框 delIds
*/

/* 单元格内容不够长时,方便看单元格的内容 */
function showTheWrite(str){
   	document.getElementById("div_write").innerText = str;
}  
   	    	
/* 实现表格序号列自动调整 created by tony 20081219 */
function sortnoTR(){
	sortno('resultTable', 2, 1);
}

/* 清除表格行的高亮 filterName 表格含有这个名称的,才进行处理 */
function clearTRstyle( filterName ){
	var tableobjs = document.getElementsByTagName("TR");
	for(var i=0;i<tableobjs.length;i++){
		if((tableobjs[i].parentNode.parentNode.id).toString().indexOf(filterName)!=-1){
			tableobjs[i].style.background = '';		//清空背景
		}
	}
}

/* 设置单元格的文字 by tony 20091110 
	paras: obj colNo:要填写值的列号; value:设定的值;
*/	    
function setTDText( obj, colNo, value ){
	var currTr = obj.parentElement.parentElement;
	//alert(currTr.getElementsByTagName("TD")[colNo].innerText);
	currTr.getElementsByTagName("TD")[colNo].innerText = value;
}

/* 设置下拉框的值 by tony 20100110
	paras: obj curValue:当前的值;
*/	    
function setSelectOption(obj, curValue ){
	for (i=0;i<obj.length;i++){
		if(obj.options[i].value == curValue){
			obj.options[i].selected=true;
			break; 
		}
	} 
}

/* 设置此行是否更新 by tony 20091110 
*/	    
function setTRUpdateFlag( obj ){
	//alert(obj.type);
	//alert(obj.defaultValue);
	var currTr = obj.parentElement.parentElement;
	if(currTr.innerHTML.toLowerCase().indexOf("<span")==0){
		currTr = obj.parentElement.parentElement.parentElement;
	}
	//alert(currTr.innerHTML);
	if(obj.value!=obj.defaultValue){	//当填写的框内容发生变化时,设置本行记录发生变化标识
		currTr.cells[1].all.mr_changed.value = "1";
	}
	//currTr.cells[1].document.getElementsByTagName("INPUT")[4].value = "1";
	//alert(currTr.cells[1].all.mr_changed.value);
	//alert(currTr.cells[1].document.getElementsByTagName("INPUT")[4].value);
	//alert(currTr.cells[2].document.getElementsByTagName("INPUT").value);
	//currTr.getElementsByTagName("TD").all.mr_changed = "1";.document.getElementsByTagName("INPUT")
}


/* 设置此行是否更新 by tony 20120125 reason:按上面判断，有时无法触发，所以直接至标志；例如日期框
*/	    
function setTRUpdateFlagTrue( obj ){
	var currTr = obj.parentElement.parentElement;
	if(currTr.innerHTML.toLowerCase().indexOf("<span")==0){
		currTr = obj.parentElement.parentElement.parentElement;
	}
	currTr.cells[1].all.mr_changed.value = "1";	//当填写的框内容发生变化时,设置本行记录发生变化标识
}

/* 新增一行 */
function newTRRecord(objId, isClear) {
	var _stemp = "";
	var i = 1;
	var tableObj = document.getElementById(objId);
	var rowLength = tableObj.rows.length;
	if(rowLength<=1){		//只有标题则不新增, 默认会有一个空行
		showError("空行不能建立新行!");
		return rowLength;
	}
	var lastTr = tableObj.rows[rowLength-1];
	//alert(lastTr.rowIndex);	
    var newTR = lastTr.cloneNode(true);
    newTR.id="a" + (++i);
    lastTr.parentNode.insertAdjacentElement("beforeEnd", newTR);	//最后一行增加新行
    
    /* 判断表格行中含有input框和textarea框 3层嵌套 */
    if (isClear) {
    	for (j=0; j<newTR.cells.length; j++) {
    	
    		/* 第一行加属性 */
    		newTR.cells[0].style.whiteSpace="nowrap";
			newTR.cells[0].ondragover = function(){this.className="drag_over" };	//动态加事件, 改变样式类
			newTR.cells[0].ondragleave = function(){this.className="drag_leave" };
			newTR.cells[0].onmousedown = function(){ clearTRstyle("result"); this.parentNode.style.background = '#0099cc';};	
		
    		var inputArray = newTR.cells[j].getElementsByTagName('input');
    		if (inputArray.length > 0) {
	   			for (m=0; m<inputArray.length; m++) {
	   				// add by tony 20100315 reason:由于radio框名称相同,IE会解析为同一组；所以只能对其单独处理。通过动态替换radio的框的名字，使其随表各行新增行的行号发生变化。同时又由于radio的框的名称不同，提交后台处理时，又和其他文本框等处理不同，所以在radio后加了一个隐藏域，进行独特的处理。这样就和其他input框处理方式相同。
					if(inputArray[m].type=="radio"){
						_stemp = inputArray[m].parentNode.innerHTML;
						_stemp = _stemp.replaceAll(inputArray[m].name,inputArray[m].name.substring(0,inputArray[m].name.length-1)+(rowLength-1));
						inputArray[m].parentNode.innerHTML=_stemp;
						//alert(_stemp);
					}else{
		   				inputArray[m].value = "";			//clear value
					}
	   			}
   			} else {
	    		var textareaArray = newTR.cells[j].getElementsByTagName('textarea');
	    		if (textareaArray.length > 0) {
		   			for (m=0; m<textareaArray.length; m++) {
		   				textareaArray[m].value = "";
		   			}
	   			} else {
	   				var selectArray = newTR.cells[j].getElementsByTagName('select');
		    		if (selectArray.length > 0) {
			   			for (m=0; m<selectArray.length; m++) {
			   				selectArray[m].selected = 0;
			   			}
		   			} else {
		   				newTR.cells[j].innerHTML = "";
		   			}
	   			}
   			}
    	}
    }
//    alert(tableObj.rows.length);
    try {
	    document.getElementsByName('number')[i-1].value = i;
	} catch (e){
	}
	
	return tableObj.rows.length;
}

/* 记录删除的记录 creaded by tony 20091110 */
function delIdsRecord(ckName) {  
	var delIds = "";delIds = "";
	
	var delCheckboxs = document.getElementsByName(ckName);
	for(var i=0;i<delCheckboxs.length;i++){
		if(delCheckboxs[i].checked){
			if(delCheckboxs[i].value!=""){
				delIds += delCheckboxs[i].value + ",";
			}										
		}
	}
	document.getElementById("delIds").value = delIds;
}
   
/* 清除删除框 */
function clearDelId(){
   	document.getElementById("delIds").value = "";
}
   
/* 删除空行 add by tony 20100318 */
function delSpaceTRRecord(objId, topRow){
	var bEmpty = false;
	var tableObj = document.getElementById(objId);
	var rowLength = tableObj.rows.length;
    for(i=rowLength-1; i>=topRow; i--){		//必须倒序，否则当前的删除，obj就少了一条，到最后一条时就会报错
   		bEmpty = false;
		var curTr = tableObj.rows[i];
	   	for (j=3; j<curTr.cells.length; j++) {								//j=3跳过控件列，从业务列开始处理
	   		var inputArray = curTr.cells[j].getElementsByTagName('input');	//只需判断是否所有input框都未填写，都未填写就认为此行无用，废弃
	   		if (inputArray.length > 0) {
	   			for (n=0; n<inputArray.length; n++) {
	   				if(inputArray[n].value==""){
	   				 	bEmpty = true;										//有一个填写了，就不用再判断了
	   				}else{
	   					bEmpty = false;
	   				}
	   			}
	   			if(!bEmpty){
	   				break;
	   			}
	   		}
	   		var textareaArray = curTr.cells[j].getElementsByTagName('textarea');	//只需判断是否所有textarea框都未填写，都未填写就认为此行无用，废弃
	   		if (textareaArray.length > 0) {
	   			for (n=0; n<textareaArray.length; n++) {
	   				if(textareaArray[n].value==""){
	   				 	bEmpty = true;										//有一个填写了，就不用再判断了
	   				}else{
	   					bEmpty = false;
	   				}
	   			}
	   			if(!bEmpty){
	   				break;
	   			}
	   		}
	   	}
	    if (bEmpty) {
	    	tableObj.deleteRow(i);
	    }
	}
}

/* 删除一行 */
function delTRRecord(ckName, tableId, topRow, clearValueObjName ,isDeleteAll) {  
	var clearValuesObjs = document.getElementsByName(clearValueObjName);
	var tableObj = document.getElementById(tableId).tBodies[0];
	var trTotal = tableObj.rows.length - topRow;	//除去表格头topRow后行总数
	
	var mr_changeds = document.getElementsByName("mr_changed");		
	var is_change = false;	//是否发生变化，如果单独增加了几个空行，就删除，可以根据此标识，不进行删除提示
	
	var delCheckboxs = document.getElementsByName(ckName);
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
			if(mr_changeds[i].value=="1"){
				is_change = true;
			}
		}
	}else{
		alert("对不起,请至少选择一条需要移除的数据");
		return false;
	}
	
	if(delNumber.length <= 0){
		alert("对不起,请至少选择一条需要移除的数据");
		return false;
	}
	
	if(is_change){
		if(!confirm("确实要移除指定内容吗?单击【确定】将移除指定内容！单击【取消】将终止移除操作!")){
			return false;
		}
	}
	
	if(trTotal==delNumber.length){			//如果是全部删除,则先新增一个空行,否则newTRRecord就成了增加表头。
		newTRRecord('resultTable', true);	//删除前，新增一个空行。
	}
	
	for(var i=0;i<delNumber.length;i++){
		if(isDeleteAll == false){
			if (i + 1 >= trTotal) {
				break;
			}		
		}
		tableObj.deleteRow(delNumber[i] - i);
	}
}

/* 清空:删除所有 by tony 20091112 */
function clearTRRecord(ckName, tableId, topRow) {  
	var tableObj = document.getElementById(tableId).tBodies[0];
	var delNumber = tableObj.rows.length - topRow;
		for(var i=delNumber;i>topRow-1;i--){
			tableObj.deleteRow(i);
		}
}

/* by tony 20100212 最后一行为空，则删除。reason:如果最后一行为空在删除,这样就不会因_CheckAll监测最后一行为空而提示未填写了 */
function doLastTR(objId) {
	var bEmpty = true;
	var tableObj = document.getElementById(objId);
	var rowLength = tableObj.rows.length;
	var lastTr = tableObj.rows[rowLength-1];
    
   	for (j=3; j<lastTr.cells.length; j++) {								//j=3跳过控件列，从业务列开始处理
   		var inputArray = lastTr.cells[j].getElementsByTagName('input');	//只需判断是否所有input框都未填写，都未填写就认为此行无用，废弃
   		if (inputArray.length > 0) {
   			for (n=0; n<inputArray.length; n++) {
   				if(inputArray[n].value!=""){
   				 	bEmpty = false;										//有一个填写了，就不用再判断了
   				 	break;
   				}
   			}
   		}
   	}
    if (bEmpty) {
    	tableObj.deleteRow(rowLength-1);	//删除最后一行
    }
}


/* 移到 */
function swapTRRecord(ckName, tableId, topRow, Flag) {  
	var tableObj = document.getElementById(tableId).tBodies[0];
	var trTotal = tableObj.rows.length - topRow;	//除去表格头topRow后行总数
	
	var selCheckboxs = document.getElementsByName(ckName);
	var selNumber = -1;	//选中的checkbox的个数
	var curRow = -1; 	//选中第几个checkbox
	if(selCheckboxs.length > 0){
		selNumber = 0;
		var rowLength = selCheckboxs.length;
		for(var i=0;i<rowLength;i++){
			if(selCheckboxs[i].checked){
				selNumber++;
				curRow = i+1;	//选中的记录
				break;			//只要选择的第一条	
			}
		}
	}
	if(selNumber==-1){
		//忽略,没有选择要移动的记录
	}else if(selNumber==0){
		alert("请至少选择一条需要移动的记录!");
		return false;
	}else if(selNumber==0){
		alert("请至少选择一条需要移动的记录!");
		return false;
	}else if(selNumber==1){
		try{
			if(Flag=="up"){
				if(topRow!=curRow){
			  		trSwap_Up(tableId, curRow);	//上移
			  		document.getElementsByName(ckName)[curRow-2].checked = true;
			  		mcobj = document.getElementsByName("mr_changed")[curRow-2];
			  		if(mcobj!=undefined){
			  			mcobj.value="1";		//移动,记录排序号发生变化
			  			mcobj = document.getElementsByName("mr_changed")[curRow-1];
				  		if(mcobj!=undefined){
				  			mcobj.value="1";	//移动,记录排序号发生变化
				  		}
			  		}
			  	}
			}else if(Flag=="dn"){
				trSwap_Down(tableId, curRow);	//下移
				document.getElementsByName(ckName)[curRow].checked = true;
				mcobj = document.getElementsByName("mr_changed")[curRow];
			  		if(mcobj!=undefined){
			  			mcobj.value="1";		//移动,记录排序号发生变化
			  			mcobj = document.getElementsByName("mr_changed")[curRow-1];
				  		if(mcobj!=undefined){
				  			mcobj.value="1";	//移动,记录排序号发生变化
				  		}
			  		}
			}
		}catch(err){
			//忽略错误
		}
	}
	return true;
}

/* 重新排列序号列值 */
function sortno(objId, whichCol, topRow){
	var tableObj = document.getElementById(objId);
	var trTotal = tableObj.rows.length - topRow;	//除去表格头topRow后行总数
	for (m=topRow; m<=trTotal; m++) {
		var curTr = tableObj.rows[m];
		var inputArray = curTr.cells[whichCol].getElementsByTagName('input');
   		if (inputArray.length > 0) {
   			for (n=0; n<inputArray.length; n++) {
   				inputArray[n].value = m;
   			}
   		}
	}
}

/* 交换表格行 */
function trSwap(tableId, tr1Index, tr2Index){
	try{
		var tableObj = document.getElementById(tableId);
		tableObj.rows[tr1Index].swapNode(tableObj.rows[tr2Index]);
	}catch(err){
		//alert('trSwap出错!');
	}
}
/* 交换表格行.上移 */
function trSwap_Up(tableId, trIndex){
	trSwap(tableId,trIndex,trIndex-1);
}
/* 交换表格行.下移 */
function trSwap_Down(tableId, trIndex){
	trSwap(tableId,trIndex,trIndex+1);
}



/* 实现拖动 start */

var obj;
var xx=0,yy=0;
var tagobj;
var dragobj;
function dragtableinit(){
	var tableobjs = document.getElementsByTagName("TR");
	for(var i=0;i<tableobjs.length;i++){
		if((tableobjs[i].parentNode.parentNode.id).toString().indexOf("result")!=-1){
			tableobjs[i].onmousedown=mousedown;
			tableobjs[i].ondragover=dragover;
			tableobjs[i].ondragend=dragend;
			tableobjs[i].ondrag=dragmove;
			//tableobjs[i].style.position="relative";	//有这个则拖动时显示拖动的行,但与textarea的展现相冲突,所以屏蔽
			//tableobjs[i].style.zIndex=1;
		}
	}
}
function mousedown(){
	obj = event.srcElement;
	if(obj.tagName=="TD") obj=obj.parentNode;
	if(obj.tagName!="TR") return false;
	if(obj.rowIndex==0) return false;
	yy=event.clientY;
	xx=event.clientX;
	obj.style.zIndex=0;
	try{
		obj.dragDrop(); 
	}catch(e){
	}
}
function dragmove(){
	obj.style.top = event.clientY-yy;
	obj.style.left = event.clientX-xx;
	obj.style.cursor = 'move';
}
function dragover(){
	tagobj=event.srcElement;
	if(tagobj.tagName=="TD"){tagobj=tagobj.parentNode;}
	if(tagobj.tagName!="TR")return false;

}
function dragend( ){
	var iTargetPosition = 0;
	obj.style.cursor = 'move';
	obj.style.top=0;
	obj.style.left=0;
	obj.style.zIndex=1;
	if(tagobj!=null && tagobj.rowIndex!=0){
		var t1 = resultTable.rows[obj.rowIndex];
		iTargetPosition = tagobj.rowIndex;
		var t2 = resultTable.rows[iTargetPosition];
		try{
			resultTable.getElementsByTagName('tbody')[0].insertBefore(t1,t2);
		} catch (e){
		}
		tagobj.style.zIndex=1;
	}
	obj.style.cursor = '';
	tagobj=null;
	
	sortnoTR();	//拖动完重新排序
}

/* 实现拖动 end   */

/* 实现加亮 start */
    function ShowFloatDiv2(obj,num,length){
	    for(var id = 1; id<=length; id++){
		    objMain = obj + id;
		    objss = obj + '_own' + id;
		    objother = obj + '_visibility' + id;
			if(id == num && objMain){
	    	try{document.getElementById(objMain).style.display = "none";}catch(e){};
	    	try{document.getElementById(objss).style.display = "none";}catch(e){};
	    	try{document.getElementById(objother).style.display = "block";}catch(e){};
	    	}
	    }
	}

    function HideFloatDiv2(obj,num,length){
	    for(var id = 1; id<=length; id++){
		    objMain = obj + id;
		    objss = obj + '_own' + id;
		    objother = obj + '_visibility' + id;
			if(id == num){
	    	try{document.getElementById(objMain).style.display = "block";}catch(e){};
	    	try{document.getElementById(objss).style.display = "block";}catch(e){};
	    	try{document.getElementById(objother).style.display = "none";}catch(e){};
	    	}
	    }
	}

	function HightLightRows(obj){
		//alert(obj.parentNode.parentNode.tagName);
		var Rows = obj.parentNode.parentNode;
		if(Rows.className == 'rowList_main'){
			Rows.className = 'rowList_main_bg';
		}
	}
/* 实现加亮 end   */
