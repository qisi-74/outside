package com.dao;

import com.po.videogroup;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
@Mapper
public interface VideoGroupDao {
    List<videogroup> selectSub(int vgid);
    List<videogroup> selectAll();
}
