package com.cartList.model;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import redis.clients.jedis.Jedis;

public class Try {

	public static void main(String[] args) {
		Integer userId = new Integer(1001); 
		Jedis jedis = null;
		List<CartListVO> userCartList = new ArrayList<CartListVO>();
		try {
			jedis = new Jedis("localhost", 6379);
			String id = String.valueOf(userId);
			System.out.println(jedis.llen("CartList:" + id + ":product"));
			
			while(jedis.llen("CartList:" + id + ":product") != 0) {
				String cartListString = jedis.lpop("CartList:" + id + ":product");
				JSONTokener tokener = new JSONTokener(cartListString);
				JSONObject cartListJson = new JSONObject(tokener);
				userCartList.add(new CartListVO(userId, cartListJson.getInt("productSpecId"), cartListJson.getInt("itemQuantity")));
			}
			

			
//			for(String jsonString : cartListString) {
//				System.out.println(jsonString);
//				JSONTokener tokener = new JSONTokener(jsonString);
//				JSONObject cartListJson = new JSONObject(tokener);
//				System.out.println(cartListJson);
//				Integer productSpecId = Integer.valueOf(cartListJson.getString("productSpecId"));
//				Integer itemQuantity = new Integer(cartListJson.getInt("itemQuantity"));
//				CartListVO cartListVO = new CartListVO(userId, productSpecId, itemQuantity);
//				userCartList.add(cartListVO);
//			}
			
			for(int i = 0 ; i < userCartList.size() ; i++) {
//				System.out.println(userCartList.get(i).getProductSpecId());
//				System.out.println(userCartList.get(i).getProductSpecId().getClass());
				if(new Integer(11006) == userCartList.get(i).getProductSpecId()) {
					userCartList.remove(i);
				}
			}
//			for(CartListVO vo : userCartList) {
//				if(new Integer(11006) == vo.getProductSpecId()) {
//					userCartList.remove(vo);
//				}
//			}
			System.out.println(userCartList.size());
			
		} catch(NumberFormatException e) {
			e.printStackTrace();
		} 
		catch(JSONException e) {
			e.printStackTrace();
		}
		finally {
			if(jedis != null) {
				jedis.close();
			}
		}

	}

}
