    $("a.tracking").on("click", function(e){
//    	console.log($(this).closest("tr").attr("data-sn"));
	    $.ajax({
	    	
	        url: "http://localhost:8081/Sportify/shopping/SportifyShop.do",           // 資料請求的網址
	        type: "POST",                  // GET | POST | PUT | DELETE | PATCH
	        data: {
	        		"action": "tracking",
	        		"productSN": $(this).parents("div.product").attr("data-product")
	                },                  // 傳送資料到指定的 url
	        //processData: false,
	        // dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
	        contentType:"application/x-www-form-urlencoded",   //"application/x-www-form-urlencoded""application/json"
	        beforeSend: function(){       // 在 request 發送之前執行
	            
	        },
	        success: function(data){      // request 成功取得回應後執行
//	                 console.log($(e.target).closest("tr"));
		        		if(data == "Item exists."){
		        			alert("Item exists.");
		        		} else if (data == "Item added successfully."){
		        			alert("Item added successfully.");
		        		} else if(data == "Please sign in first."){
		        			alert("Please sign in first.");
		        		}
	        },
	        error: function(xhr){         // request 發生錯誤的話執行
	          console.log(xhr);
	          alert("新增失敗，請稍後再試");
	        }
	    	
		    })
	    })
	    
