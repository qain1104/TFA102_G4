package com.SportsNews.model;

import java.sql.Timestamp;
import java.util.List;

public class SportsNewsService {

	private SportsNewsDAO_interface dao;

	public SportsNewsService() {
		dao = new SportsNewsJDBCDAO();
	}

	public SportsNewsVO addSportsNews(Integer managerId, String title, String content, Timestamp newsDate,
			String newsSource, Integer newsType) {

		SportsNewsVO sportsNewsVO = new SportsNewsVO();
		sportsNewsVO.setManagerId(managerId);
		sportsNewsVO.setTitle(title);
		sportsNewsVO.setContent(content);
		sportsNewsVO.setNewsDate(newsDate);
		sportsNewsVO.setNewsSource(newsSource);
		sportsNewsVO.setNewsType(newsType);
		dao.insert(sportsNewsVO);

		return sportsNewsVO;
	}

	public SportsNewsVO updateSportsNews(Integer newsSn, Integer managerId, String title, String content,
			Timestamp newsDate, String newsSource, Integer newsType) {

		SportsNewsVO sportsNewsVO = new SportsNewsVO();
		sportsNewsVO.setNewsSn(newsSn);
		sportsNewsVO.setManagerId(managerId);
		sportsNewsVO.setTitle(title);
		sportsNewsVO.setContent(content);
		sportsNewsVO.setNewsDate(newsDate);
		sportsNewsVO.setNewsSource(newsSource);
		sportsNewsVO.setNewsType(newsType);
		dao.update(sportsNewsVO);

		return sportsNewsVO;
	}

	public void deleteSportsNews(Integer newsSn) {
		dao.delete(newsSn);
	}

	public SportsNewsVO getOneSportsNews(Integer newsSn) {
		return dao.findByPrimaryKey(newsSn);
	}

	public List<SportsNewsVO> getAll() {
		return dao.getAll();
	}

}
