package ua.com.alexcoffee.dao.impl;

import ua.com.alexcoffee.dao.DAO;
import ua.com.alexcoffee.repository.ItemRepository;
import ua.com.alexcoffee.model.Model;

import java.util.Collection;
import java.util.List;

/**
 * Абстрактный класс, который реализует основные методы доступа к базе данных
 * интерфейса {@link DAO}. Класс должен наследоваться
 * дочерними классами, которые будут описывать поведение объектов-наследников
 * родительского класса {@link Model}. Для работы методы
 * используют объект-репозиторий интерфейса {@link ItemRepository},
 * возвращаемый абстрактным методом getRepository(), реализацию которого каждый наследник берет
 * на себя.
 *
 * @param <T> Класс-наследник класса {@link ua.com.alexcoffee.model.Model}.
 * @author Yurii Salimov
 * @see CategoryDAOImpl
 * @see AbstractDAOImpl
 * @see PhotoDAOImpl
 * @see ProductDAOImpl
 * @see RoleDAOImpl
 * @see SalePositionDAOImpl
 * @see StatusDAOImpl
 * @see UserDAOImpl
 */
public abstract class AbstractDAOImpl<T extends Model> implements DAO<T> {

    /**
     * Добавление модели в базу данных.
     *
     * @param model Модель для добавления.
     */
    @Override
    public void add(T model) {
        getRepository().save(model);
    }

    /**
     * Добавление коллекции моделей в базу данных.
     *
     * @param models Коллекция моделей для добавления.
     */
    @Override
    public void add(Collection<T> models) {
        getRepository().save(models);
    }

    /**
     * Обновление существующей модели в базе данных.
     *
     * @param model Обновленная модель.
     */
    @Override
    public void update(T model) {
        getRepository().save(model);
    }

    /**
     * Получение модели по уникальному коду id в базе данных.
     *
     * @param id Уникальный код модели.
     * @return Объект класса {@link ua.com.alexcoffee.model.Model} -  модель с кодом id.
     */
    @Override
    public T get(Long id) {
        return getRepository().findOne(id);
    }

    /**
     * Получение всех моделей из базы данных.
     *
     * @return Объект типа List - список всех моделей.
     */
    @Override
    public List<T> getAll() {
        return getRepository().findAll();
    }

    /**
     * Удаление модели из базы данных.
     *
     * @param model Модель для удаления.
     */
    @Override
    public void remove(T model) {
        getRepository().delete(model);
    }

    /**
     * Удаление модели из базы данных по уникальному коду.
     *
     * @param id Уникальный код модели.
     */
    @Override
    public void remove(Long id) {
        getRepository().delete(id);
    }

    /**
     * Удаление коллекции моделей из базы данных.
     *
     * @param models Коллекция моделей для удаления.
     */
    @Override
    public void remove(Collection<T> models) {
        getRepository().delete(models);
    }

    /**
     * Удаление всех моделей из базы данных.
     */
    @Override
    public void removeAll() {
        getRepository().deleteAll();
    }

    /**
     * Абстрактный метод, возвращает объект интерфейса {@link ItemRepository}
     * для работы других методов данного класса.
     *
     * @return Объект-репозиторий интерфейса {@link ItemRepository}.
     */
    public abstract ItemRepository<T, Long> getRepository();
}
