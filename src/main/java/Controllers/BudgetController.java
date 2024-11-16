package Controllers;

import Modules.Budget;
import Services.Implementation.BudgetServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/budget")
public class BudgetController {
    @Autowired
    private BudgetServiceImpl budgetService;

    @GetMapping("/add-budget")
    public String showBudgetForm() {
        return "add-budget";
    }

    @PostMapping("/add-budget")
    public String saveBudget(@ModelAttribute Budget budget) {
        try{
            budgetService.saveBudget(budget);
        }catch (Exception e){
            return "redirect:/budget/add-budget";
        }
        return "dashboard";
    }
}
