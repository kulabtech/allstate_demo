package com.registration.registration.controller;


import com.registration.registration.entity.AllStateUser;
import com.registration.registration.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/user")
public class registrationController {

    @Autowired
    private UserService service;
    
    /*@CrossOrigin(origins = "http://localhost:3000") */
    @PostMapping("/register")
    public AllStateUser registration(@RequestBody AllStateUser user) throws Exception {
        String tempEmailId = user.getEmailId();
        if (tempEmailId != null &&  !"".equals(tempEmailId)){

            AllStateUser userObj=service.fetchUserByEmailId(tempEmailId);
            if (userObj != null){
                throw new Exception("User already exist with the same emailId");
            }

        }

        AllStateUser registeredEmployee = service.saveEmployee(user);
        return registeredEmployee;



    }
    
   /* @CrossOrigin(origins = "http://localhost:3000")*/
    @GetMapping("/userbyemailid")
    public AllStateUser getUser(@RequestParam String emailId){
        return  service.fetchUserByEmailId(emailId);
    }
    
   /* @CrossOrigin(origins = "http://localhost:3000")*/
    @GetMapping("/userslist")
    public List<AllStateUser> getAllUser(){
        return service.getAllUser();
    }
}
