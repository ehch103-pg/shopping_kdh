<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../include/header.jsp" %>

<div class="container">

    <div class="module-body">
    <ul class="product-list">
      <c:forEach var="products" items="${ products }">
       <li>
  	    <div class="product_thumbnail">
  	     <a href="/product/productDetail?id=${ products.productId }">
  	      <img src="${ pageContext.request.contextPath }/imgShow?imgSeq=${ products.fileSeq }">
  	     </a>
  	    </div>
  	    <div class="product_content">
  	     <div class="product_name">${ products.productName }</div>
  	     <div class="product_price">${ products.productPrice }</div>
  	    </div>
  	   </li>
      </c:forEach>
    </ul>
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
  <sec:authorize access="hasRole('ADMIN')">
   <button id="productReg" class="btn btn-success">상품 등록</button>
  </sec:authorize>
</div>
<script type="text/javascript">
	$("#productReg").on("click",function(){
		location.href='/product/productReg';
	});
</script>
</body>
</html>