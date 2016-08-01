package ua.com.alexcoffee.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.com.alexcoffee.dao.OrderDAO;
import ua.com.alexcoffee.dao.repository.OrderRepository;
import ua.com.alexcoffee.entity.Order;

@Repository
public class OrderDAOImpl extends DaoAbstractImpl<Order> implements OrderDAO {

    @Autowired
    private OrderRepository repository;

    @Override
    public void remove(String number) {
        repository.deleteByNumber(number);
    }

    @Override
    public Order get(String number) {
        return repository.findByNumber(number);
    }

    @Override
    public OrderRepository getRepository() {
        return repository;
    }
}
