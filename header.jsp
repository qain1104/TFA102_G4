<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>

<head>
    <title>Sportify</title>
    <meta charset="BIG5">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=yes">
	
	<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;200;300;400;500;700;900&display=swap">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/fontawesome.min.css">
	<link rel="shortcut icon" href="<%= request.getContextPath() %>/assets/img/logo1.png" type="image/x-icon" />
    <link rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/templatemo.css">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/custom.css">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/fontawesome.min.css">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <!-- Load fonts style after rendering the layout styles -->

    <!-- Slick -->
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/assets/css/slick.min.css">
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/assets/css/slick-theme.css">
    
</head>
<!--
    
TemplateMo 559 Zay Shop

https://templatemo.com/tm-559-zay-shop

-->

<body>
	    <!-- Start Top Nav -->
    <nav class="navbar navbar-expand-lg bg-dark navbar-light d-none d-lg-block" id="templatemo_nav_top">
        <div class="container text-light">
            <div class="w-100 d-flex justify-content-between">
                <div>
                    <i class="fa fa-envelope mx-2"></i>
                    <a class="navbar-sm-brand text-light text-decoration-none" href="mailto:service@tibame.com">service@tibame.com</a>
                    <i class="fa fa-phone mx-2"></i>
                    <a class="navbar-sm-brand text-light text-decoration-none" href="tel:+886227120589">+886227120589</a>
                </div>
                <div>
                    <a class="text-light" href="https://www.facebook.com/TibaMe/" target="_blank" rel="sponsored"><i class="fab fa-facebook-f fa-sm fa-fw me-2"></i></a>
                    <a class="text-light" href="https://www.instagram.com/tibame_e_learning/?fbclid=IwAR2IJdb5mK6SkzM-t8biQl0b7a8IdTFvMHC8GXSfb1-mBl2VhKz0_41BxoQ" target="_blank"><i class="fab fa-instagram fa-sm fa-fw me-2"></i></a>
                </div>
            </div>
        </div>
    </nav>
    <!-- Close Top Nav -->


    <!-- Header -->
    <nav class="navbar navbar-expand-lg navbar-light shadow">
        <div class="container d-flex justify-content-between align-items-center">

            <a class="navbar-brand text-success logo h1 align-self-center" href="index.html">
                <img id="logo_img" src="<%=request.getContextPath() %>/assets/img/logo.png" alt="sportify logo">
            </a>

            <button class="navbar-toggler border-0" type="button" data-bs-toggle="collapse" data-bs-target="#templatemo_main_nav" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="align-self-center collapse navbar-collapse flex-fill  d-lg-flex justify-content-lg-between" id="templatemo_main_nav">
                <div class="flex-fill">
                    <ul class="nav navbar-nav d-flex justify-content-between mx-lg-auto">
                        <li class="nav-item">
                            <a class="nav-link" href="index.html">首頁</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="shop.html">商城</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="shop.html">場地租借</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="shop.html">論壇/揪團</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="shop.html">最新消息</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="about.html">關於我們</a>
                        </li>
                    </ul>
                </div>
                <div class="navbar align-self-center d-flex">
                    <div class="d-lg-none flex-sm-fill mt-3 mb-4 col-7 col-sm-auto pr-3">
                        <div class="input-group">
                            <input type="text" class="form-control" id="inputMobileSearch" placeholder="Search ...">
                            <div class="input-group-text">
                                <i class="fa fa-fw fa-search"></i>
                            </div>
                        </div>
                    </div>
                    <a class="nav-icon d-none d-lg-inline" href="#" data-bs-toggle="modal" data-bs-target="#templatemo_search">
                        <i class="fa fa-fw fa-search text-dark mr-2"></i>
                    </a>
                    <a class="nav-icon position-relative text-decoration-none" href="${userId != null ? 'shoppingcart.jsp' : '/login.jsp'} ">
                        <i class="fa fa-fw fa-cart-arrow-down text-dark mr-1"></i>
                        <span class="position-absolute top-0 left-100 translate-middle badge rounded-pill bg-light text-dark"></span>
                    </a>
                    <a class="nav-icon position-relative text-decoration-none" href="#">
                        <div id="abc"><i class="fa fa-fw fa-user text-dark mr-3"></i></div>
                        <span class="position-absolute top-0 left-100 translate-middle badge rounded-pill bg-light text-dark"></span>
                        <div class="choose_list -none">
                            <span class="member_list" id="list_1"><a href="#">登入</a></span>
                            <span class="member_list" id="list_2"><a href="#">個人資料</a></span>
                            <span class="member_list" id="list_3"><a href="#">我的商品訂單</a></span>
                            <span class="member_list" id="list_4"><a href="#">我的場地訂單</a></span>
                            <span class="member_list" id="list_4"><a href="#">我的評論</a></span>
                        </div>
                    </a>    
                </div>
            </div>
        </div>
    </nav>
    <!-- Close Header -->

    <!-- Modal -->
    <div class="modal fade bg-white" id="templatemo_search" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg" role="document">
            <div class="w-100 pt-1 mb-5 text-right">
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form action="" method="get" class="modal-content modal-body border-0 p-0">
                <div class="input-group mb-2">
                    <input type="text" class="form-control" id="inputModalSearch" name="q" placeholder="Search ...">
                    <button type="submit" class="input-group-text bg-success text-light">
                        <i class="fa fa-fw fa-search text-white"></i>
                    </button>
                </div>
            </form>
        </div>
    </div>

