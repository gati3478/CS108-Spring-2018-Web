	// global scope
		
	// var thumbs = window.document.getElementsByTagName('a');      // Selecting all elements with 'a' tag
	var thumbs = window.document.getElementsByClassName('thumb');   // Selecting all elements with class 'thumb'
	console.log(thumbs);
	
	for (var i = 0; i < thumbs.length; i ++){
		// Will need fix for IE old versions.
		thumbs[i].addEventListener("click", loader, false);
	}
	
	function loader(e){
		console.log(this);
		e.preventDefault();
		var mainImageWrapper = document.getElementById('main-image');
		var mainImage = mainImageWrapper.getElementsByTagName('img')[0];
		mainImage.src = this.href;
	}
	
	// Comment out invokation of this method in html
	function mainImageClicked(){
		alert('main image clicked');
	}