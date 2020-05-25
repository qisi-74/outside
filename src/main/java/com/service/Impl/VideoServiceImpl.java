package com.service.Impl;


import com.dao.VideoDao;
import com.po.video;
import com.service.Interface.VideoService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @ProjectName: graduation
 * @ClassName: VideoServiceImpl
 * @Description: java类作用描述
 * @CreateUser: Mr.Hao
 * @CreateDate: 2020/4/12  9:30
 **/
@Service
public class VideoServiceImpl implements VideoService {
    private static final Log logger = LogFactory.getLog(VideoServiceImpl.class);
    @Autowired
    private VideoDao videoDao;

    @Override
    public boolean upload(String vname, String vface, String vcut_pic, String vaddress,String info, String vtime, int uid, String videokind_id) {

        video v=new video();
        v.setVname(vname);
        v.setVface(vface);
        v.setVcut_pic(vcut_pic);
        v.setVaddress(vaddress);
        v.setVinfo(info); ;
        v.setVtime(vtime);
        v.setUid(uid);
        v.setVideokind_id(Integer.parseInt(videokind_id.trim()));
        videoDao.AddVideo(v);
        return true;
    }

    @Override
    public video videoplay(int vid) {
        video v=new video();
        v=videoDao.selectAllById(vid);
        return v;
    }

    @Override
    public int getLast(int uid) {

        return videoDao.selectLastVid(uid);
    }

    @Override
    public List<video> getVidList(int uid) {
        List<video> videolist=videoDao.selectvidByuid(uid);
        Iterator<video> vl=videolist.iterator();
        while(vl.hasNext()){
            video v=vl.next();

        }
        return videolist;
    }
    @Override
    public List<video> getVtextList(int uid,String text) {

        List<video> videolist=videoDao.selectvideo(uid,text);
        Iterator<video> vl=videolist.iterator();
        while(vl.hasNext()){
            video v=vl.next();

        }
        return videolist;
    }

    @Override
    public List<video> SearchList(String text) {

            List<video> videolist=videoDao.searchvideo(text);
            Iterator<video> vl=videolist.iterator();
            while(vl.hasNext()){
                video v=vl.next();

                System.out.println(v.getVid()+v.getVname());
            }
            return videolist;
        }

    @Override
    public boolean block(int vid) {
        byte state=videoDao.selectAllById(vid).getVstate();
        byte upstate=0;
        if(state==0){
            upstate=1;
        }
        if(state==1){
            upstate=0;
        }
        video v=new video();
        v.setVid(vid);
        v.setVstate(upstate);
        videoDao.updatestate(v);
        System.out.println(upstate);
        return true;
    }

}
