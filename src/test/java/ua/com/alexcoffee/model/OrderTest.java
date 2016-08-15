package ua.com.alexcoffee.model;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import ua.com.alexcoffee.enums.RoleEnum;
import ua.com.alexcoffee.enums.StatusEnum;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class OrderTest {

    @BeforeClass
    public static void beforeTests() {
        System.out.println("\nTesting class \"Order\" - START.");
    }

    @AfterClass
    public static void afterTests() {
        System.out.println("Testing class \"Order\" - FINISH.\n");
    }

    @Test
    public void ConstructorTest() {
        System.out.print("-> Order() - ");

        Order order = new Order();
        assertNotNull(order.getNumber());
        assertNotNull(order.getDate());

        Status status = new Status(StatusEnum.NEW, "NEW");
        User client = new User();
        order = new Order(status, client, new ArrayList<SalePosition>());
        assertNotNull(order.getStatus());
        assertNotNull(order.getClient());
        assertNotNull(order.getNumber());
        assertNotNull(order.getDate());

        System.out.println("OK!");
    }

    @Test
    public void toStringTest() {
        System.out.print("-> toString() - ");

        StringBuilder sb = new StringBuilder();

        Order order = new Order();
        Status status = new Status(StatusEnum.NEW, "NEW");
        order.setStatus(status);
        sb.append(order.getNumber()).append(", ").append(status.getDescription()).append(",\n").append(order.getDate());
        assertTrue(sb.toString().equals(order.toString()));


        User client = new User("Client", "email", "phone", null);
        order.setClient(client);
        sb.append("\n\nClient: ").append(client.getName())
                .append("\ne-mail: ").append(client.getEmail())
                .append("\nphone: ").append(client.getPhone()).append("\n");
        assertTrue(sb.toString().equals(order.toString()));


        Role role = new Role(RoleEnum.ADMIN, "ADMIN");
        User manager = new User("Manager", "email", "phone", role);
        order.setManager(manager);
        sb.append("\n").append(manager.getRole().getDescription())
                .append(" ").append(manager.getName()).append("\n");
        assertTrue(sb.toString().equals(order.toString()));


        String address = "Some address";
        order.setShippingAddress(address);
        sb.append("\nShipping address: ").append(address);
        assertTrue(sb.toString().equals(order.toString()));


        String details = "Details";
        order.setShippingDetails(details);
        sb.append("\nShipping details: ").append(details);
        assertTrue(sb.toString().equals(order.toString()));


        String description = "description";
        order.setDescription(description);
        sb.append("\nDescription: ").append(description);
        assertTrue(sb.toString().equals(order.toString()));

        Product product = new Product("title", "url", null, null, 10.0);
        product.setId((long) 1);
        SalePosition position = new SalePosition(product, 5);
        order.addSalePosition(position);
        sb.append("\nSale Positions: ").append("\n1) ").append(position.getProduct().getTitle())
                .append(", № ").append(position.getProduct().getId()).append(",\n")
                .append(position.getNumber()).append(" x ")
                .append(position.getProduct().getPrice()).append(" = ")
                .append(position.getPrice()).append(" UAH;")
                .append("\n\nPRICE = ").append(position.getPrice()).append(" UAH");
        assertTrue(sb.toString().equals(order.toString()));

        System.out.println("OK!");
    }

    @Test
    public void toEqualsTest() {
        System.out.print("-> toEquals() - ");

        Order order = new Order();
        assertEquals(order.getNumber(), order.toEquals());

        System.out.println("OK!");
    }

    @Test
    public void equalsReflexiveTest() {
        System.out.print("-> Reflexive equals - ");

        Order order = new Order();
        assertTrue(order.equals(order));

        System.out.println("OK!");
    }

    @Test
    public void equalsSymmetricTest() {
        System.out.print("-> Symmetric equals - ");

        Order order1 = new Order();
        Order order2 = new Order();
        order2.setNumber(order1.getNumber());

        assertTrue(order1.equals(order2));
        assertTrue(order2.equals(order1));

        System.out.println("OK!");
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

        System.out.println("OK!");
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

        System.out.println("OK!");
    }

    @Test
    public void initializerTest() {
        System.out.print("-> initializer() - ");

        String number = "NUMBER";
        Date date = new Date();
        String shippingAddress = "address";
        String shippingDetails = "details";
        String description = "description";
        Status status = new Status(StatusEnum.NEW, "NEW");
        User client = new User("Client", "email", "phone", null);
        User manager = new User("Manager", "email", "phone", null);

        Order order = new Order();
        order.initialize(number, date, shippingAddress, shippingDetails,
                description, status, client, manager);

        assertTrue(number.equals(order.getNumber()));
        assertTrue(shippingAddress.equals(order.getShippingAddress()));
        assertTrue(shippingDetails.equals(order.getShippingDetails()));
        assertTrue(description.equals(order.getDescription()));
        assertTrue(status.equals(order.getStatus()));
        assertTrue(client.equals(order.getClient()));
        assertTrue(manager.equals(order.getManager()));

        System.out.println("OK!");
    }

    @Test
    public void addSalePositionTest() {
        System.out.print("-> addSalePosition() - ");

        Order order = new Order();
        order.addSalePosition(new SalePosition());

        assertEquals(1, order.getSalePositions().size());

        System.out.println("OK!");
    }

    @Test
    public void addSalePositionsTest() {
        System.out.print("-> addSalePositions() - ");

        List<SalePosition> salePositions = getTenSalePositions();

        Order order = new Order();
        order.addSalePositions(salePositions);

        assertEquals(10, order.getSalePositions().size());

        System.out.println("OK!");
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

        System.out.println("OK!");
    }

    @Test
    public void removeSalePositionsTest() {
        System.out.print("-> removeSalePositions() - ");

        Order order = new Order();
        List<SalePosition> positions = getTenSalePositions();

        order.setSalePositions(positions);
        assertTrue(order.getSalePositions().size() == 10);

        order.removeSalePositions(positions);
        assertTrue(order.getSalePositions().size() == 0);

        System.out.println("OK!");
    }

    @Test
    public void clearSalePositionsTest() {
        System.out.print("-> clearSalePositions() - ");

        Order order = new Order();
        order.setSalePositions(getTenSalePositions());
        order.clearSalePositions();

        assertEquals(0, order.getSalePositions().size());

        System.out.println("OK!");
    }

    @Test
    public void setAndGetSalePositionsTest() {
        System.out.print("-> setAndGetSalePositions() - ");

        Order order = new Order();
        order.setSalePositions(getTenSalePositions());

        assertNotNull(order.getSalePositions());
        assertTrue(order.getSalePositions().size() == 10);

        System.out.println("OK!");
    }

    @Test
    public void salePositionOrders() {
        System.out.print("-> Order of sale position - ");

        Order order = new Order();
        SalePosition salePosition = new SalePosition();

        order.addSalePosition(salePosition);

        assertTrue(order.equals(salePosition.getOrder()));

        System.out.println("OK!");
    }

    @Test
    public void setAndGetNumberTest() {
        System.out.print("-> setAndGetNumber() - ");

        Order order = new Order();
        order.setNumber(null);
        assertNotNull(order.getNumber());
        assertTrue(order.getNumber().isEmpty());

        String number = "NUMBER";
        order.setNumber(number);
        assertNotNull(order.getNumber());

        System.out.println("OK!");
    }

    @Test
    public void newNumberTest() {
        System.out.print("-> newNumber() - ");

        Order order = new Order();
        String number = order.getNumber();

        order.newNumber();
        String newNumber = order.getNumber();

        assertEquals(false, number.equals(newNumber));

        System.out.println("OK!");
    }

    @Test
    public void setAndGetDateTest() {
        System.out.print("-> setAndGetDate() - ");

        Order order = new Order();
        order.setDate(null);
        assertNotNull(order.getDate());
        assertTrue(order.getDate().isEmpty());

        Date date = new Date();
        order.setDate(date);
        assertNotNull(order.getDate());

        System.out.println("OK!");
    }

    @Test
    public void setAndGetStatusTest() {
        System.out.print("-> setAndGetStatus() - ");

        Order order = new Order();
        Status status = new Status(StatusEnum.CLOSED, "CLOSED");
        order.setStatus(status);

        assertNotNull(order.getStatus());
        assertTrue(status.equals(order.getStatus()));

        System.out.println("OK!");
    }

    @Test
    public void setAndGetClientTest() {
        System.out.print("-> setAndGetClient() - ");

        Order order = new Order();
        User client = new User("Client", null, null, null);
        order.setClient(client);

        assertNotNull(order.getClient());
        assertTrue(client.equals(order.getClient()));

        System.out.println("OK!");
    }

    @Test
    public void setAndGetManagerTest() {
        System.out.print("-> getManager() - ");

        User manager = new User();
        Order order = new Order();
        order.setManager(manager);

        assertNotNull(order.getManager());
        assertTrue(manager.equals(order.getManager()));

        System.out.println("OK!");
    }

    @Test
    public void setAndGetShippingAddressTest() {
        System.out.print("-> setAndGetShippingAddress() - ");

        Order order = new Order();
        order.setShippingAddress(null);
        assertNotNull(order.getShippingAddress());
        assertTrue(order.getShippingAddress().isEmpty());

        String address = "Some address.";
        order.setShippingAddress(address);
        assertTrue(address.equals(order.getShippingAddress()));

        System.out.println("OK!");
    }

    @Test
    public void setAndGetShippingDetailsTest() {
        System.out.print("-> setAndGetShippingDetails() - ");

        Order order = new Order();
        order.setShippingDetails(null);
        assertNotNull(order.getShippingDetails());
        assertTrue(order.getShippingDetails().isEmpty());

        String details = "Some details.";
        order.setShippingDetails(details);
        assertTrue(details.equals(order.getShippingDetails()));

        System.out.println("OK!");
    }

    @Test
    public void setAndGetDescriptionTest() {
        System.out.print("-> setAndGetProducts() - ");

        Order order = new Order();
        order.setDescription(null);
        assertNotNull(order.getDescription());
        assertTrue(order.getDescription().isEmpty());

        String description = "Some description.";
        order.setDescription(description);
        assertTrue(description.equals(order.getDescription()));

        System.out.println("OK!");
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

        System.out.println("OK!");
    }

    @Ignore
    private static List<SalePosition> getTenSalePositions() {
        List<SalePosition> salePositions = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Product product = new Product("Name" + i, "URL", null, null, 10 + i);
            SalePosition position = new SalePosition(product, 1);
            salePositions.add(position);
        }

        return salePositions;
    }
}
