<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
</head>
<body>
<%-- ���~��C --%>
123---${articleSN}---${apopVO.articleSN}123---${apopVO}
								<c:if test="${not empty errorMsgs}">
									<font style="color: red">�Эץ��H�U���~:</font>
									<ul>
										<c:forEach var="message" items="${errorMsgs}">
											<li style="color: red">${message}</li>
										</c:forEach>
									</ul>
								</c:if>
								<%-- !���~��C --%>
</body>
</html>