<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="Big5"%>
<%@ page import="com.CorpUser.model.*"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/cssLink.jsp"></jsp:include>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<%

	CorpUserVO currentC =  (CorpUserVO) session.getAttribute("currentC");
	Integer corpUserId = (Integer) session.getAttribute("corpUserId");
	if(currentC == null){
		session.setAttribute("location", request.getRequestURI());
		response.sendRedirect(request.getContextPath()+"/login.jsp");
		return;
	}
%>

<body>
	<jsp:include page="/header.jsp" flush="true" />
	<!-- ���~�|�����ߥ��� -->
	<div class="container pb-5 pt-5">
		<h1 class=" text-success fw-bold">�|������ ���~�|��</h1>

		<div class="row mt-5">
			<div class="col text-center">
				<h3>���~���</h3>
				<form
					action="<%=request.getContextPath()%>/corpUser/CorpUserServlet.do"
					method="post">
					<input type="hidden" name="action" value="getOne_For_Display">
					<ul class="list-group">
						<li class="list-group-item"><button type="submit"
								class="btn btn-success">��ƭק�</button></li>
					</ul>
				</form>
			</div>
			<div class="col text-center">
				<h3>�ӫ~�޲z</h3>
				<form action="�e�챵���d�ݭq�檺Servlet" method="post">
					<input type="hidden" name="action" value="">

					<ul class="list-group">
						<li class="list-group-item"><button type="button"
								class="btn btn-success">�ӫ~�W�U�[</button></li>
						<li class="list-group-item"><button type="button"
								class="btn btn-success">�ק�ӫ~��T</button></li>
						<li class="list-group-item"><button type="button"
								class="btn btn-success">�d�ݰӫ~����</button></li>
					</ul>
			</div>
			<div class="col text-center">
				<h3>���ɺ޲z</h3>
				<ul class="list-group">
					<li class="list-group-item"><a class="btn btn-success" href="<%=request.getContextPath()%>/venue/addVenue.jsp">�s�W���a </a></li>
					<li class="list-group-item"><a class="btn btn-success" href="<%=request.getContextPath()%>/venue/listAllVenue.jsp">�d�ݳ��a </a></li>
<!-- 					<li class="list-group-item"><button type="button" -->
<!-- 							class="btn btn-success">���ɳ�޲z</button></li> -->
				</ul>
			</div>
			<div class="col text-center">
				<h3>�ڪ�����</h3>
				<ul class="list-group">
					<li class="list-group-item"><button type="button"
							class="btn btn-success">�d�ݰӫ~����</button></li>
					<li class="list-group-item"><button type="button"
							class="btn btn-success">�d�ݳ��a����</button></li>
				</ul>
			</div>
<!-- 			<div class="col text-center"> -->
<!-- 				<h3>�ڪ��f��</h3> -->
<!-- 				<ul class="list-group"> -->
<!-- 					<li class="list-group-item"><button type="button" -->
<!-- 							class="btn btn-success">�d�ݰӫ~�f��</button></li> -->
<!-- 					<li class="list-group-item"><button type="button" -->
<!-- 							class="btn btn-success">�d�ݳ��a�f��</button></li> -->
<!-- 				</ul> -->
<!-- 			</div> -->
		</div>
	</div>
	<!-- Close �@��|������ -->
	<jsp:include page="/footer.jsp"></jsp:include>
	  <jsp:include page="/jsLink.jsp" flush="true"/>
</body>


</html>
