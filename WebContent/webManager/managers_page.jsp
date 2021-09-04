<%@page import="com.WebManager.model.WebManagerService"%>
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
<title>後臺管理員-頁面</title>
<jsp:include page="/cssLink.jsp"></jsp:include>
</head>
<body bgcolor='white'>

	<div class="container rounded bg-white mt-5 mb-5">
		<h1 class="text-primary fw-bold">後臺管理員頁面</h1>
		<p>當前使用者:${webManagerVO.managerName}</p>
		<a class="btn btn-primary"
			href="<%=request.getContextPath()%>/webManager/webManagerMainPage.jsp">回到主選單</a>
		<div class="border border-3 mt-5 mb-5">
			<h3>查詢後台人員:</h3>
			<%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<font style="color: red">請修正以下錯誤:</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color: red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>

			<ul>
				<li><a class="text-primary fw-bold" href='listAllManagers.jsp'>List</a>
					全部管理員 <br> <br></li>

				<li>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/webManager/WebManagerServlet.do">
						<b>輸入管理員編號 (如3001):</b> <input type="text" name="managerId">
						<input type="hidden" name="action" value="getOne_manager">
						<input type="submit" value="送出">
					</FORM>
				</li>

				<jsp:useBean id="webManagerSvc" scope="page"
					class="com.WebManager.model.WebManagerService" />

				<li>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/webManager/WebManagerServlet.do">
						<b>選擇管理員編號:</b> <select size="1" name="managerId">
							<c:forEach var="webManagerVO" items="${webManagerSvc.all}">
								<option value="${webManagerVO.managerId}">${webManagerVO.managerId}
							</c:forEach>
						</select> <input type="hidden" name="action" value="getOne_manager">
						<input type="submit" value="送出">
					</FORM>
				</li>

				<li>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/webManager/WebManagerServlet.do">
						<b>選擇管理員名稱:</b> <select size="1" name="managerId">
							<c:forEach var="webManagerVO" items="${webManagerSvc.all}">
								<option value="${webManagerVO.managerId}">${webManagerVO.managerName}
							</c:forEach>
						</select> <input type="hidden" name="action" value="getOne_manager">
						<input type="submit" value="送出">
					</FORM>
				</li>
			</ul>
		</div>
		<div class="border border-3 mt-5 mb-5">
			<c:choose>
				<c:when test="${webManagerVO.managerId == 3001}">
					<h3>後台人員管理</h3>
					<ul>
						<li><a class="text-primary fw-bold" href='addManager.jsp'>新增</a>管理員</li>
					</ul>
				</c:when>
				<c:otherwise></c:otherwise>
			</c:choose>
		</div>
	</div>

</body>
</html>