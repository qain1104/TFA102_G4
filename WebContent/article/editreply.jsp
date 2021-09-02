<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.article.model.*"%>
<%@ page import="com.reply.model.*"%>
<%@ page import="util.*"%>

<%
	Integer userId = null;
	REPLYVO replyVO = (REPLYVO) request.getAttribute("replyVO");
	ARTICLEService aSvc = new ARTICLEService();
	ARTICLEVO articleVO = aSvc.getOneArticle(replyVO.getArticleSN());
	request.setAttribute("articleVO", articleVO);
	Datahandle dh = new Datahandle();
%>
<c:if test="${!empty userId}">
<%userId=(Integer)session.getAttribute("userId"); %>
</c:if>
<!DOCTYPE html>
<html>
<head>
<title>Sportify-article</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="apple-touch-icon" href="../assets/img/apple-icon.png">
	<!-- 網頁logo -->
	<link rel="shortcut icon" href="<%= request.getContextPath() %>/assets/img/logo1.png" type="image/x-icon" />

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
		<div class="container pb-5 pt-5">
			<div class="row justify-content-center article-main">

				<!-- 左邊side -->
				<div class="col-md-2 side">
					<jsp:include page="articleside.jsp" flush="true" />
				</div>
				<!-- close左邊side -->

				<!-- 論壇本體本體 -->
				<div class="col-md-8 article-zone">
					<!-- 外框 -->
					<div class="card col-lg-12 pb-3 article-card">
						<!-- 第一行 大標 -->
						<div class="container mt-4">
							<div
								class="row justify-content-between align-items-end articletitle">
								<div class="col-auto">
									<p class="fs-2 fw-bold text-success">編輯文章</p>
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
									ACTION="<%=request.getContextPath()%>/article/reply.do">
									<p class="fs-5 text-success">文章分類</p>
									<div class="col-md-3 mb-3">
										<!--隱藏input -->
										<input type="hidden" name="replySN" value="${replyVO.replySN}">
										<input type="hidden" name="articleSN"
											value="${articleVO.articleSN}">
										<!--!隱藏input -->
										<select class="form-select" name="articleClass" disabled>
											<option disabled>分類</option>
											<option value="0"
												${(articleVO.articleClass==0)?"selected":""}>運動休閒</option>
											<option value="1"
												${(articleVO.articleClass==1)?"selected":""}>商品分享</option>
											<option value="2"
												${(articleVO.articleClass==2)?"selected":""}>運動賽事</option>
										</select>
									</div>
									<div class="input-group mb-3">
										<div class="col-md-3">
											<select class="form-select" name="articleType" disabled>
												<option disabled>類型</option>
												<option value="0"
													${(articleVO.articleType==0)?'selected':''}>討論</option>
												<option value="1"
													${(articleVO.articleType==1)?'selected':''}>發問</option>
												<option value="2"
													${(articleVO.articleType==2)?'selected':''}>心得</option>
											</select>
										</div>
										<input type="text" class="form-control" name="articleTitle"
											placeholder="請輸入標題"
											value="<%=(articleVO == null) ? "" : articleVO.getArticleTitle()%>"
											disabled>
									</div>
									<div class="mb-3">
										<label for="exampleFormControlTextarea1"
											class="form-label fs-5 text-success"> ${floor}樓:內容</label>
										<script
											src="https://cdn.ckeditor.com/4.7.3/standard/ckeditor.js"></script>
										<textarea name="replyContent">
											<%
												byte[] bytes = replyVO.getReplyContent();
												String acontent = dh.getArticleContent(bytes);
											%>
										<%=acontent%></textarea>
										<script>
											CKEDITOR.replace("replyContent");
										</script>
									</div>
									<div class="d-grid gap-2 d-md-flex justify-content-md-end">
										<button class="btn btn-success" type="button"
											data-bs-toggle="modal" data-bs-target="#deletemodal">刪除文章</button>
																				
										<button class="btn btn-success" name="action" value="upeditr"
											type="submit">編輯文章</button>
										<div class="modal fade" id="deletemodal" tabindex="-1"
											aria-labelledby="deletemodalLabel" aria-hidden="true">
											<div class="modal-dialog">
												<div class="modal-content">
													<div class="modal-header">
														<h5 class="modal-title fs-2 text-danger fw-bold">確定要刪除嗎?</h5>
														<button type="button" class="btn-close"
															data-bs-dismiss="modal" aria-label="Close"></button>
													</div>
													<div class="modal-body">
														<p class="fs-4">刪除之後將無法復原!</p>
													</div>
													<div class="modal-footer">
														<button type="button" class="btn btn-secondary"
															data-bs-dismiss="modal">再想想</button>
														<button class="btn btn-success" name="action" value="upstatus"
																					type="submit">刪除文章</button>
													</div>
												</div>
											</div>
										</div>
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
		</div>
	</div>
	<!-- CLOSE 論壇本體 -->
	<!-- Vertically centered modal -->
	<jsp:include page="/footer.jsp" flush="true" />
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