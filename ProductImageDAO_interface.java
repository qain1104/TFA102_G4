package com.productimage.model;

import java.util.*;

public interface ProductImageDAO_interface {
          public void insert(ProductImageVO productImageVO);
          public void update(ProductImageVO productImageVO);
          public void delete(Integer productSN);
          public ProductImageVO findByPrimaryKey(Integer productSN);
          public List<ProductImageVO> getAll();
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//          public List<ProductImageVO> getAll(Map<String, String[]> map); 
}
