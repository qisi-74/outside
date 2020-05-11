package com.dao;

import com.po.post;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
@Mapper
public interface PostDao {
    boolean AddPost(post p);
    boolean AddPost_V(post p);
    int Lastpid(int uid);
    List<post> selectAllBid(int uid);
    List<post> selectAllInid(String feed_str);
    int getvid(int pid);
    int getuid(int pid);
    int getpid(int vid);
    post select(int pid);
    boolean updatestate(post p);
}
