<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>
<head>
<title>���a�޲z</title>

<link rel="apple-touch-icon" href="assets/img/apple-icon.png">
<link rel="shortcut icon" type="image/x-icon"
	href="assets/img/favicon.ico">

<!-- �����ƪ��Ϊ� -->
<link rel="stylesheet" href="assets/css/bootstrap.min.css">
<!-- �����D���F�� -->
<link rel="stylesheet" href="assets/css/templatemo.css">
<!-- <link rel="stylesheet" href="assets/css/custom.css"> -->

<!-- Load fonts style after rendering the layout styles -->
<!-- �]�w�r�� -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;200;300;400;500;700;900&display=swap">
<!-- �פJ�Ϥ� -->
<link rel="stylesheet" href="assets/css/fontawesome.min.css">
<!-- �ڪ�CSS -->
<link rel="stylesheet" href="assets/css/aply.css">

<style>
table#table-1 {
	width: 450px;
	margin-top: 5px;
	margin-bottom: 10px;
	border: 3px ridge Gray;
	height: 80px;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

</head>
<body>

	<jsp:include page="/header.jsp" flush="true" />


	<%-- ���~��C --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">�Эץ��H�U���~:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<ul>
		<li><a
			href='<%=request.getContextPath()%>/venue/listAllVenue.jsp'>�d��</a><br>
			<br></li>
	
		<li><a
			href='<%=request.getContextPath()%>/venue/listAllVenue.jsp'>�ק�</a><br>
			<br></li>
			
		<li>
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/venue/venue.do">
				<b>��J�d�ߪ����a�s�� :</b> <input type="text" name="venueSN"> <input
					type="hidden" name="action" value="getOne_For_Display"> <input
					type="submit" value="�e�X">
			</FORM>
		</li>

		<jsp:useBean id="venueSvc" scope="page"
			class="com.venue.model.VenueService" />

		<li>
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/venue/venue.do">
				<b>��ܬd�ߪ����a�s��:</b> <select size="1" name="venueSN">
					<c:forEach var="venueVO" items="${venueSvc.all}">
						<option value="${venueVO.venueSN}">${venueVO.venueSN}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="�e�X">
			</FORM>
		</li>

<!-- 		<li> -->
<!-- 			<FORM METHOD="post" -->
<%-- 				ACTION="<%=request.getContextPath()%>/venue/venue.do"> --%>
<!-- 				<b>��ܬd�ߪ����~�|���s��:</b> <select size="1" name="venueSN"> -->
<%-- 					<c:forEach var="venueVO" items="${venueSvc.all}"> --%>
<%-- 						<option value="${venueVO.venueSN}">${venueVO.corpUserId} --%>
<%-- 					</c:forEach> --%>
<!-- 				</select> <input type="hidden" name="action" value="getOne_For_Display"> -->
<!-- 				<input type="submit" value="�e�X"> -->
<!-- 			</FORM> -->
<!-- 		</li> -->

		<!-- 		<li> -->
		<%-- 			<FORM METHOD="post" ENCTYPE="multipart/form-data" ACTION"<%=request.getContextPath()%>/venue/venue.do"></FORM> --%>
		<!-- 			<b>�W��:</b> <input type="hidden" name="action" value="upload_photo">  -->
		<!-- 			<input type="file" name="upload">  -->
		<!-- 			<input type="submit" > -->
		<!-- 			</FORM> -->
		<!-- 		</li> -->
	</ul>

	<ul>
		<li><a href='<%=request.getContextPath()%>/venue/addVenue.jsp'>�s�W���a���</a></li>
	</ul>
	<jsp:include page="/footer.jsp" flush="true" />
</body>
</html>