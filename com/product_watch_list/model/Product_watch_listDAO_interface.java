package com.product_watch_list.model;

import java.util.List;

public interface Product_watch_listDAO_interface {
	void addWatchList(Product_watch_listVO watchList);
	void deleteWatchList(Integer pwlSN);
	void updateWatchList(Product_watch_listVO watchList);
	Product_watch_listVO getWatchList(Integer pwlSN);
	Product_watch_listVO getWatchListByUser(Integer userId);
	List<Product_watch_listVO> getAll();
}
