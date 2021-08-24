<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.GeneralUser.model.*"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%
	GeneralUserVO generalUserVO = (GeneralUserVO) request.getAttribute("generalUserVO");
	pageContext.setAttribute("generalUserVO", generalUserVO);
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
            <div class="d-flex flex-column align-items-center text-center p-3 py-5"><img class="rounded-circle mt-5" src="<%=request.getContextPath()%>/Readerpic?userId=${generalUserVO.userId}"></div>
        </div>
        <div class="col-md-5 border-right">
            <div class="p-3 py-5">
                <div class="d-flex justify-content-between align-items-center mb-3">
                    <h4 class="text-right">�ӤH���</h4>
                </div>
                <div class="row mt-2">
                    <div class="col-md-6"><label class="labels">�@��|���s��:</label><input type="text"  readonly="readonly" class="form-control" value="${generalUserVO.userId}"></div>
                    <div class="col-md-6"><label class="labels">���U���A:</label><input type="text"  readonly="readonly" class="form-control" value="${generalUserVO.registerStatus}"></div>
                </div>
                <div class="row mt-3">
                    <div class="col-md-6"><label class="labels">�@��|���b��:</label><input type="text" readonly="readonly" class="form-control"  value="${generalUserVO.userAccount}"></div>
                    <div class="col-md-6"><label class="labels">�@��|���K�X:</label><input type="text" readonly="readonly" class="form-control"  value="${generalUserVO.userPassword}"></div>
                    <div class="col-md-6"><label class="labels">�|���m�W:</label><input type="text" readonly="readonly" class="form-control"  value="${generalUserVO.userName}"></div>
                    <div class="col-md-6"><label class="labels">�ʧO:</label><input type="text" readonly="readonly" class="form-control"  value="${generalUserVO.gender}"></div>
                    <div class="col-md-6"><label class="labels">�����Ҧr��:</label><input type="text" readonly="readonly" class="form-control"  value="${generalUserVO.id}"></div>
                    <div class="col-md-6"><label class="labels">�q��:</label><input type="text" readonly="readonly" class="form-control" value="${generalUserVO.phone}"></div>
                </div>
                <div class="row mt-3">
                    <div class="col-md-6"><label class="labels">�H�c:</label><input type="text" readonly="readonly" class="form-control" value="${generalUserVO.email}"></div>
                    <div class="col-md-6"><label class="labels">�Ыخɶ�:</label><input type="text" readonly="readonly" class="form-control" value="${generalUserVO.createdTime}>"></div>
                    <div class="col-md-12"><label class="labels">�a�}:</label><input type="text" readonly="readonly" class="form-control" value="${generalUserVO.address}"></div>
                    
                </div>
           		<form style="display: inline;" method="post" action="<%=request.getContextPath()%>/generalUser/GeneralUserServlet.do" >  
                    <input type="hidden" name="action" value="edit">  
                    <div style="display: inline;"><button style="margin: 30px 100px;" class="btn btn-primary profile-button" type="submit">�ק�</button></div>
                </form>
           
                <form style="display: inline;" method="post" action="<%=request.getContextPath()%>/generalUser/GeneralUserServlet.do" >  
                    <input type="hidden" name="action" value="back">  
                    <div style="display: inline;"><button class="btn btn-secondary profile-button" type="submit">��^</button></div>
                </form>
            </div>
        </div>
      
    </div>
</div>
<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>