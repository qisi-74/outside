package com.dao;

import com.po.user;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
@Mapper
public interface UserDao {
    int selectUidByZh(String zh);//zhanghao
    String selectNameByName(String name);
    String selectUserByZh(String zh);
    String selectnameById(int uid);
    String selectphoneById(int uid);
    String selectpwdById(int uid);
    String selectemailById(int uid);
    String selectidcardById(int uid);
    String selectheadById(int uid);
    boolean AddUser(user u);
    boolean updatesetting(user u);
    user selectuserByid(int uid);
    List<user> searchuser(String name);
    boolean setpwd(user u);
    boolean updatestate(user u);
}
