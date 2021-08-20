<%@page import="java.text.SimpleDateFormat"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.sportsgroup.model.*"%>

<%
	SportsGroupService sportsGroupSvc = new SportsGroupService();
	List<SportsGroupVO> list = sportsGroupSvc.getAll();
	pageContext.setAttribute("list", list);
	SimpleDateFormat tformat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
%>

<html>
<head>
<title>���έ���</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="apple-touch-icon" href="../assets/img/apple-icon.png">
<link rel="shortcut icon" type="image/x-icon"
	href="<%=request.getContextPath()%>/assets/img/favicon.ico">

<!-- �����ƪ��Ϊ� -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/bootstrap.min.css">
<!-- �����D���F�� -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/templatemo.css">
<!-- <link rel="stylesheet" href="assets/css/custom.css"> -->

<!-- Load fonts style after rendering the layout styles -->
<!-- �]�w�r�� -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;200;300;400;500;700;900&display=swap">
<!-- �פJ�Ϥ� -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/fontawesome.min.css">
<!-- 	�ɶ���ܾ���CSS -->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker\daterangepicker.css" />
</head>
<body>
	<jsp:include page="/header.jsp" flush="true" />
	<!--Start ����body-->
	<section class="bg-light">
		<div class="container pb-5 pt-5">
			<div class="row justify-content-center article-main">

				<!-- ����side -->
				<div class="col-md-2 side">
					<div class="card sticky-top">
						<div class="list-group list-group-flush">
							<a
								href="<%=request.getContextPath()%>/article/article.jsp?whichClass=0"
								class="list-group-item list-group-item-action list-group-item-success"
								aria-current="true"> �׾­��� </a> <a
								href="<%=request.getRequestURI()%>?whichClass=1"
								class="list-group-item list-group-item-action">�B�ʥ�</a> <a
								href="<%=request.getRequestURI()%>?whichClass=2"
								class="list-group-item list-group-item-action">�ӫ~����</a> <a
								href="<%=request.getRequestURI()%>?whichClass=3"
								class="list-group-item list-group-item-action">�B���ɨ�</a> <a
								href="<%=request.getRequestURI()%>?whichClass=4"
								class="list-group-item list-group-item-action">�ڪ��峹</a>
						</div>
						<div class="list-group list-group-flush">
							<a href="#"
								class="list-group-item list-group-item-action list-group-item-success"
								aria-current="true"> ���έ��� </a> <a href="#"
								class="list-group-item list-group-item-action">����</a> <a
								href="#" class="list-group-item list-group-item-action">�ڪ�����</a>
						</div>
					</div>
				</div>
				<!-- close����side -->

				<!-- �׾¥��饻�� -->
				<div class="col-md-8 article-zone">
					<!-- �~�� -->
					<div class="card col-lg-12 article-card">
						<!-- �Ĥ@�� �j�� -->
						<div class="container mt-4">
							<div
								class="row justify-content-between align-items-end articletitle">
								<div class="col-auto">
									<h1>���έ���</h1>

								</div>
								<!-- �o�_���Ϋ��s�}�l -->
								<div class="container">
									<div
										class="row align-items-star justify-content-between articletitle">
										<div class="col-auto"></div>
										<div class="col-auto justify-content-md-end">
											<!-- ��ӽ͵�+���s���� -->
											<!-- Button trigger modal -->
											<button type="button" class="btn btn-success"
												data-bs-toggle="modal" data-bs-target="#exampleModal">
												�o�_����</button>
											<!-- Modal -->
											<div class="modal fade" id="exampleModal" tabindex="-1"
												aria-labelledby="exampleModalLabel" aria-hidden="true">
												<div class="modal-dialog">
													<!-- �u�X�Ӫ��e���}�l -->
													<div class="modal-content">
														<!-- �u�X���e�}�l -->
														<div class="modal-body">
															<!-- �u�X���e -->
															<form>
																<div class="form-group">
																	<label for="userid">���ΤH</label> <input type="text"
																		class="form-control-sm" id="userid"
																		placeholder="��n�J�̰ѼƩm�W" readonly>
																</div>
																<div class="form-group">
																	<label for="sportsType">��������</label> <input type="text"
																		class="form-control-sm" id="sportsType"
																		placeholder="��J�Q���Ϊ�����">
																</div>
																<div class="form-group">
																	<label for="exerciseTime">���ʮɶ�</label> <input
																		type="text" name="dates2" value="exerciseTime"
																		class="form-control-sm" id="exerciseTime">
																</div>
																<div class="form-group">
																	<label for="numberLowLimit">�}�ΤH��</label> <input
																		type="text" class="form-control-sm"
																		id="numberLowLimit" placeholder="�̧C�}�ΤH��">
																</div>
																<div class="form-group">
																	<label for="remarks">���ΤH</label> <input type="textarea"
																		class="form-control" id="remarks" placeholder="�Ƶ�">
																</div>
																<div class="form-group">
																	<label for="registTime">���W����</label> <input type="text"
																		name="dates1" value="registTime" class="form-control"
																		id="registTime">
																</div>
															</form>
														</div>
														<!-- �u�X���e���� -->
														<div class="modal-footer">
															<button type="button" class="btn btn-success"
																data-bs-dismiss="modal">����</button>
															<button type="button" class="btn btn-primary">�o�_</button>
														</div>
													</div>
													<!-- �u�X�Ӫ��e������ -->
												</div>
											</div>
											<!-- ��ӽ͵�+���s���յ��� -->
										</div>
									</div>
								</div>
								<!-- �o�_���Ϋ��s���� -->
							</div>
						</div>
						<!-- close�Ĥ@�� �j�� -->

						<!-- �ĤT�� ���ΦC�� -->
						<div class="accordion accordion-flush" id="accordionFlushExample">
							<%@ include file="articlepage1.file"%>
							<!-- ���Y�}�l -->
							<div style="padding: 20px 0px 5px 0px">
								<div class="container">
									<div class="row text-center">
										<div class="col-2 h6">�B������</div>
										<div class="col-5 h6">�ɶ�</div>
										<div class="col-2 h6">�H��</div>

									</div>
								</div>
							</div>
							<!-- ���Y���� -->
							<c:forEach var="sportsGroupVO" items="${list}"
								begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
								<c:set var="tdate" value="${sportsGroupVO.issueDATE}"></c:set>
								<c:set var="tdate1" value="${sportsGroupVO.registTime}"></c:set>
								<c:set var="tdate2" value="${sportsGroupVO.registTimeEnd}"></c:set>
								<!-- �}�l -->
								<div class="accordion-item">
									<h2 class="accordion-header"
										id="flush-headingOne${sportsGroupVO.sportsGroupSN}">
										<button class="accordion-button collapsed" type="button"
											data-bs-toggle="collapse"
											data-bs-target="#flush-collapseOne${sportsGroupVO.sportsGroupSN}"
											aria-expanded="false"
											aria-controls="flush-collapseOne${sportsGroupVO.sportsGroupSN}">
											<div class="container">
												<div class="row">
													<div class="col">
														<p class="h6">${sportsGroupVO.sportsType}</p>
													</div>
													<div class="col">
														<p class="h6"><%=tformat.format(pageContext.getAttribute("tdate"))%></p>
													</div>
													<div class="col">
														<p class="h6">${sportsGroupVO.participantNumber}/
															${sportsGroupVO.numberUpLimit}</p>
													</div>
												</div>
											</div>
										</button>
									</h2>
									<div id="flush-collapseOne${sportsGroupVO.sportsGroupSN}"
										class="accordion-collapse collapse"
										aria-labelledby="flush-headingOne${sportsGroupVO.sportsGroupSN}"
										data-bs-parent="#accordionFlushExample">
										<div class="accordion-body">
											<p class="mb-0 text-success h6">���ΤH�G${sportsGroupVO.sportsGroupSN}</p>
											<p class="mb-0 text-success h6">
												���W�ɶ��G<%=tformat.format(pageContext.getAttribute("tdate1"))%>��<%=tformat.format(pageContext.getAttribute("tdate2"))%></p>
											<P class="mb-0 text-success h6">�a�I:${sportsGroupVO.sportsLocation}</P>
											<p class="mb-0 text-success h6">���ʤH��:${sportsGroupVO.numberUpLimit}</P>
											<p class="mb-0 text-success h6">�w���W�H��:${sportsGroupVO.participantNumber}</P>
											<p class="mb-0 text-success h6">�Ƶ�:${sportsGroupVO.remarks}</P>
											<div class="row">
												<div class="d-md-flex justify-content-md-end">
													<button class="btn btn-success" type="button">�ѥ[</button>
												</div>
											</div>
										</div>
									</div>
								</div>
								<!-- ���� -->
							</c:forEach>
						</div>
						<!-- �ĤT�� ���ΦC���� -->
						<%@ include file="articlepage2.file"%>
						<!-- close�׾¥��饻�� -->
					</div>
				</div>
				<!-- close�~�� -->
			</div>
		</div>
		</div>
	</section>
	<!--CLOSE ����body-->
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
	<!-- Datetimepicker�}�l -->
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/datetimepicker\jquery.min.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/datetimepicker\moment.min.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/datetimepicker\daterangepicker.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/datetimepicker\mydatetimepicker.js"></script>
	<!-- Datetimepicker���� -->
	<jsp:include page="/footer.jsp" flush="true" />
</body>
</html>