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
		<table id="table-1">
			<tr>
				<td><h1 class="text-primary fw-bold">�d�߷|������</h1></td>
			</tr>
		</table>

		<p>��e�ϥΪ�:${webManagerVO.managerName}</p>
		<a class="btn btn-primary" href="<%=request.getContextPath()%>/webManager/webManagerMainPage.jsp">�^��D���</a>
		<div class="border border-3 mt-5 mb-5" >
			<h3>�d�ߤ@��|��:</h3>
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
				<li><a class="text-primary fw-bold" href='listAllGeneralUser.jsp'>List</a> �����@��|�� <br>
				<br></li>
				<li>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/webManager/WebManagerServlet.do">
						<b>��J�@��|���s�� (�p1001):</b> <input type="text" name="userId">
						<input type="hidden" name="action" value="getOne_generalUser">
						<input type="submit" value="�e�X">
					</FORM>
				</li>

				<jsp:useBean id="generalUserSvc" scope="page"
					class="com.GeneralUser.model.GeneralUserService" />

				<li>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/webManager/WebManagerServlet.do">
						<b>��ܤ@��|���s��:</b> <select size="1" name="userId">
							<c:forEach var="generalUserVO" items="${generalUserSvc.all}">
								<option value="${generalUserVO.userId}">${generalUserVO.userId}
							</c:forEach>
						</select> <input type="hidden" name="action" value="getOne_generalUser">
						<input type="submit" value="�e�X">
					</FORM>
				</li>

				<li>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/webManager/WebManagerServlet.do">
						<b>�ΫH�c�d��:</b> <select size="1" name="userId">
							<c:forEach var="generalUserVO" items="${generalUserSvc.all}">
								<option value="${generalUserVO.userId}">${generalUserVO.email}
							</c:forEach>
						</select> <input type="hidden" name="action" value="getOne_generalUser">
						<input type="submit" value="�e�X">
					</FORM>
				</li>
			</ul>
		</div>

<div class="border border-3 mt-5 mb-5">
		<h3>�d�ߥ��~�|��:</h3>

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
			<li><a class="text-primary fw-bold" href='listAllCorpUser.jsp'>List</a> all CorpUsers. <br>
			<br></li>


			<li>
				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/webManager/WebManagerServlet.do">
					<b>��J���~�|���s�� (�p2001):</b> <input type="text" name="corpUserId">
					<input type="hidden" name="action" value="getOne_corpUser">
					<input type="submit" value="�e�X">
				</FORM>
			</li>

			<jsp:useBean id="corpUserSvc" scope="page"
				class="com.CorpUser.model.CorpUserService" />

			<li>
				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/webManager/WebManagerServlet.do">
					<b>��ܥ��~�|���s��:</b> <select size="1" name="corpUserId">
						<c:forEach var="corpUserVO" items="${corpUserSvc.all}">
							<option value="${corpUserVO.corpUserId}">${corpUserVO.corpUserId}
						</c:forEach>
					</select> <input type="hidden" name="action" value="getOne_corpUser">
					<input type="submit" value="�e�X">
				</FORM>
			</li>

			<li>
				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/webManager/WebManagerServlet.do">
					<b>��ܤ��q�W��:</b> <select size="1" name="corpUserId">
						<c:forEach var="corpUserVO" items="${corpUserSvc.all}">
							<option value="${corpUserVO.corpUserId}">${corpUserVO.companyName}
						</c:forEach>
					</select> <input type="hidden" name="action" value="getOne_corpUser">
					<input type="submit" value="�e�X">
				</FORM>
			</li>
		</ul>
	</div>
</div>
</body>
</html>