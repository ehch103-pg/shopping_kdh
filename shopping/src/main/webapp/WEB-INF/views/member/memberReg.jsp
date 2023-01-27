<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1> 회원가입 </h1>
	<div class="member-div">
	 <form name="memFrm" id="memFrm" method="post">
	  <div class="member-div-element">
		<label>아이디: </label>
		<input type="text" id="mem_id" name="mem_id"> 
	  </div>
	  <div class="member-div-element">
	 	<label>비밀번호: </label>
	 	<input type="password" id="mem_pw" name="mem_pw">
	  </div>
	  <div class="member-div-element">
	 	<label>이메일: </label>
	 	<input type="text" id="mem_email" name="mem_email">
	  </div>
	  <div>
	 	<label>이름: </label>
	 	<input type="text" id="mem_name" name="mem_name">
	  </div>
	  <div>
	  	<label>성별: </label>
	  	<select id="mem_gen">
	  	 <option value="M">남성</option>
	  	 <option value="F">여성</option>
	  	</select>
	  </div>
	  <button type="button" id="regBtn">가입</button>
	  <button type="reset">취소</button>
	 </form>
	</div>
  <script src="http://code.jquery.com/jquery-latest.js"></script> 	
  <script type="text/javascript">
  	$("#regBtn").on("click", function(){
  		let mem_id = $("#mem_id").val();
  		let mem_pw = $("#mem_pw").val();
  		let mem_email = $("#mem_email").val();
  		let mem_name = $("#mem_name").val();
  		let mem_gen = $("[id=mem_gen] :selected").val();
  		
  		let data = { 'mem_id' : mem_id, 'mem_pw' : mem_pw, 'mem_email': mem_email, 'mem_name': mem_name, 'mem_gen': mem_gen  }
  		
  		$.ajax({
  			  type : 'post'
  			, url  : '/member/memberRegProc'
  			, data : JSON.stringify(data)
  			, dataType : 'text'
  			, success : function(result){
  				 console.log(result);
  				 if(result == 1){
  					 alert('회원가입이 되었습니다. 환영합니다.');
  					 location.href('/login');
  				 }else {
  					 alert('회원가입이 정상적으로 처리가 되지 않았습니다.');
  				 }
  			  }
  			, error : function(xhr){
  				console.log(xhr);	
  			}
  		});
  	});
  </script>
</body>
</html>