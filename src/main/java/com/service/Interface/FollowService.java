package com.service.Interface;

import java.util.List;

public interface FollowService {
    String queryRelation(int following, int follower);
    List<Integer> queryFollower(int following);
    List<Integer> queryFollowing(int follower);
    boolean follow(int following, int follower);
    boolean removeFollow(int following, int follower);

}
