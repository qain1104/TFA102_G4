CREATE DATABASE IF NOT EXISTS TFA102_G4;

USE TFA102_G4;

DROP TABLE IF EXISTS PRODUCTIMAGE; 
DROP TABLE IF EXISTS PRODUCT_WATCH_LIST;
DROP TABLE IF EXISTS ORDER_LIST;
DROP TABLE IF EXISTS MORDER;
DROP TABLE IF EXISTS COUPON;
DROP TABLE IF EXISTS PRODUCTSPEC;
DROP TABLE IF EXISTS ORDER_DELIVERY_TYPE;  
DROP TABLE IF EXISTS VENUE_NOPHOURS_TABLE;
DROP TABLE IF EXISTS VENUE_WATCHLIST;
DROP TABLE IF EXISTS RENTAL_LIST;
DROP TABLE IF EXISTS ARTICLE_LIKE;
DROP TABLE IF EXISTS REPORT;
DROP TABLE IF EXISTS PARTICIPANT;
DROP TABLE IF EXISTS SPORT_NEWS;
DROP TABLE IF EXISTS CSRESPONSE;
DROP TABLE IF EXISTS SPORTS_GROUP;
DROP TABLE IF EXISTS REPLY_LIKE;
DROP TABLE IF EXISTS REPLY;
DROP TABLE IF EXISTS ARTICLE;
DROP TABLE IF EXISTS PRODUCT;
DROP TABLE IF EXISTS VENUE;   
DROP TABLE IF EXISTS GENERAL_USER;
DROP TABLE IF EXISTS CORP_USER;
DROP TABLE IF EXISTS WEB_MANAGER;

-- 一般會員表 -- 
SET auto_increment_offset=1001;
SET auto_increment_increment=1;
CREATE TABLE GENERAL_USER (
userId 		INT NOT NULL AUTO_INCREMENT COMMENT '一般會員編號(主鍵)',
registerStatus 	INT(1) NOT NULL COMMENT '註冊狀態',
userAccount 	VARCHAR(30) NOT NULL COMMENT '會員帳號',
userPassword 	VARCHAR(30) NOT NULL COMMENT '會員密碼',
userName 		VARCHAR(30) NOT NULL COMMENT '會員名稱',
id 		CHAR(10) NOT NULL COMMENT '身分證字號',
email 		VARCHAR(20) NOT NULL COMMENT '信箱',
address 		VARCHAR(50) NOT NULL COMMENT '地址',
phone 		VARCHAR(10) NOT NULL COMMENT '聯絡電話',
profilePic 		BLOB COMMENT '用戶大頭貼',
createdTime 	DATE NOT NULL COMMENT '帳號建立時間',
gender 		INT(1) NOT NULL COMMENT '性別',
CONSTRAINT GENERAL_USER_PK PRIMARY KEY (userId)
) AUTO_INCREMENT = 1001 , COMMENT '一般會員表';

INSERT INTO GENERAL_USER(registerStatus, userAccount, userPassword, userName, id, email, address, phone, profilePic , createdTime ,gender)
VALUES(1,'eric111','hi1234','Erictw','A123321456','eric1234@gmail.com','台北市萬華區萬華路6號6樓','0912345678','','2021-05-01 10:28:29',1),
      (1,'amy111','as1234','fatAmy19','B223321456','fatAmy234@gmail.com','台北市大同區大同路2號4樓','0922345678','','2021-05-02 11:38:29',0),
      (1,'bosh111','zx1234','angryBosh22','C123321456','ngryBo34@gmail.com','新北市萬里區萬里路7號12樓','0932345678','','2021-05-03 12:02:29',1),
      (1,'vicky0123','af1234','TaiwanV6','D223321456','eraV634@gmail.com','台北市百齡區百齡路336號6樓','0942345678','','2021-05-04 17:03:29',0),
      (1,'Kevin109','adaad1234','Benz987','E123321456','nz94@gmail.com','新北市永和區永和路456號16樓','0952345678','','2021-05-04 19:28:29',1),
      (0,'lofic1sd1','lofd34','Lofitdw','F123321456','lofDX4@gmail.com','台北市士林區小北61號4樓','0912245678','','2021-05-04 20:18:20',1),
      (0,';lad1203xx','ladf234','laDm9','G223321456','ladyxoxo34@gmail.com','台北市中山區中山北路二段221號4樓','0924345678','','2021-06-04 00:08:29',0),
      (0,'hwl9oxd12','zxhwo4','alloa22','H123321456','allo7x@gmail.com','新北市樹林區樹林路721號14樓','0937345678','','2021-06-23 13:08:29',1),
      (0,'xxy0076d3','afacd14','VENUM126','I223321456','view1op@gmail.com','台北市中正區正北路236號8樓','0942945678','','2021-07-04 10:08:29',0),
      (0,'KKLxxa19','asdfg324','shark1219','J123321456','nsado12@gmail.com','新北市汐止區水光路46號9樓','0952545678','','2021-07-06 07:18:39',1);



-- 企業會員表 --
SET auto_increment_offset=2001;
SET auto_increment_increment=1;
CREATE TABLE CORP_USER(
corpUserId 		INT NOT NULL AUTO_INCREMENT COMMENT '企業會員編號(主鍵)',
registerStatus 	INT(1) NOT NULL COMMENT'註冊狀態',
corpAccount 	VARCHAR(30) NOT NULL COMMENT '企業會員帳號',
corpPassword 	VARCHAR(30) NOT NULL COMMENT '會員密碼',
companyName 	VARCHAR(30) NOT NULL COMMENT '公司名稱',
ltdNo 		CHAR(10) NOT NULL COMMENT '公司行號',
email 		VARCHAR(20) NOT NULL COMMENT '信箱',
phone 		VARCHAR(10) NOT NULL COMMENT '聯絡電話',
address 		VARCHAR(50) NOT NULL COMMENT '地址',
profilePic 		BLOB COMMENT '用戶大頭貼',
createdTime 	DATE NOT NULL COMMENT '帳號建立時間',
CONSTRAINT CORP_USER_PK PRIMARY KEY (corpUserId)
) AUTO_INCREMENT = 2001 , COMMENT '企業會員表';

INSERT INTO CORP_USER(registerStatus, corpAccount, corpPassword, companyName, ltdNo, email, phone, address, profilePic , createdTime)
VALUES(1,'xxxFLC41','xx1234','xxxxtw','ABCDE12345','DDD1234@gmail.com','2441212126','高雄市新政路12號8樓','','2021-05-04 10:08:29'),
      (1,'AAA3LKS41','BB1234','ASDtw','AB21Q12345','VVV1234@gmail.com','2456212126','屏東縣檳榔路391號5樓','','2021-06-14 09:08:19'),
      (1,'VVx1GS341','CC1234','xVOLL','ABC1E12545','XVOLL34@gmail.com','2441212126','台北市中正區濟南路41號2樓','','2021-06-24 10:08:29'),
      (0,'RED52681','BD1234','REDtw','CX21Q12765','REDtw1@gmail.com','2456212126','台中市群馬二路91號1樓','','2021-07-01 08:28:39'),
      (0,'BSHOEx12431','ZZ1234','BsHOES','ABXCD12345','BsHOES@gmail.com','2441212126','高雄市墾丁路123號18樓','','2021-07-01 11:58:20'),
      (0,'CCA12211','ASD12345','CCA','AB12Q12345','CCAHJ1@gmail.com','2456212126','花蓮縣卑南路77號','','2021-07-02 16:58:20');



-- 後臺管理者 --
SET auto_increment_offset=3001;
SET auto_increment_increment=1;
CREATE TABLE WEB_MANAGER(
managerId 		INT NOT NULL AUTO_INCREMENT COMMENT '後臺管理者編號(主鍵)',
managerName 	VARCHAR(30) NOT NULL COMMENT '後臺管理者名稱',
managerEmail 	VARCHAR(30) NOT NULL COMMENT '信箱',
managerAccount 	VARCHAR(20) NOT NULL COMMENT '後臺管理者帳號',
managerPassword 	VARCHAR(20) NOT NULL COMMENT '後臺管理者密碼',
managerPic 		BLOB COMMENT '用戶大頭貼',
managerStatus 	INT(1) NOT NULL COMMENT '後臺管理者狀態',
CONSTRAINT WEB_MANAGER_PK PRIMARY KEY (managerId)
) AUTO_INCREMENT = 3001 , COMMENT  '後臺管理員表';

INSERT INTO WEB_MANAGER(managerName, managerEmail, managerAccount, managerPassword, managerPic, managerStatus)
VALUES('manager3001', 'stes090909@yahoo.com.tw', 'wm3001', '6e18fn', null, 0),
      ('manager3002', 'hodi0219@hotmail.com', 'wm3002', 'x43sdf1n', null, 0),
      ('manager3003', 'timmyhsu1809@gmail.com', 'wm3003', 'ac921xo1', null, 0);




-- 以下為商城部分 --
-- 商品 --
SET auto_increment_offset=11001;
SET auto_increment_increment=1;
CREATE TABLE PRODUCT(
productSN	  INT NOT NULL AUTO_INCREMENT COMMENT '商品編號' ,
corpUserId 	  INT NOT NULL COMMENT '企業會員編號' ,
productName   VARCHAR(50) NOT NULL COMMENT '商品名稱' ,
productClass  INT(1) NOT NULL COMMENT '商品類別' ,
productDetail TEXT NOT NULL COMMENT '商品簡介' ,
productBrand  VARCHAR(30) NOT NULL COMMENT '商品品牌' ,
productOnsale DATE NOT NULL COMMENT '上架日期' ,
productStatus INT(1) NOT NULL COMMENT '商品狀態' ,
CONSTRAINT PRODUCT_PK PRIMARY KEY (productSN) ,
CONSTRAINT PRODUCT_CORPUSERID_FK FOREIGN KEY (corpUserId) REFERENCES CORP_USER(corpUserId)
) AUTO_INCREMENT = 11001 , COMMENT  '商品';

INSERT INTO PRODUCT(corpUserId, productName, productClass, productDetail, productBrand, productOnsale, productStatus)
VALUES  (2001, '運動排汗T-shirt(白色)', 0, '舒適合身、快速排汗第一選擇', 'NIKE', '2021-07-16', 0),
        (2002, '運動排汗T-shirt(黑色)', 1, '舒適合身、快速排汗第一選擇', 'ADIDAS', '2021-07-16', 0),
        (2003, '慢跑鞋(藍色)', 2, '採用高級布料製作，讓你風馳電擎', 'SKECHERS', '2021-07-16', 0);

-- 商品規格資訊 --
SET auto_increment_offset=12001;
SET auto_increment_increment=1;
CREATE TABLE PRODUCTSPEC(
productSpecId   INT NOT NULL AUTO_INCREMENT COMMENT '商品規格編號' ,
productSN 	    INT NOT NULL COMMENT '商品編號' ,
productStock    INT(10) NOT NULL COMMENT '商品數量' ,
productPrice    INT(10) NOT NULL COMMENT '商品價格' ,
productSpec     VARCHAR(20) NOT NULL COMMENT '商品規格' ,
CONSTRAINT PRODUCTSPEC_PK PRIMARY KEY (productSpecId) ,
CONSTRAINT PRODUCTSPEC_PRODUCTSN_FK FOREIGN KEY (productSN) REFERENCES PRODUCT(productSN)
) AUTO_INCREMENT = 12001 , COMMENT  '商品規格資訊';

INSERT INTO PRODUCTSPEC(productSN, productStock, productPrice, productSpec)
VALUES(11001, 20, 490, 'XS'),
(11001, 50, 490, 'S'),
(11001, 99, 490, 'M'),
(11001, 50, 490, 'L'),
(11001, 30, 490, 'XL'),
(11001, 20, 490, 'XL'),
(11002, 20, 490, 'XS'),
(11002, 50, 390, 'S'),
(11002, 99, 390, 'M'),
(11002, 50, 390, 'L'),
(11002, 30, 390, 'XL'),
(11002, 20, 390, '2XL'),
(11003, 20, 1990, '25'),
(11003, 50, 1990, '26'),
(11003, 99, 1990, '26.5'),
(11003, 50, 1990, '27'),
(11003, 30, 1990, '28'),
(11003, 20, 1990, '29'),
(11003, 20, 1990, '29.5'),
(11003, 50, 1990, '30');



-- 商品圖片 --
SET auto_increment_offset=13001;
SET auto_increment_increment=1;
CREATE TABLE PRODUCTIMAGE(
productImageSN  INT NOT NULL AUTO_INCREMENT COMMENT '商品圖片編號' ,
productSN       INT NOT NULL COMMENT '商品編號',
productImage    MEDIUMBLOB  COMMENT '商品圖片' ,
CONSTRAINT PRODUCTIMAGE_PK PRIMARY KEY (productImageSN) ,
CONSTRAINT PRODUCTIMAGE_PRODUCTSN_FK FOREIGN KEY (productSN) REFERENCES PRODUCT(productSN)
) AUTO_INCREMENT = 13001 , COMMENT  '商品圖片';



-- 商品運送方式 --
SET auto_increment_offset = 14001;
SET auto_increment_increment = 1;
CREATE TABLE ORDER_DELIVERY_TYPE(
    orderDeliveryTypeId INT AUTO_INCREMENT NOT NULL COMMENT '運送方式編號',
    deliveryType 	VARCHAR(50) NOT NULL COMMENT '運送方式',
    deliveryFee 	INT NOT NULL COMMENT '運費',
    CONSTRAINT ORDERDELIVERYTYPE_PK PRIMARY KEY (orderDeliveryTypeId)
) AUTO_INCREMENT = 14001 , COMMENT '運送方式';

INSERT INTO ORDER_DELIVERY_TYPE(deliveryType, deliveryFee)
VALUES ('宅配', 100),
       ('超商取貨', 80);
               
               
               
-- 優惠券 --
SET auto_increment_offset = 15001;
SET auto_increment_increment = 1;
CREATE TABLE COUPON(
    couponId 	INT NOT NULL AUTO_INCREMENT COMMENT '優惠券編號',
    couponInfo 	VARCHAR(50) NOT NULL COMMENT '使用資訊',
    couponName 	VARCHAR(50) NOT NULL COMMENT '優惠券名稱',
    couponStarting 	TIMESTAMP NOT NULL COMMENT '開始時間',
    couponEnding	TIMESTAMP NOT NULL COMMENT '結束時間',
    couponSN	VARCHAR(10) NOT NULL COMMENT '序號',
    couponDiscount 	INT NOT NULL COMMENT '優惠券折扣',
    CONSTRAINT COUPON_PK PRIMARY KEY (couponId)
)  AUTO_INCREMENT = 15001 , COMMENT '優惠券';

INSERT INTO COUPON (couponInfo, couponName, couponStarting, couponEnding, couponSN, couponDiscount)
VALUES ("滿1000折200", "滿1000折200", 20210731, 20210810, "FATHERSDAY", 200),
       ("滿500折100", "滿500折100", 20210710, 20210728, "ONLYFORU", 100);



-- 訂單,因為order為關鍵字,故加上M,想表示訂單主體的意思 --
SET auto_increment_offset = 16001;
SET auto_increment_increment = 1;
CREATE TABLE MORDER(
    orderSN 	INT NOT NULL AUTO_INCREMENT COMMENT '訂單編號', 
    userId 		INT NOT NULL COMMENT '會員編號',
    couponId 	INT COMMENT '優惠券編號',
    purchaseDate 	DATE NOT NULL COMMENT '購買日期',
    totalPrice 	INT NOT NULL COMMENT '訂單總額',
    orderPayment 	INT(1) NOT NULL COMMENT '付款方式',
    orderCard 	VARCHAR(20) COMMENT '訂購人信用卡號',
    orderCardYear 	VARCHAR(4) COMMENT '信用卡有效期限(年)',
    orderCardMonth 	VARCHAR(2) COMMENT '信用卡有效期限(月)',
    orderCompleteDate 	TIMESTAMP COMMENT '完成日期',
    orderDeliveyTypeId 	INT NOT NULL COMMENT '運送方式編號',
    receiver 	VARCHAR(20) NOT NULL COMMENT '收件人',
    receiverPhone 	VARCHAR(10) NOT NULL COMMENT '收件人手機',
    receiverAddress 	VARCHAR(50) COMMENT '收件人地址',
	storeId			INT COMMENT '門市編號',
    storeName       VARCHAR(100) COMMENT '門市名稱',
    storeAddress	VARCHAR(100) COMMENT '門市地址',
    shippingDate 	TIMESTAMP COMMENT '出貨日期',
    deliveryDate 	TIMESTAMP COMMENT '收貨日期',
    deliveryStatus 	INT(1) NOT NULL COMMENT '物流狀態',
    CONSTRAINT MORDER_PK PRIMARY KEY (orderSN),
    CONSTRAINT MORDER_USERID_FK FOREIGN KEY (userId) REFERENCES GENERAL_USER (userId),
    CONSTRAINT MORDER_COUPONID_FK FOREIGN KEY (couponId) REFERENCES COUPON (couponId), 
    CONSTRAINT MORDER_ORDERDELIVERYTYPEID_FK FOREIGN KEY (orderDeliveyTypeId) REFERENCES ORDER_DELIVERY_TYPE (orderDeliveryTypeId)
) AUTO_INCREMENT = 16001 , COMMENT '訂單' ;

INSERT INTO MORDER (userId, couponId, purchaseDate, totalPrice, orderPayment, orderCard, orderCardYear, orderCardMonth, orderCompleteDate,
 orderDeliveyTypeId, receiver, receiverPhone, receiverAddress, storeId,	storeName, storeAddress, shippingDate, deliveryDate, deliveryStatus )
VALUES (1001, 15002, 20210715, 2970, 0, NULL, NULL, NULL, 20210715, 14001, 'Erictw', '0912345678', '台北市萬華區萬華路6號6樓', NULL, NULL, NULL, 20210716, 20210717, 2),
       (1002, 15002, 20210710, 1970, 1, "9000-0444-1234-0000", "2025", 8, 20210711, 14002, 'fatAmy19', '0922345678', NULL, 193557, '八德', '台北市中正區臨沂街1號1樓', 20210712, 20210714, 2),
       (1003, NULL, 20210717, 490, 0, NULL, NULL, NULL, NULL, 14001, 'angryBosh22', '0932345678', '新北市萬里區萬里路7號12樓', NULL, NULL, NULL, NULL, NULL, 0);
-- 以上為關係1 

-- 追蹤明細-商品 --
SET auto_increment_offset = 17001;
SET auto_increment_increment = 1;
CREATE TABLE PRODUCT_WATCH_LIST(
    pwlSN 		INT NOT NULL AUTO_INCREMENT COMMENT '追蹤明細編號',
    productSN 	INT NOT NULL COMMENT '商品編號',
    userId 		INT NOT NULL COMMENT '會員編號',
    CONSTRAINT PRODUCTWATCHLIST_PK PRIMARY KEY (pwlSN),
    CONSTRAINT PRODUCTWATCHLIST_PRODUCTSN_FK FOREIGN KEY (productSN) REFERENCES PRODUCT (productSN),
    CONSTRAINT PRODUCTWATCHLIST_USERID_FK FOREIGN KEY (userId) REFERENCES GENERAL_USER (userId)
)  AUTO_INCREMENT = 17001 , COMMENT '追蹤明細-商品';

INSERT INTO PRODUCT_WATCH_LIST (productSN, userId)
VALUES (11001, 1003),
       (11003, 1004),
       (11001, 1005);



-- 商品訂單明細 --
SET auto_increment_offset = 18001;
SET auto_increment_increment = 1;
CREATE TABLE ORDER_LIST(
    orderListSN 	INT NOT NULL AUTO_INCREMENT COMMENT '明細流水號',
    productSpecId 	INT NOT NULL COMMENT '商品規格編號',
    orderSN 	INT NOT NULL COMMENT '訂單編號',
    orderCost 	INT NOT NULL COMMENT '項目金額',
    purchaseQuantity 	INT NOT NULL COMMENT '購買數量',
    productRate 	INT(1) COMMENT '商品評價',
    productFeedback 	VARCHAR(100) COMMENT '商品評價回覆內容',
    CONSTRAINT ORDERLIST_PK PRIMARY KEY (orderListSN),
    CONSTRAINT ORDERLIST_PRODUCTSPECID_FK FOREIGN KEY (productSpecId) REFERENCES PRODUCTSPEC (productSpecId),
    CONSTRAINT ORDERLIST_ORDERSN_FK FOREIGN KEY (orderSN) REFERENCES MORDER (orderSN)
) AUTO_INCREMENT = 18001 , COMMENT '商品訂單明細' ;

INSERT INTO ORDER_LIST(productSpecId, orderSN, orderCost, purchaseQuantity, productRate, productFeedback)
    VALUES  (12003, 16001, 980, 2, 4, "舒適"),
	(12015, 16001, 1990, 1, 3, NULL ),
	(12016, 16002, 1990, 1, 3, NULL),
	(12010, 16003, 390, 1, NULL, NULL);



-- 以下為租借場地相關 --
-- 場地 --
SET auto_increment_offset =19001;
SET auto_increment_increment = 1;
CREATE TABLE VENUE(
venueSN 	  INT NOT NULL AUTO_INCREMENT COMMENT '場地編號',
corpUserId    INT NOT NULL COMMENT '企業會員編號' ,	
venueStatus   INT(1) NOT NULL COMMENT '審核狀態',	
venueName 	  VARCHAR(50) NOT NULL COMMENT '場地名稱',	
venueClass    INT(5) NOT NULL COMMENT '場地種類',	
venueInfo 	  TEXT COMMENT '場地資訊',	
venuePrice 	  INT NOT NULL COMMENT '場地預約價格',	
venueAddress  VARCHAR(100) NOT NULL COMMENT '場地地址',	
venuePic 	  BLOB  COMMENT '場地圖片',	
venueAccommodate INT NOT NULL COMMENT '容納人數',	
venuePhone 	  VARCHAR(10) NOT NULL COMMENT '場地電話',
CONSTRAINT VENUE_PK PRIMARY KEY (venueSN),
CONSTRAINT VENUE_CORPUSERID_FK FOREIGN KEY (corpUserId) REFERENCES CORP_USER(corpUserId)
) AUTO_INCREMENT = 19001 ,COMMENT '場地';

INSERT INTO VENUE (corpUserId, venueStatus, venueName, venueClass, venueInfo, venuePrice, venueAddress, venueAccommodate, venuePhone)
VALUES (2001, 0, '籃球場', 1, '場地不錯的籃球場', 150, '台北市大安區新生南路二段1號', 20, 0943568562),
(2002, 0, '網球場', 2, '設備很棒的網球場', 500, '台北市松山區南京東路四段6號', 12, 0923454523),
(2003, 0, '排球場', 3, '員工熱情的排球場', 300, '台北市大安區基隆路四段87號', 24, 0914325134),
(2004, 0, '壘球場', 3, '空間寬廣的壘球場', 300, '台北市士林區忠誠路二段77號', 50, 0969409526),
(2005, 0, '羽球場', 3, '場地整潔的羽球場', 300, '台北市萬華區水源路199號', 24, 0910008436);


-- 場地不開放時間表 --
SET auto_increment_offset = 20001;
SET auto_increment_increment = 1;
CREATE TABLE VENUE_NOPHOURS_TABLE(
venueNophoursSN INT NOT NULL AUTO_INCREMENT COMMENT '場地不開放時間編號',
venueSN 	    INT NOT NULL COMMENT '場地編號',
venueDate       DATE NOT NULL COMMENT '場地不開放日期',	
venueNophours   INT(3) ZEROFILL COMMENT '場地不開放時段',
CONSTRAINT VENUENOP_PK PRIMARY KEY (venueNophoursSN),
CONSTRAINT VENUENOP_VENUESN_FK FOREIGN KEY (venueSN) REFERENCES VENUE(venueSN)
) AUTO_INCREMENT = 20001 , COMMENT '場地不開放時間表';

INSERT INTO VENUE_NOPHOURS_TABLE (venueSN, venueDate, venueNophours) VALUES
(19001, STR_TO_DATE('2021-08-05','%Y-%m-%d'), 000),
(19002, STR_TO_DATE('2021-09-13','%Y-%m-%d'), 101),
(19003, STR_TO_DATE('2021-08-21','%Y-%m-%d'), 001);

-- 追蹤明細_場地 --
SET auto_increment_offset = 21001;
SET auto_increment_increment = 1;
CREATE TABLE VENUE_WATCHLIST(
venueWatchListSN 	INT NOT NULL AUTO_INCREMENT COMMENT '追蹤明細編號' ,
venueSN 	     	INT NOT NULL COMMENT '場地編號',
userId 	     		INT NOT NULL COMMENT '會員編號' ,
CONSTRAINT VENUEWATCHLIST_PK PRIMARY KEY (venueWatchListSN),
CONSTRAINT VENUEWATCHLIST_VENUESN_FK FOREIGN KEY (venueSN) REFERENCES VENUE(venueSN),
CONSTRAINT VENUEWATCHLIST_USERID_FK FOREIGN KEY (userId) REFERENCES GENERAL_USER(userId)
) AUTO_INCREMENT = 21001 , COMMENT '追蹤明細-場地';

INSERT INTO VENUE_WATCHLIST (venueSN, userId) VALUES 
(19001, 1001),
(19002, 1002),
(19003, 1003);

-- 租借單明細 --
SET auto_increment_offset = 22001;
SET auto_increment_increment = 1;
CREATE TABLE RENTAL_LIST(
rentalListSN    INT NOT NULL AUTO_INCREMENT COMMENT '租借單編號',
venueSN 	    INT NOT NULL COMMENT '場地編號',
userId          INT NOT NULL COMMENT '會員編號',
returnStatus    INT(1) NOT NULL COMMENT '歸還狀態',	
rentalDate      DATE NOT NULL COMMENT '租借日期',	
venueReview     VARCHAR(80) COMMENT '場地評論',	
beforeUse 	    BLOB COMMENT '場地使用前照片',	
afterUse 	    BLOB COMMENT '場地使用後照片',	
rentalTime 	    INT(3) ZEROFILL COMMENT '租借時段',
CONSTRAINT RENTAL_LIST_PK PRIMARY KEY (rentalListSN),
CONSTRAINT RENTAL_LIST_SN_FK FOREIGN KEY (venueSN) REFERENCES VENUE(venueSN),
CONSTRAINT RENTAL_LIST_USERID_FK FOREIGN KEY (userId) REFERENCES GENERAL_USER(userId)
) AUTO_INCREMENT = 22001 , COMMENT '租借單明細';

INSERT INTO RENTAL_LIST (venueSN, userId, returnStatus, rentalDate, venueReview, rentalTime) VALUES 
(19001, 1001, 0, STR_TO_DATE('2021-10-03','%Y-%m-%d'), '環境乾淨', 100),
(19002, 1002, 0, STR_TO_DATE('2021-09-30','%Y-%m-%d'), '設備齊全', 010),
(19003, 1003, 0, STR_TO_DATE('2021-08-24','%Y-%m-%d'), '夜晚燈光良好', 110); 


-- 以下為論壇、揪團、檢舉  --
-- 文章 --
SET auto_increment_offset = 4001;
SET auto_increment_increment = 1;
CREATE TABLE ARTICLE(
articleSN INT NOT NULL AUTO_INCREMENT COMMENT '文章編號',
userId INT NOT NULL COMMENT '會員編號',
articleClass INT(1) NOT NULL COMMENT '文章分類',
articleType INT(1) NOT NULL COMMENT '文章類型',
articleTitle VARCHAR(30) NOT NULL COMMENT '文章標題',
articleContent TEXT NOT NULL COMMENT '文章內容',
articlePop INT NOT NULL COMMENT '文章人氣',
articleLikes INT NOT NULL COMMENT '按讚數',
articleDATE DATE NOT NULL COMMENT '上架日期',
articleUpaDATE DATE COMMENT '編輯日期',
articleStatus INT(1) NOT NULL COMMENT '文章狀態',
CONSTRAINT ARTICLE_articleSN_PK PRIMARY KEY (articleSN),
CONSTRAINT ARTICLE_userId_FK FOREIGN KEY (userId) REFERENCES GENERAL_USER (userId)
) AUTO_INCREMENT = 4001, COMMENT '文章';

INSERT INTO ARTICLE(userId,articleClass,articleType,articleTitle,articleContent,articlePop,articleLikes,articleDATE,articleUpaDATE,articleStatus)
VALUES(1001,0,0,'搶頭香','就是愛搶頭香',1000,0,'2021-07-19 9:00',NULL,1),
(1001,0,1,'請問要怎麼不被檢舉','ㄏㄏ',900,0,'2021-07-19 9:05','2021-07-19 9:15',0),
(1001,1,1,'想要買東西','可是沒有錢該怎麼辦',20,0,'2021-07-19 9:20',NULL,0),
(1002,2,2,'棒球比賽場地','棒球場沒有冷氣超熱的，記得多帶幾瓶水喝',10,1,'2021-07-19 9:30',NULL,0),
(1003,1,2,'買了編號32125的球棒','真的很好用',300,2,'2021-07-19 9:45',NULL,0),
(1003,2,0,'如果打籃球不用手改用腳會怎樣','如題',100,1,'2021-07-19 9:55',NULL,0);

-- 文章按讚 --
SET auto_increment_offset = 5001;
SET auto_increment_increment = 1;
CREATE TABLE ARTICLE_LIKE(
articleLiekSN INT NOT NULL AUTO_INCREMENT COMMENT '文章按讚編號',
articleSN INT NOT NULL COMMENT '文章編號',
userId INT NOT NULL COMMENT '會員編號',
CONSTRAINT ARTICLE_LIKE_articleLiekSN_PK PRIMARY KEY (articleLiekSN),
CONSTRAINT ARTICLE_LIKE_articleSN_FK FOREIGN KEY (articleSN) REFERENCES ARTICLE (articleSN)
) AUTO_INCREMENT = 5001, COMMENT '文章按讚';

INSERT INTO ARTICLE_LIKE(articleSN,userId)
VALUES(4005,1001),
(4005,1002),
(4006,1003);


-- 文章回覆 --
SET auto_increment_offset = 6001;
SET auto_increment_increment = 1;
CREATE TABLE REPLY(
replySN INT NOT NULL AUTO_INCREMENT COMMENT '文章回覆編號',
articleSN INT NOT NULL COMMENT '文章編號',
userId INT NOT NULL COMMENT '會員編號' ,
replyContent TEXT NOT NULL COMMENT '文章內容',
replyLikes INT NOT NULL COMMENT '按讚數',
replyDATE DATE NOT NULL COMMENT '上架日期',
replyUpaDATE DATE COMMENT '編輯日期',
replyStatus INT(1) NOT NULL COMMENT '文章狀態',
CONSTRAINT REPLY_replySN_PK PRIMARY KEY (replySN),
CONSTRAINT REPLY_articleSN_FK FOREIGN KEY (articleSN) REFERENCES ARTICLE (articleSN),
CONSTRAINT REPLY_userId_FK FOREIGN KEY (userId) REFERENCES GENERAL_USER (userId)
) AUTO_INCREMENT = 6001 , COMMENT '文章回覆';

INSERT INTO REPLY(articleSN,userId,replyContent,replyLikes,replyDATE,replyUpaDATE,replyStatus)
VALUES(4005,1002,'我也買了，真的很棒',0,'2021-07-19 9:20',NULL,0),
(4003,1001,'(某些歧視言論)',0,'2021-07-19 10:10','2021-07-19 10:30',1),
(4005,1002,'我也買了，真的很棒',2,'2021-07-19 10:25',NULL,0),
(4006,1001,'ㄉㄌㄇㄎㄋㄇㄙㄌ',0,'2021-07-19 10:30',NULL,1),
(4006,1004,'那會跌倒',3,'2021-07-19 10:35',NULL,0);   


-- 回覆按讚 --
SET auto_increment_offset = 7001;
SET auto_increment_increment = 1;
CREATE TABLE REPLY_LIKE( 
replyLiekSN INT NOT NULL AUTO_INCREMENT COMMENT '回覆按讚編號',
replySN INT NOT NULL COMMENT '文章回覆編號',
userId INT NOT NULL COMMENT '會員編號',
CONSTRAINT REPLY_LIKE_replyLiekSN_PK PRIMARY KEY (replyLiekSN),
CONSTRAINT REPLY_LIKE_replySN_FK FOREIGN KEY (replySN) REFERENCES REPLY (replySN)
) AUTO_INCREMENT = 7001 , COMMENT '回覆按讚';

INSERT INTO REPLY_LIKE(replySN,userId)
VALUES(6003,1004),
(6005,1004),
(6005,1003);

-- 檢舉審核單 -- 
SET auto_increment_offset = 8001;
SET auto_increment_increment = 1;
CREATE TABLE REPORT( 
reportSN INT NOT NULL AUTO_INCREMENT COMMENT '檢舉編號',
articleSN INT NOT NULL COMMENT '文章編號',
replySN INT COMMENT '文章回覆編號',
userId INT NOT NULL COMMENT '檢舉人',
reportClass INT(1) NOT NULL COMMENT '檢舉分類',
reportContent TEXT COMMENT '檢舉內容',
reportDATE DATE NOT NULL COMMENT '檢舉時間',
reportStatus INT(1) NOT NULL COMMENT '檢舉狀態',
managerId INT COMMENT '後臺管理者',
reportAuditDATE DATE COMMENT '檢舉審核時間',
CONSTRAINT REPORT_reportSN_PK PRIMARY KEY (reportSN),
CONSTRAINT REPORT_articleSN_FK FOREIGN KEY (articleSN) REFERENCES ARTICLE (articleSN),
CONSTRAINT REPORT_replySN_FK FOREIGN KEY (replySN) REFERENCES REPLY (replySN),
CONSTRAINT REPORT_userId_FK FOREIGN KEY (userId) REFERENCES GENERAL_USER (userId),
CONSTRAINT REPORT_managerId_FK FOREIGN KEY (managerId) REFERENCES WEB_MANAGER (managerId)
) AUTO_INCREMENT = 8001, COMMENT '檢舉審核單';

INSERT INTO REPORT(articleSN,replySN,userId,reportClass,reportContent,reportDATE,reportStatus,managerId,reportAuditDATE)
VALUES(4001,NULL,1005,2,'廢文','2021-07-19 11:00',1,3001,'2021-07-19 13:05'),
(4003,6002,1005,0,NULL,'2021-07-19 11:10',1,3001,'2021-07-19 13:10'),
(4006,6004,1004,0,NULL,'2021-07-19 11:20',1,3001,'2021-07-19 13:15'),
(4005,6001,1005,1,NULL,'2021-07-19 11:30',1,3001,'2021-07-19 13:20'),
(4006,6005,1005,0,NULL,'2021-07-19 11:35',0,null,null);
   
-- 揪團 --
SET auto_increment_offset = 9001;
SET auto_increment_increment = 1;
CREATE TABLE SPORTS_GROUP(   
sportsGroupSN INT NOT NULL AUTO_INCREMENT COMMENT '揪團編號',
userId INT NOT NULL COMMENT '發起者',
sportsType VARCHAR(30) NOT NULL COMMENT '運動類型',
sportsLocation VARCHAR(30) NOT NULL COMMENT '場地資訊',
exerciseTime DATE NOT NULL COMMENT '運動時間',
numberUpLimit INT NOT NULL COMMENT '人數上限',
numberLowLimit INT NOT NULL COMMENT '人數下限',
registTime DATE NOT NULL COMMENT '報名時間',
registTimeEnd DATE NOT NULL COMMENT '報名截止時間',
issueDATE DATE NOT NULL COMMENT '發起時間',
participantNumber INT NOT NULL COMMENT '參加人數',
success INT(1) COMMENT '是否流團',
remarks TEXT COMMENT '備註',
CONSTRAINT SPORTS_GROUP_sportsGroupSN_PK PRIMARY KEY (sportsGroupSN),
CONSTRAINT SPORTS_GROUP_userId_FK FOREIGN KEY (userId) REFERENCES GENERAL_USER(userId)
) AUTO_INCREMENT = 9001 , COMMENT '揪團';

INSERT INTO SPORTS_GROUP(userId,sportsType,sportsLocation,exerciseTime,numberUpLimit,numberLowLimit,registTime,registTimeEnd,issueDATE,participantNumber,success,remarks)
VALUES(1005,'籃球','健康高中第一籃球場','2021-7-17 9:00',10,3,'2021-7-10','2021-7-15','2021-7-8 9:10',5,0,'有球不用帶'),
(1001,'籃球','健康高中第二籃球場','2021-7-12 9:00',10,3,'2021-7-11','2021-7-10','2021-7-8 9:20',0,1,'你當球'),
(1004,'羽球','羽球高中','2021-9-20 9:00',5,1,'2021-7-18','2021-9-18','2021-7-17 9:10',3,NULL,NULL),
(1004,'桌球','桌球高中','2021-7-18 9:00',3,0,'2021-7-17','2021-7-20','2021-7-17 9:15',2,NULL,123);

-- 揪團參加者 --
SET auto_increment_offset = 10001;
SET auto_increment_increment = 1;
CREATE TABLE PARTICIPANT(
ParticipantID INT NOT NULL AUTO_INCREMENT COMMENT '參加明細編號',
sportsGroupSN INT NOT NULL COMMENT '揪團編號',
userId INT NOT NULL COMMENT '參加者',
CONSTRAINT PARTICIPANT_ParticipantID_PK PRIMARY KEY (ParticipantID),
CONSTRAINT PARTICIPANT_sportsGroupSN_FK FOREIGN KEY (sportsGroupSN) REFERENCES SPORTS_GROUP (sportsGroupSN),
CONSTRAINT PARTICIPANT_userId_FK FOREIGN KEY (userId) REFERENCES GENERAL_USER (userId)
) AUTO_INCREMENT = 10001, COMMENT '揪團參加者明細編號';

INSERT INTO PARTICIPANT(sportsGroupSN,userId)
VALUES(9001,1003),
(9001,1004),
(9001,1006),
(9001,1002),
(9001,1007),
(9003,1006),
(9003,1005),
(9003,1003),
(9004,1002),
(9004,1003);



-- 以下為運動新聞表  --
-- 運動新聞表 --
SET auto_increment_offset=23001;
SET auto_increment_increment=1;
CREATE TABLE SPORT_NEWS(
	newsSn 	INT NOT NULL AUTO_INCREMENT COMMENT '新聞編號(主鍵)',
	managerId 	INT NOT NULL COMMENT '後臺管理者編號' ,
	title 	VARCHAR(20) NOT NULL COMMENT '標題',
	content 	TEXT NOT NULL COMMENT '內文',
	newsDate	DATE NOT NULL COMMENT '新聞日期',
	newsSource 	VARCHAR(20) NOT NULL COMMENT '新聞來源',
	newsType 	INT(1) NOT NULL COMMENT '賽事分類',
            CONSTRAINT SPORT_NEWS_PK PRIMARY KEY (newsSn),
            CONSTRAINT SPORT_NEWS_managerId_FK FOREIGN KEY (managerId) REFERENCES WEB_MANAGER (managerId)
) AUTO_INCREMENT = 23001 ,COMMENT '運動相關資訊';



-- 以下客服回應表單  --
-- 客服回應表單 --
SET auto_increment_offset=24001;
SET auto_increment_increment=1;
CREATE TABLE CSRESPONSE (
  responseSN 	INT NOT NULL AUTO_INCREMENT COMMENT '回覆編號',
  managerId 	INT NOT NULL COMMENT '後臺管理者',
  userId 		INT NOT NULL COMMENT '會員編號',
  csDescription 	MEDIUMTEXT NOT NULL COMMENT '問題描述',
  csResponse 	MEDIUMTEXT  COMMENT '回覆內容',
  submittedDate 	TIMESTAMP NOT NULL COMMENT '提交日期',
  closingDate 	TIMESTAMP COMMENT '結案日期',
  responseStatus 	INT NOT NULL COMMENT '回覆狀態',
  CONSTRAINT CSRESPONSE_PK PRIMARY KEY (responseSN),
  CONSTRAINT CSRESPONSE_MANAGERID_FK FOREIGN KEY (managerId) REFERENCES WEB_MANAGER(managerId),
  CONSTRAINT CSRESPONSE_USERID_FK FOREIGN KEY (userId) REFERENCES GENERAL_USER(userId)
  ) AUTO_INCREMENT = 24001 , COMMENT '客服回應表單' ;
  
INSERT INTO CSRESPONSE(managerId,userId,csDescription,csResponse,submittedDate,closingDate,responseStatus)
VALUES(3001,1003,'我的帳號登不進去','親愛的顧客你好非常抱歉造成你的困擾XXX',20210515,20210520,1),
(3001,1005,'我的貨品什麼時候到?','親愛的顧客你好非常抱歉造成你的困擾XXX',20210615,20210620,1),
(3002,1007,'為什麼我莫名其妙被檢舉',null,20210515,null,0),
(3003,1009,'我想查詢是否已扣款了',null,20210615,null,0);
  