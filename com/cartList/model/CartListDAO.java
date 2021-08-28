package com.cartList.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Vector;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.product.model.ProductService;
import com.product.model.ProductVO;
import com.productspec.model.ProductSpecService;
import com.productspec.model.ProductSpecVO;

import redis.clients.jedis.Jedis;

public class CartListDAO implements CartListDAO_interface{

	@Override
	// 若有下面的update是否不需要add?
	public void addCartList(CartListVO cartList) {
		Jedis jedis = null;
		CartListVO cartlistForJSON = null;
		
		try {
			jedis = new Jedis("localhost", 6379);
			String userId = String.valueOf(cartList.getUserId());
			cartlistForJSON = new CartListVO(cartList.getProductSpecId(), cartList.getItemQuantity());
			String jsonCartlist = new JSONObject(cartlistForJSON).toString();
			jedis.rpush("CartList:" + userId + ":product", jsonCartlist);

		} finally {
			if(jedis != null) {
				jedis.close();
			}
		}	
	}

	@Override
	// 將add和update寫在一起，先判斷是否為重複的商品
	public void updateCartList(CartListVO cartList) {
		Jedis jedis = null;
		Integer itemQuantity = null;
		CartListVO cartlistAdd = null;
		String jsonCartlist = null;
		
		try {
			jedis = new Jedis("localhost", 6379);
			String userId = String.valueOf(cartList.getUserId());
			List<String> cartListString = (ArrayList<String>)jedis.lrange("CartList:" + userId + ":product", 0, -1);
			
			for(int i = 0; i < cartListString.size(); i++) {
				JSONTokener tokener = new JSONTokener(cartListString.get(i));
				JSONObject cartListJson = new JSONObject(tokener);
				
				// 判斷是否為同一樣商品，若為同樣商品則進行數量加總，若不同商品則新增
				if(cartList.getProductSpecId().intValue() == cartListJson.getInt("productSpecId")) {
					itemQuantity = new Integer(cartListJson.getInt("itemQuantity")) + cartList.getItemQuantity();
					cartlistAdd = new CartListVO(cartList.getProductSpecId(), itemQuantity);
					jsonCartlist = new JSONObject(cartlistAdd).toString();
					jedis.lset("CartList:" + userId + ":product", i, jsonCartlist);
					return;
				}
			}	
			itemQuantity = cartList.getItemQuantity();
			cartlistAdd = new CartListVO(cartList.getProductSpecId(), itemQuantity);
			jsonCartlist = new JSONObject(cartlistAdd).toString();
			jedis.rpush("CartList:" + userId + ":product", jsonCartlist);
		} catch (JSONException e) {
			throw new RuntimeException("JSON parse error : " + e.getMessage());
		} 
		finally {
			if(jedis != null) {
				jedis.close();
			}
		}	
	}

	@Override
	// 清空購物車
	public void deleteCartList(Integer userId) {
		Jedis jedis = null;
		String id = String.valueOf(userId);
		try {
			jedis = new Jedis("localhost", 6379);
			jedis.del("CartList:" + id + ":product");
		} finally {
			if(jedis != null) {
				jedis.close();
			}
		}	
	}

	@Override
	// 取得購物車所有內容
	public List<CartListVO> getCartList(Integer userId){
		Jedis jedis = null;
		List<CartListVO> userCartList = new Vector<CartListVO>(); 
		
		try {
			jedis = new Jedis("localhost", 6379);
			String id = String.valueOf(userId);
			List<String> cartListString = jedis.lrange("CartList:" + id + ":product", 0, -1);
			for(String jsonString : cartListString) {
				JSONTokener tokener = new JSONTokener(jsonString);
				JSONObject cartListJson = new JSONObject(tokener);
				Integer productSpecId = new Integer(cartListJson.getInt("productSpecId"));
				Integer itemQuantity = new Integer(cartListJson.getInt("itemQuantity"));
				CartListVO cartListVO = new CartListVO(userId, productSpecId, itemQuantity);
				userCartList.add(cartListVO);
			}
			
		} catch(NumberFormatException e) {
			throw new RuntimeException("Number parse error : " + e.getMessage());
		} catch (JSONException e) {
			throw new RuntimeException("JSON parse error : " + e.getMessage());
		}
		finally {
			if(jedis != null) {
				jedis.close();
			}
		}
		return userCartList;			
	}

	@Override
	//刪除單一物件
	public void deleteCartListSingle(CartListVO cartList) {
		Jedis jedis = null;
		CartListVO cartlistDelete = null;
		String jsonCartlist = null;
		
		try {
			jedis = new Jedis("localhost", 6379);
			String userId = String.valueOf(cartList.getUserId());
			cartlistDelete = new CartListVO(cartList.getProductSpecId(), cartList.getItemQuantity());
			jsonCartlist = new JSONObject(cartlistDelete).toString();
			jedis.lrem("CartList:" + userId + ":product", 0, jsonCartlist);
		} catch(Exception e){
			e.printStackTrace();
		} 
		finally {
			if(jedis != null) {
				jedis.close();
			}
		}	
	}

	@Override
	// 點選+按鈕會新增一件
	public void addOnePiece(CartListVO cartList) {
		Jedis jedis = null;
		try {
			jedis = new Jedis("localhost", 6379);
			String userId = String.valueOf(cartList.getUserId());
			List<String> cartListString = (ArrayList<String>)jedis.lrange("CartList:" + userId + ":product", 0, -1);
			
			for(int i = 0; i < cartListString.size(); i++) {
				JSONTokener tokener = new JSONTokener(cartListString.get(i));
				JSONObject cartListJson = new JSONObject(tokener);
				if(cartList.getProductSpecId().intValue() == cartListJson.getInt("productSpecId")) {
					Integer itemQuantity = new Integer(cartListJson.getInt("itemQuantity") + 1);
					CartListVO cartlistAdd = new CartListVO(cartList.getProductSpecId(), itemQuantity);
					String jsonCartlist = new JSONObject(cartlistAdd).toString();
					jedis.lset("CartList:" + userId + ":product", i, jsonCartlist);
					return;
				}
			}
		} catch (JSONException e) {
			throw new RuntimeException("JSON parse error : " + e.getMessage());
		} 
		finally {
			if(jedis != null) {
				jedis.close();
			}
		}	
	}

	@Override
	// 點選-按鈕會減少一件(條件為數量大於一件)
	public void deleteOnePiece(CartListVO cartList) {
		Jedis jedis = null;
		
		try {
			jedis = new Jedis("localhost", 6379);
			String userId = String.valueOf(cartList.getUserId());
			List<String> cartListString = (ArrayList<String>)jedis.lrange("CartList:" + userId + ":product", 0, -1);
			
			for(int i = 0; i < cartListString.size(); i++) {
				JSONTokener tokener = new JSONTokener(cartListString.get(i));
				JSONObject cartListJson = new JSONObject(tokener);
				
				if(cartList.getProductSpecId().intValue() == cartListJson.getInt("productSpecId")) {
					
					if(cartListJson.getInt("itemQuantity") >= 1) {
						Integer itemQuantity = new Integer(cartListJson.getInt("itemQuantity") - 1);
						CartListVO cartlistDelete = new CartListVO(cartList.getProductSpecId(), itemQuantity);
						String jsonCartlist = new JSONObject(cartlistDelete).toString();
						jedis.lset("CartList:" + userId + ":product", i, jsonCartlist);
						return;			
					} else {
						System.out.println("請點選刪除");
					}
				}	
			}
		} catch (JSONException e) {
			throw new RuntimeException("JSON parse error : " + e.getMessage());
		} 
		finally {
			if(jedis != null) {
				jedis.close();
			}
		}		
	}

	@Override
	// 計算產品總數量和總金額
	public Map<String, Integer> getTotalAmount(Integer userId) {
		Jedis jedis = null;
		ProductSpecService service = new ProductSpecService();
		Integer totalAmount = 0;
		Integer totalQuantity = 0;
		// 拿來存放購物車的總數量和總金額
		Map<String, Integer> totalCartList = new HashMap<String, Integer>();

		try {
			jedis = new Jedis("localhost", 6379);
			String id = String.valueOf(userId);
			List<String> cartListString = jedis.lrange("CartList:" + id + ":product", 0, -1);
			for(String jsonString : cartListString) {
				JSONTokener tokener = new JSONTokener(jsonString);
				JSONObject cartListJson = new JSONObject(tokener);
				Integer productSpecId = new Integer(cartListJson.getInt("productSpecId"));
				Integer itemQuantity = new Integer(cartListJson.getInt("itemQuantity"));
				Integer price = service.getOneProduct(productSpecId).getProductPrice();
				Integer itemAmount = price * itemQuantity;
				totalAmount += itemAmount; // 計算總金額
				totalQuantity += itemQuantity; // 計算商品總數量
			}
			
			totalCartList.put("totalAmount", totalAmount);
			totalCartList.put("totalQuantity", totalQuantity);
			
		} catch(NumberFormatException e) {
			throw new RuntimeException("Number parse error : " + e.getMessage());
		} catch (JSONException e) {
			throw new RuntimeException("JSON parse error : " + e.getMessage());
		}
		finally {
			if(jedis != null) {
				jedis.close();
			}
		}
		
		return totalCartList;
	}
	
	@Override
	// 將購物車的明細以及產品存成一個Map
	public Map<CartListVO, ProductVO> getProductFromSpec(Integer userId){

		CartListService service = new CartListService();
		List<CartListVO> cartList =  service.getCartList(userId); 
		Map<CartListVO, ProductVO> map = new LinkedHashMap<CartListVO, ProductVO>();
		//要改用service
		ProductSpecService productSpecService = new ProductSpecService();
		ProductService productService = new ProductService();
		
		for(CartListVO vo : cartList) {
			Integer productSpecId = vo.getProductSpecId();
			ProductSpecVO productSpec = productSpecService.getOneProduct(productSpecId);
			ProductVO product = productService.getOneProduct(productSpec.getProductSN());
			map.put(vo, product);
		}
		
		
		return map;
	}
}
