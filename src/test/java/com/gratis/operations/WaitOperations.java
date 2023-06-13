package com.gratis.operations;

import com.gratis.base.BaseTest;
import com.gratis.locators.LocatorManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import java.time.Duration;
import java.util.List;

public class WaitOperations {
    private static WaitOperations instance;
    private FluentWait<WebDriver> waitToAppear;
    private FluentWait<WebDriver> waitToDisappear;

    LocatorManager locatorManager;

    private static final Logger logger = LogManager.getLogger(WaitOperations.class);


    public WaitOperations() {
        waitToAppear = new FluentWait<>(BaseTest.getDriver());
        waitToAppear.withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(300))
                .ignoring(NoSuchElementException.class);
        locatorManager = LocatorManager.getInstance();

    }

    public static WaitOperations getInstance() {
        if (instance == null) {
            synchronized (WaitOperations.class) {
                if (instance == null) {
                    instance = new WaitOperations();
                }
            }
        }
        return instance;
    }

    public WebElement waitForElement(String key){
        return waitToAppear.until(ExpectedConditions.presenceOfElementLocated(locatorManager.getBy(key)));
    }

    public List<WebElement> waitForElements(String key) {
        return waitToAppear.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locatorManager.getBy(key)));
    }

    public boolean waitUntilElementAppears(String key){
        WebElement element = waitForElement(key);
        return element != null;
    }

    public void waitBySecond(long seconds){
        try {
            Thread.sleep(seconds*1000);
            logger.info("{} saniye beklendi.",seconds);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void waitUntilElementDisappears(String key){
        waitToDisappear = new FluentWait<>(BaseTest.getDriver())
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class);
        waitToDisappear.until(driver -> driver.findElements(locatorManager.getBy(key)).isEmpty());
        logger.info("{} elementi kaybolana kadar beklendi.",key);


    }


}
;