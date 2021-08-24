<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.GeneralUser.model.*"%>

<%
  GeneralUserVO generalUserVO = (GeneralUserVO) request.getAttribute("generalUserVO"); //CorpUserServlet.java (Concroller) 存入req的corpUserVO物件 (包括幫忙取出的corpUserVO, 也包括輸入資料錯誤時的corpUserVO物件)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>一般會員資料修改 - update_generalUser_input.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>一般會員資料修改 - update_generalUser_input.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/generalUser/GeneralUserServlet.do" name="form1">
<table>
	<tr>
		<td>一般會員編號:<font color=red><b>*</b></font></td>
		<td><%=generalUserVO.getUserId()%></td>
	</tr>
	
	<tr>
		<td>註冊狀態:</td>
		<td>
		<%
		if(generalUserVO.getRegisterStatus()==0) {
			out.print("未完成驗證");
		} else if(generalUserVO.getRegisterStatus()==1) {
			out.print("已通過驗證");
		}
		%></td>
	</tr>
	
	<tr>
		<td>一般會員帳號:</td>
		<td><%=generalUserVO.getUserAccount()%></td>
	</tr>
	
	<tr>
		<td>一般會員密碼:</td>
		<td>
		<%
		for(int i = 0; i<=(generalUserVO.getUserPassword()).length() ; i++){
			out.println("*");
		}
		%>
		</td>
		<td><button>更改密碼</button></td>
	</tr>
	
	
	<tr>
		<td>姓名:</td>
		<td><%=generalUserVO.getUserName()%></td>
	</tr>
	
	<tr>
		<td>身分證字號:</td>
		<td><%=generalUserVO.getId()%></td>
	</tr>
	
	<tr>
		<td>信箱:</td>
		<td><%=generalUserVO.getEmail()%></td>
	</tr>
	
	<tr>
		<td>地址:</td>
		<td><input type="TEXT" name="address" value="<%=generalUserVO.getAddress()%>"/></td>
	</tr>
	
	<tr>
		<td>電話:</td>
		<td><input type="TEXT" name="phone" value="<%=generalUserVO.getPhone()%>"/></td>
	</tr>
	
	<tr>
		<td>地址:</td>
		<td><input type="TEXT" name="address" value="<%=generalUserVO.getAddress()%>"/></td>
	</tr>
	
	<tr>
		<td>大頭貼:</td>
		<td><input type="file" name="profilePic" accept="image/*,.pdf" value="<%=generalUserVO.getProfilePic()%>"/></td>
	</tr>
	
	<tr>
		<td>帳號創建時間</td>
		<td><%=generalUserVO.getCreatedTime()%></td>
	</tr>
	
	<tr>
		<td>性別</td>
		<td><%if(generalUserVO.getGender()==0) {
			out.print("女");
		} else if(generalUserVO.getGender()==1) {
			out.print("男");
		}
		%></td>
	</tr>

	

</table>
<!-- 以下放不給修改的參數值 -->
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="userId" value="<%=generalUserVO.getUserId()%>">
<input type="hidden" name="registerStatus" value="<%=generalUserVO.getRegisterStatus()%>">
<input type="hidden" name="userAccount" value="<%=generalUserVO.getUserAccount()%>">

<input type="hidden" name="userPassword" value="<%=generalUserVO.getUserPassword()%>">   <%--如果使用者沒選改密碼應該要送這個出去 --%>
<input type="hidden" name="profilePic" value="<%=generalUserVO.getProfilePic()%>"> 

<input type="hidden" name="userName" value="<%=generalUserVO.getUserName()%>">
<input type="hidden" name="id" value="<%=generalUserVO.getId()%>">
<input type="hidden" name="email" value="<%=generalUserVO.getEmail()%>">
<input type="hidden" name="createdTime" value="<%=generalUserVO.getCreatedTime()%>">
<input type="hidden" name="gender" value="<%=generalUserVO.getGender()%>">
<input type="submit" value="送出修改"></FORM>
</body>


</html>