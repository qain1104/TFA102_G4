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
<jsp:include page="/cssLink.jsp"></jsp:include>
</head>
<body>
	<div class="container mt-5 mb-5">
		<table class="table-primary justify-content-md-center">
			<div class="table-primary justify-content-md-center">
				<thead>
					<tr class="primary border border-primary text-center mx-auto ">
						<th scope="col">編號</th>
						<th scope="col">狀態</th>
						<th scope="col">名稱</th>
						<th scope="col">email</th>
						<th scope="col">帳號</th>
<!-- 						<th scope="col">密碼</th> -->
						<th scope="col">大頭貼</th>
					</tr>
				</thead>
				<%@ include file="pages/page1.file"%>
				<c:forEach var="webManagerVO" items="${list}" begin="<%=pageIndex%>"
					end="<%=pageIndex+rowsPerPage-1%>">
					<tbody>
						<tr
							class="table-primary border border-primary text-center mx-auto">
							<th scope="row">${webManagerVO.managerId}</th>
							<td style="min-width: 30px">${webManagerVO.managerStatus==0?'離職':'在職'}</td>
							<td style="min-width: 30px">${webManagerVO.managerName}</td>
							<td style="min-width: 30px">${webManagerVO.managerEmail}</td>
							<td style="min-width: 30px">${webManagerVO.managerAccount}</td>
<%-- 							<td style="min-width: 30px">${webManagerVO.managerPassword}</td> --%>
							<td style="min-width: 30px"><img class="rounded-circle mt-5"
								src="<%=request.getContextPath()%>/Readerpic?managerId=${webManagerVO.managerId}"
								style="max-width: 30%; max-height: 30%"></td>
						</tr>
					</tbody>
				</c:forEach>
		</table>
		<div>
			<%@ include file="pages/page2.file"%>
		</div>
		<a class="btn btn-primary fw-bold" href='managers_page.jsp'>返回</a>
	</div>
</body>
</html>