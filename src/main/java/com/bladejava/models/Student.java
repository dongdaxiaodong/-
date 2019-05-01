package com.bladejava.models;

import io.github.biezhi.anima.Model;
import io.github.biezhi.anima.annotation.Table;

@Table(name = "student")
public class Student extends Model{
    private int id;
    private String stuid;
    private String college;
    private String qqmail;
    private int type;
    private String name;
    private int match;//表示还在活动中
    private String course;
    public Student(){

    }
    public Student(String stuid,String college,String qqmail,String name,int type,int match,String course){
        this.stuid=stuid;
        this.college=college;
        this.qqmail=qqmail;
        this.name=name;
        this.type=type;
        this.match=match;
        this.course=course;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public int getId() {
        return id;
    }


    public int getMatch() {
        return match;
    }

    public void setMatch(int running) {
        this.match = running;
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

    public String getQqmail() {
        return qqmail;
    }

    public void setQqmail(String qqmail) {
        this.qqmail = qqmail;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
