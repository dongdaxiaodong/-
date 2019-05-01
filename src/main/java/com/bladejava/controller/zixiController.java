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

    @PostRoute
    public String zixiLogin(Request request){
        JSONObject jsonParams= com.alibaba.fastjson.JSON.parseObject(request.bodyToString());
        String stuid=jsonParams.getString("stuid");
        String college=jsonParams.getString("college");
        String qqmail=jsonParams.getString("qqmail");
        String name=jsonParams.getString("name");
        String time=jsonParams.getString("time");
        if(Anima.select().from(zixitongzuo.class).where("stuid",stuid).one()==null){
            Anima.save(new zixitongzuo(stuid,name,college,qqmail));
        }
        meetservice meetservice=Anima.select().from(com.bladejava.models.meetservice.class)
                .notNull("stuid1")
                .where("stuid2",null)
                .notNull("launchtime")
                .where("matchtime",null)
                .where("time",time)
                .where("complete",null).one();
        if(meetservice!=null){
            //进行update
            Anima.update().from(com.bladejava.models.meetservice.class).byId(meetservice.getId())
                    .set("stuid2",stuid)
                    .set("matchtime",System.currentTimeMillis()+"");
        }
        else {
            Anima.save(new meetservice(stuid,time,System.currentTimeMillis()+""));
        }

        return JSON.toJSONString(new responseStatus<>("ok"));
    }

    @PostRoute("match")
    public String zixiMatch(Request request){
        meetservice usingService= getService.meetserviceGet(request);
        int status=Match.checkTongzuoProcess(usingService);
        HashMap<String,Object> response=new HashMap<>();
        response.put("match",status);
        return JSON.toJSONString(new responseStatus<>(response));
    }

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

    @PostRoute("finishMeeting")
    public String zixifinishMeeting(Request request){
        JSONObject jsonParams= com.alibaba.fastjson.JSON.parseObject(request.bodyToString());
        String stuid=jsonParams.getString("stuid");
        meetservice meetservice=getService.meetserviceGet(request);
        Anima.update().from(com.bladejava.models.meetservice.class).byId(meetservice.getId())
                .set("finishtime",System.currentTimeMillis()+"")
                .set("complete",1);
        return JSON.toJSONString(new responseStatus<>("ok"));
    }
}
