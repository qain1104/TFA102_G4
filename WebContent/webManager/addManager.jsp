<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.Timestamp"%>
<%@ page import="com.WebManager.model.*"%>

<%
	WebManagerVO webManagerVO = (WebManagerVO) session.getAttribute("webManagerVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>�޲z����Ʒs�W - addManager.jsp</title>

<jsp:include page="/cssLink.jsp"></jsp:include>

</head>
<body bgcolor='white'>
	<div class="container rounded bg-white mt-5 mb-5">
		<div class="p-3 py-5">
			<h1 class="text-primary fw-bold">�s�W�޲z��</h1>
			<p>��e�ϥΪ�:${webManagerVO.managerName}</p>
			<a class="btn btn-primary" href="managers_page.jsp">��^</a>
		</div>
		<div>
			<h3>��Ʒs�W:</h3>

			<%-- ���~��C --%>
			<c:if test="${not empty errorMsgs}">
				<font style="color: red">�Эץ��H�U���~:</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color: red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/webManager/WebManagerServlet.do"
				id="form1">

				<div class="mb-3">
					<label for="formGroupExampleInput" class="form-label">�޲z���W��:</label>
					<input class="form-control" type="TEXT" name="managerName"
						size="45" required="required" value="" placeholder="�i��J����B�^��B�Ʀr">
				</div>
				<div class="mb-3">
					<label for="formGroupExampleInput2" class="form-label">�޲z���H�c:</label><input
						class="form-control" type="Email" name="managerEmail" size="45"
						required="required" value="" placeholder="�вŦX���`�H�c�榡">
				</div>
				<div class="mb-3">
					<label for="formGroupExampleInput" class="form-label">�޲z���b��:</label><input
						class="form-control" type="text" name="managerAccount" size="45"
						required="required" value="" placeholder="�i��J�^��B�Ʀr">
				</div>
				<div class="mb-3">
					<label for="formGroupExampleInput" class="form-label">�޲z���K�X:</label><input
						class="form-control" type="password" name="managerPassword"
						size="45" required="required" value="" placeholder="�i��J�^��B�Ʀr">
				</div>
				<br> <input type="hidden" name="action" value="insert">
				<input class="btn btn-primary" type="submit" value="�e�X�s�W">
			</FORM>
		</div>
	</div>
	<script>
		$("#form1").submit(function() {
			alert("�s�W���\�I");
		});
	</script>
</body>



</html>