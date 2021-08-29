<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.Timestamp"%>
<%@ page import="com.GeneralUser.model.*"%>
<%@ page import="java.text.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	GeneralUserService generalUserSvc = new GeneralUserService();
	List<GeneralUserVO> list = generalUserSvc.getAll();
	Date date = new Date();
	SimpleDateFormat myFmt1 = new SimpleDateFormat("yyyy/MM/dd HH:mm");
	pageContext.setAttribute("list", list);
%>


<html>
<head>
<title>所有一般會員資料 - listAllGeneralCorpUser.jsp</title>
<jsp:include page="/cssLink.jsp"></jsp:include>
<style>
</style>
</head>
<body>
	<div class="container mt-5 mb-5">
			<table class="table-primary justify-content-md-center">
				<div class="table-primary justify-content-md-center">
					<thead>
						<tr class="primary border border-primary text-center mx-auto margin:auto">
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
				<%@ include file="pages/page1.file"%>
				<c:forEach var="generalUserVO" items="${list}"
					begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
					<tbody>
						<tr class="table-primary border border-primary text-center mx-auto">
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
				</c:forEach>
			</table>
			<div>
				<%@ include file="pages/page2.file"%>
			</div>
			<a class="btn btn-primary fw-bold" href='select_page.jsp'>返回</a>
		</div>
	</div>
</body>
</html>