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
  <div class="like-container">
   <label> 좋아요 </label>
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
  </div>
</div>

<input type="hidden" id="loginUser" value="${ check }">
<input type="hidden" id="reviewNo" value="${ reviewNo }">

<div class="button-area"> 
	<button class="btn btn-success" id="go_list">목록</button>
	<c:if test="${ check eq writer }">
		<button id="go_modify" class="btn btn-warning">수정</button>
	</c:if>
</div>

<script>
	let no = document.getElementById("reviewNo").value;
	$("#go_list").on("click", function(){
		location.href='/review/reviewList';
	});
	
	$("#go_modify").on("click", function(){
		location.href='/review/reviewWrite?id='+no;
	});
	
	$("#likeBtn").on("click", function(){
		let login = $("#loginUser").val();
		let data = { 'like_user': login, 'reviewNo' : no};
		let htmlTag;
		$.ajax({
			  type : 'post'
			, url  : '/review/LikeProc' 
			, data : JSON.stringify(data)
			, cache : false
			, contentType: 'application/json'
			, success : function(data){
				if(data.result == 'S'){
					if(data.like_switch == '0'){
						alert('좋아요가 취소되었습니다.');
						htmlTag += '<i class="fa-regular fa-heart"></i>';
						$("#likeBtn").html(htmlTag);
					}else {
						alert('좋아요가 반영되었습니다.');
						htmlTag += ' <i class="fa-solid fa-heart"></i>';
						$("#likeBtn").html(htmlTag);
					}
				}else {
					alert('좋아요가 적용되지 않았습니다.')
				}
			}
			, error   : function(xhr){
				console.log(xhr);
			}
			,
		});
	});
</script>


</body>
</html>