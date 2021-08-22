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
<title>���u��Ʒs�W - addSportsGroup.jsp</title>
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
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker\daterangepicker.css" />
</head>
<body bgcolor='white'>
	<table id="table-1">
		<tr>
			<td>
				<h3>���ηs�W addSportsGroup.jsp"<%=userId%>"</h3>
			</td>
			<td>
				<h4>
					<a href="<%=request.getContextPath()%>/sportsGroup/select_page.jsp"><img
						src="images/tomcat.png" width="100" height="100" border="0">�^����</a>
				</h4>
			</td>
		</tr>
	</table>
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
			<tr>
				<td>�o�_��userId:</td>
				<td><input type="TEXT" name="userId" size="45"
					value="<%=(sportsGroupVO == null) ? "1006" : sportsGroupVO.getUserId()%>" /></td>
			</tr>
			<tr>
				<td>�B������sportsType:</td>
				<td><input type="TEXT" name="sportsType" size="45"
					value="<%=(sportsGroupVO == null) ? "�x�y����" : sportsGroupVO.getSportsType()%>" /></td>
			</tr>
			<tr>
				<td>�a�IsportsLocation:</td>
				<td><input type="TEXT" name="sportsLocation" size="45"
					value="<%=(sportsGroupVO == null) ? "����" : sportsGroupVO.getSportsLocation()%>" /></td>
			</tr>
			<tr>
				<td>�|�쬡�ʮɶ�exerciseTime:</td>
				<td><input type="TEXT" name="exerciseTime" size="45"
					value="<%=(sportsGroupVO == null) ? "2021-7-18 9:00:00" : sportsGroupVO.getExerciseTime()%>" /></td>
			</tr>
			<tr>
				<td>�H�ƤW��numberUpLimit:</td>
				<td><input type="TEXT" name="numberUpLimit" size="45"
					value="<%=(sportsGroupVO == null) ? "4" : sportsGroupVO.getNumberUpLimit()%>" /></td>
			</tr>
			<tr>
				<td>�H�ƤU��numberLowLimit:</td>
				<td><input type="TEXT" name="numberLowLimit" size="45"
					value="<%=(sportsGroupVO == null) ? "3" : sportsGroupVO.getNumberLowLimit()%>" /></td>
			</tr>
			<tr>
				<td>���W����registTime (end):</td>

				<td><input type="TEXT" name="registTimeAndEnd" size="45"
					value="<%=(sportsGroupVO == null) ? "2021-7-18 9:00:00" : sportsGroupVO.getRegistTime()%>" /></td>
				<%-- 					<input type="hidden" name="registTime" value="<%=start.format('YYYY-MM-DD hh:mm:ss')%>"> --%>
				<%-- 					<input type="hidden" name="registTimeEnd" value="<%=end.format('YYYY-MM-DD hh:mm:ss')%>"> --%>
			</tr>
			<tr>
				<td>�o�_�ɶ�issueDATE:</td>
				<td><input type="hidden" name="issueDATE" size="45"
					value="<%=(sportsGroupVO == null) ? "2021-7-18 9:00:00" : sportsGroupVO.getIssueDATE()%>" /></td>
			</tr>
			<tr>
				<td>�ѥ[�H��participantNumber :</td>
				<td><input type="TEXT" name="participantNumber" size="45"
					value="<%=(sportsGroupVO == null) ? "0" : sportsGroupVO.getParticipantNumber()%>" /></td>
			</tr>
			<tr>
				<td>�O�_�y��success :</td>
				<td><input type="TEXT" name="success" size="45"
					value="<%=(sportsGroupVO == null) ? "0" : sportsGroupVO.getSuccess()%>" /></td>
			</tr>
			<tr>
				<td>�Ƶ�remarks :</td>
				<td><input type="TEXT" name="remarks" size="45"
					value="<%=(sportsGroupVO == null) ? "���մ���" : sportsGroupVO.getRemarks()%>" /></td>
			</tr>
		</table>
		<br> <input type="hidden" name="action" value="insert"> 
		<input type="submit" value="�e�X�s�W">
	</FORM>
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
</body>
</html>