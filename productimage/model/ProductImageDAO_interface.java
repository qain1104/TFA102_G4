package com.productimage.model;

import java.util.*;

public interface ProductImageDAO_interface {
          public void insert(ProductImageVO productImageVO);
          public void update(ProductImageVO productImageVO);
          public void delete(Integer productSN);
          public ProductImageVO findByPrimaryKey(Integer productSN);
          public List<ProductImageVO> getAll();
          //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//          public List<ProductImageVO> getAll(Map<String, String[]> map); 
}
