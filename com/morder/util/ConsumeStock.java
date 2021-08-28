package com.morder.util;

import com.order_list.model.Order_listVO;
import com.productspec.model.ProductSpecService;
import com.productspec.model.ProductSpecVO;

public class ConsumeStock {
	
	public Integer consumeStock(Order_listVO orderList) {
		
		ProductSpecService productSpecService  = new ProductSpecService();
		Integer productSpecId = orderList.getProductSpecId();
		ProductSpecVO productSpec =  productSpecService.getOneProduct(productSpecId);
		Integer stockAfterConsume = productSpec.getProductStock() - orderList.getPurchaseQuantity();			
		
		return stockAfterConsume;
		
	}
}
