<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.WebManager.model.*"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
	WebManagerVO webManagerVO = (WebManagerVO) request.getAttribute("webManagerVO");
%>

<html>
<head>
<title>listOneManager.jsp</title>

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
	width: 600px;
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

	<h4>�����Ƚm�߱ĥ� Script ���g�k����:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>�޲z����� - listOneManager.jsp</h3>
				<h4>
					<a href="managers_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">�^����</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>�޲z���s��</th>
			<th>�޲z�����A</th>
			<th>�޲z���W��</th>
			<th>�޲z��email</th>
			<th>�޲z���b��</th>
			<th>�޲z���K�X</th>
			<th>�޲z���j�Y�K</th>
		</tr>
		<tr>
			<td>${webManagerVO.managerId}</td>
			<td>${webManagerVO.managerStatus}</td>
			<td>${webManagerVO.managerName}</td>
			<td>${webManagerVO.managerEmail}</td>
			<td>${webManagerVO.managerAccount}</td>
			<td>${webManagerVO.managerPassword}</td>
			<td><img
				src="<%=request.getContextPath()%>/Readerpic?managerId=${webManagerVO.managerId}"></td>
		</tr>
	</table>

</body>
</html>