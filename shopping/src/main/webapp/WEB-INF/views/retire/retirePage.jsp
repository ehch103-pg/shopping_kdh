<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../include/header.jsp" %>

<div class="container">
	<p> ${ Id } 님. 본 회원은 탈퇴 상태입니다. 해제를 원하실 경우 하단에 비밀번호를 입력해주시기 바랍니다. </p>
	<input type="password" id="recoveryPw" class="form-control">
	<button type="button" id="checkBtn" class="btn btn-primary">확인</button>
	<input type="hidden" id="recoveryId" value="${ Id }">
</div>

<script type="text/javascript">
  $("#checkBtn").on("click", function(){
	 var passwd = $("#recoveryPw").val();
	 var recoveryId = document.getElementById('recoveryId').value;
	 var data = { 'recoveryId' : recoveryId, 'passwd' : passwd }
	 sendUrl('/member/returnMember', data); 
  });
</script>
</body>
</html>