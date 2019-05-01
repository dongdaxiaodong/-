package com.bladejava.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.blade.mvc.annotation.Path;
import com.blade.mvc.annotation.PostRoute;
import com.blade.mvc.http.Request;
import com.bladejava.models.helpservice;
import com.bladejava.models.xuezha;
import com.bladejava.utils.Match;
import com.bladejava.utils.responseStatus;
import io.github.biezhi.anima.Anima;
import com.bladejava.models.dalao;
import java.util.HashMap;
import com.bladejava.utils.getService;

@Path("dalao")
public class dalaoController {

    @com.blade.mvc.annotation.JSON
    @PostRoute
    public String dalaoLogin(Request request){
        //登录需要知道是否已经存在,所以需要判断一次，其次，service看是否complete
        JSONObject jsonParams= com.alibaba.fastjson.JSON.parseObject(request.bodyToString());
        String stuid=jsonParams.getString("stuid");
        String name=jsonParams.getString("name");
        String college=jsonParams.getString("college");
        String qqmail=jsonParams.getString("qqmail");
        String course=jsonParams.getString("course");
        if(Anima.select().from(dalao.class).where("stuid",stuid).one()==null){
            Anima.save(new dalao(stuid,college,name,qqmail));
        }
        //然后进行匹配
        helpservice helpservice=Anima.select().from(helpservice.class)
                .notEq("getstuid","0")
                .where("givestuid","0")
                .notEq("launchtime","0")
                .where("matchtime","0")
                .where("course",course)
                .where("complete","-1").one();
        if(helpservice!=null){
            //进行update
            Anima.update().from(helpservice.class)
                    .set("givestuid",stuid)
                    .set("matchtime",System.currentTimeMillis()+"")
                    .where("id",helpservice.getId()).execute();
        }
        else {
            Anima.save(new helpservice(stuid,course,System.currentTimeMillis()+""));
        }

        return JSON.toJSONString(new responseStatus<>("ok"));
     }

     @com.blade.mvc.annotation.JSON
    @PostRoute("match")
    public String dalaoMatch(Request request){
        JSONObject jsonParams= com.alibaba.fastjson.JSON.parseObject(request.bodyToString());
        String stuid=jsonParams.getString("stuid");
        helpservice usingService=getService.helpserviceByDalao(stuid);
        int status=Match.checkProcess(usingService);
        HashMap<String,Object> response=new HashMap<>();
        response.put("match",status);
        System.out.println("status is "+status);
        return JSON.toJSONString(new responseStatus<>(response));
    }

    @com.blade.mvc.annotation.JSON
    @PostRoute("getMatchInformation")
    public String dalaogetMatchInformation(Request request){
        JSONObject jsonParams= com.alibaba.fastjson.JSON.parseObject(request.bodyToString());
        String stuid=jsonParams.getString("stuid");
        helpservice usingService=getService.helpserviceByDalao(stuid);
        String xuezhastuid=usingService.getGetstuid();
        xuezha xuezha=Anima.select().from(com.bladejava.models.xuezha.class)
                .where("stuid",xuezhastuid).one();
        String matchName=xuezha.getName();
        String matchQqmail=xuezha.getQqmail();
        String matchCollege=xuezha.getCollege();
        HashMap<String,Object> response=new HashMap<>();
        response.put("matchName",matchName);
        response.put("matchQqmail",matchQqmail);
        response.put("matchCollege",matchCollege);
        response.put("matchStuid",xuezhastuid);
        return JSON.toJSONString(new responseStatus<>(response));
    }

    @com.blade.mvc.annotation.JSON
    @PostRoute("finishMeeting")
    public String dalaoFinishMeeting(Request request){
        JSONObject jsonParams= com.alibaba.fastjson.JSON.parseObject(request.bodyToString());
        String stuid=jsonParams.getString("stuid");
        System.out.println("stuid is"+stuid);
        String imgUrl=jsonParams.getString("imgUrl");
        System.out.println("imgurl is "+imgUrl);
        helpservice usingService=getService.helpserviceByDalao(stuid);
        Anima.update().from(helpservice.class)
                .set("finishtime",System.currentTimeMillis()+"")
                .set("complete",1)
                .set("imgurl",imgUrl)
                .where("id",usingService.getId()).execute();
        return JSON.toJSONString(new responseStatus<>("ok"));
    }
}
