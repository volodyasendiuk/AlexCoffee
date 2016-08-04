package ua.com.alexcoffee.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alexcoffee.dao.CategoryDAO;
import ua.com.alexcoffee.dao.ProductDAO;
import ua.com.alexcoffee.model.Category;
import ua.com.alexcoffee.model.Product;
import ua.com.alexcoffee.exception.BadRequestException;
import ua.com.alexcoffee.exception.WrongInformationException;
import ua.com.alexcoffee.service.ProductService;

import java.util.Collections;
import java.util.List;

@Service
public class ProductServiceImpl extends ItemServiceImpl<Product> implements ProductService {

    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private CategoryDAO categoryDAO;

    @Override
    @Transactional(readOnly = true)
    public Product get(String url) throws WrongInformationException, BadRequestException {
        if (url.isEmpty()) {
            throw new WrongInformationException("No product URL!");
        }
        Product product = productDAO.get(url);
        if (product == null) {
            throw new BadRequestException("Can't find product by URL " + url + "!");
        }
        return product;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getByCategoryUrl(String categoryUrl) throws WrongInformationException, BadRequestException {
        if (categoryUrl.isEmpty()) {
            throw new WrongInformationException("No category URL!");
        }
        Category category = categoryDAO.get(categoryUrl);
        if (category == null) {
            throw new BadRequestException("Can't find category by URL " + categoryUrl + "!");
        }

        List<Product> productList = productDAO.getListByCategoryId(category.getId());
        if (productList.isEmpty()) {
            throw new BadRequestException("Can't find products by category url " + categoryUrl + "!");
        }
        return productList;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getByCategoryId(Long categoryId) {
        List<Product> productList = productDAO.getListByCategoryId(categoryId);
        return productList;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getRandomByCategoryId(int size, Long categoryId) {
        return getRandomByCategoryId(size, categoryId, (long) -1);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getRandomByCategoryId(int size, Long categoryId, Long differentProductId) {
        List<Product> products = productDAO.getListByCategoryId(categoryId);
        if (products.isEmpty()) {
            return null;
        }

        products.remove(productDAO.get(differentProductId));
        return getShuffleSubList(products, 0, size);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getRandom(int size) {
        List<Product> products = productDAO.getAll();
        return getShuffleSubList(products, 0, size);
    }

    @Override
    @Transactional
    public void remove(String url) throws WrongInformationException {
        if (url.isEmpty()) {
            throw new WrongInformationException("No product URL!");
        }
        productDAO.remove(url);
    }

    @Override
    @Transactional
    public void removeByCategoryUrl(String url) throws WrongInformationException, BadRequestException {
        if (url.isEmpty()) {
            throw new WrongInformationException("No category URL!");
        }

        Category category = categoryDAO.get(url);

        if (category == null) {
            throw new BadRequestException("Can't find category by URL " + url + "!");
        }
        productDAO.removeByCategoryId(category.getId());
    }

    @Override
    @Transactional
    public void removeByCategoryId(Long id) throws BadRequestException {
        if (categoryDAO.get(id) == null) {
            throw new BadRequestException("Can't find category by id " + id + "!");
        }
        productDAO.removeByCategoryId(id);
    }

    @Override
    public ProductDAO getDao() {
        return productDAO;
    }

    private static List<Product> getShuffleSubList(List<Product> products, int start, int end) {
        if (start > products.size() || start > end) {
            return null;
        }

        if (end > products.size()) {
            end = products.size();
        }

        Collections.shuffle(products);
        return products.subList(start, end);
    }
}
