package com.gratis.pages;

import com.gratis.operations.AssertionOperations;
import com.gratis.operations.ClickOperations;
import com.gratis.operations.VariousOperations;
import com.gratis.operations.WaitOperations;

public class HomePage {
    ClickOperations clickOperations;
    WaitOperations waitOperations;

    AssertionOperations assertionOperations;
    VariousOperations variousOperations;
    public HomePage() {
        clickOperations = ClickOperations.getInstance();
        waitOperations = WaitOperations.getInstance();
        variousOperations = VariousOperations.getInstance();
        assertionOperations = AssertionOperations.getInstance();
    }

    public void homePageOpenControl(){

        assertionOperations.isElementDisplayed("ANA SAYFA KONTROL1");
        clickOperations.clickItemWithSE("KABUL ET");
        waitOperations.waitForElement("SAYFA YUKLENME KONTROL");
    }

    public void hoverNavAndClickRandomSubNav(){
        variousOperations.mouseHoverToElement("CÝLT BAKIM");
        waitOperations.waitForElement("CÝLT BAKIM NAV LIST");
        waitOperations.waitBySecond(1);
        clickOperations.randomClickWithAttribute("CÝLT BAKIM NAV LIST");
        waitOperations.waitForElement("SEÇÝLEN ALT KATEGORÝ");
        waitOperations.waitForElement("SAYFA YUKLENME KONTROL");
    }


}
