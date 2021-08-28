package com.cartList.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.product.model.ProductVO;
import com.productspec.model.ProductSpecJDBCDAO;
import com.productspec.model.ProductSpecVO;

public class Test_CartList {

	public static void main(String[] args) {
		
		// addCartList(CartListVO cartList)
		CartListVO cartList = new CartListVO();
		cartList.setUserId(new Integer(1002));
		cartList.setProductSpecId(new Integer(12032));
		cartList.setItemQuantity(new Integer(1));
		CartListDAO dao = new CartListDAO();
		dao.updateCartList(cartList);
		
		CartListVO cartList2 = new CartListVO();
		cartList2.setUserId(new Integer(1002));
		cartList2.setProductSpecId(new Integer(12023));
		cartList2.setItemQuantity(new Integer(3));
		CartListDAO dao2 = new CartListDAO();
		dao2.updateCartList(cartList2);
		
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
//		CartListService service = new CartListService();
//		List<CartListVO> cartList =  service.getCartList(new Integer(1001));
//		for(CartListVO cart : cartList) {
//			System.out.println(cart);
//		}
		
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
		
		
		//Map<String, Integer> getTotalAmount(Integer userId)
//		CartListService service = new CartListService();
//		Map<String, Integer> map = service.getTotalAmount(new Integer(1003));
//		for(Integer i : map.values()) {
//			System.out.println(i);
//		}
		
		//Map<ProductSpecVO, ProductVO> getProductFromSpec(Integer userId)
//		CartListService service = new CartListService();
//		Map<CartListVO, ProductVO> map = service.getProductFromSpec(new Integer(1003));
//		for(CartListVO vo : map.keySet()) {
//			System.out.println(vo);
//		}
//		for(ProductVO vo : map.values()) {
//			System.out.println(vo.getProductName());
//		}

//		Integer price = dao.findByPrimaryKey(11003).getProductPrice();
//		System.out.println(price);

	}

}
