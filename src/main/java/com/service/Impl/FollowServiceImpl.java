package com.service.Impl;


import com.dao.FollowDao;
import com.po.follow;
import com.service.Interface.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class FollowServiceImpl implements FollowService {
    @Autowired
    private FollowDao followDao;
    @Override
    /*
    @following:要查的用户
    @follower:我自己
     */
    public String queryRelation(int following,int follower) {
        follow f=new follow();
        f.setFollowing(following);
        f.setFollower(follower);
        System.out.println(f.getFollowing()+f.getFollower());
        f= followDao.queryRelation(f);
        if(f!=null){
            int relation=f.getRelation();
            if(relation==3){
                return "互关";
            }
            if(relation==0){
                return  "关注";
            }
            if(relation==2){
                if(f.getFollowing()==following) {
                    return "关注";
                }else {
                    return "已关注";
                }
            }
            if(relation==1){
                if(f.getFollowing()==following) {
                    return "已关注";
                }else {
                    return "关注";
                }
            }

        }

        return "关注";
    }

    @Override
    public List<Integer> queryFollower(int f) {
        List<Integer> fan1=followDao.queryFanFollowing(f);
        System.out.println(fan1.size());
        List<Integer> fan2=followDao.queryFanFollower(f);
        System.out.println(fan2.size());
        fan1.addAll(fan2);
        return fan1;
    }

    @Override
    public List<Integer> queryFollowing(int f) {
        List<Integer> fan1=followDao.queryFollowing(f);
        System.out.println(fan1.size());
        List<Integer> fan2=followDao.queryFollower(f);
        System.out.println(fan2.size());
        fan1.addAll(fan2);
        return fan1;
    }

    @Override
    public boolean follow( int following,int follower) {
        //先查询关系
        follow f=new follow();
        f.setFollowing(following);
        f.setFollower(follower);
        f=followDao.queryRelation(f);
        follow to=new follow();
        if(f!=null){
            //双方肯定有关系，因为没有关系就没有记录，不存在0的说法
            int relation=f.getRelation();
            if(f.getFollowing()==follower){//如果是操作用户在ing的位置
                to.setFollowing(follower);
                to.setFollower(following);
                if(relation==1){//即对方已关注操作用户，回粉
                    //改为互关
                    System.out.println("回粉");
                    to.setRelation(3);
                }
                if(relation==2){//即操作用户已关注对方, 取关
                    //删除
                    System.out.println("取关");
                followDao.noFollow(f);
                    return true;
                }
                if(relation==3){//已经互关
                    System.out.println("互关的取关");
                    to.setRelation(1);
                }
                followDao.updateFollow(to);
            } else if(f.getFollowing()==following){
                to.setFollowing(following);
                to.setFollower(follower);
                if(relation==2){//即对方已关注操作用户，回粉
                    //改为互关
                    to.setRelation(3);
                }
                if(relation==1){//即操作用户已关注对方, 取关
                    //删除
                    followDao.noFollow(f);
                    return true;
                }
                if(relation==3){//已经互关
                    to.setRelation(2);
                }
                followDao.updateFollow(to);
            }
        }else{
            //如果双方都没关系
            to.setFollowing(following);
            to.setFollower(follower);
            to.setRelation(1);
            System.out.println(to.getFollowing()+" "+to.getFollower()+" "+to.getRelation());
            followDao.follow(to);
        }
        return true;
    }

    @Override
    public boolean removeFollow(int follower, int following) {
        return false;
    }
}
