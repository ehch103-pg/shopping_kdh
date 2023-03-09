<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../include/header.jsp" %>

<div class="container">
	<div class="product_thumb">
		<img src="${ pageContext.request.contextPath }/imgShow?imgSeq=${ product.fileSeq }">
	</div>
	<div class="product_content">
	  <form id="product_info" name="product_info">
	   <div>${ product.productPrice }</div>
	  </form>
	</div>
	<sec:authorize access="hasRole('ROLE_USER')">
	   <button id="review_write" class="btn">리뷰 작성하기</button>
	</sec:authorize>
	<sec:authorize access="hasRole('ROLE_ADMIN')">
	   <button id="productModBtn" class="btn" onclick="location.href='/product/productMod'">상품 정보 수정하기</button>
	</sec:authorize>
	<input type="hidden" id="productCd" value="${ Id }">
</div>

<script type="text/javascript">
	$("#review_write").on("click", function(){
		var productCd  = $("#productCd").val();
		location.href='/review/reviewWrite?productCd='+productCd;
	});
	
</script>
</body>
</html>