package com.productspec.model;

import java.sql.Timestamp;
import java.util.List;

public class ProductSpecService {

	private ProductSpecDAO_interface dao;

	public ProductSpecService() {
		dao = new ProductSpecDAO();
	}

	public ProductSpecVO addProduct(Integer productSN, Integer productStock, Integer productPrice, 
			String productSpec) {

		ProductSpecVO productSpecVO = new ProductSpecVO();

		productSpecVO.setProductSN(productSN);
		productSpecVO.setProductStock(productStock);
		productSpecVO.setProductPrice(productPrice);
		productSpecVO.setProductSpec(productSpec);
		dao.insert(productSpecVO);

		return productSpecVO;
	}

	public ProductSpecVO updateProduct(Integer productSpecId, Integer productSN, Integer productStock, 
			Integer productPrice, String productSpec) {

		ProductSpecVO productSpecVO = new ProductSpecVO();

		productSpecVO.setProductSpecId(productSpecId);
		productSpecVO.setProductSN(productSN);
		productSpecVO.setProductStock(productStock);
		productSpecVO.setProductPrice(productPrice);
		productSpecVO.setProductSpec(productSpec);
		dao.update(productSpecVO);

		return productSpecVO;
	}

	public void deleteProduct(Integer productSpecId) {
		dao.delete(productSpecId);
	}

	public ProductSpecVO getOneProduct(Integer productSpecId) {
		return dao.findByPrimaryKey(productSpecId);
	}

	public List<ProductSpecVO> getAll() {
		return dao.getAll();
	}
}
