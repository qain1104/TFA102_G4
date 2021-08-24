<%@page import="com.WebManager.model.WebManagerService"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.WebManager.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
	WebManagerService webSvc = new WebManagerService();
	List<WebManagerVO> list = webSvc.getAll();
	pageContext.setAttribute("list", list);
%>


<html>
<head>
<title>�Ҧ��޲z����� - listAllManagers.jsp</title>

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
				<h3>�Ҧ��޲z����� - listAllManagers.jsp</h3>
				<h4>
					<a href="webManagerMainPage.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">�^����</a>
				</h4>
			</td>
		</tr>
	</table>

	<%-- ���~��C --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">�Эץ��H�U���~:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

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
						<input type="submit" value="�R��"> <input type="hidden"
							name="managerId" value="${webManagerVO.managerId}"> <input
							type="hidden" name="action" value="retired">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>