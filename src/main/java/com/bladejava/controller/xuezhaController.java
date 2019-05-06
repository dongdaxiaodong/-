package com.bladejava.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.blade.mvc.annotation.Path;
import com.blade.mvc.annotation.PostRoute;
import com.blade.mvc.http.Request;
import com.bladejava.models.dalao;
import com.bladejava.models.helpservice;
import com.bladejava.utils.Match;
import com.bladejava.utils.getService;
import com.bladejava.utils.mailSend.SendMail;
import com.bladejava.utils.responseStatus;
import io.github.biezhi.anima.Anima;
import com.bladejava.models.xuezha;
import io.github.biezhi.ome.SendMailException;

import java.util.HashMap;

@Path("xuezha")
public class xuezhaController {


    @com.blade.mvc.annotation.JSON
    @PostRoute
    public String xuezhaLogin(Request request)throws SendMailException{
        JSONObject jsonParams= com.alibaba.fastjson.JSON.parseObject(request.bodyToString());
        String stuid=jsonParams.getString("stuid");
        String college=jsonParams.getString("college");
        String qqmail=jsonParams.getString("qqmail");
        String name=jsonParams.getString("name");
        String course=jsonParams.getString("course");
        if(Anima.select().from(xuezha.class).where("stuid",stuid).one()==null){
            Anima.save(new xuezha(stuid,name,college,qqmail));
        }
        //然后进行匹配
        helpservice helpservice=Anima.select().from(helpservice.class)
                .notEq("givestuid","0")
                .where("getstuid","0")
                .notEq("launchtime","0")
                .where("matchtime","0")
                .where("course",course)
                .where("complete","-1").one();
        if(helpservice!=null){
            Anima.update().from(helpservice.class)
                    .set("getstuid",stuid)
                    .set("matchtime",System.currentTimeMillis()+"")
                    .where("id", helpservice.getId()).execute();
            dalao dalao=Anima.select().from(com.bladejava.models.dalao.class)
                    .where("stuid",helpservice.getGivestuid()).one();
            SendMail.sendMailToDaka(dalao.getQqmail(),qqmail);
            SendMail.sendMailToUser(qqmail,dalao.getQqmail());
        }
        else {
            Anima.save(new helpservice(stuid,course,System.currentTimeMillis()+"",2));
        }

        return JSON.toJSONString(new responseStatus<>("ok"));
    }


    @com.blade.mvc.annotation.JSON
    @PostRoute("match")
    public String xuezhaMatch(Request request){
        JSONObject jsonParams= com.alibaba.fastjson.JSON.parseObject(request.bodyToString());
        String stuid=jsonParams.getString("stuid");
        helpservice usingService= getService.helpserviceByXuezha(stuid);
        int status=Match.checkProcess(usingService);
        HashMap<String,Object> response=new HashMap<>();
        response.put("match",status);
        return JSON.toJSONString(new responseStatus<>(response));
    }

    @com.blade.mvc.annotation.JSON
    @PostRoute("getMatchInformation")
    public String xuezhaGetMatchInformation(Request request){
        JSONObject jsonParams= com.alibaba.fastjson.JSON.parseObject(request.bodyToString());
        String stuid=jsonParams.getString("stuid");
        helpservice usingService=getService.helpserviceByXuezha(stuid);
        String dalaostuid=usingService.getGivestuid();
        dalao dalao=Anima.select().from(com.bladejava.models.dalao.class).where("stuid",dalaostuid).one();
        String matchName=dalao.getName();
        String matchQqmail=dalao.getQqmail();
        String matchCollege=dalao.getCollege();
        HashMap<String,Object> response=new HashMap<>();
        response.put("matchName",matchName);
        response.put("matchQqmail",matchQqmail);
        response.put("matchCollege",matchCollege);
        response.put("matchStuid",dalaostuid);
        return JSON.toJSONString(new responseStatus<>(response));
    }


    @com.blade.mvc.annotation.JSON
    @PostRoute("finishMeeting")
    public String xuezhafinishMeeting(Request request){
        JSONObject jsonParams= com.alibaba.fastjson.JSON.parseObject(request.bodyToString());
        String stuid=jsonParams.getString("stuid");
        helpservice usingService=getService.helpserviceByXuezha(stuid);
        Anima.update().from(helpservice.class)
                .set("finishtime",System.currentTimeMillis()+"")
                .set("complete",1)
                .where("id",usingService.getId()).execute();
        return JSON.toJSONString(new responseStatus<>("ok"));
    }
}
