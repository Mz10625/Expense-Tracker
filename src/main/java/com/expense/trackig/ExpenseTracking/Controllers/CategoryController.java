package com.expense.trackig.ExpenseTracking.Controllers;

import com.expense.trackig.ExpenseTracking.Modules.Category;
import com.expense.trackig.ExpenseTracking.Services.Implementation.CategoryServiceImpl;
import com.expense.trackig.ExpenseTracking.Services.Interface.CategoriesService;
import com.expense.trackig.ExpenseTracking.Services.Interface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoriesService categoryService;
    @Autowired
    UserService userService;

    @GetMapping("/dashboard")
    public String dashboard(@CookieValue(value = "expense_cookie", defaultValue = "null") String cookie,Model model){
        if(!userService.isLoggedIn(cookie))
            return "redirect:/public/login";
        model.addAttribute("categories",categoryService.getAllCategories());
        return "dashboard";
    }

    @PostMapping("/add")
    public String addCategory(@CookieValue(value = "expense_cookie", defaultValue = "null") String cookie,@ModelAttribute Category category){
        if(!userService.isLoggedIn(cookie))
            return "redirect:/public/login";
        try {
            categoryService.saveCategory(category);
        }catch (Exception e){
            return "redirect:/category/category-not-added";
        }
        return "redirect:/category/dashboard";
    }
    @GetMapping("/remove/{name}")
    public String removeCategory(@CookieValue(value = "expense_cookie", defaultValue = "null") String cookie,@PathVariable("name") String name){
        if(!userService.isLoggedIn(cookie))
            return "redirect:/public/login";
        try {
            categoryService.removeCategory(name);
        }catch (Exception e){
            return "redirect:/category/category-not-removed";
        }
        return "redirect:/category/dashboard";
    }

    @GetMapping("/category-not-added")
    @ResponseBody
    public String categoryNotAdded(){
        return "Failed to add new Category";
    }

    @GetMapping("/category-not-removed")
    @ResponseBody
    public String categoryNotRemoved(){
        return "Failed to remove Category";
    }

}
