package ua.com.alexcoffee.dao.repository;

import ua.com.alexcoffee.entity.Order;

public interface OrderRepository extends ItemRepository<Order, Long> {

    Order findByNumber(String number);

    void deleteByNumber(String number);
}
