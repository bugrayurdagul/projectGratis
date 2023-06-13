package com.gratis.pages;

import com.gratis.operations.AssertionOperations;
import com.gratis.operations.ClickOperations;
import com.gratis.operations.VariousOperations;
import com.gratis.operations.WaitOperations;

import java.io.IOException;

public class ProductIndividualPage {

    ClickOperations clickOperations;
    WaitOperations waitOperations;

    AssertionOperations assertionOperations;
    VariousOperations variousOperations;

    public ProductIndividualPage() {
        clickOperations = ClickOperations.getInstance();
        waitOperations = WaitOperations.getInstance();
        variousOperations = VariousOperations.getInstance();
        assertionOperations = AssertionOperations.getInstance();
    }

    public void checkIfPageLoadedd(){
        waitOperations.waitUntilElementAppears("�R�N B�LG�S�");
    }

    public void writeProductNameAndPriceToExcel() throws IOException {
        variousOperations.writeToExcel("�R�N B�LG�S�");
        variousOperations.costWriteToExcel("�R�N TUTARI");
    }

    public void clickAddToCartButton(){
        waitOperations.waitUntilElementDisappears("YUKLENME SPINNER");
        clickOperations.clickItemWithAC("SEPETE EKLE");
        waitOperations.waitBySecond(1);
    }
    public void readFromExcelAndWriteToBoxes() throws IOException {

        waitOperations.waitForElement("G�R�� MODAL KONTROL");
        variousOperations.readFromExcelAndWriteBoxes("EPOSTA","��FRE");
        waitOperations.waitBySecond(5);
    }




}
