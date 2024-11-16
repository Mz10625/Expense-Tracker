package com.expense.trackig.ExpenseTracking.Services.Interface;

import com.expense.trackig.ExpenseTracking.Modules.Expense;

import java.util.List;

public interface ExpenseService {
    public void saveExpense(Expense expense);
    public void removeExpense(String id);
    public List<Expense> getAllExpenses(String month, int year, String category);
}
