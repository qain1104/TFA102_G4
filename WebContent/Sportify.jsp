<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <title>Sportify</title>
    <meta charset="BIG5">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=yes">
	
	<!-- 網頁logo -->
	<link rel="shortcut icon" href="<%= request.getContextPath() %>/assets/img/logo1.png" type="image/x-icon" />
	
	<!-- 字體 -->
	<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;200;300;400;500;700;900&display=swap">
   
    <!-- fontawesome -->
    <link rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/fontawesome.min.css">
    
    <!-- bootstrap -->
    <link rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/bootstrap.min.css">
    
    <!-- 套版原有的 -->
    <link rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/templatemo.css">
	
	<!-- header -->
	<link rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/custom.css">
	
    <!-- Slick -->
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/assets/css/slick.min.css">
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/assets/css/slick-theme.css">
    
</head>
<body>
	<!-- Header -->
 	<jsp:include page="/header.jsp" flush="true" />	
        <!-- Start Banner Hero -->
        <div id="template-mo-zay-hero-carousel" class="carousel slide" data-bs-ride="carousel">
            <ol class="carousel-indicators">
                <li data-bs-target="#template-mo-zay-hero-carousel" data-bs-slide-to="0" class="active"></li>
                <li data-bs-target="#template-mo-zay-hero-carousel" data-bs-slide-to="1"></li>
                <li data-bs-target="#template-mo-zay-hero-carousel" data-bs-slide-to="2"></li>
            </ol>
            <div class="carousel-inner">
                <div class="carousel-item active">
                    <div class="container">
                        <div class="row p-5">
                            <div class="mx-auto col-md-8 col-lg-6 order-lg-last">
                                <img class="img-fluid" src="./assets/img/banner_img_01.jpg" alt="">
                            </div>
                            <div class="col-lg-6 mb-0 d-flex align-items-center">
                                <div class="text-align-left align-self-center">
                                    <h1 class="h1 text-success">
                                        <b>Zay</b>
                                    	eCommerce
                                    </h1>
                                    <h3 class="h2">Tiny and Perfect eCommerce Template</h3>
                                    <p>
                                        Zay Shop is an eCommerce HTML5 CSS template with latest version of Bootstrap 5 (beta 1). 
                                                                            This template is 100% free provided by
                                        <a rel="sponsored" class="text-success" href="https://templatemo.com" target="_blank">TemplateMo</a>
                                        website. 
                                                                            Image credits go to
                                        <a rel="sponsored" class="text-success" href="https://stories.freepik.com/" target="_blank">Freepik Stories</a>,
                                        <a rel="sponsored" class="text-success" href="https://unsplash.com/" target="_blank">Unsplash</a>
                                        and
                                        <a rel="sponsored" class="text-success" href="https://icons8.com/" target="_blank">Icons 8</a>.
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="carousel-item">
                    <div class="container">
                        <div class="row p-5">
                            <div class="mx-auto col-md-8 col-lg-6 order-lg-last">
                                <img class="img-fluid" src="./assets/img/banner_img_02.jpg" alt="">
                            </div>
                            <div class="col-lg-6 mb-0 d-flex align-items-center">
                                <div class="text-align-left">
                                    <h1 class="h1">Proident occaecat</h1>
                                    <h3 class="h2">Aliquip ex ea commodo consequat</h3>
                                    <p>
                                        You are permitted to use this Zay CSS template for your commercial websites. 
                                                                            You are
                                        <strong>not permitted</strong>
                                        to re-distribute the template ZIP file in any kind of template collection websites.
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="carousel-item">
                    <div class="container">
                        <div class="row p-5">
                            <div class="mx-auto col-md-8 col-lg-6 order-lg-last">
                                <img class="img-fluid" src="./assets/img/banner_img_03.jpg" alt="">
                            </div>
                            <div class="col-lg-6 mb-0 d-flex align-items-center">
                                <div class="text-align-left">
                                    <h1 class="h1">Repr in voluptate</h1>
                                    <h3 class="h2">Ullamco laboris nisi ut
                                    </h3>
                                    <p>
                                        We bring you 100% free CSS templates for your websites. 
                                                                            If you wish to support TemplateMo, please make a small contribution via PayPal or tell your friends about our website. Thank you.
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <a class="carousel-control-prev text-decoration-none w-auto ps-3" href="#template-mo-zay-hero-carousel" role="button" data-bs-slide="prev">
                <i class="fas fa-chevron-left"></i>
            </a>
            <a class="carousel-control-next text-decoration-none w-auto pe-3" href="#template-mo-zay-hero-carousel" role="button" data-bs-slide="next">
                <i class="fas fa-chevron-right"></i>
            </a>
        </div>
        <!-- End Banner Hero -->


        <!-- Start Categories of The Month -->
        <section class="container py-5">
            <div class="row text-center pt-3">
                <div class="col-lg-6 m-auto">
                    <h1 class="h1">商城重點分類</h1>
                    <p>
                        Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia
                                            deserunt mollit anim id est laborum.
                    </p>
                </div>
            </div>
            <div class="row">
                <div class="col-12 col-md-4 p-5 mt-3">
                    <a href="<%= request.getContextPath() %>/shopping/SportifyShop.do?category=0"><img src="<%= request.getContextPath() %>/assets/img/shop_08.jpg" class="rounded-circle img-fluid border"></a>
                    <h5 class="text-center mt-3 mb-3">女裝區</h5>
                    <p class="text-center">
                        <a class="btn btn-success" href="<%= request.getContextPath() %>/shopping/SportifyShop.do?category=1">Go Shop</a>
                    </p>
                </div>
                <div class="col-12 col-md-4 p-5 mt-3">
                    <a href="<%= request.getContextPath() %>/shopping/SportifyShop.do?category=2"><img src="./assets/img/category_img_02.jpg" class="rounded-circle img-fluid border"></a>
                    <h2 class="h5 text-center mt-3 mb-3">鞋子區</h2>
                    <p class="text-center">   
                        <a class="btn btn-success" href="<%= request.getContextPath() %>/shopping/SportifyShop.do?category=2">Go Shop</a>
                    </p>
                </div>
                <div class="col-12 col-md-4 p-5 mt-3">
                    <a href="<%= request.getContextPath() %>/shopping/SportifyShop.do?category=3"><img src="./assets/img/category_img_03.jpg" class="rounded-circle img-fluid border"></a>
                    <h2 class="h5 text-center mt-3 mb-3">配件區</h2>
                    <p class="text-center">
                        <a class="btn btn-success" href="<%= request.getContextPath() %>/shopping/SportifyShop.do?category=3">Go Shop</a>
                    </p>
                </div>
            </div>
        </section>
        <!-- End Categories of The Month -->


        <!-- Start Featured Product -->
        <section class="bg-light">
            <div class="container py-5">
                <div class="row text-center py-3">
                    <div class="col-lg-6 m-auto">
                        <h1 class="h1">Featured Product</h1>
                        <p>
                            Reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.
                                                    Excepteur sint occaecat cupidatat non proident.
                        </p>
                    </div>
                </div>
                <div class="row">
                    <div class="col-12 col-md-4 mb-4">
                        <div class="card h-100">
                            <a href="shop-single.html">
                                <img src="./assets/img/feature_prod_01.jpg" class="card-img-top" alt="...">
                            </a>
                            <div class="card-body">
                                <ul class="list-unstyled d-flex justify-content-between">
                                    <li>
                                        <i class="text-warning fa fa-star"></i>
                                        <i class="text-warning fa fa-star"></i>
                                        <i class="text-warning fa fa-star"></i>
                                        <i class="text-muted fa fa-star"></i>
                                        <i class="text-muted fa fa-star"></i>
                                    </li>
                                    <li class="text-muted text-right">$240.00</li>
                                </ul>
                                <a href="shop-single.html" class="h2 text-decoration-none text-dark">Gym Weight</a>
                                <p class="card-text">
                                    Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sunt in culpa qui officia deserunt.
                                </p>
                                <p class="text-muted">Reviews (24)</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-12 col-md-4 mb-4">
                        <div class="card h-100">
                            <a href="shop-single.html">
                                <img src="./assets/img/feature_prod_02.jpg" class="card-img-top" alt="...">
                            </a>
                            <div class="card-body">
                                <ul class="list-unstyled d-flex justify-content-between">
                                    <li>
                                        <i class="text-warning fa fa-star"></i>
                                        <i class="text-warning fa fa-star"></i>
                                        <i class="text-warning fa fa-star"></i>
                                        <i class="text-muted fa fa-star"></i>
                                        <i class="text-muted fa fa-star"></i>
                                    </li>
                                    <li class="text-muted text-right">$480.00</li>
                                </ul>
                                <a href="shop-single.html" class="h2 text-decoration-none text-dark">Cloud Nike Shoes</a>
                                <p class="card-text">
                                    Aenean gravida dignissim finibus. Nullam ipsum diam, posuere vitae pharetra sed, commodo ullamcorper.
                                </p>
                                <p class="text-muted">Reviews (48)</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-12 col-md-4 mb-4">
                        <div class="card h-100">
                            <a href="shop-single.html">
                                <img src="./assets/img/feature_prod_03.jpg" class="card-img-top" alt="...">
                            </a>
                            <div class="card-body">
                                <ul class="list-unstyled d-flex justify-content-between">
                                    <li>
                                        <i class="text-warning fa fa-star"></i>
                                        <i class="text-warning fa fa-star"></i>
                                        <i class="text-warning fa fa-star"></i>
                                        <i class="text-warning fa fa-star"></i>
                                        <i class="text-warning fa fa-star"></i>
                                    </li>
                                    <li class="text-muted text-right">$360.00</li>
                                </ul>
                                <a href="shop-single.html" class="h2 text-decoration-none text-dark">Summer Addides Shoes</a>
                                <p class="card-text">
                                    Curabitur ac mi sit amet diam luctus porta. Phasellus pulvinar sagittis diam, et scelerisque ipsum lobortis nec.
                                </p>
                                <p class="text-muted">Reviews (74)</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- End Featured Product -->
        
	<!-- Footer -->
	<jsp:include page="/footer.jsp" flush="true" />
	
	<!-- Start Script -->
	
	<!-- fontawesom -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js"></script>
    
    <!-- jQuery -->
    <script src="<%=request.getContextPath() %>/assets/js/jquery-3.6.0.min.js"></script>
    <script src="<%=request.getContextPath() %>/assets/js/jquery-migrate-1.2.1.min.js"></script>
    
    <!-- bootstrap -->
    <script src="<%=request.getContextPath() %>/assets/js/bootstrap.bundle.min.js"></script>
    <!-- 應該跟上一個是同樣的檔 -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    
    <!-- 套版原有的 -->
    <script src="<%=request.getContextPath() %>/assets/js/templatemo.js"></script>
    
    <!-- header -->
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