<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.CorpUser.model.*"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
	CorpUserVO corpUserVO = (CorpUserVO) request.getAttribute("corpUserVO"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
	Date date = new Date();
	SimpleDateFormat myFmt1 = new SimpleDateFormat("yyyy/MM/dd HH:mm");
	date = corpUserVO.getCreatedTime();

%>

<html>
<head>
<title> listOneCorpUser.jsp</title>

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
				<h3>���u��� - listOneCorpUser.jsp</h3>
				<h4>
					<a href="select_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">�^����</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>���~�|���s��</th>
			<th>���U���A</th>
			<th>�b��</th>
			<th>�K�X</th>
			<th>���q�W��</th>
			<th>���q�渹</th>
			<th>�H�c</th>
			<th>�q��</th>
			<th>�a�}</th>
			<th>�j�Y�K</th>
			<th>�Ыخɶ�</th>
		</tr>
		<tr>
				<td>${corpUserVO.corpUserId}</td>
				<td>${corpUserVO.registerStatus}</td>
				<td>${corpUserVO.corpAccount}</td>
				<td>${corpUserVO.corpPassword}</td>
				<td>${corpUserVO.companyName}</td>
				<td>${corpUserVO.ltdNo}</td>
				<td>${corpUserVO.email}</td>
				<td>${corpUserVO.phone}</td>
				<td>${corpUserVO.address}</td>
				<td>${corpUserVO.profilePic}</td>
				<td><%=myFmt1.format(date)%></td>
		</tr>
	</table>

</body>
</html>