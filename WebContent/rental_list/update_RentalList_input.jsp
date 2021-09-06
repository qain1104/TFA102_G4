<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.rental_list.model.*"%>

<%
	RentalListVO rentalListVO = (RentalListVO) request.getAttribute("rentalListVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
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

      #preview{
        border: 1px solid lightgray;
        display: inline-block;
        width: 100px;
        min-height: 150px;
        position: relative;
     }
     #preview span.text{
        position: absolute;
        display: inline-block;
        left: 50%;
        top: 50%;
        transform: translate(-50%, -50%);
        z-index: -1;
        color: lightgray;
      }
      #preview img.preview_img{
        width: 100%;
      }
</style>

</head>
<body>
	<jsp:include page="/header.jsp" flush="true" />

	<table id="table01" align="center">
		<tr>
			<td>
				<h3>
					<b>租借單資料修改</b>
				</h3>
				<button type="button" class="btn btn-success btn-lg "
					onclick="location.href='<%=request.getContextPath()%>/rental_list/select_page.jsp'">租借單查詢</button>
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
			<%--	<tr>
		<td>租借單編號</td>
		<td><%=rentalListVO.getRentalListSN()%></td>
	</tr>
	<tr>
		<td>場地編號:</td>
		<td><input type="TEXT" name="venueSN" size="45" value="<%=rentalListVO.getVenueSN()%>" /></td>
	</tr>
	<tr>
		<td>會員編號:</td>
		<td><input type="TEXT" name="userId" size="45"	value="<%=rentalListVO.getUserId()%>" /></td>
	</tr>
	<tr>
		<td>歸還狀態:</td>
		<td><input type="TEXT" name="returnStatus" size="45"	value="<%=rentalListVO.getReturnStatus()%>" /></td>
	</tr>
	<tr>
		<td>租借日期:</td>
		<td><input type="TEXT" name="rentalDate" size="45"  value="<%=rentalListVO.getRentalDate()%>"></td>
	</tr>
	<tr>
		<td>場地評論:</td>
		<td><input type="TEXT" name="rentalTime" size="45"	value="<%=rentalListVO.getVenueReview()%>" /></td>
	</tr>
	<tr>
		<td>租借時段:</td>
		<td><input type="TEXT" name="rentalTime" size="45"	value="<%=rentalListVO.getRentalTime()%>" /></td>
	</tr>--%>





			<!--浮動標籤 -->

			<div class="container py-2">
				<div class="row justify-content-md-center">
					<div class="col col-lg-4">
						<div class="form-floating" col-lg-2>
							<input type="text" class="form-control"
								placeholder="Disabled input" id="formGroupExampleInput"
								name="rentalListSN" value="<%=rentalListVO.getRentalListSN()%>"
								disabled> <label for="floatingInputGrid"><b>租借單編號</b></label>
							
						</div>
					</div>
				</div>
			</div>

			<div class="container py-2">
				<div class="row justify-content-md-center">
					<div class="col col-lg-4">
						<div class="form-floating" col-lg-2>
							<input type="text" class="form-control" placeholder="text"
								id="formGroupExampleInput" name="venueSN"
								value="<%=rentalListVO.getVenueSN()%>"> <label
								for="floatingInputGrid">場地編號</label>
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
								value="<%=rentalListVO.getUserId()%>"> <label
								for="floatingInputGrid">會員編號</label>
						</div>
					</div>
				</div>
			</div>

<%-- <%--			<div class="container py-2">
				<div class="row justify-content-md-center">
					<div class="col col-lg-4">
						<div class="form-floating" col-lg-2>
							<input type="text" class="form-control" placeholder="text"
								id="formGroupExampleInput" name="returnStatus"
								value="<%=rentalListVO.getReturnStatus()%>"> <label
								for="floatingInputGrid">歸還狀態</label>
						</div>
					</div>
				</div>
			</div>--%>

			<div class="container py-2">
				<div class="row justify-content-md-center">
					<div class="col col-lg-4">
						<div class="form-floating" col-lg-2>
							<%-- <input type="text" class="form-control" placeholder="text"
								 id="formGroupExampleInput" name="rentalDate"
					value="<%=rentalListVO.getRentalDate()%>"> --%>
							<input type="text" class="form-control date" placeholder="text"
								name="rentalDate" id="f_date1" type="text"> <label
								for="floatingInputGrid">租借日期</label>
						</div>
					</div>
				</div>
			</div>

 			<div class="container py-2">
				<div class="row justify-content-md-center">
					<div class="col col-lg-4">
						<div class="form-floating" col-lg-2>
						<select class="form-select" id="floatingSelect"
									aria-label="Floating label select example" name="rentalTime">
									<option value="100">9AM ~ 12PM</option>
									<option value="010">12PM ~ 3PM</option>
									<option value="001">3PM ~ 6PM</option>
									<option value="110">9AM ~ 12PM & 12PM ~ 3PM</option>
									<option value="101">9AM ~ 12PM & 3PM ~ 6PM</option>
									<option value="011">12PM ~ 3PM & 3PM ~ 6PM</option>
									<option value="111">9AM ~ 6PM</option>
								</select><label for="floatingInputGrid">租借時段</label>
					<%-- 
							<input type="text" class="form-control" placeholder="text"
								id="formGroupExampleInput" name="rentalTime"
								value="<%=rentalListVO.getRentalTime()%>">--%>
						</div>
					</div>
				</div>
			</div>
<%-- 
	<div class="container py-2">
				<div class="row justify-content-md-center">
					<div class="col col-lg-4">
						<div class="form-floating" col-lg-2>
							<div class="form-floating">
								<select class="form-select" id="floatingSelect"
									aria-label="Floating label select example" name="rentalTime">
									<option value="<%=rentalListVO.getRentalTime()%>=100">9AM ~ 12PM</option>
									<option value="<%=rentalListVO.getRentalTime()%>=010">12PM ~ 3PM</option>
									<option value="<%=rentalListVO.getRentalTime()%>=001">3PM ~ 6PM</option>
									<option value="<%=rentalListVO.getRentalTime()%>=110">9AM ~ 12PM & 12PM ~ 3PM</option>
									<option value="<%=rentalListVO.getRentalTime()%>=101">9AM ~ 12PM & 3PM ~ 6PM</option>
									<option value="<%=rentalListVO.getRentalTime()%>=011">12PM ~ 3PM & 3PM ~ 6PM</option>
									<option value="<%=rentalListVO.getRentalTime()%>=111">9AM ~ 6PM</option>
								</select> <label for="floatingSelect">租借時段</label>
							</div>
						</div>
					</div>
				</div>
			</div>--%>
			
<%-- 		<div class="container py-2">
				<div class="row justify-content-md-center">
					<div class="col col-lg-4">
						<div class="form-floating" col-lg-2>
							<input type="text" class="form-control" placeholder="text"
								id="formGroupExampleInput" name="beforeUse"
								value="<%=rentalListVO.getBeforeUse()%>"> <label
								for="floatingInputGrid">使用前照片</label>
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
								value="<%=rentalListVO.getAfterUse()%>"> <label
								for="floatingInputGrid">使用後照片</label>
						</div>
					</div>
				</div>
			</div>--%>
			
	<%--					<div class="container py-2">
				<div class="row justify-content-md-center">
					<div class="col col-lg-4">
						<div class="form-floating" col-lg-2>
							<input type="file" name="afterUse" accept="image/*,.pdf" value="<%=rentalListVO.getAfterUse()%>"/></td> 
 							<label for="floatingInputGrid">使用後</label>
						</div>
					</div>
				</div>
			</div>
	<td><input type="file" name="afterUse" accept="image/*,.pdf" value="<%=rentalListVO.getAfterUse()%>"/></td>--%>


      
      
<%-- 			<div class="container py-2">
				<div class="row justify-content-md-center">
					<div class="col col-lg-4">
						<div class="form-floating" col-lg-2>
							<input type="text" class="form-control" placeholder="text"
								id="formGroupExampleInput" name="venueReview"
								value="<%=rentalListVO.getVenueReview()%>"> <label
								for="floatingInputGrid">場地評論</label>
						</div>
					</div>
				</div>
			</div>--%>

<!--上傳-->
			<div class="container py-2">
				<div class="row justify-content-md-center">
					<div class="col col-lg-4">
						<label for="formGroupExampleInput" class="form-label">使用前照片</label>
						<input type="file" name="beforeUse" accept="image/*" id="file">
						<div id="preview">
							<img id="demo" class="image"
								src="<%=request.getContextPath()%>/BFReader2?rentalListSN=<%=rentalListVO.getRentalListSN()%>" width="300" height="200" border="0">
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
							<img id="demo1" class="image"
								src="<%=request.getContextPath()%>/AFReader2?rentalListSN=<%=rentalListVO.getRentalListSN()%>" width="300" height="200" border="0">
						</div>
					</div>
				</div>
			</div>

			<!--上傳完-->

	<div class="container py-2">
		<div class="row justify-content-md-center">
			<div class="col col-lg-4">
				<label for="exampleFormControlTextarea1" class="form-label">場地評論</label>
				<textarea class="form-control" id="exampleFormControlTextarea1"
					rows="3" name="venueReview"><%=rentalListVO.getVenueReview()%></textarea>
			</div>
		</div>
	</div>

			<br>
			<div align="center">
				<input type="hidden" name="action" value="update"> <input
					type="hidden" name="rentalListSN"
					value="<%=rentalListVO.getRentalListSN()%>"> <input
					type="hidden" name="returnStatus"
					value="<%=rentalListVO.getReturnStatus()%>"> <input
					type="submit" class="btn btn-success" value="送出修改">
			</div>
		</table>
		
	</FORM>
	<jsp:include page="/footer.jsp" flush="true" />
</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>
<script src="<%=request.getContextPath() %>/assets/js/time.js"></script>
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
 	      // step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
 		   value: '<%=rentalListVO.getRentalDate()%>', 
 		   // value:   new Date(),
	//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
	//startDate:	            '2017/07/10',  // 起始日
	//minDate:               '-1970-01-01', // 去除今日(不含)之前
	//maxDate:               '+1970-01-01'  // 去除今日(不含)之後
	});

	// ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

	//      1.以下為某一天之前的日期無法選擇
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