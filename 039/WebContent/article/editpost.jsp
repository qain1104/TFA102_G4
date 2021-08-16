<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.article.model.*"%>

<%
	Integer userId = 1001;
	Integer articleSN =(Integer) request.getAttribute("articleSN");
	ARTICLEService aSvc=new ARTICLEService();
	ARTICLEVO aVO =aSvc.getOneArticle(articleSN);
	
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
	<!-- 論壇本體 -->
	<section class="bg-light">
		<div class="container pt-5 pb-5">
			<div class="row justify-content-center article-main">

				<!-- 左邊side -->
				<div class="col-md-2 mt-5 side">
					<div class="card sticky-top">
						<div class="list-group list-group-flush">
							<%
								int whichClass = 0;
							%>
							<a href="<%=request.getContextPath()%>/article/article.jsp?whichClass=0"
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
				<div class="col-md-8 mt-5 article-zone">
					<!-- 外框 -->
					<div class="card col-lg-12 pb-3 article-card">
						<!-- 第一行 大標 -->
						<div class="container mt-4">
							<div
								class="row justify-content-between align-items-end articletitle">
								<div class="col-auto">
									<p class="fs-2 fw-bold text-success">發表文章</p>
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
								<%-- 表單 --%>
								<form METHOD="post"
									ACTION="<%=request.getContextPath()%>/article/article.do">
									<p class="fs-5 text-success">文章分類</p>
									<div class="col-md-3 mb-3">
										<!--隱藏input -->
										<input type="hidden" name="userId" value="<%=userId%>">
										<input type="hidden" name="articlePop" value="0">
										<input type="hidden" name="articleLikes" value="0">
										<input type="hidden" name="articleDate" value=<%=new Date()%>>
										<input type="hidden" name="articleUpDate" value="<%=new Date()%>">
										<input type="hidden" name="articleStatus" value="0">
										<!--!隱藏input -->
										<select class="form-select" name="articleClass" required>
											<option selected disabled>分類</option>
											<option value="0">運動休閒</option>
											<option value="1">商品分享</option>
											<option value="2">運動賽事</option>
										</select>
									</div>
									<div class="input-group mb-3">
										<div class="col-md-3">
											<select class="form-select" name="articleType" required>
												<option selected disabled>類型</option>
												<option value="0">討論</option>
												<option value="1">發問</option>
												<option value="2">心得</option>
											</select>
										</div>
										<input type="text" class="form-control" name="articleTitle" placeholder="請輸入標題" value="<%= (aVO==null)? "" : aVO.getArticleTitle()%>" required>
									</div>
									<div class="mb-3">
										<label for="exampleFormControlTextarea1" class="form-label">內容</label>
										<script
											src="https://cdn.ckeditor.com/4.7.3/standard/ckeditor.js"></script>
										<textarea name="articleContent"></textarea>
										<script>
											CKEDITOR.replace("articleContent");
										</script>
									</div>

									<div class="d-grid gap-2 d-md-flex justify-content-md-end">
										<input type="hidden" name="action" value="insert"> <input
											class="btn btn-success" type="submit" value="送出文章">
									</div>
								</form>
								<%-- !表單 --%>
							</div>
						</div>
						<!-- close第一行 大標 -->
					</div>
					<!-- close外框 -->
				</div>
			</div>
	</section>
	<!-- CLOSE 論壇本體 -->
</body>
</html>