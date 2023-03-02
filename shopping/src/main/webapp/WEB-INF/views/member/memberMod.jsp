<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../include/header.jsp" %>
	<h1> 회원수정 </h1>
	<div class="textForm">
	 <form name="memFrm" id="memFrm">
	  <div class="form-group row">
	    <label for="colFormLabelLg" class="col-sm-3 col-form-label col-form-label-lg">아이디: </label>
	    <div class="col-sm-8">
			<input type="text" class="form-control" id="mem_id" name="mem_id" value="${ mem_id }" readonly> 
	  	</div>
	  </div>
	  <div class="form-group row">
	   <label for="colFormLabelLg" class="col-sm-3 col-form-label col-form-label-lg">비밀번호: </label>
	   <div class="col-sm-8">	
	 	<input type="password" class="form-control" id="mem_pw" name="mem_pw">
	    <span id="PWcheck"></span>
	   </div>
	  </div>
	  <div class="form-group row">
	   <label for="colFormLabelLg" class="col-sm-3 form-label col-form-label-lg">이메일: </label>
	   <div class="col-sm-8">	
	 	<input type="email" class="form-control" id="mem_email" name="mem_email" value="${ mem_email }">
	   </div>
	  </div>
	  <div class="form-group row">
	   <label for="colFormLabelLg" class="col-sm-3 form-label col-form-label-lg">이름: </label>
	   <div class="col-sm-8">
	 	<input type="text" class="form-control" id="mem_name" name="mem_name" value="${ mem_name }" readonly>
	   </div>
	  </div>
	   <div class="form-group row">
	   <label for="colFormLabelLg" class="col-sm-3 form-label col-form-label-lg">성별: </label>
	   <div class="col-sm-8">
	  	<select id="mem_gen" name="mem_gen" class="form-control">
	  	 <option value="M" <c:if test="${ mem_gen eq 'M' }"> selected </c:if>> 남성 </option>
	  	 <option value="F" <c:if test="${ mem_gen eq 'F' }"> selected </c:if>> 여성 </option>
	  	</select>
	   </div>
	  </div>
	  <div id="button-area" align="center">
	  	<input type="button" class="btn btn-primary" id="modBtn" value="수정">
	  	<button type="button" class="btn btn-warning">취소</button>
	  	<button type="button" class="btn btn-danger" id="retire">탈퇴</button>
	  </div>
	 </form>
	</div>
   <script type="text/javascript">
	  $("#modBtn").on("click", function(){
   		let id = $("#mem_id").val();
   		let pw = $("#mem_pw").val();
   		let email = $("#mem_email").val();
   		let name = $("#mem_name").val();
   		let gen = $("#mem_gen").val();
	  	var data = {'mem_id': id, 'mem_pw' : pw, 'mem_name' : name, 'mem_email' : email, 'mem_gen' : gen}
   		sendUrl('/member/memberModProc', data);
	  });
	  
	  $("#retire").on("click", function() {
		 let id = $("#mem_id").val();
		 location.href='memberDel?id='+id;
	  });
   </script>
</body>
</html>