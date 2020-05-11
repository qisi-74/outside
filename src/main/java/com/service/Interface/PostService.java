package com.service.Interface;


import com.po.post;

import java.util.List;

public interface PostService {
    boolean post_publish(String pcontext, int uid, String birth);
    boolean post_publish_V(String pcontext, int vid, int uid, String birth);
    int getLast(int uid);
    List<post> getAll(int uid);
    List<post> getAll(List<Integer> feed);
    int getvid(int pid);
    int getuid(int pid);
    int getpid(int vid);
    post getpost(int pid);
    boolean block(int pid);
}
