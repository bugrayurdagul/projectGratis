package com.gratis.base;

import com.gratis.operations.FindOperations;
import com.gratis.operations.VariousOperations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;

public class BaseTest {

    public static WebDriver driver;
    private static final Logger logger = LogManager.getLogger(BaseTest.class);



    public static WebDriver getDriver() {
        if (driver == null) {
            driver = DriverConfigurations.configureWebDriver();
            driver.manage().window().maximize();
            driver.get("https://www.gratis.com/");
        }
        return driver;
    }

    @BeforeAll
    static void setUp(){
        getDriver();
        FindOperations findOperations = new FindOperations();
        start();
    }


    @AfterAll
    protected static void tearDown(){
        if (driver!=null){
            driver.close();
            driver.quit();
        }
        end();
    }

    private static void start(){
        logger.info("********************************TEST BAÞLADI********************************");

    }
    private static void end(){
        logger.info("********************************TEST BÝTTÝ********************************");

    }

}
