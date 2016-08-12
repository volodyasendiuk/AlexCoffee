package ua.com.alexcoffee.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Класс описывает сущность "Заказы", наследует класс {@link Model}.
 * Заказ описывает торговые позиции, клиента, сделавшего заказ, и менеджера, который обработал заказ.
 * Аннотация @Entity говорит о том что объекты этого класса будет обрабатываться hibernate.
 * Аннотация @Table(name = "orders") указывает на таблицу "orders", в которой будут храниться объекты.
 *
 * @author Yurii Salimov
 * @see Status
 * @see User
 * @see SalePosition
 */
@Entity
@Table(name = "orders")
public class Order extends Model {
    /**
     * Номер версии класса необходимый для десериализации и сериализации.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Номер заказа. Значение поля сохраняется в колонке "number". Не может быть null.
     */
    @Column(name = "number", nullable = false)
    private String number;

    /**
     * Дата модификации заказа. Значение поля сохраняется в колонке "date". Не может быть null.
     */
    @Column(name = "date", nullable = false)
    private String date;

    /**
     * Адрес доставки заказа. Значение поля сохраняется в колонке "shipping_address".
     */
    @Column(name = "shipping_address")
    private String shippingAddress;

    /**
     * Детали доставки заказа. Значение поля сохраняется в колонке "shipping_details".
     */
    @Column(name = "shipping_details")
    private String shippingDetails;

    /**
     * Описание заказа. Значение поля сохраняется в колонке "description".
     */
    @Column(name = "description")
    private String description;

    /**
     * Статус заказа.
     * Значение поля (id объекта status) сохраняется в колонке "status_id". Не может быть null.
     * Между объектами классов {@link Order} и
     * {@link Status} связь многие-к-одному, а именно
     * много разных заказов могут иметь одинаковый статус выполнения.
     * Выборка объекта status до первого доступа нему, при первом доступе к текущему объекту.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id", referencedColumnName = "id", nullable = false)
    private Status status;

    /**
     * Клиент, оформивший заказ.
     * Значение поля (id объекта client) сохраняется в колонке "client_id". Не может быть null.
     * Между объектами классов {@link Order} и
     * {@link User} связь один-к-одному, а именно каждая
     * запись в одной таблице напрямую связана с отдельной записью в другой таблице.
     * Выборка объекта client до первого доступа нему, при первом доступе к текущему объекту.
     * Сущности связаны полностью каскадным обновлением записей в базе данных.
     */
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id", referencedColumnName = "id", nullable = false)
    private User client;

    /**
     * Менеджер, обработавший заказ.
     * Значение поля (id объекта manager) сохраняется в колонке "manager_id".
     * Между объектами классов {@link Order} и
     * {@link User} связь много-к-одному, а именно каждая
     * запись в одной таблице напрямую связана с отдельной записью в другой таблице.
     * Выборка объекта manager до первого доступа нему, при первом доступе к текущему объекту.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "manager_id", referencedColumnName = "id")
    private User manager;

    /**
     * Список торговых позиция текущего заказу.
     * К текущему заказу можно добраться через поле "order"
     * в объекте класса {@link SalePosition}.
     * Выборка продаж при первом доступе к текущему объекту.
     * Сущности связаны полностью каскадным обновлением записей в базе данных.
     */
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "order", cascade = CascadeType.ALL)
    private List<SalePosition> salePositions = new ArrayList<>();

    /**
     * Конструктр без параметров.
     * Автоматически инициализируются поля номер и дата модификации заказа.
     */
    public Order() {
        super();
        number = createRandomString();
        date = dateToString(new Date());
    }

    /**
     * Конструктор для инициализации основных переменных заказа.
     *
     * @param date   Дата модификации заказа.
     * @param status Статус заказа.
     * @param client Клиент, оформивший заказ.
     */
    public Order(Date date, Status status, User client) {
        super();
        this.date = dateToString(date);
        this.status = status;
        this.client = client;
        number = createRandomString();
    }

    /**
     * Возвращает описание заказа.
     * Переопределенный метод родительского класса {@link Object}.
     *
     * @return Значение типа String - строка описание заказа (номер, статус, дата, информация о клиенте,
     * информация о менеджере, адрес и детали доставкиб описание, торговые позиции).
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(number).append(", ")
                .append(status.getDescription()).append(", ").append(date);

        if (client != null) {
            sb.append("\n\nClient: ").append(client.getName())
                    .append("\nemail: ").append(client.getEmail())
                    .append("\nnphone: ").append(client.getPhone()).append("\n");
        }

        if (manager != null) {
            sb.append("\n").append(manager.getRole().getDescription()).append(manager.getName()).append("\n");
        }

        if (shippingAddress != null && !shippingAddress.isEmpty()) {
            sb.append("\nShipping address: ").append(shippingAddress);
        }
        if (shippingDetails != null && !shippingDetails.isEmpty()) {
            sb.append("\nShipping details: ").append(shippingDetails);
        }
        if (description != null && !description.isEmpty()) {
            sb.append("\nDescription: ").append(description);
        }

        if (salePositions != null && !salePositions.isEmpty()) {
            sb.append("\nSalePosition: ");
            int count = 0;
            for (SalePosition salePosition : salePositions) {
                sb.append("\n").append(count++).append(") ").append(salePosition.getProduct().getTitle())
                        .append("\n№ ").append(salePosition.getProduct().getId())
                        .append(", ").append(salePosition.getProduct().getPrice()).append(" UAH")
                        .append("\nnumber = ").append(salePosition.getNumber()).append(", price = ")
                        .append(salePosition.getPrice()).append(" UAH");
            }
            sb.append("\n\nPRICE = ").append(getPrice()).append(" UAH");
        }
        return sb.toString();
    }

    /**
     * Генерирует строку для конечного сравнения заказа в методе equals() родительского класса.
     * Переопределенный метод родительского класса {@link Model}.
     *
     * @return Значение типа String - номер заказа.
     */
    @Override
    public String toEquals() {
        return getNumber();
    }

    /**
     * Инициализация полей заказа.
     *
     * @param number          Номер заказа.
     * @param date            Дата модификации заказа.
     * @param shippingAddress Адрес доставки заказа.
     * @param shippingDetails Детали доставки заказа.
     * @param description     Описание заказа.
     * @param status          Статус заказа.
     * @param client          Клиент, оформивший заказ.
     * @param manager         Менеджер, обработавший заказ.
     */
    public void initializer(String number, Date date, String shippingAddress, String shippingDetails,
                            String description, Status status, User client, User manager) {
        setNumber(number);
        setDate(date);
        setShippingAddress(shippingAddress);
        setShippingDetails(shippingDetails);
        setDescription(description);
        setStatus(status);
        setClient(client);
        setManager(manager);
    }

    /**
     * Добавляет торговую позицию в текущий заказа.
     *
     * @param salePosition Торговая позиция, которая будет добавлена в заказ.
     */
    public void addSalePosition(SalePosition salePosition) {
        salePositions.add(salePosition);
        if (salePosition.getOrder() != this) {
            salePosition.setOrder(this);
        }
    }

    /**
     * Добавляет список торговых позиций в текущий заказ.
     *
     * @param salePositions Список торговых позиций, которые будут дабавлены в заказ.
     */
    public void addSalePositions(List<SalePosition> salePositions) {
        this.salePositions.addAll(salePositions);
        for (SalePosition salePosition : salePositions) {
            if (salePosition.getOrder() != this) {
                salePosition.setOrder(this);
            }
        }
    }

    /**
     * Удаляет торговую позицию из текущего заказа.
     *
     * @param salePosition Торговая позиция, которая будет удалена из заказу.
     */
    public void removeSalePosition(SalePosition salePosition) {
        salePositions.remove(salePosition);
    }

    /**
     * Удаляет список торговых позиция из текущего заказа.
     *
     * @param salePositions Список торговых позиция, которые будут удалены из заказа.
     */
    public void removeSalePositions(List<SalePosition> salePositions) {
        this.salePositions.removeAll(salePositions);
    }

    /**
     * Очищает список торговых позиция текущего заказа.
     */
    public void clearSalePositions() {
        salePositions.clear();
    }

    /**
     * Конвертирует список торговых позиций текущего заказа в список
     * только для чтений и возвращает его.
     *
     * @return Объект типа {@link List} - список торговых позиция только для чтения или пустой список.
     */
    public List<SalePosition> getSalePositions() {
        return getUnmodifiableList(salePositions);
    }

    /**
     * Устанавливает список торговых позицияй текущему заказу.
     *
     * @param salePositions Список торговых позиция.
     */
    public void setSalePositions(List<SalePosition> salePositions) {
        this.salePositions = salePositions;
        for (SalePosition salePosition : this.salePositions) {
            if (salePosition.getOrder() != this) {
                salePosition.setOrder(this);
            }
        }
    }

    /**
     * Возвращает номер заказа.
     *
     * @return Значение типа String - номер заказа.
     */
    public String getNumber() {
        return number;
    }

    /**
     * Устанавливает номер заказа.
     *
     * @param number Номер заказа.
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * Генерирует новый номер заказа.
     */
    public void newNumber() {
        number = createRandomString();
    }

    /**
     * Возвращает дату последней модификации заказа.
     *
     * @return Значение типа String - дата модификации заказа.
     */
    public String getDate() {
        return date;
    }

    /**
     * Устанавливает дату модификации заказа.
     *
     * @param date Дата модификации заказа.
     */
    public void setDate(Date date) {
        this.date = dateToString(date);
    }

    /**
     * Возвращает статус работы заказа.
     *
     * @return Объект класса {@link Status} - статус заказа.
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Устанавливает статус заказа.
     *
     * @param status Статус заказа.
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * Возвращает клиента, оформивший заказ.
     *
     * @return Объект класса {@link User} - клиент, оформивший заказ.
     */
    public User getClient() {
        return client;
    }

    /**
     * Устанавливает клиента, оформившего заказ.
     *
     * @param client Клиента, оформивший заказ.
     */
    public void setClient(User client) {
        this.client = client;
    }

    /**
     * Возвращает менеджера, обработавший заказ.
     *
     * @return Объект класса {@link User} - менеджер, обработавший заказ.
     */
    public User getManager() {
        return manager;
    }

    /**
     * Устанавливает менеджера, обработавший заказ.
     *
     * @param manager Менеджер, обработавший заказ.
     */
    public void setManager(User manager) {
        this.manager = manager;
    }

    /**
     * Возвращает адрес доставки заказа.
     *
     * @return Значение типа String - адресс доставки заказа.
     */
    public String getShippingAddress() {
        return shippingAddress;
    }

    /**
     * Устанавливает адрес доставки заказа.
     *
     * @param shippingAddress Адрес доставки заказа.
     */
    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    /**
     * Возвращает детали доставки заказа.
     *
     * @return Значение типа String - детали доставки заказа.
     */
    public String getShippingDetails() {
        return shippingDetails;
    }

    /**
     * Устанавливает детали доставки заказа.
     *
     * @param shippingDetails Детали доставки заказа.
     */
    public void setShippingDetails(String shippingDetails) {
        this.shippingDetails = shippingDetails;
    }

    /**
     * Возвращает описание заказа.
     *
     * @return Значение типа String - описание заказа.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Встанавливает описание заказа.
     *
     * @param description Описание заказа.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Возвращает цену заказа - общую стоимость всех торговых позиция.
     *
     * @return Значение типа String - цена заказа.
     */
    public double getPrice() {
        double price = 0;
        for (SalePosition salePosition : salePositions) {
            price += salePosition.getPrice();
        }
        return price;
    }
}
