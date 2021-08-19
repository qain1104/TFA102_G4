
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
$(function(){
    cal_quantity();
    cal_single_amount();
    cal_total_amount();

    $(".table").on("click", ".btn-delete", function(){
        $(this).closest("tr").remove();
        cal_quantity();
        cal_total_amount();
    })

    $(".table").on("click", "span.btn-minu", function(){
        let quantity_val_min = parseInt($(this).closest("ul").find("input").val());
        if (quantity_val_min > 1){
            $(this).closest("ul").find("input").val(quantity_val_min - 1);
            $(this).closest("ul").find("span.var-value").html(quantity_val_min - 1); 
            cal_quantity();
            cal_single_amount();
            cal_total_amount();
        }
    })

    $(".table").on("click", "span.btn-plu", function(){
        let quantity_val_plu = parseInt($(this).closest("ul").find("input").val());   
        $(this).closest("ul").find("input").val(quantity_val_plu + 1);
        $(this).closest("ul").find("span.var-value").html(quantity_val_plu + 1);
        cal_quantity();
        cal_single_amount();
        cal_total_amount();
    })
})







