package com.userManagement.usermanagementservices.controller;

import com.userManagement.usermanagementservices.entity.ResponseMessage;
import com.userManagement.usermanagementservices.entity.Users;
import com.userManagement.usermanagementservices.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/signup")
    public Users userSignup(@Valid @RequestBody Users user){
        return userService.userSignup(user);
    }

    @GetMapping("/user/")
    public List<Users> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/user/get/{phoneNo}")
    public Users getUser(@PathVariable("phoneNo") Long phoneNo){
        return userService.getSpecificUser(phoneNo);
    }

    @PostMapping("/user/login")
    public ResponseMessage userLogin(@RequestBody Users user){
        return userService.userLogin(user.getPhoneNo(), user.getPassword());
    }

    @PostMapping("/user/update/{id}")
    public ResponseMessage userUpdate(@PathVariable("id") Long id, @RequestBody Users user){
        return userService.userUpdate(user, id);
    }

    @PostMapping("/user/delete/{id}")
    public ResponseMessage userDelete(@PathVariable("id") Long id){
        return userService.userDelete(id);
    }

}
