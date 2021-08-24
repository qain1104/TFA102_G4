<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.CorpUser.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	CorpUserVO corpUserVO = (CorpUserVO) request.getAttribute("corpUserVO");
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
              <div class="d-flex flex-column align-items-center text-center p-3 py-5"><img class="rounded-circle mt-5" src="<%=request.getContextPath()%>/Readerpic?corpUserId=${corpUserVO.corpUserId}"></div>
        	  	<FORM action="<%=request.getContextPath()%>/corpUpload.do" method=post enctype="multipart/form-data">
        			<input type="file" name="profilePic">
        			<input type="hidden" name="corpUserId" value="${corpUserVO.corpUserId}">
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
                    <div class="col-md-6"><label class="labels">企業會員編號:</label><input type="text"  readonly="readonly" class="form-control" name="corpUserId" value="${corpUserVO.corpUserId}"></div>
                    <div class="col-md-6"><label class="labels">註冊狀態:</label><input type="text"  readonly="readonly" class="form-control" name="registerStatus" value="${corpUserVO.registerStatus}"></div>
                </div>
                <div class="row mt-3">
                    <div class="col-md-6"><label class="labels">企業會員帳號:</label><input type="text" readonly="readonly" class="form-control" name="corpAccount" value="${corpUserVO.corpAccount}"></div>
                    <div class="col-md-6"><label class="labels">企業會員密碼:</label><input type="text"  class="form-control" name="corpPassword" value="${corpUserVO.corpPassword}"></div>
                    <div class="col-md-6"><label class="labels">公司名稱:</label><input type="text" readonly="readonly" class="form-control" name="companyName"  value="${corpUserVO.companyName}"></div>
                     <div class="col-md-6"><label class="labels">電話:</label><input type="text"  class="form-control" name="phone" value="${corpUserVO.phone}"></div>
                    <div class="col-md-12"><label class="labels">公司行號:</label><input type="text" readonly="readonly" class="form-control" name="ltdNo"  value="${corpUserVO.ltdNo}"></div>
                   
                </div>
                <div class="row mt-3">
                    <div class="col-md-6"><label class="labels">信箱:</label><input type="text"  class="form-control" name="email" value="${corpUserVO.email}"></div>
                    <div class="col-md-6"><label class="labels">創建時間:</label><input type="text" readonly="readonly" class="form-control" name="createdTime" value="${corpUserVO.createdTime}"></div>
                    <div class="col-md-12"><label class="labels">地址:</label><input type="text"  class="form-control" name="address" value="${corpUserVO.address}"></div>
                    <input type="hidden" name="profilePic" value="${corpUserVO.profilePic}">
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