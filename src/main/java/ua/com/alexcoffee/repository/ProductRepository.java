package ua.com.alexcoffee.repository;

import ua.com.alexcoffee.model.Product;

import java.util.List;

/**
 * Репозиторий для объектов класса {@link Product}, предоставляющий
 * набор методов JPA для работы с БД. Наследует интерфейс {@link ItemRepository}.
 *
 * @author Yurii Salimov
 * @see ItemRepository
 * @see Product
 */
public interface ProductRepository extends ItemRepository<Product, Long> {
    /**
     * Возвращает товар из базы данных, у которого совпадает параметр url.
     *
     * @param url URL товара для возврата.
     * @return Объект класса {@link Product} - товара с уникальным url полем.
     */
    Product findByUrl(String url);

    /**
     * Возвращает товар из базы даных, у которого совпадает уникальный
     * артикль с значением входящего параметра.
     *
     * @param article Артикль товара для возврата.
     * @return Объект класса {@link Product} - товара с уникальным артиклем.
     */
    Product findByArticle(int article);

    /**
     * Удаляет товар из базы данных, у которого совпадает параметр url.
     *
     * @param url URL товара для удаления.
     */
    void deleteByUrl(String url);

    /**
     * Возвращает список товаров, которые пренадлежат категории
     * с уникальным кодом - входным параметром.
     *
     * @param id Уникальный код категории.
     * @return Объект типа List - список товаров.
     */
    List<Product> findByCategoryId(long id);
}
