function createUploadForm(fileElementId)
{
    //create form
    var form = $('<form method="POST" id="imgupload" enctype="multipart/form-data"></form>');
    //console.log(form);
    var imgElement = $('#' + fileElementId);
    form.append(imgElement);
    //console.log(form.html());
    //console.log($("#upload-image").html());//这个未定义，也不知道是什么原因,看看以后能不能解决
    ajaxfileupload(form);
}
function ajaxfileupload(form){
	var formdata = new FormData(form[0]);
	$.ajax({
		url:"upload/uploadimg.do",
		type:"POST",
		data:formdata,
		async: false,  
        cache: false,
		contentType : false,  //这个一定要写
		processData : false,   //这个一定要写
		success:function(data){
			var str = "<p><br></p><img src='./"+data+"' />";
			var strimg = "<img src='./"+data+"' />";
			restoreSelection();
 			document.execCommand("insertHTML",false,str);
 			var range = saveSelection();
 			currentRange(range);
 			wrapImg(strimg);
 			insertEmptyP();
 			restoreSelection();
		},
		error:function(){
			console.log("出错");
		}
	});
}
function downloadFileAjax(str){
	$.ajax({
		url:"upload/downloadimg.do?urlString="+str+"",
		async: false,
		success: function(data){
			var str = "<p><br></p><img src='./"+data+"' />";
			var strimg = "<img src='./"+data+"' />";
			restoreSelection();
 			document.execCommand("insertHTML",false,str);
 			var range = saveSelection();
 			currentRange(range);
 			wrapImg(strimg);
 			insertEmptyP();
 			restoreSelection();
		},
		error: function(){
			console.log("出错");
		}
	});
}