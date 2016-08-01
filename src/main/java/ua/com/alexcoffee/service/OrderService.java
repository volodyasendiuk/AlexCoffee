package ua.com.alexcoffee.service;

import ua.com.alexcoffee.entity.Order;

public interface OrderService extends ItemService<Order> {

    Order get(String number);

    void remove(String number);

    double getPriceOfProducts(Order order);


}
