<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.CorpUser.model.*"%>

<%
  CorpUserVO  corpUserVO = (CorpUserVO) request.getAttribute("corpUserVO"); //CorpUserServlet.java (Concroller) �s�Jreq��corpUserVO���� (�]�A�������X��corpUserVO, �]�]�A��J��ƿ��~�ɪ�corpUserVO����)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>���~�|����ƭק� - update_corpUser_input.jsp</title>

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
		 <h3>���~�|����ƭק� - update_corpUser_input.jsp</h3>
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

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/corpUser/CorpUserServlet.do" name="form1">
<table>
	<tr>
		<td>���~�|���s��:<font color=red><b>*</b></font></td>
		<td><%=corpUserVO.getCorpUserId()%></td>
	</tr>
	
	<tr>
		<td>���U���A:</td>
		<td>
		<%
		if(corpUserVO.getRegisterStatus()==0) {
			out.print("����������");
		} else if(corpUserVO.getRegisterStatus()==1) {
			out.print("�w�q�L����");
		}
		%></td>
	</tr>
	
	<tr>
		<td>���~�|���b��:</td>
		<td><%=corpUserVO.getCorpAccount()%></td>
	</tr>
	
	<tr>
		<td>���~�|���K�X:</td>
		<td>
		<%
		for(int i = 0; i<=(corpUserVO.getCorpAccount()).length() ; i++){
			out.println("*");
		}
		%>
		</td>
		<td><button>���K�X</button></td>
	</tr>
	
	
	<tr>
		<td>���q�W��:</td>
		<td><%=corpUserVO.getCompanyName()%></td>
	</tr>
	
	<tr>
		<td>���q�渹:</td>
		<td><%=corpUserVO.getLtdNo()%></td>
	</tr>
	
	<tr>
		<td>�H�c:</td>
		<td><%=corpUserVO.getEmail()%></td>
	</tr>
	
	<tr>
		<td>�q��:</td>
		<td><input type="TEXT" name="phone" value="<%=corpUserVO.getPhone()%>"/></td>
	</tr>
	
	<tr>
		<td>�a�}:</td>
		<td><input type="TEXT" name="address" value="<%=corpUserVO.getAddress()%>"/></td>
	</tr>
	
	<tr>
		<td>�j�Y�K:</td>
		<td><input type="file" name="profilePic" accept="image/*,.pdf" value="<%=corpUserVO.getProfilePic()%>"/></td>
	</tr>
	
	<tr>
		<td>�b���Ыخɶ�</td>
		<td><%=corpUserVO.getCreatedTime()%></td>
	</tr>

	

</table>
<!-- �H�U�񤣵��ק諸�Ѽƭ� -->
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="corpUserId" value="<%=corpUserVO.getCorpUserId()%>">
<input type="hidden" name="registerStatus" value="<%=corpUserVO.getRegisterStatus()%>">
<input type="hidden" name="corpAccount" value="<%=corpUserVO.getCorpAccount()%>">

<input type="hidden" name="corpPassword" value="<%=corpUserVO.getCorpPassword()%>">   <%--�p�G�ϥΪ̨S���K�X���ӭn�e�o�ӥX�h --%>
<input type="hidden" name="profilePic" value="<%=corpUserVO.getProfilePic()%>"> 

<input type="hidden" name="companyName" value="<%=corpUserVO.getCompanyName()%>">
<input type="hidden" name="ltdNo" value="<%=corpUserVO.getLtdNo()%>">
<input type="hidden" name="email" value="<%=corpUserVO.getEmail()%>">
<input type="hidden" name="createdTime" value="<%=corpUserVO.getCreatedTime()%>">
<input type="submit" value="�e�X�ק�"></FORM>
</body>



<!-- =========================================�H�U�� datetimepicker �������]�w========================================== -->

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
           theme: '',              //theme: 'dark',
 	       timepicker:false,       //timepicker:true,
 	       step: 1,                //step: 60 (�o�Otimepicker���w�]���j60����)
 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
 		   value: '<%=corpUserVO.getCreatedTime()%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // �h���S�w���t
           //startDate:	            '2017/07/10',  // �_�l��
           //minDate:               '-1970-01-01', // �h������(���t)���e
           //maxDate:               '+1970-01-01'  // �h������(���t)����
        });
        
        
   
        // ----------------------------------------------------------�H�U�ΨӱƩw�L�k��ܪ����-----------------------------------------------------------

        //      1.�H�U���Y�@�Ѥ��e������L�k���
        //      var somedate1 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});

        
        //      2.�H�U���Y�@�Ѥ��᪺����L�k���
        //      var somedate2 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});


        //      3.�H�U����Ӥ�����~������L�k��� (�]�i���ݭn������L���)
        //      var somedate1 = new Date('2017-06-15');
        //      var somedate2 = new Date('2017-06-25');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //		             ||
        //		            date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});
        
</script>
</html>