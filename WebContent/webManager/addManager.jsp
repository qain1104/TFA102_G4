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
<title>管理員資料新增 - addManager.jsp</title>

<jsp:include page="/cssLink.jsp"></jsp:include>

</head>
<body bgcolor='white'>
	<div class="container rounded bg-white mt-5 mb-5">
		<div class="p-3 py-5">
			<h1 class="text-primary fw-bold">新增管理員</h1>
			<p>當前使用者:${webManagerVO.managerName}</p>
			<a class="btn btn-primary" href="managers_page.jsp">返回</a>
		</div>
		<div>
			<h3>資料新增:</h3>

			<%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<font style="color: red">請修正以下錯誤:</font>
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
					<label for="formGroupExampleInput" class="form-label">管理員名稱:</label>
					<input class="form-control" type="TEXT" name="managerName"
						size="45" required="required" value="" placeholder="可填入中文、英文、數字">
				</div>
				<div class="mb-3">
					<label for="formGroupExampleInput2" class="form-label">管理員信箱:</label><input
						class="form-control" type="Email" name="managerEmail" size="45"
						required="required" value="" placeholder="請符合正常信箱格式">
				</div>
				<div class="mb-3">
					<label for="formGroupExampleInput" class="form-label">管理員帳號:</label><input
						class="form-control" type="text" name="managerAccount" size="45"
						required="required" value="" placeholder="可填入英文、數字">
				</div>
				<div class="mb-3">
					<label for="formGroupExampleInput" class="form-label">管理員密碼:</label><input
						class="form-control" type="password" name="managerPassword"
						size="45" required="required" value="" placeholder="可填入英文、數字">
				</div>
				<br> <input type="hidden" name="action" value="insert">
				<input class="btn btn-primary" type="submit" value="送出新增">
			</FORM>
		</div>
	</div>
	<script>
		$("#form1").submit(function() {
			alert("新增成功！");
		});
	</script>
</body>



</html>