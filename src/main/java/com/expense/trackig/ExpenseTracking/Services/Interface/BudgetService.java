package com.expense.trackig.ExpenseTracking.Services.Interface;

import com.expense.trackig.ExpenseTracking.Modules.Budget;

public interface BudgetService {
    public void saveBudget(String email, Budget budget);
    public double getAllocatedBudget(String email,String month, int year, String category);
    public boolean isBudgetAllocated(String email,String month, int year, String category);
    public void removeBudget(String email, Budget budget);
//    public boolean isBudgetSufficient(String email,String month, int year, String category,double amount);
}
