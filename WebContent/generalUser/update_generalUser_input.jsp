<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.GeneralUser.model.*"%>

<%
  GeneralUserVO generalUserVO = (GeneralUserVO) request.getAttribute("generalUserVO"); //CorpUserServlet.java (Concroller) �s�Jreq��corpUserVO���� (�]�A�������X��corpUserVO, �]�]�A��J��ƿ��~�ɪ�corpUserVO����)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>�@��|����ƭק� - update_generalUser_input.jsp</title>

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
		 <h3>�@��|����ƭק� - update_generalUser_input.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<h3>��ƭק�:</h3>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/generalUser/GeneralUserServlet.do" name="form1">
<table>
	<tr>
		<td>�@��|���s��:<font color=red><b>*</b></font></td>
		<td><%=generalUserVO.getUserId()%></td>
	</tr>
	
	<tr>
		<td>���U���A:</td>
		<td>
		<%
		if(generalUserVO.getRegisterStatus()==0) {
			out.print("����������");
		} else if(generalUserVO.getRegisterStatus()==1) {
			out.print("�w�q�L����");
		}
		%></td>
	</tr>
	
	<tr>
		<td>�@��|���b��:</td>
		<td><%=generalUserVO.getUserAccount()%></td>
	</tr>
	
	<tr>
		<td>�@��|���K�X:</td>
		<td>
		<%
		for(int i = 0; i<=(generalUserVO.getUserPassword()).length() ; i++){
			out.println("*");
		}
		%>
		</td>
		<td><button>���K�X</button></td>
	</tr>
	
	
	<tr>
		<td>�m�W:</td>
		<td><%=generalUserVO.getUserName()%></td>
	</tr>
	
	<tr>
		<td>�����Ҧr��:</td>
		<td><%=generalUserVO.getId()%></td>
	</tr>
	
	<tr>
		<td>�H�c:</td>
		<td><%=generalUserVO.getEmail()%></td>
	</tr>
	
	<tr>
		<td>�a�}:</td>
		<td><input type="TEXT" name="address" value="<%=generalUserVO.getAddress()%>"/></td>
	</tr>
	
	<tr>
		<td>�q��:</td>
		<td><input type="TEXT" name="phone" value="<%=generalUserVO.getPhone()%>"/></td>
	</tr>
	
	<tr>
		<td>�a�}:</td>
		<td><input type="TEXT" name="address" value="<%=generalUserVO.getAddress()%>"/></td>
	</tr>
	
	<tr>
		<td>�j�Y�K:</td>
		<td><input type="file" name="profilePic" accept="image/*,.pdf" value="<%=generalUserVO.getProfilePic()%>"/></td>
	</tr>
	
	<tr>
		<td>�b���Ыخɶ�</td>
		<td><%=generalUserVO.getCreatedTime()%></td>
	</tr>
	
	<tr>
		<td>�ʧO</td>
		<td><%if(generalUserVO.getGender()==0) {
			out.print("�k");
		} else if(generalUserVO.getGender()==1) {
			out.print("�k");
		}
		%></td>
	</tr>

	

</table>
<!-- �H�U�񤣵��ק諸�Ѽƭ� -->
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="userId" value="<%=generalUserVO.getUserId()%>">
<input type="hidden" name="registerStatus" value="<%=generalUserVO.getRegisterStatus()%>">
<input type="hidden" name="userAccount" value="<%=generalUserVO.getUserAccount()%>">

<input type="hidden" name="userPassword" value="<%=generalUserVO.getUserPassword()%>">   <%--�p�G�ϥΪ̨S���K�X���ӭn�e�o�ӥX�h --%>
<input type="hidden" name="profilePic" value="<%=generalUserVO.getProfilePic()%>"> 

<input type="hidden" name="userName" value="<%=generalUserVO.getUserName()%>">
<input type="hidden" name="id" value="<%=generalUserVO.getId()%>">
<input type="hidden" name="email" value="<%=generalUserVO.getEmail()%>">
<input type="hidden" name="createdTime" value="<%=generalUserVO.getCreatedTime()%>">
<input type="hidden" name="gender" value="<%=generalUserVO.getGender()%>">
<input type="submit" value="�e�X�ק�"></FORM>
</body>


</html>