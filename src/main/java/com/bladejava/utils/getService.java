package com.bladejava.utils;

import com.alibaba.fastjson.JSONObject;
import com.blade.mvc.http.Request;
import com.bladejava.models.helpservice;
import com.bladejava.models.meetservice;
import io.github.biezhi.anima.Anima;
import java.util.List;

public class getService {
    public static helpservice helpserviceByDalao(Request request){
        JSONObject jsonParams= com.alibaba.fastjson.JSON.parseObject(request.bodyToString());
        String stuid=jsonParams.getString("stuid");
        List<helpservice> helpservices= Anima.select().from(helpservice.class)
                .where("givestuid",stuid)
                .all();
        helpservice usingService=null;
        if(helpservices.size()==1){
            usingService=helpservices.get(0);
        }
        else {
            for(helpservice helpservice:helpservices){
                if(helpservice.getComplete() !=0 && helpservice.getComplete()!=1){
                    usingService=helpservice;
                    break;
                }
            }
        }
        return usingService;
    }

    public static helpservice helpserviceByXuezha(Request request){
        JSONObject jsonParams= com.alibaba.fastjson.JSON.parseObject(request.bodyToString());
        String stuid=jsonParams.getString("stuid");
        List<helpservice> helpservices= Anima.select().from(helpservice.class)
                .where("getstuid",stuid)
                .all();
        helpservice usingService=null;
        if(helpservices.size()==1){
            usingService=helpservices.get(0);
        }
        else {
            for(helpservice helpservice:helpservices){
                if(helpservice.getComplete() !=0 && helpservice.getComplete()!=1){
                    usingService=helpservice;
                    break;
                }
            }
        }
        return usingService;
    }

    public static meetservice meetserviceGet(Request request){
        JSONObject jsonParams= com.alibaba.fastjson.JSON.parseObject(request.bodyToString());
        String stuid=jsonParams.getString("stuid");
        List<meetservice> meetservices1=Anima.select().from(meetservice.class)
                .where("stuid1",stuid)
                .all();
        List<meetservice> meetservices2=Anima.select().from(meetservice.class)
                .where("stuid2",stuid)
                .all();
        for(meetservice meetservice:meetservices2){
            meetservices1.add(meetservice);
        }
        meetservice usingService=null;
        if(meetservices1.size()==0){
            usingService=meetservices1.get(0);
        }
        else {
            for(meetservice meetservice:meetservices1){
                if(meetservice.getComplete()!=0 &&meetservice.getComplete()!=1){
                    usingService=meetservice;
                    break;
                }
            }
        }
        return usingService;
    }
}
