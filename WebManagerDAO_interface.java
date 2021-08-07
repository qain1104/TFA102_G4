//本表由TFA10201黃鼎謙負責
package com.WebManager.model;

import java.util.List;


public interface WebManagerDAO_interface {
	public void insert(WebManagerVO WebManagerVO);
	public void update(WebManagerVO WebManagerVO);
	public void delete(Integer managerId);
	public WebManagerVO findByPrimaryKey(Integer managerId);//也可多設計用email,帳號查詢該會員(待商榷)
	public List<WebManagerVO> getAll();
	//萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<WebManagerVO> getAll(Map<String, String[]> map);

}
