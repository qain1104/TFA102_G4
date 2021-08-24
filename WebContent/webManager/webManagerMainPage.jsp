<%@page import="com.WebManager.model.WebManagerService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ page import="com.WebManager.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<%
	WebManagerVO webManagerVO = (WebManagerVO) session.getAttribute("webManagerVO");
%>
<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<body>
<jsp:include page="/header.jsp" flush="true"/>
    <!-- �@��|�����ߥ��� -->
    <div class="container">
        <h1>��x����</h1>
        <br>
        <p>�ثe�޲z��:${webManagerVO.managerName}</p>
      
        <div class="row">
          <div class="col">
           <h3>�ӤH���</h3>
            
            <form action="<%=request.getContextPath()%>/webManager/WebManagerServlet.do" method="post">
            	<input type="hidden" name="action" value="getOne_For_Display">
            	<input type="hidden" name="managerId" value="${webManagerVO.managerId}">
            		<ul class="list-group">
                		<li class="list-group-item"><button type="submit" class="btn btn-primary">��ƭק�</button></li>
              		</ul>
            </form>
          </div>
          <div class="col">
            <h3>�H���޲z</h3>
            
     		 <form action="<%=request.getContextPath()%>/webManager/WebManagerServlet.do" method="post">
            	<input type="hidden" name="action" value="getMembers">   
        	  		<ul class="list-group">
             			<li class="list-group-item"><button type="submit" class="btn btn-primary">���x�|��</button></li>
    				</ul>
             </form>
             
             <form action="<%=request.getContextPath()%>/webManager/WebManagerServlet.do" method="post">
             	<input type="hidden" name="action" value="getManagers"> 
             		<ul class="list-group">     
               			<li class="list-group-item"><button type="submit" class="btn btn-primary">��x�޲z��</button></li>
              		</ul>
              </form>
          </div>
          <div class="col">
           <h3>�U���f��</h3>
            <ul class="list-group">
                <li class="list-group-item"><button type="button" class="btn btn-primary">�ӫ~�f��</button></li>
                <li class="list-group-item"><button type="button" class="btn btn-primary">���a�f��</button></li>
              </ul>
          </div>
         
        
        </div>
      </div>
      <br>
    <!-- Close �@��|������ -->
    <jsp:include page="/footer.jsp"></jsp:include>
</body>


</html>
