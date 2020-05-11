package com.service.Impl;


import com.dao.PostImageDao;
import com.po.postimagepath;
import com.service.Interface.PostImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @ProjectName: graduation
 * @ClassName: PostImageServiceImpl
 * @Description: java类作用描述
 * @CreateUser: Mr.Hao
 * @CreateDate: 2020/4/18  17:07
 **/
@Service
public class PostImageServiceImpl implements PostImageService {
    @Autowired
    private PostImageDao postImageDao;
    @Override
    public boolean addPIPath(int pid, List path) {
        postimagepath pi=new postimagepath();
        pi.setPid(pid);
        Iterator pap =path.iterator();
        while(pap.hasNext()){
            String imagepath=pap.next().toString();
            System.out.println(imagepath);
            pi.setPostimagepath(imagepath);
            postImageDao.AddPIP(pi);
        }
        return true;
    }

    @Override
    public List<String> selectPostImg(int pid) {

        List<String> pip=postImageDao.selectByPid(pid);
        Iterator<String> it=pip.iterator();
        while(it.hasNext()){
            String p=it.next();

            System.out.println(p);
        }
        return pip;
    }
}
