<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http//www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>홈</title>
</head>
<body>
	<div>
		<label>홈</label>
	</div>
	<div>
		<button onclick="location.href='/login'">로그인</button>
	</div>
	<button onclick="location.href='/member/memberReg'">회원 가입</button>
	<button onclick="location.href='/member/memberMod?id=${ id }'">회원 정보 수정</button>
</body>
</html>