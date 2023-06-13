package com.gratis.pages;

import com.gratis.operations.AssertionOperations;
import com.gratis.operations.ClickOperations;
import com.gratis.operations.VariousOperations;
import com.gratis.operations.WaitOperations;

public class ProductsPage {
    ClickOperations clickOperations;
    WaitOperations waitOperations;
    VariousOperations variousOperations;

    public ProductsPage() {
       clickOperations = ClickOperations.getInstance();
       waitOperations = WaitOperations.getInstance();
       variousOperations = VariousOperations.getInstance();
    }

    public void checkIfPageLoaded(){
        waitOperations.waitBySecond(1);
        clickOperations.checkIfUrlContainsText("C�LT BAKIM NAV LIST");
        waitOperations.waitForElements("SOL CHECKBOXLAR");
    }
    public void clickRandomCheckBoxes(){
        clickOperations.randomElementClick("SOL CHECKBOXLAR");
        waitOperations.waitForElement("F�LTRE KONTROL");
        waitOperations.waitUntilElementDisappears("YUKLENME SPINNER");
        clickOperations.randomElementClick("SOL CHECKBOXLAR");
        waitOperations.waitUntilElementDisappears("YUKLENME SPINNER");
    }
    public void checkIfLeftFiltersAppears(){
        variousOperations.itemsOnLeft("SOL F�LTRELER");
    }
    public void clickRandomProduct(){
        clickOperations.randomElementClick("�R�NLER");
    }

}
