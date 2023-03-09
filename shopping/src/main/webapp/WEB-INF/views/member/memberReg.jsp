<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../include/header.jsp" %>
	<h1> 회원가입 </h1>
	<div class="textForm">
	 <form name="memFrm" id="memFrm">
	  <div class="form-group row">
	    <label for="colFormLabelLg" class="col-sm-3 col-form-label col-form-label-lg">아이디: </label>
	    <div class="col-sm-8">
		 <input class="form-control" type="text" id="mem_id" name="mem_id" required> 
	    </div>
	    <input type="button" id="member_check" value="중복확인" class="btn btn-success">
	  </div>
	  <div class="form-group row">
	   <label for="colFormLabelLg" class="col-sm-3 col-form-label col-form-label-lg">비밀번호: </label>
	   <div class="col-sm-8">	
	 	<input type="password" class="form-control" id="mem_pw" name="mem_pw" required>
	    <span id="PWcheck"></span>
	   </div>
	  </div>
	  <div class="form-group row">
	   <label for="colFormLabelLg" class="col-sm-3 form-label col-form-label-lg">이메일: </label>
	   <div class="col-sm-8">	
	 	<input type="email" class="form-control" id="mem_email" name="mem_email" required>
	   </div>
	  </div>
	  <div class="form-group row">
	   <label for="colFormLabelLg" class="col-sm-3 form-label col-form-label-lg">이름: </label>
	   <div class="col-sm-8">
	 	<input type="text" class="form-control" id="mem_name" name="mem_name" required>
	   </div>
	  </div>
	  <div class="form-group row">
	   <label for="colFormLabelLg" class="col-sm-3 form-label col-form-label-lg">성별: </label>
	   <div class="col-sm-8">	
	  	<select id="mem_gen" name="mem_gen" class="form-control" required>
	  	 <option value="M" selected>남성</option>
	  	 <option value="F">여성</option>
	  	</select>
	   </div>	
	  </div>
	  <div class="form-group row">
	   <label for="colFormLabelLg" class="col-sm-3 form-label col-form-label-lg">관리자 가입</label>
	   <div class="col-sm-8">	
	    <span>관리자 가입</span><input type="checkbox" name="adminCheck" id="adminCheck"> 
	   </div>
	  </div>
	  <div id="button-area" align="center">
	  	<input type="button" class="btn btn-primary" id="regBtn" value="가입">
	  	<input type="reset" class="btn btn-danger" value="취소">
	  </div>
	 </form>
	</div>
	
	<script>
		
		$("#member_check").on("click", function(){
			var IdVal = $("#mem_id").val();
			checkId(IdVal);
		});
	
		$("#regBtn").on("click", function() {
				var data = {
						'mem_id' : $("#mem_id").val(),
						'mem_pw' : $("#mem_pw").val(),
						'mem_email' : $("#mem_email").val(),
						'mem_name' : $("#mem_name").val(),
						'mem_gen' : $("#mem_gen option:selected").val(),
						'adminCheck' : $("#adminCheck").is(":checked")
				};
				let url = '/member/memberReg'
				console.log(data);
				sendUrl(url, data);
		});
	</script>
</body>
</html>