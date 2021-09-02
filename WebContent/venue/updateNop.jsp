<%@page import="com.venue.util.choosetime"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.venue.model.*"%>
<%@ page import="com.venue_nophours.model.*"%>

<%
	VenueNophoursVO venueNophoursVO = (VenueNophoursVO) session.getAttribute("venueNophoursVO");
	SimpleDateFormat tdformat = new SimpleDateFormat("yyyy/MM/dd");
	choosetime cs = new choosetime();
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

	<FORM METHOD="post"
		ACTION="<%=request.getContextPath()%>/venuenop/venuenop.do"
		name="form1">

		<div class="mt-1 px-3 " align="center">
			<label for="formGroupExampleInput" class="form-label">場地編號 </label> <input
				type="text" class="form-control" style="width: 50%"
				id="formGroupExampleInput" name="venueSN"
				value="<%=venueNophoursVO.getVenueSN()%>" readonly>
		</div>
		<div class="mt-1 px-3 " align="center">
			<label for="formGroupExampleInput" class="form-label">場地不開放日期
			</label> <input type="text" class="form-control" style="width: 50%"
				id="formGroupExampleInput" name="venueDate"
				value="<%=tdformat.format(venueNophoursVO.getVenueDate())%>"
				readonly>
		</div>

		<%
			int[] temp = cs.selecttime(venueNophoursVO.getVenueNophours());
			pageContext.setAttribute("temp0", temp[0]);
			pageContext.setAttribute("temp1", temp[1]);
			pageContext.setAttribute("temp2", temp[2]);
		%>
		
		<div style="position: relative; left: 44%" class="mt-3">
			<label for="formGroupExampleInput" class="form-label">場地不開放時段
			</label>
			<div class="form-check">
				<input class="form-check-input" type="checkbox" name="venueNophours"
					id="flexCheckDefault" value="1" ${(temp0 != 0)? "checked":""}> <label
					class="form-check-label" for="flexCheckDefault">
					早上9點~中午12點 </label>
			</div>
			<div class="form-check">
				<input class="form-check-input" type="checkbox" name="venueNophours"
					id="flexCheckChecked" value="2" ${(temp1 != 0)? "checked":""}> <label
					class="form-check-label"> 中午12點~下午3點 </label>
			</div>
			<div class="form-check">
				<input class="form-check-input" type="checkbox" name="venueNophours"
					id="flexCheckChecked" value="4" ${(temp2 != 0)? "checked":""}> <label
					class="form-check-label"> 下午3點~下午6點 </label>
			</div>
		</div>
			<div style="position: relative; left: 47%" class="mt-3">
				<input type="hidden" name="venueNophoursSN"
					value="${venueNophoursVO.getVenueNophoursSN()}"> <input
					type="hidden" name="action" value="updateNopHours">
					<input type="hidden" name="whichPage" value="${whichPage}"> <input
					type="submit" value="修改" class="btn btn-success">
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
</body>
</html>