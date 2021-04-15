package webshop.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import webshop.Model.Product.Category;
import webshop.Repository.CategoryRepo;

import java.util.List;

@Service
public class CategoryService {
    private CategoryRepo categoryRepo;

    @Autowired
    public CategoryService(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Transactional
    public void addCategory(Category category) {
        Category c = new Category();
        c.setCategoryName(category.getCategoryName());
       // c.setParentCategory(categoryRepo.findByCategoryName(category.getParentCategory().getCategoryName()));
       c.setParentCategoryID(category.getParentCategoryID());


      //  Category parentCat=categoryRepo.findCategoryByID(category.getParentCategory().getID());
      // long parentCatID= parentCat.getID();
       // System.out.println(categoryRepo.findCategoryByID(parentCatID).getCategoryName());
      //  c.setParentCategory(categoryRepo.findCategoryByID(parentCatID));
        categoryRepo.saveAndFlush(c);
    }

    @Transactional
    public List<Category> listCategory(){
        return categoryRepo.findAll();
    }
}
