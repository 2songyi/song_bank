<%@ page language="java" import="java.net.URLEncoder" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!doctype html>
<html lang="en">

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
    
    p.lead.error {
    	color : red;
    }
  </style>

  <!-- Custom styles for this template -->
  
</head>

<body>
  <!-- header -->
	<%@ include file="../incl/header.jsp"%>
	<!-- /header -->

  <div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
  	<h1 class="display-4">거래내역 조회</h1>
	</div>

<div class="container">
  <div class="row justify-content-center">

    <div class="col-md-9 order-md-1">
      <table class="table">
  <thead>
    <tr>
      <th scope="col">No</th>
      <th scope="col">내 계좌</th>
      <th scope="col">받는 계좌</th>
      <th scope="col">송금액</th>
      <th scope="col">잔액</th>
      <th scope="col">송금일</th>
    </tr>
  </thead>
  <tbody>
  	<c:forEach var="transferList" items="${transferList}" varStatus="status">
    <tr>
       <th scope="row">${status.index + 1}</th>
   	   <td>${fn:split(transferList, ',')[0]}</td>
       <td>${fn:split(transferList, ',')[1]}</td>
       <td>${fn:split(transferList, ',')[2]}</td>
       <td>${fn:split(transferList, ',')[3]}</td>
       <td>${fn:split(transferList, ',')[4]}</td>
   	</tr>
	</c:forEach>
  </tbody>
</table>
    </div>
  </div>

   <!-- footer -->
	<%@ include file="../incl/footer.jsp"%>
	<!-- /footer -->
</div>



</body>

</html>
