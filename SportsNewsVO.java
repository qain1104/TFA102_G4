//¥»ªí¥ÑTFA10201¶À¹©Á¾­t³d
package com.SportsNews.model;


import java.sql.Timestamp;

public class SportsNewsVO {
	private Integer newsSn;
	private Integer	managerId;
	private String	title;
	private String	content;
	private Timestamp newsDate;
	private String	newsSource;
	private Integer	newsType;
	
	public SportsNewsVO() {
		super();
	}
	
	public Integer getNewsSn() {
		return newsSn;
	}
	public void setNewsSn(Integer newsSn) {
		this.newsSn = newsSn;
	}
	public Integer getManagerId() {
		return managerId;
	}
	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getNewsDate() {
		return newsDate;
	}
	public void setNewsDate(Timestamp newsDate) {
		this.newsDate = newsDate;
	}
	public String getNewsSource() {
		return newsSource;
	}
	public void setNewsSource(String newsSource) {
		this.newsSource = newsSource;
	}
	public Integer getNewsType() {
		return newsType;
	}
	public void setNewsType(Integer newsType) {
		this.newsType = newsType;
	}

	@Override
	public String toString() {
		return "SportsNewsVO [newsSn=" + newsSn + ", managerId=" + managerId + ", title=" + title + ", content="
				+ content + ", newsDate=" + newsDate + ", newsSource=" + newsSource + ", newsType=" + newsType + "]";
	}


}
