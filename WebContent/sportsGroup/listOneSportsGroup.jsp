<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.sportsgroup.model.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%-- ���X Concroller SportsGroupServlet.java�w�s�Jrequest��EmpVO����--%>
<%
	SportsGroupVO sportsGroupVO = (SportsGroupVO) request.getAttribute("sportsGroupVO");
%>


<%-- ���X ������DeptVO����--%>
<%-- <% --%>
<!-- // SportsGroupService sportsGroupSvc = new SportsGroupService(); -->
<!-- //   DeptVO deptVO = deptSvc.getOneDept(empVO.getDeptno()); -->
<%-- %> --%>

<html>
<head>

<title>listOneSportsGroup.jsp</title>
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
	width:900px;
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
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>���u��� - ListOneEmp.jsp</h3>
				<h4>
					<a href="select_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">�^����</a>
				</h4>
			</td>
		</tr>
	</table>
	<table>
		<tr>
			<th>���νs��</th>
			<th>�o�_��</th>
			<th>�B������</th>
			<th>���a��T</th>
			<th>�B�ʮɶ�</th>
			<th>�H�ƤW��</th>
			<th>�H�ƤU��</th>
			<th>���W�ɶ�</th>
			<th>���W�I��ɶ�</th>
			<th>�o�_�ɶ�</th>
			<th>�ѥ[�H��</th>
			<th>�O�_�y��</th>
			<th>�Ƶ�</th>
		</tr>
		<tr>
			<td>${sportsGroupVO.sportsGroupSN}</td>
				<td>${sportsGroupVO.userId}</td>
				<td>${sportsGroupVO.sportsType}</td>
				<td>${sportsGroupVO.sportsLocation}</td>
				<td>${sportsGroupVO.exerciseTime}</td>
				<td>${sportsGroupVO.numberUpLimit}</td>
				<td>${sportsGroupVO.numberLowLimit}</td>
				<td>${sportsGroupVO.registTime}</td>
				<td>${sportsGroupVO.registTimeEnd}</td>
				<td>${sportsGroupVO.issueDATE}</td>
				<td>${sportsGroupVO.participantNumber}</td>
				<td>${sportsGroupVO.success}</td>
				<td>${sportsGroupVO.remarks}</td>
		</tr>
		</table>

</body>
</html>