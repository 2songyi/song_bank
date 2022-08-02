<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="ko">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.88.1">
    <title>SongBank</title>
    
    <link rel="canonical" href="https://getbootstrap.com/docs/4.6/examples/sign-in/">
	<link href="<c:url value='/resources/css/default.css'/>" rel="stylesheet">



    <!-- Bootstrap core CSS -->
<link href="<c:url value='/resources/css/bootstrap.min.css'/>" rel="stylesheet">


    <style>
    @import url('https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700;800&display=swap');
    
    body {
    	font-family: 'Nanum Gothic', sans-serif;
    }
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        -ms-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }
      
      h1 {
      	margin-bottom: 30px;}
    </style>


    <!-- Custom styles for this template -->
    <link href="<c:url value='/resources/css/signin.css'/>" rel="stylesheet">
  
  </head>
  <body class="text-center">

<form class="form-signin" action="login" method="post">
  <img class="mb-4" src="<c:url value='/resources/img/bank.png'/>" alt="" width="50" height="50">
  <h1 class="h3 mb-3 font-weight-normal font-weight-bold">SongBank</h1>
  <h1 class="h3 mb-3 font-weight-normal">로그인</h1>
  <label for="inputEmail" class="sr-only">아이디</label>
  <input type="text" name="userId" id="inputEmail" class="form-control" placeholder="아이디" required autofocus>
  <label for="inputPassword" class="sr-only">비밀번호</label>
  <input type="password" name="passwd" id="inputPassword" class="form-control" placeholder="비밀번호" required>
  <div class="checkbox mb-3">
    <label>
    </label>
  </div>
  ${error_msg}
  <button class="btn btn-lg btn-primary btn-block" type="submit">로그인</button>
  <p></p>
  <a href="add_customer">회원가입</a>
  <p class="mt-5 mb-3 text-muted">&copy; LeeSongYi</p>
</form>



  </body>
</html>
