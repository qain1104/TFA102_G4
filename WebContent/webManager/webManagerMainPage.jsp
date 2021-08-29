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
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<body>
    <!-- 一般會員中心本體 -->
    <div class="container pb-5 pt-5 border border-5  position-absolute top-50 start-50 translate-middle">
        <h1 class=" text-primary fw-bold">Sportify後台</h1>
        <br>
        <p class="">目前管理員:${webManagerVO.managerName}</p>
      
        <div class="row mt-5">
          <div class="col">
           <h3>管理員資料</h3>
            
            <form action="<%=request.getContextPath()%>/webManager/WebManagerServlet.do" method="post">
            	<input type="hidden" name="action" value="getOne_For_Display">
            	<input type="hidden" name="managerId" value="${webManagerVO.managerId}">
            		<ul class="list-group">
                		<li class="list-group-item"><button type="submit" class="btn btn-primary">資料修改</button></li>
              		</ul>
            </form>
          </div>
          <div class="col">
            <h3>人員管理</h3>
            
     		 <form action="<%=request.getContextPath()%>/webManager/WebManagerServlet.do" method="post">
            	<input type="hidden" name="action" value="getMembers">   
        	  		<ul class="list-group">
             			<li class="list-group-item"><button type="submit" class="btn btn-primary">平台會員</button></li>
    				</ul>
             </form>
             
             <form action="<%=request.getContextPath()%>/webManager/WebManagerServlet.do" method="post">
             	<input type="hidden" name="action" value="getManagers"> 
             		<ul class="list-group">     
               			<li class="list-group-item"><button type="submit" class="btn btn-primary">後台管理員</button></li>
              		</ul>
              </form>
          </div>
          <div class="col">
           <h3>管理員功能</h3>
            <ul class="list-group">
                <li class="list-group-item"><button type="button" class="btn btn-primary">審核首頁</button></li>
   	            <li class="list-group-item"><button type="button" class="btn btn-primary">客服回應</button></li>
              </ul>
          </div>
         
        
        </div>
      </div>
      <br>
    <!-- Close 一般會員中心 -->
</body>


</html>
