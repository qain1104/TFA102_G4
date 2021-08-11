package com.product_watch_list.model;

import java.util.List;

public class Product_watch_listService {
	private Product_watch_listDAO_interface dao;
	
	public Product_watch_listService(){
		dao = new Product_watch_listDAO();
	}
	
	public Product_watch_listVO addWatchList(Integer productSN, Integer userId) {
		
		Product_watch_listVO watchList = new Product_watch_listVO();
		watchList.setProductSN(productSN);
		watchList.setUserId(userId);
		dao.addWatchList(watchList);
		return watchList;
	}
	
	public Product_watch_listVO updateWatchList(Integer pwlSN, Integer productSN, Integer userId) {
		
		Product_watch_listVO watchList = new Product_watch_listVO();
		watchList.setPwlSN(pwlSN);
		watchList.setProductSN(productSN);
		watchList.setUserId(userId);
		dao.addWatchList(watchList);
		return watchList;
	}
	
	public void deleteWatchList(Integer pwlSN) {
		
		dao.deleteWatchList(pwlSN);
	}
	
	public Product_watch_listVO getWatchList(Integer pwlSN) {
		
		return dao.getWatchList(pwlSN);
	}
	
	public Product_watch_listVO getWatchListByUser(Integer userId) {
		
		return dao.getWatchListByUser(userId);
	}
	
	public List<Product_watch_listVO> getAll(){
		
		return dao.getAll();
	}

}
