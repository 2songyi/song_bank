<%@ page language="java" import="java.net.URLDecoder" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm">
  <h5 class="my-0 mr-md-auto font-weight-normal font-weight-bold"><a href="main">SongBank</a></h5>
  <nav class="my-2 my-md-0 mr-md-3">
  	<span>${userId}님 환영합니다.</span>
      <a class="p-2 text-dark" href="find_account">내 통장</a>
      <a class="p-2 text-dark" href="get_balance">잔고</a>
      <a class="p-2 text-dark" href="transfer">송금</a>
      <a class="p-2 text-dark" href="add_account">계좌 개설</a>
  </nav>
  <a class="btn btn-outline-primary" href="logout">Log out</a>
</div>