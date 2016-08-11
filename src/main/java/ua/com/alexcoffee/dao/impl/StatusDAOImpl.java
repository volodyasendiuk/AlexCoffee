package ua.com.alexcoffee.dao.impl;

import ua.com.alexcoffee.dao.StatusDAO;
import ua.com.alexcoffee.repository.StatusRepository;
import ua.com.alexcoffee.model.Status;
import ua.com.alexcoffee.enums.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Класс реализует методы доступа объектов класса {@link Status}
 * в базе данных интерфейса {@link StatusDAO}, наследует родительский
 * абстрактній класс {@link AbstractDAOImpl}, в котором реализованы
 * основные методы. Для работы методы используют объект-репозиторий
 * интерфейса {@link StatusRepository}.
 * Класс помечена аннотацией @Repository (наследник Spring'овой аннотации @Component).
 * Это позволяет Spring автоматически зарегестрировать компонент в своём контексте
 * для последующей инъекции.
 *
 * @author Yurii Salimov
 * @see AbstractDAOImpl
 * @see StatusDAO
 * @see Status
 * @see StatusRepository
 */
@Repository
public class StatusDAOImpl extends AbstractDAOImpl<Status> implements StatusDAO {
    /**
     * Объект репозитория для работы с БД.
     * Поле помечано аннотацией @Autowired, которая позволит Spring
     * автоматически инициализировать репозиторий.
     */
    @Autowired
    private StatusRepository repository;

    /**
     * Добавляет статус в базу даных по названию, которое может принимать
     * одно из значений перечисления {@link StatusEnum}.
     *
     * @param title Название статуса.
     */
    @Override
    public void add(StatusEnum title) {
        repository.save(new Status(title));
    }

    /**
     * Возвращает статус из базы даных по названию, которое может принимать
     * одно из значений перечисления {@link StatusEnum}.
     *
     * @param title Название роли.
     * @return Объект класса {@link Status} - статус с уникальным названием.
     */
    @Override
    public Status get(StatusEnum title) {
        return repository.findByTitle(title);
    }

    /**
     * Возвращает из базы даных статус по-умолчанию.
     *
     * @return Объект класса {@link Status} - статус по-умолчание.
     */
    @Override
    public Status getDefault() {
        return repository.findOne((long) 1);
    }

    /**
     * Удаляет статус из базы даных по названию, которое может принимать
     * одно из значений перечисления {@link StatusEnum}.
     *
     * @param title Название статуса.
     */
    @Override
    public void remove(StatusEnum title) {
        repository.deleteByTitle(title);
    }

    /**
     * Возвращает объект репозитория для работы основных методов доступа к базе данных.
     *
     * @return Объект класса {@link StatusRepository} - репозиторий.
     */
    @Override
    public StatusRepository getRepository() {
        return repository;
    }
}
