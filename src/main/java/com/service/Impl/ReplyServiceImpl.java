package com.service.Impl;


import com.dao.ReplyDao;
import com.po.reply;
import com.service.Interface.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @ProjectName: graduation
 * @ClassName: ReplyServiceImpl
 * @Description: java类作用描述
 * @CreateUser: Mr.Hao
 * @CreateDate: 2020/4/21  17:10
 **/
@Service
public class ReplyServiceImpl implements ReplyService {
    @Autowired
    private ReplyDao replyDao;
    @Override
    public int reply(int comment_id, int reply_id, String reply_name, String content, int from_uid, String from_name, String from_headimg, String birthtime) {
        reply r=new reply();
        r.setBirthtime(birthtime);
        r.setComment_id(comment_id);
        r.setReply_name(reply_name);
        r.setReply_id(reply_id);
        r.setFrom_uid(from_uid);
        r.setContent(content);
        r.setFrom_headimg(from_headimg);
        r.setFrom_name(from_name);
        replyDao.send_reply(r);
        return replyDao.selectLast(from_uid);
    }

    @Override
    public List<reply> getReplyList(int commentid) {
        List<reply> replyList=replyDao.commentList(commentid);
        Iterator<reply> rll=replyList.iterator();
        while(rll.hasNext()){
            reply r=rll.next();

        }
        return replyList;
    }

    @Override
    public boolean delete_reply(String table, int id) {
        if(table.equals("comment")){
            replyDao.delete_com(id);
        }
        if(table.equals("reply")){
            replyDao.delete_rep(id);
        }
        return true;
    }

    @Override
    public int getfromid(int rid) {
        return replyDao.selectfromid(rid);
    }

    @Override
    public reply getreply(int id) {
        System.out.println("getreply");
        return replyDao.selectReply(id);
    }
}
