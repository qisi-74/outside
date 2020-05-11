package com.service.Interface;


import com.po.videogroup;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @ProjectName: graduation
 * @ClassName: VideoGroupService
 * @Description: java类作用描述
 * @CreateUser: Mr.Hao
 * @CreateDate: 2020/4/18  22:28
 **/
public interface VideoGroupService {
    List<videogroup> select_sub_area(int vgid);
    boolean selectAll();
}
