<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="Big5"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>認證信箱</title>
<link href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style>
body {
	font-family: 'Varela Round', sans-serif;
}

.modal-confirm {
	color: #636363;
	width: 325px;
	margin: 30px auto;
}

.modal-confirm .modal-content {
	padding: 20px;
	border-radius: 5px;
	border: none;
}

.modal-confirm .modal-header {
	border-bottom: none;
	position: relative;
}

.modal-confirm h4 {
	text-align: center;
	font-size: 26px;
	margin: 30px 0 -15px;
}

.modal-confirm .form-control, .modal-confirm .btn {
	min-height: 40px;
	border-radius: 3px;
}

.modal-confirm .close {
	position: absolute;
	top: -5px;
	right: -5px;
}

.modal-confirm .modal-footer {
	border: none;
	text-align: center;
	border-radius: 5px;
	font-size: 13px;
}

.modal-confirm .icon-box {
	color: #fff;
	position: absolute;
	margin: 0 auto;
	left: 0;
	right: 0;
	top: -70px;
	width: 95px;
	height: 95px;
	border-radius: 50%;
	z-index: 9;
	background: #82ce34;
	padding: 15px;
	text-align: center;
	box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.1);
}

.modal-confirm .icon-box i {
	font-size: 58px;
	position: relative;
	top: 3px;
}

.modal-confirm.modal-dialog {
	margin-top: 80px;
}

.modal-confirm .btn {
	color: #fff;
	border-radius: 4px;
	background: #82ce34;
	text-decoration: none;
	transition: all 0.4s;
	line-height: normal;
	border: none;
}

.modal-confirm .btn:hover, .modal-confirm .btn:focus {
	background: #6fb32b;
	outline: none;
}

.trigger-btn {
	display: inline-block;
	margin: 100px auto;
}
</style>
</head>
<body>


	<div class="text-center">
		<!-- Button HTML (to Trigger Modal) -->
		<a href="#myModal" class="trigger-btn" data-toggle="modal">認證信箱，啟用會員</a>
	</div>

	<!-- Modal HTML -->
	<div id="myModal" class="modal fade">
		<div class="modal-dialog modal-confirm">
			<div class="modal-content">
				<div class="modal-header">
					<div class="icon-box">
						<i class="material-icons">&#xE876;</i>
					</div>
					<h4 class="modal-title">太棒了!</h4>
				</div>
				<div class="modal-body">
					<p class="text-center">系統已寄信，請查看您的信箱</p>
				</div>
				<div class="modal-footer">

									<!-- 送出backToLogic請求到corpUserServlet   -->
					<form action="<%=request.getContextPath()%>/webManager/WebManagerServlet.do" method="post">
						<input type="hidden" name="action" value="backToLogin">
						<button class="btn btn-success btn-block" type="submit">OK</button>
					</form>

				</div>
			</div>
		</div>
	</div>

</body>
</html>
