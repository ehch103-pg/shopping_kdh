<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="include/header.jsp" %>

  <div>
   <form name="loginFrm" action="/loginProc" method="post" class="form-signin">
	<div class="form-group">
	  <div class="mb-3">
		<label class="class-label-wrapper">아이디: </label>
		<input type="text" id="username" name="username" class="form-control" required autofocus>
	  </div>
	</div>
	<div class="form-group">
	  <div class="mb-3">
	  	<label class="class-label-wrapper">비밀번호: </label>	
		<input type="password" id="password" name="password" class="form-control" required>
	  </div>
	</div>
	<button class="btn btn-lg btn-primary btn-block" type="submit">로그인</button>
   </form>
   </div>
</body>
</html>