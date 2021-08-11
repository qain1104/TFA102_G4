package com.cartList.model;

import java.util.List;

public class CartListService {
	private CartListDAO_interface dao;
	
	public CartListService(){
		dao = new CartListDAO();
	}
	
	public CartListVO addCartList(Integer userId, Integer productSpecId, Integer itemQuantity) {
		
		CartListVO cartList = new CartListVO();
		cartList.setUserId(userId);
		cartList.setProductSpecId(productSpecId);
		cartList.setItemQuantity(itemQuantity);
		dao.addCartList(cartList);
		
		return cartList;
	}
	
	public CartListVO updateCartList(Integer userId, Integer productSpecId, Integer itemQuantity) {
		
		CartListVO cartList = new CartListVO();
		cartList.setUserId(userId);
		cartList.setProductSpecId(productSpecId);
		cartList.setItemQuantity(itemQuantity);
		dao.updateCartList(cartList);
		
		return cartList;
	}
	
	public void deleteCartList(Integer userId) {
		dao.deleteCartList(userId);
	}
	
	public List<CartListVO> getCartList(Integer userId) {
		return dao.getCartList(userId);
	}
	
	public void deleteCartListSingle(Integer userId, Integer productSpecId, Integer itemQuantity) {
		
		CartListVO cartList = new CartListVO();
		cartList.setUserId(userId);
		cartList.setProductSpecId(productSpecId);
		cartList.setItemQuantity(itemQuantity);
		dao.deleteCartListSingle(cartList);
	}
	
	public void addOnePiece(Integer userId, Integer productSpecId, Integer itemQuantity) {
		
		CartListVO cartList = new CartListVO();
		cartList.setUserId(userId);
		cartList.setProductSpecId(productSpecId);
		cartList.setItemQuantity(itemQuantity);
		dao.addOnePiece(cartList);

	}
	
	public void deleteOnePiece(Integer userId, Integer productSpecId, Integer itemQuantity) {
		
		CartListVO cartList = new CartListVO();
		cartList.setUserId(userId);
		cartList.setProductSpecId(productSpecId);
		cartList.setItemQuantity(itemQuantity);
		dao.deleteOnePiece(cartList);
	}

}
