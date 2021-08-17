package com.sportsgroup.model;

import java.util.List;


public interface SportsGroupDAO_interface {
	 public void insert(SportsGroupVO sportsGroupSNVO);
     public void update(SportsGroupVO sportsGroupSNVO);
     public void delete(Integer sportsGroupSN);
     public SportsGroupVO findByPrimaryKey(Integer sportsGroupSN);
     public List<SportsGroupVO> getAll();
}
