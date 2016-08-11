package ua.com.alexcoffee.service.impl;

import org.springframework.transaction.annotation.Transactional;
import ua.com.alexcoffee.dao.DAO;
import ua.com.alexcoffee.exception.BadRequestException;
import ua.com.alexcoffee.model.Model;
import ua.com.alexcoffee.service.AbstractService;

import java.util.List;

/**
 * Абстрактный класс сервисного слоя, который реализует основные методы доступа к
 * объектам наследникам родительского класса {@link Model}, описаные в
 * интерфейсе {@link AbstractService}. Класс должен наследоваться дочерними классами,
 * которые будут описывать поведение объектов-наследников родительского класса {@link Model}.
 * Для работы методы используют объект DAO интерфейса {@link DAO}, возвращаемый абстрактным
 * методом getDao(), реализацию которого каждый наследник берет на себя.
 * Методы класса помечены аннотацией @Transactional - перед исполнением метода помеченного
 * данной аннотацией начинается транзакция, после выполнения метода транзакция коммитится,
 * при выбрасывании RuntimeException откатывается.
 *
 * @param <T> Класс-наследник класса {@link ua.com.alexcoffee.model.Model}.
 * @author Yurii Salimov
 * @see Model
 * @see AbstractService
 * @see CategoryServiceImpl
 * @see OrderServiceImpl
 * @see PhotoServiceImpl
 * @see ProductServiceImpl
 * @see RoleServiceImpl
 * @see SalePositionServiceImpl
 * @see StatusServiceImpl
 * @see UserServiceImpl
 * @see DAO
 */
public abstract class AbstractServiceImpl<T extends Model> implements AbstractService<T> {
    /**
     * Добавление модели в базу данных.
     *
     * @param model Модель для добавления.
     */
    @Override
    @Transactional
    public void add(T model) {
        getDao().add(model);
    }

    /**
     * Добавление коллекции моделей в базу данных.
     *
     * @param models Коллекция моделей для добавления.
     */
    @Override
    @Transactional
    public void add(List<T> models) {
        getDao().add(models);
    }

    /**
     * Обновление существующей модели в базе данных.
     *
     * @param model Обновленная модель.
     */
    @Override
    @Transactional
    public void update(T model) {
        getDao().update(model);
    }

    /**
     * Получение модели по уникальному коду id в базе данных. Режим только для чтения.
     *
     * @param id Уникальный код модели.
     * @return Объект класса {@link ua.com.alexcoffee.model.Model} -  модель с кодом id.
     */
    @Override
    @Transactional(readOnly = true)
    public T get(Long id) throws BadRequestException {
        T model = getDao().get(id);
        if (model == null) {
            throw new BadRequestException("Can't find model by id " + id + "!");
        }
        return getDao().get(id);
    }

    /**
     * Получение всех моделей из базы данных. Режим только для чтения.
     *
     * @return Объект типа List - список всех моделей.
     */
    @Override
    @Transactional(readOnly = true)
    public List<T> getAll() {
        return getDao().getAll();
    }

    /**
     * Удаление модели из базы данных.
     *
     * @param model Модель для удаления.
     */
    @Override
    @Transactional
    public void remove(T model) {
        getDao().remove(model);
    }

    /**
     * Удаление модели из базы данных по уникальному коду.
     *
     * @param id Уникальный код модели.
     */
    @Override
    @Transactional
    public void remove(Long id) {
        getDao().remove(id);
    }

    /**
     * Удаление коллекции моделей из базы данных.
     *
     * @param models Коллекция моделей для удаления.
     */
    @Override
    @Transactional
    public void remove(List<T> models) {
        getDao().remove(models);
    }

    /**
     * Удаление всех моделей из базы данных.
     */
    @Override
    @Transactional
    public void removeAll() {
        getDao().removeAll();
    }

    /**
     * Абстрактный метод, возвращает объект интерфейса {@link DAO}
     * для работы других методов данного класса.
     *
     * @return Объект интерфейса {@link DAO}.
     */
    public abstract DAO<T> getDao();
}
