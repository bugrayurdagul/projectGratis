package com.gratis.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverConfigurations {
    public static WebDriver configureWebDriver() {
        // Chrome se�eneklerini belirleme

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized"); // Taray�c�y� tam ekran olarak ba�latma
        options.addArguments("--disable-popup-blocking"); // Pop-up engellemeyi devre d��� b�rakma
        options.addArguments("--disable-notifications"); // Bildirimleri engelleme
        options.addArguments("--disable-gpu"); // Ekran kart� kullan�lmas�n� engelleme

        // Chrome WebDriver �rne�ini olu�turma
        WebDriver driver = new ChromeDriver(options);

        // Di�er yap�land�rma ad�mlar�

        return driver;
    }
}
