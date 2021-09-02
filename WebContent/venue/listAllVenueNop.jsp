<%@page import="java.text.SimpleDateFormat"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.venue.model.*"%>
<%@ page import="com.venue_nophours.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.venue.util.*"%>

<%
	Integer venueSN = new Integer(request.getParameter("venueSN"));
	VenueNophoursService vnSvc = new VenueNophoursService();
	List<VenueNophoursVO> list = vnSvc.getMyVenueNop(venueSN);
	pageContext.setAttribute("list", list);
	choosetime cs = new choosetime();
	SimpleDateFormat tdformat = new SimpleDateFormat("yyyy/MM/dd");
%>
<!-- 	Integer venueSN = (Integer) session.getAttribute("venueSN"); -->
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>

<!-- ����logo -->
<link rel="shortcut icon"
	href="<%=request.getContextPath()%>/assets/img/logo1.png"
	type="image/x-icon" />

<!-- �r�� -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;200;300;400;500;700;900&display=swap">

<!-- fontawesome -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/fontawesome.min.css">

<!-- bootstrap -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/bootstrap.min.css">

<!-- �M���즳�� -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/templatemo.css">

<!-- header -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/custom.css">

<!-- Slick -->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/assets/css/slick.min.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/assets/css/slick-theme.css">

<style>
/* table { */
/* 	width: 800px; */
/* 	background-color: white; */
/* 	margin-top: 5px; */
/* 	margin-bottom: 5px; */
/* } */

/* table, th, td { */
/* 	border: 1px solid #CCCCFF; */
/* } */

/* th, td { */
/* 	padding: 5px; */
/* 	text-align: center; */
/* } */
</style>
</head>
<body>

	<jsp:include page="/header.jsp" flush="true" />

	<%-- ���~��C --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">�Эץ��H�U���~:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<div style="text-align: center"><%@ include
			file="/pages/page1.file"%></div>

<!-- 	<table> -->
<!-- 		<tr> -->
<!-- 			<th></th> -->
<!-- 			<th>���a�s��</th> -->
<!-- 			<th>���a���}����</th> -->
<!-- 			<th>���a���}��ɬq</th> -->
<!-- 		</tr> -->

		<table class="table">
			<thead>
				<tr>
					<th scope="col"></th>
					<th scope="col">���a�s��</th>
					<th scope="col">���a���}����</th>
					<th scope="col">���a���}��ɬq</th>
				</tr>
			</thead>
			<c:forEach var="VenueNophoursVO" items="${list}"
				begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
				<%
					VenueNophoursVO venueNophoursVO = (VenueNophoursVO) pageContext.getAttribute("VenueNophoursVO");
						String temps = venueNophoursVO.getVenueNophours();
						int[] temp = cs.selecttime(temps);
				%>

				<tbody>
					<tr>
						<th scope="row"><FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/venuenop/venuenop.do"
								style="margin-bottom: 0px;">
								<input type="hidden" name="venueNophoursSN"
									value="${VenueNophoursVO.venueNophoursSN}"> <input
									type="hidden" name="action" value="get_one_update"> <input
									type="submit" value="�ק���a���}��ɶ�" class="btn btn-success">
								<input type="hidden" name="whichPage"
									value='${empty param.whichPage?"1":param.whichPage}'>
							</FORM><br><a class="btn btn-success fw-bold" href='listAllVenue.jsp'>��^�������a</a></th>
							
						<td>${VenueNophoursVO.venueSN}</td>
						<td><%=tdformat.format(venueNophoursVO.getVenueDate())%></td>
						<td><p><%=((temp[0] == 0) ? "���W�E�I�줤��12�I���}��" : "���W�E�I�줤��12�I���}��")%></p>
							<p><%=((temp[1] == 0) ? "����12�I��U��3�I���}��" : "����12�I��U��3�I���}��")%></p>
							<p><%=((temp[2] == 0) ? "�U�Ȩ�3�I�ߤW6�I���}��" : "�U�Ȩ�3�I�ߤW6�I���}��")%></p></td>
					</tr>

					<!-- 			<tr> -->
					<!-- 			<td><FORM METHOD="post" -->
					<%-- 					ACTION="<%=request.getContextPath()%>/venuenop/venuenop.do" --%>
					<!-- 					style="margin-bottom: 0px;"> -->
					<%-- 						<input type="hidden" name="venueNophoursSN" value="${VenueNophoursVO.venueNophoursSN}"> --%>
					<!-- 					<input type="hidden" name="action" value="get_one_update"> <input -->
					<!-- 						type="submit" value="�ק���a���}��ɶ�" class="btn btn-success"> -->
					<%-- 						<input type="hidden" name="whichPage" value='${empty param.whichPage?"1":param.whichPage}'> --%>
					<!-- 				</FORM></td>  -->

					<%-- 				<td>${VenueNophoursVO.venueSN}</td> --%>
					<%-- 				<td><%=tdformat.format(venueNophoursVO.getVenueDate())%></td> --%>
					<%-- 				<td><p><%=((temp[0]==0)? "���W�E�I�줤��12�I���}��":"���W�E�I�줤��12�I���}��")%></p> --%>
					<%-- 				<p><%=((temp[1]==0)? "����12�I��U��3�I���}��":"����12�I��U��3�I���}��") %></p> --%>
					<%-- 				<p><%=((temp[2]==0)? "�U�Ȩ�3�I�ߤW6�I���}��":"�U�Ȩ�3�I�ߤW6�I���}��") %></p></td> --%>
					<!-- 		</tr> -->
			</c:forEach>
		</table>

		<div style="text-align: center"><%@ include
				file="/pages/page2.file"%></div>
		<jsp:include page="/footer.jsp" flush="true" />
		<!-- Start Script -->


		<!-- jQuery -->
		<script
			src="<%=request.getContextPath()%>/assets/js/jquery-3.6.0.min.js"></script>
		<script
			src="<%=request.getContextPath()%>/assets/js/jquery-migrate-1.2.1.min.js"></script>

		<!-- bootstrap -->
		<script
			src="<%=request.getContextPath()%>/assets/js/bootstrap.bundle.min.js"></script>
		<!-- ���Ӹ�W�@�ӬO�P�˪��� -->
		<script
			src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
			integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
			crossorigin="anonymous"></script>

		<!-- �M���즳�� -->
		<script src="<%=request.getContextPath()%>/assets/js/templatemo.js"></script>

		<!-- header -->
		<script src="<%=request.getContextPath()%>/assets/js/custom.js"></script>
		<!-- End Script -->
</body>
</html>