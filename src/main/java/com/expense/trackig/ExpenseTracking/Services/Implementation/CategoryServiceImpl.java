package com.expense.trackig.ExpenseTracking.Services.Implementation;

import com.expense.trackig.ExpenseTracking.Modules.Category;
import com.expense.trackig.ExpenseTracking.Modules.User;
import com.expense.trackig.ExpenseTracking.Repository.CategoryRepository;
import com.expense.trackig.ExpenseTracking.Services.Interface.CategoriesService;
import com.expense.trackig.ExpenseTracking.Services.Interface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoriesService {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    UserService userService;

    @Override
    public void saveCategory(String email,Category category) {
        User user = userService.getUserByEmail(email);
        user.getCategories().add(category);
        userService.saveUser(user);
    }
    @Override
    public void removeCategory(String email,String name) {
        User user = userService.getUserByEmail(email);
        List<Category> categories = user.getCategories();
        for (Category c: categories) {
            if(c.getName().equals(name)){
                categories.remove(c);
                break;
            }
        }
        user.setCategories(categories);
        userService.saveUser(user);
    }

    @Override
    public List<Category> getAllCategories(String email) {
        return userService.getUserByEmail(email).getCategories();
    }
}
