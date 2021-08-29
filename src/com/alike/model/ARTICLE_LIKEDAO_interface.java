package com.alike.model;

import java.util.List;

public interface ARTICLE_LIKEDAO_interface {
    public int insert(ARTICLE_LIKEVO alikeVO);
    public int update(ARTICLE_LIKEVO alikeVO);
    public int delete(Integer articleLikeSN);
    public ARTICLE_LIKEVO findByPrimaryKey(Integer articleLikeSN);
    public List<ARTICLE_LIKEVO> getAll();
}
