package ua.com.alexcoffee.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.com.alexcoffee.dao.ProductDAO;
import ua.com.alexcoffee.repository.ProductRepository;
import ua.com.alexcoffee.model.Product;

import java.util.List;

/**
 * Класс реализует методы доступа объектов класса {@link Product}
 * в базе данных интерфейса {@link ProductDAO}, наследует родительский
 * абстрактній класс {@link MainDAOImpl}, в котором реализованы
 * основные методы. Для работы методы используют объект-репозиторий
 * интерфейса {@link ProductRepository}.
 * Класс помечена аннотацией @Repository (наследник Spring'овой аннотации @Component).
 * Это позволяет Spring автоматически зарегестрировать компонент в своём контексте
 * для последующей инъекции.
 *
 * @author Yurii Salimov
 * @see MainDAOImpl
 * @see ProductDAO
 * @see Product
 * @see ProductRepository
 */
@Repository
public class ProductDAOImpl extends MainDAOImpl<Product> implements ProductDAO {
    /**
     * Объект репозитория {@link ProductRepository} для работы с товаров базой данных.
     */
    private ProductRepository repository;

    /**
     * Конструктор для инициализации основных переменных.
     * Помечаный аннотацией @Autowired, которая позволит Spring
     * автоматически инициализировать объект.
     *
     * @param repository Объект репозитория {@link ProductRepository} для работы с товаров базой данных.
     */
    @Autowired
    public ProductDAOImpl(ProductRepository repository) {
        super(repository);
        this.repository = repository;
    }

    /**
     * Возвращает товар из базы данных, у которого совпадает параметр url.
     *
     * @param url URL товара для возврата.
     * @return Объект класса {@link Product} - товара с уникальным url полем.
     */
    @Override
    public Product getByUrl(String url) {
        return repository.findByUrl(url);
    }

    /**
     * Возвращает товар из базы даных, у которого совпадает уникальный
     * артикль с значением входящего параметра.
     *
     * @param article Артикль товара для возврата.
     * @return Объект класса {@link Product} - товара с уникальным артиклем.
     */
    @Override
    public Product getByArticle(int article) {
        return repository.findByArticle(article);
    }

    /**
     * Удаляет товар из базы данных, у которого совпадает параметр url.
     *
     * @param url URL товара для удаления.
     */
    @Override
    public void remove(String url) {
        repository.deleteByUrl(url);
    }

    /**
     * Удаляет товары из базы даных, которые пренадлежат категории
     * с уникальным кодом - входным параметром.
     *
     * @param categoryId Уникальный код категории, товары котрой будут удалены.
     */
    @Override
    public void removeByCategoryId(long categoryId) {
        List<Product> productList = repository.findByCategoryId(categoryId);
        repository.delete(productList);
    }

    /**
     * Возвращает список товаров, которые пренадлежат категории
     * с уникальным кодом - входным параметром.
     *
     * @param categoryId Уникальный код категории.
     * @return Объект типа List - список товаров.
     */
    @Override
    public List<Product> getListByCategoryId(long categoryId) {
        return repository.findByCategoryId(categoryId);
    }
}
