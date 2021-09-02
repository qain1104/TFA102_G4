<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.venue.model.*"%>
<%@ page import="com.venue_nophours.model.*"%>
<%
	Integer corpUserId = (Integer)session.getAttribute("corpUserId");
	VenueService venSvc = new VenueService();
	List<VenueVO> list = venSvc.getMyVenue(corpUserId);
	pageContext.setAttribute("list", list);
%>

<!-- 	 -->
<html>
<head>
<title>全部場地資料</title>

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

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<div style="text-align: center"><%@ include
			file="/pages/page1.file"%></div>


	<table class="table" >
		<thead>
			<th scope="col"></th>
			<th scope="col" style="min-width:30px">場地編號</th>
			<th scope="col" style="min-width:30px">會員編號</th>
			<th scope="col" >場地名稱</th>
			<th scope="col" style="min-width:30px">類型</th>
			<th scope="col" >場地描述</th>
			<th scope="col" style="min-width:30px">租借金額</th>
			<th scope="col" style="min-width:30px">容納人數</th>
			<th scope="col" style="min-width:30px">電話</th>
			<th scope="col" style="min-width:30px">場地圖片</th>
			<th scope="col" style="min-width:30px">場地地址</th>
			<th scope="col" style="min-width:30px">狀態</th>
			<th scope="col" style="max-width: 70px !important;">場地不開放時間</th>
		</thead>

		<c:forEach var="venueVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">

			<tbody>
				<tr>
					<th scope="row"><FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/venue/venue.do"
							style="margin-bottom: 0px;">
							<input type="submit" value="修改" class="btn btn-success">
							<input type="hidden" name="venueSN" value="${venueVO.venueSN}">
							<input type="hidden" name="action" value="getOne_For_Update">
						</FORM></th>
					<td style="min-width:30px">${venueVO.venueSN}</td>
					<td style="min-width:30px">${venueVO.corpUserId}</td>
					<td style="max-width:30px">${venueVO.venueName}</td>
					<td style="min-width:30px"><c:choose>
							<c:when test="${venueVO.venueClass==1}">籃球場</c:when>
							<c:when test="${venueVO.venueClass==2}">網球場</c:when>
							<c:when test="${venueVO.venueClass==3}">排球場</c:when>
							<c:when test="${venueVO.venueClass==4}">壘球場</c:when>
							<c:when test="${venueVO.venueClass==5}">羽球場</c:when>
						</c:choose></td>
					<td style="max-width:30px"> ${venueVO.venueInfo}</td>
					<td>${venueVO.venuePrice}</td>
					<td>${venueVO.venueAccommodate}</td>
					<td>${venueVO.venuePhone}</td>
					<td>
						<div>
							<img style="max-width:70px;min-height:70px"
								src="<%=request.getContextPath()%>/ReadVenuePic?venueSN=${venueVO.venueSN}">
						</div>
					</td>
					<td style="width: 100px !important;">${venueVO.venueAddress}</td>
					<td><c:choose>
							<c:when test="${venueVO.venueStatus==0}">審核中</c:when>
							<c:when test="${venueVO.venueStatus==1}">上架中</c:when>
							<c:when test="${venueVO.venueStatus==2}">審核未通過</c:when>
						</c:choose></td>

					<th scope="row"><FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/venuenop/venuenop.do"
							style="margin-bottom: 0px;">
							<input type="hidden" name="venueSN" value="${venueVO.venueSN}">
							<input type="hidden" name="action" value="newNopDate"> <input
								type="submit" value="新增場地不開放時間" class="btn btn-success">
						</FORM> <br>
						<FORM METHOD="get"
							ACTION="<%=request.getContextPath()%>/venue/listAllVenueNop.jsp"
							style="margin-bottom: 0px;">
							<input type="hidden" name="venueSN" value="${venueVO.venueSN}">
							<input type="submit" value="查詢場地不開放時間" class="btn btn-success">
						</FORM></th>
				</tr>
			</tbody>

		</c:forEach>

	</table>
	<!-- 	</table> -->
	<div style="text-align: center">
		<%@ include file="/pages/page2.file"%></div>
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