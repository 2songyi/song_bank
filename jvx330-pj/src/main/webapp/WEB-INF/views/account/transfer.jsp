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
  <script>
  function handleOnChange(e) {
	  // 선택된 데이터 가져오기
	 /*  const value = e.value; */
	  const value = e.value2;
	  
	  // 데이터 출력
	  document.getElementById('name').value
	    = value;
	}
  </script>
</head>

<body>
  <!-- header -->
	<%@ include file="../incl/header.jsp"%>
	<!-- /header -->

  <div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
    <h1 class="display-4">계좌 이체</h1>
    <p class="lead">다른사람의 계좌로 돈을 송금할 수 있습니다.</p>
    <p class="lead error">${error_msg}</p>
  </div>

  <div class="container">
    <div class="row justify-content-center">

      <div class="col-md-5 order-md-1">
        <form action="transfer" method="post" class="needs-validation" novalidate>

          <div class="mb-3">
            <label for="username">보내시는 분</label>
            <select name="outAccountNum" class="custom-select d-block w-100" id="outAccountNum" required>
              <c:forEach var="accountList" items="${accountList}" varStatus="status">
              	<option value="${fn:split(accountList, ',')[0]}">${fn:split(accountList, ',')[0]}</option>
              </c:forEach>
            </select>
            <div class="invalid-feedback" style="width: 100%;">
              보내는 분 정보는 필수정보입니다.
            </div>
          </div>

          <div class="mb-3">
            <label for="inAccountNum">받으시는 분</label>
            <!-- <input type="text" name="inAccountNum" class="form-control" id="inAccountNum" placeholder="계좌번호" required> -->
            <select name="inAccountNum" class="custom-select d-block w-100" id="inAccountNum" onchange="handleOnChange(this)" required>
              <c:forEach var="allAccountList" items="${allAccountList}" varStatus="status">
              	<option value="${fn:split(allAccountList, ',')[0]}" value2="${fn:split(accountList, ',')[5]}">${fn:split(allAccountList, ',')[0]}</option>
              </c:forEach>
            </select>
            
            <div class="invalid-feedback" style="width: 100%;">
             	받는 분 정보는 필수정보입니다.
            </div>
          </div>
		  
		 <!-- <div class="mb-3">
            <label for="money">받는 분 성함</label>
            <input type="text" name="name" class="form-control" id="name" readonly>
            <div class="invalid-feedback" style="width: 100%;">
              최초입금액은 필수입니다.
            </div>
          </div>  -->
          
          <div class="mb-3">
            <label for="money">송금액</label>
            <input type="number" name="money" class="form-control" id="money" placeholder="송금액(원)" required>
            <div class="invalid-feedback" style="width: 100%;">
              최초입금액은 필수입니다.
            </div>
          </div>
          <div class="mb-3">
            <label for="accountPasswd">계좌 비밀번호</label>
            <input type="password" name="accountPasswd" class="form-control" id="accountPasswd" placeholder="계좌 비밀번호" required>
            <div class="invalid-feedback" style="width: 100%;">
              최초입금액은 필수입니다.
            </div>
          </div>
          <hr class="mb-4">

          <button class="btn btn-primary btn-lg btn-block" type="submit">계좌 이체</button>
        </form>
      </div>
    </div>

      <!-- footer -->
	<%@ include file="../incl/footer.jsp"%>
	<!-- /footer -->
  </div>



</body>

</html>
