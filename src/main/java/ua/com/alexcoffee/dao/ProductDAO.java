package ua.com.alexcoffee.dao;

import ua.com.alexcoffee.model.Product;

import java.util.List;

/**
 * Интерфейс описывает набор методов для работы объектов класса
 * {@link Product} с базой данных.
 * Расширяет интерфейс {@link MainDAO}.
 *
 * @author Yurii Salimov
 * @see MainDAO
 * @see ua.com.alexcoffee.dao.impl.ProductDAOImpl
 * @see Product
 */
public interface ProductDAO extends MainDAO<Product> {
    /**
     * Возвращает товар из базы данных, у которого совпадает параметр url.
     *
     * @param url URL товара для возврата.
     * @return Объект класса {@link Product} - товара с уникальным url полем.
     */
    Product getByUrl(String url);

    /**
     * Возвращает товар из базы даных, у которого совпадает уникальный
     * артикль с значением входящего параметра.
     *
     * @param article Артикль товара для возврата.
     * @return Объект класса {@link Product} - товара с уникальным артиклем.
     */
    Product getByArticle(int article);

    /**
     * Удаляет товар из базы данных, у которого совпадает параметр url.
     *
     * @param url URL товара для удаления.
     */
    void remove(String url);

    /**
     * Удаляет товары из базы даных, которые пренадлежат категории
     * с уникальным кодом - входным параметром.
     *
     * @param id Уникальный код категории, товары который будут удалены.
     */
    void removeByCategoryId(long id);

    /**
     * Возвращает список товаров, которые пренадлежат категории
     * с уникальным кодом - входным параметром.
     *
     * @param id Уникальный код категории.
     * @return Объект типа List - список товаров.
     */
    List<Product> getListByCategoryId(long id);
}
