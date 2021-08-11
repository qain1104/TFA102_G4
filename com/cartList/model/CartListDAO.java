package com.cartList.model;

import java.util.ArrayList;
import java.util.Vector;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import redis.clients.jedis.Jedis;

public class CartListDAO implements CartListDAO_interface{

	@Override
	// �Y���U����update�O�_���ݭnadd?
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
	// �Nadd�Mupdate�g�b�@�_�A���P�_�O�_�����ƪ��ӫ~
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
				
				// �P�_�O�_���P�@�˰ӫ~�A�Y���P�˰ӫ~�h�i��ƶq�[�`�A�Y���P�ӫ~�h�s�W
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
			e.printStackTrace();
		} 
		finally {
			if(jedis != null) {
				jedis.close();
			}
		}	
	}

	@Override
	// �M���ʪ���
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
	// ���o�ʪ����Ҧ����e
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
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		finally {
			if(jedis != null) {
				jedis.close();
			}
		}
		return userCartList;			
	}

	@Override
	//�R����@����
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
		} finally {
			if(jedis != null) {
				jedis.close();
			}
		}	
	}

	@Override
	// �I��+���s�|�s�W�@��
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
			e.printStackTrace();
		} 
		finally {
			if(jedis != null) {
				jedis.close();
			}
		}	
	}

	@Override
	// �I��-���s�|��֤@��(���󬰼ƶq�j��@��)
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
						CartListVO newCartlist = new CartListVO(cartList.getProductSpecId(), itemQuantity);
						String jsonCartlist = new JSONObject(newCartlist).toString();
						jedis.lset("CartList:" + userId + ":product", i, jsonCartlist);
						return;			
					} else {
						System.out.println("���I��R��");
					}
				}	
			}
		} catch (JSONException e) {
			e.printStackTrace();
		} 
		finally {
			if(jedis != null) {
				jedis.close();
			}
		}		
	}
}
