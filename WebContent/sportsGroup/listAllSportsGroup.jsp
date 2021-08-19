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
<title>�Ҧ����θ�� - listAllSportsGroup.jsp</title>
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
	<h4>�����m�߱ĥ� EL ���g�k����:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>�Ҧ����θ��</h3>
				<h4>
					<a href="<%=request.getContextPath()%>/sportsGroup/select_page.jsp"><img src="images/back1.gif"
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
			<th>���νs��</th>
			<th>�o�_��</th>
			<th>�B������</th>
			<th>���a��T</th>
			<th>�B�ʮɶ�</th>
			<th>�H�ƤW��</th>
			<th>�H�ƤU��</th>
			<th>���W�ɶ�</th>
			<th>���W�I��ɶ�</th>
			<th>�o�_�ɶ�</th>
			<th>�ѥ[�H��</th>
			<th>�O�_�y��</th>
			<th>�Ƶ�</th>
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
						<input type="submit" value="�ק�"> <input type="hidden"
							name="sportsGroupSN" value="${sportsGroupVO.sportsGroupSN}">
						<input type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/sportsGroup/sportsGroup.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="�R��"> <input type="hidden"
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