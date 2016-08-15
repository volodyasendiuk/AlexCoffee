package ua.com.alexcoffee.model;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import ua.com.alexcoffee.enums.StatusEnum;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ModelTest {
    @BeforeClass
    public static void beforeTests() {
        System.out.println("\nTesting class \"Model\" - START.");
    }

    @AfterClass
    public static void afterTests() {
        System.out.println("Testing class \"Model\" - FINISH.\n");
    }

    @Test
    public void equalsTest() {
        System.out.print("-> equals() - ");

        Category category = new Category();

        assertFalse(category.equals(null));
        assertTrue(category.equals(category));

        System.out.println("OK!");
    }

    @Test
    public void hashCodeTest() {
        System.out.print("-> hashCode() - ");

        Status status = new Status(StatusEnum.NEW, "New");
        assertTrue(status.toString().hashCode() == status.hashCode());

        status.setId((long) 2);
        assertTrue(status.getId().hashCode() == status.hashCode());

        System.out.println("OK!");
    }

    @Test
    public void getUnmodifiableListTest() {
        System.out.print("-> getUnmodifiableList() - ");

        assertEquals(Model.getUnmodifiableList(null), Collections.EMPTY_LIST);

        assertTrue(Model.getUnmodifiableList(getTenSalePositions()).size() == 10);

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
