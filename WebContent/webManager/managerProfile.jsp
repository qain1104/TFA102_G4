<%@page import="com.WebManager.model.WebManagerVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	WebManagerVO webManagerVO = (WebManagerVO) request.getAttribute("webManagerVO");
	pageContext.setAttribute("webManagerVO", webManagerVO);
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
            <div class="d-flex flex-column align-items-center text-center p-3 py-5"><img class="rounded-circle mt-5" src="<%=request.getContextPath()%>/Readerpic?managerId=${webManagerVO.managerId}"></div>
        </div>
        <div class="col-md-5 border-right">
            <div class="p-3 py-5">
                <div class="d-flex justify-content-between align-items-center mb-3">
                    <h4 class="text-right">管理員資料</h4>
                </div>
                <div class="row mt-2">
                    <div class="col-md-6"><label class="labels">管理員編號:</label><input type="text"  readonly="readonly" class="form-control" value="${webManagerVO.managerId}"></div>
                    <div class="col-md-6"><label class="labels">管理員狀態:</label><input type="text"  readonly="readonly" class="form-control" value="${webManagerVO.managerStatus}"></div>
                </div>
                <div class="row mt-3">
                    <div class="col-md-6"><label class="labels">管理員名稱:</label><input type="text" readonly="readonly" class="form-control"  value="${webManagerVO.managerName}"></div>
                    <div class="col-md-6"><label class="labels">管理員email:</label><input type="text" readonly="readonly" class="form-control" value="${webManagerVO.managerEmail}"></div>
                    <div class="col-md-6"><label class="labels">管理員帳號:</label><input type="text" readonly="readonly" class="form-control"  value="${webManagerVO.managerAccount}"></div>
                    <div class="col-md-6"><label class="labels">管理員密碼:</label><input type="text" readonly="readonly" class="form-control"  value="${webManagerVO.managerPassword}"></div>
                </div>
           		<form style="display: inline;" method="post" action="<%=request.getContextPath()%>/webManager/WebManagerServlet.do" >  
                    <input type="hidden" name="action" value="edit">  
                    <div style="display: inline;"><button style="margin: 30px 100px;" class="btn btn-primary profile-button" type="submit">修改</button></div>
                </form>
           
                <form style="display: inline;" method="post" action="<%=request.getContextPath()%>/webManager/WebManagerServlet.do" >  
                    <input type="hidden" name="action" value="back">  
                    <div style="display: inline;"><button class="btn btn-secondary profile-button" type="submit">返回</button></div>
                </form>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>