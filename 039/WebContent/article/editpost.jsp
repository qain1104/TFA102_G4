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
	<!-- �׾¥��� -->
	<section class="bg-light">
		<div class="container pt-5 pb-5">
			<div class="row justify-content-center article-main">

				<!-- ����side -->
				<div class="col-md-2 mt-5 side">
					<div class="card sticky-top">
						<div class="list-group list-group-flush">
							<%
								int whichClass = 0;
							%>
							<a href="<%=request.getContextPath()%>/article/article.jsp?whichClass=0"
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
				<div class="col-md-8 mt-5 article-zone">
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
										<input type="hidden" name="articlePop" value="0">
										<input type="hidden" name="articleLikes" value="0">
										<input type="hidden" name="articleDate" value=<%=new Date()%>>
										<input type="hidden" name="articleUpDate" value="<%=new Date()%>">
										<input type="hidden" name="articleStatus" value="0">
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
										<input type="text" class="form-control" name="articleTitle" placeholder="�п�J���D" value="<%= (aVO==null)? "" : aVO.getArticleTitle()%>" required>
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
	</section>
	<!-- CLOSE �׾¥��� -->
</body>
</html>