<%@page import="util.Datahandle"%>
<%@page import="javax.crypto.spec.DHGenParameterSpec"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.article.model.*"%>
<%@ page import="com.reply.model.*"%>
<%@ page import="com.alike.model.*"%>
<%@ page import="com.rlike.model.*"%>
<%@ page import="com.GeneralUser.model.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="util.*"%>
<%
	Integer articleSN = Integer.parseInt(request.getParameter("sn"));
	GeneralUserService guSvc = new GeneralUserService();
	ARTICLEService aSvc = new ARTICLEService();
	REPLYService rSvc = new REPLYService();
	ARTICLE_LIKEService alikeSvc = new ARTICLE_LIKEService();
	REPLY_LIKEService rlikeSvc = new REPLY_LIKEService();
	ARTICLEVO articleVO = aSvc.getOneArticle(articleSN);
	request.setAttribute("articleVO", articleVO);
	List<REPLYVO> list = rSvc.getReplybyArticle(articleSN);
	request.setAttribute("list", list);
	Datahandle dh = new Datahandle();
	SimpleDateFormat tformat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
%>
<%
	Integer userId = null;
%>
<c:if test="${!empty userId}">
	<%
		userId = (Integer) session.getAttribute("userId");
	%>
</c:if>
<!DOCTYPE html>
<html>
<head>
<title>Sportify-article</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="apple-touch-icon" href="../assets/img/apple-icon.png">
<link rel="shortcut icon" type="image/x-icon"
	href="<%=request.getContextPath()%>/assets/img/favicon.ico">

<!-- 介面排版用的 -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/bootstrap.min.css">
<!-- 不知道的東西 -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/templatemo.css">
<!-- <link rel="stylesheet" href="assets/css/custom.css"> -->

<!-- Load fonts style after rendering the layout styles -->
<!-- 設定字型 -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;200;300;400;500;700;900&display=swap">
<!-- 匯入圖片 -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/fontawesome.min.css">
<!-- footerheader css -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/custom.css">
<!-- footerheader css -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/custom.css">
<style>
img {
	max-width: 100%;
	max-height: 100%;
}
</style>
</head>
<body>
	<jsp:include page="/header.jsp" flush="true" />
	<!-- 論壇回覆本體 -->
	<section class="bg-light">
		<div class="container pb-5 pt-5">
			<div class="row justify-content-center">
				<!-- 左邊框框 -->
				<div class="col-md-2 side">
					<jsp:include page="articleside.jsp" flush="true" />
				</div>
				<!-- close左邊框框side -->
				<!-- 右邊本體 -->
				<div class="col-lg-10 article-zone">
					<!-- 右邊row -->
					<div class="row">
						<!-- 最上面標題 -->
						<div class="row">
							<div class="col-lg-12">
								<p class="fs-2 mb-0 px-1 text-success">
									<c:choose>
										<c:when test="${articleVO.articleType==0}">[討論]</c:when>
										<c:when test="${articleVO.articleType==1}">[發問]</c:when>
										<c:when test="${articleVO.articleType==2}">[心得]</c:when>
										<c:otherwise>錯誤</c:otherwise>
									</c:choose>${articleVO.articleTitle}</p>
							</div>
						</div>
						<!-- !最上面標題 -->
						<%-- 錯誤表列 --%>
						<c:if test="${not empty errorMsgs}">
							<font style="color: red"></font>
							<ul>
								<c:forEach var="message" items="${errorMsgs}">
									<li style="color: red">${message}</li>
								</c:forEach>
							</ul>
						</c:if>
						<%-- !錯誤表列 --%>
						<!--主體文章大樓 -->
						<c:choose>
							<c:when test="${articleVO.articleStatus==0}">
								<!-- 文章內容外框 -->
								<div class="row mt-3">
									<div class="col-auto">
										<div class="card sticky-top" style="max-width: 100px">
											<div>
												<img
													src="<%=request.getContextPath()%>/Readerpic?userId=${articleVO.userId}">
											</div>
											<div class="fs-5 text-success text-center">
												<c:forEach var="userVO" items="<%=guSvc.getAll()%>">
													<c:if test="${userVO.userId eq articleVO.userId}">
	                    ${userVO.userName}
                    </c:if>
												</c:forEach>
											</div>
										</div>
									</div>
									<div class="card col-10 pb-3">
										<div class="container mt-4">
											<div class="row justify-content-between">
												<div class="col-auto">
													<p class="fs-5 mb-0 text-success">樓主</p>
													<p class="fs-5 mb-0 text-success">
														<c:choose>
															<c:when
																test="${articleVO.articleUpDate == articleVO.articleDate || articleVO.articleUpDate == null}">發文時間:<%=tformat.format(articleVO.getarticleDate())%></c:when>
															<c:when
																test="${articleVO.articleUpDate != articleVO.articleDate}">最後編輯時間: <%=tformat.format(articleVO.getArticleUpDate())%></c:when>
															<c:otherwise>錯誤</c:otherwise>
														</c:choose>
													</p>
												</div>
												<div class="col-auto opacity-25">
													<form METHOD="post"
														ACTION="<%=request.getContextPath()%>/article/article.do">
														<input type="hidden" name="action" value="toedita">
														<input type="hidden" name="articleSN"
															value="${articleVO.articleSN}"> <input
															type="hidden" name="floor" value="1">
														<button class="btn btn-success"
															${userId!=articleVO.userId? 'style="visibility:hidden"':''}
															type="submit">編輯</button>
													</form>

												</div>
											</div>
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
											<div class="card mt-3 p-3" style="min-height: 300px;">
												<%
													byte[] bytes = articleVO.getArticleContent();
															String acontent = dh.getArticleContent(bytes);
												%>
												<%=acontent%>
											</div>
											<div class="row justify-content-between mt-2">
												<div class="col-auto d-grid gap-2 d-md-flex">
													<div class="row align-items-end">
														<div class="col-auto">
															<form METHOD="post"
																ACTION="<%=request.getContextPath()%>/article/article.do">
																<input type="hidden" name="action" value="alike">
																<input type="hidden" name="articleSN"
																	value="${articleVO.articleSN}"> <input
																	type="hidden" name="loguserId" value="${userId}">
																<c:if test="${!empty userId}">
																	<c:set var="aliked"
																		value="<%=alikeSvc.doesaliked(userId, articleVO.getArticleSN())%>"></c:set>
																</c:if>
																<c:choose>
																	<c:when test="${!empty userId && !aliked}">
																		<button class="btn btn-success" type="submit">收回推</button>
																	</c:when>

																	<c:otherwise>
																		<button class="btn btn-success" type="submit"
																			${empty userId? "disabled":""}>推推</button>
																	</c:otherwise>
																</c:choose>

															</form>
														</div>
														<div class="card col-auto">
															<div>${articleVO.articleLikes}</div>
														</div>
													</div>
												</div>
												<div class="col-auto d-grid gap-2 d-md-flex">
													<form METHOD="post"
														ACTION="<%=request.getContextPath()%>/article/report.do">
														<input type="hidden" name="action" value="toreport">
														<input type="hidden" name="articleSN"
															value="${articleVO.articleSN}"> <input
															type="hidden" name="articleVO" value="<%=articleVO%>">
														<input type="hidden" name="floor" value="1">
														<button ${empty userId? 'style="visibility:hidden"':''}
															class="btn btn-success" type="submit">檢舉</button>
													</form>
												</div>
											</div>
										</div>
									</div>
								</div>
								<!-- !文章內容外框 -->
							</c:when>
							<c:otherwise>
								<!-- 文章隱藏框框 -->
								<div class="row mt-3">
									<div class="col-auto">
										<div class="sticky-top" style="width: 102px"></div>
									</div>

									<div class="card col-10">
										<div class="container p-3">
											<p class="fs-5 mb-0 text-success">樓主:此文已刪除</p>
										</div>
									</div>

								</div>
								<!-- !文章隱藏框框 -->
							</c:otherwise>
						</c:choose>
						<!--主體文章大樓 -->

						<!--回覆文章大樓 -->
						<c:if test="${fn:length(list)!=0}">
							<c:forEach var="replyVO" items="${list}" varStatus="floor">
								<%
									REPLYVO replyVO = (REPLYVO) pageContext.getAttribute("replyVO");
								%>
								<c:choose>
									<c:when test="${replyVO.replyStatus==0}">
										<!-- 文章內容外框 -->
										<div class="row mt-3">
											<div class="col-auto">
												<div class="card sticky-top" style="max-width: 100px">
													<div>
														<img
															src="<%=request.getContextPath()%>/Readerpic?userId=${replyVO.userId}">
													</div>
													<div class="fs-5 text-success text-center">
														<c:forEach var="userVO" items="<%=guSvc.getAll()%>">
															<c:if test="${userVO.userId eq replyVO.userId}">
	                    ${userVO.userName}
                    </c:if>
														</c:forEach>
													</div>
												</div>
											</div>
											<div class="card col-10 pb-3">
												<div class="container mt-4">
													<div class="row justify-content-between">
														<div class="col-auto">
															<p class="fs-5 mb-0 text-success">${floor.count + 1 }樓</p>
															<p class="fs-5 mb-0 text-success">
																<c:choose>
																	<c:when
																		test="${replyVO.replyUpDate == replyVO.replyDate || replyVO.replyUpDate == null}">發文時間:<%=tformat.format(replyVO.getReplyDate())%></c:when>
																	<c:when
																		test="${replyVO.replyUpDate != replyVO.replyDate}">最後編輯時間: <%=tformat.format(replyVO.getReplyUpDate())%></c:when>
																	<c:otherwise>錯誤</c:otherwise>
																</c:choose>
															</p>
														</div>
														<div class="col-auto"
															${userId!=replyVO.userId? 'style="visibility:hidden"':''}>
															<form METHOD="post"
																ACTION="<%=request.getContextPath()%>/article/reply.do">
																<input type="hidden" name="action" value="toeditr">
																<input type="hidden" name="floor"
																	value="${floor.count + 1 }"> <input
																	type="hidden" name="replySN" value="${replyVO.replySN}">
																<button class="btn btn-success" type="submit">編輯</button>
															</form>

														</div>
													</div>
													<div class="card mt-3 p-3" style="min-height: 300px;">
														<%
															byte[] bytes = replyVO.getReplyContent();
																			String acontent = dh.getArticleContent(bytes);
														%>
														<%=acontent%>
													</div>
													<div class="row justify-content-between mt-2">
														<div class="col-auto d-grid gap-2 d-md-flex">
															<div class="row align-items-end">
																<div class="col-auto">
																	<form METHOD="post"
																		ACTION="<%=request.getContextPath()%>/article/reply.do">
																		<input type="hidden" name="action" value="rlike">
																		<input type="hidden" name="articleSN"
																			value="${articleVO.articleSN}"><input
																			type="hidden" name="replySN"
																			value="${replyVO.replySN}"> <input
																			type="hidden" name="loguserId" value="${userId}">
																		<c:if test="${!empty userId}">
																			<c:set var="rliked"
																				value="<%=rlikeSvc.doesrliked(userId, replyVO.getReplySN())%>"></c:set>
																		</c:if>
																		<c:choose>
																			<c:when test="${!empty userId &&  !rliked}">
																				<button class="btn btn-success" type="submit">收回推</button>
																			</c:when>
																			<c:otherwise>
																				<button class="btn btn-success" type="submit"
																					${empty userId? "disabled":""}>推推</button>
																			</c:otherwise>
																		</c:choose>
																	</form>
																</div>
																<div class="card col-auto">
																	<div>${replyVO.replyLikes}</div>
																</div>
															</div>
														</div>
														<div class="col-auto d-grid gap-2 d-md-flex">
															<form METHOD="post"
																ACTION="<%=request.getContextPath()%>/article/report.do">
																<input type="hidden" name="action" value="toreport">
																<input type="hidden" name="articleSN"
																	value="${articleVO.articleSN}"> <input
																	type="hidden" name="replySN" value="${replyVO.replySN}">
																<input type="hidden" name="floor"
																	value="${floor.count + 1 }">
																<button ${empty userId? 'style="visibility:hidden"':''}
																	class="btn btn-success" type="submit">檢舉</button>
															</form>
														</div>
													</div>
												</div>
											</div>
										</div>
										<!-- !文章內容外框 -->
									</c:when>
									<c:otherwise>
										<!-- 文章隱藏框框 -->
										<div class="row mt-3">
											<div class="col-auto">
												<div class="sticky-top" style="width: 102px"></div>
											</div>

											<div class="card col-10">
												<div class="container p-3">
													<p class="fs-5 mb-0 text-success">${floor.count + 1 }樓:此文已刪除</p>
												</div>
											</div>

										</div>
										<!-- !文章隱藏框框 -->
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</c:if>
						<!--回覆文章大樓 -->



						<!-- 回覆框框 -->
						<c:if test="${!empty userId}">
							<div class="row mt-3">
								<div class="col-auto">
									<div class="sticky-top" style="width: 102px"></div>
								</div>

								<div class="card col-10 pb-3">
									<div class="container mt-4">
										<p class="fs-4 mb-0 text-success">回覆</p>
										<form METHOD="post"
											ACTION="<%=request.getContextPath()%>/article/reply.do">
											<!--隱藏input -->
											<input type="hidden" name="userId" value="${userId}">
											<input type="hidden" name="articleSN" value="<%=articleSN%>">
											<!--!隱藏input -->
											<script
												src="https://cdn.ckeditor.com/4.7.3/standard/ckeditor.js"></script>
											<textarea name="replyContent"></textarea>
											<script>
												CKEDITOR
														.replace("replyContent");
											</script>
											<div
												class="col-auto d-grid gap-2 d-md-flex mt-2 justify-content-md-end">
												<input type="hidden" name="action" value="insert">
												<button class="btn btn-success" type="submit">送出</button>
											</div>
										</form>

									</div>
								</div>
							</div>
						</c:if>
						<!-- !回覆框框 -->

					</div>
					<!-- 右邊row -->
				</div>
				<!-- 右邊本體 -->
			</div>
		</div>
	</section>
	<!-- !論壇回覆本體 -->
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