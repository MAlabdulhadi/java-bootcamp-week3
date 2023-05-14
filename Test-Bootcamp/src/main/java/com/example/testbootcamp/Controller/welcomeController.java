package com.example.testbootcamp.Controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
//@RequestMapping("/home")
public class welcomeController {


    @GetMapping(path = "/name")
    public String getName(){
        return "My name is Mohammed";
    }
    @GetMapping(path ="/age")
    public String getAge(){
        return "My age is 23";
    }

    @GetMapping(path = "/check/status")
    public String getStatus(){
        return "Everything OK";
    }
    @GetMapping(path = "/health")
    public String getHealth(){
        return "Server health is up and running";
    }
    @GetMapping(path = "/info")
    public String[] info(){
        return new String[]{"mohammed", "saad"};
    }
    @GetMapping(path = "/info/user")
    public ArrayList<String> infoAboutUser(){
        ArrayList arrayInfo = new ArrayList<>();
        arrayInfo.add("mohammed");
        arrayInfo.add("23");
        return  arrayInfo;
    }


}
