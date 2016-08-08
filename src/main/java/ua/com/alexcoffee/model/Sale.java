package ua.com.alexcoffee.model;

import javax.persistence.*;

@Entity
@Table(name = "sales")
public class Sale extends Model {

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    private Product product;

    @Column(name = "number", nullable = false)
    private int number;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false)
    private Order order;

    public Sale() {
        super();
    }

    public Sale(Product product, int number) {
        this.product = product;
        this.number = number;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Sale #" + getId() + ": ")
                .append("\n").append(product.getTitle()).append("\n№ ").append(product.getId())
                .append(", ").append(product.getPrice()).append(" UAH")
                .append("\nNumber = ").append(number)
                .append("\nPrice = ").append(getPrice());
        return sb.toString();
    }

    @Override
    public String toEquals() {
        return product.toEquals();
    }

    public double getPrice() {
        return product.getPrice() * number;
    }

    public void numberIncr() {
        number++;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number > 0 ? number : 0;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
