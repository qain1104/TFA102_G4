<%@page import="com.product.model.ProductVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<% 

	response.setHeader("Cache-Control","no-cache");
	response.setHeader("Pragma","no-cache"); 
	response.setDateHeader ("Expires", 0);
		
%>
<!DOCTYPE html>
<html lang="en">
<head>
     <title>Sportify</title>
    <meta charset="BIG5">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=yes">

	<link rel="shortcut icon" href="<%= request.getContextPath() %>/assets/img/logo1.png" type="image/x-icon" />
	<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;200;300;400;500;700;900&display=swap">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/fontawesome.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/templatemo.css">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/custom.css">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/general_order.css">
</head>
<body>
<jsp:include page="/header.jsp" flush="true" />
   <!-- Start Member Center -->
    <div class="member_center">
        �|������<span id="purchasing_order">�ڪ��ӫ~�q��</span>
    </div>
    <!-- End Memeber Center -->


    <!-- Start Order management -->
    <table class="table table-light order_management">
        <thead>
          <tr>
            <th scope="col">�q��s��</th>
            <th scope="col">�ʶR���</th>
            <th scope="col">�B�e�覡</th>
            <th scope="col">�X�f���A</th>
            <th scope="col">�u�f��</th>
            <th scope="col">�ާ@</th>
          </tr>
        </thead>
        <tbody>
        <c:forEach var="userMorder" items="${userMorder}">
          <tr>
            <th scope="row" class="align-middle orderSN">${userMorder.orderSN}</th>
            <c:set var="purchaseDate" value="${userMorder.purchaseDate}"/>
            <fmt:formatDate value="${purchaseDate}" var="date" pattern="yyyy-MM-dd"/>
            <td class="align-middle purchaseDate">${date}</td>
            <td class="align-middle orderDeliveyTypeId">${(userMorder.orderDeliveyTypeId == 14001)? "�v�t" : "�W�Ө��f"}</td>
            <td class="align-middle deliveryStatus">
            <c:if test="${userMorder.deliveryStatus == 0}">���X�f</c:if>
            <c:if test="${userMorder.deliveryStatus == 1}">�w�X�f</c:if>
            <c:if test="${userMorder.deliveryStatus == 2}">�w�e�F</c:if>
            </td>
            <td class="align-middle couponId">
            <c:set var="couponId" value="${userMorder.couponId}"></c:set>
            <jsp:useBean id="couponService" class="com.coupon.model.CouponService"></jsp:useBean>
                <span class="couponSN">${couponService.getCoupon(couponId).couponSN}</span>
                <div class="coupon_information">
                                                    �u�f��Ǹ� : <span class="couponSNN">${couponService.getCoupon(couponId).couponSN}</span><br>
                                                    �ϥθ�T : <span class="couponInfo">${couponService.getCoupon(couponId).couponName}</span>
                </div>
            </td>
            <td class="align-middle operation">
                <p class="text-center"><a class="btn btn-success" href="<%= request.getContextPath() %>/order/MorderManagement.do?action=orderListManagement&orderSN=${userMorder.orderSN}">�˵�</a></p>
            </td>
          </tr>
        </c:forEach>
        </tbody>
    </table>
    <!-- End Order management -->
	<jsp:include page="/footer.jsp" flush="true" />
	<!-- Start Script -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js"></script>
    <script src="<%=request.getContextPath() %>/assets/js/jquery-3.6.0.min.js"></script>
    <script src="<%=request.getContextPath() %>/assets/js/jquery-migrate-1.2.1.min.js"></script>
    <script src="<%=request.getContextPath() %>/assets/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <script src="<%=request.getContextPath() %>/assets/js/templatemo.js"></script>
    <script src="<%=request.getContextPath() %>/assets/js/custom.js"></script>
	<script src="<%= request.getContextPath() %>/assets/js/general_order.js" charset="UTF-8"></script>
    <!-- End Script -->
</body>
</html>