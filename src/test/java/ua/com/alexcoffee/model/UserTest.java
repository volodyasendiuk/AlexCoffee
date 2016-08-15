package ua.com.alexcoffee.model;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import ua.com.alexcoffee.enums.RoleEnum;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class UserTest {

    @BeforeClass
    public static void beforeTests() {
        System.out.println("\nTesting class \"User\" - START.");
    }

    @AfterClass
    public static void afterTests() {
        System.out.println("Testing class \"User\" - FINISH.\n");
    }

    @Test
    public void toStringTest() {
        System.out.print("-> toString() - ");

        String name = "Name";
        String email = "Email";
        String phone = "Phone";
        Role role = new Role(RoleEnum.ADMIN, "ADMIN");

        User user = new User(name, email, phone, role);
        String line = "Name: " + name + "\nRole: " + role.getDescription() + "\nEmail: " + email
                + "\nPhone: " + phone;

        assertTrue(user.toString().equals(line));

        System.out.println("OK!");
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
    public void userDetailsMethodTest() {
        System.out.print("-> userDetailsMethod() - ");

        User user = new User();
        assertTrue(user.isEnabled());
        assertTrue(user.isAccountNonExpired());
        assertTrue(user.isAccountNonLocked());
        assertTrue(user.isCredentialsNonExpired());

        System.out.println("FAIL!");
    }

    @Test
    public void getAuthoritiesTest() {
        System.out.print("-> getAuthorities() - ");

        User user = new User();
        user.setRole(new Role(RoleEnum.ADMIN, "ADMIN"));

        assertNotNull(user.getAuthorities());

        System.out.println("OK!");
    }

    @Test
    public void initializeTest() {
        System.out.print("-> initialize() - ");

        String name = "name";
        String username = "username";
        String password = "pass";
        String email = "email";
        String phone = "phone";
        String vkontakte = "vk";
        String facebook = "fb";
        String skype = "sk";
        String description = "description";
        Role role = new Role();

        User user = new User();
        user.initialize(name, username, password, email, phone, vkontakte, facebook, skype, description, role);

        assertTrue(user.getName().equals(name));
        assertTrue(user.getUsername().equals(username));
        assertTrue(user.getPassword().equals(password));
        assertTrue(user.getEmail().equals(email));
        assertTrue(user.getPhone().equals(phone));
        assertTrue(user.getVkontakte().equals(vkontakte));
        assertTrue(user.getFacebook().equals(facebook));
        assertTrue(user.getSkype().equals(skype));
        assertTrue(user.getDescription().equals(description));
        assertTrue(user.getRole().equals(role));

        System.out.println("OK!");
    }

    @Test
    public void setAndGetNameTest() {
        System.out.print("-> setAndGetName() - ");

        User user = new User();
        user.setName(null);
        assertNotNull(user.getName());
        assertTrue(user.getName().isEmpty());

        String name = "NAME";
        user.setName(name);
        assertTrue(user.getName().equals(name));

        System.out.println("OK!");
    }

    @Test
    public void setAndGetUsernameTest() {
        System.out.print("-> setAndGetUsername() - ");

        User user = new User();
        user.setUsername(null);
        assertNotNull(user.getUsername());
        assertTrue(user.getUsername().isEmpty());

        String username = "USERNAME";
        user.setUsername(username);
        assertTrue(user.getUsername().equals(username));

        System.out.println("OK!");
    }

    @Test
    public void setAndGetPasswordTest() {
        System.out.print("-> setAndGetPassword() - ");

        User user = new User();
        user.setPassword(null);
        assertNotNull(user.getPassword());
        assertTrue(user.getPassword().isEmpty());

        String password = "pass";
        user.setPassword(password);
        assertTrue(user.getPassword().equals(password));

        System.out.println("OK!");
    }

    @Test
    public void setAndGetEmailTest() {
        System.out.print("-> setAndGetEmail() - ");

        User user = new User();
        user.setEmail(null);
        assertNotNull(user.getEmail());
        assertTrue(user.getEmail().isEmpty());

        String email = "email";
        user.setEmail(email);
        assertTrue(user.getEmail().equals(email));

        System.out.println("OK!");
    }

    @Test
    public void setAndGetPhoneTest() {
        System.out.print("-> setAndGetPhone() - ");

        User user = new User();
        user.setPhone(null);
        assertNotNull(user.getPhone());
        assertTrue(user.getPhone().isEmpty());

        String phone = "phone";
        user.setPhone(phone);
        assertTrue(user.getPhone().equals(phone));

        System.out.println("OK!");
    }

    @Test
    public void setAndGetVkontakteTest() {
        System.out.print("-> setAndGetVkontakte() - ");

        User user = new User();
        user.setVkontakte(null);
        assertNotNull(user.getVkontakte());
        assertTrue(user.getVkontakte().isEmpty());

        String vkontakte = "vk";
        user.setVkontakte(vkontakte);
        assertTrue(user.getVkontakte().equals(vkontakte));

        System.out.println("OK!");
    }

    @Test
    public void setAndGetFacebookTest() {
        System.out.print("-> setAndGetFacebook() - ");

        User user = new User();
        user.setFacebook(null);
        assertNotNull(user.getFacebook());
        assertTrue(user.getFacebook().isEmpty());

        String facebook = "fb";
        user.setFacebook(facebook);
        assertTrue(user.getFacebook().equals(facebook));

        System.out.println("OK!");
    }

    @Test
    public void setAndGetSkypeTest() {
        System.out.print("-> setAndGetSkype() - ");

        User user = new User();
        user.setSkype(null);
        assertNotNull(user.getSkype());
        assertTrue(user.getSkype().isEmpty());

        String skype = "skype";
        user.setSkype(skype);
        assertTrue(user.getSkype().equals(skype));

        System.out.println("OK!");
    }

    @Test
    public void setAndGetDescriptionTest() {
        System.out.print("-> setAndGetDescription() - ");

        User user = new User();
        user.setDescription(null);
        assertNotNull(user.getDescription());
        assertTrue(user.getDescription().isEmpty());

        String description = "description";
        user.setDescription(description);
        assertTrue(user.getDescription().equals(description));

        System.out.println("OK!");
    }

    @Test
    public void setAndGetRoleTest() {
        System.out.print("-> setAndGetRole() - ");

        Role role = new Role(RoleEnum.ADMIN, "ADMIN");
        User user = new User();
        user.setRole(role);

        assertNotNull(user.getRole());
        assertTrue(user.getRole().equals(role));

        System.out.println("OK!");
    }

    @Test
    public void setAndGetClientOrdersTest() {
        System.out.print("-> setAndGetClientOrders() - ");

        User user = new User();
        user.setClientOrders(getTenOrders());
        assertTrue(user.getClientOrders().size() == 10);

        System.out.println("OK!");
    }

    @Test
    public void setAndGetManagerOrdersTest() {
        System.out.print("-> setAndGetManagerOrders() - ");

        User user = new User();
        user.setManagerOrders(getTenOrders());
        assertTrue(user.getManagerOrders().size() == 10);

        System.out.println("OK!");
    }

    @Ignore
    private static List<Order> getTenOrders() {
        List<Order> orders = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            orders.add(new Order());
        }

        return orders;
    }
}
