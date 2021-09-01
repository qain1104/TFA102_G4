<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.CorpUser.model.*"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	CorpUserVO corpUserVO = (CorpUserVO) request.getAttribute("corpUserVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
	Date date = new Date();
	SimpleDateFormat myFmt1 = new SimpleDateFormat("yyyy/MM/dd HH:mm");
	date = corpUserVO.getCreatedTime();
%>

<html>
<head>
<title>listOneCorpUser.jsp</title>
<jsp:include page="/cssLink.jsp"></jsp:include>
</head>
<body>
	<div class="container mt-5 mb-5">

		<table class="table table-bordered text-center align-middle">
			<tr class="primary border border-primary text-justify">
				<th>編號</th>
				<th>狀態</th>
				<th>帳號</th>
				<!-- 			<th>密碼</th> -->
				<th>公司</th>
				<th>行號</th>
				<th>信箱</th>
				<th>電話</th>
				<th>地址</th>
				<th>大頭貼</th>
				<th>創建時間</th>
			</tr>
			<tbody>
				<tr class="border border-primary">
					<th scope="row">${corpUserVO.corpUserId}</th>
					<td>${corpUserVO.registerStatus==0?'未認證':'已認證'}</td>
					<td>${corpUserVO.corpAccount}</td>
					<td>${corpUserVO.companyName}</td>
					<td>${corpUserVO.ltdNo}</td>
					<%--<td>${generalUserVO.userPassword}</td> --%>
					<%--<td>${generalUserVO.id}</td> --%>
					<td>${corpUserVO.email}</td>
					<td>${corpUserVO.phone}</td>
					<td>${corpUserVO.address}</td>
					<td><img class="rounded-circle"
						src="<%=request.getContextPath()%>/Readerpic?corpUserId=${corpUserVO.corpUserId}"
						style="max-width: 100px;"></td>
					<td>${corpUserVO.showcreatedTime()}</td>
				</tr>
			</tbody>
		</table>
		<a class="btn btn-primary fw-bold" href='select_page.jsp'>返回</a>
	</div>
</body>
</html>