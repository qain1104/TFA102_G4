<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.WebManager.model.*"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

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
						<th scope="col">�s��</th>
						<th scope="col">���A</th>
						<th scope="col">�W��</th>
						<th scope="col">email</th>
						<th scope="col">�b��</th>
<!-- 						<th scope="col">�K�X</th> -->
						<th scope="col">�j�Y�K</th>
					</tr>
				</thead>
					<tbody>
						<tr
							class="table-primary border border-primary text-center mx-auto">
							<th scope="row">${webManagerVO.managerId}</th>
							<td style="min-width: 30px">${webManagerVO.managerStatus==0?'��¾':'�b¾'}</td>
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
		<a class="btn btn-primary fw-bold" href='select_page.jsp'>��^</a>
	</div>

</body>
</html>