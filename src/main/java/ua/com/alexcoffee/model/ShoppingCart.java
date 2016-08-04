package ua.com.alexcoffee.model;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ShoppingCart implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Sale> sales = new ArrayList<>();

    public ShoppingCart() {

    }

    public ShoppingCart(List<Sale> products) {
        this.sales = sales;
    }

    @Override
    public String toString() {
        int count = 1;
        StringBuilder sb = new StringBuilder("Shoping Cart: ");
        for (Sale sale : sales) {
            sb.append("\n").append(count++).append(") ").append(sale.getProduct().getTitle())
                    .append("\n№ ").append(sale.getProduct().getId()).append(", ").append(sale.getPrice()).append(" UAH");
        }
        sb.append("\nPrice: ").append(getPrice()).append(" UAH");
        return sb.toString();
    }

    public void addSale(Sale sale) {
        if (!sales.contains(sale)) {
            sales.add(sale);
        } else {
            sales.get(sales.indexOf(sale)).numberIncr();
        }
    }

    public void addSales(List<Sale> sales) {
        this.sales.addAll(sales);
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
    }

    public double getPrice() {
        double sum = 0;
        for (Sale sale : sales) {
            sum += sale.getPrice();
        }
        return sum;
    }

    public int getSize() {
        int size = 0;
        for (Sale sale : sales) {
            size += sale.getNumber();
        }
        return size;
    }
}
