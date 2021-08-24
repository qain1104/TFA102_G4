<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.GeneralUser.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	GeneralUserVO generalUserVO = (GeneralUserVO) request.getAttribute("generalUserVO");
%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="/header.jsp" flush="true"/>
<div class="container rounded bg-white mt-5 mb-5">
    <div class="row">
        <div class="col-md-3 border-right">
            <div class="d-flex flex-column align-items-center text-center p-3 py-5" ><img class="rounded-circle mt-5" src="<%=request.getContextPath()%>/Readerpic?userId=${generalUserVO.userId}" ></div>
        	  	<FORM action="<%=request.getContextPath()%>/generalUpload.do" method=post enctype="multipart/form-data">
        			<input type="file" name="profilePic">
        			<input type="hidden" name="userId" value="${generalUserVO.userId}">
       				<input type="submit" value="�W��">
  				</FORM>
        </div>
        <div class="col-md-5 border-right">
            <div class="p-3 py-5">
                <div class="d-flex justify-content-between align-items-center mb-3">
                    <h4 class="text-right">�ӤH���</h4>
                </div>
                <%-- ���~��C --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color:red">�Эץ��H�U���~:</font>
			<ul>
	    		<c:forEach var="message" items="${errorMsgs}">
					<li style="color:red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
               <form method="post" action="<%=request.getContextPath()%>/generalUser/GeneralUserServlet.do" >   
                <div class="row mt-2">
                    <div class="col-md-6"><label class="labels">�@��|���s��:</label><input type="text"  readonly="readonly" class="form-control" name="userId" value="${generalUserVO.userId}"></div>
                    <div class="col-md-6"><label class="labels">���U���A:</label><input type="text"  readonly="readonly" class="form-control" name="registerStatus" value="${generalUserVO.registerStatus}"></div>
                </div>
                <div class="row mt-3">
                    <div class="col-md-6"><label class="labels">�@��|���b��:</label><input type="text" readonly="readonly" class="form-control" name="userAccount"  value="${generalUserVO.userAccount}"></div>
                    <div class="col-md-6"><label class="labels">�@��|���K�X:</label><input type="text"  class="form-control"  name="userPassword" value="${generalUserVO.userPassword}"></div>
                    <div class="col-md-6"><label class="labels">�|���m�W:</label><input type="text" readonly="readonly" class="form-control" name="userName" value="${generalUserVO.userName}"></div>
                    <div class="col-md-6"><label class="labels">�ʧO:</label><input type="text" readonly="readonly" class="form-control"  name="gender" value="${generalUserVO.gender}"></div>
                    <div class="col-md-6"><label class="labels">�����Ҧr��:</label><input type="text" readonly="readonly" class="form-control" name="id"  value="${generalUserVO.id}"></div>
                    <div class="col-md-6"><label class="labels">�q��:</label><input type="text"  class="form-control" name="phone" value="${generalUserVO.phone}"></div>
                </div>
                <div class="row mt-3">
                    <div class="col-md-6"><label class="labels">�H�c:</label><input type="text" readonly="readonly" class="form-control" name="email" value="${generalUserVO.email}"></div>
                    <div class="col-md-6"><label class="labels">�Ыخɶ�:</label><input type="text" readonly="readonly" class="form-control" name="createdTime" value="${generalUserVO.createdTime}"></div>
                    <div class="col-md-12"><label class="labels">�a�}:</label><input type="text"  class="form-control" name="address" value="${generalUserVO.address}"></div>
                	<input type="hidden" name="profilePic" value="${generalUserVO.profilePic}">
                </div>
           
            	<input type="hidden" name="action" value="update">  
                <div class="mt-5 text-center"><button class="btn btn-primary profile-button" type="submit">�T�{�ק�</button></div>
            </form>
            </div>
        </div>
      
    </div>
</div>
<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>