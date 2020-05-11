package com.service.Impl;


import com.dao.VideoGroupDao;
import com.po.videogroup;
import com.service.Interface.VideoGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @ProjectName: graduation
 * @ClassName: VideoGroupServiceImpl
 *  * @Description: java类作用描述
 *  * @CreateUser: Mr.Hao
 *  * @CreateDate: 2020/4/18  22:30
 **/
@Service
public class VideoGroupServiceImpl implements VideoGroupService {
    @Autowired
    private VideoGroupDao videoGroupDao;
    @Override
    public List<videogroup> select_sub_area(int vgid) {
        System.out.println("服务端访问");
        List<videogroup> vg=videoGroupDao.selectSub(vgid);
        for (videogroup v : vg) {
            System.out.print(v.getVgid()+"  "+v.getName()+"  "+v.getInfo());
            System.out.println("");
        }
        return vg;
    }

    @Override
    public boolean selectAll() {
        List<videogroup> vg=videoGroupDao.selectAll();
        for (videogroup v : vg) {
            System.out.print(v.getVgid()+"  "+v.getName()+"  "+v.getInfo());
            System.out.println("");
        }
        return true;
    }
}
