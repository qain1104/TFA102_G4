<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.venue.model.*"%>

<%
	VenueVO venueVO = (VenueVO) request.getAttribute("venueVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>���a��Ƹ�ƭק�</title>

<!-- ����logo -->
<link rel="shortcut icon"
	href="<%=request.getContextPath()%>/assets/img/logo1.png"
	type="image/x-icon" />

<!-- �r�� -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;200;300;400;500;700;900&display=swap">

<!-- fontawesome -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/fontawesome.min.css">

<!-- bootstrap -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/bootstrap.min.css">

<!-- �M���즳�� -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/templatemo.css">

<!-- header -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/custom.css">

<!-- Slick -->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/assets/css/slick.min.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/assets/css/slick-theme.css">



<style>
table#table-1 {
	border: 2px solid black;
	margin-left: auto;
	margin-right: auto;
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
	text-align: center;
}

table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
}

table#table02 {
	height: 10px;
	margin-left: auto;
	margin-right: auto;
}
</style>

</head>
<body>
	<jsp:include page="/header.jsp" flush="true" />
	<!-- 	<table id="table-1"> -->
	<!-- 		<tr> -->
	<!-- 			<td> -->
	<!-- 				<h3>���a��ƭק�</h3> -->
	<!-- 				<h4> -->
	<%-- 					<a href="<%=request.getContextPath()%>/venue/select_page.jsp">��^�|������</a> --%>
	<!-- 				</h4> -->
	<!-- 			</td> -->
	<!-- 		</tr> -->
	<!-- 	</table> -->

	<%-- ���~��C --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">�Эץ��H�U���~:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post"
		ACTION="<%=request.getContextPath()%>/venue/venue.do" name="form1"
		enctype="multipart/form-data">

		<div class="mt-1 px-3" align="center">
			<label for="formGroupExampleInput" class="form-label">���a�s�� </label> <input
				type="text" class="form-control" style="width: 50%"
				id="formGroupExampleInput" name="venueSN"
				value="<%=venueVO.getVenueSN()%>" readonly>
		</div>

		<div class="mt-1 px-3" align="center">
			<label for="formGroupExampleInput" class="form-label">�|���s�� </label> <input
				type="text" class="form-control" style="width: 50%"
				id="formGroupExampleInput" name="corpUserId"
				value="<%=venueVO.getCorpUserId()%>" readonly>
		</div>

		<div class="mt-1 px-3 " align="center">
			<label for="formGroupExampleInput" class="form-label">���a�W�� </label> <input
				type="text" class="form-control" style="width: 50%"
				id="formGroupExampleInput" name="venueName"
				value="<%=venueVO.getVenueName()%>">
		</div>

		<div class="mt-1 px-3 " align="center">
			<label for="formGroupExampleInput" class="form-label">���a���� </label> <select
				class="form-select" aria-label="Default select example"
				style="width: 50%" name="venueClass">
				<option value="1"${venueVO.venueClass==1?"selected":""}>�x�y��</option>
				<option value="2"${venueVO.venueClass==2?"selected":""}>���y��</option>
				<option value="3"${venueVO.venueClass==3?"selected":""}>�Ʋy��</option>
				<option value="4"${venueVO.venueClass==4?"selected":""}>�S�y��</option>
				<option value="5"${venueVO.venueClass==5?"selected":""}>�вy��</option>
			</select>
		</div>

		<div class="mt-1 px-3 " align="center">
			<label for="formGroupExampleInput" class="form-label">���a��T</label> <input
				type="text" class="form-control" style="width: 50%"
				id="formGroupExampleInput" name="venueInfo"
				value="<%=venueVO.getVenueInfo()%>">
		</div>

		<div class="mt-1 px-3 " align="center">
			<label for="formGroupExampleInput" class="form-label">���a�w������
			</label> <input type="text" class="form-control" style="width: 50%"
				id="formGroupExampleInput" name="venuePrice"
				value="<%=venueVO.getVenuePrice()%>">
		</div>

		<div class="mt-1 px-3 " align="center">
			<label for="formGroupExampleInput" class="form-label">���a�a�} </label> <input
				type="text" class="form-control" style="width: 50%"
				id="formGroupExampleInput" name="venueAddress"
				value="<%=venueVO.getVenueAddress()%>">
		</div>

		<div class="mt-1 px-3 " align="center">
			<label for="formGroupExampleInput" class="form-label">�e�ǤH�� </label> <input
				type="text" class="form-control" style="width: 50%"
				id="formGroupExampleInput" name="venueAccommodate"
				value="<%=venueVO.getVenueAccommodate()%>">
		</div>

		<div class="mt-1 px-3 mb-3" align="center">
			<label for="formGroupExampleInput" class="form-label">�q�� </label> <input
				type="text" class="form-control" style="width: 50%"
				id="formGroupExampleInput" name="venuePhone"
				value="<%=venueVO.getVenuePhone()%>">
		</div>

		<div class="mt-1 px-3 mb-3" align="center">
			<label for="formGroupExampleInput" class="form-label">�Ϥ� </label> <input
				type="file" name="venuePic"
				accept="image/gif, image/png, image/jpeg" id="file">

		</div>

		<div id="preview" style="position: relative; left: 20%">
			<img width="750px" height="450px" id="demo" class="image"
				src="<%=request.getContextPath()%>/ReadVenuePic?venueSN=${venueVO.venueSN}">
		</div>


		<br> <input type="hidden" name="action" value="update"> <input
			type="hidden" name="venueSN" value="<%=venueVO.getVenueSN()%>">
		<input type="hidden" name="corpUserId"
			value="<%=venueVO.getCorpUserId()%>"> <input type="hidden"
			name="venueStatus" value="<%=venueVO.getVenueStatus()%>">
		<div align="center">
			<input type="submit" value="�e�X�ק�" class="btn btn-success">
		</div>

	</FORM>
	<jsp:include page="/footer.jsp" flush="true" />

	<!-- Start Script -->


	<!-- jQuery -->
	<script
		src="<%=request.getContextPath()%>/assets/js/jquery-3.6.0.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/assets/js/jquery-migrate-1.2.1.min.js"></script>

	<!-- bootstrap -->
	<script
		src="<%=request.getContextPath()%>/assets/js/bootstrap.bundle.min.js"></script>
	<!-- ���Ӹ�W�@�ӬO�P�˪��� -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>

	<!-- �M���즳�� -->
	<script src="<%=request.getContextPath()%>/assets/js/templatemo.js"></script>

	<!-- header -->
	<script src="<%=request.getContextPath()%>/assets/js/custom.js"></script>
	<!-- End Script -->
	<script>
		$('#file').change(function() {
			var file = $('#file')[0].files[0];
			var reader = new FileReader;
			reader.onload = function(e) {
				$('#demo').attr('src', e.target.result);
			};
			reader.readAsDataURL(file);
		});
	</script>
</body>
</html>