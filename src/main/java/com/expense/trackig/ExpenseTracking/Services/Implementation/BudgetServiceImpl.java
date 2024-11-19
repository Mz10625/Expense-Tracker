package com.expense.trackig.ExpenseTracking.Services.Implementation;

import com.expense.trackig.ExpenseTracking.Modules.Budget;
import com.expense.trackig.ExpenseTracking.Modules.Category;
import com.expense.trackig.ExpenseTracking.Modules.User;
import com.expense.trackig.ExpenseTracking.Repository.BudgetRepository;
import com.expense.trackig.ExpenseTracking.Services.Interface.BudgetService;
import com.expense.trackig.ExpenseTracking.Services.Interface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class BudgetServiceImpl implements BudgetService {
    @Autowired
    private BudgetRepository budgetRepository;
    @Autowired
    private UserService userService;

    @Override
    public void saveBudget(String email, Budget budget) {
        User user = userService.getUserByEmail(email);
        Budget savedBudget = budgetRepository.save(budget);
        user.getBudget().add(savedBudget);
        userService.saveUser(user);
    }

    @Override
    public double getAllocatedBudget(String email,String month, int year, String category) {
        User user = userService.getUserByEmail(email);
        List<Budget> list = user.getBudget();
        for(Budget budget : list) {
            if (Objects.equals(budget.getMonth().toUpperCase(), month.toUpperCase()) && budget.getYear() == year) {
                return budget.getAllocatedBudget();
            }
        }
        return 0;
    }
    public boolean isBudgetAllocated(String email,String month, int year, String category){
        User user = userService.getUserByEmail(email);
        List<Budget> budgets = user.getBudget();
        for (Budget budget: budgets) {
            if(Objects.equals(budget.getMonth(), month) && budget.getYear() == year){
                return true;
            }
        }
        return false;
    }

    @Override
    public void removeBudget(String email,Budget budget) {
        User user = userService.getUserByEmail(email);
        List<Budget> budgets = user.getBudget();
        for (Budget c: budgets) {
            if(c.getMonth().equals(budget.getMonth()) && c.getYear() == budget.getYear()){
                budgets.remove(c);
                break;
            }
        }
        user.setBudget(budgets);
        userService.saveUser(user);
    }

//    @Override
//    public boolean isBudgetSufficient(String email,String month, int year, String category, double amount) {
//        double allocatedBudget = getAllocatedBudget(email,month,year,category);
//        if(allocatedBudget - amount < 0){
//            return false;
//        }
//        return true;
//    }
}
