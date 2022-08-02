<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.88.1">
    <title>SongBank</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.6/examples/checkout/">


    <!-- Bootstrap core CSS -->
	<link href="<c:url value='/resources/css/bootstrap.min.css'/>" rel="stylesheet">
	<link href="<c:url value='/resources/css/default.css'/>" rel="stylesheet">

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
    </style>


    <!-- Custom styles for this template -->
    <link href="<c:url value='/resources/css/success_login.css'/>" rel="stylesheet">
    
     <script language="javascript">

	function agreeCheck(frm) {
   		if (frm.checkButton.disabled==true) {
    		frm.checkButton.disabled=false
   		} else {
  	 		 frm.checkButton.disabled=true   		
  	 	}
	}

	</script>
	
  </head>
  <body class="bg-light">

<div class="container">
  <div class="py-5 text-center">
    <h2>회원가입</h2>
  </div>

  <div class="row justify-content-center">

    <div class="col-md-5 order-md-1">
      <form action="check_id" method="get" class="needs-validation" novalidate>
        <div class="mb-3">
            <label for="userId">아이디</label>
            <div class="input-group mb-3">
              <input name="userId" type="text" class="form-control" placeholder="아이디를 입력해주세요." aria-describedby="button-addon2" value="${userId}">
              <button class="btn btn-outline-secondary" type="submit" id="button-addon2">아이디 중복체크</button>
            </div>
            <span>${msg}</span>
          </div>
          </form>
          
		<form action="add_customer" method="post" class="needs-validation" novalidate>
        <div class="mb-3">
          <label for="username">비밀번호</label>
          <div class="input-group">
          <input type="hidden" name="userId" value="${userId}">
            <input type="text" name="passwd" class="form-control" id="username" placeholder="비밀번호" required>
          </div>
        </div>

        <div class="mb-3">
          <label for="email">이름</label>
          <input type="text" name="name" class="form-control" id="email" placeholder="이름을 입력해주세요">
        </div>

        <div class="mb-3">
          <label for="address">주민번호</label>
          <input type="text" name="ssn" class="form-control" id="address" placeholder="생년월일 8자" required>
        </div>

        <div class="mb-3">
          <label for="address2">연락처</label>
          <input type="text" name="phone" class="form-control" id="address2" placeholder="010-0000-0000">
        </div>

		<hr class="mb-4">
        <div class="custom-control custom-checkbox">
          <input type="checkbox" class="custom-control-input" id="same-address" onClick="agreeCheck(this.form)">
          <label class="custom-control-label" for="same-address">회원가입 약관에 동의합니다. (필수)</label>
        </div>
        <hr class="mb-4">
        <button name="checkButton" class="btn btn-primary btn-lg btn-block" type="submit" name="idcheck" value="pass" disabled>회원가입 하기</button>
  </form>
</div>

  <footer class="my-5 pt-5 text-muted text-center text-small">
    <!-- <p class="mb-1">&copy; songbank</p> -->
  </footer>
</div>
</div>


    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
      <script>window.jQuery || document.write('<script src="../assets/js/vendor/jquery.slim.min.js"><\/script>')</script><script src="../assets/dist/js/bootstrap.bundle.min.js"></script>


        <script src="form-validation.js"></script>
  </body>
</html>
