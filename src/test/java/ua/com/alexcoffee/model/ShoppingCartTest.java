package ua.com.alexcoffee.model;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ShoppingCartTest {
    @BeforeClass
    public static void beforeTests() {
        System.out.println("Testing class \"ShoppingCart\" - START.\n");
    }

    @AfterClass
    public static void afterTests() {
        System.out.println("Testing class \"ShoppingCart\" - FINISH.\n");
    }


    @Test
    public void addSalePositionTest() {
        System.out.print("-> addSalePosition() - ");

        ShoppingCart shoppingCart = new ShoppingCart();
        SalePosition salePosition = new SalePosition(new Product(), 1);

        for (int i = 0; i < 10; i++) {
            shoppingCart.addSalePosition(salePosition);
        }

        assertTrue(shoppingCart.getSalePositions().size() == 1);
        assertTrue(shoppingCart.getSalePositions().get(0).getNumber() == 10);

        System.out.println("ok!");
    }

    @Test
    public void addSalePositionsTest() {
        System.out.print("-> addSalePositions() - ");

        ShoppingCart shoppingCart = new ShoppingCart();
        SalePosition salePosition = new SalePosition(new Product(), 1);

        List<SalePosition> salePositions = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            salePositions.add(salePosition);
        }

        shoppingCart.addSalePositions(salePositions);

        assertTrue(shoppingCart.getSalePositions().size() == 1);
        assertTrue(shoppingCart.getSalePositions().get(0).getNumber() == 10);

        System.out.println("ok!");
    }

    @Test
    public void getPrice() {
        System.out.print("-> getPrice() - ");

        ShoppingCart shoppingCart = new ShoppingCart();
        Product product = new Product("", "", null, null, 100);
        SalePosition salePosition = new SalePosition(product, 1);

        for (int i = 0; i < 10; i++) {
            shoppingCart.addSalePosition(salePosition);
        }

        assertTrue(shoppingCart.getPrice() == salePosition.getPrice());
        assertTrue(shoppingCart.getPrice() == product.getPrice() * 10);

        System.out.println("ok!");
    }
}