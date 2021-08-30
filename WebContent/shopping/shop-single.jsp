<%@page import="com.order_list.model.Order_listVO"%>
<%@page import="com.productspec.model.ProductSpecVO"%>
<%@page import="com.product.model.ProductVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*, com.cartList.model.CartListVO, com.cartList.model.CartListService" %>
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
	
    <!-- Slick -->
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/assets/css/slick.min.css">
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/assets/css/slick-theme.css">
    
</head>
<body>
<% 
	response.setHeader("Cache-Control","no-cache");
	response.setHeader("Pragma","no-cache"); 
	response.setDateHeader ("Expires", 0); 
	session.setAttribute("location", request.getRequestURI());
	
	ProductVO shopSingleProduct = (ProductVO)session.getAttribute("shopSingleProduct");
	List<ProductSpecVO> specList = (List<ProductSpecVO>)session.getAttribute("specList");
	Integer shopSinglePrice = (Integer)session.getAttribute("shopSinglePrice");
	List<Integer> shopSingleImageSN = (List<Integer>)session.getAttribute("shopSingleImageSN");
	List<Order_listVO> rateReview = (List<Order_listVO>)session.getAttribute("rateReview");
	
	pageContext.setAttribute("shopSingleProduct", shopSingleProduct); // 從產品陳覽頁面所帶過來的ProductVO
	pageContext.setAttribute("specList", specList); // Controller送過來的產品明細清單
	pageContext.setAttribute("shopSinglePrice", shopSinglePrice); // Controller送過來的產品價錢
	pageContext.setAttribute("shopSingleImageSN", shopSingleImageSN); // Controller送過來的產品照片編號
	pageContext.setAttribute("rateReview", rateReview); // 該產品的評分和評論
	
	Integer itemQuantity = (Integer)request.getAttribute("itemQuantity"); // 訂購選取的數量
%>
<jsp:include page="/header.jsp" flush="true" />	
    <!-- Open Content -->
    <section class="bg-light">
        <div class="container pb-5">
            <div class="row">
                <div class="col-lg-5 mt-5">
                    <div class="card mb-3">
                        <img class="card-img img-fluid" src="<%=request.getContextPath()%>/ProductImage?productSN=${shopSingleProduct.productSN}" alt="Card image cap" id="product-detail">
                    </div>
                    <div class="row">
                    
                        <!--Start Controls-->
                        <div class="col-1 align-self-center">
                            <a href="#multi-item-example" role="button" data-bs-slide="prev">
                                <i class="text-dark fas fa-chevron-left"></i>
                                <span class="sr-only">Previous</span>
                            </a>
                        </div>
                        <!--End Controls-->
                        
                        <!--Start Carousel Wrapper-->
                        <div id="multi-item-example" class="col-10 carousel slide carousel-multi-item" data-bs-ride="carousel">
                            <!--Start Slides-->
                            <div class="carousel-inner product-links-wap" role="listbox">
                                <!--First slide-->
                                <div class="carousel-item active">
                                    <div class="row">
                                    <c:forEach var="imageSN" items="${shopSingleImageSN}">
                                        <div class="col-4">
                                            <a href="#">
                                                <img class="card-img img-fluid" src="<%=request.getContextPath()%>/AllProductImage?productImageSN=${imageSN}" alt="Product Image 1">
                                            </a>
                                        </div>
                                     </c:forEach>
                                    </div>
                                </div>
                                <!--/.First slide-->
                               
                            </div>
                            <!--End Slides-->
                        </div>
                        
                        <!--End Carousel Wrapper-->
                        <!--Start Controls-->
                        <div class="col-1 align-self-center">
                            <a href="#multi-item-example" role="button" data-bs-slide="next">
                            <i class="text-dark fas fa-chevron-right"></i>
                                <span class="sr-only">Next</span>
                            </a>
                        </div>
                        <!--End Controls-->
                    </div>
                </div>
                <!-- col end -->
                <div class="col-lg-7 mt-5">
                    <div class="card">
                        <div class="card-body">
                            <h1 class="h2" data-product="${shopSingleProduct.productSN}">${shopSingleProduct.productName}</h1>
                            <p class="productPrice h3 py-2">NTD ${shopSinglePrice}</p>
                            <ul class="list-inline">
                                <li class="list-inline-item">
                                    <h6>Brand:</h6>
                                </li>
                                <li class="list-inline-item">
                                    <p class="text-muted"><strong>${shopSingleProduct.productBrand}</strong></p>
                                </li>
                            </ul>

                            <h6>Description:</h6>
                            <p>${shopSingleProduct.productDetail}</p>


                            <form action="<%= request.getContextPath() %>/shopping/shoppingcart.do" method="POST" id="shoppingdata">
                                <input type="hidden" name="productSN" value="${shopSingleProduct.productSN}">
                                <div class="row">
                                    <div class="col-auto">
                                        <ul class="list-inline pb-3">
                                            <li class="list-inline-item">Size :
                                                <input type="hidden" name="productSpecId" id="product-size" value="">
                                            </li>
                                            <c:forEach var="productSpec" items="${specList}">
                                            <li class="list-inline-item"><span class="btn btn-success btn-size" data-spec="${productSpec.productSpecId}">${productSpec.productSpec}</span></li>
                                            </c:forEach>
                                        </ul>
                                    </div>
                                    <div class="col-auto">
                                        <ul class="list-inline pb-3">
                                            <li class="list-inline-item text-right">
                                                Quantity
                                                <input type="hidden" name="itemQuantity" id="product-quanity" value="<%= itemQuantity == null ? 1 : itemQuantity %>">
                                            </li>
                                            <li class="list-inline-item"><span class="btn btn-success" id="btn-minus">-</span></li>
                                            <li class="list-inline-item"><span class="badge bg-secondary" id="var-value"><%= itemQuantity == null ? 1 : itemQuantity %></span></li>
                                            <li class="list-inline-item"><span class="btn btn-success" id="btn-plus">+</span></li>
                                        </ul>
                                    </div>
                                </div>
                                <div class="row pb-3">
                                    <div class="col d-grid">
                                        <button type="submit" class="btn btn-success btn-lg" name="action" value="buy">Buy</button>
                                    </div>
                                    <div class="col d-grid">
                                        <button type="submit" class="btn btn-success btn-lg" name="action" value="addtocart">Add To Cart</button>
                                    </div>
                                </div>
                            </form>
							<c:if test="${not empty errorMsgs}">
								<ul>
									<c:forEach var="message" items="${errorMsgs}">
										<li style="color: red">${message}</li>
									</c:forEach>
								</ul>
							</c:if>
						</div>
                    </div>
                    <div class="accordion" id="accordionExample" style="margin: 10px;">
					  <div class="accordion-item ">
					    <h2 class="accordion-header" id="headingOne">
					      <button class="accordion-button btn btn-success" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne" style="color: white;">
					        	看看其他人的評論
					      </button>
					    </h2>
					    <c:forEach var="rateReview" items="${rateReview}">
					    <div id="collapseOne" class="accordion-collapse collapse" aria-labelledby="headingOne" data-bs-parent="#accordionExample" style="background-color: white; border: grey 1px solid;">
					      <div class="accordion-body">
					      		<ul class="w-100 list-unstyled d-flex justify-content-between mb-0">
									<li class="pt-2">
										<span class="product-color-dot color-dot-red float-left rounded-circle ml-1"></span>
										<span class="product-color-dot color-dot-blue float-left rounded-circle ml-1"></span>
										<span class="product-color-dot color-dot-black float-left rounded-circle ml-1"></span>
										<span class="product-color-dot color-dot-light float-left rounded-circle ml-1"></span>
										<span class="product-color-dot color-dot-green float-left rounded-circle ml-1"></span>
									</li>
								</ul>
								<ul class="list-unstyled d-flex justify-content mb-1">
									<li>
										<i class="${rateReview.productRate >= 1 ? 'text-warning' : 'text-muted'} fa fa-star"></i> 
										<i class="${rateReview.productRate >= 2 ? 'text-warning' : 'text-muted'} fa fa-star"></i> 
										<i class="${rateReview.productRate >= 3 ? 'text-warning' : 'text-muted'} fa fa-star"></i> 
										<i class="${rateReview.productRate >= 4 ? 'text-warning' : 'text-muted'} fa fa-star"></i> 
										<i class="${rateReview.productRate >= 5 ? 'text-warning' : 'text-muted'} fa fa-star"></i>
									</li>
								</ul>
								<p><strong>${rateReview.productFeedback}</strong></p>	
					      </div>
					    </div>
					    </c:forEach>
					  </div>
					</div>  
                </div>
            </div>
        </div>
    </section>
    <!-- Close Content -->

    <!-- Start Article -->
    <section class="py-5">
        <div class="container">
            <div class="row text-left p-2 pb-3">
                <h4>您或許喜歡</h4>
            </div>

            <!--Start Carousel Wrapper-->
            <div id="carousel-related-product">
				<c:forEach var="related" items="${relatedMap}">
                <div class="p-2 pb-3">
                    <div class="product-wap card rounded-0" style=" height: 430px;">
                        <div class="card rounded-0">
                            <img class="card-img rounded-0 img-fluid" src="<%=request.getContextPath()%>/ProductImage?productSN=${related.key.productSN}">
                            <div class="card-img-overlay rounded-0 product-overlay d-flex align-items-center justify-content-center">
                            </div>
                        </div>
                        <div class="card-body">
                            <a href="<%=request.getContextPath()%>/shopping/SportifyShop.do?action=shopsingle&productSN=${related.key.productSN}" class="h3 text-decoration-none">${related.key.productName}</a>
                            <p class="text-center mb-0">NTD ${related.value} 起</p>
                        </div>
                    </div>
                </div>
                </c:forEach>
            </div>
       </div>
    </section>
    <!-- End Article -->
	<jsp:include page="/footer.jsp" flush="true" />
	
	<!-- Start Script -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js"></script>
    <script src="<%=request.getContextPath() %>/assets/js/jquery-3.6.0.min.js"></script>
    <script src="<%=request.getContextPath() %>/assets/js/jquery-migrate-1.2.1.min.js"></script>
    <script src="<%=request.getContextPath() %>/assets/js/bootstrap.bundle.min.js"></script>
    <script src="<%=request.getContextPath() %>/assets/js/templatemo.js"></script>
    <script src="<%=request.getContextPath() %>/assets/js/custom.js"></script>
    <!-- End Script -->

    <!-- Start Slider Script -->
    <script src="<%= request.getContextPath() %>/assets/js/slick.min.js"></script>
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