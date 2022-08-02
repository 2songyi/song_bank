<%@ page language="java" import="java.net.URLEncoder" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
    <h1 class="display-4">잔액조회</h1>
    <p class="lead"></p>
  </div>

  <div class="container">
    <div class="row justify-content-center">

      <div class="col-md-5 order-md-1">
        <form action="" method="post" class="needs-validation" novalidate>

          <div class="mb-3">
            <label for="accType">잔액을 조회할 계좌를 선택해주세요</label>
            <select name="accountNum" class="custom-select d-block w-100" id="accType" required>
              <c:forEach var="accountList" items="${accountList}" varStatus="status">
              	<option value="${fn:split(accountList, ',')[0]}">${fn:split(accountList, ',')[0]}</option>
              </c:forEach>
            </select>
          </div>

          <hr class="mb-4">

          <button class="btn btn-primary btn-lg btn-block" type="submit">잔액 조회</button>
        </form>

        <hr class="mb-4">
         <div class="mb-3" >
          <h4 class="text-center">${accountNum}</h4><br>
          <h3 class="text-center">${balance}</h3>
			
         </div>
         <button class="btn btn-primary btn-lg btn-block" onclick="location.href='main'">메인화면으로 돌아가기</button>
        </div>


      </div>
      <!-- footer -->
	<%@ include file="../incl/footer.jsp"%>
	<!-- /footer -->
    </div>





</body>
</html>
