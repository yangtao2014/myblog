$(function(){
	$(".width-limit .user").mouseenter(function(){
		$(".width-limit .dropdown-menu").css("display","block");
	});
	$(".width-limit .user").mouseleave(function(){
		$(".width-limit .dropdown-menu").css("display","none");
	});
});