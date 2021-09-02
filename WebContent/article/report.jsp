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
<!-- 網頁logo -->
<link rel="shortcut icon"
	href="<%=request.getContextPath()%>/assets/img/logo1.png"
	type="image/x-icon" />

<!-- 介面排版用的 -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/bootstrap.min.css">
<!-- 不知道的東西 -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/templatemo.css">
<!-- <link rel="stylesheet" href="assets/css/custom.css"> -->

<!-- Load fonts style after rendering the layout styles -->
<!-- 設定字型 -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;200;300;400;500;700;900&display=swap">
<!-- 匯入圖片 -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/fontawesome.min.css">
<!-- footerheader css -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/custom.css">
</head>
<body>
	<jsp:include page="/header.jsp" flush="true" />
	<!-- 論壇本體 -->
	<div class="bg-light">
		<div class="container pt-5 pb-5">
			<div class="row justify-content-center article-main">

				<!-- 左邊side -->
				<div class="col-md-2 side">
					<jsp:include page="articleside.jsp" flush="true" />
				</div>
				<!-- close左邊side -->

				<!-- 檢舉 -->
				<div class="col-md-8 article-zone">
					<!-- 外框 -->
					<div class="card col-lg-12 pb-3 article-card">
						<!-- 第一行 大標 -->
						<div class="container mt-4">
							<div
								class="row justify-content-between align-items-end articletitle">
								<div class="col-auto">
									<p class="fs-1 fw-bold text-success">檢舉${replyVO.replySN}</p>
								</div>
								<%-- 錯誤表列 --%>
								<c:if test="${not empty errorMsgs}">
									<font style="color: red">請修正以下錯誤:</font>
									<ul>
										<c:forEach var="message" items="${errorMsgs}">
											<li style="color: red">${message}</li>
										</c:forEach>
									</ul>
								</c:if>
								<%-- !錯誤表列 --%>
								<form METHOD="post"
									ACTION="<%=request.getContextPath()%>/article/report.do">
									<!--隱藏input -->
									<input type="hidden" name="action" value="insertreport">
									<input type="hidden" name="floor" value="${floor}"> <input
										type="hidden" name="articleSN" value="${articleVO.articleSN}">
									<input type="hidden" name="replySN" value="${replyVO.replySN}">
									<input type="hidden" name="userId" value="<%=userId%>">
									<!--!隱藏input -->
									<div class="mb-3">
										<label for="exampleFormControlTextarea1"
											class="form-label fs-5 text-success">文章分類</label> <input
											class="form-control" type="text"
											value="<%=dh.getArticleClass(articleVO.getArticleClass())%>"
											aria-label="Disabled input example" readonly disabled>
									</div>
									<div class="mb-3">
										<label for="exampleFormControlTextarea1"
											class="form-label fs-5 text-success">文章標題</label> <input
											class="form-control" type="text"
											value="<%=dh.getArticleType(articleVO.getArticleType())%>${articleVO.articleTitle}"
											aria-label="Disabled input example" readonly disabled>
									</div>
									<div class="mb-3">
										<label for="exampleFormControlTextarea1"
											class="form-label fs-5 text-success">第幾樓</label> <input
											class="form-control" type="text" value="${floor}樓"
											aria-label="Disabled input example" readonly disabled>
									</div>
									<div class="mb-3">
										<label for="exampleFormControlTextarea1"
											class="form-label fs-5 text-success">檢舉原因</label> <select
											class="form-select" name="reportClass"
											aria-label="Default select example">
											<option selected>分類</option>
											<option value="0">不當言論</option>
											<option value="1">惡意言論</option>
											<option value="2">其他</option>
										</select>
									</div>
									<div class="mb-3">
										<label for="exampleFormControlTextarea1"
											class="form-label fs-5 text-success">備註</label>
										<textarea class="form-control" name="reportContent"
											id="exampleFormControlTextarea1" rows="3"></textarea>
									</div>

									<div class="d-grid gap-2 d-md-flex justify-content-md-end">
										<button class="btn btn-success" type="submit">送出檢舉</button>
									</div>
								</form>
							</div>
						</div>
					</div>
					<!-- close第一行 大標 -->
				</div>
				<!-- close外框 -->
			</div>
		</div>
	</div>
	<!-- CLOSE 檢舉本體 -->

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