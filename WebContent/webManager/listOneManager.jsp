<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.WebManager.model.*"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

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

	<h4>此頁暫練習採用 Script 的寫法取值:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>管理員資料 - listOneManager.jsp</h3>
				<h4>
					<a href="managers_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>管理員編號</th>
			<th>管理員狀態</th>
			<th>管理員名稱</th>
			<th>管理員email</th>
			<th>管理員帳號</th>
			<th>管理員密碼</th>
			<th>管理員大頭貼</th>
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