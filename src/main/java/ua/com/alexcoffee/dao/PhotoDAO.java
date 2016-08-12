package ua.com.alexcoffee.dao;

import ua.com.alexcoffee.model.Photo;

/**
 * Интерфейс описывает набор методов для работы объектов класса
 * {@link Photo} с базой данных.
 * Расширяет интерфейс {@link MainDAO}.
 *
 * @author Yurii Salimov
 * @see MainDAO
 * @see ua.com.alexcoffee.dao.impl.PhotoDAOImpl
 * @see Photo
 */
public interface PhotoDAO extends MainDAO<Photo> {
    /**
     * Возвращает объект-изображение из базы даных, у которого совпадает уникальное
     * название с значением входящего параметра.
     *
     * @param title Название объекта-изображения для возврата.
     * @return Объект класса {@link Photo} - объекта-изображение.
     */
    Photo get(String title);

    /**
     * Удаляет объект-изображение из базы даных, у которого совпадает уникальное
     * название с значением входящего параметра.
     *
     * @param title Название объекта-изображения для удаления.
     */
    void delete(String title);
}
