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
    <td><p id="writer">${ review.review_writer }</p></td>
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
    <td><textarea id="review_content" rows="40" cols="120">${ review.review_content }</textarea></td>
   </tr>
   <tr>
    <td><label>상품명 </label></td>
    <td>
      <a href="/product/product_info?id=${ product_cd }"><span id="product_info">${ review.product_name }</span></a>
    </td>
   </tr>
   </tbody>
  </table>
  <input type="hidden" id="review_No" value="${ id }">
  <span>비밀 여부 <input type="checkbox" id="lock"></span>
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
	var lockVal;
    function saveOrModify(url){
	  let title = $("#title").val();
	  let writer = document.getElementById("writer").innerHTML;
	  let content = document.getElementById("review_content").innerHTML;
	  let product = document.getElementById("product_info").innerHTML;
	  let reviewNo = $("#review_No").val();
	  let data = { 'title':title, 'writer':writer, 'content':content, 'product':product, 'reviewNo':reviewNo, 'lock': lockVal };
	  console.log(data);
	  sendUrl(url, data);
    }
	$("#lock").on("click", function(){
		var lock = document.getElementById('lock');
		if(lock.checked){
			lockVal = 'Y'
		}else {
			lockVal = 'N';
		}
		console.log(lockVal);
	});
	
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