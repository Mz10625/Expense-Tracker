package Repository;


import Modules.Budget;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BudgetRepository extends MongoRepository<Budget,String> {
    List<Budget> findBudgetByCategory(String category);
}
