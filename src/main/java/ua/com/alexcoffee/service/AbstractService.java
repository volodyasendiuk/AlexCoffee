package ua.com.alexcoffee.service;

import ua.com.alexcoffee.model.Model;
import ua.com.alexcoffee.service.impl.AbstractServiceImpl;

import java.util.List;

/**
 * Интерфейс сервисного слоя, описывает набор основных методов для работы
 * с объектами  дочерних классов родительского класса {@link Model}.
 * Расширяет интерфейс {@link AbstractService}.
 *
 * @author Yurii Salimov
 * @see Model
 * @see AbstractServiceImpl
 * @see CategoryService
 * @see OrderService
 * @see PhotoService
 * @see ProductService
 * @see RoleService
 * @see SalePositionService
 * @see StatusService
 * @see UserService
 */
public interface AbstractService<T extends Model> {
    /**
     * Добавление модели.
     *
     * @param model Модель для добавления.
     */
    void add(T model);

    /**
     * Добавление список моделей.
     *
     * @param models Список моделей для добавления.
     */
    void add(List<T> models);

    /**
     * Обновление существующей модели.
     *
     * @param model Обновленная модель.
     */
    void update(T model);

    /**
     * Получение модели по уникальному коду id.
     *
     * @param id Уникальный код модели.
     * @return Объект класса {@link ua.com.alexcoffee.model.Model} -  модель с кодом id.
     */
    T get(Long id);

    /**
     * Получение всех моделей.
     *
     * @return Объект типа List - список всех моделей.
     */
    List<T> getAll();

    /**
     * Удаление модели.
     *
     * @param model Модель для удаления.
     */
    void remove(T model);

    /**
     * Удаление модели по уникальному коду.
     *
     * @param id Уникальный код модели.
     */
    void remove(Long id);

    /**
     * Удаление коллекции моделей.
     *
     * @param models Коллекция моделей для удаления.
     */
    void remove(List<T> models);

    /**
     * Удаление всех моделей.
     */
    void removeAll();
}