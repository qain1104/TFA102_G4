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

	<!-- Start Content Page -->
	<div class="container-fluid bg-light py-5">
		<div class="col-md-6 m-auto text-center">
			<h1 class="h1">客服回應表單</h1>
			<p>請填寫下列資訊，將有專人為你服務，Sportify感謝您！</p>
		</div>
	</div>
	<!-- Start Contact -->
	<div class="container py-5">
		<div class="row py-5">
			<form class="col-md-9 m-auto" method="post"
				ACTION="<%=request.getContextPath()%>/sportsGroup/sportsGroup.do"
				role="form" id="sendmail">
				<div class="row">
					<div class="form-group col-md-6 mb-3">
						<label for="inputname">姓名</label> <input type="text"
							class="form-control mt-1" id="name" name="name"
							placeholder="您的名字" required>
					</div>
					<div class="form-group col-md-6 mb-3">
						<label for="inputemail">聯絡Email</label> <input type="email"
							class="form-control mt-1" id="email" name="email"
							placeholder="請注意輸入格式，如有誤將不會收到信件" required>
					</div>
				</div>
				<div class="mb-3">
					<label for="inputsubject">標題</label> <input type="text"
						class="form-control mt-1" id="subject" name="subject"
						placeholder="您遭遇的問題" required>
				</div>
				<div class="mb-3">
					<label for="inputmessage">內文</label>
					<textarea class="form-control mt-1" id="message" name="message"
						placeholder="我們很遺憾您遭遇問題，請在此詳細填寫您的問題" rows="8" required></textarea>
				</div>
				<div class="row">
					<div class="col text-end mt-2">
						<button type="submit" class="btn btn-success btn-lg px-3">發送</button>
						<input type="hidden" name="action" value="sendmail">
					</div>
				</div>
			</form>
		</div>
	</div>
	<!-- End Contact -->

<script>
$("#sendmail").submit(function(){
	alert("已收到你的表單")
	console.log("AAA")
});

</script>


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