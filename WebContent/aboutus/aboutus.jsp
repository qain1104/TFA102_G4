<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<title>Sportify - About Page</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="apple-touch-icon" href="assets/img/logoicon.png">
<link rel="shortcut icon" type="image/x-icon"
	href="<%=request.getContextPath()%>/assets/img/favicon.ico">

<!-- 介面排版用的 -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/bootstrap.min.css">
<!-- 不知道的東西 -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/templatemo.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/custom.css">

<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;200;300;400;500;700;900&display=swap">
<!-- 匯入圖片 -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/fontawesome.min.css">


</head>

<body>
	<jsp:include page="/header.jsp" flush="true" />

	<!-- Modal -->
	
	<!-- Modal -->



	<section class="bg-success py-5">
		<div class="container">
			<div class="row align-items-center py-5">
				<div class="col-md-8 text-white">
					<h1>關於我們</h1>
					<p>隨著近代運動風氣越來越盛行，加上團隊內有多人喜歡運動，藉此想做一個主打運動相關的網頁，打造出一個擁有租借運動場地，獲取最新運動資訊、購買運動商品、並能揪團運動的平台。
						
												<div class="col-auto justify-content-md-end">
											<!-- 整個談窗+按鈕測試 -->
											<!-- Button trigger modal -->
											<button type="button" class="btn btn-light"
												data-bs-toggle="modal" data-bs-target="#exampleModal">
												聯絡我們</button>
											<!-- Modal -->
											<div class="modal fade" id="exampleModal" tabindex="-1"
												aria-labelledby="exampleModalLabel" aria-hidden="true">
												<!-- 彈出來的MODAL畫面開始 -->
												<div class="modal-dialog">
													<div class="modal-content">
														<!-- 彈出內容開始 -->
														<form METHOD="post" ACTION="" name="form1">
															<div class="modal-body">
																<div class="container-fluid">
																	<!-- 彈出內容 form表單開始-->
																	<div class="form-group">
																		<div class="row">
																			<div class="col">
																				<label for="email"><h6>Email</h6></label> <input
																					type="email" class="form-control"
																					name="sportsLocation" placeholder="email">
																			</div>
																		</div>
																	</div>
															
																	<div class="form-group">
																		<label for="description"><h6>內容</h6></label> 
																			<textarea id="description" class="form-control" name="description" rows="4" cols="50" placeholder="內容">
																	</textarea>
																	</div>
																</div>
															</div>
															<!-- 彈出內容結束 -->
															<div class="modal-footer">
																<input type="hidden" name="participantNumber" size="45"
																	value="0" /> <input type="hidden" name="action"
																	value="insert">
																<button type="submit" class="btn btn-success">送出</button>
															</div>
														</form>
													</div>
												</div>
												<!-- 彈出來的MODAL畫面結束 -->
											</div>
											<!-- 整個談窗+按鈕測試結束 -->
										</div></p>
				</div>
				<div class="col-md-4">
					<img src="<%=request.getContextPath()%>/assets/img/about-hero.svg"
						alt="About Hero">
				</div>
			</div>
		</div>
	</section>
	<!-- Close Banner -->

	<!-- Start Section -->
	<section class="container py-5">
		<div class="row text-center pt-5 pb-3">
			<div class="col-lg-6 m-auto">
				<h1 class="h1">服務內容</h1>
				<p>文字文字文字文字文字文字文字文字文字文字 文字文字文字文字文字文字文字文字文字文字文字 文字文字文字文字</p>
			</div>
		</div>
		<div class="row">

			<div class="col-md-6 col-lg-3 pb-5">
				<div class="h-100 py-5 services-icon-wap shadow">
					<div class="h1 text-success text-center">
						<i class="fa fa-truck fa-lg"></i>
					</div>
					<h2 class="h5 mt-4 text-center">運送服務</h2>
				</div>
			</div>

			<div class="col-md-6 col-lg-3 pb-5">
				<div class="h-100 py-5 services-icon-wap shadow">
					<div class="h1 text-success text-center">
						<i class="fas fa-exchange-alt"></i>
					</div>
					<h2 class="h5 mt-4 text-center">退換貨</h2>
				</div>
			</div>

			<div class="col-md-6 col-lg-3 pb-5">
				<div class="h-100 py-5 services-icon-wap shadow">
					<div class="h1 text-success text-center">
						<i class="fa fa-percent"></i>
					</div>
					<h2 class="h5 mt-4 text-center">折扣</h2>
				</div>
			</div>

			<div class="col-md-6 col-lg-3 pb-5">
				<div class="h-100 py-5 services-icon-wap shadow">
					<div class="h1 text-success text-center">
						<i class="fa fa-user"></i>
					</div>
					<h2 class="h5 mt-4 text-center">24小時服務</h2>
				</div>
			</div>
		</div>
	</section>
	<!-- End Section -->

	<!-- Start Brands -->
	<section class="bg-light py-5">
		<div class="container my-4">
			<div class="row text-center py-3">
				<div class="col-lg-6 m-auto">
					<h1 class="h1">品牌總覽</h1>
					<p>測試文字測試文字測試文字</p>
				</div>
				<div class="col-lg-9 m-auto tempaltemo-carousel">
					<div class="row d-flex flex-row">
						<!--Controls-->
						<div class="col-1 align-self-center">
							<a class="h1" href="#templatemo-slide-brand" role="button"
								data-bs-slide="prev"> <i
								class="text-light fas fa-chevron-left"></i>
							</a>
						</div>
						<!--End Controls-->

						<!--Carousel Wrapper-->
						<div class="col">
							<div class="carousel slide carousel-multi-item pt-2 pt-md-0"
								id="templatemo-slide-brand" data-bs-ride="carousel">
								<!--Slides-->
								<div class="carousel-inner product-links-wap" role="listbox">

									<!--First slide-->
									<div class="carousel-item active">
										<div class="row">
											<div class="col-3 p-md-5">
												<a href="#"><img class="img-fluid brand-img"
													src="<%=request.getContextPath()%>/assets/img/brand_01.png"
													alt="Brand Logo"></a>
											</div>
											<div class="col-3 p-md-5">
												<a href="#"><img class="img-fluid brand-img"
													src="<%=request.getContextPath()%>/assets/img/brand_02.png"
													alt="Brand Logo"></a>
											</div>
											<div class="col-3 p-md-5">
												<a href="#"><img class="img-fluid brand-img"
													src="<%=request.getContextPath()%>/assets/img/brand_03.png"
													alt="Brand Logo"></a>
											</div>
											<div class="col-3 p-md-5">
												<a href="#"><img class="img-fluid brand-img"
													src="<%=request.getContextPath()%>/assets/img/brand_04.png"
													alt="Brand Logo"></a>
											</div>
										</div>
									</div>
									<!--End First slide-->

									<!--Second slide-->
									<div class="carousel-item">
										<div class="row">
											<div class="col-3 p-md-5">
												<a href="#"><img class="img-fluid brand-img"
													src="<%=request.getContextPath()%>/assets/img/brand_01.png" alt="Brand Logo"></a>
											</div>
											<div class="col-3 p-md-5">
												<a href="#"><img class="img-fluid brand-img"
													src="<%=request.getContextPath()%>/assets/img/brand_02.png" alt="Brand Logo"></a>
											</div>
											<div class="col-3 p-md-5">
												<a href="#"><img class="img-fluid brand-img"
													src="<%=request.getContextPath()%>/assets/img/brand_03.png" alt="Brand Logo"></a>
											</div>
											<div class="col-3 p-md-5">
												<a href="#"><img class="img-fluid brand-img"
													src="<%=request.getContextPath()%>/assets/img/brand_04.png" alt="Brand Logo"></a>
											</div>
										</div>
									</div>
									<!--End Second slide-->

									<!--Third slide-->
									<div class="carousel-item">
										<div class="row">
											<div class="col-3 p-md-5">
												<a href="#"><img class="img-fluid brand-img"
													src="<%=request.getContextPath()%>/assets/img/brand_01.png" alt="Brand Logo"></a>
											</div>
											<div class="col-3 p-md-5">
												<a href="#"><img class="img-fluid brand-img"
													src="<%=request.getContextPath()%>/assets/img/brand_02.png" alt="Brand Logo"></a>
											</div>
											<div class="col-3 p-md-5">
												<a href="#"><img class="img-fluid brand-img"
													src="<%=request.getContextPath()%>/assets/img/brand_03.png" alt="Brand Logo"></a>
											</div>
											<div class="col-3 p-md-5">
												<a href="#"><img class="img-fluid brand-img"
													src="<%=request.getContextPath()%>/assets/img/brand_04.png" alt="Brand Logo"></a>
											</div>
										</div>
									</div>
									<!--End Third slide-->

								</div>
								<!--End Slides-->
							</div>
						</div>
						<!--End Carousel Wrapper-->

						<!--Controls-->
						<div class="col-1 align-self-center">
							<a class="h1" href="#templatemo-slide-brand" role="button"
								data-bs-slide="next"> <i
								class="text-light fas fa-chevron-right"></i>
							</a>
						</div>
						<!--End Controls-->
					</div>
				</div>
			</div>
		</div>
	</section>
	<!--End Brands-->
	
	

	<!-- Start Script -->
	<script
		src="<%=request.getContextPath()%>/assets/js/jquery-3.6.0.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/assets/js/jquery-migrate-1.2.1.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/assets/js/bootstrap.bundle.min.js"></script>
	<script src="<%=request.getContextPath()%>/assets/js/templatemo.js"></script>
	<script src="<%=request.getContextPath()%>/assets/js/custom.js"></script>
	<!-- End Script -->
	<jsp:include page="/footer.jsp" flush="true" />
</body>

</html>