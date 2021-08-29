package com.product.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

public class ProductService {

	private ProductDAO_interface dao;
	private ProductJDBCDAO jdbcdao;
	public ProductService() {
		dao = new ProductJDBCDAO();
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
		
		jdbcdao.insert(productVO);
		productVO.setProductSN(jdbcdao.key);
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
	
	//�Q�Ϊ��A�j�Mproduct�C��
    public List<ProductVO> getProductStatus(Integer productStatus) {
         List<ProductVO> list=dao.getAll().stream()
                 .filter(e ->e.getProductStatus().equals(productStatus))
                 .collect(Collectors.toList());
         return list;         
    }
    
	//�ק�product�f�֥��q�L���A
    public List<ProductVO> ChangeProductStatus(Integer corpUserId) {
         List<ProductVO> list=dao.getAll().stream()
                 .filter(e ->e.getProductStatus().equals(corpUserId))
                 .collect(Collectors.toList());
         return list;         
    }
    
    //�u���Product���A
    public ProductVO updateProductStatus(ProductVO produvtVO,Integer productStatus) {
        
        produvtVO.setProductStatus(productStatus);
        dao.update(produvtVO);
        
        return produvtVO;
    }
    
    // �M�����
    public List<ProductVO> getProductCategory(Integer productClass){
    	List<ProductVO> productList = dao.getAll()
    									 .stream()	
									     .filter(p -> p.getProductClass() == productClass)
									     .collect(Collectors.toList());
    	return productList;
    }
}
