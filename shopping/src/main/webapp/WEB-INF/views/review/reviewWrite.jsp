<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../include/header.jsp" %>
<div class="container">
  <table class="table table-bordered">
   <thead>
   <tr>
    <th colspan="10">
     <c:choose> 
     <c:when test="${ review.review_no != null  }">
      <p align="center">리뷰 수정</p>
     </c:when>
     <c:otherwise>
      <p align="center">리뷰 작성</p>
     </c:otherwise>
    </c:choose> 
    </th>
   </tr>
   </thead>
   <tbody>
   <tr>
    <td><label>제목 </label></td>
    <td><input type="text" id="title" name="title" value="${ review.review_title }"></td>
   </tr>
   <tr>
    <td><label>작성자 </label></td>
    <td><p id="writer_style">${ review.review_writer }</p></td>
   </tr>
   <tr>
    <td><label>작성일 </label></td>
    <td>
    <c:choose>
     <c:when test="${ id eq null or id eq '' }">
    	<jsp:useBean id="now" class="java.util.Date" />
    	<fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm:ss" var="today" />
    	<c:out value="${today}"/>
     </c:when>
     <c:otherwise>
     	<fmt:formatDate value="${ review.regDate }" pattern="yyyy-MM-dd HH:mm:ss" />
     </c:otherwise>
    </c:choose>
    </td>
   </tr>
   <tr>
    <td><label>리뷰 내용 </label></td>
    <td><textarea id="review_content" rows="40" cols="120">${ review.review_content }</textarea></td>
   </tr>
   <tr>
    <td><label>상품명 </label></td>
    <td><span id="product_info">${ review_product_Id }</span></td>
   </tr>
   </tbody>
  </table>
  <div class="button-area-write">
  <c:if test="${ check eq 'W' }">
    <button type="button" class="btn btn-primary" id="register">등록</button>
  </c:if>
  <c:if test="${ check eq 'M' }">
  	<button type="button" class="btn btn-secondary" id="modify">수정</button>
  </c:if> 
    <button type="reset" class="btn btn-danger">취소</button>
  </div>
</div>
<script type="text/javascript">
	let title = $("#title").val();
	let writer = $("#writer_style").innerText;
	let content = $("#review_content").innerText;
	let product = $("#product_info").innerText;
	
	let data = { 'title':title, 'writer':writer, 'content':content, 'product':product };
	$("#register").on("click", function(){
	  if (confirm("등록 하시기 전에 한 번 더 확인하시겠습니까?")) {
		 sendUrl('/review/regRev', data);
	  }
	});
	
	$("#modify").on("click", function(){
		sendUrl('/review/modRev', data);
	});
	
</script>
</body>
</html>