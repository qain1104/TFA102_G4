<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="card sticky-top">
	<div class="list-group list-group-flush">
		<%
			int whichClass = 0;
		%>
		<a
			href="<%=request.getContextPath()%>/article/article.jsp?whichClass=0"
			class="list-group-item list-group-item-action list-group-item-success"
			aria-current="true"> 論壇首頁 </a> <a
			href="<%=request.getContextPath()%>/article/article.jsp?whichClass=1"
			class="list-group-item list-group-item-action">運動休閒</a> <a
			href="<%=request.getContextPath()%>/article/article.jsp?whichClass=2"
			class="list-group-item list-group-item-action">商品分享</a> <a
			href="<%=request.getContextPath()%>/article/article.jsp?whichClass=3"
			class="list-group-item list-group-item-action">運動賽事</a> <a
			${empty userId? 'style="display:none"':''}
			href="<%=request.getContextPath()%>/article/article.jsp?whichClass=4"
			class="list-group-item list-group-item-action">我的文章</a>
	</div>
	<div class="list-group list-group-flush">
		<a  href="<%=request.getContextPath()%>/sportsGroup/sportsGroup.jsp?whichClass=0"
			class="list-group-item list-group-item-action list-group-item-success"
			aria-current="true"> 揪團首頁 </a><a ${empty userId? 'style="display:none"':''} href="<%=request.getContextPath()%>/sportsGroup/sportsGroup.jsp?whichClass=1"
			class="list-group-item list-group-item-action">我的揪團</a>
	</div>
</div>