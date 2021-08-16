<%@page import="javax.crypto.spec.DHGenParameterSpec"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.article.model.*"%>

<%
	String[] atype = {"討論", "發問", "心得"};
	request.setAttribute("atype", atype);
	ARTICLEVO aVO=(ARTICLEVO)request.getAttribute("articleVO");
	ARTICLEService aSvc= new ARTICLEService();
	Datahandle dh=new Datahandle();
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
	<!-- 論壇回覆本體 -->
	<section class="bg-light">
		<div class="container pt-5 pb-5">
			<div class="row justify-content-center">
				<!-- 左邊side -->
				<div class="col-md-2 side">
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

				<!-- 右邊本體 -->
				<div class="col-lg-10 article-zone">
					<!-- 右邊row -->
					<div class="row">
						<!-- 最上面標題 -->
						<div class="row">
							<div class="col-lg-12">
								<p class="fs-2 mb-0 px-1 text-success">
									<c:choose>
										<c:when test="${articleVO.articleType==0}">[討論]</c:when>
										<c:when test="${articleVO.articleType==1}">[發問]</c:when>
										<c:when test="${articleVO.articleType==2}">[心得]</c:when>
										<c:otherwise>錯誤</c:otherwise>
									</c:choose>${articleVO.articleTitle}</p>
							</div>
						</div>
						<!-- !最上面標題 -->

						<!-- 文章內容外框 -->
						<div class="row mt-3">
							<div class="col-auto">
								<div class="card sticky-top">
									<div>
										<img src="https://via.placeholder.com/100x100">
									</div>
									<div>userId</div>
								</div>
							</div>
							<div class="card col-10 pb-3">
								<div class="container mt-4">
									<div class="row justify-content-between">
										<div class="col-auto">
											<p class="fs-5 mb-0 text-success">樓主</p>
											<p class="fs-5 mb-0 text-success">
												<c:choose>
													<c:when
														test="${articleVO.articleUpDate == articleVO.articleDate}">發文時間: </c:when>
													<c:when
														test="${articleVO.articleUpDate != articleVO.articleDate}">最後編輯時間: </c:when>
													<c:otherwise>錯誤</c:otherwise>
												</c:choose>${articleVO.articleUpDate}</p>
										</div>
										<div class="col-auto">
											<button class="btn btn-success" type="button">編輯</button>
										</div>
									</div>
									<div class="card mt-3 p-3" style="min-height: 300px;">
							<%
							byte[] bytes=aVO.getArticleContent();
								String acontent=dh.getArticleContent(bytes);%>
								<%=acontent%>
										</div>
									<div class="row justify-content-between mt-2">
										<div class="col-auto d-grid gap-2 d-md-flex">
											<div class="row align-items-end">
												<div class="col-auto">
													<button class="btn btn-success" type="button">推推</button>
												</div>
												<div class="card col-auto">
													<div>1</div>
												</div>
											</div>
										</div>
										<div class="col-auto d-grid gap-2 d-md-flex">
											<button class="btn btn-success" type="button">檢舉</button>
										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- !文章內容外框 -->
						<!-- 回覆框框 -->
						<div class="row mt-3">
							<div class="col-auto" style="visibility: hidden">
								<div class="card sticky-top" style="width: 100px"></div>
							</div>

							<div class="card col-10 pb-3">
								<div class="container mt-4">
									<div>回覆</div>
									<form action="aply">
										<script
											src="https://cdn.ckeditor.com/4.7.3/standard/ckeditor.js"></script>
										<textarea name="editor1"></textarea>
										<script>
											CKEDITOR.replace("editor1");
										</script>
										<div
											class="col-auto d-grid gap-2 d-md-flex mt-2 justify-content-md-end">
											<button class="btn btn-success" type="button">送出</button>
										</div>
									</form>

								</div>
							</div>

						</div>
						<!-- !回覆框框 -->
					</div>
					<!-- 右邊row -->
				</div>
				<!-- 右邊本體 -->
			</div>
		</div>
	</section>
	<!-- !論壇回覆本體 -->
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
</body>
</html>