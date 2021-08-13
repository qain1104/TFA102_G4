package com.report.model;
import java.util.*;

public interface REPORTDAO_interface {
    public int insert(REPORTVO reportVO);
    public int update(REPORTVO reportVO);
    public int delete(Integer reportSN);
    public REPORTVO findByPrimaryKey(Integer reportSN);
    public List<REPORTVO> getAll();
}
