package com.bladejava.utils;

import com.bladejava.models.meetservice;
import com.bladejava.models.helpservice;
public class Match{
    public static int checkProcess(helpservice helpservice){
        int response=-1;
        if(!helpservice.getFinishtime().equals("0")){
            response=2;
        }
        else if(!helpservice.getMatchtime().equals("0")){
            response=1;
        }
        else if(!helpservice.getLaunchtime().equals("0")){
            response=0;
        }
        return response;
    }
    public static int checkTongzuoProcess(meetservice meetservice){
        int response=-1;
        if(!meetservice.getFinishtime().equals("0")){
            response=2;
        }
        else if(!meetservice.getMatchtime().equals("0")){
            response=1;
        }
        else if(!meetservice.getLaunchtime().equals("0")){
            response=0;
        }
        return response;
    }
}