<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.venue.model.*"%>
<%@ page import="com.venue_nophours.model.*"%>

<%
	VenueNophoursVO venueNophoursVO = (VenueNophoursVO) request.getAttribute("venueNophoursVO");
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

</head>
<body>
	<jsp:include page="/header.jsp" flush="true" />

	<div>
		<h3 align="center" style="padding:10px">請選擇不開放日期所對應的不開放時段</h3>
		<FORM METHOD="post"
			ACTION="<%=request.getContextPath()%>/venuenop/venuenop.do"
			name="form1">
			<c:forEach var="date" items="${chooseDateList}">
				<div style="position: relative; left: 40%">
					<span>${date}</span>
					<div>
						<div class="form-check">
							<input class="form-check-input" type="checkbox" name="${date}"
								id="flexCheckDefault" value="1"> <label
								class="form-check-label" for="flexCheckDefault">
								早上9點~中午12點 </label>
						</div>
						<div class="form-check">
							<input class="form-check-input" type="checkbox" name="${date}"
								id="flexCheckChecked" value="2"> <label
								class="form-check-label"> 中午12點~下午3點 </label>
						</div>
						<div class="form-check">
							<input class="form-check-input" type="checkbox" name="${date}"
								id="flexCheckChecked" value="4"> <label
								class="form-check-label"> 下午3點~下午6點 </label>
						</div>
					</div>
				</div>
			</c:forEach>

			<div align="center" class="mt-3">
				<input type="hidden" name="action" value="insertNopHours"> <input
					type="submit" value="送出新增" class="btn btn-success">
			</div>
		</FORM>
	</div>
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

</body>
</html>