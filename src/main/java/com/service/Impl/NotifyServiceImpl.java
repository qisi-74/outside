package com.service.Impl;


import com.dao.NotifyDao;
import com.dao.SubScriptionDao;
import com.dao.UserNotifyDao;
import com.po.notify;
import com.po.subscription;
import com.po.usernotify;
import com.service.Interface.NotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @ProjectName: graduation
 * @ClassName: FollowServiceImpl
 * @Description: java类作用描述
 * @CreateUser: Mr.Hao
 * @CreateDate: 2020/4/25  11:12
 **/
@Service
public class NotifyServiceImpl implements NotifyService {
    @Autowired
    private UserNotifyDao userNotifyDao;
    @Autowired
    private NotifyDao notifyDao;
    @Autowired
    private SubScriptionDao subScriptionDao;

    @Override
    public long createUserNoity(int uid, long nid, Timestamp time) {
        usernotify un=new usernotify();
        un.setUid(uid);
        un.setNotify(nid);
        un.setCreatedAt(time);
        userNotifyDao.AddUserNotify(un);
        return 0;
    }

    @Override
    public long createNoity(String content, byte type, int targetid, String targetType, String action, int uid, Timestamp time) {
        notify n=new notify();
        n.setContent(content);
        n.setType(type);
        n.setTargetid(targetid);
        n.setTargetType(targetType);
        n.setAction(action);
        n.setSender(uid);
        n.setCreatedAt(time);
        notifyDao.AddNotify(n);

        return notifyDao.selectLast(uid);
    }

    @Override
    public long createSubScript(int targetid, String targetType, String action, int uid, Timestamp time) {
        subscription ss=new subscription();
        ss.setAction(action);
        ss.setCreatedAt(time);
        ss.setuid(uid);
        ss.setTargetid(targetid);
        ss.setTargetType(targetType);
        subScriptionDao.AddSubScript(ss);
        return 0;
    }

    @Override
    public List<notify> pullRemind(int uid) {
//        List<subscription>=subScriptionDao.getSubScriptList(uid);
        List<Long> usernotify=userNotifyDao.getAllNotify(uid);
        Iterator all=usernotify.iterator();
        System.out.println("所有的消息列表是");
        List<notify> notify=new ArrayList<>();
        while (all.hasNext()){

            long a1= (long) all.next();
            System.out.println(a1);
            notify a =notifyDao.getNotify(a1);
            notify.add(a);
        }
        Iterator alln=notify.iterator();
        while (alln.hasNext()){
            notify n=new notify();
            n= (notify) alln.next();
        }
        return notify;
    }

    @Override
    public int pullNotReadReming(int uid) {
        List<Long> NotReadnotify=userNotifyDao.getNotReadNotify(uid);
        return NotReadnotify.size();
    }

    @Override
    public boolean read(int uid) {
        userNotifyDao.read(uid);
        return true;
    }
}
