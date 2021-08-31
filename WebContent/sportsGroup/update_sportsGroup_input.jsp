<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.sportsgroup.model.*"%>

<%
	SportsGroupVO sportsGroupVO = (SportsGroupVO) request.getAttribute("sportsGroupVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>���θ�ƭק� - update_sportsGroup_input.jsp</title>
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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
}
</style>
</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>���θ�ƭק� - update_sportsGroup_input.jsp</h3>
				<h4>
					<a href="<%=request.getContextPath()%>/select_page.jsp"><img
						src="images/back1.gif" width="100" height="32" border="0">�^����</a>
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
	<FORM METHOD="post" ACTION="sportsGroup.do" name="form1">
	<table>
	<tr>
		<td>���νs��:<font color=red><b>*</b></font></td>
		<td><%=sportsGroupVO.getSportsGroupSN()%></td>
	</tr>
	<tr>
		<td>�o�_�H:<font color=red><b>*</b></font></td>
		<td><%=sportsGroupVO.getUserId()%></td>
		<td><input type="TEXT" name="userid" size="45" value="<%=sportsGroupVO.getUserId()%>" /></td>
	</tr>
	<tr>
		<td>����:<font color=red><b>*</b></font></td>
		<td><%=sportsGroupVO.getSportsType()%></td>
		<td><input type="TEXT" name="sportstype" size="45" value="<%=sportsGroupVO.getSportsType()%>" /></td>
	</tr>
	<tr>
		<td>���a:<font color=red><b>*</b></font></td>
		<td><%=sportsGroupVO.getSportsLocation()%></td>
        <td><input type="TEXT" name="SportsLocation" size="45" value="<%=sportsGroupVO.getSportsLocation()%>" /></td>
		
	</tr>
	<tr>
		<td>�B�ʮɶ�:<font color=red><b>*</b></font></td>
		<td><%=sportsGroupVO.getExerciseTime()%></td>
		<td><input type="TEXT" name="exercisetime" size="45" value="<%=sportsGroupVO.getExerciseTime()%>" /></td>
	</tr>
	<tr>
		<td>�H�ƤW��:<font color=red><b>*</b></font></td>
		<td><%=sportsGroupVO.getNumberUpLimit()%></td>
		<td><input type="TEXT" name="numberuplimit" size="45" value="<%=sportsGroupVO.getNumberUpLimit()%>" /></td>
	</tr>
	<tr>
		<td>�H�ƤU��:<font color=red><b>*</b></font></td>
		<td><%=sportsGroupVO.getNumberLowLimit()%></td>
		<td><input type="TEXT" name="getnumberlowlimit" size="45" value="<%=sportsGroupVO.getNumberLowLimit()%>" /></td>
	</tr>
	<tr>
		<td>���W�ɶ�:<font color=red><b>*</b></font></td>
		<td><%=sportsGroupVO.getRegistTime()%></td>
		<td><input type="TEXT" name="registtime" size="45" value="<%=sportsGroupVO.getRegistTime()%>" /></td>
	</tr>
	<tr>
		<td>���W�I��ɶ�:<font color=red><b>*</b></font></td>
		<td><%=sportsGroupVO.getRegistTimeEnd()%></td>
		<td><input type="TEXT" name="registtimeend" size="45" value="<%=sportsGroupVO.getRegistTimeEnd()%>" /></td>
	</tr>
	<tr>
		<td>�o�_�ɶ�:<font color=red><b>*</b></font></td>
		<td><%=sportsGroupVO.getIssueDATE()%></td>
		<td><input type="TEXT" name="issuedate" size="45" value="<%=sportsGroupVO.getIssueDATE()%>" /></td>
	</tr>
	<tr>
		<td>�ѥ[�H��:<font color=red><b>*</b></font></td>
		<td><%=sportsGroupVO.getParticipantNumber()%></td>
		<td><input type="TEXT" name="sportstype" size="45" value="<%=sportsGroupVO.getParticipantNumber()%>" /></td>
	</tr>
	<tr>
		<td>�O�_�y��:<font color=red><b>*</b></font></td>
		<td><%=sportsGroupVO.getSuccess()%></td>
		<td><input type="TEXT" name="success" size="45" value="<%=sportsGroupVO.getSuccess()%>" /></td>
	</tr>
	<tr>
		<td>�Ƶ�:<font color=red><b>*</b></font></td>
		<td><%=sportsGroupVO.getRemarks()%></td>
		<td><input type="TEXT" name="success" size="45" value="<%=sportsGroupVO.getRemarks()%>" /></td>
		
	</tr>
</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="sportsGroupSN" value="<%=sportsGroupVO.getSportsGroupSN()%>">
<input type="submit" value="�e�X�ק�"></FORM>
</body>
</html>