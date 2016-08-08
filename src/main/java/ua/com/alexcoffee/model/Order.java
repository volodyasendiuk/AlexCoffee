package ua.com.alexcoffee.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order extends Model {

    private static final long serialVersionUID = 1L;

    @Column(name = "number", nullable = false)
    private String number;

    @Column(name = "date", nullable = false)
    private String date;

    @Column(name = "shipping_address")
    private String shippingAddress;

    @Column(name = "shipping_details")
    private String shippingDetails;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id", referencedColumnName = "id", nullable = false)
    private Status status;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id", referencedColumnName = "id", nullable = false)
    private User client;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "manager_id", referencedColumnName = "id")
    private User manager;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "order", cascade = CascadeType.ALL)
    private List<Sale> sales = new ArrayList<>();

    public Order() {
        super();
        number = createRandomString();
        date = dateToStringWithFormat(new Date());
    }

    public Order(Date date, Status status, User client) {
        super();
        this.date = dateToStringWithFormat(date);
        this.status = status;
        this.client = client;
        number = createRandomString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(number).append(", ")
                .append(status.getDescription()).append(", ").append(date)
                .append("\n\nClient: ").append(client.getName())
                .append("\nemail: ").append(client.getEmail())
                .append("\nnphone: ").append(client.getPhone()).append("\n");

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

        if (!sales.isEmpty()) {
            sb.append("\nSale: ");
            int count = 0;
            for (Sale sale : sales) {
                sb.append("\n").append(count++).append(") ").append(sale.getProduct().getTitle())
                        .append("\n№ ").append(sale.getProduct().getId())
                        .append(", ").append(sale.getProduct().getPrice()).append(" UAH")
                        .append("\nnumber = ").append(sale.getNumber()).append(", price = ")
                        .append(sale.getPrice()).append(" UAH");
            }
            sb.append("\n\nPRICE = ").append(getPrice()).append(" UAH");
        }
        return sb.toString();
    }

    @Override
    public String toEquals() {
        return getNumber();
    }

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

    public void addSale(Sale sale) {
        sales.add(sale);
        if (sale.getOrder() != this) {
            sale.setOrder(this);
        }
    }

    public void addSales(List<Sale> sales) {
        this.sales.addAll(sales);
        for (Sale sale : sales) {
            if (sale.getOrder() != this) {
                sale.setOrder(this);
            }
        }
    }

    public void removeSale(Sale sale) {
        sales.remove(sale);
    }

    public void removeSales(List<Sale> sales) {
        this.sales.removeAll(sales);
    }

    public void clearSales() {
        sales.clear();
    }

    public List<Sale> getSales() {
        return Collections.unmodifiableList(sales);
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
        for (Sale sale : this.sales) {
            if (sale.getOrder() != this) {
                sale.setOrder(this);
            }
        }
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void newNumber() {
        number = createRandomString();
    }

    public String getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = dateToStringWithFormat(date);
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getShippingDetails() {
        return shippingDetails;
    }

    public void setShippingDetails(String shippingDetails) {
        this.shippingDetails = shippingDetails;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getPrice() {
        double price = 0;
        for (Sale sale : sales) {
            price += sale.getPrice();
        }
        return price;
    }
}
