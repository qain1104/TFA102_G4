package com.article.model;
import java.util.*;

public interface ARTICLEDAO_interface {
    public int insert(ARTICLEVO articleVO);
    public int update(ARTICLEVO articleVO);
    public int delete(Integer articleSN);
    public ARTICLEVO findByPrimaryKey(Integer articleSN);
    public List<ARTICLEVO> getAll();
    public List<ARTICLEVO> findByClassKey(Integer articleSN);
}
