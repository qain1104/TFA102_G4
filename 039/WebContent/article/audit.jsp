<%@page import="javax.crypto.spec.DHGenParameterSpec"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="com.report.model.*"%>
<%@ page import="util.*"%>
<%
	Datahandle dh=new Datahandle();
	REPORTService reportSvc = new REPORTService();
	List<REPORTVO> reportList = reportSvc.getReportStatus(0);
	request.setAttribute("list", reportList);
%>
<!DOCTYPE html>
<html>
<head>
<title>Sportify-article</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="apple-touch-icon" href="../assets/img/apple-icon.png">
<link rel="shortcut icon" type="image/x-icon"
	href="<%=request.getContextPath()%>/assets/img/favicon.ico">

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
</head>
<body>
<jsp:include page="/header.jsp" flush="true" />
	<!-- 審核本體 -->
	<div class="bg-light">
		<div class="container pt-5 pb-5">
			<div class="row justify-content-center">
				<div class="card p-3 col-10" style="min-width:925px">
					<!-- 標題 -->
					<div>
						<p class="fs-1 fw-bold text-success">審核首頁</p>
					</div>
					<!-- close標題 -->
					<!-- 審核按鈕 -->
					<div class="container">
						<div class="row  justify-content-around align-items-center">
							<div class="col-auto">
								<button type="button" class="btn btn-success position-relative">
									企業審核 <span
										class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
										99+ <span class="visually-hidden">unread messages</span>
									</span>
								</button>
							</div>
							<div class="col-auto">
								<button type="button" class="btn btn-success position-relative">
									商品審核 <span
										class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
										99+ <span class="visually-hidden">unread messages</span>
									</span>
								</button>
							</div>
							<div class="col-auto">
								<button type="button" class="btn btn-success position-relative">
									場地審核 <span
										class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
										99+ <span class="visually-hidden">unread messages</span>
									</span>
								</button>
							</div>
							<div class="col-auto">
								<button type="button" class="btn btn-success position-relative">
									檢舉審核 <span
										class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
										<%=reportList.size()%><span class="visually-hidden">unread messages</span>
									</span>
								</button>
							</div>
						</div>
					</div>
					<!-- close審核按鈕 -->
					<!-- 檢舉列表 -->
					<div class="container mt-3">
						<div class="row justify-content-center">
							<div class="card col-md-10" style="min-width:842px">
								<p class="fs-1 mb-0 text-success">檢舉分頁</p>
								<table class="table align-middle table-striped ">
									<thead>
										<tr>
											<th scope="col" style="min-width:60px">#</th>
											<th scope="col" style="min-width:20px">編號</th>
											<th scope="col" style="min-width:90px">檢舉原因</th>
											<th scope="col" style="min-width:145px">檢舉時間</th>
											<th scope="col">檢舉備註</th>
											<th scope="col" style="width: 160px;min-width:160px">審核</th>
										</tr>
									</thead>
									<c:forEach var="reportVO" items="${list}">
									<c:set var="ttime" value="${reportVO.reportDate}"></c:set>
									<tbody>
										<tr>
											<td><c:choose>
											<c:when test="${reportVO.replySN eq 0}">文章</c:when>
											<c:otherwise>回覆</c:otherwise>
											</c:choose></td>
											<td>
											<c:choose>
											<c:when test="${reportVO.replySN eq 0}">${reportVO.articleSN}</c:when>
											<c:otherwise>${reportVO.replySN}</c:otherwise>
											</c:choose>
											</td>
											<td>
											<c:choose>
											<c:when test="${reportVO.reportClass eq 0}">不當言論</c:when>
											<c:when test="${reportVO.reportClass eq 1}">惡意言論</c:when>
											<c:otherwise>其他</c:otherwise>
											</c:choose>
											</td>
											<td class="fs-6"><%=dh.getTime(pageContext.getAttribute("ttime"))%></td>
											<td>${reportVO.reportContent}</td>
											<td>
												<div class="d-grid gap-2 d-md-flex">
													<button class="btn btn-success" type="button">通過</button>
													<button class="btn btn-success" type="button">不通過</button>
												</div>
											</td>
										</tr>
									</tbody>
									</c:forEach>
								</table>
							</div>
						</div>
					</div>
					<!-- close檢舉列表 -->
				</div>
			</div>
		</div>
	</div>
	<!-- CLOSE 審核本體 -->
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