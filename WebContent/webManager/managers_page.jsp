<%@page import="com.WebManager.model.WebManagerService"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.Timestamp"%>
<%@ page import="com.WebManager.model.*"%>


<%
	WebManagerVO webManagerVO= (WebManagerVO) session.getAttribute("webManagerVO");
%>

<html>
<head>
<title>��O�޲z��-����</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: green;
    display: inline;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>��O�޲z������</h3><h4></h4></td></tr>
</table>

<p>��e�ϥΪ�:${webManagerVO.managerName}</p>

<h3>�d�ߺ޲z��:</h3>
	
<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllManagers.jsp'>List</a> �����޲z��  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/webManager/WebManagerServlet.do" >
        <b>��J�޲z���s�� (�p3001):</b>
        <input type="text" name="managerId">
        <input type="hidden" name="action" value="getOne_manager">
        <input type="submit" value="�e�X">
    </FORM>
  </li>

  <jsp:useBean id="webManagerSvc" scope="page" class="com.WebManager.model.WebManagerService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/webManager/WebManagerServlet.do" >
       <b>��ܺ޲z���s��:</b>
       <select size="1" name="managerId">
         <c:forEach var="webManagerVO" items="${webManagerSvc.all}" > 
          <option value="${webManagerVO.managerId}">${webManagerVO.managerId}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_manager">
       <input type="submit" value="�e�X">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/webManager/WebManagerServlet.do" >
       <b>��ܺ޲z���W��:</b>
        <select size="1" name="managerId">
         <c:forEach var="webManagerVO" items="${webManagerSvc.all}" > 
          <option value="${webManagerVO.managerId}">${webManagerVO.managerName}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_manager">
       <input type="submit" value="�e�X">
     </FORM>
  </li>
</ul>

<h3>��x�H���޲z</h3>

<ul>
  <li><a href='addManager.jsp'>Add</a> a new Manager.</li>
</ul>


</body>
</html>