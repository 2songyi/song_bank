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

    <link rel="canonical" href="https://getbootstrap.com/docs/4.6/examples/pricing/">
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
    </style>

    <!-- Custom styles for this template -->
    
  </head>
   

<body>

   <!-- header -->
	<%@ include file="../incl/header.jsp"%>
	<!-- /header -->

  <div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
    <h1 class="display-4">계좌 개설 완료</h1>
    <p class="lead">다음과 같은 정보로 계좌가 개설되었습니다.</p>
  </div>

  <div class="container">
    <div class="row justify-content-center">

      <div class="col-md-8 order-md-1">

          <div class="mb-3">
            <table class="table">
              <thead>
                <tr>
                  <th scope="col">계좌 타입</th>
                  <th scope="col">계좌 번호</th>
                  <th scope="col">잔액</th>
                  <th scope="col">비밀번호</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>${accType}</td>
                  <td>${accountNum}</td>
                  <td>${balance}</td>
                  <td>${accountPasswd}</td>
                </tr>

              </tbody>
            </table>
          </div>
          <hr class="mb-4">

          <button class="btn btn-primary btn-lg btn-block" onclick="location.href='main'">확인</button>
       
      </div>
    </div>

      <!-- footer -->
	<%@ include file="../incl/footer.jsp"%>
	<!-- /footer -->
  </div>



</body>
</html>
