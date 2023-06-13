package com.gratis.operations;

import com.gratis.base.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import java.util.List;
import java.util.Random;


public class ClickOperations {
    WebDriver driver;
    JavascriptExecutor jsdriver;
    private static ClickOperations instance;
    private FindOperations findOperations = FindOperations.instance;


    private WebElement userElement;

    private WebElement getUserElement() {
        return userElement;
    }

    private void setUserElement(WebElement userElement) {
        this.userElement = userElement;
    }


    private static final Logger logger = LogManager.getLogger(ClickOperations.class);
    Actions actions;

    public static ClickOperations getInstance() {
        if (instance == null) {
            synchronized (ClickOperations.class) {
                if (instance == null) {
                    instance = new ClickOperations();
                }
            }
        }
        return instance;
    }

    public ClickOperations() {
        driver = BaseTest.driver;
        jsdriver = (JavascriptExecutor) driver;
        actions = new Actions(driver);

    }

    public void clickItemWithSE(String key){
        WebElement element = findOperations
                .findElement(key);
        if(element.isDisplayed()){
            element.click();
            logger.info("{} objesine Selenium Click ile týklandý.",key);
        }
    }
    public void clickItemWithAC(String key){
        WebElement element = findOperations
                .findElement(key);
        if(element.isDisplayed()){
            actions.click(element).perform();
            logger.info("{} objesine Action Click ile týklandý.",key);
        }
    }


    public void checkIfUrlContainsText(String key){
        WebElement element = getUserElement();
        assert element != null;
        logger.info("{} listesinden rastgele bir elemente týklandý.",key);
        String text = VariousOperations
                .getInstance()
                .turkishToEnglish(element.getAttribute("text"));
        String currentURL = (String) jsdriver.executeScript("return window.location.href;");
        AssertionOperations.getInstance().urlContainsText(currentURL,text);
        logger.info("Seçilen {} alt baþlýðý {} URL'inde mevcut.",element.getAttribute("text"),currentURL);
        }


    public WebElement randomElementClick(String key){
        List<WebElement> elementList = findOperations
                .findElements(key);
        Random random = new Random();

        if(elementList.size()>0) {
            int randomItemPicked = random.nextInt(elementList.size());
            WebElement element = elementList.get(randomItemPicked);
                setUserElement(element);
                element.click();
            logger.info("Rastgele seçilen {} elementine týklandý.", element.getText());
            return element;
            }
        else return null;
    }

    public void randomClickWithAttribute(String key){
        List<WebElement> elementList = findOperations
                .findElements(key);
        Random random = new Random();

        if(elementList.size()>0) {
            int randomItemPicked = random.nextInt(elementList.size());
                while (elementList.get(randomItemPicked).getAttribute("data-bind").contains("marka")) {
                    elementList.remove(randomItemPicked);
                    randomItemPicked = random.nextInt(elementList.size());
                }
            WebElement element = elementList.get(randomItemPicked);
            setUserElement(element);
            element.click();
            logger.info("Rastgele seçilen {} elementine týklandý.", element.getText());
        }
    }
}



