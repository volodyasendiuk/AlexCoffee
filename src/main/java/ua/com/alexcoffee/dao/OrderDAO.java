package ua.com.alexcoffee.dao;

import ua.com.alexcoffee.model.Order;

/**
 * Интерфейс описывает набор методов для работы объектов класса
 * {@link Order} с базой данных.
 * Расширяет интерфейс {@link DAO}.
 *
 * @author Yurii Salimov
 * @see DAO
 * @see ua.com.alexcoffee.dao.impl.AbstractDAOImpl
 * @see Order
 */
public interface OrderDAO extends DAO<Order> {
    /**
     * Возвращает заказ из базы даных, у которого совпадает уникальный номером
     * с значением входящего параметра.
     *
     * @param number Номер заказа для возврата.
     * @return Объект класса {@link Order} - заказ с уникальным номером
     * для возвращения.
     */
    Order get(String number);

    /**
     * Удаляет заказ из базы даных, у которого совпадает уникальный номером
     * с значением входящего параметра.
     *
     * @param number Номер заказа для удаление.
     */
    void remove(String number);
}
