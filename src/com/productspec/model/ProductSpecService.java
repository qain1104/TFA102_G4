package com.productspec.model;

import java.util.List;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.cartList.model.CartListVO;

public class ProductSpecService {

	private ProductSpecDAO_interface dao;

	public ProductSpecService() {
		dao = new ProductSpecJDBCDAO();
	}

	public ProductSpecVO addProductSpec(Integer productSN, Integer productStock, Integer productPrice, 
			String productSpec) {

		ProductSpecVO productSpecVO = new ProductSpecVO();

		productSpecVO.setProductSN(productSN);
		productSpecVO.setProductStock(productStock);
		productSpecVO.setProductPrice(productPrice);
		productSpecVO.setProductSpec(productSpec);
		dao.insert(productSpecVO);

		return productSpecVO;
	}

	public ProductSpecVO updateProductSpec(Integer productSpecId, Integer productSN, Integer productStock, 
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
	 
	public List<ProductSpecVO> getProductProduct(Integer productSN) {
         List<ProductSpecVO> list = dao.getAll().stream()
                 .filter(e ->e.getProductSN().equals(productSN))
                 .collect(Collectors.toList());
         return list;         
    }
	
	public Integer cartListGetPrice(CartListVO cartListVO){
		return dao.cartListGetPrice(cartListVO);
	}
	
	public void updateStock(Integer productSpecId, Integer productStock) {
		dao.updateStock(productSpecId, productStock);
	}
	
	public List<ProductSpecVO> getProductSpec(Integer productSN){
		return dao.getProductSpec(productSN);
	}
}
