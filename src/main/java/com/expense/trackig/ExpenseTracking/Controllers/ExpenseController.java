package com.expense.trackig.ExpenseTracking.Controllers;

import com.expense.trackig.ExpenseTracking.Modules.Expense;
import com.expense.trackig.ExpenseTracking.Services.Interface.BudgetService;
import com.expense.trackig.ExpenseTracking.Services.Interface.ExpenseService;
import com.expense.trackig.ExpenseTracking.Services.Interface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.Month;
import java.util.List;

@Controller
@RequestMapping("/expense")
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;
    @Autowired
    private BudgetService budgetService;
    @Autowired
    private UserService userService;

    @GetMapping("/{category}/{email}")
    public String showExpensePage(@PathVariable("email") String email,@CookieValue(value = "expense_cookie", defaultValue = "null") String cookie,@PathVariable("category") String category,Model model){
        if(!userService.isLoggedIn(cookie))
            return "redirect:/public/login";
        model.addAttribute("email",email);
        model.addAttribute("selectedMonth","November");
        model.addAttribute("months",expenseService.getMonths());
        model.addAttribute("years",expenseService.getYears());
        model.addAttribute("selectedYear","2024");
        model.addAttribute("allocatedBudget","0.0");
        model.addAttribute("remainingBudget","0.0");
        model.addAttribute("category",category);
        return "expense_page";
    }

    @GetMapping("/getExpenses")
    public String viewCategory(
            @CookieValue(value = "expense_cookie", defaultValue = "null") String cookie,
            @RequestParam("category") String category,
            @RequestParam("month") String month,
            @RequestParam("year") int year,
            Model model) {
        if(!userService.isLoggedIn(cookie))
            return "redirect:/public/login";

        List<Expense> expenses = expenseService.getAllExpenses(Month.valueOf(month.toUpperCase()).toString(),year,category);
        double allocatedBudget = budgetService.getAllocatedBudget(month,year,category);

        double totalSpent = expenses.stream().mapToDouble(Expense::getAmount).sum();
        double remainingBudget = allocatedBudget - totalSpent;


        model.addAttribute("months",expenseService.getMonths());
        model.addAttribute("years",expenseService.getYears());
        model.addAttribute("category", category);
        model.addAttribute("allocatedBudget", allocatedBudget);
        model.addAttribute("remainingBudget", remainingBudget);
        model.addAttribute("expenses", expenses);
        model.addAttribute("selectedMonth", month);
        model.addAttribute("selectedYear", Integer.toString(year));

        return "expense_page";
    }

    @GetMapping("/addExpenseForm/{category}")
    public String showExpenseForm(@CookieValue(value = "expense_cookie", defaultValue = "null") String cookie,@PathVariable("category") String category,Model model) {
        if(!userService.isLoggedIn(cookie))
            return "redirect:/public/login";
        model.addAttribute("category",category);
        return "add_expense";
    }

    @PostMapping("/addExpense")
    public String addExpense(
            @CookieValue(value = "expense_cookie", defaultValue = "null") String cookie,@ModelAttribute Expense expense
    ) {
        if(!userService.isLoggedIn(cookie))
            return "redirect:/public/login";
        if(budgetService.getAllocatedBudget(expense.getDate().getMonth().toString(),expense.getDate().getYear(),expense.getCategory())==0){
            return "redirect:/expense/budgetNotAllocatedError";
        }
        List<Expense> expenses = expenseService.getAllExpenses(expense.getDate().getMonth().toString().toUpperCase(),expense.getDate().getYear(),expense.getCategory());
        double allocatedBudget = budgetService.getAllocatedBudget(expense.getDate().getMonth().toString().toUpperCase(),expense.getDate().getYear(),expense.getCategory());
        double totalSpent = expenses.stream().mapToDouble(Expense::getAmount).sum();
        System.out.println(allocatedBudget+" "+totalSpent);
        if(allocatedBudget - (totalSpent+expense.getAmount()) < 0){
            return "redirect:/expense/insufficientBudget";
        }
        try {
            expenseService.saveExpense(expense);
        }catch (Exception e){
            return "add_expense";
        }
        return "redirect:/expense/getExpenses?category="+expense.getCategory()+"&month="+expense.getDate().getMonth()+"&year="+expense.getDate().getYear()+"";
    }
    @GetMapping("/remove/{id}")
    @ResponseBody
    public String remove(@PathVariable("id") String id,@CookieValue(value = "expense_cookie", defaultValue = "null") String cookie){
        if(!userService.isLoggedIn(cookie))
            return "redirect:/public/login";
        expenseService.removeExpense(id);
        return "Expense deleted";
    }
    @GetMapping("/budgetNotAllocatedError")
    @ResponseBody
    public String budgetNotAllocatedError(){
        return "budget not allocated for selected month";
    }
    @GetMapping("/insufficientBudget")
    @ResponseBody
    public String insufficientBudget(){
        return "insufficient budget for month";
    }
}
