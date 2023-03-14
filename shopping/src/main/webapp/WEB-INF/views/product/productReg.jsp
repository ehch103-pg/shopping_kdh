<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../include/header.jsp" %>

<div class="container">
   <form id="productFrm" name="productFrm" enctype="multipart/form-data">
     <table class="table">
       <thead>
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
         <td><label>상품 썸네일</label></td>
         <td><input class="form-control" type="file" id="productThumb" onchange="fileExpChecker('img', this.value);"></td> 
        </tr>
        <tr>
         <td><label>상품 종류</label></td>
         <td>
          <select id="productType" class="form-control">
            <option value="B01">서책</option>
         	<option value="F01">음식</option>
         	<option value="G02">주방</option>
         	<option value="G03">욕실·화장실</option>
         	<option value="G04">공구</option>
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
          <td><textarea name="contents" class="form-control" id="contents"></textarea></td>
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

<script>
  var Btn = document.querySelector('button').id;
  $(function () {
		CKEDITOR.replace('contents', {
			filebrowserUploadUrl : '${pageContext.request.contextPath}/UploadFile'
		});
  });
 
  $("#"+Btn).on("click", function(){
	 
	 let cd; 
	 var url = '/product/productUpload';
	 if(Btn == 'regBtn'){
		cd = 'R';
	 }else {
		cd = 'M';
	 }
	 
	 var name = document.getElementById('product_Nm').value;
	 var price = document.getElementById('product_price').value;
	 var kind = $("#productType option:selected").val();
	 var content = CKEDITOR.instances.contents.getData();
	 let data = { 'product_Nm' : name, 'product_price' : price, 'product_kind' : kind, 'product_intro' : content };
	 
	 sendUrl(url, data);
  });
</script>
</body>
</html>