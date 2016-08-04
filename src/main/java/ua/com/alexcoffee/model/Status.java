package ua.com.alexcoffee.model;

import ua.com.alexcoffee.enums.StatusEnum;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "statuses")
public class Status extends Model {

    private static final long serialVersionUID = 1L;

    @Column(name = "title", nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusEnum title;

    @Column(name = "description")
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "status", cascade = CascadeType.REMOVE)
    private List<Order> orders = new ArrayList<>();

    public Status() {
        super();
    }

    public Status(StatusEnum title) {
        super();
        this.title = title;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Title: ").append(title.name())
                .append("\nDescription: ").append(description);
        return sb.toString();
    }

    public void add(Order order) {
        orders.add(order);
    }

    public void remove(Order order) {
        orders.remove(order);
    }

    public void clear() {
        orders.clear();
    }

    public StatusEnum getTitle() {
        return title;
    }

    public void setTitle(StatusEnum title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Order> getOrders() {
        return Collections.unmodifiableList(orders);
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
