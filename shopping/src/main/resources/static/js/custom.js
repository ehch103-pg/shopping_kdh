function sendUrl(url, data){
	$.ajax({
		  type : 'post'
		, url  : url
		, data : data
		, cache : false
		, success : function(data){
			if(data.result == 'S'){
			  alert(data.msg);
			  location.href=data.url;
			}else {
			  alert(data.msg);
			}
		}
		, error : function(xhr){
			console.log(xhr);
		}
	});
}