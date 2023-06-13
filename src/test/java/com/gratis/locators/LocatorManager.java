package com.gratis.locators;

import com.gratis.locator_enum.LocatorTypeEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;


import java.util.HashMap;
import java.util.Map;

public class LocatorManager {
    private static LocatorManager instance;
    private final Map<String, Locator> locators;

    private static final Logger logger = LogManager.getLogger(LocatorManager.class);





    private LocatorManager() {
        locators = new HashMap<>();
        addLocator("ANA SAYFA KONTROL1","//div[@id='alert-page-change' and text()='homePage yüklendi.']",LocatorTypeEnum.XPATH);
        addLocator("SAYFA YUKLENME KONTROL","[class='ins-instory']", LocatorTypeEnum.CSS_SELECTOR);
        addLocator("YUKLENME SPINNER","cc-spinner", LocatorTypeEnum.ID);
        addLocator("KABUL ET","//button[text()='Kabul Et']", LocatorTypeEnum.XPATH);
        addLocator("GÝRÝÞ YAP","[data-drop='log-box']", LocatorTypeEnum.CSS_SELECTOR);
        addLocator("CÝLT BAKIM","[class='mega-menu']+a[data-bind=\"ccLink: {url: '/cilt-bakim/kategori/502'}\"]", LocatorTypeEnum.CSS_SELECTOR);
        addLocator("CÝLT BAKIM NAV LIST","//ul/li[@class='active']//a[@class='mm-head-navs wo-icon']", LocatorTypeEnum.XPATH);
        addLocator("SEÇÝLEN ALT KATEGORÝ","//link[contains(@href,'') and @rel='canonical']", LocatorTypeEnum.XPATH);
        addLocator("SOL CHECKBOXLAR","[class=\"form-group checkbox-structure\"]", LocatorTypeEnum.CSS_SELECTOR);
        addLocator("FÝLTRE KONTROL","[data-bind=\"foreach: visibleRefinements\"]>li", LocatorTypeEnum.CSS_SELECTOR);
        addLocator("SOL FÝLTRELER","[data-bind='foreach: visibleRefinements']>li>span[class='filter-value']",LocatorTypeEnum.CSS_SELECTOR);
        addLocator("ÜRÜNLER","//div[@class=\"product-card-price clearfix\"]/ancestor::div[@class=\"infos\"]//h3[@data-bind=\"text: name\"]",LocatorTypeEnum.XPATH);
        addLocator("ÜRÜN BÝLGÝSÝ","h1[data-bind=\"text: displayName\"]",LocatorTypeEnum.CSS_SELECTOR);
        addLocator("ÜRÜN TUTARI","[class=\"price-card list-price-card\"]>div>g-price>span>span",LocatorTypeEnum.CSS_SELECTOR);
        addLocator("SEPETE EKLE","//ul[@class=\"product-options\"]//a[text()='SEPETE EKLE']",LocatorTypeEnum.XPATH);
        addLocator("GÝRÝÞ MODAL KONTROL","[id=\"login-register-modal\"][aria-hidden=\"false\"]",LocatorTypeEnum.CSS_SELECTOR);
        addLocator("EPOSTA","[data-bind=\"validatableValue: login\"]",LocatorTypeEnum.CSS_SELECTOR);
        addLocator("ÞÝFRE","[data-bind=\"validatableValue: password\"]",LocatorTypeEnum.CSS_SELECTOR);

    }

    public static LocatorManager getInstance() {
        if (instance == null) {
            instance = new LocatorManager();
        }
        return instance;
    }

    public void addLocator(String key, String value, LocatorTypeEnum type) {
        locators.put(key, new Locator(value, type));
    }

    public Locator getLocator(String key) {
        if (locators.containsKey(key)) {
            return locators.get(key);
        } else {
            logger.error("Locator bulunamadý: {}", key);
            throw new RuntimeException("Locator bulunamadý: " + key);
        }
    }


    public By getBy(String key) {
        Locator locator = getLocator(key);
        String value = locator.getValue();
        LocatorTypeEnum type = locator.getType();

        switch (type) {
            case ID:
                return By.id(value);
            case NAME:
                return By.name(value);
            case XPATH:
                return By.xpath(value);
            case CSS_SELECTOR:
                return By.cssSelector(value);
            default:
                throw new IllegalArgumentException("Geçersiz locator tipi: " + type);
        }
    }
}
