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
	<%-- ���~��C --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">�Эץ��H�U���~:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<%-- !���~��C --%>
	
<%-- <%  int rowsPerPage = 3;  //�C��������     --%>
<!-- //     int rowNumber=0;      //�`���� -->
<!-- //     int pageNumber=0;     //�`����       -->
<!-- //     int whichPage=1;      //�ĴX�� -->
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
<!-- //     } catch (NumberFormatException e) { //�Ĥ@�����檺�ɭ� -->
<!-- //        whichPage=1; -->
<!-- //        pageIndex=0; -->
<!-- //     } catch (ArrayIndexOutOfBoundsException e) { //�`���Ƥ��~�����~���� -->
<!-- //          if (pageNumber>0){ -->
<!-- //               whichPage=pageNumber; -->
<!-- //               pageIndex=pageIndexArray[pageNumber-1]; -->
<!-- //          } -->
<!-- //     }  -->
<%-- %> --%>

<%-- <%if (pageNumber>0){%> --%>
<%--   <b><font color=red>��<%=whichPage%>/<%=pageNumber%>��</font></b> --%>
<%-- <%}%> --%>

<%-- <b>���� �X �d �� �� �� �p �U �� ��: �@<font color=red><%=rowNumber%></font>��</b> --%>

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