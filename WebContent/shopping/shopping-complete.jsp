<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.morder.model.MorderVO" %>
<%@ page import="java.util.*" %>
<%@ page import="com.order_list.model.Order_listVO" %>
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
	<link rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/shoppingcart.css">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/paying_delivery.css">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/shopping_complete.css">
</head>
<body>
<jsp:include page="/header.jsp" flush="true" />
	<!-- Start Checking status -->
	<div class="container py-5">
		<div class="row">
			<div class="col-lg-4">
				<h1 class="h2 pb-4">訂單明細</h1>
			</div>
			<div class="paying container">
				<div class="paying_status row">
					<div class="status_bar col-3" id="checking_purchasing_list" style="background-color: #f8f9fa; padding: 15px; color: black;">確認購物清單</div>
					<div class="status_bar col-3" id="choosing_method" style="background-color: #f8f9fa; padding: 0 auto; color: black;">選擇付款方式及運送資料</div>
					<div class="status_bar col-3" id="purchasing_completed" style="background-color: #59AB6E; color: white; padding: 15px;">購物完成</div>
				</div>
			</div>
		</div>
	</div>
	<!-- End Checking status -->

	<!-- Start Checking purchasing data -->
	<section class="container py-5">
		<div class="shoppinglist">
			<div class="shopping_row">
				<table class="table table-bordered complete">
					<thead>
						<tr class="merchandiseproperty">
							<th scope="col">商品名稱</th>
							<th scope="col">尺寸</th>
							<th scope="col">單價</th>
							<th scope="col" class="merchandisequantity1">件數</th>
							<th scope="col">金額</th>
						</tr>
					</thead>
					<jsp:useBean id="productSpecService" class="com.productspec.model.ProductSpecService"/>
					<jsp:useBean id="productService" class="com.product.model.ProductService"></jsp:useBean>
					<c:forEach var="order_listVO" items="${newList}">
					<c:set var="getProductSpec" value="${productSpecService.getOneProduct(order_listVO.productSpecId)}"></c:set>
					<tbody>
						<tr>
							<td class="merchandisename">${productService.getOneProduct(getProductSpec.productSN).productName}</td>
							<td class="merchandisesize">${getProductSpec.productSpec}</td>
							<td class="merchandiseprice">${getProductSpec.productPrice}</td>
							<td class="merchandisequantity">
								<ul class="extra list-inline" style="left: -40px">
									<li class="list-inline-item text-right">
										<input type="hidden" name="product-quanity" value="${order_listVO.purchaseQuantity}">
									</li>
									<li class="list-inline-item">
										<span class="badge bg-secondary var-value">${order_listVO.purchaseQuantity}</span>
									</li>
								</ul>
							</td>
							<td class="merchandiseamount">${getProductSpec.productPrice * order_listVO.purchaseQuantity}</td>
						</tr>
					</tbody>
					</c:forEach>
				</table>
			</div>
			<div class="container orderlist">
				<div class="row justify-content-start">
					<div class="col">訂單編號 : ${newMorder.orderSN}</div>
				</div>
				<div class="row justify-content-start">
					<div class="col">訂單日期 : ${purchasingDate}</div>
				</div>
				<div class="row justify-content-start">
					<div class="col">訂單狀態 : 
						<c:if test="${newMorder.deliveryStatus == 0}">未出貨</c:if>
						<c:if test="${newMorder.deliveryStatus == 1}">已出貨</c:if>
						<c:if test="${newMorder.deliveryStatus == 2}">已送達</c:if>					 
					</div>
				</div>
				<div class="row justify-content-start">
					<div class="col">取貨方式 : 
						<c:if test="${newMorder.orderDeliveyTypeId == 14001}">宅配</c:if>
						<c:if test="${newMorder.orderDeliveyTypeId == 14002}">超商取貨</c:if>
					</div>
				</div>
			</div>
			<div class="list_block receiver">收貨人資料</div>
			<form class="row g-3 receiver_data home">
				<div class="col-md-12">
					<label for="inputReceiver" class="form-label">收貨人 : ${newMorder.receiver}</label> 
				</div>
				<div class="col-md-12">
					<label for="inputReceiverPhone" class="form-label">聯絡電話  : ${newMorder.receiverPhone}</label>
				</div>
				<div class="col-12">
					<label for="inputReceiverAddress" class="form-label">取貨 :</label>
					<span id="inputReceiverAddress1">
					<c:if test="${newMorder.orderDeliveyTypeId == 14001}">${newMorder.receiverAddress}</c:if>
					<c:if test="${newMorder.orderDeliveyTypeId == 14002}">${newMorder.storeName}</c:if>	
					</span>
				</div>
			</form>
		</div>
		<div class="list_block info">付款資訊</div>
		<fieldset class="row mb-3 payingtype">
			<div class="col-sm-5">
				<div class="form-check">
					<label class="form-check-label" for="cash">
						<c:if test="${newMorder.orderPayment == 0}">貨到付款</c:if>
						<c:if test="${newMorder.orderPayment == 1}">信用卡付款</c:if>
					</label>
				</div>
			</div>
		</fieldset>
		<jsp:useBean id="couponService" class="com.coupon.model.CouponService"/>
		<div class="under_list container">
			<div class="under_list_row row">
				<div class="coupon col-md-4 offset-md-3">
					優惠券折扣: 
					<span id="couponnumber">
						${coupon.couponDiscount}
					</span>
				</div>
				<div class="deliveryfee col-md-2 offset-md-8">運費: 
					<c:if test="${newMorder.orderDeliveyTypeId == 14001}">100</c:if>
					<c:if test="${newMorder.orderDeliveyTypeId == 14002}">80</c:if>
				</div>
				<div class="totalamount col-md-2 offset-md-8">總金額: ${newMorder.totalPrice}</div>
			</div>
		</div>
	</section>
	<!-- End Checking purchasing data -->

	<!-- Start Button -->
	<div class="button_before_footer">
		<div class="shopping">
			<button class="btn btn-success btn-lg">
				<a href="<%=request.getContextPath()%>/shopping/SportifyShop.do?action=shop">繼續購物</a>
			</button>
		</div>
	</div>
	<!-- End Button -->

	<jsp:include page="/footer.jsp" flush="true" />
	<!-- Start Script -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js"></script>
    <script src="<%=request.getContextPath() %>/assets/js/jquery-3.6.0.min.js"></script>
    <script src="<%=request.getContextPath() %>/assets/js/jquery-migrate-1.2.1.min.js"></script>
    <script src="<%=request.getContextPath() %>/assets/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <script src="<%=request.getContextPath() %>/assets/js/templatemo.js"></script>
    <script src="<%=request.getContextPath() %>/assets/js/custom.js"></script>
	<script src="<%= request.getContextPath() %>/assets/js/shoppingcart.js" charset="UTF-8"></script>
    <!-- End Script -->

</body>
</html>