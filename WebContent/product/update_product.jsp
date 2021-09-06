<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.productspec.model.*"%>
<%@ page import="com.productimage.model.*"%>
<%@ page import="java.util.List"%>


<%
	ProductVO productVO = (ProductVO) request.getAttribute("productVO"); //ProductServlet.java (Concroller) 存入req的produtVO物件 (包括幫忙取出的productVO, 也包括輸入資料錯誤時的productVO物件)
	ProductImageService pSvc = new ProductImageService();
	List<ProductImageVO> productimagelist = pSvc.getProductImageVO(productVO.getProductSN());
	session.setAttribute("corpUserId", new Integer(2001));
%>

<html>
<head>
<title>商品資料修改 - update_product.jsp</title>

<meta charset="BIG5">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=yes">

<link rel="shortcut icon"
	href="<%=request.getContextPath()%>/assets/img/logo1.png"
	type="image/x-icon" />
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;200;300;400;500;700;900&display=swap">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/fontawesome.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/bootstrap.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/templatemo.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/custom.css">

<!-- Slick -->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/assets/css/slick.min.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/assets/css/slick-theme.css">
</head>
<body bgcolor='white'>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>



	<!-- Open Content -->
	<section class="bg-light">
		<div class="container pb-5">
			<div class="row">
				<!-- 			left section start -->
				<div class="col mt-5">
					<div class="card mb-3">
						<img class="card-img img-fluid"
							src="<%=request.getContextPath()%>/ProductImageServlet?productSN=${productVO.productSN}"
							alt="Card image cap" id="product-detail">
					</div>


					<div class="row">
						<div id="multi-item-example"
							class="col-10 carousel slide carousel-multi-item"
							data-bs-ride="carousel">
							<!--Start Slides-->
							<div class="carousel-inner product-links-wap" role="listbox">

								<!--First slide-->
								<c:forEach var="productImageVO" items="${productImageList}">
									<div class="carousel-item active">
										<div class="row">
											<div class="col-4">
												<a href="#"> <img class="card-img img-fluid"
													src="<%=request.getContextPath()%>/AllProductImage?productImageSN=${productImageVO.productImageSN}"
													alt="Product Image 1">
												</a>
											</div>
										</div>
									</div>
								</c:forEach>
								<!--/.First slide-->

							</div>
							<!--End Slides-->
						</div>
						<!--End Carousel Wrapper-->
						<!--Start Controls-->

					</div>
				</div>
				<!-- left section end -->
				<div class="col-lg-7 mt-5">
					<form action="" method="GET">
						<div class="card">
							<div class="card-body">
								<h5 style="display: none">企業會員編號：</h5>
								<p>
									<input type="text" name="corpUserId" size="45"
										style="display: none" readonly value="2001">
								</p>
								<h5>商品名稱:</h5>
								<p>
									<input type="text" name="productName"
										value="${productVO.productName}">
								</p>
								<br>
								<div>
									<ul class="list-inline">
										<li class="list-inline-item">
											<h5>商品類別:</h5>
										</li>
										<li class="list-inline-item">
											<p class="text-muted">
												<select size="1" name="productClass">
													<option value="0"
														${(productVO.productClass == 0)? "selected" : ""}>女生衣著

													<option value="1"
														${(productVO.productClass == 1)? "selected" : ""}>男生衣著

													<option value="2"
														${(productVO.productClass == 2)? "selected" : ""}>鞋類

													<option value="3"
														${(productVO.productClass == 3)? "selected" : ""}>其他

												</select>
											</p>
										</li>
									</ul>

								</div>

								<h5>商品簡介:</h5>
								<textarea class="form-control" id="descri" name="productDetail"
									style="resize: both;" style="width: 75%"
									value="${productVO.productDetail}">${productVO.productDetail}</textarea>

								<ul class="list-inline">
									<li class="list-inline-item">
										<h5>商品品牌:</h5>
									</li>
									<li class="list-inline-item">
										<p class="text-muted">
											<input type="text" name="productBrand"
												value="${productVO.productBrand}">
										</p>
									</li>
								</ul>
								<h5 style="display: none">上架日期：</h5>
								<p>
									<input name="productOnsale" type="datetime-local"
										style="display: none" value="${productVO.productOnsale}">
								</p>


								<h5 style="display: none">商品狀態
								</h5>
								<p style="display: none">
									<select size="1" name="productStatus" readonly="readonly">
										<option value="0"
											${(productVO.productStatus==0)? "selected":""}>下架</option>
										<option value="1"
											${(productVO.productStatus==1)? "selected":""}>審核通過</option>
										<option value="2"
											disabled
										${(productVO.productStatus==2)?"selected":""}>審核未通過</option>
									</select>
								</p>
								<div>
									<input type="hidden" name="product-title" value="Activewear">
									<div class="row pb-3">
										<div class="col d-grid">
											<button type="submit" class="btn btn-success btn-lg"
												name="action" value="back">取消</button>
										</div>
										<div class="col d-grid">
											<input type="hidden" name="productSN"
												value="<%=productVO.getProductSN()%>">
											<button type="submit" class="btn btn-success btn-lg"
												name="action" value="update">送出</button>
										</div>
									</div>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>
	<!-- Close Content -->

	<!-- Start Script -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/assets/js/jquery-3.6.0.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/assets/js/jquery-migrate-1.2.1.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/assets/js/bootstrap.bundle.min.js"></script>
	<script src="<%=request.getContextPath()%>/assets/js/templatemo.js"></script>
	<script src="<%=request.getContextPath()%>/assets/js/custom.js"></script>
	<!-- End Script -->

	<!-- Start Slider Script -->
	<script src="<%=request.getContextPath()%>/assets/js/slick.min.js"></script>

</body>
</html>