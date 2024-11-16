package Services.Interface;

import Modules.Budget;

public interface BudgetService {
    public void saveBudget(Budget budget);
    public double getAllocatedBudget(String month, int yar, String category);
}
