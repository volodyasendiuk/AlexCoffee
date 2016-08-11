package ua.com.alexcoffee.service;

import ua.com.alexcoffee.model.Status;
import ua.com.alexcoffee.enums.StatusEnum;

/**
 * Интерфейс сервисного слоя, описывает набор методов для работы
 * с объектами класса {@link Status}. Расширяет интерфейс {@link AbstractService}.
 *
 * @author Yurii Salimov
 * @see Status
 * @see AbstractService
 * @see ua.com.alexcoffee.service.impl.StatusServiceImpl
 */
public interface StatusService extends AbstractService<Status> {
    /**
     * Добавляет статус по названию, которое может принимать
     * одно из значений перечисления {@link StatusEnum}.
     *
     * @param title Название статуса для добавления.
     */
    void add(StatusEnum title);

    /**
     * Возвращает статус по названию, которое может принимать
     * одно из значений перечисления {@link StatusEnum}.
     *
     * @param title Название статуса для возвращения.
     * @return Объект класса {@link Status} - статус с названием title.
     */
    Status get(StatusEnum title);

    /**
     * Возвращает статус по-умолчанию.
     *
     * @return Объект класса {@link Status} - статус по-умолчание.
     */
    Status getDefault();

    /**
     * Удаляет статус по названию, которое может принимать
     * одно из значений перечисления {@link StatusEnum}.
     *
     * @param title Название статуса для удаления..
     */
    void remove(StatusEnum title);

}
