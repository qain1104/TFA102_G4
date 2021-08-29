<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.article.model.*"%>

<%
	ARTICLEVO articleVO = (ARTICLEVO) request.getAttribute("articleVO");
	Integer userId = null;
%>
<c:if test="${empty not userId}">
<%userId=(Integer)session.getAttribute("userId"); %>
</c:if>
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
<!-- footerheader css -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/custom.css">
</head>
<body>
<jsp:include page="/header.jsp" flush="true" />
	<!-- �׾¥��� -->
	<div class="bg-light">
		<div class="container pb-5 pt-5">
			<div class="row justify-content-center article-main">

				<!-- ����side -->
				<div class="col-md-2 side">
					<jsp:include page="articleside.jsp" flush="true"/>
				</div>
				<!-- close����side -->

				<!-- �׾¥��饻�� -->
				<div class="col-md-8 article-zone">
					<!-- �~�� -->
					<div class="card col-lg-12 pb-3 article-card">
						<!-- �Ĥ@�� �j�� -->
						<div class="container mt-4">
							<div
								class="row justify-content-between align-items-end articletitle">
								<div class="col-auto">
									<p class="fs-2 fw-bold text-success">�o��峹</p>
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
								<%-- ��� --%>
								<form METHOD="post"
									ACTION="<%=request.getContextPath()%>/article/article.do">
									<p class="fs-5 text-success">�峹����</p>
									<div class="col-md-3 mb-3">
										<!--����input -->
										<input type="hidden" name="userId" value="<%=userId%>">
										<input type="hidden" name="articlePop" value="0"> <input
											type="hidden" name="articleLikes" value="0"> <input
											type="hidden" name="articleDate" value=<%=new Date()%>>
										<input type="hidden" name="articleUpDate"
											value="<%=new Date()%>"> <input type="hidden"
											name="articleStatus" value="0">
										<!--!����input -->
										<select class="form-select" name="articleClass" required>
											<option selected disabled>����</option>
											<option value="0">�B�ʥ�</option>
											<option value="1">�ӫ~����</option>
											<option value="2">�B���ɨ�</option>
										</select>
									</div>
									<div class="input-group mb-3">
										<div class="col-md-3">
											<select class="form-select" name="articleType" required>
												<option selected disabled>����</option>
												<option value="0">�Q��</option>
												<option value="1">�o��</option>
												<option value="2">�߱o</option>
											</select>
										</div>
										<input type="text" class="form-control" name="articleTitle"
											placeholder="�п�J���D"
											value="<%=(articleVO == null) ? "" : articleVO.getArticleTitle()%>"
											required>
									</div>
									<div class="mb-3">
										<label for="exampleFormControlTextarea1" class="form-label">���e</label>
										<script
											src="https://cdn.ckeditor.com/4.7.3/standard/ckeditor.js"></script>
										<textarea name="articleContent"></textarea>
										<script>
											CKEDITOR.replace("articleContent");
										</script>
									</div>

									<div class="d-grid gap-2 d-md-flex justify-content-md-end">
										<input type="hidden" name="action" value="insert"> <input
											class="btn btn-success" type="submit" value="�e�X�峹">
									</div>
								</form>
								<%-- !��� --%>
							</div>
						</div>
						<!-- close�Ĥ@�� �j�� -->
					</div>
					<!-- close�~�� -->
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/footer.jsp" flush="true" />
</body>
</html>