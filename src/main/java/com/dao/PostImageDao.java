package com.dao;

import com.po.postimagepath;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
@Mapper
public interface PostImageDao {
    boolean AddPIP(postimagepath postimagepath);
    List<String> selectByPid(int pid);
}
