package com.example.calculatorspring.Controller;

import com.example.calculatorspring.Model.LoginRequest;
import com.example.calculatorspring.Model.PostObject;
import com.example.calculatorspring.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@CrossOrigin("http://10.22.10.71:8000/")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/Createuser")
    public boolean createUser(@RequestBody LoginRequest user){
        String username = user.getUsername();
        String password = user.getPassword();
        return userService.createUser(username,password);
    }

    @GetMapping("users")
    public LoginRequest getUser(@RequestParam String username, @RequestParam String password){
        LoginRequest user = new LoginRequest();
        if(userService.getUser(username,password)){
            user.setUsername(username);
            user.setPassword(password);
            return user;
        }
        return null;
    }
    @PostMapping ("/CalculateAndSave")
    public String calculateAndSave(@RequestBody PostObject postObject){
        return userService.calculate(postObject);
    }

    @PostMapping("/GetLog")
    public String getLog(@RequestBody PostObject postObject){
        return userService.getLog();
    }


    @PostMapping("/GetCalculations")
    public List<String> getCalculations(@RequestBody PostObject postObject){
        String username = postObject.getUserName();
        return userService.getEquations(username);
    }
}