package com.dao;

import com.po.comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
@Mapper
public interface CommentDao {
    boolean send_comment(comment com);
    List<comment> commentList(int pid);
    boolean delete_com(int id);
    int selectLast(int uid);
    int selectuid(int comment_id);
    comment getcomment(int id);
}
