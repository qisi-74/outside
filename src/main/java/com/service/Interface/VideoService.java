package com.service.Interface;



import com.po.video;

import java.util.List;

public interface VideoService {
    boolean upload(String vname, String vface, String vcut_pic, String vaddress, String info, String vtime, int uid, String videokind_id);
    video videoplay(int vid);
    int getLast(int uid);
    List<video> getVidList(int uid);
    List<video> getVtextList(int uid, String text);
    List<video> SearchList(String text);
    boolean block(int vid);
}
