// 秀出有買過的商品數量
function count_number(){
    $("td.list_number").each(function(index, element){
        $(element).html(index + 1);
    })
}

// 進行評論編輯
function edit(){
    $("td.productFeedback").each(function(index, element){
        // 如果評價不為空字串，則不能編輯
        if($(element).find("span").html().trim() !== ""){
            // console.log($(element).next().find("i.fa-edit"));
            $(element).next().find("i").addClass("-none");
        } else {
            // 如果沒有評價，可以點選編輯按鈕進行編輯
            $(element).next().find("i.fa-edit").on("click", function(){
                $(element).find("span").addClass("-none");
                $(element).find("textarea").removeClass("-none");
                $(element).next().find("i.fa-edit").addClass("-none"); // 編輯按鈕隱藏
                $(element).next().find("i.fa-save").removeClass("-none"); // 秀儲存按鈕

            })
        }
        // 儲存按鈕功能
        $(element).next().find("i.fa-save").on("click", function(e){
            // 如果textarea為空字串在點擊儲存按鈕時，編輯按鈕會再秀出來，儲存按鈕則會隱藏
            if($(element).find("textarea").val().trim() === ""){
                $(element).find("span").html($(element).find("textarea").val().trim());
                $(element).find("textarea").addClass("-none");
                $(element).find("span").removeClass("-none");
                $(element).next().find("i.fa-edit").removeClass("-none");
                $(element).next().find("i.fa-save").addClass("-none");
            } else {
                // textarea有內容時則會將內容帶到span標籤，將儲存按鈕隱藏，不再秀出編輯按鈕
                $(element).find("span").html($(element).find("textarea").val().trim());
                $(element).find("textarea").addClass("-none");
                $(element).find("span").removeClass("-none");
                $(element).next().find("i.fa-save").addClass("-none");
            }
        }) 
    })
}

//star
function star(){
    $("span.star").on("click", function(e){
        // console.log($(this).closest("td").siblings("td.operation").find("i.fa-edit").hasClass("-none"));
        // 若有儲存按鈕才可以點選star
        if(!$(this).closest("td").siblings("td.operation").find("i.fa-save").hasClass("-none")){
            let star_count = $(this).attr("data-star");
            // $(this).addClass("-on");
            $(this).parent("td").find("span").each(function(index, element){
                // console.log(element);
                if($(element).attr("data-star") <= star_count){
                    if(!$(element).hasClass("-on")){
                        $(element).addClass("-on");
                    }
                } else {
                    $(element).removeClass("-on");
                }
            })
        }
    })
    
}


$(function(){
    var couponSN = $("span.couponSN");
    var modify = $("a.modify");

    count_number();
    edit();
    star();

    // 優惠券欄位有資料才會秀出相關資訊
    if(couponSN.html() !== ""){
        // 滑鼠經過優惠券欄位時會秀出相關資訊
        couponSN.on("mouseover", function(){
            // console.log($(this).children());
            $(this).next().fadeIn();
        })
    
        // 滑鼠離開優惠券欄位時會隱藏相關資訊
        couponSN.on("mouseleave", function(){
            $(this).next().fadeOut();
        })
    }

    // 對訂單進行編輯
    modify.on("click", function(e){
        // console.log($(this).closest("td").siblings(".deliveryStatus"));
        let input_productSpec = $(this).closest("td").siblings(".productSpec").find("input");
        let purchaseQuantity_update = $(this).closest("td").siblings(".purchaseQuantity").find("input");
        if($(this).closest("td").siblings(".deliveryStatus").html() === "未出貨"){
            // 如果狀態為未出貨，可以更改尺寸和數量，將input標籤中的d-none移除
            if(input_productSpec.hasClass("d-none")){
                input_productSpec.removeClass("d-none");
                purchaseQuantity_update.removeClass("d-none");
                input_productSpec.val($(this).closest("td").siblings(".productSpec").text());
                purchaseQuantity_update.val($(this).closest("td").siblings(".purchaseQuantity").text())
            } else {
            // 更改完畢後將input標籤中的d-none加回去，並更改欄位中的值
                if(input_productSpec.val().trim() !== "" && purchaseQuantity_update.val().trim() !== ""){
                    input_productSpec.addClass("d-none");
                    purchaseQuantity_update.addClass("d-none");
                    $(this).closest("td").siblings(".productSpec").html(`${input_productSpec.val().trim()}<input type="text" class="form-control d-none productSpec_update" size="5">`);
                    $(this).closest("td").siblings(".purchaseQuantity").html(`${purchaseQuantity_update.val().trim()}<input type="text" class="form-control d-none purchaseQuantity_update" size="5">`);
                } else {
                    alert("資料不為空白");
                }
            }

        } else {
            e.preventDefault();
            alert("此訂單已出貨無法更改");
        }
    })

    $("a.tracking").on("click", function(e){
        // console.log($(e.target).closest("tr"));
        $(e.target).closest("tr").remove();
        count_number();
    })

})






