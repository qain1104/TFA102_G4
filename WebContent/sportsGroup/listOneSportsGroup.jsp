<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.sportsgroup.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%-- 取出 Concroller SportsGroupServlet.java已存入request的EmpVO物件--%>
<%
	SportsGroupVO sportsGroupVO = (SportsGroupVO) request.getAttribute("sportsGroupVO");
%>


<%-- 取出 對應的DeptVO物件--%>
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
				<h3>員工資料 - ListOneEmp.jsp</h3>
				<h4>
					<a href="select_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>
	<table>
		<tr>
			<th>揪團編號</th>
			<th>發起者</th>
			<th>運動類型</th>
			<th>場地資訊</th>
			<th>運動時間</th>
			<th>人數上限</th>
			<th>人數下限</th>
			<th>報名時間</th>
			<th>報名截止時間</th>
			<th>發起時間</th>
			<th>參加人數</th>
			<th>是否流團</th>
			<th>備註</th>
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