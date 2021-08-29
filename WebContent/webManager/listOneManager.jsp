<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.WebManager.model.*"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	WebManagerVO webManagerVO = (WebManagerVO) request.getAttribute("webManagerVO");
%>

<html>
<head>
<title>listOneManager.jsp</title>
<jsp:include page="/cssLink.jsp"></jsp:include>
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
		</table>
		<a class="btn btn-primary fw-bold" href='select_page.jsp'>返回</a>
	</div>

</body>
</html>