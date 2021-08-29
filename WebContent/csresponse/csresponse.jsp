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
	<!-- 客服回應開始 -->
	<div class="row text-center pt-5 pb-3">
		<div class="col-lg-6 m-auto">
			<h1 class="h1">客服回應表單</h1>
			<p>我們非常抱歉遇到讓你使用不愉快的經驗，請提供資訊讓我們得以改進</p>
		</div>
	</div>
	<div class="container px-5 my-5">
		<form id="contactForm" data-sb-form-api-token="API_TOKEN">
			<div class="mb-3">
				<label class="form-label" for="email">*Email </label> <input
					class="form-control" id="email" type="email" placeholder="Email "
					data-sb-validations="required,email" />
				<div class="invalid-feedback" data-sb-feedback="email:required">Email為必填項目.</div>
				<div class="invalid-feedback" data-sb-feedback="email:email">Email
					不可為空.</div>
			</div>
			<div class="mb-3">
				<label class="form-label" for="連絡電話">*連絡電話</label> <input
					class="form-control" id="連絡電話" type="text" placeholder="連絡電話"
					data-sb-validations="required" />
				<div class="invalid-feedback" data-sb-feedback="連絡電話:required">連絡電話
					為必填項目.</div>
			</div>
			<div class="mb-3">
				<label class="form-label" for="內容描述">*內容描述</label>
				<textarea class="form-control" id="內容描述" type="text"
					placeholder="內容描述" style="height: 10rem;"
					data-sb-validations="required"></textarea>
				<div class="invalid-feedback" data-sb-feedback="內容描述:required">內容描述
					為必填項目.</div>
			</div>
			<div class="d-none" id="submitSuccessMessage">
				<div class="text-center mb-3">
					<div class="fw-bolder">Form submission successful!</div>
					<p>To activate this form, sign up at</p>
					<a href="https://startbootstrap.com/solution/contact-forms">https://startbootstrap.com/solution/contact-forms</a>
				</div>
			</div>
			<div class="d-none" id="submitErrorMessage">
				<div class="text-center text-danger mb-3">Error sending
					message!</div>
			</div>
			<div class="d-grid">
				<button class="btn btn-primary btn-lg disabled" id="submitButton"
					type="submit">Submit</button>
			</div>
		</form>
	</div>
	<script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>

	<!-- 客服回應結束 -->



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