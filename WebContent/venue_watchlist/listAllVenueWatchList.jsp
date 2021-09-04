<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.venue_watchlist.model.*"%>

<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	VenueWatchListService venueWatchListSvc = new VenueWatchListService();
	List<VenueWatchListVO> list = venueWatchListSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<jsp:useBean id="venueSvc" scope="page" class="com.venue.model.VenueService" />
<html>
<head>
	<title>Sportify</title>
	<link rel="shortcut icon" href="<%= request.getContextPath() %>/assets/img/logo1.png" type="image/x-icon" />
	<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;200;300;400;500;700;900&display=swap">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/fontawesome.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/templatemo.css">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/custom.css">

<style>
table#table01 {
	border: 2px solid black;
	text-align: center;
}

table#table01 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}

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
	width: 5px;
	padding: 5px;
	text-align: center;
}

table#table02 {
	height: 10px;
}
</style>

</head>
<body>

	<jsp:include page="/header.jsp" flush="true" />
	<table id="table01" align="center">
		<tr>
			<td>
				<h3>
					<b>所有追蹤資料</b>
				</h3>
				<button type="button" class="btn btn-success btn-lg "
					onclick="location.href='<%=request.getContextPath()%>/venueUser/listAllVenue.jsp'">租借場地</button>
			</td>
		</tr>
	</table>

	<%-- 錯誤表列 --%>
	<div align="center">
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">請修正以下錯誤:</font>
			
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			
		</c:if>
	</div>

	<table class="table container">
		<thead class="table-success">
			<tr>
	<%--			<th scope="col">追蹤編號</th>--%>
				<th scope="col">場地編號</th>
	<%--			<th scope="col">會員編號</th> --%>
				<th scope="col">場地名稱</th>
            	<td scope="col"><b>場地圖片</b></td>
            	<th scope="col">取消追蹤</th>
				
			</tr>
		</thead>

		<%@ include file="/pages/page1.file"%>
		<c:forEach var="venueWatchListVO" items="${list}"
			begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">

			<tr>
<%--				<th scope="row">${venueWatchListVO.venueWatchListSN}</th>--%>
				<th scope="row">${venueWatchListVO.venueSN}</th>
<%--				<th scope="row">${venueWatchListVO.userId}</th> --%>
				<th scope="row">${venueSvc.getOneVenue(venueWatchListVO.venueSN).venueName}</th>
            	
            	<td ><img src="<%=request.getContextPath()%>/ReadVenuePic?venueSN=${venueWatchListVO.venueSN}"  style="width: 100px;"></td>
				
<%-- 				<th scope="row">
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/venue_watchlist/venue_watchlist.do"
						style="margin-bottom: 0px;">
						<input type="submit" class="btn btn-success" value="修改"> <input
							type="hidden" name="venueWatchListSN"
							value="${venueWatchListVO.venueWatchListSN}"> <input
							type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</th>--%>
				<th scope="row">
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/venue_watchlist/venue_watchlist.do"
						style="margin-bottom: 0px;">
						<input type="submit" class="btn btn-success" value="取消追蹤"> <input
							type="hidden" name="venueWatchListSN"
							value="${venueWatchListVO.venueWatchListSN}"> <input
							type="hidden" name="action" value="delete">
					</FORM>
				</th>

			</tr>
		</c:forEach>
	</table>
	<%@ include file="/pages/page2.file"%>
	<jsp:include page="/footer.jsp" flush="true" />
	<!-- Start Script -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js"></script>
    <script src="<%=request.getContextPath() %>/assets/js/jquery-3.6.0.min.js"></script>
    <script src="<%=request.getContextPath() %>/assets/js/jquery-migrate-1.2.1.min.js"></script>
    <script src="<%=request.getContextPath() %>/assets/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <script src="<%=request.getContextPath() %>/assets/js/templatemo.js"></script>
    <script src="<%=request.getContextPath() %>/assets/js/custom.js"></script>
    <script src="<%= request.getContextPath() %>/assets/js/general_order.js"></script>
    <!-- End Script -->
</body>
</html>