<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<base href="<%=basePath%>">
	<meta charset="UTF-8">
	<title>write</title>
	<link rel="stylesheet" href="css/write.css">
	<link rel="stylesheet" href="css/github-markdown.css">
</head>
<body>
  <div class="container">
    <div class="stack">
      <div class="write-mode">
        <div class="main">
          <form action="individual/writeblog.do" class="note-form" method="POST">
          	<input type="hidden" name="username" value="${user.username }">
            <input type="hidden" name="content" id="markdown-content">
            <input type="text" class="title" name="title" value="无标题文章">
            <div class="editor-box">
              <ul class="toolbar">
                <li><a data-role="p">P</a></li>
                <li><a data-role="bold" class="iconfont ic-bold"></a></li>
                <li><a data-role="italic" class="iconfont ic-italic"></a></li>
                <li><a data-role="strikethrough" class="iconfont ic-strikethrough"></a></li>
                <li><a data-role="blockquote" class="iconfont ic-blockquote"></a></li>
                <li><a data-role="img" class="iconfont ic-img"></a></li>
                <li><a data-role="insertorderedlist" class="iconfont ic-ol"></a></li>
                <li><a data-role="insertunorderedlist" class="iconfont ic-ul"></a></li>
                <li><a data-role="h1">H1</a></li>
                <li><a data-role="h2">H2</a></li>
                <li><a data-role="h3">H3</a></li>
                <li><a data-role="inserthorizontalrule">—</a></li>
                <li><a data-role="link" class="iconfont ic-link"></a></li>
                <li><a data-role="undo" class="iconfont ic-undo"></a></li>
                <li><a data-role="redo" class="iconfont ic-redo"></a></li>
                <li class="pull-right">
                  <a id="form-submit">
                    <i class="iconfont ic-send"></i>发布文章
                  </a>
                </li>
              </ul>
              <div class="markdown-body" id="markdown-body" contenteditable="true">
                <p><br></p>
              </div>
            </div>
            <div class="image-modal">
              <div class="modal-header"><h2>插入图片</h2></div>
              <div class="modal-body">
                <div class="tab-btn"> 
                  <a class="btn-link" href="#image-upload">本地上传</a> 或
                  <a class="btn-link" href="#image-external">网络图片</a> 
                </div>
                <div class="tab-content">
                  <div class="tab-pane active" id="image-upload"> 
                    <a href="" class="upload-picture">
                       <label for="upload-image">选择图片上传</label> 
                       <input id="upload-image" class="btn-upload-link" type="file" 
                       name="picture" accept="image/jpg,image/jpeg,image/png,image/gif" multiple="multiple"> 
                     </a> 
                    <button class="btn-link" id="image-cancel">取消</button> 
                  </div>
                  <div class="tab-pane" id="image-external">
                    <div class="input-prepend">
                      <span class="add-on"><i class="iconfont ic-link"></i></span>
                      <input class="span2" placeholder="图片链接" type="text">
                      <button class="btn-link" id="image-ok">确认</button>
                      <button class="btn-link" id="image-cancel">取消</button>
                    </div>
                  </div>
                </div>
              </div>
            </div>
             <div class="link-modal">
              <div class="modal-header"><h2>插入链接</h2></div>
              <div class="modal-body">
                  <div class="input-prepend">
                    <span class="link-i"><i class="iconfont ic-link"></i></span>
                    <input class="span-link" id="span-link-a" placeholder="链接地址" type="text">
                  </div>
                  <div class="input-prepend">
                    <span class="link-a">A</span>
                    <input class="span-link" id="span-link-t" placeholder="链接文本" type="text">
                  </div>
                  <button class="link-link" id="link-ok">确认</button>
                  <button class="link-link" id="link-cancel">取消</button>  
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
  <script src="js/jquery-3.0.0.js"></script>
  <script src="js/ajaxfileupload.js"></script>
  <script src="js/write.js"></script>
  <!-- 添加一个覆盖层 -->
  <div class="modal-backdrop"></div>
<body>
</html>