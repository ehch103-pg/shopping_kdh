<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../include/header.jsp" %>

<div class="container">
  <div class="">
	<span>${ id } 님 탈퇴 하시겠습니까? </span>
  </div>
  <div>
	<p>아이디 : <input type="text" id="retireId" class="form-control"></p>
	<p>비밀번호 : <input type="password" id="retirePw" class="form-control"></p>
  </div>
  <div align="center">
	<button id="submit" class="btn btn-primary">탈퇴</button>
	<button id="cancel" class="btn btn-danger">취소</button>
  </div>
</div>
<script type="text/javascript">
	$("#submit").on("click", function() {
		let Id = $('#retireId').val();
		let Pw = $('#retirePw').val();
		var data = { 'Id' : Id, 'Pw' : Pw }
		sendUrl('/member/retireMember', data);
	});
	
	$("#cancel").on("click", function() {
		window.history.back();
	});
</script>
</body>
</html>