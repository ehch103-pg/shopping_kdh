<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 회원수정 </title>
</head>
<body>
	<h1> 회원수정 </h1>
	<div class="member-div">
	 <form name="memFrm" id="memFrm" action="/memberModProc" method="post">
	  <div class="member-div-element">
		<label>아이디: </label>
		<input type="text" id="mem_id" name="mem_id" value="${ mem_id }" readonly> 
	  </div>
	  <div class="member-div-element">
	 	<label>비밀번호: </label>
	 	<input type="password" id="mem_pw" name="mem_pw">
	  </div>
	  <div class="member-div-element">
	 	<label>이메일: </label>
	 	<input type="text" id="mem_email" name="mem_email" value="${ mem_email }">
	  </div>
	  <div>
	 	<label>이름: </label>
	 	<input type="text" id="mem_name" name="mem_name" value="${ mem_name }" readonly>
	  </div>
	  <div>
	  	<label>성별: </label>
	  	<select id="mem_gen" name="mem_gen">
	  	 <option value="M" <c:if test="${ mem_gen eq 'M' }"> selected </c:if>> 남성 </option>
	  	 <option value="F" <c:if test="${ mem_gen eq 'F' }"> selected </c:if>> 여성 </option>
	  	</select>
	  </div>
	  <input type="submit" id="modBtn" value="수정">
	  <input type="reset" value="취소">
	 </form>
	</div>
  <script src="http://code.jquery.com/jquery-latest.js"></script>
</body>
</html>