<%@page import="java.util.List"%>
<%@page import="com.sportsgroup.model.SportsGroupVO"%>
<%@page import="com.participant.model.ParticipantVO"%>
<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="BIG5">
<title>揪團網頁測試</title>
<style>
table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
	border: 3px ridge Gray;
	height: 80px;
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
</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td><h3>IBM Emp: Home</h3>
				<h4>( MVC )</h4></td>
		</tr>
	</table>

	<p>This is the Home page for IBM Emp: Home</p>

	<h3>資料查詢:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<ul>
		<!--   <li><a href='listAllSportsGroup.jsp'>List</a> all Sportsgroup.  <br><br></li> -->
		<li><a
			href='<%=request.getContextPath()%>/sportsGroup/listAllSportsGroup.jsp'>List</a>
			all Sportsgroup. <br>
		<br></li>


		<li>
			<FORM METHOD="post" ACTION="sportsGroup.do">
				<b>輸入揪團編號編號 (如7001):</b> <input type="text" name="sportsGroupSN">
				<input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</li>

		<jsp:useBean id="sportsGroupSvc" scope="page"
			class="com.sportsgroup.model.SportsGroupService" />
		<jsp:useBean id="participantSvc" scope="page"
			class="com.participant.model.ParticipantService" />

		<%
			SportsGroupVO vo = sportsGroupSvc.getOneSportsGroup(9002);
			List<SportsGroupVO> list = sportsGroupSvc.getAll();
			session.setAttribute("list", list);
			List<ParticipantVO> list1 = participantSvc.getAll();
			session.setAttribute("list", list1);
			ParticipantVO vo2 = participantSvc.getOneParticipant(10002);
		%>
		<%=vo2%>
<%-- 		<%=vo%> --%>
		<li>
			<FORM METHOD="post" ACTION="sportsGroup.do">
				<b>選擇揪團編號:</b> <select size="1" name="sportsGroupSN">
					<c:forEach var="sportsGroupVO" items="${sportsGroupSvc.all}">
						<option value="${sportsGroupVO.sportsGroupSN}">${sportsGroupVO.sportsGroupSN}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</li>
	</ul>
	<table>
		<tr>
			<th>流水號</th>
			<th>揪團編號</th>
			<th>參加者</th>
		</tr>
		<c:forEach var="participantVO" items="${list}">
			<tr>
				<td>${participantVO.participantID}</td>
				<td>${participantVO.sportsGroupSN}</td>
				<td>${participantVO.userId}</td>
			</tr>
		</c:forEach>
	</table>
	<h3>揪團管理</h3>

	<ul>
		<li><a href='addEmp.jsp'>Add</a> a new Emp.</li>
	</ul>
	

</body>
</html>