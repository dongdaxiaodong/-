package com.bladejava.controller;


import com.blade.mvc.annotation.GetRoute;
import com.blade.mvc.annotation.JSON;
import com.blade.mvc.annotation.Path;
import com.blade.mvc.http.Request;

@Path
public class testController {
    @JSON
    @GetRoute("index")
    public String index(Request request){
        return "hello world";
    }
}
