package ua.com.alexcoffee.model;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class SalePositionTest {
    @BeforeClass
    public static void beforeTests() {
        System.out.println("Testing class \"SalePosition\" - START.\n");
    }

    @AfterClass
    public static void afterTests() {
        System.out.println("Testing class \"SalePosition\" - FINISH.\n");
    }

    @Test
    public void equalsReflexiveTest() {
        System.out.print("-> Reflexive equals - ");

        SalePosition salePosition = new SalePosition();
        assertTrue(salePosition.equals(salePosition));

        System.out.println("ok!");
    }

    @Test
    public void equalsSymmetricTest() {
        System.out.print("-> Symmetric equals - ");

        Product product = new Product();
        SalePosition salePosition1 = new SalePosition(product, 1);
        SalePosition salePosition2 = new SalePosition(product, 1);

        assertTrue(salePosition1.equals(salePosition2));
        assertTrue(salePosition2.equals(salePosition1));

        salePosition2 = new SalePosition(product, 5);

        assertFalse(salePosition1.equals(salePosition2));

        System.out.println("ok!");
    }

    @Test
    public void equalsTransitiveTest() {
        System.out.print("-> Transitive equals - ");

        Product product = new Product();
        SalePosition salePosition1 = new SalePosition(product, 24);
        SalePosition salePosition2 = new SalePosition(product, 24);
        SalePosition salePosition3 = new SalePosition(product, 24);

        assertTrue(salePosition1.equals(salePosition2));
        assertTrue(salePosition2.equals(salePosition3));
        assertTrue(salePosition1.equals(salePosition3));

        System.out.println("ok!");
    }

    @Test
    public void equalsConsistentTest() {
        System.out.print("-> Consistent equals - ");

        Product product = new Product();
        SalePosition salePosition1 = new SalePosition(product, 20);
        SalePosition salePosition2 = new SalePosition(product, 20);

        for (int i = 0; i < 10; i++) {
            assertTrue(salePosition1.equals(salePosition2));
        }

        System.out.println("ok!");
    }

    @Test
    public void getPriceTest() {
        Product product = new Product("", "", null, null, 100);
        SalePosition salePosition = new SalePosition(product, 10);

        assertTrue(salePosition.getPrice() == product.getPrice() * 10);

        salePosition = new SalePosition();
        assertTrue(salePosition.getPrice() == 0);
    }

    @Test
    public void setNumberTest() {
        SalePosition salePosition = new SalePosition(null, 10);
        salePosition.numberIncr();

        assertTrue(salePosition.getNumber() == 11);
    }
}