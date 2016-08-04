package ua.com.alexcoffee.dao.repository;

import ua.com.alexcoffee.model.Order;

public interface OrderRepository extends ItemRepository<Order, Long> {

    Order findByNumber(String number);

    void deleteByNumber(String number);
}
