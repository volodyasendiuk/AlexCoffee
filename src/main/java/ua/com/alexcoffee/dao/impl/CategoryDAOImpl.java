package ua.com.alexcoffee.dao.impl;

import ua.com.alexcoffee.dao.CategoryDAO;
import ua.com.alexcoffee.repository.CategoryRepository;
import ua.com.alexcoffee.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Класс реализует методы доступа объектов класса {@link Category}
 * в базе данных интерфейса {@link CategoryDAO}, наследует родительский
 * абстрактній класс {@link AbstractDAOImpl}, в котором реализованы
 * основные методы. Для работы методы используют объект-репозиторий
 * интерфейса {@link CategoryRepository}.
 * Класс помечена аннотацией @Repository (наследник Spring'овой аннотации @Component).
 * Это позволяет Spring автоматически зарегестрировать компонент в своём контексте
 * для последующей инъекции.
 *
 * @author Yurii Salimov
 * @see AbstractDAOImpl
 * @see CategoryDAO
 * @see Category
 * @see CategoryRepository
 */
@Repository
public class CategoryDAOImpl extends AbstractDAOImpl<Category> implements CategoryDAO {
    /**
     * Объект репозитория для работы с БД.
     * Поле помечано аннотацией @Autowired, которая позволит Spring
     * автоматически инициализировать репозиторий.
     */
    @Autowired
    private CategoryRepository repository;

    /**
     * Возвращает категорию из базы данных, у которой совпадает параметр url.
     *
     * @param url URL категории для возврата.
     * @return Объект класса {@link Category} - категория с уникальным url полем.
     */
    @Override
    public Category get(String url) {
        return repository.findByUrl(url);
    }

    /**
     * Удаляет категрию из базы даных, у которого совпадает поле url.
     *
     * @param url URL категории для удаления.
     */
    @Override
    public void remove(String url) {
        repository.deleteByUrl(url);
    }

    /**
     * Возвращает объект репозитория для работы основных методов доступа к базе данных.
     *
     * @return Объект класса {@link CategoryRepository} - репозиторий.
     */
    @Override
    public CategoryRepository getRepository() {
        return repository;
    }
}
