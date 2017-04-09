/*这是自己以前的思路，通不过去，想着加给选中的文本加markdown语法符号
用click一直没用，改成mousedown就成功了，不知道为什么
$(".ic-bold").mousedown(function() {
		document.execCommand("bold");
	
		 if(window.getSelection().toString()) {
				 	console.log(window.getSelection());
				 	console.log(window.getSelection().getRangeAt(0).startOffset);
				 	//$(".markdown-body").html(marked("**"+window.getSelection().toString()+"**"));
				 	document.execCommand("bold",false,null);
			}else{
				 	//extendComm
			}
}*/
//保存选区，也就是光标所在位置
function saveSelection(){
      	var selection = window.getSelection();
		var range = selection.getRangeAt(0);
		return range;
}
//设置或得到range,this._rangeData只是一个变量，如果currentRange()有参，则设置值，无参时就取出值
//只是为了得到range值
function currentRange(cr){
      	if (cr) {
            this._rangeData = cr;
        } else {
            return this._rangeData;
        }
}
//恢复选区
function restoreSelection(){
      	var selection = window.getSelection();
		var range = currentRange();
    	selection.removeAllRanges();
        selection.addRange(range);
}
//在后面插入p
function insertEmptyP(){
      	$(".markdown-body").append($('<p><br></p>'));
}
//把img包裹在div里,并加入一个div说明图片
function wrapImg(strimg){
      	//$("img").wrap($('<div class="image-package"></div>'));
      	//$(".image-package").append($('<br><div class="image-caption"></div>'));
      	$("img").each(function() {
      		if($(this).parent()[0].tagName == $("p")[0].tagName){
      			$(this).parent().replaceWith("<div class='image-package'>"+strimg+"<br><div class='image-caption'></div>");
      		}
      		else if($(this).parent().attr("class") == "markdown-body"){
      			$(this).wrap($("<div class='image-package'></div>"));
      			$(this).parent().append($("<br><div class='image-caption'></div>"));
      		}
      	});
      	
}
$(function() {
	//响应工具栏
    $('.toolbar a').mousedown(function(event) {
    	//preventDefault() 方法阻止元素发生默认的行为（例如，当点击提交按钮时阻止对表单的提交）
    	//这里是为了点击按钮时，不丢失选中的div文本
    	event.preventDefault();
    	if($(".markdown-body").is(":focus")){
			switch($(this).data('role')) {
				case 'p':
					document.execCommand('formatBlock', false, '<' + $(this).data('role') + '>');
					break;
				case 'h1':
				case 'h2':
				case 'h3':
				case 'blockquote':
					document.execCommand('formatBlock', false, '<' + $(this).data('role') + '>');
					insertEmptyP();
					break;
				case 'img':
					var range;
					range = saveSelection();
					currentRange(range);
					$(".image-modal").css("display","block");
					$(".modal-backdrop").css("display","block");
					break;
				case 'link':
					var range;
					range = saveSelection();
					currentRange(range);
					$(".link-modal").css("display","block");
					$(".modal-backdrop").css("display","block");
				default:
					document.execCommand($(this).data('role'));
					break;

			}
		}
		
	});
	//切换上传图片和图片链接,和响应确定与取消按钮
	//这是加网络图片的，上传图片不在这里
	$(".image-modal .btn-link").click(function(event) {
		event.preventDefault();
		switch($(this).attr('href')){
			case '#image-upload':
				$("#image-upload").addClass("active");
				$("#image-external").removeClass("active");
				break;
			case '#image-external':
				$("#image-upload").removeClass("active");
				$("#image-external").addClass("active");

				break;
			default:break;
		}
		switch($(this).attr('id')){
			case 'image-ok':
				if($(".span2").val()!=""){
					//$(".markdown-body").focus();

					/*//其实好像range可以做这个，但是我没学过，不会，等以后补一下
					//document.execCommand 原理我不懂，执行时会把所在行的标签替换掉，然后换行也时执行的标签
					//可编辑div默认换行是div标签，然后根据所在行的标签换行成所在行的标签
					//本想着执行execCommand时在下行加个p标签，但没成功，只能手动加个p标签按钮了
					//插入一张还好，插入两张时，失败，会把第二张插到第一张上面
					document.execCommand("insertHTML","","<p><br></p><div class='image-package'><br><div class='image-caption'></div></div>");
					document.execCommand("insertimage",false,$(".span2").val());
					document.execCommand("insertHTML","","<p><br></p>");
					$(".image-package img").insertBefore($(".image-package br"));
					//后来发现是我的光标定位问题，先放下这个，以后再改
					/*document.execCommand("formatBlock",false,"<div>");
					document.execCommand("insertimage",false,$(".span2").val());
					$(".markdown-body img").parent().addClass('image-package');*/
					downloadFileAjax($(".span2").val());

					$(".span2").val("");
					$(".image-modal").hide();
					$(".modal-backdrop").hide();
					$("#image-upload").attr("class","tab-pane active");
					$("#image-external").attr("class","tab-pane");
				}
				break;
			case 'image-cancel':
				$(".image-modal").css("display","none");
				$(".modal-backdrop").css("display","none");
				$("#image-upload").addClass("active");
				$("#image-external").removeClass("active");
				break;
			default:break;
		}
	});
	//文字链接
	$(".link-modal .link-link").click(function(event){
		event.preventDefault();
		switch($(this).attr('id')){
			case 'link-ok':
				//$(".markdown-body").focus();
				if($("#span-link-t").val()!=""&&$('span-link-a').val()!=""){
					restoreSelection();
         			document.execCommand("insertHTML",false,"<a href="+$('#span-link-a').val()+" target='_blank'>"+$("#span-link-t").val()+"</a>");
				
					$("#span-link-t").val("");
					$("#span-link-a").val("");
					$(".link-modal").hide();
					$(".modal-backdrop").hide();
					$("#image-upload").attr("class","tab-pane active");
					$("#image-external").attr("class","tab-pane");
				}
				break;
			case 'link-cancel':
				$(".link-modal").css("display","none");
				$(".modal-backdrop").css("display","none");
				$("#image-upload").addClass("active");
				$("#image-external").removeClass("active");
				break;
			default:break;
		}
	});
	//取消按钮
	$(".modal-backdrop").click(function(event) {
		$(".image-modal").css("display","none");
		$(".modal-backdrop").css("display","none");
		$("#image-upload").attr("class","tab-pane active");
		$("#image-external").attr("class","tab-pane");
	});
	//表单提交
	$("#form-submit").click(function() {
		$("#markdown-content").val($(".markdown-body").html());
    	$("form").submit();
	});
	//防止图片说明的div删除，按退格键会把它删除，所以加个监听把它加回来
	$(document).keydown(function(event) {
		var e = event || window.event;
		if(e.keyCode == 8){
			setTimeout(function(){
				//console.log(window.getSelection().getRangeAt(0).commonAncestorContainer);
				//console.log(window.getSelection().anchorNode);
				var captiondiv = window.getSelection().getRangeAt(0).commonAncestorContainer;
				//console.log(captiondiv.nodeType);
				//console.log($(".image-package").children('br'));
				//console.log($(".image-package").children('.image-caption').length);
				if(captiondiv.nodeType != 3){
					$(".image-package").children('br:last-child').replaceWith("<div class='image-caption'></div>");
				}
			},1500);
		}
	});
	//上传图片
	$(".upload-picture").change(function(event) {
		console.log("ok");
		createUploadForm("upload-image");
		$(".image-modal").hide();
		$(".modal-backdrop").hide();
		$("#image-upload").addClass("active");
		$("#image-external").removeClass("active");
		var inputfile = $("<input id='upload-image' class='btn-upload-link' type='file' name='picture' accept='image/jpg,image/jpeg,image/png,image/gif' multiple='multiple'>");
		inputfile.val("");
		$(".upload-picture").append(inputfile);
	});
	
});
