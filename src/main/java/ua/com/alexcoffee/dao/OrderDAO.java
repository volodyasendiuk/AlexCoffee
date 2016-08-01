package ua.com.alexcoffee.dao;

import ua.com.alexcoffee.entity.Order;

public interface OrderDAO extends Dao<Order> {

    void remove(String number);

    Order get(String number);
}
