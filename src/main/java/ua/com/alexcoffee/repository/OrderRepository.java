package ua.com.alexcoffee.repository;

import ua.com.alexcoffee.model.Order;

/**
 * Репозиторий для объектов класса {@link Order}, предоставляющий
 * набор методов JPA для работы с БД. Наследует интерфейс {@link ItemRepository}.
 *
 * @author Yurii Salimov
 * @see ItemRepository
 * @see Order
 */
public interface OrderRepository extends ItemRepository<Order, Long> {
    /**
     * Возвращает заказ из базы даных, у которого совпадает уникальный номером
     * с значением входящего параметра.
     *
     * @param number Номер заказа для возврата.
     * @return Объект класса {@link Order} - заказ с уникальным номером
     * для возвращения.
     */
    Order findByNumber(String number);

    /**
     * Удаляет заказ из базы даных, у которого совпадает уникальный номером
     * с значением входящего параметра.
     *
     * @param number Номер заказа для удаление.
     */
    void deleteByNumber(String number);
}
