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
    <!-- 一般會員中心本體 -->
    <div class="container pb-5 pt-5">
        <h1 class=" mt-3 text-success fw-bold">會員中心 一般會員</h1>
        <br>
      
        <div class="row">
          <div class="col text-center">
           <h3>個人資料</h3>
            <form action="<%=request.getContextPath()%>/generalUser/GeneralUserServlet.do" method="post">
            <input type="hidden" name="action" value="getOne_For_Display">
            <ul class="list-group">
                <li class="list-group-item"><button type="submit" class="btn btn-success">資料修改</button></li>
              </ul>
                </form>
          </div>
          <div class="col text-center">
            <h3>訂單管理</h3>
            <form action="送到接收查看訂單的Servlet" method="post">
            <input type="hidden" name="action" value="">
            
            <ul class="list-group">
                <li class="list-group-item"><button type="button" class="btn btn-success"><a href="<%= request.getContextPath() %>/order/MorderManagement.do?action=morderManagement">我的商品訂單</a></button></li>
                <li class="list-group-item"><button type="button" class="btn btn-success">我的場地租借單</button></li>
              </ul>
          </div>
          <div class="col text-center" >
           <h3>追蹤清單</h3>
            <ul class="list-group">
                <li class="list-group-item"><button type="button" class="btn btn-success"><a href="<%= request.getContextPath() %>/tracking/ProductTracking.do?action=trackingManagement">我的追蹤商品</a></button></li>
                <li class="list-group-item"><button type="button" class="btn btn-success">我的追蹤場地</button></li>
              </ul>
          </div>
          <div class="col text-center">
           <h3>我的評論</h3>
            <ul class="list-group">
                <li class="list-group-item"><button type="button" class="btn btn-success"><a href="<%= request.getContextPath() %>/order/MorderManagement.do?action=reviewManagement">查看商品評論</a></button></li>
                <li class="list-group-item"><button type="button" class="btn btn-success">查看場地評論</button></li>
              </ul>
          </div>
        </div>
      </div>
      <br>
    <!-- Close 一般會員中心 -->
    <jsp:include page="/footer.jsp"></jsp:include>
    <jsp:include page="/jsLink.jsp" flush="true"/>
</body>


</html>
