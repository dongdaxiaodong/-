package com.bladejava.utils;

import com.bladejava.models.meetservice;
import com.bladejava.models.helpservice;
public class Match{
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