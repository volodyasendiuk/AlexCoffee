package ua.com.alexcoffee.service.impl;

import org.springframework.transaction.annotation.Transactional;
import ua.com.alexcoffee.dao.DataDAO;
import ua.com.alexcoffee.exception.BadRequestException;
import ua.com.alexcoffee.exception.WrongInformationException;
import ua.com.alexcoffee.model.Model;
import ua.com.alexcoffee.service.MainService;

import java.util.List;

/**
 * Класс сервисного слоя, который реализует основные методы доступа к
 * объектам наследникам родительского класса {@link Model}, описаные в
 * интерфейсе {@link MainService}. Класс должен наследоваться дочерними классами,
 * которые будут описывать поведение объектов-наследников родительского класса {@link Model}.
 * Для работы методы используют объект DAO интерфейса {@link DataDAO}, возвращаемый абстрактным
 * методом dao, реализацию которого каждый наследник берет на себя.
 * Методы класса помечены аннотацией @Transactional - перед исполнением метода помеченного
 * данной аннотацией начинается транзакция, после выполнения метода транзакция коммитится,
 * при выбрасывании RuntimeException откатывается.
 *
 * @param <T> Класс-наследник класса {@link Model}.
 * @author Yurii Salimov
 * @see Model
 * @see MainService
 * @see CategoryServiceImpl
 * @see OrderServiceImpl
 * @see PhotoServiceImpl
 * @see ProductServiceImpl
 * @see RoleServiceImpl
 * @see SalePositionServiceImpl
 * @see MainServiceImpl
 * @see UserServiceImpl
 * @see DataDAO
 */
public abstract class MainServiceImpl<T extends Model> implements MainService<T> {
    /**
     * Реализация интерфейса {@link DataDAO} для работы моделей с базой данных.
     */
    private DataDAO<T> dao;

    /**
     * Конструктор для инициализации основных переменных сервиса.
     *
     * @param dao Реализация интерфейса {@link DataDAO} для работы моделей с базой данных.
     */
    public MainServiceImpl(DataDAO<T> dao) {
        super();
        this.dao = dao;
    }

    /**
     * Добавление модели в базу данных.
     *
     * @param model Модель для добавления.
     */
    @Override
    @Transactional
    public void add(T model) {
        if (model != null) {
            dao.add(model);
        }
    }

    /**
     * Добавление коллекции моделей в базу данных.
     *
     * @param models Коллекция моделей для добавления.
     */
    @Override
    @Transactional
    public void add(List<T> models) {
        if (models != null && !models.isEmpty()) {
            dao.add(models);
        }
    }

    /**
     * Обновление существующей модели в базе данных.
     *
     * @param model Обновленная модель.
     */
    @Override
    @Transactional
    public void update(T model) {
        if (model != null) {
            dao.update(model);
        }
    }

    /**
     * Получение модели по уникальному коду id в базе данных. Режим только для чтения.
     *
     * @param id Уникальный код модели.
     * @return Объект класса {@link Model} -  модель с кодом id.
     */
    @Override
    @Transactional(readOnly = true)
    public T get(Long id) throws WrongInformationException, BadRequestException {
        if (id == null) {
            throw new WrongInformationException("No model id!");
        }

        T model = dao.get(id);
        if (model == null) {
            throw new BadRequestException("Can't find model by id " + id + "!");
        }
        return dao.get(id);
    }

    /**
     * Получение всех моделей из базы данных. Режим только для чтения.
     *
     * @return Объект типа {@link List} - список всех моделей.
     */
    @Override
    @Transactional(readOnly = true)
    public List<T> getAll() {
        return dao.getAll();
    }

    /**
     * Удаление модели из базы данных.
     *
     * @param model Модель для удаления.
     */
    @Override
    @Transactional
    public void remove(T model) {
        if (model != null) {
            dao.remove(model);
        }
    }

    /**
     * Удаление модели из базы данных по уникальному коду.
     *
     * @param id Уникальный код модели.
     */
    @Override
    @Transactional
    public void remove(Long id) throws WrongInformationException {
        if (id == null) {
            throw new WrongInformationException("No model id!");
        }
        dao.remove(id);
    }

    /**
     * Удаление коллекции моделей из базы данных.
     *
     * @param models Коллекция моделей для удаления.
     */
    @Override
    @Transactional
    public void remove(List<T> models) {
        if (models != null && !models.isEmpty()) {
            dao.remove(models);
        }
    }

    /**
     * Удаление всех моделей из базы данных.
     */
    @Override
    @Transactional
    public void removeAll() {
        dao.removeAll();
    }
}
