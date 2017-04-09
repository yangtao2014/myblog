/*
 * 滚动分页js文件，总共滚动三下，要想看新内容，三次后就只有点击加载文章按钮了
 * 当浏览器滚动条到达一定位置时，就通过ajax访问服务器，获得文章内容
 */

$(function () {
	var datapage = 1;
	var bot = 700;
	var backtop = 216;
	$(window).scroll(function () {
		if($(window).scrollTop() >= backtop){
			$(".side-tool").css("display","block");
		}else{
			$(".side-tool").css("display","none");
		}
		
        if((bot + $(window).scrollTop()) >= ($(document).height() - $(window).height())) {
          if(datapage < 3){
        	  datapage = datapage + 1; 
        		if(datapage == 3){
        			$(".load-more").css("display","block");
        		}
          		$.ajax({
          			url:"individual/showmore.do",
          			data: {page:datapage-1},
          			async: false,
          			success:function(data){
          				$(data).each(function(index){
          					var li = $("<li class='have-img'></li>");
          	                var wrapa = $("<a href='' class='wrap-img'></a>");
          	                var contentdiv = $("<div class='note-content'></div>");
          	                var authordiv = $("<div class='author'>"+data[index].username+"</div>");
          	                var contenta = $("<a href='' class='title'>"+data[index].title+"</a>");
          	                var contentp = $("<p class='abstract'>"+data[index].content+"</p>");
          	                var metadiv = $("<div class='meta'>"+formatDatebox(data[index].date)+"</div>");
          	                contentdiv.append(authordiv);
          	                contentdiv.append(contenta);
          	                contentdiv.append(contentp);
          	                contentdiv.append(metadiv);
          	                li.append(wrapa);
          	                li.append(contentdiv);
          	                $(".note-list").append(li);
    					});
					},
					error:function(){
          				//请求出错处理
             			alert("Error!");
          			}
          		});
          		
          }
      }
  });
	$(".load-more").click(function(){
		$(".load-more").css("display","none");
		var page = $(".load-more").data("page");
		$.ajax({
  			url:"individual/showmore.do",
  			data:{page:page++},
  			success:function(data){
  				if(data.length){
  					$(".load-more").data("page",page);
  					$(".load-more").css("display","block");
  				
	  				$(data).each(function(index){
	  					var li = $("<li class='have-img'></li>");
	  	                var wrapa = $("<a href='' class='wrap-img'></a>");
	  	                var contentdiv = $("<div class='note-content'></div>");
	  	                var authordiv = $("<div class='author'>"+data[index].username+"</div>");
	  	                var contenta = $("<a href='' class='title'>"+data[index].title+"</a>");
	  	                var contentp = $("<p class='abstract'>"+data[index].content+"</p>");
	  	                var metadiv = $("<div class='meta'>"+formatDatebox(data[index].date)+"</div>");
	  	                contentdiv.append(authordiv);
	  	                contentdiv.append(contenta);
	  	                contentdiv.append(contentp);
	  	                contentdiv.append(metadiv);
	  	                li.append(wrapa);
	  	                li.append(contentdiv);
	  	                $(".note-list").append(li);
					});
  				}else{
  					$(".load-none").css("display","block");
  				}
			},
			error:function(){
  				//请求出错处理
     			alert("Error!");
  			}
  		});
	});
});