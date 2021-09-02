<%@page import="util.Datahandle"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.article.model.*"%>
<%@ page import="util.*"%>
<%
	Integer userId = null;
	ARTICLEVO articleVO = (ARTICLEVO) request.getAttribute("articleVO");
	Datahandle dh = new Datahandle();
%>
<c:if test="${!empty userId}">
	<%
		userId = (Integer) session.getAttribute("userId");
	%>
</c:if>
<!DOCTYPE html>
<html>
<head>
<title>Sportify-report</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="apple-touch-icon" href="../assets/img/apple-icon.png">
<!-- ����logo -->
<link rel="shortcut icon"
	href="<%=request.getContextPath()%>/assets/img/logo1.png"
	type="image/x-icon" />

<!-- �����ƪ��Ϊ� -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/bootstrap.min.css">
<!-- �����D���F�� -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/templatemo.css">
<!-- <link rel="stylesheet" href="assets/css/custom.css"> -->

<!-- Load fonts style after rendering the layout styles -->
<!-- �]�w�r�� -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;200;300;400;500;700;900&display=swap">
<!-- �פJ�Ϥ� -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/fontawesome.min.css">
<!-- footerheader css -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/custom.css">
</head>
<body>
	<jsp:include page="/header.jsp" flush="true" />
	<!-- �׾¥��� -->
	<div class="bg-light">
		<div class="container pt-5 pb-5">
			<div class="row justify-content-center article-main">

				<!-- ����side -->
				<div class="col-md-2 side">
					<jsp:include page="articleside.jsp" flush="true" />
				</div>
				<!-- close����side -->

				<!-- ���| -->
				<div class="col-md-8 article-zone">
					<!-- �~�� -->
					<div class="card col-lg-12 pb-3 article-card">
						<!-- �Ĥ@�� �j�� -->
						<div class="container mt-4">
							<div
								class="row justify-content-between align-items-end articletitle">
								<div class="col-auto">
									<p class="fs-1 fw-bold text-success">���|${replyVO.replySN}</p>
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
								<%-- !���~��C --%>
								<form METHOD="post"
									ACTION="<%=request.getContextPath()%>/article/report.do">
									<!--����input -->
									<input type="hidden" name="action" value="insertreport">
									<input type="hidden" name="floor" value="${floor}"> <input
										type="hidden" name="articleSN" value="${articleVO.articleSN}">
									<input type="hidden" name="replySN" value="${replyVO.replySN}">
									<input type="hidden" name="userId" value="<%=userId%>">
									<!--!����input -->
									<div class="mb-3">
										<label for="exampleFormControlTextarea1"
											class="form-label fs-5 text-success">�峹����</label> <input
											class="form-control" type="text"
											value="<%=dh.getArticleClass(articleVO.getArticleClass())%>"
											aria-label="Disabled input example" readonly disabled>
									</div>
									<div class="mb-3">
										<label for="exampleFormControlTextarea1"
											class="form-label fs-5 text-success">�峹���D</label> <input
											class="form-control" type="text"
											value="<%=dh.getArticleType(articleVO.getArticleType())%>${articleVO.articleTitle}"
											aria-label="Disabled input example" readonly disabled>
									</div>
									<div class="mb-3">
										<label for="exampleFormControlTextarea1"
											class="form-label fs-5 text-success">�ĴX��</label> <input
											class="form-control" type="text" value="${floor}��"
											aria-label="Disabled input example" readonly disabled>
									</div>
									<div class="mb-3">
										<label for="exampleFormControlTextarea1"
											class="form-label fs-5 text-success">���|��]</label> <select
											class="form-select" name="reportClass"
											aria-label="Default select example">
											<option selected>����</option>
											<option value="0">������</option>
											<option value="1">�c�N����</option>
											<option value="2">��L</option>
										</select>
									</div>
									<div class="mb-3">
										<label for="exampleFormControlTextarea1"
											class="form-label fs-5 text-success">�Ƶ�</label>
										<textarea class="form-control" name="reportContent"
											id="exampleFormControlTextarea1" rows="3"></textarea>
									</div>

									<div class="d-grid gap-2 d-md-flex justify-content-md-end">
										<button class="btn btn-success" type="submit">�e�X���|</button>
									</div>
								</form>
							</div>
						</div>
					</div>
					<!-- close�Ĥ@�� �j�� -->
				</div>
				<!-- close�~�� -->
			</div>
		</div>
	</div>
	<!-- CLOSE ���|���� -->

	<!-- Start Script -->
	<script src="assets/js/jquery-3.6.0.min.js"></script>
	<script src="assets/js/jquery-migrate-1.2.1.min.js"></script>
	<script src="assets/js/bootstrap.bundle.min.js"></script>
	<script src="assets/js/templatemo.js"></script>
	<script src="assets/js/custom.js"></script>
	<!-- End Script -->
	<jsp:include page="/footer.jsp" flush="true" />
</body>
</html>