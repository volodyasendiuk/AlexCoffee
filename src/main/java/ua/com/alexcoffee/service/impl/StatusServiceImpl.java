package ua.com.alexcoffee.service.impl;

import ua.com.alexcoffee.dao.StatusDAO;
import ua.com.alexcoffee.model.Status;
import ua.com.alexcoffee.enums.StatusEnum;
import ua.com.alexcoffee.exception.BadRequestException;
import ua.com.alexcoffee.exception.DuplicateException;
import ua.com.alexcoffee.exception.WrongInformationException;
import ua.com.alexcoffee.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Класс сервисного слоя реализует методы доступа объектов класса {@link Status}
 * в базе данных интерфейса {@link StatusService}, наследует родительский
 * абстрактній класс {@link AbstractServiceImpl}, в котором реализованы
 * основные методы.
 * Класс помечан аннотацией @Service - аннотация обьявляющая, что этот класс представляет
 * собой сервис – компонент сервис-слоя. Сервис является подтипом класса @Component.
 * Использование данной аннотации позволит искать бины-сервисы автоматически.
 * Методы класса помечены аннотацией @Transactional - перед исполнением метода помеченного
 * данной аннотацией начинается транзакция, после выполнения метода транзакция коммитится,
 * при выбрасывании RuntimeException откатывается.
 *
 * @author Yurii Salimov
 * @see AbstractServiceImpl
 * @see StatusService
 * @see Status
 * @see StatusDAO
 */
@Service
public class StatusServiceImpl extends AbstractServiceImpl<Status> implements StatusService {
    /**
     * Объект интерфейса для работы с базой данных.
     * Поле помечано аннотацией @Autowired, которая позволит Spring
     * автоматически инициализировать объект.
     */
    @Autowired
    private StatusDAO dao;

    /**
     * Добавляет статус по названию, которое может принимать
     * одно из значений перечисления {@link StatusEnum}.
     *
     * @param title Название статуса.
     * @throws WrongInformationException Бросает исключение, если пустой входной параметр title.
     * @throws DuplicateException        Бросает исключение, если в БД уже есть такой объект.
     */
    @Override
    @Transactional
    public void add(StatusEnum title) throws WrongInformationException, DuplicateException {
        if (title == null) {
            throw new WrongInformationException("No status title!");
        }
        if (dao.get(title) != null) {
            throw new DuplicateException("Duplicate status with title  " + title + "!");
        }
        dao.add(new Status(title));
    }

    /**
     * Возвращает статус по названию, которое может принимать
     * одно из значений перечисления {@link StatusEnum}.
     *
     * @param title Название статуса.
     * @return Объект класса {@link Status} - статус с названием title.
     * @throws WrongInformationException Бросает исключение, если пустой входной параметр title.
     * @throws BadRequestException       Бросает исключение, если не найден статус с входящим параметром title.
     */
    @Override
    @Transactional(readOnly = true)
    public Status get(StatusEnum title) throws WrongInformationException, BadRequestException {
        if (title == null) {
            throw new WrongInformationException("No status title!");
        }
        Status status = dao.get(title);
        if (status == null) {
            throw new BadRequestException("Can't find status by title " + title + "!");
        }
        return status;
    }

    /**
     * Возвращает статус по-умолчанию.
     *
     * @return Объект класса {@link Status} - статус по-умолчание.
     * @throws BadRequestException Бросает исключение, если не найден статус по-умолчание.
     */
    @Override
    @Transactional(readOnly = true)
    public Status getDefault() throws BadRequestException {
        Status status = dao.getDefault();
        if (status == null) {
            throw new BadRequestException("Can't find default status!");
        }
        return status;
    }

    /**
     * Удаляет статус по названию, которое может принимать
     * одно из значений перечисления {@link StatusEnum}.
     *
     * @param title Название статуса для удаления.
     * @throws WrongInformationException Бросает исключение, если пустой входной параметр title.
     */
    @Override
    @Transactional
    public void remove(StatusEnum title) throws WrongInformationException {
        if (title == null) {
            throw new WrongInformationException("No status title!");
        }
        dao.remove(title);
    }

    /**
     * Возвращает объект DAO для работы основных методов доступа к базе данных,
     * реализованых в родительском классе {@link AbstractServiceImpl}.
     *
     * @return Объект класса {@link StatusDAO} - объект DAO.
     * @throws BadRequestException Бросает исключение, если объект DAO равный null.
     */
    @Override
    public StatusDAO getDao() throws BadRequestException {
        if (dao == null) {
            throw new BadRequestException("Can't find status DAO!");
        }
        return dao;
    }
}
