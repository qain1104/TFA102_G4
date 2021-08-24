<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
</head>
<body>
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
	
<%-- <%  int rowsPerPage = 3;  //每頁的筆數     --%>
<!-- //     int rowNumber=0;      //總筆數 -->
<!-- //     int pageNumber=0;     //總頁數       -->
<!-- //     int whichPage=1;      //第幾頁 -->
<!-- //     int pageIndexArray[]=null; -->
<!-- //     int pageIndex=0;  -->
<%-- %> --%>

<%-- <%   --%>
<!-- //     rowNumber=list.size(); -->
<!-- //     if (rowNumber%rowsPerPage !=0) -->
<!-- //          pageNumber=rowNumber/rowsPerPage + 1; -->
<!-- //     else pageNumber=rowNumber/rowsPerPage;     -->

<!-- //     pageIndexArray=new int[pageNumber];  -->
<!-- //     for (int i=1 ; i<=pageIndexArray.length ; i++) -->
<!-- //          pageIndexArray[i-1]=i*rowsPerPage-rowsPerPage; -->
<%-- %> --%>

<%-- <%  try { --%>
<!-- //        whichPage = Integer.parseInt(request.getParameter("whichPage")); -->
<!-- //        pageIndex=pageIndexArray[whichPage-1]; -->
<!-- //     } catch (NumberFormatException e) { //第一次執行的時候 -->
<!-- //        whichPage=1; -->
<!-- //        pageIndex=0; -->
<!-- //     } catch (ArrayIndexOutOfBoundsException e) { //總頁數之外的錯誤頁數 -->
<!-- //          if (pageNumber>0){ -->
<!-- //               whichPage=pageNumber; -->
<!-- //               pageIndex=pageIndexArray[pageNumber-1]; -->
<!-- //          } -->
<!-- //     }  -->
<%-- %> --%>

<%-- <%if (pageNumber>0){%> --%>
<%--   <b><font color=red>第<%=whichPage%>/<%=pageNumber%>頁</font></b> --%>
<%-- <%}%> --%>

<%-- <b>●符 合 查 詢 條 件 如 下 所 示: 共<font color=red><%=rowNumber%></font>筆</b> --%>

<!--  <nav aria-label="Page navigation example"> -->
<!-- 	<ul class="pagination justify-content-center"> -->

<%-- <c:choose> --%>
<%-- <c:when test="<%=rowNumber<5%>"> --%>
<%-- <%for(int i;i<=rowNumber;i++){%> --%>

<%-- <%}%> --%>
<%-- </c:when> --%>
<%-- </c:choose> --%>

</ul>
</nav>
</body>
</html>