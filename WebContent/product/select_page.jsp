<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.productspec.model.*"%>
<%@ page import="com.productimage.model.*"%>

<%
	Integer productSN = new Integer(request.getParameter("productSN"));

	ProductSpecService productspecSvc = new ProductSpecService();
	List<ProductSpecVO> list1 = productspecSvc.getProductSpec(productSN);
	pageContext.setAttribute("list1", list1);
	
	ProductService productSvc = new ProductService();
	ProductVO productVO = productSvc.getOneProduct(productSN);
	pageContext.setAttribute("productVO", productVO);
	ProductVO proVO = (ProductVO) pageContext.getAttribute("productVO");
	
	ProductImageService proImgSvc = new ProductImageService();
	List<ProductImageVO>imagelist = proImgSvc.getProductImageVO(productSN);
	pageContext.setAttribute("imagelist", imagelist);
	
	List<ProductImageVO> productimagelist = proImgSvc.getProductImageVO(productVO.getProductSN());
	
%>

<jsp:useBean id="productSpecSvc" scope="page"
	class="com.productspec.model.ProductSpecService" />
<%-- ${ProductSpecVO.XXXXX} --%>
<html>
<head>
<meta charset="utf-8">
<title>Sportify Product: Home</title>
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


	<!-- Open Content -->
	<section class="bg-light">
		<div class="container pb-5">
			<div class="row">
				<!-- left section start -->
				<div class="col mt-5">
					<div class="card mb-3">
						<img class="card-img img-fluid"
							src="<%=request.getContextPath()%>/ProductImageServlet?productSN=${productVO.productSN}"
							alt="Card image cap" id="product-detail">
					</div>

					<div class="row">
						<!--Start Controls-->
						<div class="col-1 align-self-center">
							<a href="#multi-item-example" role="button" data-bs-slide="prev">
								<i class="text-dark fas fa-chevron-left"></i> <span
								class="sr-only">Previous</span>
							</a>
						</div>
						<!--End Controls-->
						<!--Start Carousel Wrapper-->
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
						<div class="col-1 align-self-center">
							<a href="#multi-item-example" role="button" data-bs-slide="next">
								<i class="text-dark fas fa-chevron-right"></i> <span
								class="sr-only">Next</span>
							</a>
						</div>
						<!--End Controls-->

					</div>
				</div>
				<!-- left section end -->

				<div class="col-lg-7 mt-5">
					<div class="card">
						<div class="card-body">
							<h1 class="h2">${productVO.productName}</h1>
							<br>
							<h5 style="display: none">商品編號:</h5>
							<p style="display: none">${productVO.productSN}</p>
							<div>
								<ul class="list-inline">
									<li class="list-inline-item">
										<h5>商品類別:</h5>
									</li>
									<li class="list-inline-item">
										<p class="text-muted">
											<c:if test="${productVO.productClass == 0}">女生衣著</c:if>
											<c:if test="${productVO.productClass == 1}">男生衣著</c:if>
											<c:if test="${productVO.productClass == 2}">鞋類</c:if>
											<c:if test="${productVO.productClass == 3}">其他</c:if>
										</p>

									</li>
								</ul>
							</div>
							<h5>商品簡介:</h5>
							<p>${productVO.productDetail}</p>
							<ul class="list-inline">
								<li class="list-inline-item">
									<h5>商品品牌:</h5>
								</li>
								<li class="list-inline-item">
									<p class="text-muted">
										<strong>${productVO.productBrand}</strong>
									</p>
								</li>
							</ul>
							<table class="table align-middle table-striped">
								<thead>
									<tr>
										<th scope="col" style="display: none">產品規格編號</th>
										<th scope="col">價格</th>
										<th scope="col">規格</th>
										<th scope="col">數量</th>
										<th scope="col"></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="productSpecVO" items="${list1}">
										<td style="display: none">${productSpecVO.productSpecId}</td>
										<td>${productSpecVO.productPrice}</td>
										<td>${productSpecVO.productSpec}</td>
										<td>${productSpecVO.productStock}</td>
										<td>
											<FORM METHOD="post"
												ACTION="<%=request.getContextPath()%>/product/product.do"
												style="margin-bottom: 0px;">
												<input type="submit" value="修改"
													class="btn btn-primary position-relative btn-sm"> <input
													type="hidden" name="productSpecId"
													value="${productSpecVO.productSpecId}"><input
													type="hidden" name="productSN"
													value="${productVO.productSN}"> <input
													type="hidden" name="action" value="check_updateProductSpec">
											</FORM>
										</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
					<!-- 按鈕 -->
					<form action="" method="GET">
						<input type="hidden" name="product-title" value="Activewear">
						<div class="row pb-3">
							<div class="col d-grid">
								<button type="submit" class="btn btn-success btn-lg"
									name="action" value="back">取消</button>
							</div>
							<div class="col d-grid">
								<button type="submit" class="btn btn-success btn-lg"
									name="action" value="back">確定</button>
							</div>
						</div>
					</form>
				</div>
			</div>
	</section>
	
	<!-- Button trigger modal -->
<!-- <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal"> -->
<!--   Launch demo modal -->
<!-- </button> -->

<!-- Modal -->
<!-- <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true"> -->
<!--   <div class="modal-dialog"> -->
<!--     <div class="modal-content"> -->
<!--       <div class="modal-header"> -->
<!--         <h5 class="modal-title" id="exampleModalLabel">Modal title</h5> -->
<!--         <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button> -->
<!--       </div> -->
<!--       <div class="modal-body"> -->
<!--         ... -->
<!--       </div> -->
<!--       <div class="modal-footer"> -->
<!--         <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button> -->
<!--         <button type="button" class="btn btn-primary">Save changes</button> -->
<!--       </div> -->
<!--     </div> -->
<!--   </div> -->
<!-- </div> -->
	
	
	
	
	<!-- Close Content -->

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
</body>
</html>