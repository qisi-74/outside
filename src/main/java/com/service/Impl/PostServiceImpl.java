package com.service.Impl;


import com.dao.PostDao;
import com.po.post;
import com.service.Interface.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @ProjectName: graduation
 * @ClassName: PostServiceImpl
 * @Description: java类作用描述
 * @CreateUser: Mr.Hao
 * @CreateDate: 2020/4/18  15:15
 **/
@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostDao postDao;


    @Override
    public boolean post_publish(String pcontext, int uid, String birth) {
        post p=new post();
        p.setPcontext(pcontext);
        p.setUid(uid);
        p.setPbirth(birth);
        postDao.AddPost(p);
        return true;
    }

    @Override
    public boolean post_publish_V(String pcontext, int vid, int uid, String birth) {
        post p=new post();
        p.setPcontext(pcontext);
        p.setVid(vid);
        p.setUid(uid);
        p.setPbirth(birth);
        postDao.AddPost_V(p);
        return true;
    }

    @Override
    public int getLast(int uid) {
        return postDao.Lastpid(uid);
    }

    @Override
    public List<post> getAll(int uid) {
        List<post> postList=postDao.selectAllBid(uid);
        Iterator<post> pl=postList.iterator();
        while(pl.hasNext()){
            post p=pl.next();
        }
        return postList;
    }

    @Override
    public List<post> getAll(List<Integer> feed) {
        Iterator<Integer> pl=feed.iterator();
        String feed_str="";
        while(pl.hasNext()){
            feed_str+=pl.next()+",";
        }
        feed_str=feed_str.substring(0,feed_str.lastIndexOf(","));
        System.out.println(feed_str);
        List<post> postList=postDao.selectAllInid(feed_str);
        System.out.println("共有这么多动态"+postList.size());
        return postList;
    }

    @Override
    public int getvid(int pid) {
        return postDao.getvid(pid);
    }
    @Override
    public int getuid(int pid) {
        return postDao.getuid(pid);
    }

    @Override
    public int getpid(int vid) {
        return postDao.getpid(vid);
    }

    @Override
    public post getpost(int pid) {
        post p=null;
        p=postDao.select(pid);
        return p;
    }

    @Override
    public boolean block(int pid) {
        byte state=postDao.select(pid).getState();
        byte upstate=0;
        if(state==0){
            upstate=1;
        }
        if(state==1){
            upstate=0;
        }
        post p=new post();
        p.setPid(pid);
        p.setState(upstate);
        postDao.updatestate(p);
        System.out.println(upstate);
        return true;
    }
}
