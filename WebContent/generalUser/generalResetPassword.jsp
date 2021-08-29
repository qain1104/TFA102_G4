<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.GeneralUser.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	GeneralUserVO currentG = (GeneralUserVO) request.getAttribute("currentG");
%>
<html>
<head>
<jsp:include page="/cssLink.jsp"></jsp:include>
<meta charset="UTF-8">
<title>修改密碼</title>
</head>
<body>
	<jsp:include page="/header.jsp" flush="true" />
	<div class="container rounded bg-white mt-5 mb-5">
		<div class="row">
			</div>
			<div class="col-md-6 border-right">
				<div class="p-10 py-10">
					<form method="post" action="<%=request.getContextPath()%>/generalUser/GeneralUserServlet.do">
						<div class="row mt-6">
							<div class="col-md-6">
								<label class="labels">Hi:</label><input type="text"
									readonly="readonly" class="form-control" name="userAccount"
									value="${generalUserVO.userAccount}">
							</div>
							<input type="hidden" name="userId" value="${generalUserVO.userId}">
							<div class="col-md-12">
								<label class="labels">輸入新密碼:</label><input type="password"
									class="form-control" name="userPassword" id="password" autofocus required="required"
									value="">
							</div>
							<div class="col-md-12">
								<label class="labels">再次輸入新密碼:</label><input type="password"
									 class="form-control" name="repeatpass" id="repeatpass" required="required"  autofocus value="">
							</div>
						</div>
						<input type="hidden" name="action" value="resetPassword">
						<div class="mt-5 text-center">
							<button class="btn btn-primary profile-button" type="submit">更新密碼</button>
						</div>
					</form>
				</div>
			</div>

		</div>
		
		<script type="text/javascript">
			$("#password").on("focusout", function() {
				let password = $("#password").val();
				var myReg = /^[(a-zA-Z0-9)]{8,30}$/;
				if (password == '' || !myReg.exec(password)) {
					alert("密碼:為英文+數字,長度8~30");
				}
			});
			$("#repeatpass").on("focusout", function() {
				let repeatpass = $("repeatpass").val();
				if (repeatpass == '' || repeatpass != password) {
					alert("請跟密碼一致");
				}
			});
		</script>
	<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>