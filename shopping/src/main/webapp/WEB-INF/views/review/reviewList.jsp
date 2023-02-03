<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../include/header.jsp" %>
 <div class="container">
  <table>
   <thead>
     <tr>
     	<th>순번</th>
     	<th>제목</th>
     	<th>작성자</th>
     	<th>작성일</th> 
     </tr>
	</thead>
   <tbody>
    <c:forEach var="review" items="${ reviewList }">
   	 <tr>
   	  <td> ${ review.reviewNo } </td>
   	  <td> ${ review.reviewTitle } </td>
   	  <td> ${ review.reviewDate } </td>
   	  <td> ${ review.review_write_date } </td>
   	 </tr>
   	</c:forEach> 
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
	       <a class="page-link" href="#" onclick="onLoadList(${ i })">
	         {i.count}  
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
 	function onLoadList(page){
 	  if(page == '' || page == null){
 		  page = 1;
 	  }
 		let form = createElement('form');
 		let input = createElemnet('input');
 		input.setAttribute('name', 'page');
 		input.setAttribute('value', page);
 		input.setAttribute('type', 'hidden');
 		
 		form.append(input);
 		form.submit();
 	}
 	
 </script>
</body>
</html>