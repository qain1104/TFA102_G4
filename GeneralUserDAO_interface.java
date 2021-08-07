//本表由TFA10201黃鼎謙負責
package com.GeneralUser.model;

import java.util.*;

//此介面裡的方法就是代表對一般會員表格的CRUD(新刪修查)
public interface GeneralUserDAO_interface {
	
		public void insert(GeneralUserVO generalUserVO);
		public void update(GeneralUserVO generalUserVO);
		public void delete(Integer userId);
		public GeneralUserVO findByPrimaryKey(Integer userId);//也可多設計用email,帳號查詢該會員(待商榷)
		public List<GeneralUserVO> getAll();
		//萬用複合查詢(傳入參數型態Map)(回傳 List)
//      public List<GeneralUserVO> getAll(Map<String, String[]> map); 
}
