<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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

table {
	width: 400px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

th, td {
	width: 5px;
	padding: 5px;
	text-align: center;
}


</style>
</head>
<body>

	<jsp:include page="/header.jsp" flush="true" />
	<table id="table01" align="center">

		<tr>
			<td><h3>
					<b>場地追蹤</b>
				</h3></td>
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





	<jsp:useBean id="venueWatchListSvc" scope="page"
		class="com.venue_watchlist.model.VenueWatchListService" />
	<!--浮動標籤 -->

	<div class="container py-2">
		<div class="row justify-content-md-center">
			<div class="col col-lg-4">
				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/venue_watchlist/venue_watchlist.do">
					<div class="form-floating" col-lg-2>

						<input type="text" class="form-control" placeholder="text"
							value="" name="venueWatchListSN"> <label
							for="floatingInputGrid">輸入追蹤明細編號 (如21001):</label> <input
							type="hidden" name="action" value="getOne_For_Display"> <input
							type="submit" class="btn btn-success" value="送出">
					</div>
				</FORM>
			</div>
		</div>
	</div>

	<div class="container py-2">
		<div class="row justify-content-md-center">
			<div class="col col-lg-4">
				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/venue_watchlist/venue_watchlist.do">
					<div class="form-floating" col-lg-2>

						<select class="form-select" id="floatingSelectGrid"
							aria-label="Floating label select example" name="venueWatchListSN">

							<c:forEach var="venueWatchListVO" items="${venueWatchListSvc.all}">
								<option value="${venueWatchListVO.venueWatchListSN}">${venueWatchListVO.venueWatchListSN}
							</c:forEach>
						</select> <label for="floatingSelectGrid">選擇追蹤明細編號</label> <input
							type="hidden" name="action" value="getOne_For_Display"> <input
							type="submit" class="btn btn-success" value="送出">
					</div>
				</FORM>
			</div>
		</div>
	</div>


	



	<ul>
		<center>
			<button type="button" class="btn btn-success btn-lg "
				onclick="location.href='<%=request.getContextPath()%>/venue_watchlist/listAllVenueWatchList.jsp'">查詢全部追蹤</button>
			<button type="button" class="btn btn-success btn-lg "
				onclick="location.href='<%=request.getContextPath()%>/venue_watchlist/addVenueWatchList.jsp'">新增追蹤</button>
		</center>
	</ul>

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