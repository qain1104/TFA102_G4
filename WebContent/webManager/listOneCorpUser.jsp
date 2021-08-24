<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.CorpUser.model.*"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	CorpUserVO corpUserVO = (CorpUserVO) request.getAttribute("corpUserVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
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

	<h4>此頁暫練習採用 Script 的寫法取值:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>員工資料 - listOneCorpUser.jsp</h3>
				<h4>
					<a href="select_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>企業會員編號</th>
			<th>註冊狀態</th>
			<th>帳號</th>
			<th>密碼</th>
			<th>公司名稱</th>
			<th>公司行號</th>
			<th>信箱</th>
			<th>電話</th>
			<th>地址</th>
			<th>大頭貼</th>
			<th>創建時間</th>
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