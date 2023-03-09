<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../include/header.jsp" %>

<div class="container">
   <form id="productFrm" name="productFrm" enctype="multipart/form-data">
     <table class="table">
       <thead align="center">
        <tr>
         <th colspan="10">
          <c:choose>
           <c:when test="${ productId eq null  or productId eq ''}">
            <label>상품 등록</label>
           </c:when>
           <c:otherwise>
            <label>상품 수정</label>
           </c:otherwise>
          </c:choose>
         </th>
        </tr>
       </thead>
       <tbody>
        <tr>
         <td><label>상품 종류</label></td>
         <td>
          <select id="productType" class="form-control">
         	<option value="food">음식</option>
         	<option value="kitchen">주방</option>
         	<option value="bathroom">욕실·화장실</option>
         	<option value="book">서책</option>
         	<option value="engineer">공구</option>
          </select>
         </td>
        </tr>
        <tr>
         <td><label>상품명</label></td>
         <td><input type="text" class="form-control" id="product_Nm" value=${ productNm }>
        </tr>
        <tr>
         <td><label>상품 가격</label></td>
         <td><input type="text" class="form-control" id="product_price" value=${ productPrice }></td>
        </tr>
        <tr>
          <td><label>상품소개</label></td>
          <td><textarea id="product_intro" rows="40" cols="120"></textarea></td>
        </tr>
        <tr>
          <td><label>등록일</label></td>
          <td>
          <c:choose>
           <c:when test="${ productId eq null or productId eq ''}">
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
       </tbody>
     </table>
     ${ Btn }
   </form>
</div>

<script type="text/javascript">
  var Btn = document.querySelector('button').id;
  $("#"+Btn).on("click", function(){
	 
	 let cd; 
	 var url = '/product/productUpload';
	 if(Btn == 'regBtn'){
		cd = 'R';
	 }else {
		cd = 'M';
	 }
	 let data = {};
	 sendUrl(url, data);
  });
</script>
</body>
</html>