package com.gratis.operations;

import com.gratis.base.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;


public class VariousOperations {

    private static final Logger logger = LogManager.getLogger(VariousOperations.class);
    private FindOperations findOperations = FindOperations.instance;

    private static VariousOperations instance;

    private int sayac;

    Actions actions;


    public VariousOperations() {
        actions = new Actions(BaseTest.getDriver());
    }

    public static VariousOperations getInstance() {
        if (instance == null) {
            instance = new VariousOperations();
        }
        return instance;
    }


    public String turkishToEnglish(String turkishWord) {
        String englishWord = turkishWord
                .replaceAll("�", "i")
                .replaceAll("�", "g")
                .replaceAll("�", "u")
                .replaceAll("�", "s")
                .replaceAll("�", "o")
                .replaceAll("�", "c")
                .replaceAll("�", "I")
                .replaceAll("�", "G")
                .replaceAll("�", "U")
                .replaceAll("�", "S")
                .replaceAll("�", "O")
                .replaceAll("�", "C")
                .replaceAll(" & ", "-")
                .replaceAll("\\s+", "-");
        return englishWord.toLowerCase();
    }

    public void mouseHoverToElement(String key) {
        actions.moveToElement(findOperations
                .findElement(key))
                .perform();
    }

    public void itemsOnLeft(String key){
        List<WebElement> elements = WaitOperations.getInstance().waitForElements(key);
        logger.warn(elements.get(0).getText());
        if (elements.size()==2){
            logger.info("Filtreleri Temizle yaz�s�n�n solundaki elementler g�r�nt�lendi. {} - {}",elements.get(0).getText(),elements.get(1).getText());
        }
    }


    public void costWriteToExcel(String key){
        List<WebElement> elementList = WaitOperations.getInstance().waitForElements(key);
        StringBuilder result = new StringBuilder();
        int sayac = 0;
        for (WebElement element : elementList) {
                result.append(element.getText());}
        logger.info("{} �r�n tutar� bulundu.",key);
        writeDataToExistingExcel(result.toString());

    }

    private void writeDataToExistingExcel(String data) {
        try {
            FileInputStream fis = new FileInputStream("src/test/java/com/gratis/excel/urun.xslx");
            Workbook workbook = new HSSFWorkbook(fis);

            Sheet sheet = workbook.getSheet("urun");
            Row row = sheet.getRow(0);
            if (row == null) {
                row = sheet.createRow(0);
            }
            Cell cell = row.getCell(1);
            if (cell == null) {
                cell = row.createCell(1);
            }
            cell.setCellValue(data);

            fis.close();

            FileOutputStream fos = new FileOutputStream("src/test/java/com/gratis/excel/urun.xslx");
            workbook.write(fos);
            fos.close();

            logger.info("�r�n tutar� excele kaydedildi.");
        } catch (IOException e) {
            e.printStackTrace();
            logger.info("�r�n tutar� excele kaydedilirken hata olu�tu.");
        }
    }

    public void writeToExcel(String key) throws IOException {
        WebElement element = WaitOperations.getInstance().waitForElement(key);
        // Workbook olu�turma
        Workbook workbook = new HSSFWorkbook();
        Cell cell;
        // Sayfa olu�turma
        Sheet sheet = workbook.createSheet("urun");
        // Sat�r ve h�cre olu�turma
        Row row = sheet.createRow(0);
        cell = row.createCell(0);

        // Locator ile WebElement'i bulma
        // WebElement'in text de�erini al�p h�creye yazma
        String text = element.getText();
        cell.setCellValue(text);

        // Excel dosyas�n� kaydetme
        FileOutputStream outputStream = new FileOutputStream("src/test/java/com/gratis/excel/urun.xslx");
        workbook.write(outputStream);
        logger.info("{} excele kaydedildi.",key);
        workbook.close();
        outputStream.close();
    }

    public void readFromExcelAndWriteBoxes(String key1, String key2) throws IOException {
        // Excel dosyas�n� okuma
        FileInputStream inputStream = new FileInputStream("src/test/java/com/gratis/excel/urun.xslx");
        Workbook workbook = new HSSFWorkbook(inputStream);

        // Sayfa se�me
        Sheet sheet = workbook.getSheet("urun");

        // �stenen h�creleri se�me
        Row row = sheet.getRow(0); // 0. sat�r� se�tik
        Cell cell1 = row.getCell(0); // 0. s�tunu se�tik
        Cell cell2 = row.getCell(1); // 1. s�tunu se�tik


        // H�crelerin de�erlerini okuma
        String value1 = cell1.getStringCellValue();
        String value2 = cell2.getStringCellValue();
        sendKeys(key1,key2,value1,value2);

        // Okunan de�erleri kullanma
        logger.info("{} �r�n ad� Email kutusuna yaz�ld�.",value1);
        logger.info("{} �r�n tutar� �ifre kutusuna yaz�ld�.",value2);
        // Kaynaklar� temizleme
        workbook.close();
        inputStream.close();
    }

    private void sendKeys(String key1,String key2, String urunAdi, String urunTutari){
        WebElement element1 = WaitOperations.getInstance().waitForElement(key1);
        WebElement element2 = WaitOperations.getInstance().waitForElement(key2);

        element1.sendKeys(urunAdi);
        element2.sendKeys(urunTutari);

    }


}
