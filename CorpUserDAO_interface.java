//本表由TFA10201黃鼎謙負責
package com.CorpUser.model;

import java.util.List;



//此介面裡的方法就是代表對企業會員表格的CRUD(新刪修查)
public interface CorpUserDAO_interface {
	public void insert(CorpUserVO corpUserVO);
	public void update(CorpUserVO corpUserVO);
	public void delete(Integer corpUserId);
	public CorpUserVO findByPrimaryKey(Integer corpUserId);//也可多設計用email,帳號查詢該會員(待商榷)
	public List<CorpUserVO> getAll();
	//萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<CorpUserVO> getAll(Map<String, String[]> map);

}
