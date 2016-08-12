package ua.com.alexcoffee.model;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import ua.com.alexcoffee.enums.RoleEnum;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class RoleTest {
    @BeforeClass
    public static void beforeTests() {
        System.out.println("Testing class \"Role\" - START.\n");
    }

    @AfterClass
    public static void afterTests() {
        System.out.println("Testing class \"Role\" - FINISH.\n");
    }

    @Test
    public void toEqualsTest() {
        System.out.print("-> toEquals() - ");

        Role role = new Role();
        assertEquals(role.toString(), role.toEquals());

        role = new Role(RoleEnum.ADMIN);
        assertEquals(role.toString(), role.toEquals());

        System.out.println("ok!");
    }

    @Test
    public void equalsReflexiveTest() {
        System.out.print("-> Reflexive equals - ");

        Role role = new Role();
        assertTrue(role.equals(role));

        role = new Role(RoleEnum.ADMIN);
        assertTrue(role.equals(role));

        System.out.println("ok!");
    }

    @Test
    public void equalsSymmetricTest() {
        System.out.print("-> Symmetric equals - ");

        Role role1 = new Role(RoleEnum.ADMIN);
        Role role2 = new Role(RoleEnum.ADMIN);

        assertTrue(role1.equals(role2));
        assertTrue(role2.equals(role1));

        System.out.println("ok!");
    }

    @Test
    public void equalsTransitiveTest() {
        System.out.print("-> Transitive equals - ");

        Role role1 = new Role(RoleEnum.ADMIN);
        Role role2 = new Role(RoleEnum.ADMIN);
        Role role3 = new Role(RoleEnum.ADMIN);

        assertTrue(role1.equals(role2));
        assertTrue(role2.equals(role3));
        assertTrue(role1.equals(role3));

        System.out.println("ok!");
    }

    @Test
    public void equalsConsistentTest() {
        System.out.print("-> Consistent equals - ");

        Role role1 = new Role(RoleEnum.ADMIN);
        Role role2 = new Role(RoleEnum.ADMIN);

        for (int i = 0; i < 10; i++) {
            assertTrue(role1.equals(role2));
        }

        System.out.println("ok!");
    }

    @Test
    public void addUserTest() {
        System.out.print("-> addUser() - ");

        Role role = new Role();
        for (int i = 0; i < 10; i++) {
            role.addUser(new User());
        }
        assertEquals(10, role.getUsers().size());

        System.out.println("ok!");
    }

    @Test
    public void addUsersTest() {
        System.out.print("-> addUsers() - ");

        List<User> users = getTenUsers();

        Role role = new Role();
        role.addUsers(users);

        assertEquals(10, role.getUsers().size());

        System.out.println("ok!");
    }

    @Test
    public void removeUserTest() {
        System.out.print("-> removeUser() - ");

        Role role = new Role();

        User user = new User();
        role.addUser(user);
        role.addUsers(getTenUsers());

        role.removeUser(user);

        assertEquals(10, role.getUsers().size());

        System.out.println("ok!");
    }

    @Test
    public void removeUsersTest() {
        System.out.print("-> removeUsers() - ");

        Role role = new Role();

        List<User> users = getTenUsers();
        role.addUsers(users);

        role.removeUsers(users);

        assertEquals(0, role.getUsers().size());

        System.out.println("ok!");
    }

    @Test
    public void clearUsersTest() {
        System.out.print("-> clearUsers() - ");

        Role role = new Role();
        List<User> users = getTenUsers();
        role.addUsers(users);

        role.clearUsers();

        assertEquals(0, role.getUsers().size());

        System.out.println("ok!");
    }

    @Ignore
    private static List<User> getTenUsers() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            users.add(new User());
        }

        return users;
    }
}