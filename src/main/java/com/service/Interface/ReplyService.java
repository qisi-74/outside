package com.service.Interface;


import com.po.reply;

import java.util.List;

public interface ReplyService {
    int reply(int comment_id, int reply_id, String reply_name, String content, int from_uid, String from_name, String from_headimg, String birthtime);
    List<reply> getReplyList(int commentid);
    boolean delete_reply(String table, int id);
    int getfromid(int rid);
    reply getreply(int id);
}
