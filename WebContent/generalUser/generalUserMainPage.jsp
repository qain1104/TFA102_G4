<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="Big5"%>
<%@ page import="com.GeneralUser.model.*"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/cssLink.jsp"></jsp:include>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<%
	GeneralUserVO currentG = (GeneralUserVO) session.getAttribute("currentG");
	Integer userId = (Integer) session.getAttribute("userId");
	if(currentG == null || userId == null){
		response.sendRedirect(request.getContextPath()+"/login.jsp");
	}
%>

<body>
<jsp:include page="/header.jsp" flush="true"/>
    <!-- �@��|�����ߥ��� -->
    <div class="container pb-5 pt-5">
        <h1 class=" mt-3 text-success fw-bold">�|������ �@��|��</h1>
        <br>
      
        <div class="row">
          <div class="col text-center">
           <h3>�ӤH���</h3>
            <form action="<%=request.getContextPath()%>/generalUser/GeneralUserServlet.do" method="post">
            <input type="hidden" name="action" value="getOne_For_Display">
            <ul class="list-group">
                <li class="list-group-item"><button type="submit" class="btn btn-success">��ƭק�</button></li>
              </ul>
                </form>
          </div>
          <div class="col text-center">
            <h3>�q��޲z</h3>
            <form action="�e�챵���d�ݭq�檺Servlet" method="post">
            <input type="hidden" name="action" value="">
            
            <ul class="list-group">
                <li class="list-group-item"><button type="button" class="btn btn-success"><a href="<%= request.getContextPath() %>/order/MorderManagement.do?action=morderManagement">�ڪ��ӫ~�q��</a></button></li>
                <li class="list-group-item"><button type="button" class="btn btn-success">�ڪ����a���ɳ�</button></li>
              </ul>
          </div>
          <div class="col text-center" >
           <h3>�l�ܲM��</h3>
            <ul class="list-group">
                <li class="list-group-item"><button type="button" class="btn btn-success"><a href="<%= request.getContextPath() %>/tracking/ProductTracking.do?action=trackingManagement">�ڪ��l�ܰӫ~</a></button></li>
                <li class="list-group-item"><button type="button" class="btn btn-success">�ڪ��l�ܳ��a</button></li>
              </ul>
          </div>
          <div class="col text-center">
           <h3>�ڪ�����</h3>
            <ul class="list-group">
                <li class="list-group-item"><button type="button" class="btn btn-success"><a href="<%= request.getContextPath() %>/order/MorderManagement.do?action=reviewManagement">�d�ݰӫ~����</a></button></li>
                <li class="list-group-item"><button type="button" class="btn btn-success">�d�ݳ��a����</button></li>
              </ul>
          </div>
        </div>
      </div>
      <br>
    <!-- Close �@��|������ -->
    <jsp:include page="/footer.jsp"></jsp:include>
    <jsp:include page="/jsLink.jsp" flush="true"/>
</body>


</html>
