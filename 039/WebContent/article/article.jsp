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
</head>
<body>
	<jsp:include page="/header.jsp" flush="true" />
	<!-- �׾¥��� -->
	<section class="bg-light">
		<div class="container">
			<div class="row justify-content-center article-main">

				<!-- ����side -->
				<div class="col-md-2 mt-5 side">
					<div class="card sticky-top">
						<div class="list-group list-group-flush">
							<%
								int whichClass = 0;
							%>
							<a
								href="<%=request.getContextPath()%>/article/article.jsp?whichClass=0"
								class="list-group-item list-group-item-action list-group-item-success"
								aria-current="true"> �׾­��� </a> <a
								href="<%=request.getRequestURI()%>?whichClass=1"
								class="list-group-item list-group-item-action">�B�ʥ�</a> <a
								href="<%=request.getRequestURI()%>?whichClass=2"
								class="list-group-item list-group-item-action">�ӫ~����</a> <a
								href="<%=request.getRequestURI()%>?whichClass=3"
								class="list-group-item list-group-item-action">�B���ɨ�</a> <a
								href="<%=request.getRequestURI()%>?whichClass=4"
								class="list-group-item list-group-item-action">�ڪ��峹</a>
						</div>
						<div class="list-group list-group-flush">
							<a href="#"
								class="list-group-item list-group-item-action list-group-item-success"
								aria-current="true"> ���έ��� </a> <a href="#"
								class="list-group-item list-group-item-action">����</a> <a
								href="#" class="list-group-item list-group-item-action">�ڪ�����</a>
						</div>
					</div>
				</div>
				<!-- close����side -->

				<!-- �׾¥��饻�� -->
				<div class="col-md-8 mt-5 mb-5 article-zone">
					<!-- �~�� -->
					<div class="card col-lg-12 article-card">
						<!-- �Ĥ@�� �j�� -->
						<div class="container mt-4">
							<div
								class="row justify-content-between align-items-end articletitle">
								<div class="col-auto">
									<c:choose>
										<c:when test="${param.whichClass==1}">
											<p class="fs-1 fw-bold text-success">�B�ʥ�</p>
										</c:when>
										<c:when test="${param.whichClass==2}">
											<p class="fs-1 fw-bold text-success">�ӫ~����</p>
										</c:when>
										<c:when test="${param.whichClass==3}">
											<p class="fs-1 fw-bold text-success">�B���ɨ�</p>
										</c:when>
										<c:when test="${param.whichClass==4}">
											<p class="fs-1 fw-bold text-success">�ڪ��峹</p>
										</c:when>
										<c:when test="${param.whichClass==5}">
											<p class="fs-1 fw-bold text-success">�峹�j�M</p>
										</c:when>
										<c:otherwise>
											<p class="fs-1 fw-bold text-success">�׾­���
										</c:otherwise>
									</c:choose>

								</div>

								<div class="col-auto">
									<form METHOD="get" ACTION="article.jsp">
										<div class="input-group mb-3">
											<input type="hidden" name="whichClass" value="5"> <input
												type="text" class="form-control" name="search"
												placeholder="�峹�j�M" aria-label="Recipient's username"
												aria-describedby="button-addon2">
											<button class="btn btn-outline-secondary" type="submit"
												id="button-addon2">�j�M</button>
										</div>
									</form>
								</div>
							</div>
						</div>
						<!-- close�Ĥ@�� �j�� -->
						<!-- �ĤG�� �����M�o����s -->
						<div class="container">
							<div class="d-md-flex justify-content-md-end">
								<form METHOD="post"
									ACTION="<%=request.getContextPath()%>/article/article.do">
									<input type="hidden" name="action" value="topost"> <input
										type="submit" class="btn btn-success" value="�o��">
								</form>

							</div>
						</div>
						<!-- close�ĤG�� �����M�o����s -->
						<!-- �ĤT�� �峹�C�� -->
						<div class="container mt-3 mb-3">
							<%@ include file="articlepage1.file"%>
							<div class="list-group aritcle-list">
								<div class="list-group-item">
									<div class="row align-items-center">
										<div class="col-2">����</div>
										<div class="col-6">���D</div>
										<div class="col-2">�H��</div>
										<div class="col-2">�o��ɶ�</div>
									</div>
								</div>
								<!-- ��s���C�� -->

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
														<c:when test="${article.articleClass==0}">�B�ʥ�</c:when>
														<c:when test="${article.articleClass==1}">�ӫ~����</c:when>
														<c:when test="${article.articleClass==2}">�B���ɨ�</c:when>
														<c:otherwise>���~</c:otherwise>
													</c:choose>
												</div>
												<div class="col-6">
													<c:choose>
														<c:when test="${article.articleType==0}">[�Q��]</c:when>
														<c:when test="${article.articleType==1}">[�o��]</c:when>
														<c:when test="${article.articleType==2}">[�߱o]</c:when>
														<c:otherwise>���~</c:otherwise>
													</c:choose>
													${article.articleTitle}
												</div>
												<div class="col-2">${article.articlePop}</div>
												<div class="col-2" style="font-size: 8px;"><%=tformat.format(pageContext.getAttribute("tdate")) %></div>
											</div>
										</a>
									</form>
								</c:forEach>
								<!-- close��s���C�� -->
							</div>

						</div>
						<%@ include file="articlepage2.file"%>
						<!-- close�׾¥��饻�� -->
					</div>
					<!-- close�~�� -->
				</div>
			</div>
		</div>
	</section>
	<!-- CLOSE �׾¥��� -->
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