<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.CorpUser.model.*"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%
	CorpUserVO currentC = (CorpUserVO) session.getAttribute("currentC");
	if(currentC == null){
		session.setAttribute("location", request.getRequestURI());
		response.sendRedirect(request.getContextPath()+"/login.jsp");
		return;
	}
	pageContext.setAttribute("currentC", currentC);
	
	Date date = new Date();
	SimpleDateFormat myFmt1 = new SimpleDateFormat("yyyy/MM/dd HH:mm");
	
%>
<html>
<head>
<jsp:include page="/cssLink.jsp"></jsp:include>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
img {
    max-width: 100%;
    max-height: 100%;
}


</style>
</head>
<body>
<jsp:include page="/header.jsp" flush="true"/>
<div class="container rounded bg-white mt-5 mb-5">
    <div class="row">
        <div class="col-md-3 border-right">
            <div class="d-flex flex-column align-items-center text-center p-3 py-5" style="max-width:500px max-heigh:500px"><img class="rounded-circle mt-5" src="<%=request.getContextPath()%>/Readerpic?corpUserId=${currentC.corpUserId}"></div>
        </div>
        <div class="col-md-5 border-right">
            <div class="p-3 py-5">
                <div class="d-flex justify-content-between align-items-center mb-3">
                    <h4 class="text-right">�ӤH���</h4>
                </div>
                <div class="row mt-2">
                    <div class="col-md-6"><label class="labels">���~�|���s��:</label><input type="text"  readonly="readonly" class="form-control" value="${currentC.corpUserId}"></div>
                    <div class="col-md-6"><label class="labels">���U���A:</label><input type="text"  readonly="readonly" class="form-control" value="${currentC.registerStatus==0?'���{��':'�w�{��'}"></div>
                </div>
                <div class="row mt-3">
                    <div class="col-md-6"><label class="labels">���~�|���b��:</label><input type="text" readonly="readonly" class="form-control"  value="${currentC.corpAccount}"></div>
                    <div class="col-md-6"><label class="labels">���~�|���K�X:</label><input type="password" readonly="readonly" class="form-control"  value="${currentC.corpPassword }"></div>
                    <div class="col-md-6"><label class="labels">���q�W��:</label><input type="text" readonly="readonly" class="form-control"  value="${currentC.companyName}"></div>
                     <div class="col-md-6"><label class="labels">�q��:</label><input type="text" readonly="readonly" class="form-control" value="${currentC.phone}"></div>
                    <div class="col-md-12"><label class="labels">���q�渹:</label><input type="text" readonly="readonly" class="form-control"  value="${currentC.ltdNo}"></div>
                </div>
                <div class="row mt-3">
                    <div class="col-md-6"><label class="labels">�H�c:</label><input type="text" readonly="readonly" class="form-control" value="${currentC.email}"></div>
                    <div class="col-md-6"><label class="labels">�Ыخɶ�:</label><input type="text" readonly="readonly" class="form-control" value="<%=myFmt1.format(currentC.getCreatedTime())%>"></div>
                    <div class="col-md-12"><label class="labels">�a�}:</label><input type="text" readonly="readonly" class="form-control" value="${currentC.address}"></div>
                </div>
           		<form style="display: inline;" method="post" action="<%=request.getContextPath()%>/corpUser/CorpUserServlet.do" >  
                    <input type="hidden" name="action" value="edit">  
                    <div style="display: inline;"><button style="margin: 30px 100px;" class="btn btn-primary profile-button" type="submit">�ק�</button></div>
                </form>
           
                <form style="display: inline;" method="post" action="<%=request.getContextPath()%>/corpUser/CorpUserServlet.do" >  
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