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
import com.bladejava.utils.responseStatus;
import io.github.biezhi.anima.Anima;
import com.bladejava.models.xuezha;
import java.util.HashMap;

@Path("xuezha")
public class xuezhaController {

    @PostRoute
    public String xuezhaLogin(Request request){
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
                .notNull("givestuid")
                .where("getstuid",null)
                .notNull("launchtime")
                .where("matchtime",null)
                .where("course",course)
                .where("complete",null).one();
        if(helpservice!=null){
            Anima.update().from(com.bladejava.models.helpservice.class).byId(helpservice.getId())
                    .set("getstuid",stuid)
                    .set("matchtime",System.currentTimeMillis()+"");
        }
        else {
            Anima.save(new helpservice(stuid,course,System.currentTimeMillis()+"",2));
        }

        return JSON.toJSONString(new responseStatus<>("ok"));
    }

    @PostRoute("match")
    public String xuezhaMatch(Request request){
        helpservice usingService= getService.helpserviceByXuezha(request);
        int status=Match.checkProcess(usingService);
        HashMap<String,Object> response=new HashMap<>();
        response.put("match",status);
        return JSON.toJSONString(new responseStatus<>(response));
    }

    @PostRoute("getMatchInformation")
    public String xuezhaGetMatchInformation(Request request){
        helpservice usingService=getService.helpserviceByXuezha(request);
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

    @PostRoute("finishMeeting")
    public String xuezhafinishMeeting(Request request){
        helpservice usingService=getService.helpserviceByXuezha(request);
        Anima.update().from(helpservice.class).byId(usingService.getId())
                .set("finishtime",System.currentTimeMillis()+"")
                .set("complete",1);
        return JSON.toJSONString(new responseStatus<>("ok"));
    }
}
