<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.GeneralUser.model.*"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.*"%>


<%
	GeneralUserVO generalUserVO = (GeneralUserVO) request.getAttribute("generalUserVO");
	Date date = new Date();
	SimpleDateFormat myFmt1 = new SimpleDateFormat("yyyy/MM/dd HH:mm");
	date = generalUserVO.getCreatedTime();
%>

<html>
<head>
<title>listOneGeneralUser.jsp</title>
<jsp:include page="/cssLink.jsp"></jsp:include>


</head>
<body>
	<div class="container mt-5 mb-5">

		<table class="table-primary justify-content-md-center">
			<thead>
				<tr class="primary border border-primary text-justify">
					<th scope="col">�s��</th>
					<th scope="col">�{��</th>
					<th scope="col">�W��</th>
					<th scope="col">�ʧO</th>
					<th scope="col">�b��</th>
					<th scope="col">�H�c</th>
					<th scope="col">�q��</th>
					<th scope="col">�a�}</th>
					<th scope="col">�j�Y�K</th>
					<th scope="col">�Ыخɶ�</th>
				</tr>
			</thead>
			<tbody>
				<tr class="table-primary border border-primary">
					<th scope="row">${generalUserVO.userId}</th>
					<td style="min-width: 30px">${generalUserVO.registerStatus==0?'���{��':'�w�{��'}</td>
					<td style="min-width: 30px">${generalUserVO.userName}</td>
					<td style="min-width: 30px">${generalUserVO.gender==0?'�k':'�k'}</td>
					<td style="min-width: 30px">${generalUserVO.userAccount}</td>
					<%--<td>${generalUserVO.userPassword}</td> --%>
					<%--<td>${generalUserVO.id}</td> --%>
					<td style="min-width: 30px">${generalUserVO.email}</td>
					<td style="min-width: 30px">${generalUserVO.phone}</td>
					<td style="min-width: 30px">${generalUserVO.address}</td>
					<td style="min-width: 30px"><img class="rounded-circle mt-5"
						src="<%=request.getContextPath()%>/Readerpic?userId=${generalUserVO.userId}"
						style="max-width: 30%; max-height: 30%"></td>
					<td>${generalUserVO.showcreatedTime()}</td>
				</tr>
			</tbody>
		</table>

		<a class="btn btn-primary fw-bold" href='select_page.jsp'>��^</a>

	</div>
</body>
</html>