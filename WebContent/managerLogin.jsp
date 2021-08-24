<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.Timestamp"%>
<%@ page import="com.CorpUser.model.*"%>
<%@ page import="com.GeneralUser.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/webLogin.css">
<title>��O�n�J����</title>
</head>
<body>
<jsp:include page="/header.jsp" flush="true"/>
<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<div class="wrapper fadeInDown">
  <div id="formContent">
    <!-- Tabs Titles -->

    <!-- Icon -->
    <div class="fadeIn first">
      <span class="fadeIn second">Sportify��x</span>
    </div>
    


    <!-- Login Form -->
    <form  method="post" action="<%=request.getContextPath()%>/webManager/WebManagerServlet.do">
      <input type="text"  class="fadeIn second" name="inputAccount" required="required" placeholder="�޲z���b��">
      <input type="text"  class="fadeIn third" name="inputPassword"  required="required" placeholder="�޲z���K�X">
      <input type="hidden" name="action" value="managerIn">
      <input type="submit" class="fadeIn fourth" value="�n�J">
    </form>

    <!-- Remind Passowrd -->
    <div id="formFooter">
      <a class="underlineHover" href="#">�ѰO�K�X?</a>
    </div>

  </div>
</div>
<jsp:include page="/footer.jsp" flush="true"/>
</body>
</html>