<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.rental_list.model.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
	RentalListService rentalListSvc = new RentalListService();
	List<RentalListVO> list = rentalListSvc.getAll();
	pageContext.setAttribute("list", list);
	SimpleDateFormat rformat = new SimpleDateFormat("yyyy/MM/dd");
%>

<html>
<head>
<title>Sportify</title>
    <link rel="shortcut icon" href="<%= request.getContextPath() %>/assets/img/logo1.png" type="image/x-icon" />
	<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;200;300;400;500;700;900&display=swap">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/fontawesome.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/templatemo.css">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/custom.css">


<style>
table#table01 {
	border: 2px solid black;
	text-align: center;
}

table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

</style>

</head>
<body>

	<jsp:include page="/header.jsp" flush="true" />
	<table id="table01" align="center">
		<tr>
			<td>
				<h3>
					<b>�Ҧ����ɸ��</b>
				</h3>
				<button type="button" class="btn btn-success btn-lg "
					onclick="location.href='<%=request.getContextPath()%>/rental_list/select_page.jsp'">���ɭ���</button>
			</td>
		</tr>
		
	</table>


	<%-- ���~��C --%>
	<div align="center">
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">�Эץ��H�U���~:</font>
			
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			
		</c:if>
	</div>

	<table class="table container" class="overflow-x-scroll">
		<thead class="table-success">
			<tr>
				<th scope="col">���ɳ�s��</th>
				<th scope="col">���a�s��</th>
				<th scope="col">�|���s��</th>
				<th scope="col">�k�٪��A</th>
				<th scope="col">���ɤ��</th>
			<%-- 
				<th scope="col">���a����</th>
				<th scope="col">�ϥΫe�Ӥ�</th>
				<th scope="col">�ϥΫ�Ӥ�</th>
											--%>
				<th scope="col">���ɮɬq</th>
				<th scope="col">�ק�</th>
				<th scope="col">�d�ݧ��㯲�ɳ�</th>
			</tr>
		</thead>

		<%@ include file="/pages/page1.file"%>
		<c:forEach var="rentalListVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">
	<c:set var="RD" value="${rentalListVO.rentalDate}"></c:set>
			<tr>
				<th scope="row">${rentalListVO.rentalListSN}</th>
				<th scope="row">${rentalListVO.venueSN}</th>
				<th scope="row">${rentalListVO.userId}</th>
				<th scope="row">${rentalListVO.returnStatus}</th>
				<th scope="row"><%=rformat.format(pageContext.getAttribute("RD")) %></th>
				<%--
				<th scope="row">${rentalListVO.venueReview}</th>
				<td>${rentalListVO.beforeUse}</td>
				<td>${rentalListVO.afterUse}</td>
				--%>
				<th scope="row">${rentalListVO.rentalTime}</th>

				
				<th>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/rental_list/rental_list.do"
						style="margin-bottom: 0px;">
						<input type="submit" class="btn btn-success" value="�ק�"> <input type="hidden"
							name="rentalListSN" value="${rentalListVO.rentalListSN}">
						<input type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</th>
				
				<th>
				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/rental_list/rental_list.do">
					<div class="form-floating" col-lg-2>

						<input
							type="hidden" name="action" value="getOne_For_Display"> 
							<input type="submit" class="btn btn-success" value="�d�ݧ��㯲�ɳ�">
							<input type="hidden" name="rentalListSN" value="${rentalListVO.rentalListSN}">
					</div>
				</FORM>
				</th>
				<%--<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/venue/venue.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�R��">
			     <input type="hidden" name="rentalListSN"  value="${rentalListVO.rentalListSN}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td> --%>
			</tr>
		</c:forEach>
	</table>
	<%@ include file="/pages/page2.file"%>
	<jsp:include page="/footer.jsp" flush="true" />
		<!-- Start Script -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js"></script>
    <script src="<%=request.getContextPath() %>/assets/js/jquery-3.6.0.min.js"></script>
    <script src="<%=request.getContextPath() %>/assets/js/jquery-migrate-1.2.1.min.js"></script>
    <script src="<%=request.getContextPath() %>/assets/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <script src="<%=request.getContextPath() %>/assets/js/templatemo.js"></script>
    <script src="<%=request.getContextPath() %>/assets/js/custom.js"></script>
    <script src="<%= request.getContextPath() %>/assets/js/general_order.js"></script>
    <!-- End Script -->
</body>
</html>