<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.productspec.model.*"%>
<%@ page import="com.productimage.model.*"%>

<%
	ProductVO productVO = (ProductVO) request.getAttribute("productVO");
	ProductSpecVO productspecVO = (ProductSpecVO) request.getAttribute("productSpecVO");
	ProductImageVO productimageVO = (ProductImageVO) request.getAttribute("productImageVO");
	session.setAttribute("corpUserId",new Integer(2001));

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>商品資料新增 - addProduct.jsp</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon"
	href="<%=request.getContextPath()%>/assets/images/favicon.png">

<!-- 介面排版用的 -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/bootstrap.min.css">
<!-- 設定字型 -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;200;300;400;500;700;900&display=swap">
<!-- 匯入圖片 -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/fontawesome.min.css">
<!-- 自己的CSS -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/product.css">
<!-- Slick -->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/assets/css/slick-theme.css">
</head>
<body>


	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<!-- 商品資訊本體 -->
	<form METHOD="post" ACTION="product.do" name="form1"
		enctype="multipart/form-data">
		<section class="bg-light">
			<div class="container pb-3 align-items-center">
				<div class="row">
					<div class="col-lg-7 mt-5 col align-self-center" style="width: 60%"">
						<div class="card">
							<div class="card-body">
								<h1 class="h2">新增商品</h1>
								<h5>
									<label>商品名稱：<input type="text"
										name="productName" size="45"
										value="<%=(productVO == null) ? "透氣背心" : productVO.getProductName()%>"></label>
								</h5>

								<h5>
									<label style="display: none">企業會員編號： <input type="text"
										name="corpUserId" size="45" readonly
										value="<%=(productVO == null) ? "2001" : productVO.getCorpUserId()%>">
									</label>
								</h5>
								<h5>
									<label>價格：<input type="TEXT" name="productPrice"
										size="45"
										value="<%=(productspecVO == null) ? "300" : productspecVO.getProductPrice()%>" /></label>
								</h5>
								<h5>
									<label>類別：<font color=red><b>*</b></font> <select
										id="category size=" 1" name="productClass">
											<option disabled required>分類</option>
											<option value="0"
												${(productVO.productClass==0)? "selected":""}>女生衣著</option>
											<option selected value="1"
												${(productVO.productClass==1)? "selected":""}>男生衣著</option>
											<option value="2"
												${(productVO.productClass==2)? "selected":""}>鞋類</option>
											<option value="3"
												${(productVO.productClass==3)? "selected":""}>其他</option>
									</select>
									</label>
								</h5>

								<h5>
									<label for=" brand">品牌：<input type="text"
										name="productBrand" size="45"
										value="<%=(productVO == null) ? "NIKE" : productVO.getProductBrand()%>"></label>
								</h5>
								<h5 class="mb-3">
									<label for="descr" class="form-label">簡介：</label>
									<textarea class="form-control" id="descri" name="productDetail"
										style="resize:both" style="width: 75%">asdf1987</textarea>
								</h5>

								<jsp:useBean id="productSpecSvc" scope="page"
									class="com.productspec.model.ProductSpecService" />

								<h5>
									<label>尺寸：<input type="text" name="productSpec"
										size="45"
										value="<%=(productVO == null) ? "XS" : productspecVO.getProductSpec()%>"></label>
								</h5>
								<h5>
									<label>數量：<input type="text" name="productStock"
										size="45"
										value="<%=(productVO == null) ? "1" : productspecVO.getProductStock()%>"></label>
								</h5>

								<h5>
									<label style="display: none">建立日期：<input
										name="productOnsale" style="display: none"
										type="datetime-local"></label>
								</h5>

								<div>
									<label for="status" style="display: none">商品狀態：</label><select
										size="1" name="productStatus" style="display: none"
										readonly="readonly">
										<option selected="true">狀態</option>
										<option value="0"
											selected
											${(productVO.productStatus==0)? "selected":""}>下架</option>
										<option value="1"
											${(productVO.productStatus==1)? "selected":""}>審核通過</option>
										<option value="2"
											${(productVO.productStatus==2)? "selected":""}>審核未通過</option>
									</select>
								</div>

								<jsp:useBean id="productImageSvc" scope="page"
									class="com.productimage.model.ProductImageService" />

								<h5>
									<label>圖片1： <input type="file" accept="image/*"
										name="productImage1"></label>
								</h5>

								<h5>
									<label>圖片2： <input type="file" accept="image/*"
										name="productImage2"></label>
								</h5>
								<br>

								<div class="row pb-3">
									<div class="col d-grid">
										<button name="action" class="btn btn-success btn-lg"
											value="back">取消</button>
									</div>

									<div class="col d-grid">
										<input type="hidden" name="action" value="insert"> <input
											type="submit" class="btn btn-success btn-lg" value="新增">
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
		</section>
	</form>
	<a
		href="<%=request.getContextPath()%>/product/product.do?action=getMy_Product">productManagement
		for test</a>
	<!-- Close Content -->

	<!-- Start Script -->
	<script
		src="<%=request.getContextPath()%>/assets/js/jquery-3.6.0.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/assets/js/jquery-migrate-1.2.1.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
	<!-- <script src="<%=request.getContextPath()%>/assets/js/bootstrap.bundle.min.js"></script> -->
	<script src="<%=request.getContextPath()%>/assets/js/templatemo.js"></script>
	<script src="<%=request.getContextPath()%>/assets/js/custom.js"></script>
	<!-- <script src="<%=request.getContextPath()%>/assets/js/product.js"></script> -->

	<!-- End Script -->
</body>
</html>