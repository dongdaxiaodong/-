package com.bladejava.controller;


import com.blade.mvc.annotation.GetRoute;
import com.blade.mvc.annotation.JSON;
import com.blade.mvc.annotation.Path;
import com.blade.mvc.http.Request;
import com.bladejava.utils.mailSend.SendMail;
import io.github.biezhi.ome.SendMailException;

@Path
public class testController {
    @JSON
    @GetRoute("index")
    public String index(Request request){
        return "hello world";
    }
    @JSON
    @GetRoute("testEmail")
    public String testEmail(Request request)throws SendMailException{
        try {
            SendMail.sendMailToDaka("20175055@stu.neu.edu.cn","1346536639@qq.com");
            System.out.println("yes");
        }
        catch (Exception e){
            System.out.println("no");
        }
    return "ok";
    }
}
