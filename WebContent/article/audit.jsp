<%@page import="javax.crypto.spec.DHGenParameterSpec"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="com.report.model.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.venue.model.*"%>
<%@ page import="util.*"%>
<%
	response.setIntHeader("Refresh", 5);
	Integer managerId = null;
	Datahandle dh = new Datahandle();
	REPORTService reportSvc = new REPORTService();
	ProductService pdtSvc = new ProductService();
	VenueService veSvc = new VenueService();
	List<REPORTVO> reportList = reportSvc.getReportStatus(0);
	List<ProductVO> productList = pdtSvc.getProductStatus(0);
	List<VenueVO> venueList = veSvc.getVenueStatus(0);
%>
<c:if test="${not empty managerId}">
	<%
		managerId = (Integer) session.getAttribute("managerId");
	%>
</c:if>
<!DOCTYPE html>
<html>
<head>
<title>Sportify-article</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="apple-touch-icon" href="../assets/img/apple-icon.png">
<!-- 網頁logo -->
<link rel="shortcut icon"
	href="<%=request.getContextPath()%>/assets/img/logo1.png"
	type="image/x-icon" />

<!-- 介面排版用的 -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/bootstrap.min.css">
<!-- 不知道的東西 -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/templatemo.css">

<!-- 設定字型 -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;200;300;400;500;700;900&display=swap">
<!-- 匯入圖片 -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/fontawesome.min.css">
<!-- footerheader css -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/custom.css">
</head>
<body>
	<jsp:include page="/header.jsp" flush="true" />
	<!-- 審核本體 -->
	<div class="bg-light">
		<div class="container pt-5 pb-5">
			<div class="row justify-content-center">
				<!-- 主框框 -->
				<div class="card p-3 col-10" style="min-width: 925px">
					<c:if test="${empty managerId}">
						<p class="fs-1 m-3 text-success text-center">審核頁面，請先登入，謝謝。</p>
					</c:if>
					<c:if test="${not empty managerId}">
						<!-- 標題 -->
						<div>
							<p class="fs-1 fw-bold text-success">審核首頁</p>
						</div>
						<!-- close標題 -->
						<!-- 審核按鈕 -->
						<div class="container">
							<div class="row  justify-content-around align-items-center">
								<div class="col-auto">
									<form method="get">
										<button type="submit" name="whichClass" value="1"
											class="btn btn-success position-relative">
											商品審核 <span
												class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
												<%=productList.size()%><span class="visually-hidden">unread
													messages</span>
											</span>
										</button>
									</form>
								</div>
								<div class="col-auto">
									<form method="get">
										<button type="submit" name="whichClass" value="2"
											class="btn btn-success position-relative">
											場地審核 <span
												class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
												<%=venueList.size()%><span class="visually-hidden">unread
													messages</span>
											</span>
										</button>
									</form>
								</div>
								<div class="col-auto">
									<form method="get">
										<button type="submit" name="whichClass" value="3"
											class="btn btn-success position-relative">
											檢舉審核 <span
												class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
												<%=reportList.size()%><span class="visually-hidden">unread
													messages</span>
											</span>
										</button>
									</form>
								</div>
							</div>
						</div>
						<!-- close審核按鈕 -->
						<!-- 審核列表 -->
						<div class="container mt-3">
							<div class="row justify-content-center">
								<div class="card col-md-10" style="min-width: 842px">

									<c:if test="${empty param.whichClass}">
										<p class="fs-1 m-3 text-success text-center">請選擇審核項目</p>
										<%-- 錯誤表列 --%>
										<c:if test="${not empty errorMsgs}">
											<font style="color: red">請修正以下錯誤:</font>
											<ul>
												<c:forEach var="message" items="${errorMsgs}">
													<li style="color: red">${message}</li>
												</c:forEach>
											</ul>
										</c:if>
										<%-- !錯誤表列 --%>
									</c:if>

									<!-- 商品表格 -->
									<c:if test="${param.whichClass==1}">
										<c:set var="list" value="<%=productList%>"></c:set>
										<p class="fs-1 mb-0 text-success">商品審核分頁</p>
										<table class="table table-striped table-hover align-middle">
											<thead>
												<tr>
													<th scope="col" style="min-width: 100px">商品名稱</th>
													<th scope="col" style="min-width: 50px">類別</th>
													<th scope="col" style="min-width: 120px">品牌</th>
													<th scope="col">商品資訊</th>
													<th scope="col" style="width: 160px; min-width: 160px">審核</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="productVO" items="${list}"
													varStatus="number">

													<tr>
														<td>${productVO.productName}</td>
														<td>${productVO.productClass}</td>
														<td>${productVO.productBrand}</td>
														<td>${productVO.productDetail}</td>
														<td>
															<div class="d-grid gap-2 d-md-flex">
																<form METHOD="post"
																	ACTION="<%=request.getContextPath()%>/article/report.do">
																	<input type="hidden" name="action"
																		value="updateProduct"> <input type="hidden"
																		name="whichClass" value="${param.whichClass}">
																	<input type="hidden" name="productSN"
																		value="${productVO.productSN}">
																	<button class="btn btn-success" type="submit"
																		name="productStatus" value="1">通過</button>
																	<button class="btn btn-success" type="submit"
																		name="productStatus" value="2">不通過</button>
																</form>
															</div>
														</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
										<c:if test="${fn:length(list)==0}">
											<p class="fs-1 m-3 text-success text-center">此項目已審核完畢</p>
										</c:if>
									</c:if>
									<!-- 商品表格列表 -->


									<!-- 場地表格 -->
									<c:if test="${param.whichClass==2}">
										<c:set var="list" value="<%=venueList%>"></c:set>
										<p class="fs-1 mb-0 text-success">場地審核分頁</p>
										<table class="table table-striped table-hover align-middle">
											<thead>
												<tr>
													<th scope="col" style="min-width: 100px">場地名稱</th>
													<th scope="col" style="min-width: 50px">類別</th>
													<th scope="col" style="min-width: 120px">電話</th>
													<th scope="col">地址</th>
													<th scope="col" style="width: 160px; min-width: 160px">審核</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="venueVO" items="${list}" varStatus="number">

													<tr>
														<td>${venueVO.venueName}</td>
														<td>${venueVO.venueClass}</td>
														<td>${venueVO.venuePhone}</td>
														<td>${venueVO.venueAddress}</td>
														<td>
															<div class="d-grid gap-2 d-md-flex">
																<form METHOD="post"
																	ACTION="<%=request.getContextPath()%>/article/report.do">
																	<input type="hidden" name="action" value="updateVenue">
																	<input type="hidden" name="whichClass"
																		value="${param.whichClass}"> <input
																		type="hidden" name="venueSN"
																		value="${venueVO.venueSN}">
																	<button class="btn btn-success" type="submit"
																		name="venueStatus" value="1">通過</button>
																	<button class="btn btn-success" type="submit"
																		name="venueStatus" value="2">不通過</button>
																</form>
															</div>
														</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
										<c:if test="${fn:length(list)==0}">
											<p class="fs-1 m-3 text-success text-center">此項目已審核完畢</p>
										</c:if>
									</c:if>
									<!-- 場地表格列表 -->


									<!-- 檢舉表格 -->
									<c:if test="${param.whichClass==3}">
										<c:set var="list" value="<%=reportList%>"></c:set>
										<p class="fs-1 mb-0 text-success">檢舉分頁</p>
										<table class="table table-striped table-hover align-middle">
											<thead>
												<tr>
													<th scope="col" style="min-width: 100px">編號</th>
													<th scope="col" style="min-width: 90px">檢舉原因</th>
													<th scope="col" style="min-width: 145px">檢舉時間</th>
													<th scope="col">檢舉備註</th>
													<th scope="col" style="width: 160px; min-width: 160px">審核</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="reportVO" items="${list}" varStatus="number">
													<c:set var="ttime" value="${reportVO.reportDate}"></c:set>

													<tr>
														<td><c:choose>
																<c:when test="${reportVO.replySN eq 0}">文章:${reportVO.articleSN}</c:when>
																<c:otherwise>回覆:${reportVO.replySN}</c:otherwise>
															</c:choose></td>
														<td><c:choose>
																<c:when test="${reportVO.reportClass eq 0}">不當言論</c:when>
																<c:when test="${reportVO.reportClass eq 1}">惡意言論</c:when>
																<c:otherwise>其他</c:otherwise>
															</c:choose></td>
														<td class="fs-6"><%=dh.getTime(pageContext.getAttribute("ttime"))%></td>
														<td>${reportVO.reportContent}</td>
														<td>
															<div class="d-grid gap-2 d-md-flex">
																<form METHOD="post"
																	ACTION="<%=request.getContextPath()%>/article/report.do">
																	<input type="hidden" name="action" value="updatereport">
																	<input type="hidden" name="reportSN"
																		value="${reportVO.reportSN}"> <input
																		type="hidden" name="managerId" value="${managerId}">
																	<c:choose>
																		<c:when test="${reportVO.replySN eq 0}">
																			<input type="hidden" name="articleSN"
																				value="${reportVO.articleSN}">
																			<input type="hidden" name="floor" value="0">
																		</c:when>
																		<c:otherwise>
																			<input type="hidden" name="replySN"
																				value="${reportVO.replySN}">
																			<input type="hidden" name="floor" value="1">
																		</c:otherwise>
																	</c:choose>
																	<input type="hidden" name="whichClass"
																		value="${param.whichClass}">
																	<button class="btn btn-success" type="submit"
																		name="reportStatus" value="1">通過</button>
																	<button class="btn btn-success" type="submit"
																		name="reportStatus" value="2">不通過</button>
																</form>
															</div>
														</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
										<c:if test="${fn:length(list)==0}">
											<p class="fs-1 m-3 text-success text-center">此項目已審核完畢</p>
										</c:if>
									</c:if>
									<!-- 檢舉表格列表 -->



								</div>
							</div>
						</div>
						<!-- close審核列表 -->
					</c:if>
				</div>
				<!-- 主框框 -->
			</div>
		</div>
	</div>
	<!-- CLOSE 審核本體 -->
	<jsp:include page="/footer.jsp" flush="true" />
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
</body>
</html>