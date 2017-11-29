package com.example.controller;

import com.example.model.Role;
import com.example.model.User;
import com.example.repository.RoleRepository;
import com.example.repository.UserRepository;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
Create By: Ron Rith
Create Date: 11/29/2017
*/
@Controller
public class UserRoleController {
    private UserRepository userRepository;
    public UserRoleController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    private RoleRepository roleRepository;
    public UserRoleController(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    public UserRoleController() {
    }

    @RequestMapping(value = "/login2")
    public String getLogin(){
        return "login/login2";
    }

    @RequestMapping(value = "/users2")
    public String index(Model model){
        List<User> users = (List<User>) userRepository.findAll();
        model.addAttribute("users2",users);
        return "users2";
    }

    @RequestMapping(value = "add2")
    public String addUser(Model model){
        model.addAttribute("user2",new User());
        return "addUser2";
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(User user) {
        userRepository.save(user);
        return "redirect:/users2";
    }

    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public String editRemoveUser(@PathVariable("id") Long id, Model model){
        userRepository.delete(id);
        return "redirect:/users2";
    }

    @RequestMapping(value = "addUserRole/{id}",method = RequestMethod.GET)
    public String addRole(@PathVariable("id") Long userId,Model model){
        model.addAttribute("roles",roleRepository.findAll());
        model.addAttribute("user",userRepository.findOne(userId));
        return "addUserRole2";
    }

    @RequestMapping(value = "/user/{id}/roles",method = RequestMethod.GET)
    public String usersAddRole(@PathVariable("id") Long id, @RequestParam Long roleId,Model model){
        Role role = roleRepository.findOne(roleId);
        User user = userRepository.findOne(id);

        if (user != null) {
            if(!user.hasRole(role)){
                user.getRoles().add(role);
            }
            userRepository.save(user);
            model.addAttribute("user",roleRepository.findOne(id));
            model.addAttribute("roles",roleRepository.findAll());
            return "redirect:/users2";
        }
        return "redirect:/users2";
    }

    @RequestMapping(value = "/getusers2",method = RequestMethod.GET)
    public @ResponseBody List<User> getUsers(){
        return (List<User>) userRepository.findAll();
    }

}
