<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../include/header.jsp" %>

<div class="container">
  <div class="">
	<span>삭제하시겠습니까? </span>
  </div>
  <div>
	<input type="password" id="retireId">
	<button id="submit">탈퇴</button>
	<button id="cancel">취소</button>
  </div>
</div>
<script type="text/javascript">
	$("#submit").on("click", function () {
		let Id ={ 'Id' :  $("#retireId").val(); }
		sendUrl('/member/deleteMember', Id);
	});
	
	$("#cancel").on("click", function () {
		window.history.back();
	});
</script>
</body>
</html>