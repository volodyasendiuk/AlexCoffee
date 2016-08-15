package ua.com.alexcoffee.model;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import ua.com.alexcoffee.enums.StatusEnum;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class StatusTest {

    @BeforeClass
    public static void beforeTests() {
        System.out.println("\nTesting class \"Status\" - START.");
    }

    @AfterClass
    public static void afterTests() {
        System.out.println("Testing class \"Status\" - FINISH.\n");
    }

    @Test
    public void toStringTest() {
        System.out.print("-> toString() - ");

        StatusEnum title = StatusEnum.NEW;
        String description = "Description";

        Status status = new Status(title, description);
        String line = "Title: " + title.name() + "\nDescription: " + description;

        assertTrue(status.toString().equals(line));

        System.out.println("OK!");
    }

    @Test
    public void toEqualsTest() {
        System.out.print("-> toEquals() - ");

        Status status = new Status(StatusEnum.NEW, "NEW");
        assertEquals(status.toString(), status.toEquals());

        System.out.println("ok!");
    }

    @Test
    public void equalsReflexiveTest() {
        System.out.print("-> Reflexive equals - ");

        Status status = new Status();
        assertTrue(status.equals(status));

        status = new Status(StatusEnum.NEW, "NEW");
        assertTrue(status.equals(status));

        System.out.println("ok!");
    }

    @Test
    public void equalsSymmetricTest() {
        System.out.print("-> Symmetric equals - ");

        Status status1 = new Status(StatusEnum.NEW, "NEW");
        Status status2 = new Status(StatusEnum.NEW, "NEW");

        assertTrue(status1.equals(status2));
        assertTrue(status2.equals(status1));

        System.out.println("ok!");
    }

    @Test
    public void equalsTransitiveTest() {
        System.out.print("-> Transitive equals - ");

        Status status1 = new Status(StatusEnum.NEW, "NEW");
        Status status2 = new Status(StatusEnum.NEW, "NEW");
        Status status3 = new Status(StatusEnum.NEW, "NEW");

        assertTrue(status1.equals(status2));
        assertTrue(status2.equals(status3));
        assertTrue(status1.equals(status3));

        System.out.println("ok!");
    }

    @Test
    public void equalsConsistentTest() {
        System.out.print("-> Consistent equals - ");

        Status status1 = new Status(StatusEnum.NEW, "NEW");
        Status status2 = new Status(StatusEnum.NEW, "NEW");

        for (int i = 0; i < 10; i++) {
            assertTrue(status1.equals(status2));
        }

        System.out.println("ok!");
    }

    @Test
    public void addOrderTest() {
        System.out.print("-> addOrder() - ");

        Status status = new Status();
        for (int i = 0; i < 10; i++) {
            status.addOrder(new Order());
        }
        assertEquals(10, status.getOrders().size());

        System.out.println("ok!");
    }

    @Test
    public void addOrdersTest() {
        System.out.print("-> addOrders() - ");

        List<Order> orders = getTenOrders();

        Status status = new Status();
        status.addOrders(orders);

        assertEquals(10, status.getOrders().size());

        System.out.println("ok!");
    }

    @Test
    public void removeOrderTest() {
        System.out.print("-> removeOrder() - ");

        Status status = new Status();

        Order order = new Order();
        status.addOrder(order);
        status.addOrders(getTenOrders());

        status.removeOrder(order);

        assertEquals(10, status.getOrders().size());

        System.out.println("ok!");
    }

    @Test
    public void removeOrdersTest() {
        System.out.print("-> removeOrders() - ");

        Status status = new Status();

        List<Order> orders = getTenOrders();
        status.addOrders(orders);
        status.addOrders(getTenOrders());

        status.removeOrders(orders);

        assertEquals(10, status.getOrders().size());

        System.out.println("ok!");
    }

    @Test
    public void clearOrdersTest() {
        System.out.print("-> clearOrders() - ");

        Status status = new Status();
        List<Order> orders = getTenOrders();
        status.addOrders(orders);

        status.clearOrders();

        assertEquals(0, status.getOrders().size());

        System.out.println("ok!");
    }

    @Test
    public void setAndGetOrdersTest() {
        System.out.print("-> setAndGetProducts() - ");

        Status status = new Status(StatusEnum.NEW, "NEW");
        status.setOrders(getTenOrders());

        assertNotNull(status.getOrders());
        assertTrue(status.getOrders().size() == 10);

        System.out.println("OK!");
    }

    @Test
    public void setAndGetTitleTest() {
        System.out.print("-> setAndGetProducts() - ");

        Status status = new Status();
        status.setTitle(StatusEnum.NEW);

        assertNotNull(status.getTitle());
        assertTrue(status.getTitle().equals(StatusEnum.NEW));
        assertFalse(status.getTitle().equals(StatusEnum.CLOSED));

        System.out.println("OK!");
    }

    @Test
    public void setAndGetDescriptionTest() {
        System.out.print("-> setAndGetDescription() - ");

        Status status = new Status();
        status.setDescription(null);
        assertNotNull(status.getDescription());
        assertTrue(status.getDescription().isEmpty());

        String description = "Description";
        status.setDescription(description);
        assertTrue(status.getDescription().equals(description));

        System.out.println("OK!");
    }

    @Ignore
    private static List<Order> getTenOrders() {
        List<Order> orders = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            orders.add(new Order());
        }

        return orders;
    }
}
