package ua.com.alexcoffee.model;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ProductTest {
    @BeforeClass
    public static void beforeTests() {
        System.out.println("Testing class \"Product\" - START.\n");
    }

    @AfterClass
    public static void afterTests() {
        System.out.println("Testing class \"Product\" - FINISH.\n");
    }

    @Test
    public void toEqualsTest() {
        System.out.print("-> toEquals() - ");

        Product product = new Product("Title", "url", new Category(), new Photo(), 1000);
        assertEquals(product.getArticle() + "Titleurl" + product.getPrice(), product.toEquals());

        System.out.println("ok!");
    }

    @Test
    public void equalsReflexiveTest() {
        System.out.print("-> Reflexive equals - ");

        Product product = new Product();
        assertTrue(product.equals(product));

        System.out.println("ok!");
    }

    @Test
    public void equalsSymmetricTest() {
        System.out.print("-> Symmetric equals - ");

        Product product1 = new Product();
        Product product2 = new Product();
        product2.setArticle(product1.getArticle());

        assertTrue(product1.equals(product2));
        assertTrue(product2.equals(product1));

        System.out.println("ok!");
    }

    @Test
    public void equalsTransitiveTest() {
        System.out.print("-> Transitive equals - ");

        Product product1 = new Product();
        Product product2 = new Product();
        product2.setArticle(product1.getArticle());
        Product product3 = new Product();
        product3.setArticle(product2.getArticle());

        assertTrue(product1.equals(product2));
        assertTrue(product2.equals(product3));
        assertTrue(product1.equals(product3));

        System.out.println("ok!");
    }

    @Test
    public void equalsConsistentTest() {
        System.out.print("-> Consistent equals - ");

        Product product1 = new Product();
        Product product2 = new Product();
        product2.setArticle(product1.getArticle());

        for (int i = 0; i < 10; i++) {
            assertTrue(product1.equals(product2));
        }

        System.out.println("ok!");
    }

    @Test
    public void newArticleTest() {
        System.out.print("-> newArticle() - ");

        Product product = new Product();
        int article = product.getArticle();

        product.newArticle();
        int newArticle = product.getArticle();

        assertTrue(article != newArticle);

        System.out.println("ok!");
    }

    @Test
    public void getPrice() {
        System.out.print("-> getPrice() - ");

        Product product = new Product();

        product.setPrice(123);
        assertTrue(product.getPrice() == 123);

        product.setPrice(-50);
        assertTrue(product.getPrice() == 0);

        System.out.println("ok!");
    }
}