package com.expense.trackig.ExpenseTracking.Controllers;

import com.expense.trackig.ExpenseTracking.Modules.Expense;
import com.expense.trackig.ExpenseTracking.Services.Interface.BudgetService;
import com.expense.trackig.ExpenseTracking.Services.Interface.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/expense")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;
    @Autowired
    private BudgetService budgetService;

    @GetMapping("/{category}")
    public String showExpensePage(Model model){
        model.addAttribute("selectedMonth","Month");
        model.addAttribute("selectedYear","Year");
        model.addAttribute("allocated","0");
        model.addAttribute("available","0");
        return "expense_page";
    }

    @GetMapping("/getExpenses")
    public String viewCategory(
            @RequestParam("category") String category,
            @RequestParam("month") String month,
            @RequestParam("year") int year,
            Model model) {

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
    public String showExpenseForm(Model model) {
        model.addAttribute("expense", new Expense());
        return "add_expense";
    }

    @PostMapping("/addExpense")
    public String addExpense(
            @ModelAttribute Expense expense,
            @RequestParam String category,
            @RequestParam Date date,
            @RequestParam String item,
            @RequestParam double amount,
            @RequestParam String paymentMethod,
            @RequestParam String notes) {

        try {
            expenseService.saveExpense(expense);
        }catch (Exception e){
            return "add_expense";
        }

        return "redirect:/expense/"+category;
    }
}
