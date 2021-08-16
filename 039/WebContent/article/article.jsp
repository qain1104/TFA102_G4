<%@page import="java.text.SimpleDateFormat"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.article.model.*"%>


<%
	Integer userId = 1001;
	List<ARTICLEVO> list = null;
	ARTICLEService articleSvc = new ARTICLEService();
	SimpleDateFormat tformat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
%>
<c:choose>
	<c:when test="${param.whichClass==0 || empty param.whichClass}">
		<%
			list = articleSvc.getAll();
		%>
	</c:when>
	<c:when test="${param.whichClass==4}">
		<%
			list = articleSvc.getMyArticle(userId);
		%>
	</c:when>
	<c:when test="${param.whichClass==5}">
		<%
			String search = new String(request.getParameter("search").getBytes("iso-8859-1"), "utf-8");
					list = articleSvc.getSearchArticle(search);
		%>
	</c:when>
	<c:otherwise>
		<%
			list = articleSvc.getClassArticle(Integer.parseInt(request.getParameter("whichClass")) - 1);
					
		%>
	</c:otherwise>
</c:choose>
<% Collections.reverse(list);
pageContext.setAttribute("list", list);%>
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
	<!-- 論壇本體 -->
	<section class="bg-light">
		<div class="container">
			<div class="row justify-content-center article-main">

				<!-- 左邊side -->
				<div class="col-md-2 mt-5 side">
					<div class="card sticky-top">
						<div class="list-group list-group-flush">
							<%
								int whichClass = 0;
							%>
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
				<div class="col-md-8 mt-5 mb-5 article-zone">
					<!-- 外框 -->
					<div class="card col-lg-12 article-card">
						<!-- 第一行 大標 -->
						<div class="container mt-4">
							<div
								class="row justify-content-between align-items-end articletitle">
								<div class="col-auto">
									<c:choose>
										<c:when test="${param.whichClass==1}">
											<p class="fs-1 fw-bold text-success">運動休閒</p>
										</c:when>
										<c:when test="${param.whichClass==2}">
											<p class="fs-1 fw-bold text-success">商品分享</p>
										</c:when>
										<c:when test="${param.whichClass==3}">
											<p class="fs-1 fw-bold text-success">運動賽事</p>
										</c:when>
										<c:when test="${param.whichClass==4}">
											<p class="fs-1 fw-bold text-success">我的文章</p>
										</c:when>
										<c:when test="${param.whichClass==5}">
											<p class="fs-1 fw-bold text-success">文章搜尋</p>
										</c:when>
										<c:otherwise>
											<p class="fs-1 fw-bold text-success">論壇首頁
										</c:otherwise>
									</c:choose>

								</div>

								<div class="col-auto">
									<form METHOD="get" ACTION="article.jsp">
										<div class="input-group mb-3">
											<input type="hidden" name="whichClass" value="5"> <input
												type="text" class="form-control" name="search"
												placeholder="文章搜尋" aria-label="Recipient's username"
												aria-describedby="button-addon2">
											<button class="btn btn-outline-secondary" type="submit"
												id="button-addon2">搜尋</button>
										</div>
									</form>
								</div>
							</div>
						</div>
						<!-- close第一行 大標 -->
						<!-- 第二行 分類和發文按鈕 -->
						<div class="container">
							<div class="d-md-flex justify-content-md-end">
								<form METHOD="post"
									ACTION="<%=request.getContextPath()%>/article/article.do">
									<input type="hidden" name="action" value="topost"> <input
										type="submit" class="btn btn-success" value="發文">
								</form>

							</div>
						</div>
						<!-- close第二行 分類和發文按鈕 -->
						<!-- 第三行 文章列表 -->
						<div class="container mt-3 mb-3">
							<%@ include file="articlepage1.file"%>
							<div class="list-group aritcle-list">
								<div class="list-group-item">
									<div class="row align-items-center">
										<div class="col-2">分類</div>
										<div class="col-6">標題</div>
										<div class="col-2">人氣</div>
										<div class="col-2">發文時間</div>
									</div>
								</div>
								<!-- 文連結列表 -->

								<c:forEach var="article" items="${list}" begin="<%=pageIndex%>"
									end="<%=pageIndex+rowsPerPage-1%>">
									<c:set var="tdate" value="${article.articleDate}"></c:set>
									<form METHOD="post"
										ACTION="<%=request.getContextPath()%>/article/article.do"
										id="apopform${article.articleSN}">
										<input type="hidden" name="action" value="uppopa"> <input
											type="hidden" name="articleSN" value="${article.articleSN}">
										<a herf="#"
											onclick="document.getElementById('apopform${article.articleSN}').submit()"
											class="list-group-item list-group-item-action">
											<div class="row align-items-center">
												<div class="col-2">
													<c:choose>
														<c:when test="${article.articleClass==0}">運動休閒</c:when>
														<c:when test="${article.articleClass==1}">商品分享</c:when>
														<c:when test="${article.articleClass==2}">運動賽事</c:when>
														<c:otherwise>錯誤</c:otherwise>
													</c:choose>
												</div>
												<div class="col-6">
													<c:choose>
														<c:when test="${article.articleType==0}">[討論]</c:when>
														<c:when test="${article.articleType==1}">[發問]</c:when>
														<c:when test="${article.articleType==2}">[心得]</c:when>
														<c:otherwise>錯誤</c:otherwise>
													</c:choose>
													${article.articleTitle}
												</div>
												<div class="col-2">${article.articlePop}</div>
												<div class="col-2" style="font-size: 8px;"><%=tformat.format(pageContext.getAttribute("tdate")) %></div>
											</div>
										</a>
									</form>
								</c:forEach>
								<!-- close文連結列表 -->
							</div>

						</div>
						<%@ include file="articlepage2.file"%>
						<!-- close論壇本體本體 -->
					</div>
					<!-- close外框 -->
				</div>
			</div>
		</div>
	</section>
	<!-- CLOSE 論壇本體 -->
	<!-- Start Script -->
	<script
		src="<%=request.getContextPath()%>/assets/js/jquery-3.6.0.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/assets/js/jquery-migrate-1.2.1.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/assets/js/bootstrap.bundle.min.js"></script>
	<script src="<%=request.getContextPath()%>/assets/js/templatemo.js"></script>
	<script src="<%=request.getContextPath()%>/assets/js/custom.js"></script>
	<script>
		
	</script>
	<!-- End Script -->
	<jsp:include page="/footer.jsp" flush="true" />
</body>
</html>