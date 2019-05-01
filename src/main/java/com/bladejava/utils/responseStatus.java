package com.bladejava.utils;

import javax.swing.plaf.PanelUI;

public class responseStatus<T> {
    private int status;
    private T data;
    private final static int goodStatus=200;
    private final static int badStatus=500;
    public responseStatus(T data){
        this.status=goodStatus;
        this.data=data;
    }
    public void badResponse(){
        this.status=badStatus;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static int getGoodStatus() {
        return goodStatus;
    }

    public static int getBadStatus() {
        return badStatus;
    }
}
