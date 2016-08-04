package ua.com.alexcoffee.dao;

import ua.com.alexcoffee.model.Order;

public interface OrderDAO extends DAO<Order> {

    void remove(String number);

    Order get(String number);
}
