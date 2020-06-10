package com.service.Impl;


import com.dao.UserDao;
import com.po.user;
import com.service.Interface.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private static final Log logger = LogFactory.getLog(UserServiceImpl.class);
    @Autowired
    private UserDao userDao;

    @Override
    public boolean login(String zh,String ps) {
        if(userDao.selectUserByZh(zh)!=null){
            if(userDao.selectUserByZh(zh).equals(ps)){
                return true;
            }else {
                logger.info("密码不对呢");
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean register(String name,String password,String phone) {
        if(userDao.selectUserByZh(phone)!=null){
            return false;
        }else {
            user u=new user();
            u.setName(name);
            u.setPassword(password);
            u.setPhone(phone);
            userDao.AddUser(u);
            return true;
        }
    }

    @Override
    public int uniq(String zh) {
        return userDao.selectUidByZh(zh);
    }

    @Override
    public String uniq_name(String name) {
        return userDao.selectNameByName(name);
    }

    @Override
    public String query_name(int uid) {
        return  userDao.selectnameById(uid);
    }

    @Override
    public boolean update_setting(int uid, String name,short sex, String qianming) {
        user u=new user();
        u.setUid(uid);
        u.setName(name);
        u.setSex(sex);
        u.setQianming(qianming);
        userDao.updatesetting(u);
        return true;
    }

    @Override
    public boolean update_sign(int uid,String sign) {
        user u=new user();
        u.setUid(uid);
        u.setQianming(sign);
        userDao.updatesetting(u);
        return true;
    }

    @Override
    public boolean update_head(int uid, String head) {
        user u=new user();
        u.setUid(uid);
        u.setTouxiang(head);
        userDao.updatesetting(u);
        return true;
    }

    @Override
    public String setting(int uid,String field ) {
        String set="";
        switch (field){
            case "name":set=userDao.selectnameById(uid);break;
            case "phone":set=userDao.selectphoneById(uid);break;
            case "idcard":set=userDao.selectidcardById(uid);break;
            case "pwd":set=userDao.selectpwdById(uid);break;
            case "email":set=userDao.selectemailById(uid);break;
        }
        System.out.println(set);
        return set;
    }

    @Override
    public String getheadimg(int uid) {
        return userDao.selectheadById(uid);
    }

    @Override
    public user query(int uid) {
        user u=userDao.selectuserByid(uid);
        return u;
    }

    @Override
    public List<user> searchuser(String name) {
        List<user> result=userDao.searchuser(name);
        return result;
    }

    @Override
    public String checkpwd(int uid) {
        String old=userDao.selectpwdById(uid);
        return old;
    }

    @Override
    public boolean updatepwd(int uid, String pwd) {
        user u=new user();
        u.setUid(uid);
        u.setPassword(pwd);
        userDao.updatesetting(u);
        return true;
    }
    @Override
    public boolean updatemail(int uid, String email) {
        logger.info(uid+email);
        user u=new user();
        u.setUid(uid);
        u.setEmail(email);
        userDao.updatesetting(u);
        return true;
    }

    @Override
    public boolean findpwd(String email, String pwd) {
        user u=new user();
        u.setEmail(email);
        u.setPassword(pwd);
        System.out.println(u.getPassword()+u.getEmail());
        userDao.setpwd(u);
        return true;
    }

    @Override
    public boolean block(int uid) {
        byte state=userDao.selectuserByid(uid).getState();
        byte upstate=0;
        if(state==0){
            upstate=1;
        }
        if(state==1){
            upstate=0;
        }
        user u=new user();
        u.setUid(uid);
        u.setState(upstate);
        userDao.updatestate(u);
        System.out.println(upstate);
        return true;
    }
}
