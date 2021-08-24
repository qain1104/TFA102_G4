<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.Timestamp"%>
<%@ page import="com.GeneralUser.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	GeneralUserService generalUserSvc = new GeneralUserService();
	List<GeneralUserVO> list = generalUserSvc.getAll();
	pageContext.setAttribute("list", list);
%>


<html>
<head>
<title>所有一般會員資料 - listAllGeneralCorpUser.jsp</title>

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

	<h4>此頁練習採用 EL 的寫法取值:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>所有一般會員資料 - listAllCorpUser.jsp</h3>
				<h4>
					<a href="select_page.jsp"><img src="images/back1.gif"
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

		<c:forEach var="generalUserVO" items="${list}">

			<tr>
				<td>${generalUserVO.userId}</td>
				<td>${generalUserVO.registerStatus}</td>
				<td>${generalUserVO.userAccount}</td>
				<td>${generalUserVO.userPassword}</td>
				<td>${generalUserVO.userName}</td>
				<td>${generalUserVO.id}</td>
				<td>${generalUserVO.email}</td>
				<td>${generalUserVO.phone}</td>
				<td>${generalUserVO.address}</td>
				<td><img class="rounded-circle mt-5" src="<%=request.getContextPath()%>/Readerpic?userId=${generalUserVO.userId}"></td>
				<td>${generalUserVO.createdTime}</td>
				<td>${generalUserVO.gender}</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/generalUser/GeneralUserServlet.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="刪除"> <input type="hidden"
							name="userId" value="${generalUserVO.userId}"> <input
							type="hidden" name="action" value="delete">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>