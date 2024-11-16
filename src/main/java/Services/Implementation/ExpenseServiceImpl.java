package Services.Implementation;

import Modules.Expense;
import Repository.ExpenseRepository;
import Services.Interface.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService {
    @Autowired
    ExpenseRepository expenseRepository;

    @Override
    public void saveExpense(Expense expense) {
        expenseRepository.save(expense);
    }

    @Override
    public void removeExpense(String id) {
        expenseRepository.deleteById(id);
    }

    @Override
    public List<Expense> getAllExpenses(String month, int year, String category) {
        LocalDate startDate = LocalDate.of(year, Month.valueOf(month), 1);
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());
        return expenseRepository.findByCategoryAndDateBetween(category, startDate, endDate);
    }
}
