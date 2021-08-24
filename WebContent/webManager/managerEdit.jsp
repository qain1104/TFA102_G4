<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.WebManager.model.WebManagerVO"%>

<%
	WebManagerVO webManagerVO = (WebManagerVO) request.getAttribute("webManagerVO");
%>
<html>
<head>
<meta charset="UTF-8">
<title>更改管理員資料</title>
</head>
<body>
<jsp:include page="/header.jsp" flush="true"/>
<div class="container rounded bg-white mt-5 mb-5">
    <div class="row">
        <div class="col-md-3 border-right">
            <div class="d-flex flex-column align-items-center text-center p-3 py-5" ><img class="rounded-circle mt-5" src="<%=request.getContextPath()%>/Readerpic?managerId=${webManagerVO.managerId}" ></div>
        	  	<FORM action="<%=request.getContextPath()%>/managerUpload.do" method=post enctype="multipart/form-data">
        			<input type="file" name="managerPic">
        			<input type="hidden" name="managerId" value="${webManagerVO.managerId}">
       				<input type="submit" value="上傳">
  				</FORM>
        </div>
        <div class="col-md-5 border-right">
            <div class="p-3 py-5">
                <div class="d-flex justify-content-between align-items-center mb-3">
                    <h4 class="text-right">管理員資料</h4>
                </div>
                <%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color:red">請修正以下錯誤:</font>
			<ul>
	    		<c:forEach var="message" items="${errorMsgs}">
					<li style="color:red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
               <form method="post" action="<%=request.getContextPath()%>/webManager/WebManagerServlet.do" >   
                 <div class="row mt-2">
                    <div class="col-md-6"><label class="labels">管理員編號:</label><input type="text"  readonly="readonly" class="form-control" name="managerId" value="${webManagerVO.managerId}"></div>
                </div>
                <div class="row mt-2">
                    <div class="col-md-6"><label class="labels">管理員名稱:</label><input type="text" readonly="readonly" class="form-control"  name="managerName" value="${webManagerVO.managerName}"></div>
                    <div class="col-md-6"><label class="labels">管理員狀態:</label><input type="text"  readonly="readonly" class="form-control"  name="managerStatus" value="${webManagerVO.managerStatus}"></div>
                </div>
                <div class="row mt-3">
                    <div class="col-md-6"><label class="labels">管理員帳號:</label><input type="text" readonly="readonly" class="form-control" name="managerAccount" value="${webManagerVO.managerAccount}"></div>
                    <div class="col-md-6"><label class="labels">管理員密碼:</label><input type="text"  class="form-control" name="managerPassword" value="${webManagerVO.managerPassword}"></div>
                	<div class="col-md-12"><label class="labels">管理員email:</label><input type="text" readonly="readonly" class="form-control" name="managerEmail" value="${webManagerVO.managerEmail}"></div>
                	<input type="hidden" name="managerPic" value="${webManagerVO.managerPic}">
                </div>
            	<input type="hidden" name="action" value="update">  
                <div class="mt-5 text-center"><button class="btn btn-primary profile-button" type="submit">確認修改</button></div>
            </form>
            </div>
        </div>
      
    </div>
</div>
<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>