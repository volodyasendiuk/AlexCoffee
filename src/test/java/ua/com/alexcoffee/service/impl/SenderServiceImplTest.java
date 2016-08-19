package ua.com.alexcoffee.service.impl;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import ua.com.alexcoffee.model.Order;
import ua.com.alexcoffee.service.SenderService;
import ua.com.alexcoffee.tools.MockMain;
import ua.com.alexcoffee.tools.MockService;

import java.util.Properties;

import static org.junit.Assert.assertNotNull;

public class SenderServiceImplTest {

    private static SenderService senderService;

    @BeforeClass
    public static void setUp() {
        System.out.println("\nTesting class \"RoleServiceImpl\" - START.");

        senderService = MockService.getSenderService();
    }

    @AfterClass
    public static void tearDown() {
        System.out.println("Testing class \"RoleServiceImpl\" - FINISH.\n");
    }

    @Test
    public void getPropertiesTest() throws Exception {
        System.out.println("-> getProperties() - ");

        Properties properties = senderService.getSSLProperties();
        assertNotNull(properties);

        properties = senderService.getTLSProperties();
        assertNotNull(properties);

        System.out.println("OK!");
    }

    @Test
    public void noExceptionOfVoidMethodTest() throws Exception {
        System.out.print("-> noExceptionOfVoidMethod() - ");

        Order order = MockMain.getOrder();
        senderService.send(order);

        System.out.println("OK!");
    }
}