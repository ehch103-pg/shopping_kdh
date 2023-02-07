<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../include/header.jsp" %>
<div class="container">
 <form id="detailForm" name="detailForm" method="post">
  <table class="table table-bordered">
   <thead>
   <tr>
    <th colspan="10"> <p align="center">리뷰 작성</p></th>
   </tr>
   </thead>
   <tbody>
   <tr>
    <td><label>제목: </label></td>
    <td><input type="text" id="title" name="title"></td>
   </tr>
   <tr>
    <td><label>작성자: </label></td>
    <td><p id="writer_style">${ writer }</p></td>
   </tr>
   <tr>
    <td><label>작성일: </label></td>
    <td>
    	<jsp:useBean id="now" class="java.util.Date" />
    	<fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm:ss" var="today" />
    	<c:out value="${today}"/>
    </td>
   </tr>
   <tr>
    <td><label>리뷰 내용: </label></td>
    <td><textarea rows="40" cols="120"></textarea></td>
   </tr>
   <tr>
    <td><label>상품명: </label></td>
   </tr>
   </tbody>
  </table>
  <div id="button-area">
   <input type="button" id="submit" value="등록">
   <input type="reset" value="취소">
  </div>
 </form>
</div>
<script type="text/javascript">
	let form = $("#detailForm").serialize();
	$("#submit").on("click", function(){
	  if (confirm("등록 하시기 전에 한 번 더 확인하시겠습니까?")) 
			 form.submit();
	});
	
</script>
</body>
</html>