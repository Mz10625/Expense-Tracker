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
import java.util.Date;
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

    @GetMapping("/{category}")
    public String showExpensePage(@CookieValue(value = "expense_cookie", defaultValue = "null") String cookie,Model model){
        if(!userService.isLoggedIn(cookie))
            return "redirect:/public/login";
        model.addAttribute("selectedMonth","Month");
        model.addAttribute("selectedYear","Year");
        model.addAttribute("allocated","0");
        model.addAttribute("available","0");
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
        List<Expense> expenses = expenseService.getAllExpenses(month,year,category);
        double allocatedBudget = budgetService.getAllocatedBudget(month,year,category);

        double totalSpent = expenses.stream().mapToDouble(Expense::getAmount).sum();
        double remainingBudget = allocatedBudget - totalSpent;

        model.addAttribute("category", category);
        model.addAttribute("allocatedBudget", allocatedBudget);
        model.addAttribute("remainingBudget", remainingBudget);
        model.addAttribute("expenses", expenses);
        model.addAttribute("selectedMonth", month);
        model.addAttribute("selectedYear", year);

        return "expense-page";
    }

    @GetMapping("/addExpenseForm")
    public String showExpenseForm(@CookieValue(value = "expense_cookie", defaultValue = "null") String cookie,Model model) {
        if(!userService.isLoggedIn(cookie))
            return "redirect:/public/login";
        model.addAttribute("expense", new Expense());
        return "add_expense";
    }

    @PostMapping("/addExpense")
    public String addExpense(
            @CookieValue(value = "expense_cookie", defaultValue = "null") String cookie,
            @ModelAttribute Expense expense,
            @RequestParam String category,
            @RequestParam Date date
    ) {
        if(!userService.isLoggedIn(cookie))
            return "redirect:/public/login";
        try {
            expenseService.saveExpense(expense);
        }catch (Exception e){
            return "add_expense";
        }
        return "redirect:/expense/redirectToExpense/"+category+"/"+date.getYear()+"/"+ Month.of(date.getMonth()).name();
    }
    @GetMapping("/redirectToExpense/{category}/{year}/{month}")
    public String redirectToExpense(@CookieValue(value = "expense_cookie", defaultValue = "null") String cookie,@PathVariable("category") String category,@PathVariable("year") int year,@PathVariable String month, Model model){
        if(!userService.isLoggedIn(cookie))
            return "redirect:/public/login";
        model.addAttribute("year",year);
        model.addAttribute("category",category);
        model.addAttribute("month",month);
        return "redirect:/expense/getExpenses";
    }
}
