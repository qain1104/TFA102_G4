<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="Big5"%>
<%@ page import="com.CorpUser.model.*"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/cssLink.jsp"></jsp:include>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<%

	CorpUserVO currentC =  (CorpUserVO) session.getAttribute("currentC");
	Integer corpUserId = (Integer) session.getAttribute("corpUserId");
	if(currentC == null){
		session.setAttribute("location", request.getRequestURI());
		response.sendRedirect(request.getContextPath()+"/login.jsp");
		return;
	}
%>

<body>
	<jsp:include page="/header.jsp" flush="true" />
	<!-- 企業會員中心本體 -->
	<div class="container pb-5 pt-5">
		<h1 class=" text-success fw-bold">會員中心 企業會員</h1>

		<div class="row mt-5">
			<div class="col text-center">
				<h3>企業資料</h3>
				<form
					action="<%=request.getContextPath()%>/corpUser/CorpUserServlet.do"
					method="post">
					<input type="hidden" name="action" value="getOne_For_Display">
					<ul class="list-group">
						<li class="list-group-item"><button type="submit"
								class="btn btn-success">資料修改</button></li>
					</ul>
				</form>
			</div>
			<div class="col text-center">
				<h3>商品管理</h3>
				<form action="送到接收查看訂單的Servlet" method="post">
					<input type="hidden" name="action" value="">

					<ul class="list-group">
						<li class="list-group-item"><button type="button"
								class="btn btn-success">商品上下架</button></li>
						<li class="list-group-item"><button type="button"
								class="btn btn-success">修改商品資訊</button></li>
						<li class="list-group-item"><button type="button"
								class="btn btn-success">查看商品評論</button></li>
					</ul>
			</div>
			<div class="col text-center">
				<h3>租借管理</h3>
				<ul class="list-group">
					<li class="list-group-item"><a class="btn btn-success" href="<%=request.getContextPath()%>/venue/addVenue.jsp">新增場地 </a></li>
					<li class="list-group-item"><a class="btn btn-success" href="<%=request.getContextPath()%>/venue/listAllVenue.jsp">查看場地 </a></li>
<!-- 					<li class="list-group-item"><button type="button" -->
<!-- 							class="btn btn-success">租借單管理</button></li> -->
				</ul>
			</div>
			<div class="col text-center">
				<h3>我的評論</h3>
				<ul class="list-group">
					<li class="list-group-item"><button type="button"
							class="btn btn-success">查看商品評論</button></li>
					<li class="list-group-item"><button type="button"
							class="btn btn-success">查看場地評論</button></li>
				</ul>
			</div>
<!-- 			<div class="col text-center"> -->
<!-- 				<h3>我的審核</h3> -->
<!-- 				<ul class="list-group"> -->
<!-- 					<li class="list-group-item"><button type="button" -->
<!-- 							class="btn btn-success">查看商品審核</button></li> -->
<!-- 					<li class="list-group-item"><button type="button" -->
<!-- 							class="btn btn-success">查看場地審核</button></li> -->
<!-- 				</ul> -->
<!-- 			</div> -->
		</div>
	</div>
	<!-- Close 一般會員中心 -->
	<jsp:include page="/footer.jsp"></jsp:include>
	  <jsp:include page="/jsLink.jsp" flush="true"/>
</body>


</html>
