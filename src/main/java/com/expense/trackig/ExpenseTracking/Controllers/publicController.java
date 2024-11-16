package com.expense.trackig.ExpenseTracking.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/public")
public class publicController {
    @GetMapping("/loginForm")
    public String loginForm(){
        return "login";
    }

    @GetMapping("/signInForm")
    public String signInForm(){
        return "sign_in";
    }
}