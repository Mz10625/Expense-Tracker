package com.expense.trackig.ExpenseTracking.Services.Implementation;

import com.expense.trackig.ExpenseTracking.Modules.Expense;
import com.expense.trackig.ExpenseTracking.Repository.ExpenseRepository;
import com.expense.trackig.ExpenseTracking.Services.Interface.ExpenseService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService {
    @Autowired
    ExpenseRepository expenseRepository;
    @Getter
    final private List<String> months = new ArrayList<>(Arrays.asList(
            "January", "February", "March", "April",
            "May", "June", "July", "August",
            "September", "October", "November", "December"
    ));
    @Getter
    final private List<String> years = new ArrayList<>(Arrays.asList("2023","2024","2025","2026","2027","2028"));

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
