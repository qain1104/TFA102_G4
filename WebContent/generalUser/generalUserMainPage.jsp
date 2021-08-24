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
    <!-- 一般會員中心本體 -->
    <div class="container">
        <h1>會員中心-一般會員</h1>
        <br>
        <p>嗨!${generalUserVO.userName}</p>
      
        <div class="row">
          <div class="col">
           <h3>個人資料</h3>
            <form action="<%=request.getContextPath()%>/generalUser/GeneralUserServlet.do" method="post">
            <input type="hidden" name="action" value="getOne_For_Display">
            <ul class="list-group">
             
                <li class="list-group-item"><button type="submit" class="btn btn-primary">資料修改</button></li>
    
              </ul>
                </form>
          </div>
          <div class="col">
            <h3>訂單管理</h3>
            <form action="送到接收查看訂單的Servlet" method="post">
            <input type="hidden" name="action" value="">
            
            <ul class="list-group">
                <li class="list-group-item"><button type="button" class="btn btn-primary">我的商品訂單</button></li>
                <li class="list-group-item"><button type="button" class="btn btn-primary">我的場地租借單</button></li>
              </ul>
          </div>
          <div class="col">
           <h3>追蹤清單</h3>
            <ul class="list-group">
                <li class="list-group-item"><button type="button" class="btn btn-primary">我的追蹤商品</button></li>
                <li class="list-group-item"><button type="button" class="btn btn-primary">我的追蹤場地</button></li>
              </ul>
          </div>
          <div class="col">
           <h3>我的評論</h3>
            <ul class="list-group">
                <li class="list-group-item"><button type="button" class="btn btn-primary">查看商品評論</button></li>
                <li class="list-group-item"><button type="button" class="btn btn-primary">查看場地評論</button></li>
              </ul>
          </div>
          <div class="col">
           <h3>我的優惠券</h3>
            <ul class="list-group">
                <li class="list-group-item"><button type="button" class="btn btn-primary">查看優惠券</button></li>
                
              </ul>
          </div>
        </div>
      </div>
      <br>
    <!-- Close 一般會員中心 -->
    <jsp:include page="/footer.jsp"></jsp:include>
</body>


</html>
