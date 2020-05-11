package com.service.Impl;


import com.dao.VerifyDao;
import com.service.Interface.VerifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 *
 * @ProjectName: graduation
 * @ClassName: VerifyServiceImpl
 * @Description: java类作用描述
 * @CreateUser: Mr.Hao
 * @CreateDate: 2020/4/26  17:07
 **/
@Service
public class VerifyServiceImpl implements VerifyService {
    @Autowired
    private VerifyDao verifyDao;
    @Override
    public boolean check(String verid,String pwd) {
        boolean flag=false;
        String dbpwd=verifyDao.selectpwdById(verid);
        if(dbpwd.equals(pwd)){
            flag=true;
        }
        return flag;
    }
}
