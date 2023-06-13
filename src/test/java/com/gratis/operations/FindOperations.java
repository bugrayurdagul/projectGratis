package com.gratis.operations;

import org.openqa.selenium.WebElement;

import java.util.List;

public class FindOperations {
    public static final FindOperations instance = new FindOperations();


/*    public static FindOperations getInstance() {
        if (instance == null) {
            synchronized (FindOperations.class) {
                if (instance == null) {
                    instance = new FindOperations();
                }
            }
        }
        return instance;
    }*/

    public WebElement findElement(String key) {
        return WaitOperations
                .getInstance()
                .waitForElement(key);
    }
    public List<WebElement> findElements(String key) {
        return WaitOperations
                .getInstance()
                .waitForElements(key);
    }
}
