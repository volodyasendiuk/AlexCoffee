package ua.com.alexcoffee.model;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PhotoTest {
    @BeforeClass
    public static void beforeTests() {
        System.out.println("Testing class \"Photo\" - START.\n");
    }

    @AfterClass
    public static void afterTests() {
        System.out.println("Testing class \"Photo\" - FINISH.\n");
    }

    @Test
    public void toEqualsTest() {
        System.out.print("-> toEquals() - ");

        Photo photo = new Photo("Photo", "link1", "link2");
        assertEquals(photo.toString(), photo.toEquals());

        System.out.println("ok!");
    }

    @Test
    public void equalsReflexiveTest() {
        System.out.print("-> Reflexive equals - ");

        Photo photo = new Photo();
        assertTrue(photo.equals(photo));

        System.out.println("ok!");
    }

    @Test
    public void equalsSymmetricTest() {
        System.out.print("-> Symmetric equals - ");

        Photo photo1 = new Photo("Photo", "link1", "link2");
        Photo photo2 = new Photo("Photo", "link1", "link2");

        assertTrue(photo1.equals(photo2));
        assertTrue(photo2.equals(photo1));

        System.out.println("ok!");
    }

    @Test
    public void equalsTransitiveTest() {
        System.out.print("-> Transitive equals - ");

        Photo photo1 = new Photo("Photo", "link1", "link2");
        Photo photo2 = new Photo("Photo", "link1", "link2");
        Photo photo3 = new Photo("Photo", "link1", "link2");

        assertTrue(photo1.equals(photo2));
        assertTrue(photo2.equals(photo3));
        assertTrue(photo1.equals(photo3));

        System.out.println("ok!");
    }

    @Test
    public void equalsConsistentTest() {
        System.out.print("-> Consistent equals - ");

        Photo photo1 = new Photo("Photo", "link1", "link2");
        Photo photo2 = new Photo("Photo", "link1", "link2");

        for (int i = 0; i < 10; i++) {
            assertTrue(photo1.equals(photo2));
        }

        System.out.println("ok!");
    }
}