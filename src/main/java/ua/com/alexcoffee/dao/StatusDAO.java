package ua.com.alexcoffee.dao;

import ua.com.alexcoffee.enums.StatusEnum;
import ua.com.alexcoffee.model.Status;

/**
 * Интерфейс описывает набор методов для работы объектов класса
 * {@link Status} с базой данных.
 * Расширяет интерфейс {@link DAO}.
 *
 * @author Yurii Salimov
 * @see DAO
 * @see ua.com.alexcoffee.dao.impl.StatusDAOImpl
 * @see Status
 * @see ua.com.alexcoffee.model.Order
 */
public interface StatusDAO extends DAO<Status> {
    /**
     * Добавляет статус в базу даных по названию, которое может принимать
     * одно из значений перечисления {@link StatusEnum}.
     *
     * @param title Название статуса.
     */
    void add(StatusEnum title);

    /**
     * Возвращает статус из базы даных по названию, которое может принимать
     * одно из значений перечисления {@link StatusEnum}.
     *
     * @param title Название роли.
     * @return Объект класса {@link Status} - статус с уникальным названием.
     */
    Status get(StatusEnum title);

    /**
     * Возвращает из базы даных статус по-умолчанию.
     *
     * @return Объект класса {@link Status} - статус по-умолчание.
     */
    Status getDefault();

    /**
     * Удаляет статус из базы даных по названию, которое может принимать
     * одно из значений перечисления {@link StatusEnum}.
     *
     * @param title Название статуса.
     */
    void remove(StatusEnum title);
}
