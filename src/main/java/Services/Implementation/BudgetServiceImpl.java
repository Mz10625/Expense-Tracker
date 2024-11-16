package Services.Implementation;

import Modules.Budget;
import Repository.BudgetRepository;
import Services.Interface.BudgetService;
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
            if (Objects.equals(budget.getMonth(), month) && budget.getYear() == year) {
                return budget.getAllocatedBudget();
            }
        }
        return 0;
    }
}
