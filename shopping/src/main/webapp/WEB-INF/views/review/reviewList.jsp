<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../include/header.jsp" %>
 <div class="container">
  <table class="table">
   <thead>
     <tr>
     	<th scope="col">순번</th>
     	<th scope="col">제목</th>
     	<th scope="col">작성자</th>
     	<th scope="col">작성일</th> 
     	<th scope="col">조회수</th>
     </tr>
	</thead>
   <tbody>
   <c:choose>
    <c:when test="${ size > 0}">
	 <c:forEach var="review" items="${ reviewList }">
	  <tr>
	   <td> ${ review.reviewNo } </td>
	   <td><a href="/review/reviewDetail?id=${ review.reviewNo }"> ${ review.reviewTitle } </a></td>
	   <td> ${ review.reviewWriter }</td>
	   <td> ${ review.reviewWriteDate }</td>
	   <td> ${ review.viewCount }</td>
	  </tr> 
	 </c:forEach>
   	</c:when>
   	<c:otherwise>
   	 <tr>
      <td>게시물이 존재하지 않습니다.</td>
     </tr>
   	</c:otherwise>
   </c:choose>
   </tbody>
  </table>
  <div class="search_wrap">
   <div class="search_area">
    <div class="row">
  	 <div class="col-md-8">
  	  <select id="searchType">
  	  	<option value="All" <c:if test="${ option eq 'All' }"> selected </c:if> >전체</option>
  	  	<option value="title" <c:if test="${ option eq 'title' }"> selected </c:if>>제목</option>
  	  	<option value="writer" <c:if test="${ option eq 'writer' }"> selected </c:if>>작성자</option>
  	  	<option value="regDate" <c:if test="${ option eq 'regDate' }"> selected </c:if>>작성일</option> 
  	  </select>
  	   
  	   <input type="text" id="keyword" name="keyword" value="${ keyword }"> 
  	   <input type="button" id="search" class="btn float-left" value="검색" onclick="searchKeyword();">
  	   <form id="searchForm" name="searchForm" method="get">
  	  
  	  </form>
  	 </div>
   	 <div class="col-md-4"><input type="button" id="review_write" class="btn float-right" value="리뷰 글 쓰기"></div>
    </div>
   </div>
  </div>
  <div id="pagination-field" class="text-center">
   <nav>
    <ul class="pagination">
     <li class="page-item">
      <c:if test="${ paging.currentPageNo > 1}">
        <a class="page-link" href="#" onclick="onLoadList(${ paging.currentPageNo -1 })" aria-label="이전">
         <span aria-hidden="true">&laquo;</span>
        </a>
      </c:if>
     </li>
     <c:forEach var="page" varStatus="i" begin="${ paging.firstPageOnIndex }" end="${ paging.lastPageOnIndex }">
	     <li class="page-item">
	       <a class="page-link" href="#" onclick="onLoadList(${ i.count })">
	         ${ i.count } 
	       </a>
	     </li>
	 </c:forEach> 
	 <li class="page-item">
	  <c:if test="${ paging.currentPageNo < paging.lastPageOnIndex }">
        <a class="page-link" href="#" onclick="onLoadList(${ paging.currentPageNo + 1 })" aria-label="다음">
         <span aria-hidden="true">&raquo;</span>
        </a>
      </c:if>
     </li>
    </ul>
   </nav>
  </div>	
 </div>
 <script>
 
 	function onLoadList(pageVal){
 		let keyword = document.getElementById('keyword');
 		let searchType = $("#searchType option:selected").val();
 		let page = document.createElement('input');
 		page.setAttribute('type', 'hidden');
 		page.setAttribute('value', pageVal);
 		page.setAttribute('name', 'page');
 		
 		let form = $("#searchForm");
 		form.append(keyword);
 		form.append(searchType);
 		form.append(page);
 		form.submit();
 	}
 	
 	$("#review_write").on("click", function(){
 		location.href='/review/reviewWrite';
 	});
 	
 	$("#keyword").keydown(function(key){
 		if(key.keyCode == 13){
 			searchKeyword();
 		}
 	});
 	
 	function searchKeyword(){
 		let keywordVal = document.getElementById('keyword').value;
 		let searchVal = $("#searchType option:selected").val();
 		var searchType = document.createElement('input');
 		searchType.setAttribute('type', 'hidden');
 		searchType.setAttribute('value', searchVal);
 		searchType.setAttribute('name', 'option');
 		
 		var keyword = document.createElement('input');
 		keyword.setAttribute('type', 'hidden');
 		keyword.setAttribute('value', keywordVal);
 		keyword.setAttribute('name', 'searchWord');
 		
 		let searchForm = document.getElementById('searchForm');
 		searchForm.append(keyword);
 		searchForm.append(searchType);
 		searchForm.submit();
 	}
 	
 </script>
</body>
</html>