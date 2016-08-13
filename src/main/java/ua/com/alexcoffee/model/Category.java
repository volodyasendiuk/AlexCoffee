package ua.com.alexcoffee.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс описывает сущность "Категория товаров", наследует класс {@link Model}.
 * Категория описывает набор товаров, которые имеют общие характеристики.
 * Аннотация @Entity говорит о том что объекты этого класса будет обрабатываться hibernate.
 * Аннотация @Table(name = "categories") указывает на таблицу "categories", в которой будут храниться объекты.
 *
 * @author Yurii Salimov
 * @see Product
 * @see Photo
 */
@Entity
@Table(name = "categories")
public class Category extends Model {
    /**
     * Номер версии класса необходимый для десериализации и сериализации.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Название категории. Значение поля сохраняется в колонке "title". Не может быть null.
     */
    @Column(name = "title", nullable = false)
    private String title;

    /**
     * URL категории. Значение поля сохраняется в колонке "url". Не может быть null.
     */
    @Column(name = "url", nullable = false)
    private String url;

    /**
     * Описание категории. Значение поля сохраняется в колонке "description".
     */
    @Column(name = "description")
    private String description;

    /**
     * Изображение категории.
     * Значение поля (id объекта photo) сохраняется в колонке "photo_id".
     * Между объектами классов {@link Category}
     * и {@link Photo} связь один-к-одному, а именно каждая
     * запись в одной таблице напрямую связана с отдельной записью в другой таблице.
     * Выборка объекта photo до первого доступа нему, при первом доступе к текущему объекту.
     * Сущности связаны полностью каскадным обновлением записей в базе данных.
     */
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "photo_id", referencedColumnName = "id")
    private Photo photo;

    /**
     * Список товаров, которые относятся к данной категории.
     * К текущей категории можно добраться через поле "category"
     * в объекте класса {@link Category}.
     * Выборка объектов products при первом доступе к нему.
     * Сущности связаны полностью каскадным обновлением записей в базе данных.
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category", cascade = CascadeType.ALL)
    private List<Product> products = new ArrayList<>();

    /**
     * Конструктр без параметров.
     */
    public Category() {
        super();
    }

    /**
     * Конструктор для инициализации основных переменных категории.
     *
     * @param title       Название категории.
     * @param url         URL категории.
     * @param description Описание категории.
     * @param photo       Изображение категории.
     */
    public Category(String title, String url, String description, Photo photo) {
        super();
        this.title = title;
        this.url = url;
        this.description = description;
        this.photo = photo;
    }

    /**
     * Возвращает описание категории.
     * Переопределенный метод родительского класса {@link Object}.
     *
     * @return Значение типа {@link String} - строка описание категории (название, URL, описание).
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nTitle: ").append(title)
                .append("\nUrl: ").append(url)
                .append("\nDiscription: ").append(description);
        return sb.toString();
    }

    /**
     * Генерирует строку для конечного сравнения категорий в методе equals() родительского класса..
     * Переопределенный метод родительского класса {@link Model}.
     *
     * @return Значение типа {@link String} - название + URL категории.
     */
    @Override
    public String toEquals() {
        if (title.isEmpty() || url.isEmpty()) {
            return super.toString();
        } else {
            return getTitle() + getUrl();
        }
    }

    /**
     * Инициализация полей категории.
     *
     * @param title       Название категории.
     * @param url         URL категории.
     * @param description Описание категории.
     * @param photo       Изображение категории.
     */
    public void initializer(String title, String url, String description, Photo photo) {
        setTitle(title);
        setUrl(url);
        setDescription(description);
        setPhoto(photo);
    }

    /**
     * Метод добавляет товар в список категории products.
     *
     * @param product Товар, который будет добавлен в текущую категорию.
     */
    public void addProduct(Product product) {
        products.add(product);
    }

    /**
     * Метод добавляет список товаров в список категории products.
     *
     * @param products Список товаров, которые будут добавлены в текущую категорию.
     */
    public void addProducts(List<Product> products) {
        this.products.addAll(products);
    }

    /**
     * Метод удаляет товар из списка products.
     *
     * @param product Товар, который будет удален из данной категории.
     */
    public void removeProduct(Product product) {
        products.remove(product);
    }

    /**
     * Метод удаляет список товаров из списка products.
     *
     * @param products Список товаров, которые будут удалены из данной категории.
     */
    public void removeProducts(List<Product> products) {
        this.products.removeAll(products);
    }

    /**
     * Метод очищает список товаров products.
     */
    public void clearProducts() {
        products.clear();
    }

    /**
     * Метод конвертирует список товаров products данной категории
     * в список только для чтений и возвращает его.
     *
     * @return Объект типа {@link List} - список товаров только для чтения или пустой список.
     */
    public List<Product> getProducts() {
        return getUnmodifiableList(products);
    }

    /**
     * Метод устанавливает список товаров products,
     * которые будут относиться к данной категории.
     *
     * @param products Список товаров .
     */
    public void setProducts(List<Product> products) {
        this.products = products;
    }

    /**
     * Метод возвращает название категории.
     *
     * @return Значение типа {@link String} - название категории.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Метод устанавливает название категории.
     *
     * @param title Название категории.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Метод возвращает URL категории.
     *
     * @return Значение типа {@link String} - URL категории.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Метод устанавливает URL категории.
     *
     * @param url URL категории.
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Метод возвращает описание категории.
     *
     * @return Значение типа {@link String} - описание категории.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Метод устанавливает описание категории.
     *
     * @param description Описание категории.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Метод возвращает изображение категории.
     *
     * @return Объект класса {@link Photo} - изображение категории.
     */
    public Photo getPhoto() {
        return photo;
    }

    /**
     * Метод устанавливает изображение категории.
     *
     * @param photo Изображение категории.
     */
    public void setPhoto(Photo photo) {
        this.photo = photo;
    }
}
