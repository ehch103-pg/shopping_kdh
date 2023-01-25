<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
   <form action="/loginProc" method="post">
	<div>
	  <div>
		<label class="class-label-wrapper">아이디: </label>
	  </div>
	  <div>
		<input type="text" id="username" name="username">
	  </div>
	</div>
	<div>
	  <div>
	  	<label class="class-label-wrapper">비밀번호: </label>	
	  </div>
	  <div>
		<input type="password" id="password" name="password">
	  </div>
	</div>
	<button type="submit">로그인</button>
	<button type="button" onclick="location.href='member/memberReg'">회원가입</button>
   </form>
</body>
</html>