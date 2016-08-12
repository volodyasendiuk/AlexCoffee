package ua.com.alexcoffee.dao.impl;

import ua.com.alexcoffee.dao.PhotoDAO;
import ua.com.alexcoffee.repository.PhotoRepository;
import ua.com.alexcoffee.model.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Класс реализует методы доступа объектов класса {@link Photo}
 * в базе данных интерфейса {@link PhotoDAO}, наследует родительский
 * абстрактній класс {@link MainDAOImpl}, в котором реализованы
 * основные методы. Для работы методы используют объект-репозиторий
 * интерфейса {@link PhotoRepository}.
 * Класс помечена аннотацией @Repository (наследник Spring'овой аннотации @Component).
 * Это позволяет Spring автоматически зарегестрировать компонент в своём контексте
 * для последующей инъекции.
 *
 * @author Yurii Salimov
 * @see MainDAOImpl
 * @see PhotoDAO
 * @see Photo
 * @see PhotoRepository
 */
@Repository
public class PhotoDAOImpl extends MainDAOImpl<Photo> implements PhotoDAO {
    /**
     * Объект репозитория {@link PhotoRepository} для работы изображений с базой данных.
     */
    private PhotoRepository repository;

    /**
     * Конструктор для инициализации основных переменных.
     * Помечаный аннотацией @Autowired, которая позволит Spring
     * автоматически инициализировать объект.
     *
     * @param repository Объект репозитория {@link PhotoRepository} для работы изображений с базой данных.
     */
    @Autowired
    public PhotoDAOImpl(PhotoRepository repository) {
        super(repository);
        this.repository = repository;
    }

    /**
     * Возвращает объект-изображение из базы даных, у которого совпадает уникальное
     * название с значением входящего параметра.
     *
     * @param title Название объекта-изображения для возврата.
     * @return Объект класса {@link Photo} - объекта-изображение.
     */
    @Override
    public Photo get(String title) {
        return repository.findByTitle(title);
    }

    /**
     * Удаляет объект-изображение из базы даных, у которого совпадает уникальное
     * название с значением входящего параметра.
     *
     * @param title Название объекта-изображения для удаления.
     */
    @Override
    public void delete(String title) {
        Photo photo = repository.findByTitle(title);
        repository.delete(photo);
    }
}
