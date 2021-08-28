var inputReceiver = $("input#inputReceiver");
var inputPhone = $("input#inputReceiverPhone");
var inputAddress = $("input#inputReceiverAddress");
var receiver_data = $("div.receiver_data");

// function cal_quantity(){
//     var total_quantity = 0;   
//     $("li input").each(function(index){
//         total_quantity += parseInt($(this).val());
//     })
//     $("span#morder_quantity").html(total_quantity);
// }

// function cal_single_amount(){
//     $("td.merchandise_amount").each(function(index, element){
//         let price = parseInt($(element).siblings("td.merchandise_price").html());
//         let quantity = parseInt($(element).siblings("td.merchandise_quantity").find("li input").val());
//         let amount = price*quantity;
//         $(element).html(amount);
//     });
// }

function cal_total_amount(){
    var total_amount = 0;
    var order_delivery_type = parseInt($("span#order_delivery_type").html());
  
    $("td.merchandise_amount").each(function(index, element){
        var single_amount = parseInt($(element).html());
        total_amount += single_amount;
    });
    if (!isNaN(order_delivery_type)){
        var total = total_amount + order_delivery_type
        $("span#morder_amount").html(total);
    } else {
        $("span#morder_amount").html(total_amount);
    }
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
    // cal_quantity();
    // cal_single_amount();
    // cal_total_amount()

    $("input#home_delivery").on("click", function(){

        let home_html = `<div class="col-md-3">
                            <label for="inputReceiver" class="form-label">收貨人</label>
                            <input type="text" class="form-control" id="inputReceiver" name="receiver" value="">
                        </div>
                        <div class="col-md-3 offset-md-1">
                            <label for="inputReceiverPhone" class="form-label">聯絡電話</label>
                            <input type="text" class="form-control" id="inputReceiverPhone" name="receiverPhone" value="">
                        </div>
                        <div class="col-10">
                            <label for="inputReceiverAddress" class="form-label">收貨地址</label>
                            <input type="text" class="form-control" id="inputReceiverAddress" name="receiverAddress" value="">
                        </div>`;
        receiver_data.html(home_html);
        if($("div.receiver_data").hasClass("store")){
            $("div.receiver_data").removeClass("store")
            $("div.receiver_data").addClass("home");
            $("button.pick").addClass("-none");
        }
        same_as_buyer();
        $("span#order_delivery_type").html(100);
        cal_total_amount();
    })

    $("input#convenience_store").on("click", function(){
        let store_html = `<div class="col-md-3">
                            <label for="inputReceiver" class="form-label">收貨人</label>
                            <input type="text" class="form-control" id="inputReceiver" name="receiver" value="">
                        </div>
                        <div class="col-md-3 offset-md-1">
                            <label for="inputReceiverPhone" class="form-label">聯絡電話</label>
                            <input type="text" class="form-control" id="inputReceiverPhone" name="receiverPhone" value="">
                        </div>
                        <div class="col-md-3">
                        </div>
                        <div class="col-md-3">
                            <label for="inputCode" class="form-label">門市編號</label>
                            <input type="text" class="form-control" id="inputCode" name="storeId" value="">
                        </div>
                        <div class="col-md-3 offset-md-1">
                            <label for="inputStore" class="form-label">門市名稱</label>
                            <input type="text" class="form-control" id="inputStore" name="storeName" value="">
                        </div>
                        <div class="col-10">
                            <label for="inputStoreAddress" class="form-label">門市地址</label>
                            <input type="text" class="form-control" id="inputStoreAddress" name="storeAddress" value="">
                        </div>`;
        receiver_data.html(store_html);
    
        if($("div.receiver_data").hasClass("home")){
            $("div.receiver_data").removeClass("home")
            $("div.receiver_data").addClass("store");
            $("button.pick").removeClass("-none");
        }
        same_as_buyer();
        $("span#order_delivery_type").html(80);
        cal_total_amount();
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
        if($("input#inputBuyer").val() === "" || $("input#inputPhone").val() === "" || $("input#inputAddress").val() === ""){
            alert("請輸入資料");
            e.preventDefault();
        }

        if($("input#home_delivery").attr("checked")){
            
            if($("input#inputReceiver").val() === "" || $("input#inputReceiverPhone").val() === "" || $("input#inputReceiverAddress").val() === ""){
                alert("請填寫收貨人資料");
            }
        }

        if($("input#convenience_store").attr("checked")){
            
            if($("input#inputReceiver").val() === "" || $("input#inputReceiverPhone").val() === "" || $("input#inputReceiverAddress").val() === "" || $("input#inputCode").val() === "" || $("input#inputStore").val() === "" || $("input#inputStoreAddress").val() === ""){
                alert("請填寫收貨人資料");
            }
        }

    })

})







