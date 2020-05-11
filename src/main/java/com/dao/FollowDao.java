package com.dao;

import com.po.follow;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
@Mapper
public interface FollowDao {
   follow queryRelation(follow f);
   List<Integer> queryFollower(int following);
   List<Integer> queryFollowing(int follower);
   List<Integer> queryFanFollower(int following);
   List<Integer> queryFanFollowing(int follower);
   boolean follow(follow f);
   boolean noFollow(follow f);
   boolean updateFollow(follow f);
}
