package ua.com.alexcoffee.entity;

import ua.com.alexcoffee.enums.StatusEnum;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "statuses")
public class Status extends Model {

    private static final long serialVersionUID = 1L;

    @Column(name = "title", nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusEnum title;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "status", cascade = CascadeType.REMOVE)
    private Set<Order> orders = new HashSet<>();

    public Status() {
        super();
    }

    public Status(long id, StatusEnum title) {
        super(id);
        this.title = title;
    }

    public Status(StatusEnum title) {
        super();
        this.title = title;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Title: ").append(title.name())
                .append("\nDescription: ").append(description);
        return sb.toString();
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

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}
