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
<title>���έ��� </title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">


<!-- �����ƪ��Ϊ� -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/bootstrap.min.css">
<!-- �����D���F�� -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/templatemo.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/custom.css">
<!-- Load fonts style after rendering the layout styles -->
<!-- �]�w�r�� -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;200;300;400;500;700;900&display=swap">
<!-- �פJ�Ϥ� -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/fontawesome.min.css">
	
	<!-- ����logo -->
	<link rel="shortcut icon" href="<%= request.getContextPath() %>/assets/img/logo1.png" type="image/x-icon" />
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
					<jsp:include page="../article/articleside.jsp" flush="true" />
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
									<p class="fs-1 fw-bold text-success">${param.whichClass==1 ? "�ڪ�����" : "���έ���"}</p>

								</div>
								<!-- �o�_���Ϋ��s�}�l -->
								<div class="container">
									<div
										class="row align-items-star justify-content-between articletitle">
										<%-- ���~��C --%>
										<div class="col-auto h6">
											<c:if test="${not empty errorMsgs}">
												<font style="color: red">�Эץ��H�U���~:</font>
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
										<%-- ���~��C --%>
										<div class="col-auto justify-content-md-end">
											<!-- ��ӽ͵�+���s���� -->
											<!-- Button trigger modal -->
											<button type="button" class="btn btn-success" ${empty userId? "disabled":""}
												data-bs-toggle="modal" data-bs-target="#exampleModal">
												�o�_����</button>
											<!-- Modal -->
											<div class="modal fade" id="exampleModal" tabindex="-1"
												aria-labelledby="exampleModalLabel" aria-hidden="true">
												<!-- �u�X�Ӫ�MODAL�e���}�l -->
												<div class="modal-dialog">
													<div class="modal-content">
														<!-- �u�X���e�}�l -->
														<form METHOD="post" ACTION="sportsGroup.do" name="form1">
															<div class="modal-body">
																<div class="container-fluid">
																	<!-- �u�X���e form���}�l-->
																	<div class="form-group">
																		<!--<label for="userid">���ΤH</label>  -->
																		<input type="hidden" class="form-control"
																			name="userId" value="<%=userId%>" readonly>

																	</div>
																	<div class="form-group">
																		<div class="row">
																			<div class="col">
																				<label for="sportsType"><h6>��������</h6></label> <input
																					type="TEXT" class="form-control" name="sportsType"
																					placeholder="��J�Q�o�_������">
																			</div>
																			<div class="col">
																				<label for="sportsLocation"><h6>���ʦa�I</h6></label> <input
																					type="TEXT" class="form-control"
																					name="sportsLocation" placeholder="��J�a�I">
																			</div>
																		</div>
																	</div>
																	<div class="form-group">
																		<label for="exerciseTime">���ʮɶ�</label> <input
																			type="text" name="exerciseTime" value="exerciseTime"
																			class="form-control" id="exerciseTime">
																	</div>

																	<div class="form-group container">
																		<div class="row">
																			<div class="col">
																				<label for="numberLowLimit">�}�ΤH��</label> <input
																					type="text" class="form-control col"
																					name="numberLowLimit" placeholder="�̤�">
																			</div>
																			<div class="col">
																				<label for="numberUpLimit">�̦h</label> <input
																					type="text" class="form-control col"
																					name="numberUpLimit" placeholder="�̦h">
																			</div>
																		</div>
																	</div>
																	<div class="form-group">
																		<label for="remarks">�Ƶ�</label> <input type="text"
																			class="form-control" name="remarks">
																	</div>
																	<div class="form-group">
																		<label for="registTimeAndEnd">���W����</label><input
																			type="text" name="registTimeAndEnd"
																			value="registTimeAndEnd" class="form-control">
																	</div>
																</div>
															</div>
															<!-- �u�X���e���� -->
															<div class="modal-footer">

																<button type="button" class="btn btn-success"
																	data-bs-dismiss="modal">����</button>
																<input type="hidden" name="participantNumber" size="45"
																	value="0" /> <input type="hidden" name="action"
																	value="insert">

																<button type="submit" class="btn btn-success">�o�_</button>
															</div>
														</form>
													</div>
												</div>
												<!-- �u�X�Ӫ�MODAL�e������ -->
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
							<div style="padding: 16px 20px 16px 20px">
								<div class="container">
									<div class="row text-center">
										<div class="col h6">�B������</div>
										<div class="col h6" style="padding-right: 0px">�ɶ�</div>
										<div class="col h6" style="padding-left: 0px">�H��</div>
										<div class="col h6">�o�_�ɶ�</div>
									</div>
								</div>
							</div>
							<!-- ���Y���� -->
							<c:forEach var="sportsGroupVO" items="${list}"
								begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
								<c:set var="tdate" value="${sportsGroupVO.issueDATE}"></c:set>
								<c:set var="tdate1" value="${sportsGroupVO.registTime}"></c:set>
								<c:set var="tdate2" value="${sportsGroupVO.registTimeEnd}"></c:set>
								<c:set var="tdate3" value="${sportsGroupVO.exerciseTime}"></c:set>
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
											<p class="mb-0 text-success h6">���ΤH�G
												${generalUserSvc.getOneGeneralUser(sportsGroupVO.userId).userName}</p>
											<p class="mb-0 text-success h6">
												���W�ɶ��G<%=tformat.format(pageContext.getAttribute("tdate1"))%>��<%=tformat.format(pageContext.getAttribute("tdate2"))%></p>
											<P class="mb-0 text-success h6">�a�I:${sportsGroupVO.sportsLocation}</P>
											<p class="mb-0 text-success h6">���ʤH��:${sportsGroupVO.numberUpLimit}</P>
											<p class="mb-0 text-success h6">�w���W�H��:${participantSVC.getParticipant(sportsGroupVO.sportsGroupSN).size()}</P>
											<p class="mb-0 text-success h6">�Ƶ�:${sportsGroupVO.remarks}</P>
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
																	

																<button class="btn btn-success" type="submit" ${empty userId? "disabled":""}>�ѥ[${participantVO.participantID}</button>
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

																<button class="btn btn-success" type="submit">�h�X</button>
															</form>
														</c:when>
													</c:choose>
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

	<!-- 	<td> -->

	<%-- 	                ${generalUserSvc.getOneGeneralUser(sportsGroupVO.sportsGroupSN).userName}     --%>


	<!-- 			</td> -->
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