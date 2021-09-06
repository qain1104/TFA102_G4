<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.rental_list.model.*"%>

<%
	RentalListVO rentalListVO = (RentalListVO) request.getAttribute("rentalListVO");
%>

<html>
<head>
<meta charset="BIG5">
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

table#table-1 {
	border: 2px solid black;
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
}
</style>

<style>
table {
	width: 400px;
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
	<table id="table01" align="center">
		<tr>
			<td>
				<h3>
					<b>租借單新增</b>
				</h3>
				<button type="button" class="btn btn-success btn-lg "
					onclick="location.href='<%=request.getContextPath()%>/venueUser/listAllVenue.jsp'">租借首頁</button>
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


	<FORM METHOD="post"
		ACTION="<%=request.getContextPath()%>/rental_list/rental_list.do"
		name="form1" class="row g-3" enctype="multipart/form-data">
		<table>
			<%--<tr>
		<td>場地編號:</td>
		<td><input type="TEXT" name="venueSN" size="45" 
			 value="<%= (rentalListVO==null)? "" : rentalListVO.getVenueSN()%>" /></td>
	</tr>
	<tr>
		<td>會員編號:</td>
		<td><input type="TEXT" name="userId" size="45"
			 value="<%= (rentalListVO==null)? "" : rentalListVO.getUserId()%>" /></td>
	</tr>
	<tr>
		<td>歸還狀態:</td>
		<td><input type="TEXT" name="returnStatus" size="45"
			 value="<%= (rentalListVO==null)? "" : rentalListVO.getReturnStatus()%>" /></td>
	</tr>
	<tr>
		<td>租借日期:</td>
		<td><input name="rentalDate" id="f_date1" type="text"></td>
	</tr>
	<tr>
		<td>租借時段:</td>
		<td><input type="TEXT" name="rentalTime" size="45"
			 value="<%= (rentalListVO==null)? "" : rentalListVO.getRentalTime()%>" /></td>
	</tr>--%>


			<%-- 原本的
	<div class="mt-3 px-3 mb-5" align="center">
				<label for="formGroupExampleInput" class="form-label">場地編號</label> <input
					type="text"  class="form-control"
					style="width: 30%" id="formGroupExampleInput"name="venueSN"
					value="<%=(rentalListVO == null) ? "19001" : rentalListVO.getVenueSN()%>" >
			</div>

			<div class="mt-3 px-3 mb-5" align="center">
				<label for="formGroupExampleInput" class="form-label">會員編號</label> <input
					type="text"  class="form-control"
					style="width: 30%" id="formGroupExampleInput"name="userId"
					value="<%=(rentalListVO == null) ? "1001" : rentalListVO.getUserId()%>" >
			</div>

			<div class="mt-3 px-3 mb-5" align="center">
				<label for="formGroupExampleInput" class="form-label">歸還狀態</label> <input
					type="text"  class="form-control"
					style="width: 30%" id="formGroupExampleInput"name="returnStatus"
					value="<%=(rentalListVO == null) ? "" : rentalListVO.getReturnStatus()%>" >
			</div>

			<div class="mt-3 px-3 mb-5" align="center">
				<label for="formGroupExampleInput" class="form-label">租借日期</label> <input
					type="text"  class="form-control"
					style="width: 30%" id="formGroupExampleInput"name="rentalDate"
					<%--name="rentalDate" id="f_date1" type="text" >%>
					value="<%=(rentalListVO == null) ? "" : rentalListVO.getRentalDate()%>" >
			</div>

			<div class="mt-3 px-3 mb-5" align="center">
				<label for="formGroupExampleInput" class="form-label">場地評論</label> <input
					type="text"  class="form-control"
					style="width: 30%" id="formGroupExampleInput"name="venueReview"
					value="<%=(rentalListVO == null) ? "" : rentalListVO.getVenueReview()%>" >
			</div>

			<div class="mt-3 px-3 mb-5" align="center">
				<label for="formGroupExampleInput" class="form-label">租借時段</label> <input
					type="text"  class="form-control"
					style="width: 30%" id="formGroupExampleInput"name="rentalTime"
					value="<%=(rentalListVO == null) ? "" : rentalListVO.getRentalTime()%>" >
			</div> --%>

			<!--浮動標籤 -->
			<div class="container py-2">
				<div class="row justify-content-md-center">
					<div class="col col-lg-4">
						<div class="form-floating" col-lg-2>
							<input type="text" class="form-control" placeholder="text"
								id="formGroupExampleInput"
								value="${venueSN}" disabled>
							<label for="floatingInputGrid">場地編號</label>
						</div>
					</div>
				</div>
			</div>

			<div class="container py-2">
				<div class="row justify-content-md-center">
					<div class="col col-lg-4">
						<div class="form-floating" col-lg-2>
							<input type="text" class="form-control" placeholder="text"
								id="formGroupExampleInput" name="userId"
								value="<%=(rentalListVO == null) ? "" : rentalListVO.getUserId()%>">
							<label for="floatingInputGrid">會員編號</label>
						</div>
					</div>
				</div>
			</div>

			<%--			<div class="container py-2">
					<div class="row justify-content-md-center">
						<div class="col col-lg-4">
							<div class="form-floating" col-lg-2>
								<input type="text" class="form-control" placeholder="text"
									id="formGroupExampleInput" name="returnStatus"
									value="<%=(rentalListVO == null) ? "" : rentalListVO.getReturnStatus()%>">
								<label for="floatingInputGrid">歸還狀態</label>
							</div>
						</div>
					</div>
				</div>--%>

			<div class="container py-2">
				<div class="row justify-content-md-center">
					<div class="col col-lg-4">
						<div class="form-floating" col-lg-2>
							<input type="text" class="form-control date" placeholder="text"
								name="rentalDate" id="f_date1"> <label
								for="floatingInputGrid">租借日期</label>
						</div>
					</div>
				</div>
			</div>

	<%--		<div class="container py-2">
				<div class="row justify-content-md-center">
					<div class="col col-lg-4">
						<div class="form-floating" col-lg-2>
							<input type="text" class="form-control" placeholder="text"
								id="formGroupExampleInput" name="rentalTime"
								value="<%=(rentalListVO == null) ? "" : rentalListVO.getRentalTime()%>">
							<label for="floatingInputGrid">租借時段</label>
						</div>
					</div>
				</div>
			</div>--%>

			<div class="container py-2">
				<div class="row justify-content-md-center">
					<div class="col col-lg-4">
						<div class="form-floating" col-lg-2>
							<div class="form-floating">
								<select class="form-select" id="floatingSelect"
									aria-label="Floating label select example" name="rentalTime">
									<option value="100">9AM ~ 12PM</option>
									<option value="010">12PM ~ 3PM</option>
									<option value="001">3PM ~ 6PM</option>
									<option value="110">9AM ~ 12PM & 12PM ~ 3PM</option>
									<option value="101">9AM ~ 12PM & 3PM ~ 6PM</option>
									<option value="011">12PM ~ 3PM & 3PM ~ 6PM</option>
									<option value="111">9AM ~ 6PM</option>
								</select> <label for="floatingSelect">租借時段</label>
							</div>
						</div>
					</div>
				</div>
			</div>
			<%--				<div class="container py-2">
					<div class="row justify-content-md-center">
						<div class="col col-lg-4">
							<div class="form-floating" col-lg-2>
								<input type="text" class="form-control" placeholder="text"
									id="formGroupExampleInput" name="beforeUse"
									value="<%=(rentalListVO == null) ? "" : rentalListVO.getBeforeUse()%>">
								<label for="floatingInputGrid">使用前照片</label>
							</div>
						</div>
					</div>
				</div>
				
				<div class="container py-2">
					<div class="row justify-content-md-center">
						<div class="col col-lg-4">
							<div class="form-floating" col-lg-2>
								<input type="text" class="form-control" placeholder="text"
									id="formGroupExampleInput" name="afterUse"
									value="<%=(rentalListVO == null) ? "" : rentalListVO.getAfterUse()%>">
								<label for="floatingInputGrid">使用後照片</label>
							</div>
						</div>
					</div>
				</div>--%>


	<%--		<div class="container py-2">
				<div class="row justify-content-md-center">
					<div class="col col-lg-4">
						<label for="exampleFormControlTextarea1" class="form-label">場地評論</label>
						<textarea class="form-control" id="exampleFormControlTextarea1"
							rows="3" name="venueReview"><%=(rentalListVO == null) ? "" : rentalListVO.getVenueReview()%></textarea>
					</div>
				</div>
			</div> --%>


			<!--上傳-->
			<div class="container py-2">
				<div class="row justify-content-md-center">
					<div class="col col-lg-4">
						<label for="formGroupExampleInput" class="form-label">使用前照片</label>
						<input type="file" name="beforeUse" accept="image/*" id="file">
						<div id="preview">
							<img id="demo" class="image" width="300" height="200" border="0" class="img-thumbnail">
						</div>
					</div>
				</div>
			</div>



			<div class="container py-2">
				<div class="row justify-content-md-center">
					<div class="col col-lg-4">
						<label for="formGroupExampleInput" class="form-label">使用後照片</label>
						<input type="file" name="afterUse" accept="image/*" id="file1">
						<div id="preview">
							<img id="demo1" class="image" width="300" height="200" border="0" class="img-thumbnail">
						</div>
					</div>
				</div>
			</div>
			<div id="preview">
				<img id="demo1" class="image">
			</div>
			<!--上傳完-->



			<%--			<div class="col-md-4">
				<div class="container py-2">
					<div class="row justify-content-md-center">
						<div class="col col-lg-8">
							<div class="form-floating" col-lg-2>
								<input type="text" class="form-control" placeholder="text"
								id="formGroupExampleInput" name="venueSN"
								value="<%=(rentalListVO == null) ? "" : rentalListVO.getVenueSN()%>">
							<label for="floatingInputGrid">場地編號</label>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="container py-2">
					<div class="row justify-content-md-center">
						<div class="col col-lg-8">
							<div class="form-floating" col-lg-2>
								<input type="text" class="form-control" placeholder="text"
								id="formGroupExampleInput" name="userId"
								value="<%=(rentalListVO == null) ? "" : rentalListVO.getUserId()%>">
							<label for="floatingInputGrid">會員編號</label>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="col-md-4">
				<div class="container py-2">
					<div class="row justify-content-md-center">
						<div class="col col-lg-8">
							<div class="form-floating" col-lg-2>
								<input type="text" class="form-control" placeholder="text"
								id="formGroupExampleInput" name="returnStatus"
								value="<%=(rentalListVO == null) ? "" : rentalListVO.getReturnStatus()%>">
							<label for="floatingInputGrid">歸還狀態</label>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="col-md-6">
				<div class="container py-2">
					<div class="row justify-content-md-center">
						<div class="col col-lg-6">
							<div class="form-floating" col-lg-2>
								<input type="text" class="form-control" placeholder="text"
								name="rentalDate" id="f_date1"> <label
								for="floatingInputGrid">租借日期</label>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="col-md-6">
				<div class="container py-2">
					<div class="row justify-content-md-center">
						<div class="col col-lg-6">
							<div class="form-floating" col-lg-2>
								<input type="text" class="form-control" placeholder="text"
								id="formGroupExampleInput" name="rentalTime"
								value="<%=(rentalListVO == null) ? "" : rentalListVO.getRentalTime()%>">
							<label for="floatingInputGrid">租借時段</label>
							</div>
						</div>
					</div>
				</div>
			</div>


			<div class="col-md-6">
				<div class="container py-2">
					<div class="row justify-content-md-center">
						<div class="col col-lg-6">
							<div class="form-floating" col-lg-2>
								<input type="text" class="form-control"
									placeholder="Disabled input" id="formGroupExampleInput"
									name="beforeUse" value="" disabled> <img
									src="/TFA102_123/RLReader"> <label
									for="floatingInputGrid">使用前照片</label>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="container py-2">
					<div class="row justify-content-md-center">
						<div class="col col-lg-6">
							<div class="form-floating" col-lg-2>
								<input type="text" class="form-control"
									placeholder="Disabled input" id="formGroupExampleInput"
									name="beforeUse" value="" disabled> <img
									src="/TFA102_123/RLReader"> <label
									for="floatingInputGrid">使用後照片</label>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="col-md-12">
				<div class="container py-2">
					<div class="row justify-content-md-center">
						<div class="col col-lg-8">
							<label for="exampleFormControlTextarea1" class="form-label">場地評論</label>
				<textarea class="form-control" id="exampleFormControlTextarea1"
					rows="3" name="venueReview"><%=(rentalListVO == null) ? "" : rentalListVO.getVenueReview()%></textarea>
						</div>
					</div>
				</div>
			</div>--%>


		</table>

		<div align="center">
			<input type="hidden" name="action" value="insert">
			<input type="hidden" name="venueSN" value="${venueSN}"> <input
				type="submit" class="btn btn-success" value="送出新增">
		</div>
	</FORM>

	<jsp:include page="/footer.jsp" flush="true" />
</body>

<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<%
	java.sql.Timestamp rentalDate = null;
	try {
		rentalDate = rentalListVO.getRentalDate();
	} catch (Exception e) {
		rentalDate = new java.sql.Timestamp(System.currentTimeMillis());
	}
%>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       //  step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value:'<%=rentalDate%>',
	// value:   new Date(),
	//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
	//startDate:	            '2017/07/10',  // 起始日
	//minDate:               '-1970-01-01', // 去除今日(不含)之前
	//maxDate:               '+1970-01-01'  // 去除今日(不含)之後
	});

	// ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

	//     1.以下為某一天之前的日期無法選擇
	//      var somedate1 = new Date('2017-06-15');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() <  somedate1.getYear() || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});

	//      2.以下為某一天之後的日期無法選擇
	//      var somedate2 = new Date('2017-06-15');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() >  somedate2.getYear() || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});

	//      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
	//      var somedate1 = new Date('2017-06-15');
	//      var somedate2 = new Date('2017-06-25');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() <  somedate1.getYear() || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
	//		             ||
	//		            date.getYear() >  somedate2.getYear() || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});
</script>
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

<script>
	$('#file1').change(function() {
		var file = $('#file1')[0].files[0];
		var reader = new FileReader;
		reader.onload = function(e) {
			$('#demo1').attr('src', e.target.result);
		};
		reader.readAsDataURL(file);
	});
</script>
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
</html>