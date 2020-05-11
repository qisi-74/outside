package com.service.app.email;

/**
 * Created by IntelliJ IDEA.
 *
 * @ProjectName: graduation
 * @ClassName: EmailUtil
 * @Description: java类作用描述
 * @CreateUser: Mr.Hao
 * @CreateDate: 2020/3/16  15:00
 **/

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class EmailUtil_netease {

    public static boolean sendMail(String email, String emailMsg) throws UnsupportedEncodingException {
        String from = "OutSide_Offical@163.com";                 // 邮件发送人的邮件地址
        String to = email;                                         // 邮件接收人的邮件地址
        final String username = "OutSide_Offical@163.com";      //发件人的邮件帐户
        final String password = "joker810215";                       //发件人的邮件授权码

        //定义Properties对象,设置环境信息
        Properties props = System.getProperties();

        //设置邮件服务器的地址
        props.setProperty("mail.smtp.host", "smtp.163.com"); // 指定的smtp服务器
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.transport.protocol", "smtp");//设置发送邮件使用的协议
        //创建Session对象,session对象表示整个邮件的环境信息
        Session session = Session.getInstance(props);
        //设置输出调试信息
        session.setDebug(true);
        try {
            //Message的实例对象表示一封电子邮件
            MimeMessage message = new MimeMessage(session);
            //设置发件人的地址
            message.setFrom(new InternetAddress(from));
            //设置主题
            message.setSubject("OUTSIDE验证");
            //设置邮件的文本内容
            message.setContent((emailMsg),"text/html;charset=utf-8");
            message.setRecipient(Message.RecipientType.TO,new InternetAddress(from, "OUTSIDE","UTF-8"));
            message.addRecipients(Message.RecipientType.CC,to);
            //从session的环境中获取发送邮件的对象
            Transport transport=session.getTransport();
            //连接邮件服务器
            transport.connect("smtp.163.com",25, username, password);
            //设置收件人地址,并发送消息
            transport.sendMessage(message,message.getAllRecipients());
            transport.close();
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }
}