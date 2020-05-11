package com.service.Interface;


import java.util.List;

public interface PostImageService {
    boolean addPIPath(int pid, List path);
    List<String> selectPostImg(int pid);
}
