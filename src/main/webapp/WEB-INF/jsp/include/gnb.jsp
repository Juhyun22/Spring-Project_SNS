<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="d-flex align-items-center h-100 ml-3">
	<div>
		<a href="#" id="icon" class="text-monospace text-info">
			<h2>Gramgram</h2>
		</a>
	</div>
	<div class="d-flex align-items-end">
		<c:if test="${not empty userName}">
			<div>${userName}님 안녕하세요.</div>
			<a href="/user/sign_out">로그아웃</a>
		</c:if>
	</div>
</div>