package com.productspec.model;

import java.util.*;

public interface ProductSpecDAO_interface {
          public void insert(ProductSpecVO productSpecVO);
          public void update(ProductSpecVO productSpecVO);
          public void delete(Integer productSN);
          public ProductSpecVO findByPrimaryKey(Integer productSN);
          public List<ProductSpecVO> getAll();
          //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//          public List<ProductSpecVO> getAll(Map<String, String[]> map); 
}
