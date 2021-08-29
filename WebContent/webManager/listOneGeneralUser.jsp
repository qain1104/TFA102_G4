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
					<th scope="col">編號</th>
					<th scope="col">認證</th>
					<th scope="col">名稱</th>
					<th scope="col">性別</th>
					<th scope="col">帳號</th>
					<th scope="col">信箱</th>
					<th scope="col">電話</th>
					<th scope="col">地址</th>
					<th scope="col">大頭貼</th>
					<th scope="col">創建時間</th>
				</tr>
			</thead>
			<tbody>
				<tr class="table-primary border border-primary">
					<th scope="row">${generalUserVO.userId}</th>
					<td style="min-width: 30px">${generalUserVO.registerStatus==0?'未認證':'已認證'}</td>
					<td style="min-width: 30px">${generalUserVO.userName}</td>
					<td style="min-width: 30px">${generalUserVO.gender==0?'女':'男'}</td>
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

		<a class="btn btn-primary fw-bold" href='select_page.jsp'>返回</a>

	</div>
</body>
</html>