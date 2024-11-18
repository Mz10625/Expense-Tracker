package com.expense.trackig.ExpenseTracking.Services.Implementation;

import com.expense.trackig.ExpenseTracking.Modules.Budget;
import com.expense.trackig.ExpenseTracking.Repository.BudgetRepository;
import com.expense.trackig.ExpenseTracking.Services.Interface.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class BudgetServiceImpl implements BudgetService {
    @Autowired
    private BudgetRepository budgetRepository;

    @Override
    public void saveBudget(Budget budget) {
        budgetRepository.save(budget);
    }

    @Override
    public double getAllocatedBudget(String month, int year, String category) {
        List<Budget> list = budgetRepository.findBudgetByCategory(category);
        for(Budget budget : list) {
            if (Objects.equals(budget.getMonth().toUpperCase(), month.toUpperCase()) && budget.getYear() == year) {
                return budget.getAllocatedBudget();
            }
        }
        return 0;
    }
    public boolean isBudgetAllocated(String month, int year, String category){
        return budgetRepository.findBudgetByMonthAndYear(month, year) != null;
    }

    @Override
    public void removeBudget(Budget budget) {
        budgetRepository.deleteBudgetByMonthAndYear(budget.getMonth(), budget.getYear());
    }

    @Override
    public boolean isBudgetSufficient(String month, int year, String category, double amount) {
        System.out.println(month);
        double allocatedBudget = getAllocatedBudget(month,year,category);
        System.out.println(allocatedBudget);
        if(allocatedBudget - amount < 0){
            return false;
        }
        return true;
    }
}
