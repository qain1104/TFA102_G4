
function cal_quantity(){
    var total_quantity = 0;   
    $("li input").each(function(index){
        total_quantity += parseInt($(this).val());
    })
    $("span#morder_quantity").html(total_quantity);
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
$(function(){


    $(".table").on("click", ".btn-delete", function(e){
        let productSpecId = $(this).closest("tr").find("td.merchandise_size").attr("id");
        let itemQuantity = $(this).closest("tr").find("td input").val();
        let that = $(this);
        let item = $(this).closest("tr");
        // $(this).closest("tr").remove();


        let r = confirm("請確認是否刪除？");
        if (r){
      
          $.ajax({
            url: "http://localhost:8081/Sportify/shopping/shoppingcart.do",           // 資料請求的網址
            type: "POST",                  // GET | POST | PUT | DELETE | PATCH
            data: {"action": "delete",
                    "productSpecId": productSpecId,
                    "itemQuantity": itemQuantity
                    },                  // 傳送資料到指定的 url
            //processData: false,
            // dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
            contentType:"application/x-www-form-urlencoded",   //"application/x-www-form-urlencoded""application/json"
            beforeSend: function(){       // 在 request 發送之前執行
                
            },
            success: function(data){      // request 成功取得回應後執行

              if(data == "delet complete"){
                item.remove();
              }

              cal_quantity();
              cal_single_amount();
              cal_total_amount();
      
            },
            error: function(xhr){         // request 發生錯誤的話執行
              console.log(xhr);
              alert("刪除失敗，請稍後再試");
            }
          });
      
      
        }
    })

    $(".table").on("click", "span.btn-minu", function(){

        let productSpecId = $(this).closest("tr").find("td.merchandise_size").attr("id");
        let itemQuantity = $(this).closest("tr").find("td input").val();
        let input_val = $(this).closest("ul").find("input");
        let span_html = $(this).closest("ul").find("span.var-value");
        let quantity_val_min = parseInt($(this).closest("ul").find("input").val());
        if (quantity_val_min > 1){ 

            $.ajax({
                url: "http://localhost:8081/Sportify/shopping/shoppingcart.do",           // 資料請求的網址
                type: "POST",                  // GET | POST | PUT | DELETE | PATCH
                data: {"action": "deleteOnePiece",
                        "productSpecId": productSpecId,
                        "itemQuantity": itemQuantity
                        },                  // 傳送資料到指定的 url
                //processData: false,
                // dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
                contentType:"application/x-www-form-urlencoded",   //"application/x-www-form-urlencoded""application/json"
                beforeSend: function(){       // 在 request 發送之前執行
                    
                },
                success: function(data){      // request 成功取得回應後執行
    
                  if(data == "delet complete"){
                    input_val.val(quantity_val_min - 1);
                    span_html.html(quantity_val_min - 1);
                  }

                  cal_quantity();
                  cal_single_amount();
                  cal_total_amount();
          
                },
                error: function(xhr){         // request 發生錯誤的話執行
                  console.log(xhr);
                  alert("數量更改無效，請稍後再試");
                }
              });
        } else {
            alert("數量為1，無法再減少");
        }
    })

    $(".table").on("click", "span.btn-plu", function(){
        let productSpecId = $(this).closest("tr").find("td.merchandise_size").attr("id");
        let itemQuantity = $(this).closest("tr").find("td input").val();
        let quantity_val_plu = parseInt($(this).closest("ul").find("input").val()); 
        let input_val = $(this).closest("ul").find("input");
        let span_html =   $(this).closest("ul").find("span.var-value");


        $.ajax({
            url: "http://localhost:8081/Sportify/shopping/shoppingcart.do",           // 資料請求的網址
            type: "POST",                  // GET | POST | PUT | DELETE | PATCH
            data: {"action": "addOnePiece",
                    "productSpecId": productSpecId,
                    "itemQuantity": itemQuantity
                    },                  // 傳送資料到指定的 url
            //processData: false,
            // dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
            contentType:"application/x-www-form-urlencoded",   //"application/x-www-form-urlencoded""application/json"
            beforeSend: function(){       // 在 request 發送之前執行
                
            },
            success: function(data){      // request 成功取得回應後執行

              if(data == "add complete"){
                input_val.val(quantity_val_plu + 1);
                span_html.html(quantity_val_plu + 1);
              }

              cal_quantity();
              cal_single_amount();
              cal_total_amount();
      
            },
            error: function(xhr){         // request 發生錯誤的話執行
              console.log(xhr);
              alert("數量更改無效，請稍後再試");
            }
          });
    })
})







