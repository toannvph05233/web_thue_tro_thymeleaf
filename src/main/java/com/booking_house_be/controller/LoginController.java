package com.booking_house_be.controller;

import com.booking_house_be.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    IAccountService accountService;

    @GetMapping()
    public String login() {
        return "home/login";
    }

    @GetMapping("/check-username")
    public boolean checkUsername(@RequestParam("username") String username) {
        return accountService.getAccountByUsername(username) != null;
    }

    @GetMapping("/check-email")
    public boolean checkEmail(@RequestParam("email") String email) {
        return accountService.getAccountByEmail(email) != null;
    }
}
