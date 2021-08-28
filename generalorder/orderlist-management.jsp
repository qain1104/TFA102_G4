<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>

<%
response.setHeader("Cache-Control","no-cache");
response.setHeader("Pragma","no-cache"); 
response.setDateHeader ("Expires", 0);
//prevents caching at the proxy server
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
        會員中心<span id="purchasing_order">我的商品訂單</span>
    </div>
    <div class="order_number">${morder.orderSN}</div>
    <!-- End Memeber Center -->


    <!-- Start Order management -->
    <table class="table table-light order_management">
        <thead>
          <tr>
            <th scope="col">名稱</th>
            <th scope="col">尺寸</th>
            <th scope="col">價格</th>
            <th scope="col">數量</th>
            <th scope="col">產品圖片</th>
            <th scope="col">出貨狀態</th>
            <th scope="col">小計</th>
          </tr>
        </thead>
        <jsp:useBean id="productSpecService" class="com.productspec.model.ProductSpecService"/>
        <jsp:useBean id="productService" class="com.product.model.ProductService"/>
        <jsp:useBean id="MorderService" class="com.morder.model.MorderService"/>
        <tbody>
        <c:forEach var="orderListVO" items="${orderList}">
        <c:set var="getProductSpec" value="${productSpecService.getOneProduct(orderListVO.productSpecId)}"></c:set>
          <tr>
            <td class="align-middle productName" data-sn="${orderListVO.orderListSN}">${productService.getOneProduct(getProductSpec.productSN).productName}</td>
            <td class="align-middle productSpec">
            	${getProductSpec.productSpec}
                <input type="text" class="form-control d-none productSpec_update input-sm" name="updateSpec" size="5">
            </td>
            <td class="align-middle productPrice">${getProductSpec.productPrice}</td>
            <td class="align-middle purchaseQuantity">
            	${orderListVO.purchaseQuantity}
                <input type="text" class="form-control d-none purchaseQuantity_update input-sm" name="updateQuantity" size="5">
            </td>
            <td class="align-middle productImage"><img src="<%=request.getContextPath() %>/ProductImage?productSN=${getProductSpec.productSN}" alt="product_image" style="width: 100px;"></td>
            <td class="align-middle deliveryStatus">
			<c:if test="${morder.deliveryStatus == 0}">未出貨</c:if>  
			<c:if test="${morder.deliveryStatus == 1}">已出貨</c:if>
			<c:if test="${morder.deliveryStatus == 2}">已送達</c:if>        
            </td>
            <td class="align-middle item_amount">${getProductSpec.productPrice * orderListVO.purchaseQuantity}</td>
          </tr>
        </c:forEach>
        </tbody>
    </table>
    <!-- End Order management -->

    <!-- Start Delivery Information -->
    <div class="under_list container">
        <div class="under_list_row row delivery_information">
            <div class="type col-md-12 offset-md-1"></div>
            <div class="receiver col-md-2 offset-md-1">收貨人 : ${morder.receiver}</div>
            <div class="receiver_phone col-md-7 offset-md-1">連絡電話 : ${morder.receiverPhone}</div>
            <c:if test="${morder.orderDeliveyTypeId == 14001}">
            <div class="receiver_address col-md-12 offset-md-1">地址 : ${morder.receiverAddress}</div>
            </c:if>
			<c:if test="${morder.orderDeliveyTypeId == 14002}">
            <div class="stor_name col-md-2 offset-md-1">超商 : ${morder.storeName}</div>
            <div class="stor_no col-md-2 offset-md-1">超商編號 : ${morder.storeId}</div>
            <div class="stor_address col-md-12 offset-md-1">超商地址 : ${morder.storeAddress}</div>
            </c:if>
        </div>
    </div>
    <!-- End Delivery Information -->	
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