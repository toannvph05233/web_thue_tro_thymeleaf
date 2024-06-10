package com.booking_house_be.controller;

import com.booking_house_be.entity.Account;
import com.booking_house_be.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@Controller
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    IAccountService accountService;

    @GetMapping()
    public String login() {
        return "home/register";
    }
    
    @PostMapping
    public String createNewAccount(@ModelAttribute Account account) {
        if (accountService.checkRegister(account) == null) {
            return "redirect:/register";
        }
        return "redirect:/login";
    }
}