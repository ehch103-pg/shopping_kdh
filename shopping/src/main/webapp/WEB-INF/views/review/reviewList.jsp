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
     </tr>
	</thead>
   <tbody>
   	 <tr>
   	  <td> 1 </td>
   	  <td> ㅋㅋㅋ </td>
   	  <td> test </td>
   	  <td> 2023-02-06 </td>
   	 </tr> 
   </tbody>
  </table>
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
	         1 
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
 	$(document).ready(function(){
 		onLoadList(1);
 	});
 
 	function onLoadList(page){
 	  if(page == '' || page == null){
 		  page = 1;
 	  }
 	  var data = {'page': page}
 	  $.ajax({
 		    type : 'get'
 		  , data : data
 	 	  , url  : '/review/reviewList'
 	 	  , success : function(){
 	 		  alert('성공');
 	 	  }
 	 	  , error : function(xhr){
 	 		  console.log(xhr);
 	 	  }
 	  });
 	}
 	
 </script>
</body>
</html>