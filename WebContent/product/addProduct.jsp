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
<title>�ӫ~��Ʒs�W - addProduct.jsp</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon"
	href="<%=request.getContextPath()%>/assets/images/favicon.png">

<!-- �����ƪ��Ϊ� -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/bootstrap.min.css">
<!-- �]�w�r�� -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;200;300;400;500;700;900&display=swap">
<!-- �פJ�Ϥ� -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/fontawesome.min.css">
<!-- �ۤv��CSS -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/product.css">
<!-- Slick -->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/assets/css/slick-theme.css">
</head>
<body>


	<%-- ���~��C --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">�Эץ��H�U���~:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<!-- �ӫ~��T���� -->
	<form METHOD="post" ACTION="product.do" name="form1"
		enctype="multipart/form-data">
		<section class="bg-light">
			<div class="container pb-3 align-items-center">
				<div class="row">
					<div class="col-lg-7 mt-5 col align-self-center" style="width: 60%"">
						<div class="card">
							<div class="card-body">
								<h1 class="h2">�s�W�ӫ~</h1>
								<h5>
									<label>�ӫ~�W�١G<input type="text"
										name="productName" size="45"
										value="<%=(productVO == null) ? "�z��I��" : productVO.getProductName()%>"></label>
								</h5>

								<h5>
									<label style="display: none">���~�|���s���G <input type="text"
										name="corpUserId" size="45" readonly
										value="<%=(productVO == null) ? "2001" : productVO.getCorpUserId()%>">
									</label>
								</h5>
								<h5>
									<label>����G<input type="TEXT" name="productPrice"
										size="45"
										value="<%=(productspecVO == null) ? "300" : productspecVO.getProductPrice()%>" /></label>
								</h5>
								<h5>
									<label>���O�G<font color=red><b>*</b></font> <select
										id="category size=" 1" name="productClass">
											<option disabled required>����</option>
											<option value="0"
												${(productVO.productClass==0)? "selected":""}>�k�ͦ��</option>
											<option selected value="1"
												${(productVO.productClass==1)? "selected":""}>�k�ͦ��</option>
											<option value="2"
												${(productVO.productClass==2)? "selected":""}>�c��</option>
											<option value="3"
												${(productVO.productClass==3)? "selected":""}>��L</option>
									</select>
									</label>
								</h5>

								<h5>
									<label for=" brand">�~�P�G<input type="text"
										name="productBrand" size="45"
										value="<%=(productVO == null) ? "NIKE" : productVO.getProductBrand()%>"></label>
								</h5>
								<h5 class="mb-3">
									<label for="descr" class="form-label">²���G</label>
									<textarea class="form-control" id="descri" name="productDetail"
										style="resize:both" style="width: 75%">asdf1987</textarea>
								</h5>

								<jsp:useBean id="productSpecSvc" scope="page"
									class="com.productspec.model.ProductSpecService" />

								<h5>
									<label>�ؤo�G<input type="text" name="productSpec"
										size="45"
										value="<%=(productVO == null) ? "XS" : productspecVO.getProductSpec()%>"></label>
								</h5>
								<h5>
									<label>�ƶq�G<input type="text" name="productStock"
										size="45"
										value="<%=(productVO == null) ? "1" : productspecVO.getProductStock()%>"></label>
								</h5>

								<h5>
									<label style="display: none">�إߤ���G<input
										name="productOnsale" style="display: none"
										type="datetime-local"></label>
								</h5>

								<div>
									<label for="status" style="display: none">�ӫ~���A�G</label><select
										size="1" name="productStatus" style="display: none"
										readonly="readonly">
										<option selected="true">���A</option>
										<option value="0"
											selected
											${(productVO.productStatus==0)? "selected":""}>�U�[</option>
										<option value="1"
											${(productVO.productStatus==1)? "selected":""}>�f�ֳq�L</option>
										<option value="2"
											${(productVO.productStatus==2)? "selected":""}>�f�֥��q�L</option>
									</select>
								</div>

								<jsp:useBean id="productImageSvc" scope="page"
									class="com.productimage.model.ProductImageService" />

								<h5>
									<label>�Ϥ�1�G <input type="file" accept="image/*"
										name="productImage1"></label>
								</h5>

								<h5>
									<label>�Ϥ�2�G <input type="file" accept="image/*"
										name="productImage2"></label>
								</h5>
								<br>

								<div class="row pb-3">
									<div class="col d-grid">
										<button name="action" class="btn btn-success btn-lg"
											value="back">����</button>
									</div>

									<div class="col d-grid">
										<input type="hidden" name="action" value="insert"> <input
											type="submit" class="btn btn-success btn-lg" value="�s�W">
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