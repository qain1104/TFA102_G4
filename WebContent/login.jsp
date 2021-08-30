<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.Timestamp"%>
<%@ page import="com.CorpUser.model.*"%>
<%@ page import="com.GeneralUser.model.*"%>

<%
	CorpUserVO corpUserVO = (CorpUserVO) request.getAttribute("corpUserVO");//��i�ӲĤ@��getAttribute�O�ŭ�,���ӫO�s���L����ƥΪ�
	GeneralUserVO generalUserVO = (GeneralUserVO) request.getAttribute("generalUserVO");
	// ����֨�
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Sportify</title>
<jsp:include page="/cssLink.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="/header.jsp" flush="true" />


	<div id="logreg-forms">
		<form class="form-signin" method="post"
			action="<%=request.getContextPath()%>/webManager/WebManagerServlet.do">
			<h1 class="h3 mb-3 font-weight-normal" style="text-align: center">
				�n�JSportify</h1>
			<input type="text" name="inputAccount" class="form-control"
				placeholder="�b���ΫH�c" required="required" autofocus> <input
				type="password" name="inputPassword" class="form-control"
				placeholder="�п�J�K�X" required="required"> <input type="radio"
				name="class" class="form-check-input" value="general" checked>�@��|��
			<input type="radio" name="class" class="form-check-input"
				value="corp">���~�|�� <input type="hidden" name="action"
				value="login">
				<br>
			<%-- ���~��C --%>
			<c:if test="${not empty errorMsgs}">
				<font style="color: red"></font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color: red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>
			<button class="btn btn-success btn-block" type="submit">
				<i class="fas fa-sign-in-alt"></i>�I���n�J
			</button>
			<a href="#" id="forgot_pswd">�ѰO�K�X�F��?</a>
			<hr>
			<button class="btn btn-primary btn-block" type="button"
				id="btn-signup">
				<i class="fas fa-user-plus"></i> �@��|�����U
			</button>
			<span>OR</span>
			<button class="btn btn-primary btn-block" type="button"
				id="btn-signup-corp">
				<i class="fas fa-user-plus"></i> ���~�|�����U
			</button>
		</form>
		<!-- �ѰO�K�X  -->
		<form
			action="<%=request.getContextPath()%>/webManager/WebManagerServlet.do"
			method="post" class="form-reset" id="forgetForm">
			<input type="email" name="resetEmail" class="form-control"
				placeholder="�ж�J���Uemail" required="required" autofocus> <input
				type="hidden" name="action" value="forgetPassword">
			<button class="btn btn-primary btn-block" type="submit"
				id="btnforget" name="btnforget">�o�e�T�{�H</button>
			<a href="#" id="cancel_reset"><i class="fas fa-angle-left"></i>��^</a>
		</form>
		<!-- �ѰO�K�X  -->

		<!-- �@��|�����U  -->
		<form
			action="<%=request.getContextPath()%>/generalUser/GeneralUserServlet.do"
			class="form-signup" id="generalUser" method="post">
			<input type="text" name="userAccount" class="form-control" maxlength="16"
				placeholder="�b��"
				value="<%=(generalUserVO == null) ? "" : generalUserVO.getUserAccount()%>"
				required="required" maxlength="16"  autofocus> <input type="password"
				name="userPassword" class="form-control" maxlength="16" placeholder="�K�X"
				value="<%=(generalUserVO == null) ? "" : generalUserVO.getUserPassword()%>"
				required autofocus> <input type="password"
				name="user-repeatpass"  maxlength="16" class="form-control" placeholder="�A���T�{�K�X"
				value="" required autofocus> <input type="text"
				name="userName" maxlength="10" class="form-control" placeholder="�m�W"
				value="<%=(generalUserVO == null) ? "" : generalUserVO.getUserName()%>"
				required="required" autofocus> <input type="text" name="id"
				class="form-control" maxlength="10" placeholder="������"
				value="<%=(generalUserVO == null) ? "" : generalUserVO.getUserId()%>"
				required="required" autofocus> <input type="email"
				name="email" class="form-control" maxlength="30" placeholder="�H�c"
				value="<%=(generalUserVO == null) ? "" : generalUserVO.getEmail()%>"
				required autofocus> <input type="text" name="address"
				class="form-control" maxlength="30" placeholder="�a�}"
				value="<%=(generalUserVO == null) ? "" : generalUserVO.getAddress()%>"
				required autofocus> <input type="tel" name="phone"
				class="form-control" maxlength="10" placeholder="�q��"
				value="<%=(generalUserVO == null) ? "" : generalUserVO.getPhone()%>"
				required autofocus> <input class="form-check-input"
				type="radio" name="gender" id="man" value=1 checked>�k�� <input
				class="form-check-input" type="radio" name="gender" id="woman"
				value=0>�k�� <input type="hidden" name="action"
				value="GeneralUserSignUp">
			<!-- �W�ǷӤ� -->
			<hr>
			<button class="btn btn-primary btn-block" type="submit">
				<i class="fas fa-user-plus"></i>�e�X�ӽ�
			</button>
			<a href="#" id="cancel_signup"><i class="fas fa-angle-left"></i>��^</a>
		</form>
		<!-- �@��|�����U  -->

		<!-- ���~�|�����U -->
		<form
			action="<%=request.getContextPath()%>/corpUser/CorpUserServlet.do"
			method="post" class="form-signup-corp" id="corpUser">
			<input type="text" id="corpAccount" name="corpAccount"
				class="form-control" placeholder="�b��"
				value="<%=(corpUserVO == null) ? "" : corpUserVO.getCorpAccount()%>"
				required="required" autofocus> <input type="password"
				id="corpPassword" name="corpPassword" class="form-control"
				placeholder="�K�X"
				value="<%=(corpUserVO == null) ? "" : corpUserVO.getCorpPassword()%>"
				required autofocus> <input type="password"
				id="corpUser-repeatpass" name="corpUser-repeatpass"
				class="form-control" placeholder="�A���T�{�K�X" required autofocus>
			<input type="text" id="companyName" name="companyName" maxlength="30"
				maxlength="20" class="form-control" placeholder="���q�W��"
				value="<%=(corpUserVO == null) ? "" : corpUserVO.getCompanyName()%>"
				required="required" autofocus> <input type="text" id="ltdNo"
				name="ltdNo" maxlength="10" class="form-control" placeholder="ltdNo"
				value="<%=(corpUserVO == null) ? "" : corpUserVO.getLtdNo()%>"
				required="required" autofocus> <input type="email"
				id="email" name="email" class="form-control" placeholder="�H�c"
				value="<%=(corpUserVO == null) ? "" : corpUserVO.getEmail()%>"
				required autofocus> <input type="tel" id="phone"
				name="phone" maxlength="10" class="form-control" placeholder="�q��"
				value="<%=(corpUserVO == null) ? "" : corpUserVO.getPhone()%>"
				required autofocus> <input type="text" id="address"
				name="address" maxlength="30" class="form-control" placeholder="�a�}"
				value="<%=(corpUserVO == null) ? "" : corpUserVO.getAddress()%>"
				required autofocus> <input type="hidden" name="action"
				value="CorpUserSignUp">
			<hr>
			<button class="btn btn-primary btn-block" type="submit">
				<i class="fas fa-user-plus"></i>�e�X�ӽ�
			</button>
			<a href="#" id="cancel_signup_corp"><i class="fas fa-angle-left"></i>��^</a>
		</form>
		<!-- ���~�|�����U -->
	</div>
	<jsp:include page="/footer.jsp" flush="true" />
	<jsp:include page="/jsLink.jsp" flush="true" />


	<script>
		$("#forgetForm").submit(function() {
			alert("�w�H�H��ӫH�c�I");
		});
	</script>
</body>
</html>