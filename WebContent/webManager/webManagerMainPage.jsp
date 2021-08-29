<%@page import="com.WebManager.model.WebManagerService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ page import="com.WebManager.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/cssLink.jsp"></jsp:include>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<%
	Integer currentW = (Integer) session.getAttribute("currentW");
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
    <!-- �@��|�����ߥ��� -->
    <div class="container pb-5 pt-5 border border-5  position-absolute top-50 start-50 translate-middle">
        <h1 class=" text-primary fw-bold">Sportify��x</h1>
        <br>
        <p class="">�ثe�޲z��:${webManagerVO.managerName}</p>
      
        <div class="row mt-5">
          <div class="col">
           <h3>�޲z�����</h3>
            
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
           <h3>�޲z���\��</h3>
            <ul class="list-group">
                <li class="list-group-item"><button type="button" class="btn btn-primary">�f�֭���</button></li>
   	            <li class="list-group-item"><button type="button" class="btn btn-primary">�ȪA�^��</button></li>
              </ul>
          </div>
         
        
        </div>
      </div>
      <br>
    <!-- Close �@��|������ -->
</body>


</html>
