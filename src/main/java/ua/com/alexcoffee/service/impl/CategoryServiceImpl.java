package ua.com.alexcoffee.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alexcoffee.dao.CategoryDAO;
import ua.com.alexcoffee.model.Category;
import ua.com.alexcoffee.exception.BadRequestException;
import ua.com.alexcoffee.exception.WrongInformationException;
import ua.com.alexcoffee.service.CategoryService;

/**
 * Класс сервисного слоя реализует методы доступа объектов класса {@link Category}
 * в базе данных интерфейса {@link CategoryService}, наследует родительский
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
 * @see CategoryService
 * @see Category
 * @see CategoryDAO
 */
@Service
public class CategoryServiceImpl extends AbstractServiceImpl<Category> implements CategoryService {
    /**
     * Объект интерфейса для работы с базой данных.
     * Поле помечано аннотацией @Autowired, которая позволит Spring
     * автоматически инициализировать объект.
     */
    @Autowired
    private CategoryDAO dao;

    /**
     * Возвращает категорию из базы данных, у которой совпадает параметр url.
     * Режим только для чтения.
     *
     * @param url URL категории для возврата.
     * @return Объект класса {@link Category} - категория с уникальным url полем.
     * @throws WrongInformationException Бросает исключение, если пустой входной параметр url.
     * @throws BadRequestException       Бросает исключение, если не найдена категория с входящим параметром url.
     */
    @Override
    @Transactional(readOnly = true)
    public Category get(String url) throws WrongInformationException, BadRequestException {
        if (url.isEmpty()) {
            throw new WrongInformationException("No category URL!");
        }
        Category category = dao.get(url);
        if (category == null) {
            throw new BadRequestException("Can't find category by url " + url + "!");
        }
        return category;
    }

    /**
     * Удаляет категрию из базы даных, у которого совпадает поле url.
     *
     * @param url URL категории для удаления.
     * @throws WrongInformationException Бросает исключение, если пустой входной параметр url.
     */
    @Override
    @Transactional
    public void remove(String url) throws WrongInformationException {
        if (url.isEmpty()) {
            throw new WrongInformationException("No category URL!");
        }
        dao.remove(url);
    }

    /**
     * Возвращает объект DAO для работы основных методов доступа к базе данных,
     * реализованых в родительском классе {@link AbstractServiceImpl}.
     *
     * @return Объект класса {@link CategoryDAO} - объект DAO.
     * @throws BadRequestException Бросает исключение, если объект DAO равный null.
     */
    @Override
    public CategoryDAO getDao() throws BadRequestException {
        if (dao == null) {
            throw new BadRequestException("Can't find category DAO!");
        }
        return dao;
    }
}
