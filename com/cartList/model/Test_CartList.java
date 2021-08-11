package com.cartList.model;

import java.util.List;

public class Test_CartList {

	public static void main(String[] args) {
		
		// addCartList(CartListVO cartList)
//		CartListVO cartList = new CartListVO();
//		cartList.setUserId(new Integer(1002));
//		cartList.setProductSpecId(new Integer(11005));
//		cartList.setItemQuantity(new Integer(3));
//		CartListDAO dao = new CartListDAO();
//		dao.addCartList(cartList);
//		
//		CartListVO cartList2 = new CartListVO();
//		cartList2.setUserId(new Integer(1001));
//		cartList2.setProductSpecId(new Integer(11001));
//		cartList2.setItemQuantity(new Integer(1));
//		CartListDAO dao2 = new CartListDAO();
//		dao2.addCartList(cartList2);
		
		//updateCartList(CartListVO cartList)
//		CartListVO cartList = new CartListVO();
//		cartList.setUserId(new Integer(1001));
//		cartList.setProductSpecId(new Integer(11002));
//		cartList.setItemQuantity(new Integer(1));
//		CartListDAO dao = new CartListDAO();
//		dao.updateCartList(cartList);
		
		//deleteCartList(Integer userId)
//		CartListDAO dao = new CartListDAO();
//		dao.deleteCartList(new Integer(1001));
		
		//getCartList(Integer userId)
		CartListDAO dao = new CartListDAO();
		List<CartListVO> cartList =  dao.getCartList(new Integer(1001));
		for(CartListVO cart : cartList) {
			System.out.println(cart);
		}
		
		//deleteCartListSingle(CartListVO cartList)
//		CartListDAO dao = new CartListDAO();
//		CartListVO cartList = new CartListVO(1002, 11003, 2);
//		dao.deleteCartListSingle(cartList);
		
		//addOnePiece(CartListVO cartList)
//		CartListDAO dao = new CartListDAO();
//		CartListVO cartList = new CartListVO(1002, 11005, 3);
//		dao.addOnePiece(cartList);
		
		//deleteOnePiece(CartListVO cartList)
//		CartListDAO dao = new CartListDAO();
//		CartListVO cartList = new CartListVO(1002, 11005, 4);
//		dao.deleteOnePiece(cartList);
	}

}
