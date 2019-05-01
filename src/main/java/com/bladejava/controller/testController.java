package com.bladejava.controller;


import com.blade.mvc.annotation.GetRoute;
import com.blade.mvc.annotation.JSON;
import com.blade.mvc.annotation.Path;
import com.blade.mvc.annotation.PostRoute;
import com.blade.mvc.http.Request;
import com.blade.mvc.ui.RestResponse;

@Path
public class testController {


    @JSON
    @GetRoute("index")
    public String index(){
        return "hello world!";
    }

    @PostRoute("/dalao")
    public String dalao(Request request){
        return "";
    }
}
