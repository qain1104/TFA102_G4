<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*, com.cartList.model.CartListVO, com.cartList.model.CartListService" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Sportify</title>
    <link rel="shortcut icon" href="<%= request.getContextPath() %>/assets/img/logo1.png" type="image/x-icon" />
	<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;200;300;400;500;700;900&display=swap">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/fontawesome.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/templatemo.css">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/custom.css">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/general_order.css">
</head>
<body>
<% 

	response.setHeader("Cache-Control","no-cache");
	response.setHeader("Pragma","no-cache"); 
	response.setDateHeader ("Expires", 0);
		
%>
<jsp:include page="/header.jsp" flush="true" />
   <!-- Start Member Center -->
    <div class="member_center">
        會員中心<span id="trackinglist">商品追蹤清單</span>
    </div>
    <!-- End Memeber Center -->


    <!-- Start Order management -->
    <table class="table table-light merchandise_trackinglist">
        <thead>
          <tr>
            <th scope="col">項目列表</th>
            <th scope="col">商品名稱</th>
            <th scope="col">商品圖片</th>
            <th scope="col">取消追蹤</th>
            <th scope="col">購買</th>
          </tr>
        </thead>
        <tbody>
        <jsp:useBean id="productService" class="com.product.model.ProductService"/>
        <c:forEach var="trackingList" items="${userTracking}" varStatus="item">
          <tr class="${trackingList.pwlSN}" data-sn="${trackingList.pwlSN}">
            <td class="align-middle list_number">${item.count}</td>
            <td class="align-middle productName">${productService.getOneProduct(trackingList.productSN).productName}</td>
            <td class="align-middle productImage"><img src="<%=request.getContextPath() %>/ProductImage?productSN=${trackingList.productSN}" alt="product_image" style="width: 100px;"></td>
            <td class="align-middle following">
                <a class="btn btn-success text-white tracking"><i class="far fa-heart"></i></a>
            </td>
            <td class="align-middle purchasing_page">
                <a class="btn btn-success text-white" href="<%=request.getContextPath()%>/shopping/SportifyShop.do?action=shopsingle&productSN=${trackingList.productSN}"><i class="fas fa-tshirt"></i></a>
            </td>
          </tr>
        </c:forEach>
        </tbody>
    </table>
    <!-- End Order management -->

	<c:if test="${not empty errorMsgs}">
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

<jsp:include page="/footer.jsp" flush="true" />
	<!-- Start Script -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js"></script>
    <script src="<%=request.getContextPath() %>/assets/js/jquery-3.6.0.min.js"></script>
    <script src="<%=request.getContextPath() %>/assets/js/jquery-migrate-1.2.1.min.js"></script>
    <script src="<%=request.getContextPath() %>/assets/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <script src="<%=request.getContextPath() %>/assets/js/templatemo.js"></script>
    <script src="<%=request.getContextPath() %>/assets/js/custom.js"></script>
    <script src="<%= request.getContextPath() %>/assets/js/general_order.js"></script>
    <!-- End Script -->

</body>
</html>