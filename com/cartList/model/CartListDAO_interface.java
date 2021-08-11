package com.cartList.model;

import java.util.List;;

public interface CartListDAO_interface {
	
	void addCartList(CartListVO cartList);
	void updateCartList(CartListVO cartList);
	void deleteCartList(Integer cartListId);
	void deleteCartListSingle(CartListVO cartList);
	List<CartListVO> getCartList(Integer userId);
	void addOnePiece(CartListVO cartList);
	void deleteOnePiece(CartListVO cartList);
}
