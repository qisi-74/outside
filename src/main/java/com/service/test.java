package com.service;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @ProjectName: graduation
 * @ClassName: test
 * @Description: java类作用描述
 * @CreateUser: Mr.Hao
 * @CreateDate: 2020/4/18  18:17
 **/
@Service
public class test {
    public void tt(List list){
        System.out.println(list.get(0));
    }
}
