package com.bladejava.models;

import io.github.biezhi.anima.Model;
import io.github.biezhi.anima.annotation.Table;


@Table(name = "xuezha")
public class xuezha extends Model{
    private int id;
    private String stuid;
    private String name;
    private String college;
    private String qqmail;


    public xuezha(){

    }

    public xuezha(String stuid, String name, String college, String qqmail) {
        this.stuid = stuid;
        this.name = name;
        this.college = college;
        this.qqmail = qqmail;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
