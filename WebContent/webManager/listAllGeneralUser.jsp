<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.Timestamp"%>
<%@ page import="com.GeneralUser.model.*"%>
<%@ page import="java.text.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
	GeneralUserService generalUserSvc = new GeneralUserService();
	List<GeneralUserVO> list = generalUserSvc.getAll();
	Date date = new Date();
	SimpleDateFormat myFmt1 = new SimpleDateFormat("yyyy/MM/dd HH:mm");
	pageContext.setAttribute("list", list);
%>


<html>
<head>
<title>�Ҧ��@��|����� - listAllGeneralCorpUser.jsp</title>
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
				<%@ include file="pages/page1.file"%>
				<c:forEach var="generalUserVO" items="${list}"
					begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
					<tbody>
						<tr class="table-primary border border-primary text-center mx-auto">
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
				</c:forEach>
			</table>
			<div>
				<%@ include file="pages/page2.file"%>
			</div>
			<a class="btn btn-primary fw-bold" href='select_page.jsp'>��^</a>
		</div>
	</div>
</body>
</html>