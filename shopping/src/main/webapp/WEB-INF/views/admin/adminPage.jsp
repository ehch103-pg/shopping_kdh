<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ include file="../include/header.jsp" %>

<div class="container">
    <sec:authorize access="hasRole('ROLE_ADMIN')">
    	<span>관리자 페이지</span>
    </sec:authorize>
</div>

</body>
</html>