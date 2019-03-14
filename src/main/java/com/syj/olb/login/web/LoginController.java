package com.syj.olb.login.web;

import com.syj.olb.login.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;


@Controller
@RequestMapping("/login")
public class LoginController {
    @RequestMapping("/login")
    public String login(){
        return "jsps/user/login";
    }
    @RequestMapping("/validateLogin")
    public String validateLogin(){
        return "jsps/user/login";
    }
    /*@RequestMapping("/login1")
    @ResponseBody
    public Person login1(String username,String password){
        System.out.println("input username is "+username);
        System.out.println("input password is "+password);
        Person person = new Person();
        person.setName("11");
        person.setHeight("22");
        person.setWeight("33");
        return person;
    }*/
   /* @RequestMapping("/")
    public String login2(String username,String password){
        System.out.println("input username is "+username);
        System.out.println("input password is "+password);
        return "index";
    }*/
}
