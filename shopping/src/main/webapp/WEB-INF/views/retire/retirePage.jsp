<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../include/header.jsp" %>

<div class="container">
	<p> ${ Id } 님. 본 회원은 탈퇴 상태입니다. 해제를 원하실 경우 하단에 ID를 입력해주시기 바랍니다. </p>
	<input type="text" id="recoveryId">
	<button type="button" id="checkBtn">확인</button>
</div>
</body>
</html>