package com.service.Interface;


import com.po.comment;

import java.util.List;

public interface CommentService {
    boolean comment(int pid, String content, int from_uid, String headimg, String name, String birthtime);
    List<comment> getCommonList(int pid);
    boolean delete(int commentid);
    int getLast(int uid);
    int getuid(int cid);
    comment getcomment(int id);
}
