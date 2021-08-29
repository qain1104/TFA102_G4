<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.Timestamp"%>
<%@ page import="java.util.Date"%>
<%@ page import="com.CorpUser.model.*"%>
<%@ page import="java.text.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
	CorpUserService corpSvc = new CorpUserService();
	List<CorpUserVO> list = corpSvc.getAll();
	Date date = new Date();
	SimpleDateFormat myFmt1 = new SimpleDateFormat("yyyy/MM/dd HH:mm");
	pageContext.setAttribute("list", list);
%>


<html>
<head>
<title>�Ҧ����~�|����� - listAllCorpUser.jsp</title>
<jsp:include page="/cssLink.jsp"></jsp:include>
</head>
<body>
	<div class="container mt-5 mb-5">

		<table class="table-primary justify-content-md-center">
			<tr class="text-center">
				<th>�s��</th>
				<th>���A</th>
				<th>�b��</th>
				<!-- 			<th>�K�X</th> -->
				<th>���q</th>
				<th>�渹</th>
				<th>�H�c</th>
				<th>�q��</th>
				<th>�a�}</th>
				<th>�j�Y�K</th>
				<th>�Ыخɶ�</th>
			</tr>
			<%@ include file="pages/page1.file"%>
			<c:forEach var="corpUserVO" items="${list}" begin="<%=pageIndex%>"
				end="<%=pageIndex + rowsPerPage - 1%>">
				<tbody>
				
					<tr class="table-primary border border-primary text-center mx-auto">
						<th scope="row">${corpUserVO.corpUserId}</th>
						<td>${corpUserVO.registerStatus==0?'���{��':'�w�{��'}</td>
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
		<a class="btn btn-primary fw-bold" href='select_page.jsp'>��^</a>
	</div>
</body>
</html>