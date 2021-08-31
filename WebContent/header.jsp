<%@page import="java.util.List"%>
<%@page import="com.cartList.model.CartListService"%>
<%@page import="com.cartList.model.CartListVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!--
    
TemplateMo 559 Zay Shop
https://templatemo.com/tm-559-zay-shop
-->
<!-- Start Top Nav -->
<%
	Integer userId = (Integer) session.getAttribute("userId");
	if (userId != null) {
		CartListService cartListService = new CartListService();
		List<CartListVO> cartList = cartListService.getCartList(userId);
		if (cartList == null) {
			session.setAttribute("headerCartList", 0);
		} else {
			session.setAttribute("headerCartList", cartList.size());
		}
	} else {
		List<CartListVO> cartList = (List<CartListVO>) session.getAttribute("cartList");
		if (cartList == null) {
			session.setAttribute("headerCartList", 0);
		} else {
			session.setAttribute("headerCartList", cartList.size());
		}
	}
%>
<nav
	class="navbar navbar-expand-lg bg-dark navbar-light d-none d-lg-block"
	id="templatemo_nav_top">
	<div class="container text-light">
		<div class="w-100 d-flex justify-content-between">
			<div>
				<i class="fa fa-envelope mx-2"></i> <a
					class="navbar-sm-brand text-light text-decoration-none"
					href="mailto:service@tibame.com">service@tibame.com</a> <i
					class="fa fa-phone mx-2"></i> <a
					class="navbar-sm-brand text-light text-decoration-none"
					href="tel:+886227120589">+886227120589</a>
			</div>
			<div>
				<a class="text-light" href="https://www.facebook.com/TibaMe/"
					target="_blank" rel="sponsored"><i
					class="fab fa-facebook-f fa-sm fa-fw me-2"></i></a> <a
					class="text-light"
					href="https://www.instagram.com/tibame_e_learning/?fbclid=IwAR2IJdb5mK6SkzM-t8biQl0b7a8IdTFvMHC8GXSfb1-mBl2VhKz0_41BxoQ"
					target="_blank"><i class="fab fa-instagram fa-sm fa-fw me-2"></i></a>
			</div>
		</div>
	</div>
</nav>
<!-- Close Top Nav -->


<!-- Header -->
<nav class="navbar navbar-expand-lg navbar-light shadow">
	<div
		class="container d-flex justify-content-between align-items-center">

		<a class="navbar-brand text-success logo h1 align-self-center"
			href="<%=request.getContextPath()%>/Sportify.jsp"> <img
			id="logo_img" src="<%=request.getContextPath()%>/assets/img/logo.png"
			alt="sportify logo">
		</a>

		<button class="navbar-toggler border-0" type="button"
			data-bs-toggle="collapse" data-bs-target="#templatemo_main_nav"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div
			class="align-self-center collapse navbar-collapse flex-fill  d-lg-flex justify-content-lg-between"
			id="templatemo_main_nav">
			<div class="flex-fill">
				<ul class="nav navbar-nav d-flex justify-content-between mx-lg-auto">
					<li class="nav-item"><a class="nav-link"
						href="<%=request.getContextPath()%>/Sportify.jsp">首頁</a></li>
					<li class="nav-item"><a class="nav-link"
						href="<%=request.getContextPath()%>/shopping/SportifyShop.do?action=shop">商城</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="">場地租借</a>

					</li>
					<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/article/article.jsp">論壇</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/sportsGroup/sportsGroup.jsp">揪團</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="about.html">關於我們</a>
					</li>
				</ul>
			</div>
			<div class="navbar align-self-center d-flex">
				<div class="d-lg-none flex-sm-fill mt-3 mb-4 col-7 col-sm-auto pr-3">
					<div class="input-group">
						<input type="text" class="form-control" id="inputMobileSearch"
							placeholder="Search ...">
						<div class="input-group-text">
							<i class="fa fa-fw fa-search"></i>
						</div>
					</div>
				</div>
				<a class="nav-icon d-none d-lg-inline" href="#"
					data-bs-toggle="modal" data-bs-target="#templatemo_search"> <i
					class="fa fa-fw fa-search text-dark mr-2"></i>
				</a> <a class="nav-icon position-relative text-decoration-none" href="<%= request.getContextPath() %>/shopping/shoppingcart.jsp">
					<i class="fa fa-fw fa-cart-arrow-down text-dark mr-1"></i> <span
					class="position-absolute top-0 left-100 translate-middle badge rounded-pill bg-light text-dark">${headerCartList}</span>
				</a> <a class="nav-icon position-relative text-decoration-none" href="#">
					<div id="abc">
						<i class="fa fa-fw fa-user text-dark mr-3"></i>
					</div> <span
					class="position-absolute top-0 left-100 translate-middle badge rounded-pill bg-light text-dark"></span>
					<div class="choose_list -none">
						<c:choose>
							<c:when test="${empty currentG && empty currentC}">
								<span class="member_list" id="list_1"><a
									href="<%=request.getContextPath()%>/login.jsp">登入/註冊</a></span>
							</c:when>
							<c:when test="${!empty currentG && empty currentC}">
								<span class="member_list" id="list_2"><a
									href="<%=request.getContextPath()%>/generalUser/generalUserMainPage.jsp">會員中心</a></span>
								<span class="member_list" id="list_1"><a
									href="<%=request.getContextPath()%>/webManager/WebManagerServlet.do?action=logout">登出</a></span>
							</c:when>
							<c:otherwise>
								<span class="member_list" id="list_2"><a
									href="<%=request.getContextPath()%>/corpUser/corpUserMainPage.jsp">會員中心</a></span>
								<span class="member_list" id="list_1"><a
									href="<%=request.getContextPath()%>/webManager/WebManagerServlet.do?action=logout">登出</a></span>
							</c:otherwise>


						</c:choose>
					</div>
				</a>
			</div>
		</div>
	</div>
</nav>
<!-- Close Header -->

<!-- Modal -->
<div class="modal fade bg-white" id="templatemo_search" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg" role="document">
		<div class="w-100 pt-1 mb-5 text-right">
			<button type="button" class="btn-close" data-bs-dismiss="modal"
				aria-label="Close"></button>
		</div>
		<form action="<%=request.getContextPath()%>/shopping/SportifyShop.do"
			method="POST" class="modal-content modal-body border-0 p-0">
			<div class="input-group mb-2">
				<input type="text" class="form-control" id="inputModalSearch"
					name="query" placeholder="想買什麼商品嗎?">
				<button type="submit" class="input-group-text bg-success text-light"
					name="action" value="search">
					<i class="fa fa-fw fa-search text-white"></i>
				</button>
			</div>
		</form>
	</div>
</div>