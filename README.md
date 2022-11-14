# Song Bank
수행기간 : 2022.07.25 ~ 2022.07.31

## 접속 링크🖥️
http://13.125.200.41:8080/songbank/banking/login

## 2022.11.01 ~ 리팩토링 진행 중
1. 회원가입 비밀번호 암호화 적용
2. 계자이체 프로시저 적용
2. AWS 배포 완료

## 1. 프로젝트 소개
입출금통장, 예금통장을 개설하고 계좌이체를 할 수 있는 은행시스템을 구현한 프로젝트입니다.

### 1.1 프로젝트 개요
```
회원가입한 은행 회원은 입출금통장, 예금통장을 개설하고 타인의 계좌로 계좌이체 할 수 있습니다.
본인이 지금까지 개설한 통장을 한눈에 확인할 수 있고, 잔액을 조회할 수 있습니다.
```
## 2. 프로젝트 구조
### 2.1 요구사항
> - 로그인, 로그아웃
> - 회원가입
> - 계좌 개설
> - 계좌 이체
> - 내 계좌 목록 확인
> - 계좌별 잔고 확인
> - 전체 고객 조회

### 2.2 클래스 다이어그램
![뱅킹클래스다이어그램](https://user-images.githubusercontent.com/90902468/189020255-2e840770-c9dc-4add-838b-d2e6ba0157e1.png)
### 2.3 개발도구
> - JAVA
> - Spring
> - Mysql
> - tomcat server
> - HTML, CSS
> - JSP
> - BootStrap
> - cos.jar

## 3. 주요 코드
```
@Transactional
@Override
public void transfer(String outAccountNum, String inAccountNum, double money) {
	double balance1 = getBalance(outAccountNum) - money; //출금계좌용
	double balance2 = getBalance(inAccountNum) + money; //입금계좌용
		
	withDraw(outAccountNum, balance1);
	deposit(inAccountNum, balance2);
		
	// outAccountNum계좌에서 money만큼 출금 withdraw
	// inAccountNum 계좌에 money만큼 입금 deposit
		
	// 계좌이체 시 거래내역 추가하기
	addtransferHistory(outAccountNum, inAccountNum, money, balance1);
}
```
계좌이체를 구현하는 service 메서드입니다.
출금, 입금 중 하나만 실패여도 전체 rollback을 구현하기 위해 트랜잭션을 사용했습니다.
둘 다 성공해야 commit되는 분리할 수 없는 하나의 단위로 취급했습니다.

## 4. 주요 상세페이지
- 메인페이지
![songbankMain](https://user-images.githubusercontent.com/90902468/189832623-f2a9cb4b-637b-47aa-95a0-100126db313c.png)
- 계좌 개설 페이지
![Songbank1](https://user-images.githubusercontent.com/90902468/189832645-61e14c46-226f-4c7a-ba4f-eb860e43326a.png)
- 계좌 이체 페이지
![Songbank2](https://user-images.githubusercontent.com/90902468/189832656-06f38c0c-e51d-4e11-b207-46f62a5c5cb9.png)

