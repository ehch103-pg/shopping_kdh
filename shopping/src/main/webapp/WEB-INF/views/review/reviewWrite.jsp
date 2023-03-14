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
    <td><input type="text" id="title" name="title" value="${ review.review_title }" required></td>
   </tr>
   <tr>
    <td><label>작성자 </label></td>
    <c:choose>
     <c:when test="${ id eq null or id eq ''}">
      <td><p id="writer">${ writer }</p></td>
     </c:when>
     <c:otherwise>
      <td><p id="writer">${ review.review_writer }</p></td>
     </c:otherwise>
    </c:choose>
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
     	<fmt:formatDate value="${ regDate }" pattern="yyyy-MM-dd HH:mm:ss" />
     </c:otherwise>
    </c:choose>
    </td>
   </tr>
   <tr>
    <td><label>리뷰 내용 </label></td>
    <td><textarea id="review_content" name="review_content">${ review.review_content }</textarea></td>
   </tr>
   <tr>
    <td><label>상품명 </label></td>
    <td>
      <a href="/product/productDetail?id=${ productCd }"><span id="product_info">${ product_name }</span></a>
      <input type="hidden" id="productCd" value="${ productCd }">
    </td>
   </tr>
   </tbody>
  </table>
  <input type="hidden" id="review_No" value="${ id }">
  <div class="button-area-write" align="center">
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
	$(function () {
		CKEDITOR.replace('review_content');
		filebrowserUploadUrl : '${pageContext.request.contextPath}/UploadFile'
	});
    function saveOrModify(url){
	  let title = $("#title").val();
	  let writer = document.getElementById("writer").innerHTML;
	  let content = CKEDITOR.instances.review_content.getData();
	  let product = document.getElementById("productCd").value;
	  console.log(product);
	  let reviewNo = $("#review_No").val();
	  let data = { 'title':title, 'writer':writer, 'content':content, 'product':product, 'reviewNo':reviewNo };
	  console.log(data);
	  sendUrl(url, data);
    }
	
	$("#register").on("click", function(){
	  if (confirm("등록 하시기 전에 한 번 더 확인하시겠습니까?")) {
		  saveOrModify('/review/regRev');
	  }
	});
	
	$("#modify").on("click", function(){
		saveOrModify('/review/modRev');
	});
	
</script>
</body>
</html>