package ua.com.alexcoffee.service;

import ua.com.alexcoffee.model.Order;

public interface OrderService extends ItemService<Order> {

    Order get(String number);

    void remove(String number);
}
