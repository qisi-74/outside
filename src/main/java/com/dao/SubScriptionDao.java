package com.dao;

import com.po.subscription;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
@Mapper
public interface SubScriptionDao {
   boolean AddSubScript(subscription ss);
   List<subscription> getSubScriptList(int uid);
 }
