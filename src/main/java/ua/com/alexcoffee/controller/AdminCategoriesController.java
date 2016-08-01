package ua.com.alexcoffee.controller;

import ua.com.alexcoffee.entity.Category;
import ua.com.alexcoffee.entity.Photo;
import ua.com.alexcoffee.exception.WrongInformationException;
import ua.com.alexcoffee.service.CategoryService;
import ua.com.alexcoffee.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/admin")
public class AdminCategoriesController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private PhotoService photoService;

    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public ModelAndView viewAllCategories(ModelAndView modelAndView) {
        modelAndView.addObject("categories", categoryService.getAll());
        modelAndView.setViewName("admin/category/all");
        return modelAndView;
    }

    @RequestMapping(value = "/view_category_{id}", method = RequestMethod.GET)
    public ModelAndView viewCategory(@PathVariable(value = "id") long id, ModelAndView modelAndView) {
        modelAndView.addObject("category", categoryService.get(id));
        modelAndView.setViewName("admin/category/one");
        return modelAndView;
    }

    @RequestMapping(value = "/add_category", method = RequestMethod.GET)
    public ModelAndView getAddCategoryPage(ModelAndView modelAndView) {
        modelAndView.addObject("photos", photoService.getAll());
        modelAndView.setViewName("admin/category/add");
        return modelAndView;
    }

    @RequestMapping(value = "/save_category", method = RequestMethod.POST)
    public ModelAndView saveCategory(@RequestParam String title,
                                     @RequestParam String url,
                                     @RequestParam String description,
                                     @RequestParam(value = "photo_title") String photoTitle,
                                     @RequestParam(value = "photo") MultipartFile photoFile,
                                     ModelAndView modelAndView) {
        Photo photo = new Photo(photoTitle, "img/" + photoFile.getOriginalFilename(), null);
        Category category = new Category(title, url, description, photo);
        categoryService.add(category);
        photoService.saveFile(photoFile);
        modelAndView.setViewName("redirect:/admin/categories");
        return modelAndView;
    }

    @RequestMapping(value = "/save_category", method = RequestMethod.GET)
    public void saveCategory() throws WrongInformationException {
        throw new WrongInformationException("GET method in \"/save_category\" is not supported!");
    }

    @RequestMapping(value = "/edit_category_{id}", method = RequestMethod.GET)
    public ModelAndView getEditCategoryPage(@PathVariable(value = "id") long id, ModelAndView modelAndView) {
        modelAndView.addObject("category", categoryService.get(id));
        modelAndView.addObject("photos", photoService.getAll());
        modelAndView.setViewName("admin/category/edit");
        return modelAndView;
    }

    @RequestMapping(value = "/update_category", method = RequestMethod.POST)
    public ModelAndView updateCategory(@RequestParam long id,
                                       @RequestParam String title,
                                       @RequestParam String url,
                                       @RequestParam String description,
                                       @RequestParam(value = "photo_id") long photoId,
                                       @RequestParam(value = "photo_title") String photoTitle,
                                       @RequestParam(value = "photo") MultipartFile photoFile,
                                       ModelAndView modelAndView) {
        Photo photo = photoService.get(photoId);
        photo.setTitle(photoTitle);
        String photoLinkShort = !photoFile.getOriginalFilename().isEmpty() ? "img/" + photoFile.getOriginalFilename() : photo.getPhotoLinkShort();
        photo.setPhotoLinkShort(photoLinkShort);

        Category category = categoryService.get(id);
        category.setAllInfo(title, url, description, photo);
        categoryService.update(category);

        photoService.saveFile(photoFile);

        modelAndView.setViewName("redirect:/admin/view_category_" + id);
        return modelAndView;
    }

    @RequestMapping(value = "/update_category", method = RequestMethod.GET)
    public void updateCategory() throws WrongInformationException {
        throw new WrongInformationException("GET method in \"/update_category\" is not supported!");
    }

    @RequestMapping(value = "/delete_category_{id}", method = RequestMethod.GET)
    public ModelAndView deleteCategory(@PathVariable(value = "id") long id, ModelAndView modelAndView) {
        categoryService.remove(id);
        modelAndView.setViewName("redirect:/admin/categories");
        return modelAndView;
    }

    @RequestMapping(value = "/delete_all_categories", method = RequestMethod.GET)
    public ModelAndView deleteAllCategories(ModelAndView modelAndView) {
        categoryService.removeAll();
        modelAndView.setViewName("redirect:/admin/categories");
        return modelAndView;
    }
}
