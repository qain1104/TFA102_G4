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
<title>��O�޲z��-����</title>
<jsp:include page="/cssLink.jsp"></jsp:include>
</head>
<body bgcolor='white'>

	<div class="container rounded bg-white mt-5 mb-5">
		<h1 class="text-primary fw-bold">��O�޲z������</h1>
		<p>��e�ϥΪ�:${webManagerVO.managerName}</p>
		<a class="btn btn-primary"
			href="<%=request.getContextPath()%>/webManager/webManagerMainPage.jsp">�^��D���</a>
		<div class="border border-3 mt-5 mb-5">
			<h3>�d�߫�x�H��:</h3>
			<%-- ���~��C --%>
			<c:if test="${not empty errorMsgs}">
				<font style="color: red">�Эץ��H�U���~:</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color: red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>

			<ul>
				<li><a class="text-primary fw-bold" href='listAllManagers.jsp'>List</a>
					�����޲z�� <br> <br></li>

				<li>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/webManager/WebManagerServlet.do">
						<b>��J�޲z���s�� (�p3001):</b> <input type="text" name="managerId">
						<input type="hidden" name="action" value="getOne_manager">
						<input type="submit" value="�e�X">
					</FORM>
				</li>

				<jsp:useBean id="webManagerSvc" scope="page"
					class="com.WebManager.model.WebManagerService" />

				<li>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/webManager/WebManagerServlet.do">
						<b>��ܺ޲z���s��:</b> <select size="1" name="managerId">
							<c:forEach var="webManagerVO" items="${webManagerSvc.all}">
								<option value="${webManagerVO.managerId}">${webManagerVO.managerId}
							</c:forEach>
						</select> <input type="hidden" name="action" value="getOne_manager">
						<input type="submit" value="�e�X">
					</FORM>
				</li>

				<li>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/webManager/WebManagerServlet.do">
						<b>��ܺ޲z���W��:</b> <select size="1" name="managerId">
							<c:forEach var="webManagerVO" items="${webManagerSvc.all}">
								<option value="${webManagerVO.managerId}">${webManagerVO.managerName}
							</c:forEach>
						</select> <input type="hidden" name="action" value="getOne_manager">
						<input type="submit" value="�e�X">
					</FORM>
				</li>
			</ul>
		</div>
		<div class="border border-3 mt-5 mb-5">
			<c:choose>
				<c:when test="${webManagerVO.managerId == 3001}">
					<h3>��x�H���޲z</h3>
					<ul>
						<li><a class="text-primary fw-bold" href='addManager.jsp'>�s�W</a>�޲z��</li>
					</ul>
				</c:when>
				<c:otherwise></c:otherwise>
			</c:choose>
		</div>
	</div>

</body>
</html>