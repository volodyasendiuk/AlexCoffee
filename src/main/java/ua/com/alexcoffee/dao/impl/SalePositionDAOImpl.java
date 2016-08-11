package ua.com.alexcoffee.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.com.alexcoffee.dao.SalePositionDAO;
import ua.com.alexcoffee.repository.SalePositionRepository;
import ua.com.alexcoffee.model.SalePosition;

/**
 * Класс реализует методы доступа объектов класса {@link SalePosition}
 * в базе данных интерфейса {@link SalePositionDAO}, наследует родительский
 * абстрактній класс {@link AbstractDAOImpl}, в котором реализованы
 * основные методы. Для работы методы используют объект-репозиторий
 * интерфейса {@link SalePositionRepository}.
 * Класс помечена аннотацией @Repository (наследник Spring'овой аннотации @Component).
 * Это позволяет Spring автоматически зарегестрировать компонент в своём контексте
 * для последующей инъекции.
 *
 * @author Yurii Salimov
 * @see AbstractDAOImpl
 * @see SalePositionDAO
 * @see SalePosition
 * @see SalePositionRepository
 */
@Repository
public class SalePositionDAOImpl extends AbstractDAOImpl<SalePosition> implements SalePositionDAO {
    /**
     * Объект репозитория для работы с БД.
     * Поле помечано аннотацией @Autowired, которая позволит Spring
     * автоматически инициализировать репозиторий.
     */
    @Autowired
    private SalePositionRepository salePositionRepository;

    /**
     * Возвращает объект репозитория для работы основных методов доступа к базе данных.
     *
     * @return Объект класса {@link SalePositionRepository} - репозиторий.
     */
    @Override
    public SalePositionRepository getRepository() {
        return salePositionRepository;
    }
}
