package com.rlike.model;

import java.util.List;

public interface REPLY_LIKEDAO_interface {
    public int insert(REPLY_LIKEVO rlikeVO);
    public int update(REPLY_LIKEVO rlikeVO);
    public int delete(Integer replyLikeSN);
    public REPLY_LIKEVO findByPrimaryKey(Integer replyLikeSN);
    public List<REPLY_LIKEVO> getAll();
}
