<%@page import="com.product.model.ProductVO"%>
<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*, com.cartList.model.CartListVO, com.cartList.model.CartListService, com.morder.model.MorderVO" %>
<% 
	response.setHeader("Cache-Control","no-cache");
	response.setHeader("Pragma","no-cache"); 
	response.setDateHeader ("Expires", 0);
	
 	Integer userId = (Integer)session.getAttribute("userId"); 
//	Integer userId = new Integer(1003);
 	CartListService cartListService = new CartListService(); 
	Map<CartListVO, ProductVO> shoppingCartMap = cartListService.getProductFromSpec(userId);
	session.setAttribute("shoppingCartMap", shoppingCartMap);	
	Map<String, Integer> totalCartList = cartListService.getTotalAmount(userId);
	session.setAttribute("totalCartList", totalCartList);
	MorderVO morder = (MorderVO)request.getAttribute("morder");
	String couponValue = (String)request.getAttribute("couponValue");
%> 


<!DOCTYPE html>
<html lang="en">
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
            <div class="col-lg-4">
                <h1 class="h2 pb-4">付款方式及運送資料</h1> 
            </div>
            <div class="paying container">
                <div class="paying_status row">
                    <div class="status_bar col-3" id="checking_purchasing_list" style="background-color: #f8f9fa; padding: 15px; color: black;">確認購物清單</div>
                    <div class="status_bar col-3" id="choosing_method" style="background-color: #59AB6E; color: white; padding: 0 auto;">選擇付款方式及運送資料</div>
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
                      </tr>
                    </thead>
                    <tbody>
                    
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
                        	<td class="merchandise_quantity" style="left: 50px;">
                            	<ul class="list-inline">
                                	<li class="list-inline-item text-right">
                                    	<input type="hidden" name="product-quanity" value="${getQuantity}">
                                	</li>
                                	<li class="list-inline-item"><span class="badge bg-secondary var-value">${getQuantity}</span></li>
                            	</ul>
                        	</td>
                        	<td class="merchandise_amount">${getQuantity * getPrice}</td>
                      	</tr>
                      </c:forEach>
                    </tbody>
                  </table>
            </div>
            <div class="check_paying_delivery">
                <form class="row g-3 purchasing_data" action="<%= request.getContextPath() %>/order/Morder.do" method="POST">
                    <div class="list_block">
                        	訂購人資料
                    </div>
                    <div class="col-md-3">
                        <label for="inputBuyer" class="form-label">訂購人</label><span style="color:red">${errorMsgs.userName}</span>
                        <input type="text" class="form-control" id="inputBuyer" name="userName" value="${currentG.userName}" readonly="true">
                    </div>
                    <div class="col-md-3 offset-md-1">
                        <label for="inputPhone" class="form-label">聯絡電話</label><span style="color:red">${errorMsgs.phone}</span>
                        <input type="text" class="form-control" id="inputPhone" name="phone" value="${currentG.phone}" readonly="true">
                    </div>
                    <div class="col-10">
                        <label for="inputAddress" class="form-label">地址</label><span style="color:red">${errorMsgs.address}</span>
                        <input type="text" class="form-control" id="inputAddress" name="address" value="${currentG.address}" readonly="true">
                    </div>
                    <div class="list_block">
                       	 配送方式
                    </div><span style="color:red">${errorMsgs.deliveyType}</span>
                    <fieldset class="row mb-3 delivery_type">
                        <div class="col-sm-5">
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="deliveryType" id="home_delivery" value="14001" checked <c:if test="${morder.orderDeliveyTypeId == '14001'}">checked</c:if>>   
                                <label class="form-check-label" for="home_delivery">                                              
                                  	宅配
                                </label>
                            </div>
<!--                             <div class="form-check offset-sm-3"> -->
<%--                                 <input class="form-check-input" type="radio" name="deliveryType" id="convenience_store" value="14002" <c:if test="${morder.orderDeliveyTypeId == '14002'}">checked</c:if>> --%>
<!--                                 <label class="form-check-label" for="convenience_store"> -->
<!--                                   	超商取貨 -->
<!--                                 </label> -->
<!--                                 <button type="button" class="btn btn-success pick -none" style="color:white;" onclick="showModal()">選取門市</button> -->
<!--                             </div> -->
                        </div>
                    </fieldset>
                    <div class="list_block receiver">
                        	收貨人資料
                    </div><span style="color:red">${errorMsgs.deliveyType}</span>
                    <div class="same_buyer">
                        <input class="form-check-input" type="checkbox" id="same_as_buyer" name="sameAsBuyer" value="1" <c:if test="${param.sameAsBuyer == '1'}">checked</c:if>>
                        <label class="form-check-label" for="same_as_buyer">
                           	 同訂購人資料
                        </label>
                    </div>
                    <div class="receiver_data home">
                        <div class="col-md-3">
                            <label for="inputReceiver" class="form-label">收貨人</label>
                            <input type="text" class="form-control" id="inputReceiver" name="receiver" value="${morder.receiver}">
                        </div>
                        <div class="col-md-3 offset-md-1">
                            <label for="inputReceiverPhone" class="form-label">聯絡電話</label>
                            <input type="text" class="form-control" id="inputReceiverPhone" name="receiverPhone" value="${morder.receiverPhone}">
                        </div>
                        <div class="col-10">
                            <label for="inputReceiverAddress" class="form-label">收貨地址</label>
                            <input type="text" class="form-control" id="inputReceiverAddress" name="receiverAddress" value="${morder.receiverAddress}">
                        </div>
                    </div> 
                    <div style="color:red">${errorMsgs.receiver}</div>
                    <div style="color:red"> ${errorMsgs.receiverPhone} </div>
                    <div style="color:red"> ${errorMsgs.receiverAddress} </div>
                    <div style="color:red"> ${errorMsgs.storeId} </div>
                    <div style="color:red"> ${errorMsgs.storeName} </div>
                    <div style="color:red"> ${errorMsgs.storeAddress} </div>
                    <div class="list_block">
                       	 付款方式
                    </div>
                    <fieldset class="row mb-3 paying_type">
                        <div class="col-sm-5">
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="paying" id="cash" value="0" checked <c:if test="${morder.orderDeliveyTypeId == '0'}">checked</c:if>>
                                <label class="form-check-label" for="cash">
                                    	貨到付款
                                </label>
                            </div>
                            <div class="form-check  offset-sm-3">
                            <input class="form-check-input" type="radio" name="paying" id="credit_card" value="1" <c:if test="${morder.orderDeliveyTypeId == '1'}">checked</c:if>>
                            <label class="form-check-label" for="credit_card">
                                	信用卡
                            </label>
                          </div>
                        </div>
                    </fieldset>
                    <jsp:useBean id="Order_delivery_typeDAO" class="com.order_delivery_type.model.Order_delivery_typeService"/>
                    <div class="under_list container">
                        <div class="under_list_row row">
                            <div class="coupon col-md-4 offset-md-3">優惠券編號: <input type="text" name="couponValue" id="coupon" value="${couponValue}"></div>
                            <div class="total_quantity col-md-2 offset-md-1">共 <strong><span id="morder_quantity">${totalCartList.get("totalQuantity")}</span></strong> 件</div>
                            <div class="delivery_fee col-md-2 offset-md-8">運費: <strong><span id="order_delivery_type">${Order_delivery_typeDAO.getOneType(14001).deliveryFee}</span></strong></div>
                            <div class="total_amount col-md-2 offset-md-8">總金額: <strong><span id="morder_amount">${totalCartList.get("totalAmount") + Order_delivery_typeDAO.getOneType(14001).deliveryFee}</span></strong></div>
                        	<div style="color:red"> ${errorMsgs.couponValue} </div>
                        	<div style="color:red"> ${errorMsgs.addError} </div>
                        </div>
                    </div>
                    <div class="button_before_footer">
                        <div class="back_to_cart">
                            <button class="btn btn-success btn-lg" name="action" value="backToCart">上一步</button>         
                        </div>
                        <div class="next_step">
                            <button class="btn btn-success btn-lg" name="action" value="payingProcess">下一步</button>
                        </div>
                    </div>             
                </form>
            </div>
        </div>        
    </section>
    <!-- End Checking purchasing data -->
    
	<jsp:include page="/footer.jsp" flush="true" />
	<!-- Start Script -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js"></script>
    <script src="<%=request.getContextPath() %>/assets/js/jquery-3.6.0.min.js"></script>
    <script src="<%=request.getContextPath() %>/assets/js/jquery-migrate-1.2.1.min.js"></script>
    <script src="<%=request.getContextPath() %>/assets/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <script src="<%=request.getContextPath() %>/assets/js/templatemo.js"></script>
    <script src="<%=request.getContextPath() %>/assets/js/custom.js"></script>
    <script src="<%= request.getContextPath() %>/assets/js/paying_delivery.js" charset="UTF-8"></script>
    <!-- End Script -->

</body>
</html>