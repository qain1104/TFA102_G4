// 秀出有買過的商品數量
function count_number(){
    $("td.list_number").each(function(index, element){
        $(element).html(index + 1);
    })
}


    count_number();
    
    $("span.couponSN").each(function(index, element){
        // 滑鼠經過優惠券欄位時會秀出相關資訊
        $(element).on("mouseover", function(){
            $(this).next().fadeIn();
        })
        
        // 滑鼠離開優惠券欄位時會隱藏相關資訊
        $(element).on("mouseleave", function(){
            $(this).next().fadeOut();
        })
    	
    })
    
    // 對訂單進行編輯
    $("a.modify").on("click", function(e){
    	
    	if($(this).attr("data-edit") == true){
    		alert("資料更新中");
    		return;
    	}
    	
        let input_productSpec = $(this).closest("td").siblings(".productSpec").find("input");
        let purchaseQuantity_update = $(this).closest("td").siblings(".purchaseQuantity").find("input");
        if($(this).parents("td").siblings(".deliveryStatus").html().trim() === "未出貨"){
        	
            // 如果狀態為未出貨，可以更改尺寸和數量，將input標籤中的d-none移除
            if(input_productSpec.hasClass("d-none")){
            	$(this).attr("data-edit", true);
                input_productSpec.removeClass("d-none");
                purchaseQuantity_update.removeClass("d-none");
//                input_productSpec.val($(this).closest("td").siblings(".productSpec").text());
//                purchaseQuantity_update.val($(this).closest("td").siblings(".purchaseQuantity").text());
                
            } else {
                // 更改完畢後將input標籤中的d-none加回去，並更改欄位中的值
                if(input_productSpec.val().trim() !== "" && purchaseQuantity_update.val().trim() !== ""){
            	
		       	    $.ajax({
		        	    	
		        	        url: "http://localhost:8081/Sportify/order/MorderManagement.do",           // 資料請求的網址
		        	        type: "POST",                  // GET | POST | PUT | DELETE | PATCH
		        	        data: {"update": "update",
		        	                "orderListSN": $(this).closest("td").siblings(".productName").attr("data-sn"),
		        	                "morderSN": $("div.order_number").html(),
		        	                "updateSpec": input_productSpec.val().trim(),
		        	                "updateQuantity": purchaseQuantity_update.val().trim()
		        	                },                  // 傳送資料到指定的 url
		        	        //processData: false,
		        	        // dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
		        	        contentType:"application/x-www-form-urlencoded",   //"application/x-www-form-urlencoded""application/json"
		        	        beforeSend: function(){       // 在 request 發送之前執行
		        	            
		        	        },
		        	        success: function(data){      // request 成功取得回應後執行
		//        	                 console.log($(e.target).closest("tr"));
		        	        		 console.log(data);
		        		        		if(data == "update complete"){
		        		        			
		        		                        input_productSpec.addClass("d-none");
		        		                        purchaseQuantity_update.addClass("d-none");
		        		                        $(this).closest("td").siblings(".productSpec").html(`${input_productSpec.val().trim()}<input type="text" class="form-control d-none productSpec_update" size="5">`);
		        		                        $(this).closest("td").siblings(".purchaseQuantity").html(`${purchaseQuantity_update.val().trim()}<input type="text" class="form-control d-none purchaseQuantity_update" size="5">`);
		        		                        $(this).closest("td").siblings("item_amount").html(parseInt(purchaseQuantity_update.val().trim()) * parseInt($(this).closest("td").siblings("item_amount").html()));
		        		        		}
		        	                
		        	  
		        	        },
		        	        error: function(xhr){         // request 發生錯誤的話執行
		        	          console.log(xhr);
		        	          alert("更改失敗，請聯絡店家");
		        	          input_productSpec.addClass("d-none");
		                      purchaseQuantity_update.addClass("d-none");
		        	        }
		        		    })
                } else {
                    alert("資料不為空白");
                    input_productSpec.addClass("d-none");
                    purchaseQuantity_update.addClass("d-none");
                }
            }

        } else {
            e.preventDefault();
            alert("此訂單已出貨無法更改");
        }
    })
    
    
    $("td.productFeedback").each(function(index, element){
    	var star_count;
    	// 如果評價不為空字串，則不能編輯
        if($(element).find("span").html().trim() !== ""){
            // console.log($(element).next().find("i.fa-edit"));
            $(element).next().find("i").addClass("-none");
        } else {
            // 如果沒有評價，可以點選編輯按鈕進行編輯
            $(element).next().find("button.btn-edit").on("click", function(){
                $(element).find("span").addClass("-none");
                $(element).find("textarea").removeClass("-none");
                $(element).next().find("button.btn-edit").addClass("-none"); // 編輯按鈕隱藏
                $(element).next().find("button.btn-save").removeClass("-none"); // 秀儲存按鈕
                
                // star
        	    $("span.star").on("click", function(e){
        	        // 若有儲存按鈕才可以點選star
        	        if(!$(this).closest("td").siblings("td.operation").find("button.btn-save").hasClass("-none")){
        	            star_count = $(this).attr("data-star");
        	            $(this).closest("td").attr("data-rate", star_count);
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
            })
        }

        // 儲存按鈕功能
        $(element).next().find("button.btn-save").on("click", function(e){
        	// 如果textarea為空字串在點擊儲存按鈕時，編輯按鈕會再秀出來，儲存按鈕則會隱藏
        	if($(element).find("textarea").val().trim() === ""){
        		$(element).find("span").html($(element).find("textarea").val().trim());
        		$(element).find("textarea").addClass("-none");
        		$(element).find("span").removeClass("-none");
        		$(element).next().find("button.btn-edit").removeClass("-none");
        		$(element).next().find("button.btn-save").addClass("-none");
        		
        	    $.ajax({
        	    	
        	        url: "http://localhost:8081/Sportify/order/MorderManagement.do",           // 資料請求的網址
        	        type: "POST",                  // GET | POST | PUT | DELETE | PATCH
        	        data: {"save": "save",
        	        		"productFeedback": $(element).find("textarea").val().trim(),
        	                "productRate": $(element).prev().attr("data-rate"),
        	                "listSN": $(element).parent("tr").attr("data-listsn")
        	                },                  // 傳送資料到指定的 url
        	        //processData: false,
        	        // dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
        	        contentType:"application/x-www-form-urlencoded",   //"application/x-www-form-urlencoded""application/json"
        	        beforeSend: function(){       // 在 request 發送之前執行
        	            
        	        },
        	        success: function(data){      // request 成功取得回應後執行
//        	                 console.log($(e.target).closest("tr"));
        	        		 console.log(data);
        		        		if(data == "review complete"){
        		        			$(element).find("span").html($(element).find("textarea").val().trim());
        		                	$(element).find("textarea").addClass("-none");
        		                	$(element).find("span").removeClass("-none");
        		                	$(element).next().find("button.btn-save").addClass("-none");
        		        		}
        	                
        	  
        	        },
        	        error: function(xhr){         // request 發生錯誤的話執行
        	          console.log(xhr);
        	          alert("評論更新失敗，請稍後再試");
        	        }
        	    	
        		    })
        		
        	} else {
            // textarea有內容時則會將內容帶到span標籤並傳送內容至後端，將儲存按鈕隱藏，不再秀出編輯按鈕       		
        	    $.ajax({
        	    	
        	        url: "http://localhost:8081/Sportify/order/MorderManagement.do",           // 資料請求的網址
        	        type: "POST",                  // GET | POST | PUT | DELETE | PATCH
        	        data: {"save": "save",
        	        		"productFeedback": $(element).find("textarea").val().trim(),
        	                "productRate": $(element).prev().attr("data-rate"),
        	                "listSN": $(element).parent("tr").attr("data-listsn")
        	                },                  // 傳送資料到指定的 url
        	        //processData: false,
        	        // dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
        	        contentType:"application/x-www-form-urlencoded",   //"application/x-www-form-urlencoded""application/json"
        	        beforeSend: function(){       // 在 request 發送之前執行
        	            
        	        },
        	        success: function(data){      // request 成功取得回應後執行
//        	                 console.log($(e.target).closest("tr"));
        	        		 console.log(data);
        		        		if(data == "review complete"){
        		        			$(element).find("span").html($(element).find("textarea").val().trim());
        		                	$(element).find("textarea").addClass("-none");
        		                	$(element).find("span").removeClass("-none");
        		                	$(element).next().find("button.btn-save").addClass("-none");
        		        		}
        	                
        	  
        	        },
        	        error: function(xhr){         // request 發生錯誤的話執行
        	          console.log(xhr);
        	          alert("評論更新失敗，請稍後再試");
        	        }
        	    	
        		    })
        	}
        }) 
    })
    
    
    $("a.tracking").on("click", function(e){
//    	console.log($(this).closest("tr").attr("data-sn"));
	    $.ajax({
	    	
	        url: "http://localhost:8081/Sportify/tracking/ProductTracking.do",           // 資料請求的網址
	        type: "POST",                  // GET | POST | PUT | DELETE | PATCH
	        data: {"tracking": "cancle",
	                "pwlSN": $(this).closest("tr").attr("data-sn")
	                },                  // 傳送資料到指定的 url
	        //processData: false,
	        // dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
	        contentType:"application/x-www-form-urlencoded",   //"application/x-www-form-urlencoded""application/json"
	        beforeSend: function(){       // 在 request 發送之前執行
	            
	        },
	        success: function(data){      // request 成功取得回應後執行
//	                 console.log($(e.target).closest("tr"));
	        		 console.log(data);
		        		if(data == "cancle complete"){
		        			$(e.target).closest("tr").remove();
			                count_number();
		        		}
	                
	  
	        },
	        error: function(xhr){         // request 發生錯誤的話執行
	          console.log(xhr);
	          alert("刪除失敗，請稍後再試");
	        }
	    	
		    })
	    })


//	    $("span.star").on("click", function(e){
//	        // 若有儲存按鈕才可以點選star
//	        if(!$(this).closest("td").siblings("td.operation").find("button.btn-save").hasClass("-none")){
//	            let star_count = $(this).attr("data-star");
//	            // $(this).addClass("-on");
//	            $(this).parent("td").find("span").each(function(index, element){
//	                // console.log(element);
//	                if($(element).attr("data-star") <= star_count){
//	                    if(!$(element).hasClass("-on")){
//	                        $(element).addClass("-on");
//	                    }
//	                } else {
//	                    $(element).removeClass("-on");
//	                }
//	            })
//	        }
//	    })


