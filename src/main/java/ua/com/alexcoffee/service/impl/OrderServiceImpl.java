package ua.com.alexcoffee.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alexcoffee.dao.OrderDAO;
import ua.com.alexcoffee.model.Order;
import ua.com.alexcoffee.exception.BadRequestException;
import ua.com.alexcoffee.exception.WrongInformationException;
import ua.com.alexcoffee.service.OrderService;

@Service
public class OrderServiceImpl extends ItemServiceImpl<Order> implements OrderService {

    @Autowired
    private OrderDAO dao;

    @Override
    @Transactional(readOnly = true)
    public Order get(String number) throws WrongInformationException, BadRequestException {
        if (number.isEmpty()) {
            throw new WrongInformationException("No order number!");
        }
        Order order = dao.get(number);
        if (order == null) {
            throw new BadRequestException("Can't find order by number " + number + "!");
        }
        return order;
    }

    @Override
    @Transactional
    public void remove(String number) throws WrongInformationException {
        if (number.isEmpty()) {
            throw new WrongInformationException("No order number!");
        }
        dao.remove(number);
    }

    @Override
    public OrderDAO getDao() {
        return dao;
    }
}
