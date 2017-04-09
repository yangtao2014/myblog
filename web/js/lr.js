window.onload = function(){
	var signup = document.getElementById('signup');
	var signin = document.getElementById('signin');
	var vsignup = document.getElementById('view-signup');
	var vsignin = document.getElementById('view-signin');
	var lrBar = document.getElementById('lr-slider-bar');
	signup.onclick = function(){
		signup.className = "active";
		signin.className = "";
		vsignup.style.display = "block";
		vsignin.style.display = "none";
		lrBar.style.left = 0;
	}
	signin.onclick = function(){
		signin.className = "active";
		signup.className = "";
		vsignup.style.display = "none";
		vsignin.style.display = "block";
		lrBar.style.left = "4em";
	}
}