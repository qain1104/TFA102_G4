<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.venue.model.*"%>
<%@ page import="com.CorpUser.model.*" %>


<%
	CorpUserVO currentC = (CorpUserVO) session.getAttribute("currentC");
	Integer corpUserId = (Integer) session.getAttribute("corpUserId");
	if (currentC == null) {
		session.setAttribute("location", request.getRequestURI());
		response.sendRedirect(request.getContextPath() + "/login.jsp");
		return;
	}
	VenueVO venueVO = (VenueVO) request.getAttribute("venueVO");
%>

<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>

<!-- 網頁logo -->
<link rel="shortcut icon"
	href="<%=request.getContextPath()%>/assets/img/logo1.png"
	type="image/x-icon" />

<!-- 字體 -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;200;300;400;500;700;900&display=swap">

<!-- fontawesome -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/fontawesome.min.css">

<!-- bootstrap -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/bootstrap.min.css">

<!-- 套版原有的 -->
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
</style>
</head>
<body>

	<jsp:include page="/header.jsp" flush="true" />

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
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
			<label for="formGroupExampleInput" class="form-label">會員編號 </label> <input
				type="text" class="form-control" readonly="readonly" style="width: 50%"
				id="formGroupExampleInput" name="corpUserId"
				value="<%=(currentC == null) ? "" : corpUserId %>">
		</div>

		<div class="mt-1 px-3 " align="center">
			<label for="formGroupExampleInput" class="form-label">場地名稱 </label> <input
				type="text" class="form-control" style="width: 50%"
				id="formGroupExampleInput" name="venueName"
				value="<%=(venueVO == null) ? "" : venueVO.getVenueName()%>">
		</div>

		<div class="mt-1 px-3 " align="center">
			<label for="formGroupExampleInput" class="form-label">場地類型 </label> <select
				class="form-select" aria-label="Default select example"
				style="width: 50%" name="venueClass">
				<option value="1">籃球場</option>
				<option value="2">網球場</option>
				<option value="3">排球場</option>
				<option value="4">壘球場</option>
				<option value="5">羽球場</option>
			</select>
		</div>

		<div class="mt-1 px-3 " align="center">
			<label for="formGroupExampleInput" class="form-label">場地資訊</label> <input
				type="text" class="form-control" style="width: 50%"
				id="formGroupExampleInput" name="venueInfo"
				value="<%=(venueVO == null) ? "" : venueVO.getVenueInfo()%>">
		</div>

		<div class="mt-1 px-3 " align="center">
			<label for="formGroupExampleInput" class="form-label">場地預約價格
			</label> <input type="text" class="form-control" style="width: 50%"
				id="formGroupExampleInput" name="venuePrice"
				value="<%=(venueVO == null) ? "" : venueVO.getVenuePrice()%>">
		</div>

		<div class="mt-1 px-3 " align="center">
			<label for="formGroupExampleInput" class="form-label">場地地址 </label> <input
				type="text" class="form-control" style="width: 50%"
				id="formGroupExampleInput" name="venueAddress"
				value="<%=(venueVO == null) ? "" : venueVO.getVenueAddress()%>">
		</div>

		<div class="mt-1 px-3 " align="center">
			<label for="formGroupExampleInput" class="form-label">容納人數 </label> <input
				type="text" class="form-control" style="width: 50%"
				id="formGroupExampleInput" name="venueAccommodate"
				value="<%=(venueVO == null) ? "" : venueVO.getVenueAccommodate()%>">
		</div>

		<div class="mt-1 px-3 mb-3" align="center">
			<label for="formGroupExampleInput" class="form-label">電話 </label> <input
				type="text" class="form-control" style="width: 50%"
				id="formGroupExampleInput" name="venuePhone"
				value="<%=(venueVO == null) ? "" : venueVO.getVenuePhone()%>">
		</div>

		<div class="mt-1 px-3 mb-3" align="center">
			<label for="formGroupExampleInput" class="form-label">圖片 </label> <input
				type="file" name="venuePic"
				accept="image/gif, image/png, image/jpeg" id="file">

		</div>

		<div id="preview"
			style="position: relative; left: 20%; border-style: none;">
			<img width="750px" height="450px" id="demo" class="image" border="0" />
		</div>

		<div align="center" class="mt-3">
			<input type="hidden" name="action" value="insert"> <input
				type="submit" value="送出新增" class="btn btn-success">
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
	<!-- 應該跟上一個是同樣的檔 -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>

	<!-- 套版原有的 -->
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