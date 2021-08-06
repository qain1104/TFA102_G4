//本表由TFA10201黃鼎謙負責
package com.SportsNews.model;

import java.util.List;

public interface SportsNewsDAO_interface {
	public void insert(SportsNewsVO sportsNewsVO);

	public void update(SportsNewsVO sportsNewsVO);

	public void delete(Integer corpUserId);

	public SportsNewsVO findByPrimaryKey(Integer newsSn);

	public List<SportsNewsVO> getAll();
	// 萬用複合查詢(傳入參數型態Map)(回傳 List)
	// public List<CorpUserVO> getAll(Map<String, String[]> map);
}
