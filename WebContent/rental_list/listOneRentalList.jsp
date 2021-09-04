<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.rental_list.model.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%--@ page import="com.dept.model.*"--%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%-- ���X Concroller EmpServlet.java�w�s�Jrequest��EmpVO����--%>
<%
	RentalListVO rentalListVO = (RentalListVO) request.getAttribute("rentalListVO");
	SimpleDateFormat rformat = new SimpleDateFormat("yyyy/MM/dd");
%>

<%-- ���X ������DeptVO����--%>
<%--
  DeptService deptSvc = new DeptService();
  DeptVO deptVO = deptSvc.getOneDept(empVO.getDeptno());
--%>

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
table#table-1 {
	border: 2px solid black;
	text-align: center;
	margin: middle
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

<style>
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
	padding: 5px;
	text-align: center;
}
</style>

</head>
<body>
	<jsp:include page="/header.jsp" flush="true" />
	<table id="table-1" align="center">
		<tr>
			<td>
				<h3>
					<b>���ɳ���</b>
				</h3>
				<button type="button" class="btn btn-success btn-lg "
					onclick="location.href='<%=request.getContextPath()%>/rental_list/select_page.jsp'">���ɳ�d��</button>
			</td>
		</tr>
	</table>

	<!--�B�ʼ��� -->
	<%--
	<div class="container py-2">
		<div class="row justify-content-md-center">
			<div class="col col-lg-4">
				<div class="form-floating" col-lg-2>
					<input type="text" class="form-control"
						placeholder="Disabled input" id="formGroupExampleInput"
						name="rentalListSN" value="<%=rentalListVO.getRentalListSN()%>"
						disabled> <label for="floatingInputGrid"><b>���ɳ�s��</b></label>

				</div>
			</div>
		</div>
	</div>

	<div class="container py-2">
		<div class="row justify-content-md-center">
			<div class="col col-lg-4">
				<div class="form-floating" col-lg-2>
					<input type="text" class="form-control"
						placeholder="Disabled input" id="formGroupExampleInput"
						name="venueSN" value="<%=rentalListVO.getVenueSN()%>" disabled>
					<label for="floatingInputGrid">���a�s��</label>
				</div>
			</div>
		</div>
	</div>

	<div class="container py-2">
		<div class="row justify-content-md-center">
			<div class="col col-lg-4">
				<div class="form-floating" col-lg-2>
					<input type="text" class="form-control"
						placeholder="Disabled input" id="formGroupExampleInput"
						name="userId" value="<%=rentalListVO.getUserId()%>" disabled>
					<label for="floatingInputGrid">�|���s��</label>
				</div>
			</div>
		</div>
	</div>

	<div class="container py-2">
		<div class="row justify-content-md-center">
			<div class="col col-lg-4">
				<div class="form-floating" col-lg-2>
					<input type="text" class="form-control"
						placeholder="Disabled input" id="formGroupExampleInput"
						name="returnStatus" value="<%=rentalListVO.getReturnStatus()%>"
						disabled> <label for="floatingInputGrid">�k�٪��A</label>
				</div>
			</div>
		</div>
	</div>

	<div class="container py-2">
		<div class="row justify-content-md-center">
			<div class="col col-lg-4">
				<div class="form-floating" col-lg-2>
					<input type="text" class="form-control"
						placeholder="Disabled input" id="formGroupExampleInput"
						name="rentalDate" value="<%=rentalListVO.getRentalDate()%>"
						disabled> <label for="floatingInputGrid">���ɤ��</label>
				</div>
			</div>
		</div>
	</div>
	
	<div class="container py-2">
		<div class="row justify-content-md-center">
			<div class="col col-lg-4">
				<div class="form-floating" col-lg-2>
					<input type="text" class="form-control"
						placeholder="Disabled input" id="formGroupExampleInput"
						name="rentalTime" value="<%=rentalListVO.getRentalTime()%>"
						disabled> <label for="floatingInputGrid">���ɮɬq</label>
				</div>
			</div>
		</div>
	</div>

	<div class="container py-2">
		<div class="row justify-content-md-center">
			<div class="col col-lg-4">
				<div class="form-floating" col-lg-2>
					<input type="text" class="form-control"
						placeholder="Disabled input" id="formGroupExampleInput"
						name="beforeUse" value="" disabled>
						<img src="/TFA102_123/RLReader">
					<label for="floatingInputGrid">�ϥΫe�Ӥ�</label>
				</div>
			</div>
		</div>
	</div>

	<div class="container py-2">
		<div class="row justify-content-md-center">
			<div class="col col-lg-4">
				<div class="form-floating" col-lg-2>
					<input type="text" class="form-control"
						placeholder="Disabled input" id="formGroupExampleInput"
						name="afterUse" value="" disabled>
						<img src="/TFA102_123/RLReader">
					<label for="floatingInputGrid">�ϥΫ�Ӥ�</label>
				</div>
			</div>
		</div>
	</div>
	

 	<div class="container py-2">
		<div class="row justify-content-md-center">
			<div class="col col-lg-4">
				<div class="form-floating" col-lg-2>
					<input type="text" class="form-control"
						placeholder="Disabled input" id="formGroupExampleInput"
						name="venueReview" value="<%=rentalListVO.getVenueReview()%>"
						disabled> <label for="floatingInputGrid">���a����</label>
				</div>
			</div>
		</div>
	</div>

	<div class="container py-2">
		<div class="row justify-content-md-center">
			<div class="col col-lg-4">
				<label for="exampleFormControlTextarea1" class="form-label">���a����</label>
				<textarea class="form-control" id="exampleFormControlTextarea1"
					rows="3" name="venueReview" disabled><%=rentalListVO.getVenueReview()%></textarea>
			</div>
		</div>
	</div>--%>



	<div class="container py-5">
		<form class="row g-3">
			<div class="col-md-4">
				<div class="container py-2">
					<div class="row justify-content-md-center">
						<div class="col col-lg-8">
							<div class="form-floating" col-lg-2>
								<input type="text" class="form-control"
									placeholder="Disabled input" id="formGroupExampleInput"
									name="rentalListSN" value="<%=rentalListVO.getRentalListSN()%>"
									disabled> <label for="floatingInputGrid"><b>���ɳ�s��</b></label>

							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="container py-2">
					<div class="row justify-content-md-center">
						<div class="col col-lg-8">
							<div class="form-floating" col-lg-2>
								<input type="text" class="form-control"
									placeholder="Disabled input" id="formGroupExampleInput"
									name="venueSN" value="<%=rentalListVO.getVenueSN()%>" disabled>
								<label for="floatingInputGrid">���a�s��</label>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="container py-2">
					<div class="row justify-content-md-center">
						<div class="col col-lg-8">
							<div class="form-floating" col-lg-2>
								<input type="text" class="form-control"
									placeholder="Disabled input" id="formGroupExampleInput"
									name="userId" value="<%=rentalListVO.getUserId()%>" disabled>
								<label for="floatingInputGrid">�|���s��</label>
							</div>
						</div>
					</div>
				</div>
			</div>

<%-- 			<div class="col-md-4">
				<div class="container py-2">
					<div class="row justify-content-md-center">
						<div class="col col-lg-8">
							<div class="form-floating" col-lg-2>
								<input type="text" class="form-control"
									placeholder="Disabled input" id="formGroupExampleInput"
									name="returnStatus" value="<%=rentalListVO.getReturnStatus()%>"
									disabled> <label for="floatingInputGrid">�k�٪��A</label>
							</div>
						</div>
					</div>
				</div>
			</div>--%>

<%-- 			<div class="col-md-4">
				<div class="container py-2">
					<div class="row justify-content-md-center">
						<div class="col col-lg-8">
							<div class="form-floating" col-lg-2>
								<select class="form-select" id="floatingSelect"  disabled="disabled"
									aria-label="Floating label select example" name="returnStatus">
		<%-- 							<option value="<%=rentalListVO.getReturnStatus()%>=0">�w�k��</option>
									<option value="<%=rentalListVO.getReturnStatus()%>=1">���k��</option>--%>
					<%-- 				<c:choose>
										<option><c:when
												test="${rentalListVO.returnStatus==0}">�w�k��</c:when></option>
										<option><c:when
												test="${rentalListVO.returnStatus==1}">���k��</c:when></option>
									</c:choose>
								</select>
								<label for="floatingInputGrid">�k�٪��A</label>
							</div>
						</div>
					</div>
				</div>
			</div>--%>

			<div class="col-md-6">
				<div class="container py-2">
					<div class="row justify-content-md-center">
						<div class="col col-lg-6">
							<div class="form-floating" col-lg-2>
								<input type="text" class="form-control"
									placeholder="Disabled input" id="formGroupExampleInput"
									name="rentalDate" value="<%=rformat.format(rentalListVO.getRentalDate())%>"
									disabled> <label for="floatingInputGrid">���ɤ��</label>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="col-md-6">
				<div class="container py-2">
					<div class="row justify-content-md-center">
						<div class="col col-lg-6">
							<div class="form-floating" col-lg-2>
								<select class="form-select" id="floatingSelect"
									disabled="disabled" aria-label="Floating label select example"
									name="rentalTime">
			<%-- 					<option value="<%=rentalListVO.getRentalTime()%>=100">9AM
										~ 12PM</option>
									<option value="<%=rentalListVO.getRentalTime()%>=010">12PM
										~ 3PM</option>
									<option value="<%=rentalListVO.getRentalTime()%>=001">3PM
										~ 6PM</option>
									<option value="<%=rentalListVO.getRentalTime()%>=110">9AM
										~ 12PM & 12PM ~ 3PM</option>
									<option value="<%=rentalListVO.getRentalTime()%>=101">9AM
										~ 12PM & 3PM ~ 6PM</option>
									<option value="<%=rentalListVO.getRentalTime()%>=011">12PM
										~ 3PM & 3PM ~ 6PM</option>
									<option value="<%=rentalListVO.getRentalTime()%>=111">9AM
										~ 6PM</option>--%>	
										<c:choose>
										<option><c:when
												test="${rentalListVO.rentalTime==100}">9AM
										~ 12PM</c:when></option>
										<option><c:when
												test="${rentalListVO.rentalTime==010}">12PM
										~ 3PM</c:when></option>
										<option><c:when
												test="${rentalListVO.rentalTime==001}">3PM
										~ 6PM</c:when></option>
										<option><c:when
												test="${rentalListVO.rentalTime==110}">9AM
										~ 12PM & 12PM ~ 3PM</c:when></option>
										<option><c:when
												test="${rentalListVO.rentalTime==101}">9AM
										~ 12PM & 3PM ~ 6PM</c:when></option>
										<option><c:when
												test="${rentalListVO.rentalTime==011}">12PM
										~ 3PM & 3PM ~ 6PM</c:when></option>
										<option><c:when
												test="${rentalListVO.rentalTime==111}">9AM
										~ 6PM</c:when></option>
									</c:choose>
								</select> <label for="floatingInputGrid">���ɮɬq</label>
								<%-- 		<input type="text" class="form-control"
									placeholder="Disabled input" id="formGroupExampleInput"
									name="rentalTime" value="<%=rentalListVO.getRentalTime()%>"
									disabled> --%>
							</div>
						</div>
					</div>
				</div>
			</div>


			<div class="col-md-6">
				<div class="container py-2">
					<div class="row justify-content-md-center">
						<div class="col col-lg-6">
							<div class="form-floating" col-lg-2>
								<input type="text" class="form-control"
									placeholder="Disabled input" id="formGroupExampleInput"
									name="beforeUse" value="" disabled> <img
									src="/TFA102_123/BFReader2?rentalListSN=<%=rentalListVO.getRentalListSN()%>"
									width="300" height="200" border="0" class="img-thumbnail">
								<label for="floatingInputGrid">�ϥΫe�Ӥ�</label>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="container py-2">
					<div class="row justify-content-md-center">
						<div class="col col-lg-6">
							<div class="form-floating" col-lg-2>
								<input type="text" class="form-control"
									placeholder="Disabled input" id="formGroupExampleInput"
									name="beforeUse" value="" disabled> <img
									src="/TFA102_123/AFReader2?rentalListSN=<%=rentalListVO.getRentalListSN()%>"
									width="300" height="200" border="0" class="img-thumbnail">
								<label for="floatingInputGrid">�ϥΫ�Ӥ�</label>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="col-md-12">
				<div class="container py-2">
					<div class="row justify-content-md-center">
						<div class="col col-lg-8">
							<label for="exampleFormControlTextarea1" class="form-label">���a����</label>
							<textarea class="form-control" id="exampleFormControlTextarea1"
								rows="3" name="venueReview" disabled><%=rentalListVO.getVenueReview()%></textarea>
						</div>
					</div>
				</div>
			</div>



		</form>
	</div>
	<div align="center">
		<FORM METHOD="post"
			ACTION="<%=request.getContextPath()%>/rental_list/rental_list.do"
			style="margin-bottom: 0px;">
			<input type="submit" class="btn btn-success" value="�ק�"> <input
				type="hidden" name="rentalListSN"
				value="${rentalListVO.rentalListSN}"> <input type="hidden"
				name="action" value="getOne_For_Update">
		</FORM>
	</div>
	<br>

	<%-- 

<div class="mt-3 px-3" align="center">
		<label for="formGroupExampleInput" class="form-label">���ɳ�s��</label> <input
			type="text" placeholder="Disabled input" class="form-control"
			style="width: 30%" id="formGroupExampleInput" align="middle"
			value="<%=rentalListVO.getRentalListSN()%>" disabled>
	</div>
	
<div class="mt-3 px-3" align="center">
		<label for="formGroupExampleInput" class="form-label">���a�s��</label> <input
			type="text" placeholder="Disabled input" class="form-control"
			style="width: 30%" id="formGroupExampleInput" align="middle"
			value="<%=rentalListVO.getVenueSN()%>" disabled>
	</div>

	<div class="mt-3 px-3" align="center">
		<label for="formGroupExampleInput" class="form-label">�|���s��</label>
		<input type="text" placeholder="Disabled input" class="form-control"
			style="width: 30%" id="formGroupExampleInput"
			value="<%=rentalListVO.getUserId()%>" disabled>
	</div>

	<div class="mt-3 px-3" align="center">
		<label for="formGroupExampleInput" class="form-label">�k�٪��A</label> <input
			type="text" placeholder="Disabled input" class="form-control"
			style="width: 30%" id="formGroupExampleInput"
			value="<%=rentalListVO.getReturnStatus()%>" disabled>
	</div>

	<div class="mt-3 px-3" align="center">
		<label for="formGroupExampleInput" class="form-label">���ɤ��</label> <input
			type="text" placeholder="Disabled input" class="form-control"
			style="width: 30%" id="formGroupExampleInput"
			value="<%=rentalListVO.getRentalDate()%>" disabled>
	</div>

	<div class="mt-3 px-3" align="center">
		<label for="formGroupExampleInput" class="form-label">���a����</label> <input
			type="text" placeholder="Disabled input" class="form-control"
			style="width: 30%" id="formGroupExampleInput"
			value="<%=rentalListVO.getVenueReview()%>" disabled>
	</div>

	<div class="mt-3 px-3" align="center">
		<label for="formGroupExampleInput" class="form-label">�ϥΫe�Ӥ�</label> <input
			type="text" placeholder="Disabled input" class="form-control"
			style="width: 30%" id="formGroupExampleInput"
			value="<%=rentalListVO.getBeforeUse()%>" disabled>
	</div>

	<div class="mt-3 px-3" align="center">
		<label for="formGroupExampleInput" class="form-label">�ϥΫ�Ӥ�</label> <input
			type="text" placeholder="Disabled input" class="form-control"
			style="width: 30%" id="formGroupExampleInput"
			value="<%=rentalListVO.getAfterUse()%>" disabled>
	</div>

	<div class="mt-3 px-3" align="center">
		<label for="formGroupExampleInput" class="form-label">���ɮɬq</label> <input
			type="text" placeholder="Disabled input" class="form-control"
			style="width: 30%" id="formGroupExampleInput"
			value="<%=rentalListVO.getRentalTime()%>" disabled>
	</div><br>

<table>
	<tr>
		<th>���ɳ�s��</th>
		<th>���a�s��</th>
		<th>�|���s��</th>
		<th>�k�٪��A</th>
		<th>���ɤ��</th>
		<th>���ɮɬq</th>
	</tr>
	<tr>
		<td><%=rentalListVO.getRentalListSN()%></td>
		<td><%=rentalListVO.getVenueSN()%></td>
		<td><%=rentalListVO.getUserId()%></td>
		<td><%=rentalListVO.getReturnStatus()%></td>
		<td><%=rentalListVO.getRentalDate()%></td>
		<td><%=rentalListVO.getRentalTime()%></td>
		
	</tr>
</table>--%>
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