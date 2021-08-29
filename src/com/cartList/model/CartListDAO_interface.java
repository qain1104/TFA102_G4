package com.cartList.model;

import java.util.List;
import java.util.Map;

import com.product.model.ProductVO;
import com.productspec.model.ProductSpecVO;;

public interface CartListDAO_interface {
	
	void addCartList(CartListVO cartList);
	void updateCartList(CartListVO cartList);
	void deleteCartList(Integer cartListId);
	void deleteCartListSingle(CartListVO cartList);
	List<CartListVO> getCartList(Integer userId);
	void addOnePiece(CartListVO cartList);
	void deleteOnePiece(CartListVO cartList);
	Map<String, Integer> getTotalAmount(Integer userId);
	Map<CartListVO, ProductVO> getProductFromSpec(Integer userId);
}
