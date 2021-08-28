<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
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
    <title>�H�Υd�I��</title>
</head>
<body>
    <form class="credit-card">
        <div class="form-header">
          <h4 class="title">�H�Υd���</h4>
        </div>
       
        <div class="form-body">
          <!-- Card Number -->
          <div class="card_title">�H�Υd�d��</div>
          <input type="text" class="card-number" name="orderCard" placeholder="1234-5678-1234-5678" value="${orderCard}">
       	  <div style="color:red"> ${errorMsgs.orderCard} </div>
          <!-- Date Field -->
          <div class="date-field">
            <div class="month">
                <div class="card_title">�H�Υd���Ĥ��</div>
                <select name="orderCardMonth" value="${orderCardMonth}">
                    <option value="1">1��</option>
                    <option value="2">2��</option>
                    <option value="3">3��</option>
                    <option value="4">4��</option>
                    <option value="5">5��</option>
                    <option value="6">6��</option>
                    <option value="7">7��</option>
                    <option value="8">8��</option>
                    <option value="9">9��</option>
                    <option value="10">10��</option>
                    <option value="11">11��</option>
                    <option value="12">12��</option>
                </select>
            </div>
            <div class="year">
                <div class="card_title">�H�Υd���Ħ~��</div>
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
            <div class="card_title">�H�Υd�I�᥽3�X</div>
            <div class="cvv-input">
              <input type="text" class="cvv-number" name="cvvNumber" value="${cvvNumber}">
            </div>
          </div>
		  <div style="color:red"> ${errorMsgs.cvvNumber} </div>
		  <div style="color:red"> ${errorMsgs.addError} </div>
          <!-- Buttons -->
          <button type="submit" class="proceed-btn" name="action" value="aftercreditcard">�T�{�I��</button>
        </div>
    </form>
</body>
</html>