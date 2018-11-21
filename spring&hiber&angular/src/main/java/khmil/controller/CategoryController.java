package khmil.controller;

import khmil.model.Category;
import khmil.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(path = "/categories", method = RequestMethod.GET)
    public ModelAndView getAllCategories(ModelAndView modelAndView) {
        List<Category> categories = categoryService.getAll();
        modelAndView.setViewName("categories");
        modelAndView.addObject("categories", categories);
        return modelAndView;
    }

    @RequestMapping(value = "/category", method = RequestMethod.GET)
    public ModelAndView getCategoryById(@RequestParam("c_id") Long id, ModelAndView modelAndView) {
        Category category = categoryService.getById(id);
        modelAndView.setViewName("category");
        modelAndView.addObject("category", category);
        return modelAndView;
    }

}
