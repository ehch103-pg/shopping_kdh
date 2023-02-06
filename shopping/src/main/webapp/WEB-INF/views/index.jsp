<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ include file="include/header.jsp" %>

<label>홈</label>
	<div>
	  <sec:authorize access="isAnonymous()">
		<button onclick="location.href='/login'">로그인</button>
		<button onclick="location.href='/member/memberReg'">회원 가입</button>
	  </sec:authorize>
	  <sec:authorize access="isAuthenticated()">
		<button onclick="location.href='/logout'">로그아웃</button>
		<button onclick="location.href='/member/memberMod?id=${ id }'">회원 정보 수정</button>
		<button onclick="location.href='review/reviewList'">리뷰 목록</button>
	  </sec:authorize>
	</div>
</body>
</html>