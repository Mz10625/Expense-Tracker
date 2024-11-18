package com.expense.trackig.ExpenseTracking.Services.Interface;

import com.expense.trackig.ExpenseTracking.Modules.Budget;

public interface BudgetService {
    public void saveBudget(Budget budget);
    public double getAllocatedBudget(String month, int year, String category);
    public boolean isBudgetAllocated(String month, int year, String category);
    public void removeBudget(Budget budget);
    public boolean isBudgetSufficient(String month, int year, String category,double amount);
}
