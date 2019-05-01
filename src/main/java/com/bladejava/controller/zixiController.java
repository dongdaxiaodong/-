package com.bladejava.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.blade.mvc.annotation.Path;
import com.blade.mvc.annotation.PostRoute;
import com.blade.mvc.http.Request;
import com.bladejava.models.meetservice;
import com.bladejava.utils.getService;

import com.bladejava.utils.Match;
import com.bladejava.utils.responseStatus;
import io.github.biezhi.anima.Anima;
import com.bladejava.models.zixitongzuo;

import java.util.HashMap;

@Path("zixi")
public class zixiController {


    @com.blade.mvc.annotation.JSON
    @PostRoute
    public String zixiLogin(Request request){
        JSONObject jsonParams= com.alibaba.fastjson.JSON.parseObject(request.bodyToString());
        String stuid=jsonParams.getString("stuid");
        String college=jsonParams.getString("college");
        String qqmail=jsonParams.getString("qqmail");
        String name=jsonParams.getString("name");
        String time=jsonParams.getString("time");
        System.out.println("name is "+name);
        System.out.println("stuid is"+stuid);
        System.out.println(Anima.select().from(zixitongzuo.class).where("stuid",stuid).one()==null);
        if(Anima.select().from(zixitongzuo.class).where("stuid",stuid).one()==null){
            Anima.save(new zixitongzuo(stuid,name,college,qqmail));
        }
        meetservice meetservice=Anima.select().from(com.bladejava.models.meetservice.class)
                .notEq("stuid1","0")
                .where("stuid2","0")
                .notEq("launchtime","0")
                .where("matchtime","0")
                .where("meettime",time)
                .where("complete","-1").one();
        if(meetservice!=null){
            //进行update
            Anima.update().from(meetservice.class)
                    .set("stuid2",stuid)
                    .set("matchtime",System.currentTimeMillis()+"")
                    .where("id",meetservice.getId()).execute();
        }
        else {
            Anima.save(new meetservice(stuid,time,System.currentTimeMillis()+""));
        }

        return JSON.toJSONString(new responseStatus<>("ok"));
    }


    @com.blade.mvc.annotation.JSON
    @PostRoute("match")
    public String zixiMatch(Request request){
        meetservice usingService= getService.meetserviceGet(request);
        int status=Match.checkTongzuoProcess(usingService);
        HashMap<String,Object> response=new HashMap<>();
        response.put("match",status);
        System.out.println(status);
        return JSON.toJSONString(new responseStatus<>(response));
    }

    @com.blade.mvc.annotation.JSON
    @PostRoute("getMatchInformation")
    public String zixigetMatchInformation(Request request){
        meetservice usingService=getService.meetserviceGet(request);
        JSONObject jsonParams= com.alibaba.fastjson.JSON.parseObject(request.bodyToString());
        String stuid=jsonParams.getString("stuid");
        String stuid1=usingService.getStuid1();
        String stuid2=usingService.getStuid2();
        String matchName;
        String matchQqmail;
        String matchCollege;
        String matchStuid;
        if(stuid1.equals(stuid)){
            String matchstuid=usingService.getStuid2();
            zixitongzuo zixitongzuo=Anima.select().from(com.bladejava.models.zixitongzuo.class)
                    .where("stuid",matchstuid).one();
            matchName=zixitongzuo.getName();
            matchQqmail=zixitongzuo.getQqmail();
            matchCollege=zixitongzuo.getCollege();
            matchStuid=matchstuid;
        }
        else {
            String matchstuid=usingService.getStuid1();
            zixitongzuo zixitongzuo=Anima.select().from(com.bladejava.models.zixitongzuo.class)
                    .where("stuid",matchstuid).one();
            matchName=zixitongzuo.getName();
            matchQqmail=zixitongzuo.getQqmail();
            matchCollege=zixitongzuo.getCollege();
            matchStuid=matchstuid;
        }
        HashMap<String,Object> response=new HashMap<>();
        response.put("matchName",matchName);
        response.put("matchQqmail",matchQqmail);
        response.put("matchCollege",matchCollege);
        response.put("matchStuid",matchStuid);
        return JSON.toJSONString(new responseStatus<>(response));
    }


    @com.blade.mvc.annotation.JSON
    @PostRoute("finishMeeting")
    public String zixifinishMeeting(Request request){
        JSONObject jsonParams= com.alibaba.fastjson.JSON.parseObject(request.bodyToString());
        String stuid=jsonParams.getString("stuid");
        meetservice meetservice=getService.meetserviceGet(request);
        Anima.update().from(com.bladejava.models.meetservice.class)
                .set("finishtime",System.currentTimeMillis()+"")
                .set("complete",1)
                .where("id",meetservice.getId()).execute();
        return JSON.toJSONString(new responseStatus<>("ok"));
    }
}
