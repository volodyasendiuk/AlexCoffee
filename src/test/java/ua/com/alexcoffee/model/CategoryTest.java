package ua.com.alexcoffee.model;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CategoryTest {

    @BeforeClass
    public static void beforeTests() {
        System.out.println("Testing class \"Category\" - START.\n");
    }

    @AfterClass
    public static void afterTests() {
        System.out.println("Testing class \"Category\" - FINISH.\n");
    }

    @Test
    public void toEqualsTest() {
        System.out.print("-> toEquals() - ");

        Category category = new Category("Category", "cat_url", "", null);
        assertEquals("Categorycat_url", category.toEquals());

        System.out.println("ok!");
    }

    @Test
    public void equalsReflexiveTest() {
        System.out.print("-> Reflexive equals - ");

        Category category = new Category();
        assertTrue(category.equals(category));

        System.out.println("ok!");
    }

    @Test
    public void equalsSymmetricTest() {
        System.out.print("-> Symmetric equals - ");

        Category cat1 = new Category("Category", "cat_url", "Description about some category/", null);
        Category cat2 = new Category("Category", "cat_url", "", null);

        assertTrue(cat1.equals(cat2));
        assertTrue(cat2.equals(cat1));

        System.out.println("ok!");
    }

    @Test
    public void equalsTransitiveTest() {
        System.out.print("-> Transitive equals - ");

        Category cat1 = new Category("Category", "cat_url", "", null);
        Category cat2 = new Category("Category", "cat_url", "", null);
        Category cat3 = new Category("Category", "cat_url", "", null);

        assertTrue(cat1.equals(cat2));
        assertTrue(cat2.equals(cat3));
        assertTrue(cat1.equals(cat3));

        System.out.println("ok!");
    }

    @Test
    public void equalsConsistentTest() {
        System.out.print("-> Consistent equals - ");

        Category cat1 = new Category("Category", "cat_url", "Description about some category/", null);
        Category cat2 = new Category("Category", "cat_url", "", null);

        for (int i = 0; i < 10; i++) {
            assertTrue(cat1.equals(cat2));
        }

        System.out.println("ok!");
    }

    @Test
    public void addProductTest() {
        System.out.print("-> addProduct() - ");

        Category category = new Category();
        for (int i = 0; i < 10; i++) {
            category.addProduct(new Product());
        }
        assertEquals(10, category.getProducts().size());

        System.out.println("ok!");
    }

    @Test
    public void addProductsTest() {
        System.out.print("-> addProducts() - ");

        List<Product> products = getTenProducts();

        Category category = new Category();
        category.addProducts(products);
        assertEquals(10, category.getProducts().size());

        System.out.println("ok!");
    }

    @Test
    public void removeProductTest() {
        System.out.print("-> removeProduct() - ");

        Category category = new Category();

        Product product = new Product();
        category.addProduct(product);
        category.addProducts(getTenProducts());

        category.removeProduct(product);

        assertEquals(10, category.getProducts().size());

        System.out.println("ok!");
    }

    @Test
    public void removeProductsTest() {
        System.out.print("-> removeProducts() - ");

        Category category = new Category();
        List<Product> products = getTenProducts();

        category.addProducts(products);
        category.addProducts(getTenProducts());
        category.removeProducts(products);

        assertEquals(10, category.getProducts().size());

        System.out.println("ok!");
    }

    @Test
    public void clearProductsTest() {
        System.out.print("-> clearProducts() - ");

        Category category = new Category();
        List<Product> products = getTenProducts();
        category.addProducts(products);
        category.clearProducts();

        assertEquals(0, category.getProducts().size());

        System.out.println("ok!");
    }

    @Test
    public void getPhotoTest() {
        System.out.print("-> getPhoto() - ");

        Category category = new Category("Category", "cat_url", "", null);
        assertNull(category.getPhoto());

        category.setPhoto(new Photo());
        assertNotNull(category.getPhoto());

        System.out.println("ok!");
    }

    @Ignore
    private static List<Product> getTenProducts() {
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            products.add(new Product());
        }

        return products;
    }
}