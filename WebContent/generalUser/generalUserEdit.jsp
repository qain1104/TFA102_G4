<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.GeneralUser.model.*"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	GeneralUserVO currentG = (GeneralUserVO)request.getAttribute("currentG");
	if(currentG == null){
	response.sendRedirect(request.getContextPath()+"/login.jsp");
	}
	Date date = new Date();
	SimpleDateFormat myFmt1 = new SimpleDateFormat("yyyy/MM/dd HH:mm");
%>
<html>
<head>
<jsp:include page="/cssLink.jsp"></jsp:include>
<meta charset="UTF-8">
<style>
img {
	max-width: 100%;
	max-height: 100%;
}
</style>
<title>�ק�ӤH���</title>
</head>
<body>
	<jsp:include page="/header.jsp" flush="true" />
	<div class="container rounded bg-white mt-5 mb-5">
		<div class="row">
			<div class="col-md-3 border-right">
				<div
					class="d-flex flex-column align-items-center text-center p-3 py-5"
					style="max-width: 500px max-heigh:500px">
					<img class="rounded-circle mt-5"
						src="<%=request.getContextPath()%>/Readerpic?userId=${currentG.userId}">
				</div>
				<FORM action="<%=request.getContextPath()%>/generalUpload.do"
					method=post enctype="multipart/form-data">
					<input type="file" name="profilePic"> <input type="hidden"
						name="userId" value="${currentG.userId}"> <input
						type="submit" value="�W��">
				</FORM>
			</div>
			<div class="col-md-5 border-right">
				<div class="p-3 py-5">
					<div class="d-flex justify-content-between align-items-center mb-3">
						<h4 class="text-right">�ӤH���</h4>
					</div>
					
					<form method="post"
						action="<%=request.getContextPath()%>/generalUser/GeneralUserServlet.do">
						<div class="row mt-2">
							<div class="col-md-6">
								<label class="labels">�@��|���s��:</label><input type="text"
									readonly="readonly" class="form-control" name="userId"
									value="${currentG.userId}">
							</div>
							<div class="col-md-6">
								<label class="labels">���U���A:</label><input type="text"
									readonly="readonly" class="form-control" name="showRegisterStatus"
									value="${currentG.registerStatus==0?'���{��':'�w�{��'}">
								<input type="hidden"
									readonly="readonly" class="form-control" name="registerStatus"
									value="${currentG.registerStatus}">
							</div>
						</div>
						<div class="row mt-3">
							<div class="col-md-6">
								<label class="labels">�@��|���b��:</label><input type="text"
									readonly="readonly" class="form-control" name="userAccount"
									value="${currentG.userAccount}">
							</div>
							<div class="col-md-6">
								<label class="labels">�@��|���K�X:</label><input type="text"
									class="form-control" name="userPassword"
									value="${currentG.userPassword}">
							</div>
							<div class="col-md-6">
								<label class="labels">�|���m�W:</label><input type="text"
									readonly="readonly" class="form-control" name="userName"
									value="${currentG.userName}">
							</div>
							<div class="col-md-6">
								<label class="labels">�ʧO:</label><input type="text"
									readonly="readonly" class="form-control" name="showGender"
									value="${currentG.gender==1?'�k':'�k'}">
									<input type="hidden"
									readonly="readonly" class="form-control" name="gender"
									value="${currentG.gender}">
							</div>
							<div class="col-md-6">
								<label class="labels">�����Ҧr��:</label><input type="text"
									readonly="readonly" class="form-control" name="id"
									value="${currentG.id}">
							</div>
							<div class="col-md-6">
								<label class="labels">�q��:</label><input type="text"
									class="form-control" name="phone"
									value="${currentG.phone}">
							</div>
						</div>
						<div class="row mt-3">
							<div class="col-md-6">
								<label class="labels">�H�c:</label><input type="text"
									readonly="readonly" class="form-control" name="email"
									value="${currentG.email}">
							</div>
							<div class="col-md-6">
								<label class="labels">�Ыخɶ�:</label><input type="text"
									readonly="readonly" class="form-control" name="showCreatedTime"
									value="<%=myFmt1.format(currentG.getCreatedTime())%>">
									<input type="hidden"
									readonly="readonly" class="form-control" name="createdTime"
									value="${currentG.getCreatedTime()}">
							</div>
							<div class="col-md-12">
								<label class="labels">�a�}:</label><input type="text"
									class="form-control" name="address"
									value="${currentG.address}">
							</div>
							<input type="hidden" name="profilePic"
								value="${currentG.profilePic}">
						</div>

						<input type="hidden" name="action" value="update">
						<div class="mt-5 text-center">
							<button class="btn btn-success profile-button" type="submit">�T�{�ק�</button>
						</div>
					</form>
				</div>
			</div>

		</div>
	</div>
	<jsp:include page="/footer.jsp"></jsp:include>
	<jsp:include page="/jsLink.jsp" flush="true" />
</body>
</html>