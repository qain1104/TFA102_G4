<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>

<html>
<head>
<meta charset="BIG5">
<title>Sportify</title>
	<link rel="shortcut icon" href="<%= request.getContextPath() %>/assets/img/logo1.png" type="image/x-icon" />
	<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;200;300;400;500;700;900&display=swap">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/fontawesome.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/templatemo.css">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/custom.css">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/venue1.css">
</head>
<body>
<jsp:include page="/header.jsp" flush="true" />
<br>
<div class="paying container ji">
        <div class="btn-group"  role="group" aria-label="Basic radio toggle button group">

            <input type="radio" class="btn-check" name="btnradio" id="btnradio1" autocomplete="off" onclick="location.href='#'">
            <label class="btn btn-outline-success btn-lg" for="btnradio1">����</label>
        
            <input type="radio" class="btn-check" name="btnradio" id="btnradio2" autocomplete="off" onclick="location.href='Appointment_notice.html#001'">
            <label class="btn btn-outline-success btn-lg" for="btnradio2">����</label>
        
            <input type="radio" class="btn-check" name="btnradio" id="btnradio3" autocomplete="off" onclick="location.href='Appointment_notice.html#002'">
            <label class="btn btn-outline-success btn-lg" for="btnradio3">��s</label>

            <input type="radio" class="btn-check" name="btnradio" id="btnradio4" autocomplete="off">
            <label class="btn btn-outline-success btn-lg" for="btnradio4">���s</label>

            <input type="radio" class="btn-check" name="btnradio" id="btnradio5" autocomplete="off">
            <label class="btn btn-outline-success btn-lg" for="btnradio5">�H�q</label>

            <input type="radio" class="btn-check" name="btnradio" id="btnradio6" autocomplete="off">
            <label class="btn btn-outline-success btn-lg" for="btnradio6">�_��</label>

            <input type="radio" class="btn-check" name="btnradio" id="btnradio7" autocomplete="off">
            <label class="btn btn-outline-success btn-lg" for="btnradio7">�j�P</label>

            <input type="radio" class="btn-check" name="btnradio" id="btnradio8" autocomplete="off">
            <label class="btn btn-outline-success btn-lg" for="btnradio8">�U��</label>

            <input type="radio" class="btn-check" name="btnradio" id="btnradio9" autocomplete="off">
            <label class="btn btn-outline-success btn-lg" for="btnradio9">�n��</label>

            <input type="radio" class="btn-check" name="btnradio" id="btnradio10" autocomplete="off">
            <label class="btn btn-outline-success btn-lg" for="btnradio10">�Q�s</label>

            <input type="radio" class="btn-check" name="btnradio" id="btnradio11" autocomplete="off">
            <label class="btn btn-outline-success btn-lg" for="btnradio11">�h�L</label>

            <input type="radio" class="btn-check" name="btnradio" id="btnradio12" autocomplete="off">
            <label class="btn btn-outline-success btn-lg" for="btnradio12">�j�w</label>
        </div>
    </div>
    <p></p><hr /><p></p>
    <div class="ji">
        <h1><b>�w������(����)</b></h1>
    </div>
    <div class="paying container">
            <h2><b>���a�w������</b></h2>
            <h3><b>1.���a�w���}��ɶ��G</b></h3>
            <h4>�ϥΤ�e7���14�餺�}����]��~�ɶ������a�����w��(�p2��1��i�w��2��8���2��15�餺���a)�A�~�`�����t�椽�i�B
                �J�Ѩa�h���O�_���F���ť��O�_�F���Z���ҼзǡA�Φ]�|�쬡�ʡB�V�m�����ΰt�X���F�����ʵ��A�o�Ȱ��}��w���C
            </h4>
            <h3><b>2.�i�w�����a�ơG</b></h3>
            <h4>�U���߻ݵ���ڳ��a�ϥΪ��p�}��w�����]�ƶq�A�ӤH���w�C�H�C���i�w��2������(2���a1�p�ɩ�1���a2�p��)�C
                �����q�B�渹�B�ǮաB���������|�쬡�ʤ����a�w���A�йq���U���ߪA�ȥx��
            </h4>
            <h3><b>3.�I�ڴ����G</b></h3>
            <h4>
                �����w�����\��Щ�ϥγ��a��7��e�����I�ڡB���ʩΨ����@�~(�p�w��2��8�鯲�ΡA������2��1��e����)�A
                �Y���ۦ槹���W�z�@�~�N�O�����`�@���C���`�����b���A�U���ߦ��v�̨�w���޲z��k�I�H�ӱb�����v�������I�A�Ʊ�����w����̴����I�ڡA�Ω�������ۦ�����A�H�K�v�T����w���v�q�C
            </h4>
            <h3><b>4.�I�ڹO�ɳB�z�G</b></h3>
            <h4>�O�ɥ������I�ڤ��w�������N�����O�d�A��w���ɬq���a�N���X�}�񵹨�L�����q�ܹw�����{���C</h4>
            <h3><b>5.�y�㯲�ɻݨD�G</b></h3>
            <h4>�p�ݲy��Ц۳ơA�Υi��{���d�i����(�y�㯲�ɶO�νгw�������d�i�u�@�H��)�C</h4><br>
    </div>
    <p></p><hr /><p></p>
    <div class="ji">
        <a name="001" id="001" ></a>
        <!-- ���I -->
        <h1><b>�w������(����)</b></h1>
    </div>
    <div class="paying container">
            <h2><b>���a�w������</b></h2>
            <h3><b>1.���a�w���}��ɶ��G</b></h3>
            <h4>�ϥΤ�e7���14�餺�}����]��~�ɶ������a�����w��(�p2��1��i�w��2��8���2��15�餺���a)�A�~�`�����t�椽�i�B
                �J�Ѩa�h���O�_���F���ť��O�_�F���Z���ҼзǡA�Φ]�|�쬡�ʡB�V�m�����ΰt�X���F�����ʵ��A�o�Ȱ��}��w���C
            </h4>
            <h3><b>2.�i�w�����a�ơG</b></h3>
            <h4>�U���߻ݵ���ڳ��a�ϥΪ��p�}��w�����]�ƶq�A�ӤH���w�C�H�C���i�w��2������(2���a1�p�ɩ�1���a2�p��)�C
                �����q�B�渹�B�ǮաB���������|�쬡�ʤ����a�w���A�йq���U���ߪA�ȥx��
            </h4>
            <h3><b>3.�I�ڴ����G</b></h3>
            <h4>
                �����w�����\��Щ�10������������d�I�ڡA������d�I�ڤ謰�w�����\�A�ާ@�O�ɽЭ��s�w���B
                ���ʩΨ����@�~�Щ���a�ϥη�餧�u�e2��v�i�u�W�������a�A�é����a�ϥη�餧�u7�餺�v�ܤ��ߪA�ȥx��z�h�O�A
                �Y���ۦ槹���W�z�@�~�N�O�����`�@���C���`�����b���A�U���ߦ��v�̨�w���޲z��k�I�H�ӱb�����v�������I�A
                �Ʊ�����w����̴����I�ڡA�Ω�������ۦ�����A�H�K�v�T����w���v�q�C
            </h4>
            <h3><b>4.�I�ڹO�ɳB�z�G</b></h3>
            <h4>�O�ɥ������I�ڤ��w�������N�����O�d�A��w���ɬq���a�N���X�}�񵹨�L�����q�ܹw�����{���C</h4>
            <h3><b>5.�y�㯲�ɻݨD�G</b></h3>
            <h4>�p�ݲy��Ц۳ơA�Υi��{���d�i����(�y�㯲�ɶO�νгw�������d�i�u�@�H��)�C</h4><br>
    </div>
    <p></p><hr /><p></p>
    <div class="ji">
        <a name="002" id="002" ></a>
        <h1><b>�w������(��s)</b></h1>
    </div>
    <div class="paying container">
            <h2><b>���a�w������</b></h2>
            <h3><b>1.���a�w���}��ɶ��G</b></h3>
            <h4>�ϥΤ�e7���14�餺�}����]��~�ɶ������a�����w��(�p2��1��i�w��2��8���2��15�餺���a)�A�~�`�����t�椽�i�B
                �J�Ѩa�h���O�_���F���ť��O�_�F���Z���ҼзǡA�Φ]�|�쬡�ʡB�V�m�����ΰt�X���F�����ʵ��A�o�Ȱ��}��w���C
            </h4>
            <h3><b>2.�i�w�����a�ơG</b></h3>
            <h4>�U���߻ݵ���ڳ��a�ϥΪ��p�}��w�����]�ƶq�A�ӤH���w�C�H�C���i�w��2������(2���a1�p�ɩ�1���a2�p��)�C
                �����q�B�渹�B�ǮաB���������|�쬡�ʤ����a�w���A�йq���U���ߪA�ȥx��
            </h4>
            <h3><b>3.�I�ڴ����G</b></h3>
            <h4>
                �����w�����\��Щ�10������������d�I�ڡA������d�I�ڤ謰�w�����\�A�ާ@�O�ɽЭ��s�w���B
                ���ʩΨ����@�~�Щ���a�ϥη�餧�u�e2��v�i�u�W�������a�A�é����a�ϥη�餧�u7�餺�v�ܤ��ߪA�ȥx��z�h�O�A
                �Y���ۦ槹���W�z�@�~�N�O�����`�@���C���`�����b���A�U���ߦ��v�̨�w���޲z��k�I�H�ӱb�����v�������I�A
                �Ʊ�����w����̴����I�ڡA�Ω�������ۦ�����A�H�K�v�T����w���v�q�C
            </h4>
            <h3><b>4.�I�ڹO�ɳB�z�G</b></h3>
            <h4>�O�ɥ������I�ڤ��w�������N�����O�d�A��w���ɬq���a�N���X�}�񵹨�L�����q�ܹw�����{���C</h4>
            <h3><b>5.�y�㯲�ɻݨD�G</b></h3>
            <h4>�p�ݲy��Ц۳ơA�Υi��{���d�i����(�y�㯲�ɶO�νгw�������d�i�u�@�H��)�C</h4>
    </div>

<jsp:include page="/footer.jsp" flush="true" />
		<!-- Start Script -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js"></script>
    <script src="<%=request.getContextPath() %>/assets/js/jquery-3.6.0.min.js"></script>
    <script src="<%=request.getContextPath() %>/assets/js/jquery-migrate-1.2.1.min.js"></script>
    <script src="<%=request.getContextPath() %>/assets/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <script src="<%=request.getContextPath() %>/assets/js/templatemo.js"></script>
    <script src="<%=request.getContextPath() %>/assets/js/custom.js"></script>
    <script src="<%= request.getContextPath() %>/assets/js/general_order.js"></script>
    <!-- End Script -->
</body>
</html>