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

h4 {
	color: blue;
	display: inline;
}

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
	width: 5px;
	padding: 5px;
	text-align: center;
}

table#table02 {
	height: 10px;
}
</style>
</head>
<body background="">

	<jsp:include page="/header.jsp" flush="true" />

	<table id="table01" align="center">

		<tr>
			<td>
				<h3>
					<b>我的場地租借單</b>
				</h3>
				<button type="button" class="btn btn-success btn-lg "
					onclick="location.href='<%=request.getContextPath()%>/venueUser/listAllVenue.jsp'">場地列表</button>
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


	<%--  	<center>
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/rental_list/rental_list.do">
				<b class="paying container">輸入租借單編號 (如22001):</b> <br>
				<input type="text" name="rentalListSN"> <input type="hidden"
					name="action" value="getOne_For_Display"> <input
					type="submit" value="送出">
			</FORM>



			


			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/rental_list/rental_list.do">
				<b class="paying container">選擇租借單編號:</b> <br>
				<select size="1" name="rentalListSN">
					<c:forEach var="rentalListVO" items="${rentalListSvc.all}">
						<option value="${rentalListVO.rentalListSN}">${rentalListVO.rentalListSN}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</center> --%>


	<jsp:useBean id="rentalListSvc" scope="page"
		class="com.rental_list.model.RentalListService" />
		
	<!--浮動標籤 -->

	<div class="container py-2">
		<div class="row justify-content-md-center">
			<div class="col col-lg-4">
				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/rental_list/rental_list.do">
					<div class="form-floating" col-lg-2>

						<input type="text" class="form-control" placeholder="text"
							value="" name="rentalListSN"> <label
							for="floatingInputGrid">輸入租借單編號 (如22001):</label> <input
							type="hidden" name="action" value="getOne_For_Display"> <input
							type="submit" class="btn btn-success" value="送出">
					</div>
				</FORM>
			</div>
		</div>
	</div>
<%-- 
	<div class="container py-2">
		<div class="row justify-content-md-center">
			<div class="col col-lg-4">
				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/rental_list/rental_list.do">
					<div class="form-floating" col-lg-2>

						<select class="form-select" id="floatingSelectGrid"
							aria-label="Floating label select example" name="rentalListSN">

							<c:forEach var="rentalListVO" items="${rentalListSvc.all}">
								<option value="${rentalListVO.rentalListSN}">${rentalListVO.rentalListSN}
							</c:forEach>
						</select> <label for="floatingSelectGrid">選擇租借單編號</label> <input
							type="hidden" name="action" value="getOne_For_Display"> <input
							type="submit" class="btn btn-success" value="送出">
					</div>
				</FORM>
			</div>
		</div>
	</div>
--%>

	<%--  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/rental_list/rental_list.do" >
       <b>選擇場地編號:</b>
       <select size="1" name="rentalListSN">
         <c:forEach var="rentalListVO" items="${rentalListSvc.all}" > 
          <option value="${rentalListVO.rentalListSN}">${rentalListVO.venueSN}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>--%>



	

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