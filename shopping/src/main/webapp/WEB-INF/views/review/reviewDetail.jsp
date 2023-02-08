<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../include/header.jsp" %>

<div class="container">
	<table class="table table-bordered">
	 <thead>
	  <tr>
	 	<th colspan="10" class="thead"> 리뷰 상세</th> 
	  </tr>
	 </thead>
	 <tbody>
	 <tr>
    <td><label>제목 </label></td>
    <td><input type="text" id="title" name="title" value="${ title }"></td>
   </tr>
   <tr>
    <td><label>작성자 </label></td>
    <td><p id="writer_style">${ writer }</p></td>
   </tr>
   <tr>
    <td><label>작성일 </label></td>
    <td><fmt:formatDate value="${ regDate }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
   </tr>
   <tr>
    <td><label>리뷰 내용 </label></td>
    <td><textarea id="review_content" rows="40" cols="120">${ content }</textarea></td>
   </tr>
   <tr>
    <td><label>상품명 </label></td>
    <td><span id="product_info">${ product }</span></td>
   </tr>
   </tbody>
  </table>
</div>

<div class="button-area"> 
	<button class="btn btn-success" id="go_list">목록</button>
	<c:if test="${ check eq 1 }">
		<button id="go_modify" class="btn btn-warning">수정</button>
	</c:if>
</div>

<script>
	$("#go_list").on("click", function(){
		location.href='/review/reviewList';
	});
	
	$("#go_modify").on("click", function(){
		let no = document.getElementById("reviewNo").value;
		location.href='/review/reviewWrite?id='+no;
	});
</script>


</body>
</html>