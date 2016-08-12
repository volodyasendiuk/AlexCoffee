package ua.com.alexcoffee.model;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import ua.com.alexcoffee.enums.RoleEnum;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserTest {

    @BeforeClass
    public static void beforeTests() {
        System.out.println("Testing class \"User\" - START.\n");
    }

    @AfterClass
    public static void afterTests() {
        System.out.println("Testing class \"User\" - FINISH.\n");
    }

    @Test
    public void toEqualsTest() {
        System.out.print("-> toEquals() - ");

        User user = new User("User", "someemail", "+380000000000", null);
        assertEquals(user.getName() + user.getEmail() + user.getPhone(), user.toEquals());

        System.out.println("ok!");
    }

    @Test
    public void equalsReflexiveTest() {
        System.out.print("-> Reflexive equals - ");

        User user = new User("User", "someemail", "+380000000000", null);
        assertTrue(user.equals(user));

        System.out.println("ok!");
    }

    @Test
    public void equalsSymmetricTest() {
        System.out.print("-> Symmetric equals - ");

        User user1 = new User("User", "someemail", "+380000000000", null);
        User user2 = new User("User", "someemail", "+380000000000", null);

        assertTrue(user1.equals(user2));
        assertTrue(user2.equals(user1));

        System.out.println("ok!");
    }

    @Test
    public void equalsTransitiveTest() {
        System.out.print("-> Transitive equals - ");

        User user1 = new User("User", "someemail", "+380000000000", null);
        User user2 = new User("User", "someemail", "+380000000000", null);
        User user3 = new User("User", "someemail", "+380000000000", null);

        assertTrue(user1.equals(user2));
        assertTrue(user2.equals(user3));
        assertTrue(user1.equals(user3));

        System.out.println("ok!");
    }

    @Test
    public void equalsConsistentTest() {
        System.out.print("-> Consistent equals - ");

        User user1 = new User("User", "someemail", "+380000000000", null);
        User user2 = new User("User", "someemail", "+380000000000", null);

        for (int i = 0; i < 10; i++) {
            assertTrue(user1.equals(user2));
        }

        System.out.println("ok!");
    }

    @Test
    public void getRoleTest() {
        System.out.print("-> getRole() - ");

        User user = new User("User", "someemail", "+380000000000", new Role(RoleEnum.ADMIN));

        assertEquals(new Role(RoleEnum.ADMIN), user.getRole());

        System.out.println("ok!");
    }
}