package com.service.Interface;


import com.po.user;

import java.util.List;

public interface UserService {
    public boolean login(String zh, String ps);//登录账号密码匹配
    public boolean register(String name, String password, String phone);//注册用户
    public int uniq(String zh);//查询uid，用于辅助查询其他
    public String uniq_name(String name);//用户名是否存在
    public String query_name(int uid);//用户名是否存在
    boolean update_setting(int uid, String name, short sex, String qianming);//更新信息
    boolean update_sign(int uid, String sign);
    boolean update_head(int uid, String head);
    public String setting(int uid, String field);
    String getheadimg(int uid);
    user query(int uid);
    List<user> searchuser(String name);
    String checkpwd(int uid);
    boolean updatepwd(int uid, String pwd);
    boolean findpwd(String email, String pwd);
    boolean block(int uid);
}
