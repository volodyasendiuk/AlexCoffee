package ua.com.alexcoffee.model;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class OrderTest {

    @BeforeClass
    public static void beforeTests() {
        System.out.println("Testing class \"Order\" - START.\n");
    }

    @AfterClass
    public static void afterTests() {
        System.out.println("Testing class \"Order\" - FINISH.\n");
    }

    @Test
    public void toEqualsTest() {
        System.out.print("-> toEquals() - ");

        Order order = new Order();
        assertEquals(order.getNumber(), order.toEquals());

        System.out.println("ok!");
    }

    @Test
    public void equalsReflexiveTest() {
        System.out.print("-> Reflexive equals - ");

        Order order = new Order();
        assertTrue(order.equals(order));

        System.out.println("ok!");
    }

    @Test
    public void equalsSymmetricTest() {
        System.out.print("-> Symmetric equals - ");

        Order order1 = new Order(new Date(), new Status(), new User());
        Order order2 = order1;

        assertTrue(order1.equals(order2));
        assertTrue(order2.equals(order1));

        System.out.println("ok!");
    }

    @Test
    public void equalsTransitiveTest() {
        System.out.print("-> Transitive equals - ");

        Order order1 = new Order();
        Order order2 = new Order();
        order2.setNumber(order1.getNumber());
        Order order3 = new Order();
        order3.setNumber(order2.getNumber());

        assertTrue(order1.equals(order2));
        assertTrue(order2.equals(order3));
        assertTrue(order1.equals(order3));

        System.out.println("ok!");
    }

    @Test
    public void equalsConsistentTest() {
        System.out.print("-> Consistent equals - ");

        Order order1 = new Order();
        Order order2 = new Order();
        order2.setNumber(order1.getNumber());

        for (int i = 0; i < 10; i++) {
            assertTrue(order1.equals(order2));
        }

        System.out.println("ok!");
    }

    @Test
    public void addSalePositionTest() {
        System.out.print("-> addSalePosition() - ");

        Order order = new Order();
        order.addSalePosition(new SalePosition());

        assertEquals(1, order.getSalePositions().size());

        System.out.println("ok!");
    }

    @Test
    public void salePositionOrders() {
        System.out.print("-> Order of sale position - ");

        Order order = new Order();
        SalePosition salePosition = new SalePosition();

        order.addSalePosition(salePosition);

        assertTrue(order.equals(salePosition.getOrder()));

        System.out.println("ok!");
    }

    @Test
    public void addSalePositionsTest() {
        System.out.print("-> addSalePositions() - ");

        List<SalePosition> salePositions = getTenSalePositions();

        Order order = new Order();
        order.addSalePositions(salePositions);

        assertEquals(10, order.getSalePositions().size());

        System.out.println("ok!");
    }

    @Test
    public void removeSalePositionTest() {
        System.out.print("-> removeSalePositions() - ");

        Order order = new Order();

        SalePosition salePosition = new SalePosition();

        order.addSalePosition(salePosition);
        order.addSalePositions(getTenSalePositions());
        order.removeSalePosition(salePosition);

        assertEquals(10, order.getSalePositions().size());

        System.out.println("ok!");
    }

    @Test
    public void clearSalePositionsTest() {
        System.out.print("-> clearSalePositions() - ");

        Order order = new Order();
        order.setSalePositions(getTenSalePositions());
        order.clearSalePositions();

        assertEquals(0, order.getSalePositions().size());

        System.out.println("ok!");
    }

    @Test
    public void newNumberTest() {
        System.out.print("-> newNumber() - ");

        Order order = new Order();
        String number = order.getNumber();

        order.newNumber();
        String newNumber = order.getNumber();

        assertEquals(false, number.equals(newNumber));

        System.out.println("ok!");
    }

    @Test
    public void getPriceTest() {
        System.out.print("-> getPrice() - ");

        Order order = new Order();
        Product product;
        SalePosition salePosition;
        double price = 0;

        for (int i = 0; i < 10; i++) {
            product = new Product();
            product.setPrice(Math.random());

            salePosition = new SalePosition();
            salePosition.setProduct(product);

            order.addSalePosition(salePosition);

            price += product.getPrice();
        }

        assertTrue(price == order.getPrice());

        System.out.println("ok!");
    }

    @Ignore
    private static List<SalePosition> getTenSalePositions() {
        List<SalePosition> salePositions = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            salePositions.add(new SalePosition());
        }

        return salePositions;
    }
}