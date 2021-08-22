<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.sportsgroup.model.*"%>

<%
	SportsGroupVO sportsGroupVO = (SportsGroupVO) request.getAttribute("sportsGroupVO");
	Integer userId = 1001;
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>改版本!!!</title>
<style>
table#table-1 {
	background-color: #CCCCFF;
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
</style>
link rel="apple-touch-icon" href="../assets/img/apple-icon.png">
<link rel="shortcut icon" type="image/x-icon"
	href="<%=request.getContextPath()%>/assets/img/favicon.ico">

<!-- 介面排版用的 -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/bootstrap.min.css">
<!-- 不知道的東西 -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/templatemo.css">
<!-- <link rel="stylesheet" href="assets/css/custom.css"> -->

<!-- Load fonts style after rendering the layout styles -->
<!-- 設定字型 -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;200;300;400;500;700;900&display=swap">
<!-- 匯入圖片 -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/fontawesome.min.css">
<!-- 	時間選擇器的CSS -->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker\daterangepicker.css" />
</head>
</head>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color: red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color: red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<FORM METHOD="post" ACTION="sportsGroup.do" name="form1">
	<table>
		<!-- 		<tr> -->
		<!-- 			<td>發起者userId:</td> -->
		<!-- 			<td><input type="TEXT" name="userId" size="45" -->
		<%-- 				value="<%=(sportsGroupVO == null) ? "1006" : sportsGroupVO.getUserId()%>" /></td> --%>
		<!-- 		</tr> -->
		<!-- 		<tr> -->
		<!-- 			<td>運動類型sportsType:</td> -->
		<!-- 			<td><input type="TEXT" name="sportsType" size="45" -->
		<%-- 				value="<%=(sportsGroupVO == null) ? "籃球測試" : sportsGroupVO.getSportsType()%>" /></td> --%>
		<!-- 		</tr> -->
		<!-- 		<tr> -->
		<!-- 			<td>地點sportsLocation:</td> -->
		<!-- 			<td><input type="TEXT" name="sportsLocation" size="45" -->
		<%-- 				value="<%=(sportsGroupVO == null) ? "測試" : sportsGroupVO.getSportsLocation()%>" /></td> --%>
		<!-- 		</tr> -->
		<!-- 		<tr> -->
		<!-- 			<td>舉辦活動時間exerciseTime:</td> -->
		<!-- 			<td><input type="TEXT" name="exerciseTime" size="45" -->
		<%-- 				value="<%=(sportsGroupVO == null) ? "2021-7-18 9:00:00" : sportsGroupVO.getExerciseTime()%>" /></td> --%>
		<!-- 		</tr> -->
		<!-- 		<tr> -->
		<!-- 			<td>人數上限numberUpLimit:</td> -->
		<!-- 			<td><input type="TEXT" name="numberUpLimit" size="45" -->
		<%-- 				value="<%=(sportsGroupVO == null) ? "4" : sportsGroupVO.getNumberUpLimit()%>" /></td> --%>
		<!-- 		</tr> -->
		<!-- 		<tr> -->
		<!-- 			<td>人數下限numberLowLimit:</td> -->
		<!-- 			<td><input type="TEXT" name="numberLowLimit" size="45" -->
		<%-- 				value="<%=(sportsGroupVO == null) ? "3" : sportsGroupVO.getNumberLowLimit()%>" /></td> --%>
		<!-- 		</tr> -->
		<!-- 		<tr> -->
		<!-- 			<td>報名期限registTime (end):</td> -->

		<!-- 			<td><input type="TEXT" name="registTimeAndEnd" size="45" -->
		<%-- 				value="<%=(sportsGroupVO == null) ? "2021-7-18 9:00:00" : sportsGroupVO.getRegistTime()%>" /></td> --%>
		<%-- 			<%-- 					<input type="hidden" name="registTime" value="<%=start.format('YYYY-MM-DD hh:mm:ss')%>"> --%>
		--%>
		<%-- 			<%-- 					<input type="hidden" name="registTimeEnd" value="<%=end.format('YYYY-MM-DD hh:mm:ss')%>"> --%>
		--%>
		<!-- 		</tr> -->

		<!-- 		<tr> -->
		<!-- 			<td>參加人數participantNumber :</td> -->
		<!-- 			<td><input type="TEXT" name="participantNumber" size="45" -->
		<%-- 				value="<%=(sportsGroupVO == null) ? "0" : sportsGroupVO.getParticipantNumber()%>" /></td> --%>
		<!-- 		</tr> -->
		<!-- 		<tr> -->
		<!-- 			<td>是否流團success :</td> -->
		<!-- 			<td><input type="TEXT" name="success" size="45" -->
		<%-- 				value="<%=(sportsGroupVO == null) ? "0" : sportsGroupVO.getSuccess()%>" /></td> --%>
		<!-- 		</tr> -->
		<!-- 		<tr> -->
		<!-- 			<td>備註remarks :</td> -->
		<!-- 			<td><input type="TEXT" name="remarks" size="45" -->
		<%-- 				value="<%=(sportsGroupVO == null) ? "測試測試" : sportsGroupVO.getRemarks()%>" /></td> --%>
		<!-- 		</tr> -->
	</table>
	<br> <input type="hidden" name="action" value="insert"> <input
		type="submit" value="送出新增">
</FORM>


<div class="modal-dialog">
	<div class="modal-content">
		<!-- 彈出內容開始 -->
		<form METHOD="post" ACTION="sportsGroup.do" name="form1">
			<div class="modal-body">
				<div class="container-fluid">
					<!-- 彈出內容 form表單開始-->
					<div class="form-group">
						<label for="userid">揪團人</label> <input type="text"
							class="form-control" name="userId" value="<%=userId%>" readonly>
					</div>
					<div class="form-group">
						<div class="row">
							<div class="col">
								<label for="sportsType"><h6>活動類型</h6></label> <input type="TEXT"
									class="form-control" name="sportsType" placeholder="輸入想發起的活動">
							</div>
							<div class="col">
								<label for="sportsLocation"><h6>活動地點</h6></label> <input
									type="TEXT" class="form-control" name="sportsLocation"
									placeholder="輸入地點">
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="exerciseTime">活動時間</label> <input type="text"
							name="exerciseTime" value="exerciseTime" class="form-control"
							id="exerciseTime">
					</div>
					<div class="form-group row">
						<label for="numberLowLimit">開團人數</label> <input type="text"
							class="form-control col" name="numberLowLimit" placeholder="最低">
						<input type="text" class="form-control col" name="numberUpLimit"
							placeholder="最多">
					</div>
					<div class="form-group">
						<label for="remarks">備註</label> <input type="text"
							class="form-control" name="remarks" placeholder="備註">
					</div>
					<div class="form-group">
						<label for="registTimeAndEnd">報名期限</label><input type="text"
							name="registTimeAndEnd" value="registTimeAndEnd"
							class="form-control">
					</div>
				</div>
			</div>
			<!-- 彈出內容結束 -->
			<div class="modal-footer">

				<button type="button" class="btn btn-success"
					data-bs-dismiss="modal">關閉</button>
				<input type="hidden" name="participantNumber" size="45" value="0" />
				<input type="hidden" name="action" value="insert">

				<button type="submit" class="btn btn-success">發起</button>
			</div>
		</form>
	</div>
</div>
<!-- 彈出來的MODAL畫面結束 -->
</div>
<!-- 整個談窗+按鈕測試結束 -->
</div>
</div>
</div>
<!-- Start Script -->
<script
	src="<%=request.getContextPath()%>/assets/js/jquery-3.6.0.min.js"></script>
<script
	src="<%=request.getContextPath()%>/assets/js/jquery-migrate-1.2.1.min.js"></script>
<script
	src="<%=request.getContextPath()%>/assets/js/bootstrap.bundle.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/templatemo.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/custom.js"></script>
<!-- End Script -->
<!-- Datetimepicker開始 -->
<script type="text/javascript"
	src="<%=request.getContextPath()%>/datetimepicker\jquery.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/datetimepicker\moment.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/datetimepicker\daterangepicker.min.js"></script>
<script
	src="<%=request.getContextPath()%>/datetimepicker\mydatetimepicker.js"></script>
<!-- Datetimepicker結束 -->
<jsp:include page="/footer.jsp" flush="true" />
</body>
</html>
1
