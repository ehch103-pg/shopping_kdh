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
     <td><label>조회수</label>
     <td><p>${ view_Count }</p></td>
    </tr>
    <tr>
     <td><label>제목 </label></td>
     <td><p>${ title }</p></td>
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
     <td><p>${ content }</p></td>
    </tr>
    <tr>
     <td><label>상품명 </label></td>
     <td>
        <a href="/product/product_info?id=${ product_cd }">
         <span id="product_info">${ product_name }</span>
        </a>
     </td>
    </tr>
   </tbody>
  </table>
  <div class="like-container">
   <label> 좋아요: </label> 
   <label id="like_count"> ${ like_count } </label>
   <c:if test="${ like_check != null }">
    <button id="likeBtn">
    <c:choose>
     <c:when test="${ like_check == 0}">
      <i class="fa-regular fa-heart"></i>
     </c:when>
     <c:otherwise> 
      <i class="fa-solid fa-heart"></i>
   	 </c:otherwise>
   	</c:choose>
   </button>
  </c:if>
   <input type="hidden" id="like_check" value="${ like_check }">
   <input type="hidden" id="loginUser" value="${ check }">
   <input type="hidden" id="reviewNo" value="${ reviewNo }">
  </div>
</div>

<input type="hidden" id="page" value="${ page }">
<input type="hidden" id="keyword" value="${ keyword }">

<div class="button-area"> 
	<button class="btn btn-success" id="go_list">목록</button>
	<c:if test="${ check eq writer or role eq 'ROLE_ADMIN'}">
		<button id="go_modify" class="btn btn-warning">수정</button>
		<button id="removeReview" class="btn btn-danger">삭제</button>
	</c:if>
</div>

<script>
	let no = document.getElementById("reviewNo").value;
	var msg="";
	$("#go_list").on("click", function(){
		 var keyword = $("#keyword").val();
		 var page = $("#page").val();
		
		location.href='/review/reviewList';
	});
	
	$("#go_modify").on("click", function(){
		location.href='/review/reviewWrite?id='+no;
	});
	
	$("#likeBtn").on("click", function(){
		var count = $("#like_check").val();
		let login = $("#loginUser").val();
		let data = { 'like_user': login, 'reviewNo' : no};
		if(count%2 == 0){
			msg = '좋아요를 하시겠습니까?'
		}else {
			msg = '좋아요를 취소하시겠습니까?'
		}
		
		if(confirm(msg)){
			likeChange(data);
		}
	});
	
	$("#removeReview").on("click", function(){
		location.href='/review/reviewDel';
	})
</script>


</body>
</html>