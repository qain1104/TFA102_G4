//�����TFA10201�������t�d
package com.SportsNews.model;

import java.util.List;

public interface SportsNewsDAO_interface {
	public void insert(SportsNewsVO sportsNewsVO);

	public void update(SportsNewsVO sportsNewsVO);

	public void delete(Integer corpUserId);

	public SportsNewsVO findByPrimaryKey(Integer newsSn);

	public List<SportsNewsVO> getAll();
	// �U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
	// public List<CorpUserVO> getAll(Map<String, String[]> map);
}
