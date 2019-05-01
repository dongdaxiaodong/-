package com.bladejava.models;
import io.github.biezhi.anima.Model;
import io.github.biezhi.anima.annotation.Column;
import io.github.biezhi.anima.annotation.Table;
@Table(name = "meetservice")
public class meetservice extends Model{
    private int id;
    @Column(name = "stuid1")
    private String stuid1;
    @Column(name = "stuid2")
    private String stuid2;
    private String meettime;//自习日期
    private String launchtime;
    private String matchtime;
    private String finishtime;
    private String  complete;
    private String imgurl;

    public meetservice(){

    }

    public meetservice(String stuid1, String meettime, String launchtime) {
        this.stuid1 = stuid1;
        this.meettime = meettime;
        this.launchtime = launchtime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStuid1() {
        return stuid1;
    }

    public void setStuid1(String stuid1) {
        this.stuid1 = stuid1;
    }

    public String getStuid2() {
        return stuid2;
    }

    public void setStuid2(String stuid2) {
        this.stuid2 = stuid2;
    }

    public String getMeettime() {
        return meettime;
    }

    public void setMeettime(String meettime) {
        this.meettime = meettime;
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

    public String  getComplete() {
        return complete;
    }

    public void setComplete(String complete) {
        this.complete = complete;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }
}
