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
  <body>

<!-- header -->
	<%@ include file="../incl/header.jsp"%>
	<!-- /header -->

<div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
  <h1 class="display-4">계좌 개설</h1>
  <p class="lead">입출금 통장 혹은 예금 통장을 개설할 수 있습니다.<br>다음 양식을 채우고 계좌 개설 버튼을 눌러주세요.</p>
</div>

<div class="container">
  <div class="row justify-content-center">

    <div class="col-md-5 order-md-1">
      <form action="add_account" method="post" class="needs-validation" novalidate>

        <div class="mb-3">
          <label for="username">고객번호</label>
            <input type="text" name="cid" class="form-control" id="cid" value="${cid}" required readonly>
            <div class="invalid-feedback" style="width: 100%;">
              고객번호는 필수정보입니다.
            </div>
        </div>

        <div class="mb-3">
          <label for="accType">계좌 종류</label>
          <select name="accType" class="custom-select d-block w-100" id="accType" required>
            <option value="S">예금 계좌</option>
            <option value="C">입출금 계좌</option>
          </select>
        </div>

        <div class="mb-3">
          <label for="username">최초 입금액</label>
            <input type="number" name="balance" class="form-control" id="balance" placeholder="최초 입금액(원)" required>
            <div class="invalid-feedback" style="width: 100%;">
              최초입금액은 필수입니다.
            </div>
        </div>
        
        <div class="mb-3">
          <label for="accountPasswd">계좌 비밀번호</label>
            <input type="text" name="accountPasswd" class="form-control" id="accountPasswd" placeholder="4자리 비밀번호" required
            maxlength="4" minlength="4">
            <div class="invalid-feedback" style="width: 100%;">
              비밀번호은 필수입니다.
            </div>
        </div>
        
        <hr class="mb-4">
        <div class="custom-control custom-checkbox">
          <input type="checkbox" class="custom-control-input" id="same-address" onClick="agreeCheck(this.form)">
          <label class="custom-control-label" for="same-address">계좌 개설 약관에 동의합니다. (필수)</label>
        </div>
        <hr class="mb-4">

        <button name="checkButton" class="btn btn-primary btn-lg btn-block" type="submit" disabled>계좌 개설</button>
      </form>
    </div>
  </div>

    <!-- footer -->
	<%@ include file="../incl/footer.jsp"%>
	<!-- /footer -->
</div>



  </body>
</html>
