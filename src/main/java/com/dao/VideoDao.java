package com.dao;

import com.po.video;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


import java.util.List;


@Repository
@Mapper
public interface VideoDao {
     String selectnById(int i);
     boolean AddVideo(video v);
     video selectAllById(int vid);
     int selectLastVid(int uid);
     List<video> selectvidByuid(int uid);
     List<video> selectvideo(@Param("uid") int uid, @Param("vname") String vname);
     List<video> searchvideo(String vtext);
     boolean updatestate(video v);
}
