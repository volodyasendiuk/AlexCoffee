package ua.com.alexcoffee.controller;

import ua.com.alexcoffee.model.Category;
import ua.com.alexcoffee.model.Photo;
import ua.com.alexcoffee.model.Product;
import ua.com.alexcoffee.exception.WrongInformationException;
import ua.com.alexcoffee.service.CategoryService;
import ua.com.alexcoffee.service.PhotoService;
import ua.com.alexcoffee.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alexcoffee.service.UserService;

@Controller
@RequestMapping(value = "/admin")
public class AdminProductsController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private PhotoService photoService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public ModelAndView viewAllProducts(ModelAndView modelAndView) {
        modelAndView.addObject("products", productService.getAll());
        modelAndView.addObject("auth_user", userService.getAuthenticatedUser());
        modelAndView.setViewName("admin/product/all");
        return modelAndView;
    }

    @RequestMapping(value = "/view_product_{id}", method = RequestMethod.GET)
    public ModelAndView viewProduct(@PathVariable(value = "id") long id, ModelAndView modelAndView) {
        modelAndView.addObject("product", productService.get(id));
        modelAndView.addObject("auth_user", userService.getAuthenticatedUser());
        modelAndView.setViewName("admin/product/one");
        return modelAndView;
    }

    @RequestMapping(value = "/add_product", method = RequestMethod.GET)
    public ModelAndView getAddProductPage(ModelAndView modelAndView) {
        modelAndView.addObject("categories", categoryService.getAll());
        modelAndView.addObject("photos", photoService.getAll());
        modelAndView.addObject("auth_user", userService.getAuthenticatedUser());
        modelAndView.setViewName("admin/product/add");
        return modelAndView;
    }

    @RequestMapping(value = "/save_product", method = RequestMethod.POST)
    public ModelAndView saveProduct(@RequestParam String title,
                                    @RequestParam String url,
                                    @RequestParam String parameters,
                                    @RequestParam String description,
                                    @RequestParam(value = "category") long categoryId,
                                    @RequestParam(value = "photo_title") String photoTitle,
                                    @RequestParam(value = "small_photo") MultipartFile smallPhotoFile,
                                    @RequestParam(value = "big_photo") MultipartFile bigPhotoFile,
                                    @RequestParam double price,
                                    ModelAndView modelAndView) {
        Category category = categoryService.get(categoryId);
        Photo photo = new Photo(photoTitle, "img/" + smallPhotoFile.getOriginalFilename(), "img/" + bigPhotoFile.getOriginalFilename());
        Product product = new Product();
        product.initializer(title, url, parameters, description, category, photo, price);
        productService.add(product);

        photoService.saveFile(smallPhotoFile);
        photoService.saveFile(bigPhotoFile);

        modelAndView.setViewName("redirect:/admin/products");
        return modelAndView;
    }

    @RequestMapping(value = "/save_product", method = RequestMethod.GET)
    public void saveProduct() throws WrongInformationException {
        throw new WrongInformationException("GET method in \"/save_product\" is not supported!");
    }

    @RequestMapping(value = "/edit_product_{id}", method = RequestMethod.GET)
    public ModelAndView getEditProductPage(@PathVariable(value = "id") long id, ModelAndView modelAndView) {
        modelAndView.addObject("product", productService.get(id));
        modelAndView.addObject("categories", categoryService.getAll());
        modelAndView.addObject("photos", photoService.getAll());
        modelAndView.addObject("auth_user", userService.getAuthenticatedUser());
        modelAndView.setViewName("admin/product/edit");
        return modelAndView;
    }

    @RequestMapping(value = "/update_product", method = RequestMethod.POST)
    public ModelAndView updateProduct(@RequestParam long id,
                                      @RequestParam String title,
                                      @RequestParam String url,
                                      @RequestParam String parameters,
                                      @RequestParam String description,
                                      @RequestParam(value = "category") long categoryId,
                                      @RequestParam(value = "photo_id") long photoId,
                                      @RequestParam(value = "photo_title") String photoTitle,
                                      @RequestParam(value = "small_photo") MultipartFile smallPhotoFile,
                                      @RequestParam(value = "big_photo") MultipartFile bigPhotoFile,
                                      @RequestParam double price,
                                      ModelAndView modelAndView) {
        Product product = productService.get(id);
        Category category = categoryService.get(categoryId);

        Photo photo = photoService.get(photoId);
        String photoLinkShort = !smallPhotoFile.getOriginalFilename().isEmpty() ? "img/" + smallPhotoFile.getOriginalFilename() : photo.getPhotoLinkShort();
        String photoLinkLong = !bigPhotoFile.getOriginalFilename().isEmpty() ? "img/" + bigPhotoFile.getOriginalFilename() : photo.getPhotoLinkLong();
        photo.initializer(photoTitle, photoLinkShort, photoLinkLong);

        product.initializer(title, url, parameters, description, category, photo, price);

        productService.update(product);

        photoService.saveFile(smallPhotoFile);
        photoService.saveFile(bigPhotoFile);

        modelAndView.setViewName("redirect:/admin/view_product_" + id);
        return modelAndView;
    }

    @RequestMapping(value = "/update_product", method = RequestMethod.GET)
    public void updateProduct() throws WrongInformationException {
        throw new WrongInformationException("GET method in \"/update_product\" is not supported!");
    }

    @RequestMapping(value = "/delete_product_{id}", method = RequestMethod.GET)
    public ModelAndView deleteProduct(@PathVariable(value = "id") long id, ModelAndView modelAndView) {
        productService.remove(id);
        modelAndView.setViewName("redirect:/admin/products");
        return modelAndView;
    }

    @RequestMapping(value = "/delete_all_products", method = RequestMethod.GET)
    public ModelAndView deleteAllProducts(ModelAndView modelAndView) {
        productService.removeAll();
        modelAndView.setViewName("redirect:/admin/products");
        return modelAndView;
    }
}
