<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.CorpUser.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	CorpUserVO currentC = (CorpUserVO) request.getAttribute("currentC");
%>
<html>
<head>
<jsp:include page="/cssLink.jsp"></jsp:include>
<meta charset="UTF-8">
<title>�ק�K�X</title>
</head>
<body>
	<jsp:include page="/header.jsp" flush="true" />
	<div class="container rounded bg-white mt-5 mb-5">
		<div class="row">
			</div>
			<div class="col-md-6 border-right">
				<div class="p-10 py-10">
					<form method="post" action="<%=request.getContextPath()%>/corpUser/CorpUserServlet.do">
						<div class="row mt-6">
							<div class="col-md-6">
								<label class="labels">Hi:</label><input type="text"
									readonly="readonly" class="form-control" name="corpAccount"
									value="${currentC.corpAccount}">
							</div>
							<input type="hidden" name="corpUserId" value="${currentC.corpUserId}">
							<div class="col-md-12">
								<label class="labels">��J�s�K�X:</label><input type="password"
									class="form-control" name="corpPassword" id="password" autofocus required="required"
									value="">
							</div>
							<div class="col-md-12">
								<label class="labels">�A����J�s�K�X:</label><input type="password"
									 class="form-control" name="repeatpass" id="repeatpass" required="required"  autofocus value="">
							</div>
						</div>
						<input type="hidden" name="action" value="resetPassword">
						<div class="mt-5 text-center">
							<button class="btn btn-primary profile-button" type="submit">��s�K�X</button>
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
					alert("�K�X:���^��+�Ʀr,����8~30");
				}
			});
			$("#repeatpass").on("focusout", function() {
				let repeatpass = $("repeatpass").val();
				if (repeatpass == '' || repeatpass != password) {
					alert("�и�K�X�@�P");
				}
			});
		</script>
	<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>