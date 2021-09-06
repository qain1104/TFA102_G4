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
            <label class="btn btn-outline-success btn-lg" for="btnradio1">中正</label>
        
            <input type="radio" class="btn-check" name="btnradio" id="btnradio2" autocomplete="off" onclick="location.href='Appointment_notice.html#001'">
            <label class="btn btn-outline-success btn-lg" for="btnradio2">內湖</label>
        
            <input type="radio" class="btn-check" name="btnradio" id="btnradio3" autocomplete="off" onclick="location.href='Appointment_notice.html#002'">
            <label class="btn btn-outline-success btn-lg" for="btnradio3">文山</label>

            <input type="radio" class="btn-check" name="btnradio" id="btnradio4" autocomplete="off">
            <label class="btn btn-outline-success btn-lg" for="btnradio4">中山</label>

            <input type="radio" class="btn-check" name="btnradio" id="btnradio5" autocomplete="off">
            <label class="btn btn-outline-success btn-lg" for="btnradio5">信義</label>

            <input type="radio" class="btn-check" name="btnradio" id="btnradio6" autocomplete="off">
            <label class="btn btn-outline-success btn-lg" for="btnradio6">北投</label>

            <input type="radio" class="btn-check" name="btnradio" id="btnradio7" autocomplete="off">
            <label class="btn btn-outline-success btn-lg" for="btnradio7">大同</label>

            <input type="radio" class="btn-check" name="btnradio" id="btnradio8" autocomplete="off">
            <label class="btn btn-outline-success btn-lg" for="btnradio8">萬華</label>

            <input type="radio" class="btn-check" name="btnradio" id="btnradio9" autocomplete="off">
            <label class="btn btn-outline-success btn-lg" for="btnradio9">南港</label>

            <input type="radio" class="btn-check" name="btnradio" id="btnradio10" autocomplete="off">
            <label class="btn btn-outline-success btn-lg" for="btnradio10">松山</label>

            <input type="radio" class="btn-check" name="btnradio" id="btnradio11" autocomplete="off">
            <label class="btn btn-outline-success btn-lg" for="btnradio11">士林</label>

            <input type="radio" class="btn-check" name="btnradio" id="btnradio12" autocomplete="off">
            <label class="btn btn-outline-success btn-lg" for="btnradio12">大安</label>
        </div>
    </div>
    <p></p><hr /><p></p>
    <div class="ji">
        <h1><b>預約須知(中正)</b></h1>
    </div>
    <div class="paying container">
            <h2><b>場地預約須知</b></h2>
            <h3><b>1.場地預約開放時間：</b></h3>
            <h4>使用日前7日至14日內開放場館營業時間內場地網路預約(如2月1日可預約2月8日至2月15日內場地)，年節期間另行公告、
                遇天災則視臺北市政府宣布是否達停班停課標準，或因舉辦活動、訓練期間及配合市政府活動等，得暫停開放預約。
            </h4>
            <h3><b>2.可預約場地數：</b></h3>
            <h4>各中心需視實際場地使用狀況開放預約場館數量，個人限定每人每次可預約2次場次(2場地1小時或1場地2小時)。
                ※公司、行號、學校、機關團體舉辦活動之場地預約，請電洽各中心服務台※
            </h4>
            <h3><b>3.付款期限：</b></h3>
            <h4>
                網路預約成功後請於使用場地日7日前完成付款、異動或取消作業(如預約2月8日租用，必須於2月1日前完成)，
                若未自行完成上述作業將記錄異常一次。異常紀錄帳號，各中心有權依其預約管理辦法施以該帳號停權相關措施，希望民眾預約後依期限付款，或於期限內自行取消，以免影響後續預約權益。
            </h4>
            <h3><b>4.付款逾時處理：</b></h3>
            <h4>逾時未完成付款之預約紀錄將不予保留，原預約時段場地將釋出開放給其他民眾電話預約或臨租。</h4>
            <h3><b>5.球具租借需求：</b></h3>
            <h4>如需球具請自備，或可於現場櫃檯租借(球具租借費用請逕洽中心櫃檯工作人員)。</h4><br>
    </div>
    <p></p><hr /><p></p>
    <div class="ji">
        <a name="001" id="001" ></a>
        <!-- 錨點 -->
        <h1><b>預約須知(內湖)</b></h1>
    </div>
    <div class="paying container">
            <h2><b>場地預約須知</b></h2>
            <h3><b>1.場地預約開放時間：</b></h3>
            <h4>使用日前7日至14日內開放場館營業時間內場地網路預約(如2月1日可預約2月8日至2月15日內場地)，年節期間另行公告、
                遇天災則視臺北市政府宣布是否達停班停課標準，或因舉辦活動、訓練期間及配合市政府活動等，得暫停開放預約。
            </h4>
            <h3><b>2.可預約場地數：</b></h3>
            <h4>各中心需視實際場地使用狀況開放預約場館數量，個人限定每人每次可預約2次場次(2場地1小時或1場地2小時)。
                ※公司、行號、學校、機關團體舉辦活動之場地預約，請電洽各中心服務台※
            </h4>
            <h3><b>3.付款期限：</b></h3>
            <h4>
                網路預約成功後請於10分鐘內完成刷卡付款，完成刷卡付款方為預約成功，操作逾時請重新預約、
                異動或取消作業請於場地使用當日之「前2日」可線上取消場地，並於原場地使用當日之「7日內」至中心服務台辦理退費，
                若未自行完成上述作業將記錄異常一次。異常紀錄帳號，各中心有權依其預約管理辦法施以該帳號停權相關措施，
                希望民眾預約後依期限付款，或於期限內自行取消，以免影響後續預約權益。
            </h4>
            <h3><b>4.付款逾時處理：</b></h3>
            <h4>逾時未完成付款之預約紀錄將不予保留，原預約時段場地將釋出開放給其他民眾電話預約或臨租。</h4>
            <h3><b>5.球具租借需求：</b></h3>
            <h4>如需球具請自備，或可於現場櫃檯租借(球具租借費用請逕洽中心櫃檯工作人員)。</h4><br>
    </div>
    <p></p><hr /><p></p>
    <div class="ji">
        <a name="002" id="002" ></a>
        <h1><b>預約須知(文山)</b></h1>
    </div>
    <div class="paying container">
            <h2><b>場地預約須知</b></h2>
            <h3><b>1.場地預約開放時間：</b></h3>
            <h4>使用日前7日至14日內開放場館營業時間內場地網路預約(如2月1日可預約2月8日至2月15日內場地)，年節期間另行公告、
                遇天災則視臺北市政府宣布是否達停班停課標準，或因舉辦活動、訓練期間及配合市政府活動等，得暫停開放預約。
            </h4>
            <h3><b>2.可預約場地數：</b></h3>
            <h4>各中心需視實際場地使用狀況開放預約場館數量，個人限定每人每次可預約2次場次(2場地1小時或1場地2小時)。
                ※公司、行號、學校、機關團體舉辦活動之場地預約，請電洽各中心服務台※
            </h4>
            <h3><b>3.付款期限：</b></h3>
            <h4>
                網路預約成功後請於10分鐘內完成刷卡付款，完成刷卡付款方為預約成功，操作逾時請重新預約、
                異動或取消作業請於場地使用當日之「前2日」可線上取消場地，並於原場地使用當日之「7日內」至中心服務台辦理退費，
                若未自行完成上述作業將記錄異常一次。異常紀錄帳號，各中心有權依其預約管理辦法施以該帳號停權相關措施，
                希望民眾預約後依期限付款，或於期限內自行取消，以免影響後續預約權益。
            </h4>
            <h3><b>4.付款逾時處理：</b></h3>
            <h4>逾時未完成付款之預約紀錄將不予保留，原預約時段場地將釋出開放給其他民眾電話預約或臨租。</h4>
            <h3><b>5.球具租借需求：</b></h3>
            <h4>如需球具請自備，或可於現場櫃檯租借(球具租借費用請逕洽中心櫃檯工作人員)。</h4>
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