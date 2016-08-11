package ua.com.alexcoffee.repository;

import ua.com.alexcoffee.model.Photo;

/**
 * Репозиторий для объектов класса {@link Photo}, предоставляющий
 * набор методов JPA для работы с БД. Наследует интерфейс {@link ItemRepository}.
 *
 * @author Yurii Salimov
 * @see ItemRepository
 * @see Photo
 */
public interface PhotoRepository extends ItemRepository<Photo, Long> {
    /**
     * Возвращает объект-изображение из базы даных, у которого совпадает уникальное
     * название с значением входящего параметра.
     *
     * @param title Название объекта-изображения для возврата.
     * @return Объект класса {@link Photo} - объекта-изображение.
     */
    Photo findByTitle(String title);
}
