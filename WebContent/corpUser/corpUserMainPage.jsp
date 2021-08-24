<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="Big5"%>
<%@ page import="com.CorpUser.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<%
	CorpUserVO corpUserVO = (CorpUserVO) session.getAttribute("corpUserVO");
%>

<body>
	<jsp:include page="/header.jsp" flush="true" />
	<!-- ���~�|�����ߥ��� -->
	<div class="container">
		<h1>�|������-���~�|��</h1>
		<p>��!${corpUserVO.companyName}</p>

		<div class="row">
			<div class="col">
				<h3>���~���</h3>
				<form
					action="<%=request.getContextPath()%>/corpUser/CorpUserServlet.do"
					method="post">
					<input type="hidden" name="action" value="getOne_For_Display">
					<ul class="list-group">
						<li class="list-group-item"><button type="submit"
								class="btn btn-primary">��ƭק�</button></li>
					</ul>
				</form>
			</div>
			<div class="col">
				<h3>�ӫ~�޲z</h3>
				<form action="�e�챵���d�ݭq�檺Servlet" method="post">
					<input type="hidden" name="action" value="">

					<ul class="list-group">
						<li class="list-group-item"><button type="button"
								class="btn btn-primary">�ӫ~�W�U�[</button></li>
						<li class="list-group-item"><button type="button"
								class="btn btn-primary">�ק�ӫ~��T</button></li>
						<li class="list-group-item"><button type="button"
								class="btn btn-primary">�d�ݰӫ~����</button></li>
					</ul>
			</div>
			<div class="col">
				<h3>���ɺ޲z</h3>
				<ul class="list-group">
					<li class="list-group-item"><button type="button"
							class="btn btn-primary">�s�W���a</button></li>
					<li class="list-group-item"><button type="button"
							class="btn btn-primary">�ק���a</button></li>
					<li class="list-group-item"><button type="button"
							class="btn btn-primary">���ɳ�޲z</button></li>
				</ul>
			</div>
			<div class="col">
				<h3>�ڪ�����</h3>
				<ul class="list-group">
					<li class="list-group-item"><button type="button"
							class="btn btn-primary">�d�ݰӫ~����</button></li>
					<li class="list-group-item"><button type="button"
							class="btn btn-primary">�d�ݳ��a����</button></li>
				</ul>
			</div>
			<div class="col">
				<h3>�ڪ��f��</h3>
				<ul class="list-group">
					<li class="list-group-item"><button type="button"
							class="btn btn-primary">�d�ݰӫ~�f��</button></li>
					<li class="list-group-item"><button type="button"
							class="btn btn-primary">�d�ݳ��a�f��</button></li>
				</ul>
			</div>
		</div>
	</div>
	<!-- Close �@��|������ -->
	<jsp:include page="/footer.jsp"></jsp:include>
</body>


</html>
