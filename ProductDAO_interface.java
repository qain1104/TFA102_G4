package com.product.model;

import java.util.*;

public interface ProductDAO_interface {
          public void insert(ProductVO productVO);
          public void update(ProductVO productVO);
          public void delete(Integer productSN);
          public ProductVO findByPrimaryKey(Integer productSN);
          public List<ProductVO> getAll();
          //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//          public List<ProductVO> getAll(Map<String, String[]> map); 
}