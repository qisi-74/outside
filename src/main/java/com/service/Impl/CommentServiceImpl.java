package com.service.Impl;


import com.dao.CommentDao;
import com.po.comment;
import com.service.Interface.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @ProjectName: graduation
 * @ClassName: CommentServiceImpl
 * @Description: java类作用描述
 * @CreateUser: Mr.Hao
 * @CreateDate: 2020/4/21  13:35
 **/
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentDao commentDao;
    @Override
    public boolean comment(int pid, String content, int from_uid, String headimg, String name, String birthtime) {
        comment com=new comment();
        com.setPid(pid);
        com.setContent(content);
        com.setFrom_uid(from_uid);
        com.setFrom_headimg(headimg);
        com.setFrom_name(name);
        com.setBirthtime(birthtime);
        System.out.println(com.getBirthtime());
        commentDao.send_comment(com);
        return true;
    }

    @Override
    public List<comment> getCommonList(int pid) {
        List<comment> pcl=commentDao.commentList(pid);
        Iterator<comment> it=pcl.iterator();
        while(it.hasNext()){
            comment c=it.next();

            System.out.println(c.getContent());
        }
        return pcl;
    }

    @Override
    public boolean delete(int commentid) {
        System.out.println("zhixing");
        commentDao.delete_com(commentid);
        return true;
    }

    @Override
    public int getLast(int uid) {
        return commentDao.selectLast(uid);
    }

    @Override
    public int getuid(int cid) {
        return commentDao.selectuid(cid);
    }

    @Override
    public comment getcomment(int id) {
        System.out.println("comment");
        return commentDao.getcomment(id);
    }
}
