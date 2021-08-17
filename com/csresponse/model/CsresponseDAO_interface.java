package com.csresponse.model;

import java.util.List;


public interface CsresponseDAO_interface {
	public void insert(CsresponseVO csresponseVO);
    public void update(CsresponseVO csresponseVO);
    public void delete(Integer responseSN);
    public CsresponseVO findByPrimaryKey(Integer responseSN);
    public List<CsresponseVO> getAll();

}
