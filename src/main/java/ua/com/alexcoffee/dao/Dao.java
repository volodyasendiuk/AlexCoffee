package ua.com.alexcoffee.dao;

import ua.com.alexcoffee.dao.impl.AbstractDAOImpl;
import ua.com.alexcoffee.model.Model;

import java.util.Collection;
import java.util.List;

/**
 * Интерфейс описывает стандартный (общий) набор методов для работы с базой данных.
 * Объекты для работы должны быть наследниками класса {@link Model}.
 *
 * @param <T> Класс-наследник класса {@link Model}.
 * @author Yurii Salimov
 * @see CategoryDAO
 * @see OrderDAO
 * @see PhotoDAO
 * @see ProductDAO
 * @see RoleDAO
 * @see SalePositionDAO
 * @see StatusDAO
 * @see UserDAO
 * @see AbstractDAOImpl
 */
public interface DAO<T extends Model> {

    /**
     * Добавление модели в базу данных.
     *
     * @param model Модель для добавления.
     */
    void add(T model);

    /**
     * Добавление коллекции моделей в базу данных.
     *
     * @param models Коллекция моделей для добавления.
     */
    void add(Collection<T> models);

    /**
     * Обновление существующей модели в базе данных.
     *
     * @param model Обновленная модель.
     */
    void update(T model);

    /**
     * Получение модели по уникальному коду id в базе данных.
     *
     * @param id Уникальный код модели.
     * @return Объект класса {@link ua.com.alexcoffee.model.Model} -  модель с кодом id.
     */
    T get(Long id);

    /**
     * Получение всех моделей из базы данных.
     *
     * @return Объект типа List - список всех моделей.
     */
    List<T> getAll();

    /**
     * Удаление модели из базы данных.
     *
     * @param model Модель для удаления.
     */
    void remove(T model);

    /**
     * Удаление модели из базы данных по уникальному коду.
     *
     * @param id Уникальный код модели.
     */
    void remove(Long id);

    /**
     * Удаление коллекции моделей из базы данных.
     *
     * @param models Коллекция моделей для удаления.
     */
    void remove(Collection<T> models);

    /**
     * Удаление всех моделей из базы данных.
     */
    void removeAll();
}
