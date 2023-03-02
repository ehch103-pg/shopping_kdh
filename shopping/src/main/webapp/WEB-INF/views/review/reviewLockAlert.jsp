<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../include/header.jsp" %>
	<div class="container">
	  <p> 해당 게시글은 비밀글 처리가 되어있습니다. <br>
	      내용을 확인하고 싶다면 하단의 비밀번호를 입력해주시기 바랍니다. </p> 
	  <input type="password" id="lockPw">
	  <button class="btn btn-primary" id="sendlockPW">입력</button>
	  <input type="hidden" id="reviewNo" value="${ reviewNo }">
	</div>
<script type="text/javascript">
	$("#sendlockPw").on("click", function(){
		var url = '/review/checkLock';
		var lock = $("#lock").val();
		var reviewNo = $("#reviewNo").val();
		let data = { 'lock' : lock, 'reviewNo': reviewNo }
		sendUrl(url, data);
	});
</script>
</body>
</html>