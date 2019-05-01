package com.bladejava.utils;

import com.bladejava.models.Student;
import com.bladejava.models.meetservice;
import io.github.biezhi.anima.Anima;
import com.bladejava.models.helpservice;
public class Match{

    public static void dalaoMatchxuezha(Student dalao){
        Student matchedXuezha= Anima.select().from(Student.class).
                where("type",1).
                where("match",0).
                where("course",dalao.getCourse()).
                one();
        //接下来进行对表进行修改
        if(matchedXuezha!=null){
            Anima.update().from(Student.class).where("stuid",dalao.getStuid())
                    .set("match",1);
            Anima.update().from(Student.class).where("stuid",matchedXuezha.getStuid())
                    .set("match",1);
            Anima.update().from(helpservice.class).where("givestuid",dalao.getStuid())
                    .set("getstuid",matchedXuezha.getStuid())
                    .set("matchtime",System.currentTimeMillis()+"");
        }
    }

    public static void xuezhaMatchDalao(Student xuezha){
        Student matchDalao=Anima.select().from(Student.class)
                .where("type",0)
                .where("match",0)
                .where("course",xuezha.getCourse())
                .one();
        if(matchDalao!=null){
            Anima.update().from(Student.class).where("stuid",xuezha.getStuid())
                    .set("match",1);
            Anima.update().from(Student.class).where("stuid",matchDalao.getStuid())
                    .set("match",1);
            Anima.update().from(helpservice.class).where("givestuid",matchDalao.getStuid())
                    .set("getstuid",xuezha.getStuid())
                    .set("matchtime",System.currentTimeMillis()+"");
        }
    }

    public static int checkProcess(helpservice helpservice){
        int response=-1;
        if(helpservice.getFinishtime()!=null){
            response=2;
        }
        else if(helpservice.getMatchtime()!=null){
            response=1;
        }
        else if(helpservice.getLaunchtime()!=null){
            response=0;
        }
        return response;
    }
    public static int checkTongzuoProcess(meetservice meetservice){
        int response=-1;
        if(meetservice.getFinishtime()!=null){
            response=2;
        }
        else if(meetservice.getMatchtime()!=null){
            response=1;
        }
        else if(meetservice.getLaunchtime()!=null){
            response=0;
        }
        return response;
    }
}