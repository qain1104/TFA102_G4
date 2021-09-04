<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.venue_watchlist.model.*"%>

<%
	VenueWatchListVO venueWatchListVO = (VenueWatchListVO) request.getAttribute("venueWatchListVO");
%>



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
	width: 400px;
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
				<h3>
					<b>追蹤明細資料</b>
				</h3>
				<button type="button" class="btn btn-success btn-lg "
					onclick="location.href='<%=request.getContextPath()%>/venue_watchlist/select_page.jsp'">租借首頁</button>
			</td>
		</tr>
	</table>


	<div class="container py-2">
		<div class="row justify-content-md-center">
			<div class="col col-lg-4">
				<div class="form-floating" col-lg-2>
					<input type="text" class="form-control"
						placeholder="Disabled input" id="formGroupExampleInput"
						name="venueWatchListSN"
						value="<%=venueWatchListVO.getVenueWatchListSN()%>" disabled>
					<label for="floatingInputGrid"><b>追蹤明細編號</b></label>

				</div>
			</div>
		</div>
	</div>

	<div class="container py-2">
		<div class="row justify-content-md-center">
			<div class="col col-lg-4">
				<div class="form-floating" col-lg-2>
					<input type="text" class="form-control"
						placeholder="Disabled input" id="formGroupExampleInput"
						name="venueSN" value="<%=venueWatchListVO.getVenueSN()%>" disabled>
					<label for="floatingInputGrid">場地編號</label>
				</div>
			</div>
		</div>
	</div>

	<div class="container py-2">
		<div class="row justify-content-md-center">
			<div class="col col-lg-4">
				<div class="form-floating" col-lg-2>
					<input type="text" class="form-control"
						placeholder="Disabled input" id="formGroupExampleInput"
						name="userId" value="<%=venueWatchListVO.getUserId()%>" disabled>
					<label for="floatingInputGrid">會員編號</label>
				</div>
			</div>
		</div>
	</div>
	<br>

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