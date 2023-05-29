package com.example.repit.controllers;

import com.example.repit.entities.Authentication;
import com.example.repit.security.AuthDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(){
        return "login";
    }


    @PostMapping("/login")
    public String stud(){
        return "studProfile";
    }

}
