package com.csresponse.model;

import java.sql.Date;
import java.util.List;


public class CsresponseService {
	private CsresponseDAO_interface dao;
	
	public CsresponseService() {
		dao = new CsresponseJDBCDAO();
	}

	public CsresponseVO addCsresponse(Integer managerId, Integer userId, String csDescription, String csResponse,
			Date submittedDate, Date closingDate, Integer responseStatus) {

		CsresponseVO csresponseVO = new CsresponseVO();

		csresponseVO.setManagerId(managerId);
		csresponseVO.setUserId(userId);
		csresponseVO.setCsDescription(csDescription);
		csresponseVO.setCsResponse(csResponse);
		csresponseVO.setSubmittedDate(submittedDate);
		csresponseVO.setClosingDate(closingDate);
		csresponseVO.setResponseStatus(responseStatus);
		dao.insert(csresponseVO);

		return csresponseVO;
	}
	
	public CsresponseVO updateCsresponse(Integer  responseSN,Integer managerId, Integer userId, String csDescription, String csResponse,
			Date submittedDate, Date closingDate, Integer responseStatus) {
		
		CsresponseVO csresponseVO = new CsresponseVO();
		
		csresponseVO.setResponseSN(responseSN);
		csresponseVO.setManagerId(managerId);
		csresponseVO.setUserId(userId);
		csresponseVO.setCsDescription(csDescription);
		csresponseVO.setCsResponse(csResponse);
		csresponseVO.setSubmittedDate(submittedDate);
		csresponseVO.setClosingDate(closingDate);
		csresponseVO.setResponseStatus(responseStatus);
		dao.update(csresponseVO);
		
		return csresponseVO;
		
	}
	
	public void deleteCsresponse(Integer  responseSN) {
		dao.delete(responseSN);
	}
	
	public CsresponseVO getOneEmp(Integer  responseSN) {
		return dao.findByPrimaryKey(responseSN);
	}
	public List<CsresponseVO> getAll() {
		return dao.getAll();
	}
}