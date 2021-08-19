var inputReceiver = $("input#inputReceiver");
var inputPhone = $("input#inputReceiverPhone");
var inputAddress = $("input#inputReceiverAddress");
var receiver_data = $("div.receiver_data");

function cal_quantity(){
    var total_quantity = 0;   
    $("li input").each(function(index){
        total_quantity += parseInt($(this).val());
    })
    $(".total_quantity").html(`共 <strong>${total_quantity}</strong> 件`);
}

function cal_single_amount(){
    $("td.merchandise_amount").each(function(index, element){
        let price = parseInt($(element).siblings("td.merchandise_price").html());
        let quantity = parseInt($(element).siblings("td.merchandise_quantity").find("li input").val());
        let amount = price*quantity;
        $(element).html(amount);
    });
}

function cal_total_amount(){
    var total_amount = 0;
    $("td.merchandise_amount").each(function(index, element){
        var single_amount = parseInt($(element).html());
        total_amount += single_amount;
    });
    $("div.total_amount").html(`總金額: ${total_amount}`);
}

function same_as_buyer(){
        if($("input#same_as_buyer").attr("checked") == "checked"){
            $("input#inputReceiver").val($("input#inputBuyer").val());
            $("input#inputReceiverPhone").val($("input#inputPhone").val());
            $("input#inputReceiverAddress").val($("input#inputAddress").val());
        } else {
            $("input#inputReceiver").val("");
            $("input#inputReceiverPhone").val("");
            $("input#inputReceiverAddress").val("");
        }
}

$(function(){
    cal_quantity();
    cal_single_amount();
    cal_total_amount()

    $("input#home_delivery").on("click", function(){
        let home_html = `<div class="col-md-3">
                            <label for="inputReceiver" class="form-label">收貨人</label>
                            <input type="text" class="form-control" id="inputReceiver">
                        </div>
                        <div class="col-md-3 offset-md-1">
                            <label for="inputReceiverPhone" class="form-label">聯絡電話</label>
                            <input type="text" class="form-control" id="inputReceiverPhone">
                        </div>
                        <div class="col-10">
                            <label for="inputReceiverAddress" class="form-label">收貨地址</label>
                            <input type="text" class="form-control" id="inputReceiverAddress">
                        </div>`;
        receiver_data.html(home_html);
        if($("div.receiver_data").hasClass("store")){
            $("div.receiver_data").removeClass("store")
            $("div.receiver_data").addClass("home");
        }
        same_as_buyer();
        $("div.delivery_fee").html("運費: 100");
    })

    $("input#convenience_store").on("click", function(){
        let store_html = `<div class="col-md-3">
                            <label for="inputReceiver" class="form-label">收貨人</label>
                            <input type="text" class="form-control" id="inputReceiver">
                        </div>
                        <div class="col-md-3 offset-md-1">
                            <label for="inputReceiverPhone" class="form-label">聯絡電話</label>
                            <input type="text" class="form-control" id="inputReceiverPhone">
                        </div>
                        <div class="col-md-3">
                        </div>
                        <div class="col-md-3">
                            <label for="inputCode" class="form-label">門市編號</label>
                            <input type="text" class="form-control" id="inputCode">
                        </div>
                        <div class="col-md-3 offset-md-1">
                            <label for="inputStore" class="form-label">門市名稱</label>
                            <input type="text" class="form-control" id="inputStore">
                        </div>
                        <div class="col-10">
                            <label for="inputStoreAddress" class="form-label">門市地址</label>
                            <input type="text" class="form-control" id="inputStoreAddress">
                        </div>`;
        receiver_data.html(store_html);
        if($("div.receiver_data").hasClass("home")){
            $("div.receiver_data").removeClass("home")
            $("div.receiver_data").addClass("store");
        }
        same_as_buyer();
        $("div.delivery_fee").html("運費: 80");
    })

    $("input#same_as_buyer").on("click", function(){
        same_as_buyer();
    })

    // 勾選same_as_buyer會自動將資料帶入欄位中
    // $("input#same_as_buyer").delegate("div.receiver_data", "change", function(){
    //     if($("input#same_as_buyer").attr("checked") == "checked"){
    //         same_as_buyer();
    //         inputReceiver.val($("input#inputBuyer").val());
    //     }
    // })

    $("div.next_step").on("click", function(e){
        if($("input#inputBuyer").val() === ""){
            alert("請輸入資料");
            e.preventDefault();
        }
        if($("input#inputPhone").val() === ""){
            alert("請輸入資料");
            e.preventDefault();
        }
        if($("input#inputAddress").val() === ""){
            alert("請輸入資料");
            e.preventDefault();
        }
    })

})







