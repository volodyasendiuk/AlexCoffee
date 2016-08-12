package ua.com.alexcoffee.dao;

import ua.com.alexcoffee.model.Order;

/**
 * Интерфейс описывает набор методов для работы объектов класса
 * {@link Order} с базой данных.
 * Расширяет интерфейс {@link MainDAO}.
 *
 * @author Yurii Salimov
 * @see MainDAO
 * @see ua.com.alexcoffee.dao.impl.OrderDAOImpl
 * @see Order
 */
public interface OrderDAO extends MainDAO<Order> {
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
