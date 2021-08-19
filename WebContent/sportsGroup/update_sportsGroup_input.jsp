<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.sportsgroup.model.*"%>

<%
	SportsGroupVO sportsGroupVO = (SportsGroupVO) request.getAttribute("sportsGroupVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>揪團資料修改 - update_sportsGroup_input.jsp</title>
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
				<h3>揪團資料修改 - update_sportsGroup_input.jsp</h3>
				<h4>
					<a href="<%=request.getContextPath()%>/select_page.jsp"><img
						src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
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
	<FORM METHOD="post" ACTION="sportsGroup.do" name="form1">
	<table>
	<tr>
		<td>揪團編號:<font color=red><b>*</b></font></td>
		<td><%=sportsGroupVO.getSportsGroupSN()%></td>
	</tr>
	<tr>
		<td>發起人:<font color=red><b>*</b></font></td>
		<td><%=sportsGroupVO.getUserId()%></td>
		<td><input type="TEXT" name="userid" size="45" value="<%=sportsGroupVO.getUserId()%>" /></td>
	</tr>
	<tr>
		<td>類型:<font color=red><b>*</b></font></td>
		<td><%=sportsGroupVO.getSportsType()%></td>
		<td><input type="TEXT" name="sportstype" size="45" value="<%=sportsGroupVO.getSportsType()%>" /></td>
	</tr>
	<tr>
		<td>場地:<font color=red><b>*</b></font></td>
		<td><%=sportsGroupVO.getSportsLocation()%></td>
        <td><input type="TEXT" name="SportsLocation" size="45" value="<%=sportsGroupVO.getSportsLocation()%>" /></td>
		
	</tr>
	<tr>
		<td>運動時間:<font color=red><b>*</b></font></td>
		<td><%=sportsGroupVO.getExerciseTime()%></td>
		<td><input type="TEXT" name="exercisetime" size="45" value="<%=sportsGroupVO.getExerciseTime()%>" /></td>
	</tr>
	<tr>
		<td>人數上限:<font color=red><b>*</b></font></td>
		<td><%=sportsGroupVO.getNumberUpLimit()%></td>
		<td><input type="TEXT" name="numberuplimit" size="45" value="<%=sportsGroupVO.getNumberUpLimit()%>" /></td>
	</tr>
	<tr>
		<td>人數下限:<font color=red><b>*</b></font></td>
		<td><%=sportsGroupVO.getNumberLowLimit()%></td>
		<td><input type="TEXT" name="getnumberlowlimit" size="45" value="<%=sportsGroupVO.getNumberLowLimit()%>" /></td>
	</tr>
	<tr>
		<td>報名時間:<font color=red><b>*</b></font></td>
		<td><%=sportsGroupVO.getRegistTime()%></td>
		<td><input type="TEXT" name="registtime" size="45" value="<%=sportsGroupVO.getRegistTime()%>" /></td>
	</tr>
	<tr>
		<td>報名截止時間:<font color=red><b>*</b></font></td>
		<td><%=sportsGroupVO.getRegistTimeEnd()%></td>
		<td><input type="TEXT" name="registtimeend" size="45" value="<%=sportsGroupVO.getRegistTimeEnd()%>" /></td>
	</tr>
	<tr>
		<td>發起時間:<font color=red><b>*</b></font></td>
		<td><%=sportsGroupVO.getIssueDATE()%></td>
		<td><input type="TEXT" name="issuedate" size="45" value="<%=sportsGroupVO.getIssueDATE()%>" /></td>
	</tr>
	<tr>
		<td>參加人數:<font color=red><b>*</b></font></td>
		<td><%=sportsGroupVO.getParticipantNumber()%></td>
		<td><input type="TEXT" name="sportstype" size="45" value="<%=sportsGroupVO.getParticipantNumber()%>" /></td>
	</tr>
	<tr>
		<td>是否流團:<font color=red><b>*</b></font></td>
		<td><%=sportsGroupVO.getSuccess()%></td>
		<td><input type="TEXT" name="success" size="45" value="<%=sportsGroupVO.getSuccess()%>" /></td>
	</tr>
	<tr>
		<td>備註:<font color=red><b>*</b></font></td>
		<td><%=sportsGroupVO.getRemarks()%></td>
		<td><input type="TEXT" name="success" size="45" value="<%=sportsGroupVO.getRemarks()%>" /></td>
		
	</tr>
</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="sportsGroupSN" value="<%=sportsGroupVO.getSportsGroupSN()%>">
<input type="submit" value="送出修改"></FORM>
</body>
</html>