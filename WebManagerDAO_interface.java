//�����TFA10201�������t�d
package com.WebManager.model;

import java.util.List;


public interface WebManagerDAO_interface {
	public void insert(WebManagerVO WebManagerVO);
	public void update(WebManagerVO WebManagerVO);
	public void delete(Integer managerId);
	public WebManagerVO findByPrimaryKey(Integer managerId);//�]�i�h�]�p��email,�b���d�߸ӷ|��(�ݰӺe)
	public List<WebManagerVO> getAll();
	//�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//  public List<WebManagerVO> getAll(Map<String, String[]> map);

}
