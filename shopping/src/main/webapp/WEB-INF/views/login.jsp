<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="include/header.jsp" %>

  <div>
   <form name="loginFrm" action="/loginProc" method="post">
	<div class="form-group">
	  <div class="col-xs-15">
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
   </form>
   </div>
</body>
</html>