package com.dao;

import com.po.usernotify;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
@Mapper
public interface UserNotifyDao {
   boolean AddUserNotify(usernotify un);
   List<Long> getAllNotify(int uid);
   List<Long> getNotReadNotify(int uid);
   boolean read(int uid);
}
