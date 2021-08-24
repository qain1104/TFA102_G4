<%@page import="com.WebManager.model.WebManagerService"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.WebManager.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	WebManagerService webSvc = new WebManagerService();
	List<WebManagerVO> list = webSvc.getAll();
	pageContext.setAttribute("list", list);
%>


<html>
<head>
<title>所有管理員資料 - listAllManagers.jsp</title>

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
	width: 800px;
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
				<h3>所有管理員資料 - listAllManagers.jsp</h3>
				<h4>
					<a href="webManagerMainPage.jsp"><img src="images/back1.gif"
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
			<th>管理員編號</th>
			<th>管理員狀態</th>
			<th>管理員名稱</th>
			<th>管理員email</th>
			<th>管理員帳號</th>
			<th>管理員密碼</th>
			<th>管理員大頭貼</th>
		
		</tr>

		<c:forEach var="webManagerVO" items="${list}">
			<tr>
				<td>${webManagerVO.managerId}</td>
				<td>${webManagerVO.managerStatus}</td>
				<td>${webManagerVO.managerName}</td>
				<td>${webManagerVO.managerEmail}</td>
				<td>${webManagerVO.managerAccount}</td>
				<td>${webManagerVO.managerPassword}</td>
				<td><img src="<%=request.getContextPath()%>/Readerpic?managerId=${webManagerVO.managerId}"></td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/webManager/WebManagerServlet.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="刪除"> <input type="hidden"
							name="managerId" value="${webManagerVO.managerId}"> <input
							type="hidden" name="action" value="retired">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>