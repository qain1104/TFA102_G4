package com.product.model;

import java.sql.Timestamp;
import java.util.List;

public class ProductService {

	private ProductDAO_interface dao;

	public ProductService() {
		dao = new ProductDAO();
	}

	public ProductVO addProduct(Integer corpUserId, String productName, Integer productClass, 
			String productDetail, String productBrand, Timestamp productOnsale, Integer productStatus) {

		ProductVO productVO = new ProductVO();

		productVO.setCorpUserId(corpUserId);
		productVO.setProductName(productName);
		productVO.setProductClass(productClass);
		productVO.setProductDetail(productDetail);
		productVO.setProductBrand(productBrand);
		productVO.setProductOnsale(productOnsale);
		productVO.setProductStatus(productStatus);
		
		dao.insert(productVO);

		return productVO;
	}

	public ProductVO updateProduct(Integer productSN, Integer corpUserId, String productName, Integer productClass, 
			String productDetail, String productBrand, Timestamp productOnsale, Integer productStatus) {

		ProductVO productVO = new ProductVO();

		productVO.setProductSN(productSN);
		productVO.setCorpUserId(corpUserId);
		productVO.setProductName(productName);
		productVO.setProductClass(productClass);
		productVO.setProductDetail(productDetail);
		productVO.setProductBrand(productBrand);
		productVO.setProductOnsale(productOnsale);
		productVO.setProductStatus(productStatus);
		dao.update(productVO);

		return productVO;
	}

	public void deleteProduct(Integer productSN) {
		dao.delete(productSN);
	}

	public ProductVO getOneProduct(Integer productSN) {
		return dao.findByPrimaryKey(productSN);
	}

	public List<ProductVO> getAll() {
		return dao.getAll();
	}
}
