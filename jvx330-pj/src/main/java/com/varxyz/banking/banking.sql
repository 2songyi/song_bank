CREATE TABLE Account(
	aid				BIGINT		PRIMARY KEY AUTO_INCREMENT,
	customerId		BIGINT		NOT NULL,
	accountNum		CHAR(11)	NOT NULL, -- 000-00-0000
	accType			CHAR		NOT NULL 	DEFAULT 'S',
	balance			DOUBLE		NOT NULL	DEFAULT 0,
	accountPasswd	CHAR(4)		NOT NULL	DEFAULT '0000', 
	interestRate	DOUBLE		NOT NULL 	DEFAULT 0,
	overAmount		DOUBLE 		NOT NULL 	DEFAULT 0,
	regDate			TIMESTAMP	NOT NULL 	DEFAULT CURRENT_TIMESTAMP,
	CONSTRAINT	Account_customerId_FK	FOREIGN KEY(customerId) REFERENCES Customer(cid)
)AUTO_INCREMENT = 1001;



CREATE TABLE Customer(
	cid			BIGINT		PRIMARY KEY AUTO_INCREMENT,
	userId		VARCHAR(30)		NOT NULL,
	passwd		VARCHAR(20)	 	NOT NULL,
	name		VARCHAR(20) 	NOT NULL,
	ssn			VARCHAR(14) 	NOT NULL, -- 123456-7890123
	phone		VARCHAR(13) 	NOT NULL, -- 010-1234-5678
	regDate		TIMESTAMP		NOT NULL	DEFAULT CURRENT_TIMESTAMP
)AUTO_INCREMENT = 1001;

CREATE TABLE TRANSFER(
	tid				BIGINT		PRIMARY KEY AUTO_INCREMENT,
	outAccountNum	VARCHAR(14)	NOT NULL,
	inAccountNum	VARCHAR(14)	NOT NULL,
	money			DOUBLE		NOT NULL,
	balance			DOUBLE		NOT NULL,
	regDate			TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP
)AUTO_INCREMENT = 1001;

SELECT * FROM TRANSFER;

SELECT * FROM Account;
DROP TABLE Account;

SELECT * FROM Customer;
DROP TABLE Customer;

SELECT *
FROM Account a INNER JOIN Customer c 
ON a.customerId = c.cid WHERE a.customerId = 1001;

SELECT a.aid, a.customerId, a.accountNum, a.balance, a.interestRATE, a.overAmount, a.accType, c.name, c.ssn, c.phone, a.regDate
FROM Account a INNER JOIN Customer c 
ON a.customerId = c.cid WHERE a.customerId = ?";

SELECT balance FROM Account WHERE accountNum = '985-50-4801';

SELECT * FROM Customer WHERE userId = 'wer' AND passwd = 'wer';

DELETE FROM Customer WHERE cid=1003;

-- 계좌이체 비밀번호 확인
SELECT * FROM Account a INNER JOIN Customer c
ON (a.customerId = c.cid) WHERE a.accountNum = '013-03-5922' AND c.passwd='sdf';


SELECT * FROM Account a INNER JOIN Customer c
ON (a.customerId = c.cid);

SELECT * FROM Account a INNER JOIN Customer c
ON a.customerId = c.cid WHERE c.userId = 'sdf';

SELECT cid FROM Customer WHERE userId = 'sdf';

SELECT * FROM Account a INNER JOIN Customer c ON a.customerId = c.cid;


