package com.gratis.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverConfigurations {
    public static WebDriver configureWebDriver() {
        // Chrome seçeneklerini belirleme

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized"); // Tarayýcýyý tam ekran olarak baþlatma
        options.addArguments("--disable-popup-blocking"); // Pop-up engellemeyi devre dýþý býrakma
        options.addArguments("--disable-notifications"); // Bildirimleri engelleme
        options.addArguments("--disable-gpu"); // Ekran kartý kullanýlmasýný engelleme

        // Chrome WebDriver örneðini oluþturma
        WebDriver driver = new ChromeDriver(options);

        // Diðer yapýlandýrma adýmlarý

        return driver;
    }
}
