function sendUrl(url, data){
	$.ajax({
		  type : 'post'
		, url  : url
		, data : JSON.stringify(data)
		, contentType: "application/json; charset=utf-8"
		, cache : false
		, success : function(data){
			if(data.result == 'S'){
			  alert(data.msg);
			  location.href=data.url;
			}else {
			  alert(data.msg);
			  location.reload();
			}
		}
		, error : function(xhr){
			console.log(xhr);
		}
	});
}

function checkPassword(data){
	let check = document.getElementById("PWcheck");
	if(data == null || data.length < 10){
		check.innerHTML = '비밀번호는 최소 10글자 이상 입력 바랍니다'
		check.setAttribute('style', 'color:red');
		document.getElementById("PWcheck").focus(); 
	}else {
		check.innerHTML = '';
	}
}

function fileExpChecker(expcd, fileExp){
	if(expcd != fileExp){
		alert('허용된 확장자가 아닙니다.');
		return false;
	}
}

function checkId(data){
	var mem_Id = document.getElementById("mem_id");
	$.ajax({
		  type : 'post'
		, url : '/member/memberCheck'
		, data : JSON.stringify({ 'Id' : data })
		, contentType: "application/json; charset=utf-8"
		, success : function(data){
			console.log(data);
			if(data.result == 'None'){
				if(confirm('중복되지 않은 아이디입니다. 해당 아이디를 사용하시겠습니까?')){
					mem_Id.setAttribute('readonly', 'readonly');
				}
			}else if(data.result == 'Exist'){
				alert('이미 사용중인 아이디입니다.');
				mem_Id.value = '';
				mem_Id.focus();
				return false;
			}else {
				alert('제대로된 처리가 되질 않았습니다.');
			}
		}
		, error : function(xhr){
				console.log(xhr);
		}
	});
}



function likeChange(data){
	var like_count = 0;
	var htmlTag = "";
	var like_check = document.getElementById('like_check');
	$.ajax({
		  type : 'post'
		, url  : '/review/LikeProc' 
		, data : JSON.stringify(data)
		, cache : false
		, contentType: 'application/json'
		, success : function(data){
			like_count = data.like_count;
			if(data.result == 'S'){
				if(data.like_switch == '0'){
					htmlTag += '<i class="fa-regular fa-heart"></i>';
					alert('좋아요가 취소되었습니다.');
				}else {
					htmlTag += '<i class="fa-solid fa-heart"></i>';
					alert('좋아요가 반영되었습니다.');
				}
				like_check.value = like_count;
				$("#like_count").html(like_count);
				$("#likeBtn").html(htmlTag);
			}else {
				alert('좋아요가 적용되지 않았습니다.')
			}
		}
		, error   : function(xhr){
			console.log(xhr);
		}
	});
}