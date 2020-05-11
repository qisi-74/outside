package com.dao;

import com.po.notify;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Repository
@Mapper
public interface NotifyDao {
   boolean AddNotify(notify n);
   long selectLast(int uid);
   notify getNotify(long id);

}
