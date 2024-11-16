package Repository;

import Modules.Expense;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseRepository extends MongoRepository<Expense, String> {
    List<Expense> findByCategoryAndDateBetween(String category, LocalDate startDate, LocalDate endDate);
}
