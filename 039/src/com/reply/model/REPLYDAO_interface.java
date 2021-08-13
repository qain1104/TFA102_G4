package com.reply.model;
import java.util.*;

public interface REPLYDAO_interface {
    public int insert(REPLYVO replyVO);
    public int update(REPLYVO replyVO);
    public int delete(Integer replySN);
    public REPLYVO findByPrimaryKey(Integer replySN);
    public List<REPLYVO> getAll();
}
