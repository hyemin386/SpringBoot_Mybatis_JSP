<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> <!-- jsp 페이지 선언부에 spring message를 사용할 수 있도록 선언 -->
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> <!-- security 사용 -->
<!DOCTYPE html>
<html>
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">

<title>Hello, world!</title>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarTogglerDemo01">
			<a class="navbar-brand" href="#">Hidden brand</a>
			<ul class="navbar-nav mr-auto mt-2 mt-lg-0">
				<li class="nav-item active"><a class="nav-link" href="#">Home
						<span class="sr-only">(current)</span>
				</a></li>
				<li class="nav-item"><a class="nav-link" href="#">Link</a></li>
				<li class="nav-item"><a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Login</a></li>
			</ul>
			<form class="form-inline my-2 my-lg-0">
				<input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
				<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
			</form>
		</div>
	</nav>

	<div class="jumbotron jumbotron-fluid mt-3">
		<div class="container">
		<h1>${message}</h1>
			<h1>
				<sec:authorize access="isAuthenticated()"> <!-- 로그인 상태 또는 사용자가 익명 사용자 -->
					로그인 성공상태
				</sec:authorize> <!-- 로그인 관련 유무, 권한태그 -->
				
				<sec:authorize access="!isAuthenticated()"> <!-- 로그인 하지 않은 상태 -->
					로그인 하지 않은 상태
				</sec:authorize>
				
				<!-- 관리자인지 아닌지 --> 
				<sec:authorize access="hasRole('ROLE_ADMIN')">
					관리자 입니다
				</sec:authorize>
				
				<sec:authorize access="hasRole('ROLE_MEMBER')">
					일반 회원입니다
				</sec:authorize>
				
			</h1>
		
			<h1 class="display-4">Fluid jumbotron</h1>
			<p class="lead">This is a modified jumbotron that occupies the entire horizontal space of its parent.</p>
		</div>
	</div>

	<div class="container">
		<!-- code(key)가 없는 경우 text의 메시지가 출력 -->
		<h1><spring:message code="hello1234" text="defalut message"></spring:message></h1>
		<h1><spring:message code="hello"></spring:message></h1>
		<h1><spring:message code="user.welcome" arguments="${user}, ${msg}" argumentSeparator=","></spring:message></h1>
	</div>
	
	<footer class="footer mt-auto py-3 bg-dark">
		<div class="container">
			<span class="text-muted">Place sticky footer content here.</span>
		</div>
	</footer>

	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>

</body>
</html>