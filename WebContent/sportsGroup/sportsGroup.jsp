<%@page import="java.text.SimpleDateFormat"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.sportsgroup.model.*"%>

<%
	SportsGroupService sportsGroupSvc = new SportsGroupService();
	List<SportsGroupVO> list = sportsGroupSvc.getAll();
	pageContext.setAttribute("list", list);
	SimpleDateFormat tformat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
%>

<html>
<head>
<title>揪團首頁</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="apple-touch-icon" href="../assets/img/apple-icon.png">
<link rel="shortcut icon" type="image/x-icon" href="<%=request.getContextPath()%>/assets/img/favicon.ico">

<!-- 介面排版用的 -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/bootstrap.min.css">
<!-- 不知道的東西 -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/templatemo.css">
<!-- <link rel="stylesheet" href="assets/css/custom.css"> -->

<!-- Load fonts style after rendering the layout styles -->
<!-- 設定字型 -->
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;200;300;400;500;700;900&display=swap">
<!-- 匯入圖片 -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/fontawesome.min.css">
</head>
<body>
	<jsp:include page="/header.jsp" flush="true" />
	<!--Start 揪團body-->
	<section class="bg-light">
		<div class="container pb-5 pt-5">
			<div class="row justify-content-center article-main">

				<!-- 左邊side -->
				<div class="col-md-2 side">
					<div class="card sticky-top">
						<div class="list-group list-group-flush">
							<a
								href="<%=request.getContextPath()%>/article/article.jsp?whichClass=0"
								class="list-group-item list-group-item-action list-group-item-success"
								aria-current="true"> 論壇首頁 </a> <a
								href="<%=request.getRequestURI()%>?whichClass=1"
								class="list-group-item list-group-item-action">運動休閒</a> <a
								href="<%=request.getRequestURI()%>?whichClass=2"
								class="list-group-item list-group-item-action">商品分享</a> <a
								href="<%=request.getRequestURI()%>?whichClass=3"
								class="list-group-item list-group-item-action">運動賽事</a> <a
								href="<%=request.getRequestURI()%>?whichClass=4"
								class="list-group-item list-group-item-action">我的文章</a>
						</div>
						<div class="list-group list-group-flush">
							<a href="#"
								class="list-group-item list-group-item-action list-group-item-success"
								aria-current="true"> 揪團首頁 </a> <a href="#"
								class="list-group-item list-group-item-action">揪團</a> <a
								href="#" class="list-group-item list-group-item-action">我的揪團</a>
						</div>
					</div>
				</div>
				<!-- close左邊side -->

				<!-- 論壇本體本體 -->
				<div class="col-md-8 article-zone">
					<!-- 外框 -->
					<div class="card col-lg-12 article-card">
						<!-- 第一行 大標 -->
						<div class="container mt-4">
							<div
								class="row justify-content-between align-items-end articletitle">
								<div class="col-auto">
									<h1>揪團首頁</h1>

								</div>
								<!-- 發起揪團按鈕開始 -->
								<div class="container">
									<div
										class="row align-items-star justify-content-between articletitle">
										<div class="col-auto"></div>
										<div class="col-auto justify-content-md-end">
											<button class="btn btn-success" type="button">發起揪團</button>
										</div>
									</div>
								</div>
								<!-- 發起揪團按鈕結束 -->
							</div>
						</div>
						<!-- close第一行 大標 -->

						<!-- 第三行 揪團列表 -->
						<div class="accordion accordion-flush" id="accordionFlushExample">
							<%@ include file="articlepage1.file"%>
							<!-- 標頭開始 -->
							 <div style="padding:20px 0px 5px 0px">
								<div class="container">
									<div class="row text-center">
										<div class="col-2 h6">運動類型</div>
										<div class="col-5 h6">時間</div>
										<div class="col-2 h6">人數</div>
									
									</div>
								</div>
							</div>
							<!-- 標頭結束 -->
							<c:forEach var="sportsGroupVO" items="${list}"
								begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
								<c:set var="tdate" value="${sportsGroupVO.issueDATE}"></c:set>
								<c:set var="tdate1" value="${sportsGroupVO.registTime}"></c:set>
								<c:set var="tdate2" value="${sportsGroupVO.registTimeEnd}"></c:set>
								<!-- 開始 -->
								<div class="accordion-item">
									<h2 class="accordion-header"
										id="flush-headingOne${sportsGroupVO.sportsGroupSN}">
										<button class="accordion-button collapsed" type="button"
											data-bs-toggle="collapse"
											data-bs-target="#flush-collapseOne${sportsGroupVO.sportsGroupSN}"
											aria-expanded="false"
											aria-controls="flush-collapseOne${sportsGroupVO.sportsGroupSN}">
											<div class="container">
												<div class="row">
													<div class="col">
														<p class="h6">${sportsGroupVO.sportsType}</p>
													</div>
													<div class="col">
														<p class="h6"><%=tformat.format(pageContext.getAttribute("tdate")) %></p>
													</div>
													<div class="col">
														<p class="h6">${sportsGroupVO.participantNumber}/
															${sportsGroupVO.numberUpLimit}</p>
													</div>
												</div>
											</div>
										</button>
									</h2>
									<div id="flush-collapseOne${sportsGroupVO.sportsGroupSN}"
										class="accordion-collapse collapse"
										aria-labelledby="flush-headingOne${sportsGroupVO.sportsGroupSN}"
										data-bs-parent="#accordionFlushExample">
										<div class="accordion-body">
											<p class="mb-0 text-success h6">揪團人：${sportsGroupVO.sportsGroupSN}</p>
											<p class="mb-0 text-success h6">報名時間：<%=tformat.format(pageContext.getAttribute("tdate1")) %>至<%=tformat.format(pageContext.getAttribute("tdate2")) %></p>
											<P class="mb-0 text-success h6">地點:${sportsGroupVO.sportsLocation}</P>
											<p class="mb-0 text-success h6">活動人數:${sportsGroupVO.numberUpLimit}</P>
											<p class="mb-0 text-success h6">已報名人數:${sportsGroupVO.participantNumber}</P>
											<p class="mb-0 text-success h6">備註:${sportsGroupVO.remarks}</P>
											<div class="row">
												<div class="d-md-flex justify-content-md-end">
													<button class="btn btn-success" type="button">參加</button>
												</div>
											</div>
										</div>
									</div>
								</div>
								<!-- 結束 -->
							</c:forEach>
						</div>
						<!-- 第三行 揪團列表結束 -->
						<%@ include file="articlepage2.file"%>
						<!-- close論壇本體本體 -->
					</div>
					</div>
					<!-- close外框 -->
				</div>
			</div>
		</div>
	</section>
	<!--CLOSE 揪團body-->
	<!-- Start Script -->
	<script
		src="<%=request.getContextPath()%>/assets/js/jquery-3.6.0.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/assets/js/jquery-migrate-1.2.1.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/assets/js/bootstrap.bundle.min.js"></script>
	<script src="<%=request.getContextPath()%>/assets/js/templatemo.js"></script>
	<script src="<%=request.getContextPath()%>/assets/js/custom.js"></script>
	<!-- End Script -->
	<jsp:include page="/footer.jsp" flush="true" />
</body>
</html>