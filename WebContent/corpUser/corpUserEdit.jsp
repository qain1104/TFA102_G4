<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.CorpUser.model.*"%>
<%@ page import="java.text.*"%>
<%@ page import="java.util.Date"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	CorpUserVO currentC = (CorpUserVO) session.getAttribute("currentC");
	if(currentC == null){
		session.setAttribute("location", request.getRequestURI());
		response.sendRedirect(request.getContextPath()+"/login.jsp");
		return;
	}
	Date date = new Date();
	SimpleDateFormat myFmt1 = new SimpleDateFormat("yyyy/MM/dd HH:mm");
%>
<html>
<head>
<jsp:include page="/cssLink.jsp"></jsp:include>
<meta charset="UTF-8">
<style>
img {
    max-width: 100%;
    max-height: 100%;
}
</style>
<title>修改企業會員資料</title>
</head>
<body>
<jsp:include page="/header.jsp" flush="true"/>
<div class="container rounded bg-white mt-5 mb-5">
    <div class="row">
        <div class="col-md-3 border-right">
              <div class="d-flex flex-column align-items-center text-center p-3 py-5"><img class="rounded-circle mt-5" src="<%=request.getContextPath()%>/Readerpic?corpUserId=${currentC.corpUserId}"></div>
        	  	<FORM action="<%=request.getContextPath()%>/corpUpload.do" method=post enctype="multipart/form-data">
        			<input type="file" name="profilePic">
        			<input type="hidden" name="corpUserId" value="${currentC.corpUserId}">
       				<input type="submit" value="上傳">
  				</FORM>
        </div>
        <div class="col-md-5 border-right">
            <div class="p-3 py-5">
                <div class="d-flex justify-content-between align-items-center mb-3">
                    <h4 class="text-right">企業資料</h4>
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
               <form method="post" action="<%=request.getContextPath()%>/corpUser/CorpUserServlet.do" >   
                <div class="row mt-2">
                    <div class="col-md-6"><label class="labels">企業會員編號:</label><input type="text"  readonly="readonly" class="form-control" name="corpUserId" value="${currentC.corpUserId}"></div>
                    <div class="col-md-6"><label class="labels">註冊狀態:</label><input type="text"  readonly="readonly" class="form-control" name="registerStatus" value="${currentC.registerStatus}"></div>
                </div>
                <div class="row mt-3">
                    <div class="col-md-6"><label class="labels">企業會員帳號:</label><input type="text" readonly="readonly" class="form-control" name="corpAccount" value="${currentC.corpAccount}"></div>
                    <div class="col-md-6"><label class="labels">企業會員密碼:</label><input type="password"  class="form-control" name="corpPassword" value="${currentC.corpPassword}"></div>
                    <div class="col-md-6"><label class="labels">公司名稱:</label><input type="text" readonly="readonly" class="form-control" name="companyName"  value="${currentC.companyName}"></div>
                     <div class="col-md-6"><label class="labels">電話:</label><input type="text"  class="form-control" name="phone" value="${currentC.phone}"></div>
                    <div class="col-md-12"><label class="labels">公司行號:</label><input type="text" readonly="readonly" class="form-control" name="ltdNo"  value="${currentC.ltdNo}"></div>
                   
                </div>
                <div class="row mt-3">
                    <div class="col-md-6"><label class="labels">信箱:</label><input type="text"  class="form-control" name="email" value="${currentC.email}"></div>
                    <div class="col-md-6"><label class="labels">創建時間:</label><input type="text" readonly="readonly" class="form-control" name="createdTime" value="<%=myFmt1.format(currentC.getCreatedTime())%>"></div>
                    <div class="col-md-12"><label class="labels">地址:</label><input type="text"  class="form-control" name="address" value="${currentC.address}"></div>
                    <input type="hidden" name="profilePic" value="${currentC.profilePic}">
                </div>
           
            	<input type="hidden" name="action" value="update">  
                <div class="mt-5 text-center"><button class="btn btn-success profile-button" type="submit">確認修改</button></div>
            </form>
            </div>
        </div>
      
    </div>
</div>
<jsp:include page="/footer.jsp"></jsp:include>
  <jsp:include page="/jsLink.jsp" flush="true"/>
</body>
</html>