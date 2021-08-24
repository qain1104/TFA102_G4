package com.report.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class REPORTService {

	private REPORTDAO_interface dao;

	public REPORTService() {
		dao = new REPORTJDBCDAO();
	}

	public REPORTVO addReport(Integer articleSN,Integer replySN,Integer userId,Integer reportClass,String reportContent,Timestamp reportDate,Integer reportStatus,Integer managerId,Timestamp reportAuditDate){

		REPORTVO reportVO = new REPORTVO();

		reportVO.setArticleSN(articleSN);
		reportVO.setReplySN(replySN);
		reportVO.setUserId(userId);
		reportVO.setReportClass(reportClass);
		reportVO.setReportContent(reportContent);
		reportVO.setReportDate(reportDate);
		reportVO.setReportStatus(reportStatus);
		reportVO.setManagerId(managerId);
		reportVO.setReportAuditDate(reportAuditDate);
		
		dao.insert(reportVO);

		return reportVO;
	}

	public REPORTVO updateReport(Integer reportSN,Integer articleSN,Integer replySN,Integer userId,Integer reportClass,String reportContent,Timestamp reportDate,Integer reportStatus,Integer managerId,Timestamp reportAuditDate){

		REPORTVO reportVO = new REPORTVO();

		reportVO.setReportSN(reportSN);
		reportVO.setArticleSN(articleSN);
		reportVO.setReplySN(replySN);
		reportVO.setUserId(userId);
		reportVO.setReportClass(reportClass);
		reportVO.setReportContent(reportContent);
		reportVO.setReportDate(reportDate);
		reportVO.setReportStatus(reportStatus);
		reportVO.setManagerId(managerId);
		reportVO.setReportAuditDate(reportAuditDate);
		
		dao.update(reportVO);

		return reportVO;
	}

	public void deleteReport(Integer reportSN) {
		dao.delete(reportSN);
	}

	public REPORTVO getOneReport(Integer reportSN) {
		return dao.findByPrimaryKey(reportSN);
	}

	public List<REPORTVO> getAll() {
		return dao.getAll();
	}
	
	public List<REPORTVO> getReportStatus(Integer reportStatus) {
		 List<REPORTVO> list=dao.getAll().stream()
				 .filter(e ->e.getReportStatus().equals(reportStatus))
				 .collect(Collectors.toList());
		 return list;		 
	}
	
	public REPORTVO updateManagerStatus(REPORTVO reportVO,Integer reportStatus,Integer managerId) {
		reportVO.setReportStatus(reportStatus);
		reportVO.setManagerId(managerId);
		reportVO.setReportAuditDate(new Timestamp(System.currentTimeMillis()));
		dao.update(reportVO);

		return reportVO;
		
	}
	
	public List<REPORTVO> getReportSameArticle(Integer articleSN) {
		 List<REPORTVO> list=dao.getAll().stream()
				 .filter(e ->e.getReportStatus().equals(0))
				 .filter(e ->e.getArticleSN().equals(articleSN))
				 .filter(e ->e.getReplySN().equals(0))
				 .collect(Collectors.toList());
		 return list;		 
	}
	
	public List<REPORTVO> getReportSameReply(Integer replySN) {
		 List<REPORTVO> list=dao.getAll().stream()
				 .filter(e ->e.getReportStatus().equals(0))
				 .filter(e ->e.getReplySN().equals(replySN))
				 .collect(Collectors.toList());
		 return list;		 
	}
}
