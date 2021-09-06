<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.productspec.model.*"%>

<%
	ProductService productSvc = new ProductService();
	ProductSpecService prodcutspecSvc = new ProductSpecService();
	List<ProductVO> corpProductList = (List<ProductVO>) session.getAttribute("corpProductList");
	session.setAttribute("corpUserId", new Integer(2001));
%>
<jsp:useBean id="productSpecSvc" scope="page"
	class="com.productspec.model.ProductSpecService" />

<html>
<head>
<title>�ڪ��ӫ~��� - listMyProduct.jsp</title>
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
<body bgcolor='white'>

	<!-- 	<h4>�����m�߱ĥ� EL ���g�k����:</h4> -->
	<!-- 	<table id="table-1"> -->
	<!-- 		<tr> -->
	<!-- 			<td> -->
	<!-- 				<h3>�ڪ��ӫ~���</h3> -->
	<!-- 				<h4> -->
	<!-- 					<a href="select_page.jsp"><img -->
	<%-- 						src="<%=request.getContextPath()%>/assets/images/back1.gif" --%>
	<!-- 						width="100" height="32" border="0">�^����</a> -->
	<!-- 				</h4> -->
	<!-- 			</td> -->
	<!-- 		</tr> -->
	<section>
		<div class="container">
			<div class="row justify-content-center">
				<div class="card col-md-10 pt-3">
					<h1>�ӫ~�޲z�M��</h1>
					<!-- close���D -->
					<!-- �s�W�ӫ~���s -->
					<div class="col align-self-end">
						<div class="row  justify-content-around align-items-center pb-3">
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/product/product.do">
								<div class="col-auto">
									<div class="right">
										<input type="button"
											class="btn btn-primary position-relative;"
											onclick="location.href='http://localhost:8081/Sportify/product/addProduct.jsp'"
											value="�s�W�ӫ~"> <input type="hidden" name="action"
											value="insert">
									</div>
								</div>
							</FORM>
						</div>
					</div>
					<%-- ���~��C --%>
					<c:if test="${not empty errorMsgs}">
						<font style="color: red">�Эץ��H�U���~:</font>
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li style="color: red">${message}</li>
							</c:forEach>
						</ul>
					</c:if>

					<!-- close�W�[���s -->
					<table class="table align-middle table-striped">
						<thead>
							<tr>
								<th scope="col" style="width: auto;">�ӫ~�s��</th>
								<th scope="col" style="display: none">���~�|���s��</th>
								<th scope="col" style="width: 10%;">�ӫ~�W��</th>
								<th scope="col" style="width: auto;">�ӫ~���O</th>
								<th scope="col" style="width: 30%;">�ӫ~²��</th>
								<th scope="col" style="width: auto;">�~�P</th>
								<th scope="col" style="width: 10%;">�إߤ��</th>
								<th scope="col" style="width: 10%;">���A</th>
								<th scope="col" style="width: 5%;"></th>
								<th scope="col" style="width: 5%;"></th>
							</tr>
						</thead>

						<tbody>
							<div>
								<%@ include file="page1.file"%>
							</div>
							<c:forEach var="productVO" items="${corpProductList}"
								begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
								<tr>
									<td><a
										href="<%=request.getContextPath()%>/product/product.do?action=getOne_For_Display&productSN=${productVO.productSN}">${productVO.productSN}</a></td>
									<td style="display: none">${productVO.corpUserId}</td>
									<td>${productVO.productName}</td>
									<td><c:if test="${productVO.productClass == 0}">�k�ͦ��</c:if>
										<c:if test="${productVO.productClass == 1}">�k�ͦ��</c:if> <c:if
											test="${productVO.productClass == 2}">�c��</c:if> <c:if
											test="${productVO.productClass == 3}">��L</c:if></td>
									<td>${productVO.productDetail}</td>
									<td>${productVO.productBrand}</td>

									<c:set var="productOnsale" value="${productVO.productOnsale}"></c:set>
									<fmt:formatDate value="${productOnsale}" var="date"
										pattern="yyyy/MM/dd HH:mm:ss" />
									<td>${date}</td>
									<td><c:if test="${productVO.productStatus == 0}">�U�[</c:if>
										<c:if test="${productVO.productStatus == 1}">�f�ֳq�L</c:if> <c:if
											test="${productVO.productStatus == 2}">�f�֥��q�L</c:if>
									<td>
										<div>
											<FORM METHOD="post"
												ACTION="<%=request.getContextPath()%>/product/product.do"
												style="margin-bottom: 0px;">
												<input type="submit" class="btn btn-success btn-sm"
													value="�ק�"> <input type="hidden" name="productSN"
													value="${productVO.productSN}"> <input
													type="hidden" name="action" value="check_update">
											</FORM>
										</div>
									</td>
									<td>
										<div>
											<FORM METHOD="post"
												ACTION="<%=request.getContextPath()%>/product/product.do"
												style="margin-bottom: 0px;">
												<c:if test="${productVO.productStatus == 1}">
													<input type="submit" class="btn btn-success btn-sm"
														value="�U�[">
													<input type="hidden" name="productSN"
														value="${productVO.productSN}">
													<input type="hidden" name="action" value="down_product">
												</c:if>
											</FORM>
										</div>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<%@ include file="page2.file"%>
		</div>
		</div>
	</section>
</body>
</html>