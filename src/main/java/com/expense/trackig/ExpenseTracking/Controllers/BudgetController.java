package com.expense.trackig.ExpenseTracking.Controllers;

import com.expense.trackig.ExpenseTracking.Modules.Budget;
import com.expense.trackig.ExpenseTracking.Services.Implementation.BudgetServiceImpl;

import com.expense.trackig.ExpenseTracking.Services.Interface.BudgetService;
import com.expense.trackig.ExpenseTracking.Services.Interface.ExpenseService;
import com.expense.trackig.ExpenseTracking.Services.Interface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.Month;

@Controller
@RequestMapping("/budget")
public class BudgetController {
    @Autowired
    private BudgetService budgetService;
    @Autowired
    private ExpenseService expenseService;
    @Autowired
    private UserService userService;

    @GetMapping("/add-budget/{category}")
    public String showBudgetForm(@CookieValue(value = "expense_cookie", defaultValue = "null") String cookie,@PathVariable("category") String category, Model model) {
        if(!userService.isLoggedIn(cookie))
            return "redirect:/public/login";
        model.addAttribute("months",expenseService.getMonths());
        model.addAttribute("years",expenseService.getYears());
        model.addAttribute("category",category);
        return "add_budget";
    }

    @PostMapping("/add-budget")
    public String saveBudget(@CookieValue(value = "expense_cookie", defaultValue = "null") String cookie,@ModelAttribute Budget budget) {
        if(!userService.isLoggedIn(cookie))
            return "redirect:/public/login";
        try{
            if(budgetService.isBudgetAllocated(budget.getMonth(), budget.getYear(), budget.getCategory())){
                budgetService.removeBudget(budget);
            }
            budgetService.saveBudget(budget);
        }catch (Exception e){
            System.out.println(e);
            return "redirect:/budget/add-budget/"+budget.getCategory();
        }
        return "redirect:/expense/getExpenses?category="+budget.getCategory()+"&month="+ Month.valueOf(budget.getMonth().toUpperCase()) +"&year="+budget.getYear()+"";
    }
}
