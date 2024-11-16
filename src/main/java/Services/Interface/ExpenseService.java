package Services.Interface;

import Modules.Expense;

import java.util.List;

public interface ExpenseService {
    public void saveExpense(Expense expense);
    public void removeExpense(String id);
    public List<Expense> getAllExpenses(String month, int year, String category);
}
