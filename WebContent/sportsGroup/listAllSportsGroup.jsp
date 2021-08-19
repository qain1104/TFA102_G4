<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.sportsgroup.model.*"%>

<%
	SportsGroupService sportsGroupSvc = new SportsGroupService();
	List<SportsGroupVO> list = sportsGroupSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<html>
<head>
<meta charset="BIG5">
<title>所有揪團資料 - listAllSportsGroup.jsp</title>
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
	width: 1000px;
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
	<h4>此頁練習採用 EL 的寫法取值:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>所有揪團資料</h3>
				<h4>
					<a href="<%=request.getContextPath()%>/sportsGroup/select_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>
	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

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
		<%@ include file="pages/page1.file"%>
		<c:forEach var="sportsGroupVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">
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

				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/sportsGroup/sportsGroup.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="修改"> <input type="hidden"
							name="sportsGroupSN" value="${sportsGroupVO.sportsGroupSN}">
						<input type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/sportsGroup/sportsGroup.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="刪除"> <input type="hidden"
							name="sportsGroupSN" value="${sportsGroupVO.sportsGroupSN}">
						<input type="hidden" name="action" value="delete">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
	<%@ include file="pages/page2.file"%>
</body>
</html>