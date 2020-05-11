package com.service.app.upload;

/**
 * Created by IntelliJ IDEA.
 *
 * @ProjectName: graduation
 * @ClassName: PJProgressListener
 * @Description: java类作用描述
 * @CreateUser: Mr.Hao
 * @CreateDate: 2020/3/21  10:12
 **/

import org.apache.commons.fileupload.ProgressListener;
import com.po.ProgressEntity;

import javax.servlet.http.HttpSession;

public class PJProgressListener implements ProgressListener {
    private HttpSession session;
    public PJProgressListener() {
    }
    public PJProgressListener(HttpSession _session) {
        session=_session;
        ProgressEntity ps = new ProgressEntity();
        session.setAttribute("upload_ps", ps);
    }
    public void update(long pBytesRead, long pContentLength, int pItems) {
        ProgressEntity ps = (ProgressEntity) session.getAttribute("upload_ps");
        ps.setpBytesRead(pBytesRead);
        ps.setpContentLength(pContentLength);
        ps.setpItems(pItems);
        ps.setUseTime(System.currentTimeMillis()-ps.getStartTime());
        ps.setPrecent();
    }

}