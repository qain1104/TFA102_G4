//�����TFA10201�������t�d
package com.GeneralUser.model;

import java.util.*;

//�������̪���k�N�O�N���@��|����檺CRUD(�s�R�׬d)
public interface GeneralUserDAO_interface {
	
		public void insert(GeneralUserVO generalUserVO);
		public void update(GeneralUserVO generalUserVO);
		public void delete(Integer userId);
		public GeneralUserVO findByPrimaryKey(Integer userId);//�]�i�h�]�p��email,�b���d�߸ӷ|��(�ݰӺe)
		public List<GeneralUserVO> getAll();
		//�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//      public List<GeneralUserVO> getAll(Map<String, String[]> map); 
}
