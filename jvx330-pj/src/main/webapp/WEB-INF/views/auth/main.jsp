<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <link href="<c:url value='/resources/css/success_login.css'/>" rel="stylesheet">
  </head>
  <body>
	<!-- header -->
	<%@ include file="../incl/header.jsp"%>
	<!-- /header -->

<div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
 	<h1 class="display-4 font-weight-bold">SongBank</h1>
    <p class="lead">다양한 은행업무를 수행하세요</p>
</div>
<div class="container">
	  <form class="" action="find_transfer_history" method="post">
    <div class="row justify-content-center text-center">
      <c:forEach var="accountList" items="${accountList}" varStatus="status">
      <div class="col-sm-5">
        <div class="card" style="margin-bottom: 20px;">
          <div class="card-body">
            <p class="card-text text-left" style="margin-bottom: 5px;">${fn:split(accountList, ',')[0]}</p>
            <input type="hidden" name="accountNum" value="${fn:split(accountList, ',')[0]}">
            <p class="card-text">잔액</p>
            <h5 class="card-title text-center font-weight-bold">${fn:split(accountList, ',')[2]}원</h5>
            <div class="btn-group btn-group-lg " role="group" aria-label="Large button group">
              <button type="submit" class="btn btn-outline-primary" onclick="location.href='find_transfer_history'">거래내역</button>
              <button type="button" class="btn btn-outline-primary" onclick="location.href='transfer'">송금하기</button>
            </div>
          </div>
        </div>
      </div>
      </c:forEach>
    </div>
  </form>
  
    <hr class="mb-4">
  <div class="card-deck mb-3 text-center">
    <div class="card mb-4 shadow-sm">
      <div class="card-header">
        <h4 class="my-0 font-weight-normal">내 통장</h4>
      </div>
      <div class="card-body">
        <ul class="list-unstyled mt-3 mb-4">
          <li></li>
          <li></li>
        </ul>
        <button type="button" class="btn btn-lg btn-block btn-primary" onclick="location.href='find_account'">내 계좌 목록</button>
        <button type="button" class="btn btn-lg btn-block btn-primary" onclick="location.href='get_balance'">잔고 확인</button>
      </div>
    </div>
    <div class="card mb-4 shadow-sm">
      <div class="card-header">
        <h4 class="my-0 font-weight-normal">계좌 개설</h4>
      </div>
      <div class="card-body">
        <!-- <h1 class="card-title pricing-card-title">$15 <small class="text-muted">/ mo</small></h1> -->
        <ul class="list-unstyled mt-3 mb-4">
          <li>예금 통장 개설</li>
          <li>입출금 통장 개설</li>
        </ul>
        <button type="button" class="btn btn-lg btn-block btn-primary" onclick="location.href='add_account'">지금 계좌 개설하기</button>
      </div>
    </div>
    <div class="card mb-4 shadow-sm">
      <div class="card-header">
        <h4 class="my-0 font-weight-normal">송금</h4>
      </div>
      <div class="card-body">
        <!-- <h1 class="card-title pricing-card-title">$29 <small class="text-muted">/ mo</small></h1> -->
        <ul class="list-unstyled mt-3 mb-4">
          <li>다른사람의 계좌로<br>이체할 수 있습니다</li>
        </ul>
        <button type="button" class="btn btn-lg btn-block btn-primary" onclick="location.href='transfer'">계좌이체</button>
      </div>
    </div>
  </div>

  <!-- footer -->
	<%@ include file="../incl/footer.jsp"%>
	<!-- /footer -->
</div>



  </body>
</html>
