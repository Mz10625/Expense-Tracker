package com.expense.trackig.ExpenseTracking.Services.Interface;

import com.expense.trackig.ExpenseTracking.Modules.Budget;

public interface BudgetService {
    public void saveBudget(Budget budget);
    public double getAllocatedBudget(String month, int yar, String category);
}
