package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*
Create By: Ron Rith
Create Date: 11/29/2017
*/
@Controller
public class PublicController {
    @RequestMapping(name = "/public",method = RequestMethod.GET)
    public String getPublic(){
        return "public";
    }
}
