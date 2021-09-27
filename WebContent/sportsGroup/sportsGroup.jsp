<%@page import="java.text.SimpleDateFormat"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.sportsgroup.model.*"%>
<%@ page import="com.participant.model.*"%>

<%
	SportsGroupService sportsGroupSvc = new SportsGroupService();
	ParticipantService participantSvc = new ParticipantService();
	List<SportsGroupVO> list = null;
	List<ParticipantVO> list1 = null;
	SimpleDateFormat tformat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
	Integer userId = (Integer)session.getAttribute("userId");
%>

<c:choose>
	<c:when test="${param.whichClass==0 || empty param.whichClass}">
		<%
			list = sportsGroupSvc.getAll();
		%>
	</c:when>

	<c:when test="${param.whichClass==1}">
		<%
			list = participantSvc.getSportsGroup(userId);
		%>
	</c:when>




</c:choose>

<%
	Collections.reverse(list);
	pageContext.setAttribute("list", list);
%>
<jsp:useBean id="generalUserSvc" scope="page"
	class="com.GeneralUser.model.GeneralUserService" />
<jsp:useBean id="participantSVC" scope="page"
	class="com.participant.model.ParticipantService" />
<html>

<head>
<title>揪團首頁 </title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">


<!-- 介面排版用的 -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/bootstrap.min.css">
<!-- 不知道的東西 -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/templatemo.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/custom.css">
<!-- Load fonts style after rendering the layout styles -->
<!-- 設定字型 -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;200;300;400;500;700;900&display=swap">
<!-- 匯入圖片 -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/fontawesome.min.css">
	
	<!-- 網頁logo -->
	<link rel="shortcut icon" href="<%= request.getContextPath() %>/assets/img/logo1.png" type="image/x-icon" />
<!-- 	時間選擇器的CSS -->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker\daterangepicker.css" />
</head>

<body>
	<jsp:include page="/header.jsp" flush="true" />
	<!--Start 揪團body-->
	<section class="bg-light">
		<div class="container pb-5 pt-5">
			<div class="row justify-content-center article-main">

				<!-- 左邊side -->
				<div class="col-md-2 side">
					<jsp:include page="../article/articleside.jsp" flush="true" />
				</div>
				<!-- close左邊side -->

				<!-- 論壇本體本體 -->
				<div class="col-md-8 article-zone">
					<!-- 外框 -->
					<div class="card col-lg-12 article-card">
						<!-- 第一行 大標 -->
						<div class="container mt-4">
							<div
								class="row justify-content-between align-items-end articletitle">
								<div class="col-auto">
									<p class="fs-1 fw-bold text-success">${param.whichClass==1 ? "我的揪團" : "揪團首頁"}</p>

								</div>
								<!-- 發起揪團按鈕開始 -->
								<div class="container">
									<div
										class="row align-items-star justify-content-between articletitle">
										<%-- 錯誤表列 --%>
										<div class="col-auto h6">
											<c:if test="${not empty errorMsgs}">
												<font style="color: red">請修正以下錯誤:</font>
												<ul>
													<c:forEach var="message" items="${errorMsgs}">
														<li style="color: red">${message}</li>
													</c:forEach>
												</ul>
											</c:if>
											<c:if test="${not empty successMsgs}">
												<ul>
													<c:forEach var="message" items="${successMsgs}">
														<li style="color: #009100">${successMsgs}</li>
													</c:forEach>
												</ul>
											</c:if>
										</div>
										<%-- 錯誤表列 --%>
										<div class="col-auto justify-content-md-end">
											<!-- 整個談窗+按鈕測試 -->
											<!-- Button trigger modal -->
											<button type="button" class="btn btn-success" ${empty userId? "disabled":""}
												data-bs-toggle="modal" data-bs-target="#exampleModal">
												發起揪團</button>
											<!-- Modal -->
											<div class="modal fade" id="exampleModal" tabindex="-1"
												aria-labelledby="exampleModalLabel" aria-hidden="true">
												<!-- 彈出來的MODAL畫面開始 -->
												<div class="modal-dialog">
													<div class="modal-content">
														<!-- 彈出內容開始 -->
														<form METHOD="post" ACTION="sportsGroup.do" name="form1">
															<div class="modal-body">
																<div class="container-fluid">
																	<!-- 彈出內容 form表單開始-->
																	<div class="form-group">
																		<!--<label for="userid">揪團人</label>  -->
																		<input type="hidden" class="form-control"
																			name="userId" value="<%=userId%>" readonly>

																	</div>
																	<div class="form-group">
																		<div class="row">
																			<div class="col">
																				<label for="sportsType"><h6>活動類型</h6></label> <input
																					type="TEXT" class="form-control" name="sportsType"
																					placeholder="輸入想發起的活動">
																			</div>
																			<div class="col">
																				<label for="sportsLocation"><h6>活動地點</h6></label> <input
																					type="TEXT" class="form-control"
																					name="sportsLocation" placeholder="輸入地點">
																			</div>
																		</div>
																	</div>
																	<div class="form-group">
																		<label for="exerciseTime">活動時間</label> <input
																			type="text" name="exerciseTime" value="exerciseTime"
																			class="form-control" id="exerciseTime">
																	</div>

																	<div class="form-group container">
																		<div class="row">
																			<div class="col">
																				<label for="numberLowLimit">開團人數</label> <input
																					type="text" class="form-control col"
																					name="numberLowLimit" placeholder="最少">
																			</div>
																			<div class="col">
																				<label for="numberUpLimit">最多</label> <input
																					type="text" class="form-control col"
																					name="numberUpLimit" placeholder="最多">
																			</div>
																		</div>
																	</div>
																	<div class="form-group">
																		<label for="remarks">備註</label> <input type="text"
																			class="form-control" name="remarks">
																	</div>
																	<div class="form-group">
																		<label for="registTimeAndEnd">報名期限</label><input
																			type="text" name="registTimeAndEnd"
																			value="registTimeAndEnd" class="form-control">
																	</div>
																</div>
															</div>
															<!-- 彈出內容結束 -->
															<div class="modal-footer">

																<button type="button" class="btn btn-success"
																	data-bs-dismiss="modal">關閉</button>
																<input type="hidden" name="participantNumber" size="45"
																	value="0" /> <input type="hidden" name="action"
																	value="insert">

																<button type="submit" class="btn btn-success">發起</button>
															</div>
														</form>
													</div>
												</div>
												<!-- 彈出來的MODAL畫面結束 -->
											</div>
											<!-- 整個談窗+按鈕測試結束 -->
										</div>
									</div>
								</div>
								<!-- 發起揪團按鈕結束 -->
							</div>
						</div>
						<!-- close第一行 大標 -->

						<!-- 第三行 揪團列表 -->
						<div class="accordion accordion-flush" id="accordionFlushExample">
							<%@ include file="articlepage1.file"%>
							<!-- 標頭開始 -->
							<div style="padding: 16px 20px 16px 20px">
								<div class="container">
									<div class="row text-center">
										<div class="col h6">運動類型</div>
										<div class="col h6" style="padding-right: 0px">時間</div>
										<div class="col h6" style="padding-left: 0px">人數</div>
										<div class="col h6">發起時間</div>
									</div>
								</div>
							</div>
							<!-- 標頭結束 -->
							<c:forEach var="sportsGroupVO" items="${list}"
								begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
								<c:set var="tdate" value="${sportsGroupVO.issueDATE}"></c:set>
								<c:set var="tdate1" value="${sportsGroupVO.registTime}"></c:set>
								<c:set var="tdate2" value="${sportsGroupVO.registTimeEnd}"></c:set>
								<c:set var="tdate3" value="${sportsGroupVO.exerciseTime}"></c:set>
								<!-- 開始 -->
								<div class="accordion-item">
									<h2 class="accordion-header"
										id="flush-headingOne${sportsGroupVO.sportsGroupSN}">
										<button class="accordion-button collapsed" type="button"
											data-bs-toggle="collapse"
											data-bs-target="#flush-collapseOne${sportsGroupVO.sportsGroupSN}"
											aria-expanded="false"
											aria-controls="flush-collapseOne${sportsGroupVO.sportsGroupSN}">
											<div class="container">
												<div class="row text-center">
													<div class="col">
														<p class="h6">${sportsGroupVO.sportsType}</p>
													</div>
													<div class="col">
														<p class="h6"><%=tformat.format(pageContext.getAttribute("tdate3"))%></p>
													</div>
													<div class="col">
														<p class="h6">${participantSVC.getParticipant(sportsGroupVO.sportsGroupSN).size()}/
															${sportsGroupVO.numberUpLimit}</p>
													</div>
													<div class="col">
														<p class="h6"><%=tformat.format(pageContext.getAttribute("tdate"))%></p>
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
											<p class="mb-0 text-success h6">揪團人：
												${generalUserSvc.getOneGeneralUser(sportsGroupVO.userId).userName}</p>
											<p class="mb-0 text-success h6">
												報名時間：<%=tformat.format(pageContext.getAttribute("tdate1"))%>至<%=tformat.format(pageContext.getAttribute("tdate2"))%></p>
											<P class="mb-0 text-success h6">地點:${sportsGroupVO.sportsLocation}</P>
											<p class="mb-0 text-success h6">活動人數:${sportsGroupVO.numberUpLimit}</P>
											<p class="mb-0 text-success h6">已報名人數:${participantSVC.getParticipant(sportsGroupVO.sportsGroupSN).size()}</P>
											<p class="mb-0 text-success h6">備註:${sportsGroupVO.remarks}</P>
											<div class="row">
												<div class="d-md-flex justify-content-md-end">
													<%
														SportsGroupVO vo = (SportsGroupVO) pageContext.getAttribute("sportsGroupVO");
													%>
													<c:choose>
														<c:when
															test="<%=participantSvc.getaa(userId, vo.getSportsGroupSN())%>">
															<form METHOD="post"
																ACTION="<%=request.getContextPath()%>/sportsGroup/sportsGroup.do">
																<input type="hidden" name="whichClass"
																	value="${param.whichClass}"> <input
																	type="hidden" name="action" value="join"> <input
																	type="hidden" name="userId" value="<%=userId%>">
																<input type="hidden" name="sportsGroupSN"
																	value="${sportsGroupVO.sportsGroupSN}">
																<input type="hidden" name="numberUpLimit"
																	value="${sportsGroupVO.numberUpLimit}">
																<input type="hidden" name="participantNumber"
																	value="${participantSVC.getParticipant(sportsGroupVO.sportsGroupSN).size()}">
																	

																<button class="btn btn-success" type="submit" ${empty userId? "disabled":""}>參加${participantVO.participantID}</button>
															</form>
														</c:when>
														<c:when
															test="<%=!participantSvc.getaa(userId, vo.getSportsGroupSN())%>">
															<form METHOD="post"
																ACTION="<%=request.getContextPath()%>/sportsGroup/sportsGroup.do">

																<input type="hidden" name="whichClass"
																	value='${param.whichClass == 1 ? "1" : "0"}'> <input
																	type="hidden" name="action" value="leave"> <input
																	type="hidden" name="userId" value="<%=userId%>">
																	<input
																	type="hidden" name="suserId" value="${sportsGroupVO.userId}">
																<input type="hidden" name="sportsGroupSN"
																	value="${sportsGroupVO.sportsGroupSN}">

																<button class="btn btn-success" type="submit">退出</button>
															</form>
														</c:when>
													</c:choose>
												</div>

											</div>
										</div>
									</div>
								</div>
								<!-- 結束 -->
							</c:forEach>
						</div>
						<!-- 第三行 揪團列表結束 -->
						<%@ include file="articlepage2.file"%>
						<!-- close論壇本體本體 -->
					</div>
				</div>
				<!-- close外框 -->
			</div>
		</div>
		</div>
	</section>

	<!-- 	<td> -->

	<%-- 	                ${generalUserSvc.getOneGeneralUser(sportsGroupVO.sportsGroupSN).userName}     --%>


	<!-- 			</td> -->
	<!--CLOSE 揪團body-->
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
	<!-- Datetimepicker開始 -->
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/datetimepicker\jquery.min.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/datetimepicker\moment.min.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/datetimepicker\daterangepicker.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/datetimepicker\mydatetimepicker.js"></script>
	<!-- Datetimepicker結束 -->
	<jsp:include page="/footer.jsp" flush="true" />
</body>
</html>