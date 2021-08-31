<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import = "com.morder.model.MorderVO" %>
<%@ page import = "com.order_list.model.Order_listVO" %>
<%@ page import = "java.util.*" %>
<% 
	MorderVO morder = (MorderVO)session.getAttribute("morder");
	List<Order_listVO> list = (ArrayList<Order_listVO>)session.getAttribute("list");

	//session.setAttribute("morder", morder);
	//session.setAttribute("list", list);

	response.setHeader("Cache-Control","no-cache");
	response.setHeader("Pragma","no-cache"); 
	response.setDateHeader ("Expires", 0);
	//prevents caching at the proxy server

%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href='https://fonts.googleapis.com/css?family=Roboto:400,300,100' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/credit_card.css">
    <title>信用卡付款</title>
</head>
<body>
    <form class="credit-card">
        <div class="form-header">
          <h4 class="title">信用卡資料</h4>
        </div>
       
        <div class="form-body">
          <!-- Card Number -->
          <div class="card_title">信用卡卡號</div>
          <input type="text" class="card-number" name="orderCard" placeholder="1234-5678-1234-5678" value="${orderCard}">
       	  <div style="color:red"> ${errorMsgs.orderCard} </div>
          <!-- Date Field -->
          <div class="date-field">
            <div class="month">
                <div class="card_title">信用卡有效月份</div>
                <select name="orderCardMonth" value="${orderCardMonth}">
                    <option value="1">1月</option>
                    <option value="2">2月</option>
                    <option value="3">3月</option>
                    <option value="4">4月</option>
                    <option value="5">5月</option>
                    <option value="6">6月</option>
                    <option value="7">7月</option>
                    <option value="8">8月</option>
                    <option value="9">9月</option>
                    <option value="10">10月</option>
                    <option value="11">11月</option>
                    <option value="12">12月</option>
                </select>
            </div>
            <div class="year">
                <div class="card_title">信用卡有效年份</div>
                <select name="orderCardYear" value="${orderCardMonth}">
                    <option value="2021">2021</option>
                    <option value="2022">2022</option>
                    <option value="2023">2023</option>
                    <option value="2024">2024</option>
                    <option value="2025">2025</option>
                    <option value="2026">2026</option>
                    <option value="2027">2027</option>
                    <option value="2028">2028</option>
                    <option value="2029">2029</option>
                    <option value="2030">2030</option>
                    <option value="2031">2031</option>
                    <option value="2032">2032</option>
                </select>
            </div>
          </div>
       
          <!-- Card Verification Field -->
          <div class="card-verification">
            <div class="card_title">信用卡背後末3碼</div>
            <div class="cvv-input">
              <input type="text" class="cvv-number" name="cvvNumber" value="${cvvNumber}">
            </div>
          </div>
		  <div style="color:red"> ${errorMsgs.cvvNumber} </div>
		  <div style="color:red"> ${errorMsgs.addError} </div>
          <!-- Buttons -->
          <button type="submit" class="proceed-btn" name="action" value="aftercreditcard">確認付款</button>
        </div>
    </form>
</body>
</html>