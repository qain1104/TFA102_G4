<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.venue.model.*"%>
<%@ page import="com.venue_nophours.model.*"%>
<%
	Integer corpUserId = (Integer)session.getAttribute("corpUserId");
	VenueService venSvc = new VenueService();
	List<VenueVO> list = venSvc.getMyVenue(corpUserId);
	pageContext.setAttribute("list", list);
%>

<!-- 	 -->
<html>
<head>
<title>�������a���</title>

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


	<table class="table" >
		<thead>
			<th scope="col"></th>
			<th scope="col" style="min-width:30px">���a�s��</th>
			<th scope="col" style="min-width:30px">�|���s��</th>
			<th scope="col" >���a�W��</th>
			<th scope="col" style="min-width:30px">����</th>
			<th scope="col" >���a�y�z</th>
			<th scope="col" style="min-width:30px">���ɪ��B</th>
			<th scope="col" style="min-width:30px">�e�ǤH��</th>
			<th scope="col" style="min-width:30px">�q��</th>
			<th scope="col" style="min-width:30px">���a�Ϥ�</th>
			<th scope="col" style="min-width:30px">���a�a�}</th>
			<th scope="col" style="min-width:30px">���A</th>
			<th scope="col" style="max-width: 70px !important;">���a���}��ɶ�</th>
		</thead>

		<c:forEach var="venueVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">

			<tbody>
				<tr>
					<th scope="row"><FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/venue/venue.do"
							style="margin-bottom: 0px;">
							<input type="submit" value="�ק�" class="btn btn-success">
							<input type="hidden" name="venueSN" value="${venueVO.venueSN}">
							<input type="hidden" name="action" value="getOne_For_Update">
						</FORM></th>
					<td style="min-width:30px">${venueVO.venueSN}</td>
					<td style="min-width:30px">${venueVO.corpUserId}</td>
					<td style="max-width:30px">${venueVO.venueName}</td>
					<td style="min-width:30px"><c:choose>
							<c:when test="${venueVO.venueClass==1}">�x�y��</c:when>
							<c:when test="${venueVO.venueClass==2}">���y��</c:when>
							<c:when test="${venueVO.venueClass==3}">�Ʋy��</c:when>
							<c:when test="${venueVO.venueClass==4}">�S�y��</c:when>
							<c:when test="${venueVO.venueClass==5}">�вy��</c:when>
						</c:choose></td>
					<td style="max-width:30px"> ${venueVO.venueInfo}</td>
					<td>${venueVO.venuePrice}</td>
					<td>${venueVO.venueAccommodate}</td>
					<td>${venueVO.venuePhone}</td>
					<td>
						<div>
							<img style="max-width:70px;min-height:70px"
								src="<%=request.getContextPath()%>/ReadVenuePic?venueSN=${venueVO.venueSN}">
						</div>
					</td>
					<td style="width: 100px !important;">${venueVO.venueAddress}</td>
					<td><c:choose>
							<c:when test="${venueVO.venueStatus==0}">�f�֤�</c:when>
							<c:when test="${venueVO.venueStatus==1}">�W�[��</c:when>
							<c:when test="${venueVO.venueStatus==2}">�f�֥��q�L</c:when>
						</c:choose></td>

					<th scope="row"><FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/venuenop/venuenop.do"
							style="margin-bottom: 0px;">
							<input type="hidden" name="venueSN" value="${venueVO.venueSN}">
							<input type="hidden" name="action" value="newNopDate"> <input
								type="submit" value="�s�W���a���}��ɶ�" class="btn btn-success">
						</FORM> <br>
						<FORM METHOD="get"
							ACTION="<%=request.getContextPath()%>/venue/listAllVenueNop.jsp"
							style="margin-bottom: 0px;">
							<input type="hidden" name="venueSN" value="${venueVO.venueSN}">
							<input type="submit" value="�d�߳��a���}��ɶ�" class="btn btn-success">
						</FORM></th>
				</tr>
			</tbody>

		</c:forEach>

	</table>
	<!-- 	</table> -->
	<div style="text-align: center">
		<%@ include file="/pages/page2.file"%></div>
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