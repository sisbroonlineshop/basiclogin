package com.example.controller;

import com.example.model.User;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
Create By: Ron Rith
Create Date: 11/29/2017
*/
@RestController
public class UserRestController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/jsonusers",method = RequestMethod.GET)
    public List<User> users(){
        return (List<User>) userRepository.findAll();
    }
}
