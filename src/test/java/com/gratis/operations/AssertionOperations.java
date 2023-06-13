package com.gratis.operations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;

public class AssertionOperations {

    private static AssertionOperations instance;
    private FindOperations findOperations = FindOperations.instance;



    private static final Logger logger = LogManager.getLogger(AssertionOperations.class);



    public static AssertionOperations getInstance() {
        if (instance == null) {
            synchronized (AssertionOperations.class) {
                if (instance == null) {
                    instance = new AssertionOperations();
                }
            }
        }
        return instance;
    }

    public void objectContainsText(String key, String text){
        WebElement element = findOperations
                .findElement(key);
        if (element.getText().contains(text)){
            Assertions.assertTrue(element.getText().contains(text));
            logger.info("{} objesi \"{}\" textini içeriyor.",key,text);
        }
    }

    public void urlContainsText(String url,String text ){

        if (url!=null){
            Assertions.assertTrue(url.contains(text));
            logger.info("URL \"{}\" textini içeriyor.",text);
        }
    }

    public void isElementDisplayed(String key){
        WebElement element = findOperations
                .findElement(key);
        if (element.isDisplayed()){
            logger.info("{} objesi sayfa üzerinde görüntülendi.",key);
        }
        else
        {
            Assertions.assertTrue(element.isDisplayed(), key + " objesi sayfa üzerinde görüntülenemedi.");
        }
    }



}
