package com.dao;

import com.po.reply;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
@Mapper
public interface ReplyDao {
    boolean send_reply(reply rep);
    List<reply> commentList(int comment_id);
    boolean delete_com(int comment_id);
    boolean delete_rep(int id);
    int selectLast(int uid);
    int selectfromid(int rid);
    reply selectReply(int id);
}
