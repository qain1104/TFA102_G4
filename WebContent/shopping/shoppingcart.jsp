<%@page import="com.productspec.model.ProductSpecVO"%>
<%@page import="com.productspec.model.ProductSpecJDBCDAO"%>
<%@page import="com.product.model.ProductVO"%>
<%@page import="com.product.model.ProductJDBCDAO"%>
<%@page import="com.cartList.model.CartListVO"%>
<%@page import="com.cartList.model.CartListService"%>
<%@ page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% 
	response.setHeader("Cache-Control","no-cache");
	response.setHeader("Pragma","no-cache"); 
	response.setDateHeader ("Expires", 0);
	
 	Integer userId = (Integer)session.getAttribute("userId"); // 會員編號
 	
 	if(userId == null){
 		session.setAttribute("location", request.getRequestURI());
 		response.sendRedirect(request.getContextPath()+"/login.jsp");
 		return;
 	}
 	
 	CartListService cartListService = new CartListService();  
	Map<CartListVO, ProductVO> shoppingCartMap =  cartListService.getProductFromSpec(userId);
	session.setAttribute("shoppingCartMap", shoppingCartMap); // 購物車所需的資料
	Map<String, Integer> totalCartList = cartListService.getTotalAmount(userId);
	session.setAttribute("totalCartList", totalCartList); // 計算總金額和總數量

%> 

<!DOCTYPE html>
<html>
<head>
    <title>Sportify</title>
 	
 	<meta charset="BIG5">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=yes">

	<link rel="shortcut icon" href="<%= request.getContextPath() %>/assets/img/logo1.png" type="image/x-icon" />
	<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;200;300;400;500;700;900&display=swap">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/fontawesome.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/templatemo.css">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/custom.css">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/shoppingcart.css">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/paying_delivery.css">	
</head>

<body>
<jsp:include page="/header.jsp" flush="true" />
    <!-- Start Checking status -->
    <div class="container py-5">
        <div class="row">
            <div class="col-lg-3">
                <h1 class="h2 pb-4">購物車</h1> 
            </div>
            <div class="paying container">
                <div class="paying_status row">
                    <div class="status_bar col-3" id="checking_purchasing_list" style="background-color: #59AB6E; color: white; padding: 15px;">確認購物清單</div>
                    <div class="status_bar col-3" id="choosing_method" style="background-color: #f8f9fa; padding: 0 auto; color: black;">選擇付款方式及運送資料</div>
                    <div class="status_bar col-3" id="purchasing_completed" style="background-color: #f8f9fa; padding: 15px;">購物完成</div>
                </div>
            </div>
        </div>
    </div>
    <!-- End Checking status -->


    <!-- Start Checking purchasing data -->
    <section class="container py-5">
        <div class="shopping_list">
            <div class="shopping_row">
                <table class="table">
                    <thead>
                      <tr class="merchandise_property">
                        <th scope="col" class="merchandise_title">商品</th>
                        <th scope="col">商品名稱</th>
                        <th scope="col">尺寸</th>
                        <th scope="col">單價</th>
                        <th scope="col" class="merchandise_quantity">件數</th>
                        <th scope="col">金額</th>
                        <th scope="col">操作</th>
                      </tr>
                    </thead>
                    <tbody>
						<form action="<%= request.getContextPath() %>/shopping/shoppingcart.do" method="POST">
						<jsp:useBean id="productSpecService" class="com.productspec.model.ProductSpecService"/>
						<c:forEach var="cartList" items="${shoppingCartMap}">
						<c:set var="getProductSpec" value="${cartList.key.productSpecId}"/>
						<c:set var="getQuantity" value="${cartList.key.itemQuantity}"/>
						<c:set var="getPrice" value="${productSpecService.getOneProduct(getProductSpec).productPrice}"/>
	                    <tr>
	                        <th scope="row"><img src="<%=request.getContextPath() %>/ProductImage?productSN=${cartList.value.productSN}" class="img-thumbnail" alt="product"><a href="#"></a></th>
	                        <td class="merchandise_name">${cartList.value.productName}</td>
	                        <td class="merchandise_size" id="${getProductSpec}">${productSpecService.getOneProduct(getProductSpec).productSpec}</td>
	                        <td class="merchandise_price">${getPrice}</td>
	                        <td class="merchandise_quantity">
	                            <ul class="list-inline">
	                                <li class="list-inline-item text-right">
	                                    <input type="hidden" name="product-quanity" value="${getQuantity}">
	                                </li>
	                                <li class="list-inline-item"><span class="btn-minu btn btn-success">-</span></li>
	                                <li class="list-inline-item"><span class="badge bg-secondary var-value">${getQuantity}</span></li>
	                                <li class="list-inline-item"><span class="btn-plu btn btn-success">+</span></li>
	                            </ul>
	                        </td>
	                        <td class="merchandise_amount">${getQuantity * getPrice}</td>
	                        <td><button type="button" class="btn btn-outline-success btn-delete" name="action" value="delete">刪除</button></td>
	                     </tr>
	                     </c:forEach>
	                     </form>
                    </tbody>
                </table>
			</div>
            <div class="under_list container">
                <div class="under_list_row row">
                    <div class="total_quantity col-md-2 offset-md-8">共 <strong><span id="morder_quantity">${totalCartList.get("totalQuantity")}</span></strong> 件</div>
                    <div class="total_amount col-md-2 offset-md-8">總金額: <strong><span id="morder_amount">${totalCartList.get("totalAmount")}</span></strong></div>
                </div>
            </div>
        </div>           
    </section>
    <!-- End Checking purchasing data -->

    <!-- Start Button -->
	<div class="button_before_footer">
	    <form action="<%= request.getContextPath() %>/shopping/shoppingcart.do" method="POST">
	        <div class="back_to_shop" style="position: relative; left: -400px; bottom: 35px;">
	            <button type="submit" class="btn btn-success btn-lg" name="action" value="backToShop">繼續購物</button>         
	        </div>
	        <div class="next_step" style="position: relative; right: -400px; bottom: 80px;">
	            <button type="submit" class="btn btn-success btn-lg" name="action" value="nextStep">下一步</button>
	        </div>
	     </form>
	 </div>
    <!-- End Button -->
	<jsp:include page="/footer.jsp" flush="true" />

	<!-- Start Script -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js"></script>
    <script src="<%=request.getContextPath() %>/assets/js/jquery-3.6.0.min.js"></script>
    <script src="<%=request.getContextPath() %>/assets/js/jquery-migrate-1.2.1.min.js"></script>
    <script src="<%=request.getContextPath() %>/assets/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <script src="<%=request.getContextPath() %>/assets/js/templatemo.js"></script>
    <script src="<%=request.getContextPath() %>/assets/js/custom.js"></script>
	<script src="<%= request.getContextPath() %>/assets/js/shoppingcart.js" charset="UTF-8"></script>
    <!-- End Script -->

</body>

</html>