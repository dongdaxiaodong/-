package com.bladejava.models;

import io.github.biezhi.anima.Model;

public class dalao extends Model{
    private int id;
    private String stuid;
    private String college;
    private String name;
    private String qqmail;

    public dalao(String stuid,String college,String name,String qqmail){
        this.stuid=stuid;
        this.college=college;
        this.name=name;
        this.qqmail=qqmail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStuid() {
        return stuid;
    }

    public void setStuid(String stuid) {
        this.stuid = stuid;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQqmail() {
        return qqmail;
    }

    public void setQqmail(String qqmail) {
        this.qqmail = qqmail;
    }
}
