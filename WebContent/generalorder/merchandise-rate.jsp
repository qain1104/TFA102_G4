<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="java.util.*, com.cartList.model.CartListVO, com.cartList.model.CartListService" %>
<!DOCTYPE html>
<% 

	response.setHeader("Cache-Control","no-cache");
	response.setHeader("Pragma","no-cache"); 
	response.setDateHeader ("Expires", 0);
		
%>
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
        會員中心<span id="trackinglist">我的評價</span>
    </div>
    <!-- End Memeber Center -->


    <!-- Start Order management -->
    <table class="table table-light merchandise_trackinglist">
        <thead>
          <tr>
            <th scope="col">購買日期</th>
            <th scope="col">產品名稱</th>
            <th scope="col">評價</th>
            <th scope="col">評論內容</th>
            <th scope="col">操作</th>
          </tr>
        </thead>
        <tbody>
        <jsp:useBean id="productService" class="com.product.model.ProductService"/>
        <jsp:useBean id="productSpecService" class="com.productspec.model.ProductSpecService"/>
        <c:forEach var="forReviewMap" items="${forReviewMap}">
        <c:forEach var="forReviewOrderlist" items="${forReviewMap.value}">
          <tr data-listsn="${forReviewOrderlist.orderListSN}">
          	<c:set var="purchaseDate" value="${forReviewMap.key.purchaseDate}"/>
            <fmt:formatDate value="${purchaseDate}" var="date" pattern="yyyy-MM-dd"/>
            <td class="align-middle orderListSN">${date}</td>
            <td class="align-middle productName">${productService.getOneProduct(productSpecService.getOneProduct(forReviewOrderlist.productSpecId).productSN).productName}</td>
            <td class="align-middle productRate" data-rate="${forReviewOrderlist.productRate}">
                <span class="star ${forReviewOrderlist.productRate >= 1 ? '-on' : ''}" data-star="1"><i class="fas fa-star"></i></span>
                <span class="star ${forReviewOrderlist.productRate >= 2 ? '-on' : ''}" data-star="2"><i class="fas fa-star"></i></span>
                <span class="star ${forReviewOrderlist.productRate >= 3 ? '-on' : ''}" data-star="3"><i class="fas fa-star"></i></span>
                <span class="star ${forReviewOrderlist.productRate >= 4 ? '-on' : ''}" data-star="4"><i class="fas fa-star"></i></span>
                <span class="star ${forReviewOrderlist.productRate >= 5 ? '-on' : ''}" data-star="5"><i class="fas fa-star"></i></span>
            </td>
            <td class="align-middle productFeedback">
                <div class="form-group feedback_area">
                    <span class="feedback">${forReviewOrderlist.productFeedback}</span>
                    <textarea class="form-control feedback -none" id="exampleFormControlTextarea1"></textarea>
                </div>
            </td>
            <td class="align-middle operation">
                <button class="btn-edit" style="border: none; padding: 0; background: none; outline: none;"><i class="far fa-edit btn-success text-white" style="width: 30px; height: 30px; padding: 5px; border-radius: 5px;"></i></button>
                <button class="btn-save -none" style="border: none; padding: 0; background: none; outline: none;"><i class="far fa-save btn-success text-white" style="width: 30px; height: 30px; padding: 5px; border-radius: 5px;"></i></button>
            </td>
          </tr>
        </c:forEach>
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