package com.expense.trackig.ExpenseTracking.Controllers;

import com.expense.trackig.ExpenseTracking.Modules.Budget;
import com.expense.trackig.ExpenseTracking.Services.Implementation.BudgetServiceImpl;

import com.expense.trackig.ExpenseTracking.Services.Interface.BudgetService;
import com.expense.trackig.ExpenseTracking.Services.Interface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/budget")
public class BudgetController {
    @Autowired
    private BudgetService budgetService;
    @Autowired
    private UserService userService;

    @GetMapping("/add-budget")
    public String showBudgetForm(@CookieValue(value = "expense_cookie", defaultValue = "null") String cookie) {
        if(!userService.isLoggedIn(cookie))
            return "redirect:/public/login";
        return "add-budget";
    }

    @PostMapping("/add-budget")
    public String saveBudget(@CookieValue(value = "expense_cookie", defaultValue = "null") String cookie,@ModelAttribute Budget budget) {
        if(!userService.isLoggedIn(cookie))
            return "redirect:/public/login";
        try{
            budgetService.saveBudget(budget);
        }catch (Exception e){
            return "redirect:/budget/add-budget";
        }
        return "dashboard";
    }
}
