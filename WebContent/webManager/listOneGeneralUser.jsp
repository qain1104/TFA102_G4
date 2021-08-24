<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.GeneralUser.model.*"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.*"%>

<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	GeneralUserVO generalUserVO = (GeneralUserVO) request.getAttribute("generalUserVO");
	Date date = new Date();
	SimpleDateFormat myFmt1 = new SimpleDateFormat("yyyy/MM/dd HH:mm");
	date = generalUserVO.getCreatedTime();
	
%>

<html>
<head>
<title>listOneGeneralUser.jsp</title>

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
				<h3>員工資料 - listOneGeneralUser.jsp</h3>
				<h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>一般會員編號</th>
			<th>註冊狀態</th>
			<th>帳號</th>
			<th>密碼</th>
			<th>姓名</th>
			<th>身分證字號</th>
			<th>信箱</th>
			<th>地址</th>
			<th>電話</th>
			<th>大頭貼</th>
			<th>創建時間</th>
			<th>性別</th>
		</tr>
		<tr>
			<td>${generalUserVO.userId}</td>
			<td>${generalUserVO.registerStatus}</td>
			<td>${generalUserVO.userAccount}</td>
			<td>${generalUserVO.userPassword}</td>
			<td>${generalUserVO.userName}</td>
			<td>${generalUserVO.id}</td>
			<td>${generalUserVO.email}</td>
			<td>${generalUserVO.address}</td>
			<td>${generalUserVO.phone}</td>
			<td>${generalUserVO.profilePic}</td>
			<td><%=myFmt1.format(date)%></td>
			<td>${generalUserVO.gender}</td>
<%-- 		<td><%=session.getAttribute("tempAccount") %></td> --%>
		</tr>
	</table>

</body>
</html>