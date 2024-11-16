package Services.Interface;

import Modules.Category;

public interface CategoriesService {
    public void saveCategory(Category category);
    public void removeCategory(String name);
}
