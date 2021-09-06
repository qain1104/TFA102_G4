<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.venue.model.*"%>
<%@ page import="com.venue_nophours.model.*"%>
<%
	VenueService venSvc = new VenueService();
	List<VenueVO> list = venSvc.getVenueStatus(1);
	pageContext.setAttribute("list", list);
%>

<!-- 	Integer corpUserId = (Integer)session.getAttribute("corpUserId"); -->
<html>
<head>
<title>���a�C��</title>

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
table#table01 {
	border: 2px solid black;
	text-align: center;
}

table#table01 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}

table {
	width: 400px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	width: 5px;
	padding: 5px;
	text-align: center;
}

table#table02 {
	height: 10px;
}
</style>
</head>
<body>

	<jsp:include page="/header.jsp" flush="true" />
<table id="table01" align="center">

		<tr>
			<td><h3>
					<b>���a�C��</b>
				</h3><button type="button" class="btn btn-success btn-lg "
					onclick="location.href='<%=request.getContextPath()%>/rental_list/select_page.jsp'">�ڪ����ɳ�</button>
				</td>
		</tr>

	</table>
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


	<table class="table container" class="overflow-x-scroll">
		<thead class="table-success">
			<tr>
<%-- 			<th scope="col">���a�s��</th>--%>
			<th scope="col">���a�W��</th>
			<th scope="col">���a����</th>
			<td scope="col"><b>���a�Ϥ�</b></td>
			<th scope="col">���a��T</th>
			<th scope="col">����</th>
			<th scope="col">�l��</th>
			</tr>
		</thead>

		<c:forEach var="venueVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">

			<tbody>
				<tr>
	<%--				<th scope="row">${venueVO.venueSN}</th>--%>
	 				<th scope="row">${venueVO.venueName}</th>
					<th scope="row"><c:choose>
							<c:when test="${venueVO.venueClass==1}">�x�y��</c:when>
							<c:when test="${venueVO.venueClass==2}">���y��</c:when>
							<c:when test="${venueVO.venueClass==3}">�Ʋy��</c:when>
							<c:when test="${venueVO.venueClass==4}">�S�y��</c:when>
							<c:when test="${venueVO.venueClass==5}">�вy��</c:when>
						</c:choose></th>
					
					
					<td>
						<div>
							<img width="100px" height="100px"
								src="<%=request.getContextPath()%>/ReadVenuePic?venueSN=${venueVO.venueSN}">
						</div>
					</td>
					<th>
				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/venue/venue.do">
					<div class="form-floating" col-lg-2>

						<input
							type="hidden" name="action" value="getOne_For_Display"> 
							<input type="submit" class="btn btn-success" value="���a��T">
							<input type="hidden" name="venueSN" value="${venueVO.venueSN}">
					</div>
				</FORM>
				</th>
					<th><FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/rental_list/rental_list.do"
						style="margin-bottom: 0px;">
						<input type="submit" class="btn btn-success disabled" value="����">
						<input type="hidden" name="venueSN" value="${venueVO.venueSN}">
						<input type="hidden" name="action" value="NewRentalList">
					</FORM></th>
					<th><FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/rental_list/rental_list.do"
						style="margin-bottom: 0px;">
						<input type="submit" class="btn btn-success disabled" value="�l��">
						<input type="hidden" name="venueSN" value="${venueVO.venueSN}">
						<input type="hidden" name="action" value="NewVWL">
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
	<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js"></script>
    <script src="<%=request.getContextPath() %>/assets/js/jquery-3.6.0.min.js"></script>
    <script src="<%=request.getContextPath() %>/assets/js/jquery-migrate-1.2.1.min.js"></script>
    <script src="<%=request.getContextPath() %>/assets/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <script src="<%=request.getContextPath() %>/assets/js/custom.js"></script>
    <script src="<%=request.getContextPath() %>/assets/js/VW-tracking.js"></script>
    <!-- End Script -->

    <!-- Start Slider Script -->
    <script src="<%=request.getContextPath() %>/assets/js/slick.min.js"></script>
    <script>
        $('#carousel-related-product').slick({
            infinite: true,
            arrows: false,
            slidesToShow: 4,
            slidesToScroll: 3,
            dots: true,
            responsive: [{
                    breakpoint: 1024,
                    settings: {
                        slidesToShow: 3,
                        slidesToScroll: 3
                    }
                },
                {
                    breakpoint: 600,
                    settings: {
                        slidesToShow: 2,
                        slidesToScroll: 3
                    }
                },
                {
                    breakpoint: 480,
                    settings: {
                        slidesToShow: 2,
                        slidesToScroll: 3
                    }
                }
            ]
        });
    </script>

</body>
</html>