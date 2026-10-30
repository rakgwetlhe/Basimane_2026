package utils;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;

public class ReadData {

    static String testDataPath = System.getProperty("user.dir") + "/src/test/java/testData/TestData.xlsx";
    static FileInputStream fileInputStream;

    static {
        try {
            fileInputStream = new FileInputStream(testDataPath);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static XSSFWorkbook workbook;

    static {
        try {
            workbook = new XSSFWorkbook(fileInputStream);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ReadData() throws Exception {
    }

    static XSSFSheet sheet = workbook.getSheet("Login");

    public static String username = sheet.getRow(2).getCell(0).getStringCellValue();
    public static String password = sheet.getRow(2).getCell(1).getStringCellValue();

}


