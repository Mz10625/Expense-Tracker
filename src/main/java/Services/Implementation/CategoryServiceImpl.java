package Services.Implementation;

import Modules.Category;
import Repository.CategoryRepository;
import Services.Interface.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoriesService {
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }
    @Override
    public void removeCategory(String name) {
        categoryRepository.deleteById(name);
    }
}
