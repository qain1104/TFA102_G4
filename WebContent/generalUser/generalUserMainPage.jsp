<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="Big5"%>
<%@ page import="com.GeneralUser.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<%
	GeneralUserVO generalUserVO = (GeneralUserVO) session.getAttribute("generalUserVO");
%>

<body>
<jsp:include page="/header.jsp" flush="true"/>
    <!-- �@��|�����ߥ��� -->
    <div class="container">
        <h1>�|������-�@��|��</h1>
        <br>
        <p>��!${generalUserVO.userName}</p>
      
        <div class="row">
          <div class="col">
           <h3>�ӤH���</h3>
            <form action="<%=request.getContextPath()%>/generalUser/GeneralUserServlet.do" method="post">
            <input type="hidden" name="action" value="getOne_For_Display">
            <ul class="list-group">
             
                <li class="list-group-item"><button type="submit" class="btn btn-primary">��ƭק�</button></li>
    
              </ul>
                </form>
          </div>
          <div class="col">
            <h3>�q��޲z</h3>
            <form action="�e�챵���d�ݭq�檺Servlet" method="post">
            <input type="hidden" name="action" value="">
            
            <ul class="list-group">
                <li class="list-group-item"><button type="button" class="btn btn-primary">�ڪ��ӫ~�q��</button></li>
                <li class="list-group-item"><button type="button" class="btn btn-primary">�ڪ����a���ɳ�</button></li>
              </ul>
          </div>
          <div class="col">
           <h3>�l�ܲM��</h3>
            <ul class="list-group">
                <li class="list-group-item"><button type="button" class="btn btn-primary">�ڪ��l�ܰӫ~</button></li>
                <li class="list-group-item"><button type="button" class="btn btn-primary">�ڪ��l�ܳ��a</button></li>
              </ul>
          </div>
          <div class="col">
           <h3>�ڪ�����</h3>
            <ul class="list-group">
                <li class="list-group-item"><button type="button" class="btn btn-primary">�d�ݰӫ~����</button></li>
                <li class="list-group-item"><button type="button" class="btn btn-primary">�d�ݳ��a����</button></li>
              </ul>
          </div>
          <div class="col">
           <h3>�ڪ��u�f��</h3>
            <ul class="list-group">
                <li class="list-group-item"><button type="button" class="btn btn-primary">�d���u�f��</button></li>
                
              </ul>
          </div>
        </div>
      </div>
      <br>
    <!-- Close �@��|������ -->
    <jsp:include page="/footer.jsp"></jsp:include>
</body>


</html>
