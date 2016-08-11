package ua.com.alexcoffee.dao;

import ua.com.alexcoffee.model.SalePosition;

/**
 * Интерфейс описывает набор методов для работы объектов класса
 * {@link SalePosition} с базой данных.
 * Наследует интерфейс {@link DAO}, своих дополнительных методв
 * не имеет.
 *
 * @author Yurii Salimov
 * @see DAO
 * @see ua.com.alexcoffee.dao.impl.SalePositionDAOImpl
 * @see SalePosition
 * @see ua.com.alexcoffee.model.Order
 * @see ua.com.alexcoffee.model.ShoppingCart
 */
public interface SalePositionDAO extends DAO<SalePosition> {
}
