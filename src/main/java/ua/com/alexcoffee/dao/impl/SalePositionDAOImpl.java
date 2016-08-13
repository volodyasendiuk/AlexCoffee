package ua.com.alexcoffee.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.com.alexcoffee.dao.SalePositionDAO;
import ua.com.alexcoffee.repository.SalePositionRepository;
import ua.com.alexcoffee.model.SalePosition;

/**
 * Класс реализует методы доступа объектов класса {@link SalePosition}
 * в базе данных интерфейса {@link SalePositionDAO}, наследует родительский
 * абстрактній класс {@link MainDAOImpl}, в котором реализованы
 * основные методы. Для работы методы используют объект-репозиторий
 * интерфейса {@link SalePositionRepository}.
 * Класс помечена аннотацией @Repository (наследник Spring'овой аннотации @Component).
 * Это позволяет Spring автоматически зарегестрировать компонент в своём контексте
 * для последующей инъекции.
 *
 * @author Yurii Salimov
 * @see MainDAOImpl
 * @see SalePositionDAO
 * @see SalePosition
 * @see SalePositionRepository
 */
@Repository
public class SalePositionDAOImpl extends MainDAOImpl<SalePosition> implements SalePositionDAO {
    /**
     * Конструктор для инициализации основных переменных.
     * Помечаный аннотацией @Autowired, которая позволит Spring
     * автоматически инициализировать объект.
     *
     * @param repository Реализация репозитория {@link SalePositionRepository}
     *                   для работы торговых позиций с базой данных.
     */
    @Autowired
    public SalePositionDAOImpl(SalePositionRepository repository) {
        super(repository);
    }
}
