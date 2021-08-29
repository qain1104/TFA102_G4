<%@page import="com.product.model.ProductVO"%>
<%@page import="java.util.stream.Collectors" %>
<%@page import="java.util.*"%>
<%@page import="com.product.model.ProductService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
 	List<ProductVO> shopAllproductList = (List<ProductVO>)session.getAttribute("shopAllproductList");
	pageContext.setAttribute("shopAllproductList", shopAllproductList);
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
	
    <!-- Slick -->
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/assets/css/slick.min.css">
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/assets/css/slick-theme.css">
    
</head>
<body>
<jsp:include page="/header.jsp" flush="true" />	
   <!-- Start Content -->
	<div class="container py-5">
		<div class="row">
			<div class="col-lg-3">
				<h1 class="h2 pb-4">Categories</h1>
				<ul class="list-unstyled templatemo-accordion">
					<li class="pb-3">
						<a class="collapsed d-flex justify-content-between h3 text-decoration-none" href="<%= request.getContextPath() %>/shopping/SportifyShop.do?category=1"> 
						Men 
						</a>
						<a class="collapsed d-flex justify-content-between h3 text-decoration-none" href="<%= request.getContextPath() %>/shopping/SportifyShop.do?category=0"> 
						Women
						</a> 
						<a class="collapsed d-flex justify-content-between h3 text-decoration-none" href="<%= request.getContextPath() %>/shopping/SportifyShop.do?category=2"> 
						Shoes 
						</a> 
						<a class="collapsed d-flex justify-content-between h3 text-decoration-none" href="<%= request.getContextPath() %>/shopping/SportifyShop.do?category=3"> 
						Other 
						</a>
					</li>
				</ul>
			</div>

			<div class="col-lg-9">
				<div class="row">
					<div class="col-md-6"></div>
					<div class="col-md-6 pb-4"></div>
				</div>
				<div class="getmore row" data-fetching="false">
				<jsp:useBean id="productSpecDAO" class="com.productspec.model.ProductSpecJDBCDAO"/>
				<jsp:useBean id="Order_listService" class="com.order_list.model.Order_listService"/>
				<%@ include file="/shop-pages/page1.file" %> 
				<c:forEach var="product" items="${shopAllproductList}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
					<div class="product col-md-4" data-product="${product.productSN}">
						<div class="card mb-4 product-wap rounded-0" style=" height: 460px;">
							<div class="card rounded-0">
								<img class="card-img rounded-0 img-fluid" src="<%=request.getContextPath()%>/ProductImage?productSN=${product.productSN}">
							<div class="card-img-overlay rounded-0 product-overlay d-flex align-items-center justify-content-center">
							<ul class="list-unstyled">
								<li>
									<a class="tracking btn btn-success text-white">
										<i class="far fa-heart"></i>
									</a>
								</li>
								<li>
									<a class="btn btn-success text-white mt-2" href="<%=request.getContextPath()%>/shopping/SportifyShop.do?action=shopsingle&productSN=${product.productSN}">
										<i class="far fa-eye"></i>
									</a>
								</li>
							</ul>
						</div>
					</div>
					<div class="card-body">
						<a href="shop-single.html" class="h3 text-decoration-none">
						${product.productName}
						</a>
						<ul class="w-100 list-unstyled d-flex justify-content-between mb-0">
							<li class="pt-2">
								<span class="product-color-dot color-dot-red float-left rounded-circle ml-1"></span>
								<span class="product-color-dot color-dot-blue float-left rounded-circle ml-1"></span>
								<span class="product-color-dot color-dot-black float-left rounded-circle ml-1"></span>
								<span class="product-color-dot color-dot-light float-left rounded-circle ml-1"></span>
								<span class="product-color-dot color-dot-green float-left rounded-circle ml-1"></span>
							</li>
						</ul>
						<ul class="list-unstyled d-flex justify-content-center mb-1">
							<li>
								<i class="${Order_listService.getProductRate(product.productSN) >= 1 ? 'text-warning' : 'text-muted'} fa fa-star"></i> 
								<i class="${Order_listService.getProductRate(product.productSN) >= 2 ? 'text-warning' : 'text-muted'} fa fa-star"></i> 
								<i class="${Order_listService.getProductRate(product.productSN) >= 3 ? 'text-warning' : 'text-muted'} fa fa-star"></i> 
								<i class="${Order_listService.getProductRate(product.productSN) >= 4 ? 'text-warning' : 'text-muted'} fa fa-star"></i> 
								<i class="${Order_listService.getProductRate(product.productSN) >= 5 ? 'text-warning' : 'text-muted'} fa fa-star"></i>
							</li>
						</ul>
							<p class="text-center mb-0">NTD ${productSpecDAO.getFirstItemPrice(product.productSN)} 起</p>
						</div>
					</div>
				</div>
				</c:forEach>
			</div>
			<div div="row">
            	<ul class="pagination pagination-lg justify-content-end">
                	<%@ include file="/shop-pages/page2.file" %> 
<!--                    	<li class="page-item"> -->
<!--                        	<a class="page-link rounded-0 shadow-sm border-top-0 border-left-0 text-dark" href="#">3</a> -->
<!--                     </li> -->
                </ul>
            </div>
		</div>
	</div>
</div>
		<!-- End Content -->
<jsp:include page="/footer.jsp" flush="true" />

	<!-- Start Script -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js"></script>
    <script src="<%=request.getContextPath() %>/assets/js/jquery-3.6.0.min.js"></script>
    <script src="<%=request.getContextPath() %>/assets/js/jquery-migrate-1.2.1.min.js"></script>
    <script src="<%=request.getContextPath() %>/assets/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <script src="<%=request.getContextPath() %>/assets/js/custom.js"></script>
    <script src="<%=request.getContextPath() %>/assets/js/shop-tracking.js"></script>
    <!-- End Script -->

    <!-- Start Slider Script -->
    <script src="<%=request.getContextPath() %>/assets/js/slick.min.js"></script>
    <script>
        $('#carousel-related-product').slick({
            infinite: true,
            arrows: false,
            slidesToShow: 4,
            slidesToScroll: 3,
            dots: true,
            responsive: [{
                    breakpoint: 1024,
                    settings: {
                        slidesToShow: 3,
                        slidesToScroll: 3
                    }
                },
                {
                    breakpoint: 600,
                    settings: {
                        slidesToShow: 2,
                        slidesToScroll: 3
                    }
                },
                {
                    breakpoint: 480,
                    settings: {
                        slidesToShow: 2,
                        slidesToScroll: 3
                    }
                }
            ]
        });
    </script>

</body>
</html>