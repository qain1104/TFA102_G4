package com.productimage.model;

import java.util.List;
import java.util.stream.Collectors;

public class ProductImageService {

	private ProductImageDAO_interface dao;

	public ProductImageService() {
		dao = new ProductImageJDBCDAO();
	}

	public ProductImageVO addProductImage(Integer productSN, byte[] productImage) {

		ProductImageVO productImageVO = new ProductImageVO();

		productImageVO.setProductSN(productSN);
		productImageVO.setProductImage(productImage);
		
		dao.insert(productImageVO);

		return productImageVO;
	}

	public ProductImageVO updateProductImage(Integer productImageSN, Integer productSN, byte[] productImage) {

		ProductImageVO productImageVO = new ProductImageVO();

		productImageVO.setProductImageSN(productImageSN);
		productImageVO.setProductSN(productSN);
		productImageVO.setProductImage(productImage);
		dao.update(productImageVO);

		return productImageVO;
	}

	public void deleteProduct(Integer productImageSN) {
		dao.delete(productImageSN);
	}

	public ProductImageVO getOneProduct(Integer productImageSN) {
		return dao.findByPrimaryKey(productImageSN);
	}

	public List<ProductImageVO> getAll() {
		return dao.getAll();
	}
	
	public List<ProductImageVO> findByProduct(Integer productSN){
		return dao.findByProduct(productSN);
	}
	
	 public List<ProductImageVO> getProductImageVO(Integer productSN) {
		 List<ProductImageVO> list = dao.getAll().stream()
                 .filter(e ->e.getProductSN().equals(productSN))
                 .collect(Collectors.toList());
         return list;         
    	}

}
