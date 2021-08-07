//�����TFA10201�������t�d
package com.CorpUser.model;

import java.util.List;



//�������̪���k�N�O�N�����~�|����檺CRUD(�s�R�׬d)
public interface CorpUserDAO_interface {
	public void insert(CorpUserVO corpUserVO);
	public void update(CorpUserVO corpUserVO);
	public void delete(Integer corpUserId);
	public CorpUserVO findByPrimaryKey(Integer corpUserId);//�]�i�h�]�p��email,�b���d�߸ӷ|��(�ݰӺe)
	public List<CorpUserVO> getAll();
	//�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//  public List<CorpUserVO> getAll(Map<String, String[]> map);

}
