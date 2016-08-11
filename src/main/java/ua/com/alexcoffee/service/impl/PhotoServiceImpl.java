package ua.com.alexcoffee.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ua.com.alexcoffee.dao.PhotoDAO;
import ua.com.alexcoffee.model.Photo;
import ua.com.alexcoffee.exception.BadRequestException;
import ua.com.alexcoffee.exception.WrongInformationException;
import ua.com.alexcoffee.service.PhotoService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Класс сервисного слоя реализует методы доступа объектов класса {@link Photo}
 * в базе данных интерфейса {@link PhotoService}, наследует родительский
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
 * @see PhotoService
 * @see Photo
 * @see PhotoDAO
 */
@Service
public class PhotoServiceImpl extends AbstractServiceImpl<Photo> implements PhotoService {
    /**
     * Путь для сохранения файлов в файловой системе.
     */
    private static final String PATH = "c:/Server/apache-tomcat-8.0.33/webapps/ROOT/resources/img/";

    /**
     * Объект интерфейса для работы с базой данных.
     * Поле помечано аннотацией @Autowired, которая позволит Spring
     * автоматически инициализировать объект.
     */
    @Autowired
    private PhotoDAO dao;

    /**
     * Возвращает объект-изображение из базы даных, у которого совпадает уникальное
     * название с значением входящего параметра. Режим только для чтения.
     *
     * @param title Название объекта-изображения для возврата.
     * @return Объект класса {@link Photo} - объекта-изображение.
     * @throws WrongInformationException Бросает исключение, если пустой входной параметр title.
     * @throws BadRequestException       Бросает исключение, если не найден объект-изображеие с входящим параметром title.
     */
    @Override
    @Transactional(readOnly = true)
    public Photo get(String title) throws WrongInformationException, BadRequestException {
        if (title.isEmpty()) {
            throw new WrongInformationException("No photo title!");
        }
        Photo photo = dao.get(title);
        if (photo == null) {
            throw new BadRequestException("Can't find photo by title " + title + "!");
        }
        return photo;
    }

    /**
     * Удаляет объект-изображение из базы даных, у которого совпадает уникальное
     * название с значением входящего параметра.
     *
     * @param title Название объекта-изображения для удаления.
     * @throws WrongInformationException Бросает исключение, если пустой входной параметр title.
     */
    @Override
    @Transactional
    public void remove(String title) throws WrongInformationException {
        if (title.isEmpty()) {
            throw new WrongInformationException("No photo title!");
        }
        dao.delete(title);
    }

    /**
     * Сохраняет файл в файловой системе.
     *
     * @param photo Файл для сохранения.
     */
    @Override
    @Transactional
    public void saveFile(MultipartFile photo) {
        if (photo != null && !photo.isEmpty()) {
            try (OutputStream stream = new FileOutputStream(PATH + photo.getOriginalFilename())) {
                stream.write(photo.getBytes());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Удаляет файл по url.
     *
     * @param url URL файла для удаления.
     */
    @Override
    @Transactional
    public void deleteFile(String url) {
        File file = new File(PATH + url);
        if (file.exists() && file.isFile()) {
            file.delete();
        }
    }

    /**
     * Возвращает объект DAO для работы основных методов доступа к базе данных,
     * реализованых в родительском классе {@link AbstractServiceImpl}.
     *
     * @return Объект класса {@link PhotoDAO} - объект DAO.
     * @throws BadRequestException Бросает исключение, если объект DAO равный null.
     */
    @Override
    public PhotoDAO getDao() throws BadRequestException {
        if (dao == null) {
            throw new BadRequestException("Can't find photo DAO!");
        }
        return dao;
    }
}
