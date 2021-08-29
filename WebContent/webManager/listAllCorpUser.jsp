<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.Timestamp"%>
<%@ page import="java.util.Date"%>
<%@ page import="com.CorpUser.model.*"%>
<%@ page import="java.text.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	CorpUserService corpSvc = new CorpUserService();
	List<CorpUserVO> list = corpSvc.getAll();
	Date date = new Date();
	SimpleDateFormat myFmt1 = new SimpleDateFormat("yyyy/MM/dd HH:mm");
	pageContext.setAttribute("list", list);
%>


<html>
<head>
<title>所有企業會員資料 - listAllCorpUser.jsp</title>
<jsp:include page="/cssLink.jsp"></jsp:include>
</head>
<body>
	<div class="container mt-5 mb-5">

		<table class="table-primary justify-content-md-center">
			<tr class="text-center">
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
			<%@ include file="pages/page1.file"%>
			<c:forEach var="corpUserVO" items="${list}" begin="<%=pageIndex%>"
				end="<%=pageIndex + rowsPerPage - 1%>">
				<tbody>
				
					<tr class="table-primary border border-primary text-center mx-auto">
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
						<td><img class="rounded-circle mt-5"
							src="<%=request.getContextPath()%>/Readerpic?corpUserId=${corpUserVO.corpUserId}"
							style="max-width: 40%; max-height: 40%"></td>
						<td>${corpUserVO.showcreatedTime()}</td>
					</tr>
				</tbody>
			</c:forEach>
			
			</table>
			<%@ include file="pages/page2.file"%>
		<a class="btn btn-primary fw-bold" href='select_page.jsp'>返回</a>
	</div>
</body>
</html>