<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Shopping Mall</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/custom.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css" 
	integrity="sha512-SzlrxWUlpfuzQ+pcUCosxcglQRNAq/DZjVsC0lE40xsADsfeQoEypE+enwcOiGjk/bSuGGKHEyjSoQ1zVisanQ==" 
	crossorigin="anonymous" referrerpolicy="no-referrer" />

</head>
<body>
 	<script src="http://code.jquery.com/jquery-latest.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
	<script src="${pageContext.request.contextPath}/js/custom.js"></script>
	<header>
	 <nav class="navbar navbar-expand-lg navbar-light bg-light">
	  <div class="container-fluid">
	   <div class="navbar-header">
	    <a class="navbar-brand" href="#">홈</a>
	   </div>
	   <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      	<ul class="nav navbar-nav navbar-right">
      	 <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="#">Home</a>
         </li>
      	</ul>
       </div>
	  </div>
	 </nav>
	</header>