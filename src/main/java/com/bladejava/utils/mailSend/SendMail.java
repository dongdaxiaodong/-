package com.bladejava.utils.mailSend;

import io.github.biezhi.ome.OhMyEmail;
import io.github.biezhi.ome.SendMailException;

public class SendMail {
    private static  final String text1="你参与的东北大学志愿者协会同桌的你活动已经为你匹配到";
    private static final String textDaka="需要你提供辅导帮助的同学了,该同学的邮箱地址为";
    private static final String text2=",请与TA联系";
    private static final String textXueZha="可以给你提供帮助的同学了,该同学的邮箱地址为";
    private static final String textTongZuo="'你的同桌'了，同桌的邮箱地址为";
    public static void sendMailToDaka(String dakaIp,String userIp)throws SendMailException{
        String text=text1+textDaka+userIp+text2;
        sendMail(text,dakaIp);
    }
    public static void sendMailToUser(String userIp,String dakaIp)throws SendMailException{
        String text=text1+textXueZha+dakaIp+text2;
        sendMail(text,userIp);
    }
    public static void sendMailToTongZuo(String me,String ta)throws  SendMailException{
        String text=text1+textTongZuo+ta+text2;
        sendMail(text,me);
    }
    private static void sendMail(String text,String emailIp)throws SendMailException{
        OhMyEmail.subject("东北大学志愿者协会同桌的你活动")
                .text(text)
                .from("ColinZhang")
                .to(emailIp)
                .send();
    }
}
