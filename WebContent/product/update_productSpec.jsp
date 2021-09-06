<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.productspec.model.*"%>
<%@ page import="com.productimage.model.*"%>

<%
	ProductSpecVO productSpecVO = (ProductSpecVO) request.getAttribute("productSpecVO");

	Integer productSN = new Integer(request.getParameter("productSN"));

	ProductSpecService productspecSvc = new ProductSpecService();
	List<ProductSpecVO> list1 = productspecSvc.getProductSpec(productSN);
	pageContext.setAttribute("list1", list1);
	
	ProductService productSvc = new ProductService();
	ProductVO productVO = productSvc.getOneProduct(productSN);
	pageContext.setAttribute("productVO", productVO);
	ProductVO proVO = (ProductVO) pageContext.getAttribute("productVO");
	
%>

<jsp:useBean id="productSpecSvc" scope="page"
	class="com.productspec.model.ProductSpecService" />


<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>update_productSpec</title>

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

	<FORM METHOD="post"
		ACTION="<%=request.getContextPath()%>/product/product.do"
		style="margin-bottom: 0px;">
		<section class="bg-light">
			<div class="container pb-3 align-items-center">
				<div class="row">
					<div class="col-lg-7 mt-5 col align-self-center" style="width: 25%">
						<div class="card">
							<div class="card-body">
								<h1 class="h2">�ӫ~�W��קﭶ</h1>

								<h5 style="display: none" readonly="readonly">
									�ӫ~�s��<input style="display: none" name="productSN"
										value="${productSpecVO.productSN}">${productSpecVO.productSN}></h5>

								<h5 style="display: none" readonly="readonly">
									�ӫ~�W��s��<input style="display: none" name="productSpecId"
										value="${productSpecVO.productSpecId}">${productSpecVO.productSpecId}</h5>

								<h5>
									����<input type="text" name="productPrice"
										value="${productSpecVO.productPrice}">
								</h5>

								<h5>
									�W��<input type="text" name="productSpec"
										value="${productSpecVO.productSpec}">
								</h5>

								<h5>
									�ƶq<input type="text" name="productStock"
										value="${productSpecVO.productStock}">
								</h5>

								<div>
									<input type="hidden" name="action" value="updateProductSpec">
									<input type="hidden" name="productSpecId"
										value="${productSpecVO.productSpecId}"> <input
										type="hidden" name="productSN" value="${productVO.productSN}"><input
										type="submit" value="�ק�"
										class="btn btn-primary position-relative btn-sm">
								</div>
							</div>
						</div>
					</div>
				</div>
		</section>
	</FORM>

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