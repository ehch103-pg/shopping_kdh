<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../include/header.jsp" %>

<div class="container">
	<button class="btn" id="modBtn">회원정보 수정</button>
	<button class="btn" id="payRecord">구매내역</button>
	
	<input type="hidden" id="mem_id" value="${ id }">
</div>

<script type="text/javascript">
	$("button").on("click", function(){
		var btnId = $(this).attr('id');
		var mem_id = $("#mem_id").val();
		if(btnId == 'modBtn'){
			location.href='/member/memberMod?id='+mem_id;
		}else if(btnId == 'payRecord'){
			location.href='/product/payRecord?mem_id='+mem_id;
		}
	});
</script>
</body>
</html>