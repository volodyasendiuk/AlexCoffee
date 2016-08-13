package ua.com.alexcoffee.model;

import javax.persistence.*;

/**
 * Класс описывает сущность "Товар", наследует класс {@link Model}.
 * Аннотация @Entity говорит о том что объекты этого класса будет обрабатываться hibernate.
 * Аннотация @Table(name = "products") указывает на таблицу "products", в которой будут храниться объекты.
 *
 * @author Yurii Salimov
 * @see Category
 * @see Photo
 * @see SalePosition
 */
@Entity
@Table(name = "products")
public class Product extends Model {
    /**
     * Номер версии класса необходимый для десериализации и сериализации.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Набор вожможных для использованния символов по-умолчанию.
     */
    public static final char[] CODE_PATTERN = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};

    /**
     * Длина возвращаемой строки по-умолчанию {@value CODE_LENGTH}.
     */
    public static final int CODE_LENGTH = 5;

    /**
     * Артикль товара. Значение поля сохраняется в колонке "article". Не может быть null.
     */
    @Column(name = "article", nullable = false)
    private int article;

    /**
     * Название товара. Значение поля сохраняется в колонке "title". Не может быть null.
     */
    @Column(name = "title", nullable = false)
    private String title;

    /**
     * URL товара. Значение поля сохраняется в колонке "url". Не может быть null.
     */
    @Column(name = "url", nullable = false)
    private String url;

    /**
     * Параметры товара. Значение поля сохраняется в колонке "parameters".
     */
    @Column(name = "parameters")
    private String parameters;

    /**
     * Описание товара. Значение поля сохраняется в колонке "description".
     */
    @Column(name = "description")
    private String description;

    /**
     * Категория товара.
     * Значение поля (id объекта category) сохраняется в колонке "category_id". Не может быть null.
     * Между объектами классов {@link Product} и
     * {@link Category} связь многие-к-одному, а именно каждая
     * много заказов могут иметь одинаковый статус выполнения.
     * Выборка объекта category до первого доступа нему, при первом доступе к текущему объекту.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    private Category category;

    /**
     * Изображение товара.
     * Значение поля (id объекта photo) сохраняется в колонке "photo_id".
     * Между объектами классов {@link Category} и
     * {@link Photo} связь один-к-одному, а именно каждая
     * запись в одной таблице напрямую связана с отдельной записью в другой таблице.
     * Выборка объекта photo до первого доступа нему, при первом доступе к текущему объекту.
     * Сущности связаны полностью каскадным обновлением записей в базе данных.
     */
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "photo_id", referencedColumnName = "id")
    private Photo photo;

    /**
     * Цена товара. Значение поля сохраняется в колонке "description". Не может быть null.
     */
    @Column(name = "price", nullable = false)
    private double price;

    /**
     * Изображение товара.
     * К текущему товару можно добраться через поле "product"
     * в объекте класса {@link SalePosition}.
     * Выборка объекта salePosition при первом доступе к нему.
     * Сущность salePosition автоматически удаляется при удалении текущей.
     */
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.REMOVE)
    private SalePosition salePosition;

    /**
     * Конструктр без параметров.
     * Автоматически инициализируются поля article.
     */
    public Product() {
        super();
        newArticle();
    }

    /**
     * Конструктор для инициализации основных переменных товара.
     * Автоматически инициализируются поля article.
     *
     * @param title    Название товара.
     * @param url      URL товара.
     * @param category Категория товара.
     * @param photo    Изображение товара.
     * @param price    Цена товара.
     */
    public Product(String title, String url, Category category, Photo photo, double price) {
        super();
        this.title = title;
        this.url = url;
        this.category = category;
        this.photo = photo;
        this.price = price;

        newArticle();
    }

    /**
     * Возвращает описание товара.
     * Переопределенный метод родительского класса {@link Object}.
     *
     * @return Значение типа {@link String} - строка описание товара
     * (название, параметры, описание, название категории, цена).
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Title: ").append(title)
                .append("\nParameters: ").append(parameters)
                .append("\nDescription: ").append(description);

        if (category != null) {
            sb.append("\nCategory: ").append(category.getTitle());
        }

        sb.append("\nPrice = ").append(price).append(" UAH");
        return sb.toString();
    }

    /**
     * Генерирует строку для конечного сравнения товаров в методе equals() родительского класса.
     * Переопределенный метод родительского класса {@link Model}.
     *
     * @return Значение типа {@link String} - название + URL + цена товара.
     */
    @Override
    public String toEquals() {
        return getArticle() + getTitle() + getUrl() + getPrice();
    }

    /**
     * Инициализация полей товара.
     *
     * @param title       Название товара.
     * @param url         URL товара.
     * @param parameters  Параметры товара.
     * @param description Описание товара.
     * @param category    Категория товара.
     * @param photo       Изображение товара.
     * @param price       Цена товара.
     */
    public void initializer(String title, String url, String parameters,
                            String description, Category category, Photo photo, double price) {
        setTitle(title);
        setUrl(url);
        setParameters(parameters);
        setDescription(description);
        setCategory(category);
        setPhoto(photo);
        setPrice(price);
    }

    /**
     * Генерирует новый артикль товара.
     */
    public void newArticle() {
        article = Integer.parseInt(createRandomString(CODE_PATTERN, CODE_LENGTH));
    }

    /**
     * Возвращает артикль товара.
     *
     * @return Значение типа int - артикль товара.
     */
    public int getArticle() {
        return article;
    }

    /**
     * Устанавливает артикль товара.
     *
     * @param article Артикль товара.
     */
    public void setArticle(int article) {
        this.article = article;
    }

    /**
     * Возвращает название товара.
     *
     * @return Значение типа {@link String} - название товара.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Устанавливает название товара.
     *
     * @param title Название товара.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Возвращает URL товара.
     *
     * @return Значение типа {@link String} - URL товара.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Устанавливает URL товара.
     *
     * @param url URL товара.
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Возвращает параметры товара.
     *
     * @return Значение типа {@link String} - параметры товара.
     */
    public String getParameters() {
        return parameters;
    }

    /**
     * Устанавливает параметры товара.
     *
     * @param parameters Параметры товара.
     */
    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    /**
     * Возвращает описание товара.
     *
     * @return Значение типа {@link String} - описание товара.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Устанавливает описание товара.
     *
     * @param description Описание товара.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Возвращает изображение товара.
     *
     * @return Объект класса {@link Photo} - изображение товара.
     */
    public Photo getPhoto() {
        return photo;
    }

    /**
     * Устанавливает изображение товара.
     *
     * @param photo Изображене товара.
     */
    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    /**
     * Возвращает категорию товара.
     *
     * @return Объект класса {@link Category} - категория товара.
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Устанавливает категорию товара.
     *
     * @param category Категорию товара.
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * Возвращает цену товара.
     *
     * @return Значение типа double - цена товара.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Устанавливает цену товара.
     *
     * @param price Цена товара.
     */
    public void setPrice(double price) {
        this.price = price > 0 ? price : 0;
    }

    /**
     * Возвращает продажу, для которой пренадлежит текущий товара.
     *
     * @return Объект класса {@link SalePosition} - артикль товара.
     */
    public SalePosition getSalePosition() {
        return salePosition;
    }

    /**
     * Устанавливает продажу, для которой пренадлежит текущий товара.
     *
     * @param salePosition Продажа товара.
     */
    public void setSalePosition(SalePosition salePosition) {
        this.salePosition = salePosition;
    }
}