package ua.com.alexcoffee.model;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import ua.com.alexcoffee.enums.RoleEnum;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class RoleTest {

    @BeforeClass
    public static void beforeTests() {
        System.out.println("\nTesting class \"Role\" - START.");
    }

    @AfterClass
    public static void afterTests() {
        System.out.println("Testing class \"Role\" - FINISH.\n");
    }

    @Test
    public void toStringTest() {
        System.out.print("-> toString() - ");

        RoleEnum title = RoleEnum.ADMIN;
        String description = "Description";

        Role role = new Role(title, description);

        String line = "Title: " + title.name() + "\nDescription: " + description;

        assertTrue(role.toString().equals(line));

        System.out.println("FAIL!");
    }

    @Test
    public void toEqualsTest() {
        System.out.print("-> toEquals() - ");

        Role role = new Role(RoleEnum.ADMIN, "ADMIN");
        assertTrue(role.toEquals().equals(RoleEnum.ADMIN.name()));

        System.out.println("OK!");
    }

    @Test
    public void equalsReflexiveTest() {
        System.out.print("-> Reflexive equals - ");

        Role role = new Role();
        assertTrue(role.equals(role));

        role = new Role(RoleEnum.ADMIN, "ADMIN");
        assertTrue(role.equals(role));

        System.out.println("OK!");
    }

    @Test
    public void equalsSymmetricTest() {
        System.out.print("-> Symmetric equals - ");

        Role role1 = new Role(RoleEnum.ADMIN, "ADMIN");
        Role role2 = new Role(RoleEnum.ADMIN, "");

        assertTrue(role1.equals(role2));
        assertTrue(role2.equals(role1));

        System.out.println("OK!");
    }

    @Test
    public void equalsTransitiveTest() {
        System.out.print("-> Transitive equals - ");

        Role role1 = new Role(RoleEnum.ADMIN, "ADMIN");
        Role role2 = new Role(RoleEnum.ADMIN, "");
        Role role3 = new Role(RoleEnum.ADMIN, "ADMIN");

        assertTrue(role1.equals(role2));
        assertTrue(role2.equals(role3));
        assertTrue(role1.equals(role3));

        System.out.println("OK!");
    }

    @Test
    public void equalsConsistentTest() {
        System.out.print("-> Consistent equals - ");

        Role role1 = new Role(RoleEnum.ADMIN, "ADMIN");
        Role role2 = new Role(RoleEnum.ADMIN, "");

        for (int i = 0; i < 10; i++) {
            assertTrue(role1.equals(role2));
        }

        System.out.println("OK!");
    }

    @Test
    public void addUserTest() {
        System.out.print("-> addUser() - ");

        Role role = new Role();
        for (int i = 0; i < 10; i++) {
            role.addUser(new User());
        }
        assertEquals(10, role.getUsers().size());

        System.out.println("OK!");
    }

    @Test
    public void addUsersTest() {
        System.out.print("-> addUsers() - ");

        List<User> users = getTenUsers();

        Role role = new Role();
        role.addUsers(users);

        assertEquals(10, role.getUsers().size());

        System.out.println("OK!");
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

        System.out.println("OK!");
    }

    @Test
    public void removeUsersTest() {
        System.out.print("-> removeUsers() - ");

        Role role = new Role();

        List<User> users = getTenUsers();
        role.addUsers(users);

        role.removeUsers(users);

        assertEquals(0, role.getUsers().size());

        System.out.println("OK!");
    }

    @Test
    public void clearUsersTest() {
        System.out.print("-> clearUsers() - ");

        Role role = new Role();
        List<User> users = getTenUsers();
        role.addUsers(users);

        role.clearUsers();

        assertEquals(0, role.getUsers().size());

        System.out.println("OK!");
    }

    @Test
    public void setAndGetUsersTest() {
        System.out.print("-> setAndGetUsers() - ");

        Role role = new Role(RoleEnum.ADMIN, "");
        role.setUsers(getTenUsers());

        assertNotNull(role.getUsers());
        assertTrue(role.getUsers().size() == 10);

        System.out.println("OK!");
    }

    @Test
    public void setAndGetTitleTest() {
        System.out.print("-> setAndGetTitle() - ");

        Role role = new Role();
        role.setTitle(RoleEnum.ADMIN);

        assertNotNull(role.getTitle());
        assertTrue(role.getTitle().equals(RoleEnum.ADMIN));

        System.out.println("OK!");
    }

    @Test
    public void setDescriptionTest() {
        System.out.print("-> setAndGetDescription() - ");

        Role role = new Role();
        role.setDescription(null);
        assertNotNull(role.getDescription());
        assertTrue(role.getDescription().isEmpty());

        String description = "Description";
        role.setDescription(description);
        assertTrue(role.getDescription().equals(description));

        System.out.println("OK!");
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
