package PracticeWork;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class inventoryForm {

    WebDriver driver;

    @BeforeTest
    public void setup() throws InterruptedException{

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://ndosisimplifiedautomation.vercel.app/");
        Thread.sleep(5000);
    }

    @Test
    public void clickLoginButton() {
        driver.findElement(By.xpath("//*[@id=\"app-root\"]/nav/div[1]/div[3]/button/span[2]")).click();
    }

    @Test(dependsOnMethods = {"clickLoginButton"})
    public void enterUsername() {
        driver.findElement(By.id("login-email")).sendKeys("monde@gmail.com");
    }

    @Test(dependsOnMethods = {"enterUsername"})
    public void enterPassword() {
        driver.findElement(By.id("login-password")).sendKeys("@12345678");
    }

    @Test(dependsOnMethods = {"enterPassword"})
    public void clickSubmitButton() {
        driver.findElement(By.id("login-submit")).click();
    }

    @Test(dependsOnMethods = {"clickSubmitButton"})
    public void verifyLoginSuccess () throws  InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"app-main-content\"]/section/div[1]/div[1]/h2/span[1]")).isDisplayed();
    }

    @Test(dependsOnMethods = {"verifyLoginSuccess"})
    public void clickLearnTab() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.xpath ("//*[@id=\"app-root\"]/nav/div[1]/div[2]/div[1]/button")).click();
    }

    @Test(dependsOnMethods = {"clickLearnTab"})
    public void clickLearningMaterial() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"app-root\"]/nav/div[1]/div[2]/div[1]/div/button[2]/span[2]")).click();
    }

    @Test(dependsOnMethods = {"clickLearningMaterial"})
    public void verifyLearningMaterialPage() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.id("practice-heading")).isDisplayed();
    }

    @Test(dependsOnMethods = {"verifyLearningMaterialPage"})
    public void clickWebAutomationAdvanceTab() {
        driver.findElement(By.xpath("//*[@id=\"tab-btn-web\"]/span[2]")).click();
    }

    @Test(dependsOnMethods = {"clickWebAutomationAdvanceTab"})
    public void verifyWebAutomationAdvancePage() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.id("inventory-title")).isDisplayed();
    }

    @Test(dependsOnMethods = {"verifyWebAutomationAdvancePage"})
    public void selectDeviceType() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"deviceType\"]/option[2]")).click();
    }

    @Test(dependsOnMethods = {"selectDeviceType"})
    public void selectDeviceModel() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"brand\"]/option[2]")).click();
    }

    @Test(dependsOnMethods = {"selectDeviceModel"})
    public void selectDeviceStorage() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.id("storage-128GB")).click();
    }

    @Test(dependsOnMethods = {"selectDeviceStorage"})
    public void selectDeviceColor() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"color\"]/option[4]")).click();
    }

    @Test(dependsOnMethods = {"selectDeviceColor"})
    public void enterDeviceQuantity() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.id("quantity")).clear();
        driver.findElement(By.id("quantity")).sendKeys("2");
    }

    @Test(dependsOnMethods = {"enterDeviceQuantity"})
    public void enterDeliveryAddress() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.id("address")).sendKeys("123 Main Street, Serengeti, Gauteng, 12345");
    }

    @Test(dependsOnMethods = {"enterDeliveryAddress"})
    public void clickNextButton() throws InterruptedException {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 500);");
        Thread.sleep(2000);
        driver.findElement(By.id("inventory-next-btn")).click();
    }

    @Test(dependsOnMethods = {"clickNextButton"})
    public void verifyOrderPreviewPage() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.id("review-section-title")).isDisplayed();
    }

    @Test(dependsOnMethods = {"verifyOrderPreviewPage"})
    public void selectShippingMethod() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.id("shipping-option-standard")).click();
    }

    @Test(dependsOnMethods = {"selectShippingMethod"})
    public void selectWarrantyOption() {
        driver.findElement(By.id("warranty-1yr")).click();
    }

    @Test(dependsOnMethods = {"selectWarrantyOption"})
    public void enterDiscountCode() {
        driver.findElement(By.id("discount-code")).sendKeys("SAVE10");
        driver.findElement(By.id("apply-discount-btn")).click();
    }

    @Test(dependsOnMethods = {"enterDiscountCode"})
    public void clickConfirmPurchaseButton () throws InterruptedException {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 500);");
        Thread.sleep(2000);
        driver.findElement(By.id("purchase-device-btn")).click();
    }

    @Test(dependsOnMethods = {"clickConfirmPurchaseButton"})
    public void verifyPurchaseSuccessMessage() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"purchase-success-toast\"]/div[1]/h4")).isDisplayed();
    }

    @Test(dependsOnMethods = {"verifyPurchaseSuccessMessage"})
    public void clickViewInvoiceButton() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.id("view-history-btn")).click();
    }

    @Test(dependsOnMethods = {"clickViewInvoiceButton"})
    public void verifyInvoiceHistory () throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.id("invoice-history-title")).isDisplayed();
    }

    @Test(dependsOnMethods = {"verifyInvoiceHistory"})
    public void clickViewInvoiceDetailsButton() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("[id^='view-invoice-']")).click();
    }

    @Test(dependsOnMethods = {"clickViewInvoiceDetailsButton"})
    public void verifyInvoiceDetailsPage() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div/div[1]")).isDisplayed();
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
