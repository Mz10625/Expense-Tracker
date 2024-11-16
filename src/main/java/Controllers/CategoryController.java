package Controllers;

import Modules.Category;
import Services.Implementation.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryServiceImpl categoryService;

    @PostMapping("/add")
    public String addCategory(@ModelAttribute Category category){
        try {
            categoryService.saveCategory(category);
        }catch (Exception e){
            return "redirect:/category-not-added";
        }
        return "redirect:/dashboard";
    }
    @GetMapping("/remove/{name}")
    public String removeCategory(@PathVariable("name") String name){
        try {
            categoryService.removeCategory(name);
        }catch (Exception e){
            return "redirect:/category-not-removed";
        }
        return "redirect:/dashboard";
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
