package com.gratis.tests;

import com.gratis.base.BaseTest;
import com.gratis.pages.HomePage;
import com.gratis.pages.ProductIndividualPage;
import com.gratis.pages.ProductsPage;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class MainTest extends BaseTest {

    HomePage homePage;
    ProductsPage productsPage;
    ProductIndividualPage productIndividualPage;

    public MainTest() {
        homePage = new HomePage();
        productsPage = new ProductsPage();
        productIndividualPage = new ProductIndividualPage();
    }

    @Test
    public void gratisTest() throws IOException {
        homePage.homePageOpenControl();
        homePage.hoverNavAndClickRandomSubNav();
        productsPage.checkIfPageLoaded();
        productsPage.clickRandomCheckBoxes();
        productsPage.checkIfLeftFiltersAppears();
        productsPage.clickRandomProduct();
        productIndividualPage.checkIfPageLoadedd();
        productIndividualPage.writeProductNameAndPriceToExcel();
        productIndividualPage.clickAddToCartButton();
        productIndividualPage.readFromExcelAndWriteToBoxes();
    }
}
