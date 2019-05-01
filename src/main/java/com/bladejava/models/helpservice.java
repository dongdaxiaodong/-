package com.bladejava.models;


import io.github.biezhi.anima.Model;
import io.github.biezhi.anima.annotation.Table;

@Table(name = "helpservice")
public class helpservice extends Model{
    private int id;
    private String givestuid;
    private String getstuid;
    private String course;
    private String imgurl;
    private String launchtime;
    private String matchtime;
    private String finishtime;
    private int complete;


    public helpservice(String givestuid,String course,String launchtime){
        this.givestuid=givestuid;
        this.course=course;
        this.launchtime=launchtime;
    }
    public helpservice(String getstuid,String course,String launchtime,int id){
        this.getstuid=getstuid;
        this.course=course;
        this.launchtime=launchtime;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGivestuid() {
        return givestuid;
    }

    public void setGivestuid(String givestuid) {
        this.givestuid = givestuid;
    }

    public String getGetstuid() {
        return getstuid;
    }

    public void setGetstuid(String getstuid) {
        this.getstuid = getstuid;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getLaunchtime() {
        return launchtime;
    }

    public void setLaunchtime(String launchtime) {
        this.launchtime = launchtime;
    }

    public String getMatchtime() {
        return matchtime;
    }

    public void setMatchtime(String matchtime) {
        this.matchtime = matchtime;
    }

    public String getFinishtime() {
        return finishtime;
    }

    public void setFinishtime(String finishtime) {
        this.finishtime = finishtime;
    }

    public int getComplete() {
        return complete;
    }

    public void setComplete(int complete) {
        this.complete = complete;
    }
}
