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
<title>�睊��!!!</title>
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

<!-- �����ƪ��Ϊ� -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/bootstrap.min.css">
<!-- �����D���F�� -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/templatemo.css">
<!-- <link rel="stylesheet" href="assets/css/custom.css"> -->

<!-- Load fonts style after rendering the layout styles -->
<!-- �]�w�r�� -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;200;300;400;500;700;900&display=swap">
<!-- �פJ�Ϥ� -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/fontawesome.min.css">
<!-- 	�ɶ���ܾ���CSS -->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker\daterangepicker.css" />
</head>
</head>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color: red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color: red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<FORM METHOD="post" ACTION="sportsGroup.do" name="form1">
	<table>
		<!-- 		<tr> -->
		<!-- 			<td>�o�_��userId:</td> -->
		<!-- 			<td><input type="TEXT" name="userId" size="45" -->
		<%-- 				value="<%=(sportsGroupVO == null) ? "1006" : sportsGroupVO.getUserId()%>" /></td> --%>
		<!-- 		</tr> -->
		<!-- 		<tr> -->
		<!-- 			<td>�B������sportsType:</td> -->
		<!-- 			<td><input type="TEXT" name="sportsType" size="45" -->
		<%-- 				value="<%=(sportsGroupVO == null) ? "�x�y����" : sportsGroupVO.getSportsType()%>" /></td> --%>
		<!-- 		</tr> -->
		<!-- 		<tr> -->
		<!-- 			<td>�a�IsportsLocation:</td> -->
		<!-- 			<td><input type="TEXT" name="sportsLocation" size="45" -->
		<%-- 				value="<%=(sportsGroupVO == null) ? "����" : sportsGroupVO.getSportsLocation()%>" /></td> --%>
		<!-- 		</tr> -->
		<!-- 		<tr> -->
		<!-- 			<td>�|�쬡�ʮɶ�exerciseTime:</td> -->
		<!-- 			<td><input type="TEXT" name="exerciseTime" size="45" -->
		<%-- 				value="<%=(sportsGroupVO == null) ? "2021-7-18 9:00:00" : sportsGroupVO.getExerciseTime()%>" /></td> --%>
		<!-- 		</tr> -->
		<!-- 		<tr> -->
		<!-- 			<td>�H�ƤW��numberUpLimit:</td> -->
		<!-- 			<td><input type="TEXT" name="numberUpLimit" size="45" -->
		<%-- 				value="<%=(sportsGroupVO == null) ? "4" : sportsGroupVO.getNumberUpLimit()%>" /></td> --%>
		<!-- 		</tr> -->
		<!-- 		<tr> -->
		<!-- 			<td>�H�ƤU��numberLowLimit:</td> -->
		<!-- 			<td><input type="TEXT" name="numberLowLimit" size="45" -->
		<%-- 				value="<%=(sportsGroupVO == null) ? "3" : sportsGroupVO.getNumberLowLimit()%>" /></td> --%>
		<!-- 		</tr> -->
		<!-- 		<tr> -->
		<!-- 			<td>���W����registTime (end):</td> -->

		<!-- 			<td><input type="TEXT" name="registTimeAndEnd" size="45" -->
		<%-- 				value="<%=(sportsGroupVO == null) ? "2021-7-18 9:00:00" : sportsGroupVO.getRegistTime()%>" /></td> --%>
		<%-- 			<%-- 					<input type="hidden" name="registTime" value="<%=start.format('YYYY-MM-DD hh:mm:ss')%>"> --%>
		--%>
		<%-- 			<%-- 					<input type="hidden" name="registTimeEnd" value="<%=end.format('YYYY-MM-DD hh:mm:ss')%>"> --%>
		--%>
		<!-- 		</tr> -->

		<!-- 		<tr> -->
		<!-- 			<td>�ѥ[�H��participantNumber :</td> -->
		<!-- 			<td><input type="TEXT" name="participantNumber" size="45" -->
		<%-- 				value="<%=(sportsGroupVO == null) ? "0" : sportsGroupVO.getParticipantNumber()%>" /></td> --%>
		<!-- 		</tr> -->
		<!-- 		<tr> -->
		<!-- 			<td>�O�_�y��success :</td> -->
		<!-- 			<td><input type="TEXT" name="success" size="45" -->
		<%-- 				value="<%=(sportsGroupVO == null) ? "0" : sportsGroupVO.getSuccess()%>" /></td> --%>
		<!-- 		</tr> -->
		<!-- 		<tr> -->
		<!-- 			<td>�Ƶ�remarks :</td> -->
		<!-- 			<td><input type="TEXT" name="remarks" size="45" -->
		<%-- 				value="<%=(sportsGroupVO == null) ? "���մ���" : sportsGroupVO.getRemarks()%>" /></td> --%>
		<!-- 		</tr> -->
	</table>
	<br> <input type="hidden" name="action" value="insert"> <input
		type="submit" value="�e�X�s�W">
</FORM>


<div class="modal-dialog">
	<div class="modal-content">
		<!-- �u�X���e�}�l -->
		<form METHOD="post" ACTION="sportsGroup.do" name="form1">
			<div class="modal-body">
				<div class="container-fluid">
					<!-- �u�X���e form���}�l-->
					<div class="form-group">
						<label for="userid">���ΤH</label> <input type="text"
							class="form-control" name="userId" value="<%=userId%>" readonly>
					</div>
					<div class="form-group">
						<div class="row">
							<div class="col">
								<label for="sportsType"><h6>��������</h6></label> <input type="TEXT"
									class="form-control" name="sportsType" placeholder="��J�Q�o�_������">
							</div>
							<div class="col">
								<label for="sportsLocation"><h6>���ʦa�I</h6></label> <input
									type="TEXT" class="form-control" name="sportsLocation"
									placeholder="��J�a�I">
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="exerciseTime">���ʮɶ�</label> <input type="text"
							name="exerciseTime" value="exerciseTime" class="form-control"
							id="exerciseTime">
					</div>
					<div class="form-group row">
						<label for="numberLowLimit">�}�ΤH��</label> <input type="text"
							class="form-control col" name="numberLowLimit" placeholder="�̧C">
						<input type="text" class="form-control col" name="numberUpLimit"
							placeholder="�̦h">
					</div>
					<div class="form-group">
						<label for="remarks">�Ƶ�</label> <input type="text"
							class="form-control" name="remarks" placeholder="�Ƶ�">
					</div>
					<div class="form-group">
						<label for="registTimeAndEnd">���W����</label><input type="text"
							name="registTimeAndEnd" value="registTimeAndEnd"
							class="form-control">
					</div>
				</div>
			</div>
			<!-- �u�X���e���� -->
			<div class="modal-footer">

				<button type="button" class="btn btn-success"
					data-bs-dismiss="modal">����</button>
				<input type="hidden" name="participantNumber" size="45" value="0" />
				<input type="hidden" name="action" value="insert">

				<button type="submit" class="btn btn-success">�o�_</button>
			</div>
		</form>
	</div>
</div>
<!-- �u�X�Ӫ�MODAL�e������ -->
</div>
<!-- ��ӽ͵�+���s���յ��� -->
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
<!-- Datetimepicker�}�l -->
<script type="text/javascript"
	src="<%=request.getContextPath()%>/datetimepicker\jquery.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/datetimepicker\moment.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/datetimepicker\daterangepicker.min.js"></script>
<script
	src="<%=request.getContextPath()%>/datetimepicker\mydatetimepicker.js"></script>
<!-- Datetimepicker���� -->
<jsp:include page="/footer.jsp" flush="true" />
</body>
</html>
1
