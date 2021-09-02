<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.venue.model.*"%>

<%
	VenueVO venueVO = (VenueVO) request.getAttribute("venueVO");
%>

<html>
<head>
<title>場地資料</title>
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
table#table-1 {
	border: 2px solid black;
	text-align: center;
	margin: middle
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

<style>
table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
}
</style>

</head>
<body>
	<jsp:include page="/header.jsp" flush="true" />
	<table id="table-1" align="center">
		<tr>
			<td>
				<h3>場地資料</h3>
				<h4>
					<a href="<%=request.getContextPath()%>/venue/select_page.jsp">返回場地管理頁面</a>
				</h4>
			</td>
		</tr>
	</table>

	<div class="mt-3 px-3" align="center">
		<label for="formGroupExampleInput" class="form-label">場地編號 </label> <input
			type="text" placeholder="Disabled input" class="form-control"
			style="width: 50%" id="formGroupExampleInput" align="middle"
			value="<%=venueVO.getVenueSN()%>" disabled>
	</div>

	<div class="mt-3 px-3" align="center">
		<label for="formGroupExampleInput" class="form-label">企業會員編號 </label>
		<input type="text" placeholder="Disabled input" class="form-control"
			style="width: 50%" id="formGroupExampleInput"
			value="<%=venueVO.getCorpUserId()%>" disabled>
	</div>

	<div class="mt-3 px-3" align="center">
		<label for="formGroupExampleInput" class="form-label">場地名稱 </label> <input
			type="text" placeholder="Disabled input" class="form-control"
			style="width: 50%" id="formGroupExampleInput"
			value="<%=venueVO.getVenueName()%>" disabled>
	</div>

	<div class="mt-3 px-3" align="center">
		<label for="formGroupExampleInput" class="form-label">場地描述 </label> <input
			type="text" placeholder="Disabled input" class="form-control"
			style="width: 50%" id="formGroupExampleInput"
			value="<%=venueVO.getVenueInfo()%>" disabled>
	</div>

	<div class="mt-3 px-3" align="center">
		<label for="formGroupExampleInput" class="form-label">租借金額 </label> <input
			type="text" placeholder="Disabled input" class="form-control"
			style="width: 50%" id="formGroupExampleInput"
			value="<%=venueVO.getVenuePrice()%>" disabled>
	</div>

	<div class="mt-3 px-3" align="center">
		<label for="formGroupExampleInput" class="form-label">場地地址 </label> <input
			type="text" placeholder="Disabled input" class="form-control"
			style="width: 50%" id="formGroupExampleInput"
			value="<%=venueVO.getVenueAddress()%>" disabled>
	</div>

	<div class="mt-3 px-3" align="center">
		<label for="formGroupExampleInput" class="form-label">場地圖片 </label> <input
			type="text" placeholder="Disabled input" class="form-control"
			style="width: 50%" id="formGroupExampleInput"
			value="<%=venueVO.getVenuePic()%>" disabled>
	</div>

	<div class="mt-3 px-3" align="center">
		<label for="formGroupExampleInput" class="form-label">容納人數 </label> <input
			type="text" placeholder="Disabled input" class="form-control"
			style="width: 50%" id="formGroupExampleInput"
			value="<%=venueVO.getVenueAccommodate()%>" disabled>
	</div>

	<div class="mt-3 px-3 mb-5" align="center">
		<label for="formGroupExampleInput" class="form-label">電話 </label> <input
			type="text" placeholder="Disabled input" class="form-control"
			style="width: 50%" id="formGroupExampleInput"
			value="<%=venueVO.getVenuePhone()%>" disabled>
	</div>

	<!-- 	<table> -->
	<!-- 		<tr> -->
	<!-- 			<th>場地編號</th> -->
	<!-- 			<th>企業會員編號</th> -->
	<!-- 			<th>場地名稱</th> -->
	<!-- 			<th>場地描述</th> -->
	<!-- 			<th>租借金額</th> -->
	<!-- 			<th>場地地址</th> -->
	<!-- 			<th>場地圖片</th> -->
	<!-- 			<th>容納人數</th> -->
	<!-- 			<th>電話</th> -->
	<!-- 		</tr> -->
	<!-- 		<tr> -->
	<%-- 			<td><%=venueVO.getVenueSN()%></td> --%>
	<%-- 			<td><%=venueVO.getCorpUserId()%></td> --%>
	<%-- 			<td><%=venueVO.getVenueName()%></td> --%>
	<%-- 			<td><%=venueVO.getVenueInfo()%></td> --%>
	<%-- 			<td><%=venueVO.getVenuePrice()%></td> --%>
	<%-- 			<td><%=venueVO.getVenueAddress()%></td> --%>
	<%-- 			<td><%=venueVO.getVenuePic()%></td> --%>
	<%-- 			<td><%=venueVO.getVenueAccommodate()%></td> --%>
	<%-- 			<td><%=venueVO.getVenuePhone()%></td> --%>

	<!-- 		</tr> -->
	<!-- 	</table> -->

	<jsp:include page="/footer.jsp" flush="true" />
</body>
</html>