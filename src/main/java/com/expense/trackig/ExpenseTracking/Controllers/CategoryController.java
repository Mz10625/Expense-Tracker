package com.expense.trackig.ExpenseTracking.Controllers;

import com.expense.trackig.ExpenseTracking.Modules.Category;
import com.expense.trackig.ExpenseTracking.Services.Implementation.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryServiceImpl categoryService;

    @GetMapping("/dashboard")
    public String dashboard(Model model){
        model.addAttribute("categories",categoryService.getAllCategories());
        return "dashboard";
    }

    @PostMapping("/add")
    public String addCategory(@ModelAttribute Category category){
        try {
            categoryService.saveCategory(category);
        }catch (Exception e){
            return "redirect:/category/category-not-added";
        }
        return "redirect:/category/dashboard";
    }
    @GetMapping("/remove/{name}")
    public String removeCategory(@PathVariable("name") String name){
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
