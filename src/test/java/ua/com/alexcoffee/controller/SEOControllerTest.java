package ua.com.alexcoffee.controller;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import ua.com.alexcoffee.tools.MockController;

import static junit.framework.Assert.assertFalse;
import static junit.framework.TestCase.assertNotNull;

public class SEOControllerTest {

    private static SEOController seoController;

    @BeforeClass
    public static void setUp() {
        System.out.println("\nTesting class \"SEOController\" - START.\n");

        seoController = MockController.getSeoController();
    }

    @AfterClass
    public static void tearDown() {
        System.out.println("Testing class \"SEOController\" - FINISH.\n");
    }

    @Test
    public void getRobotsTxtTest() throws Exception {
        System.out.print("-> getRobotsTxt() - ");

        assertNotNull(seoController.getRobotsTxt());
        assertFalse(seoController.getRobotsTxt().isEmpty());

        System.out.println("OK!");
    }

    @Test
    public void getSiteMapXmlTest() throws Exception {
        System.out.print("-> getSiteMapXml() - ");

        assertNotNull(seoController.getSiteMapXml());
        assertFalse(seoController.getSiteMapXml().isEmpty());

        System.out.println("OK!");
    }
}
