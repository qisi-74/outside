package com.service.Interface;

import com.po.notify;

import java.sql.Timestamp;
import java.util.List;

public interface NotifyService {
    long createUserNoity(int uid, long nid, Timestamp time);
    long createNoity(String content, byte type, int targetid, String targetType, String action, int uid, Timestamp time);
    long createSubScript(int targetid, String targetType, String action, int uid, Timestamp time);
    List<notify> pullRemind(int uid);
    int pullNotReadReming(int uid);
    boolean read(int uid);
}
