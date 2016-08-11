package ua.com.alexcoffee.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.alexcoffee.dao.SalePositionDAO;
import ua.com.alexcoffee.exception.BadRequestException;
import ua.com.alexcoffee.model.SalePosition;
import ua.com.alexcoffee.service.SalePositionService;

/**
 * Класс сервисного слоя реализует методы доступа объектов класса {@link SalePosition}
 * в базе данных интерфейса {@link SalePositionService}, наследует родительский
 * абстрактній класс {@link AbstractServiceImpl}, в котором реализованы
 * основные методы.
 * Класс помечан аннотацией @Service - аннотация обьявляющая, что этот класс представляет
 * собой сервис – компонент сервис-слоя. Сервис является подтипом класса @Component.
 * Использование данной аннотации позволит искать бины-сервисы автоматически.
 * Методы класса помечены аннотацией @Transactional - перед исполнением метода помеченного
 * данной аннотацией начинается транзакция, после выполнения метода транзакция коммитится,
 * при выбрасывании RuntimeException откатывается.
 *
 * @author Yurii Salimov
 * @see AbstractServiceImpl
 * @see SalePositionService
 * @see SalePosition
 * @see SalePositionDAO
 */
@Service
public class SalePositionServiceImpl extends AbstractServiceImpl<SalePosition> implements SalePositionService {
    /**
     * Объект интерфейса для работы с базой данных.
     * Поле помечано аннотацией @Autowired, которая позволит Spring
     * автоматически инициализировать объект.
     */
    @Autowired
    private SalePositionDAO dao;

    /**
     * Возвращает объект DAO для работы основных методов доступа к базе данных,
     * реализованых в родительском классе {@link AbstractServiceImpl}.
     *
     * @return Объект класса {@link SalePositionDAO} - объект DAO.
     * @throws BadRequestException Бросает исключение, если объект DAO равный null.
     */
    @Override
    public SalePositionDAO getDao() throws BadRequestException {
        if (dao == null) {
            throw new BadRequestException("Can't find sale position DAO!");
        }
        return dao;
    }
}
