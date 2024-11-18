package com.expense.trackig.ExpenseTracking.Controllers;
import com.expense.trackig.ExpenseTracking.Modules.User;
import com.expense.trackig.ExpenseTracking.Services.Interface.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/login")
    public String login(@ModelAttribute User u, HttpServletResponse response){
       User user = userService.getUserByEmailAndPassword(u.getEmail(),u.getPassword());
       if(user == null){
           return "redirect:/public/login";
       }
        userService.setCookie(response);
        return "redirect:/category/dashboard";
    }
    @PostMapping("/sign-in")
    public String sign_in(@RequestParam String email, @RequestParam String password, Model model) {
        if (userService.getUserByEmail(email) != null) {
            model.addAttribute("error", "Email already in use");
            return "sign_in";
        }
        User newUser = new User(email, password);
        userService.saveUser(newUser);
        return "redirect:/public/login";
    }
    @GetMapping("/logout")
    public String logout(HttpServletResponse response){
        Cookie cookie = new Cookie("expense_cookie", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        return "login";
    }
}


