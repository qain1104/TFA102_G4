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
			throw new RuntimeException("JSON parse error : " + e.getMessage());
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
			throw new RuntimeException("JSON parse error : " + e.getMessage());
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
						CartListVO cartlistDelete = new CartListVO(cartList.getProductSpecId(), itemQuantity);
						String jsonCartlist = new JSONObject(cartlistDelete).toString();
						jedis.lset("CartList:" + userId + ":product", i, jsonCartlist);
						return;			
					} else {
						System.out.println("���I��R��");
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
	// �p�ⲣ�~�`�ƶq�M�`���B
	public Map<String, Integer> getTotalAmount(Integer userId) {
		Jedis jedis = null;
		ProductSpecService service = new ProductSpecService();
		Integer totalAmount = 0;
		Integer totalQuantity = 0;
		// ���Ӧs���ʪ������`�ƶq�M�`���B
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
				totalAmount += itemAmount; // �p���`���B
				totalQuantity += itemQuantity; // �p��ӫ~�`�ƶq
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
	// �N�ʪ��������ӥH�β��~�s���@��Map
	public Map<CartListVO, ProductVO> getProductFromSpec(Integer userId){

		CartListService service = new CartListService();
		List<CartListVO> cartList =  service.getCartList(userId); 
		Map<CartListVO, ProductVO> map = new LinkedHashMap<CartListVO, ProductVO>();
		//�n���service
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
