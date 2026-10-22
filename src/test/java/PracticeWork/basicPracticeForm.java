package PracticeWork;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class basicPracticeForm {

    WebDriver driver;

    @BeforeTest
    public void setup() throws InterruptedException {

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
    public void verifyLoginSuccess() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"app-main-content\"]/section/div[1]/div[1]/h2/span[1]")).isDisplayed();
    }
    @Test(dependsOnMethods = {"verifyLoginSuccess"})
    public void clickLearnTab() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"app-root\"]/nav/div[1]/div[2]/div[1]/button")).click();
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
    public void clickWebAutomationBasicFormTab() {
        driver.findElement(By.xpath("//*[@id=\"tab-btn-password\"]/span[2]")).click();
    }
    @Test(dependsOnMethods = {"clickWebAutomationBasicFormTab"})
    public void verifyWebAutomationBasicFormPage() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.id("page-title")).isDisplayed();
    }
    @Test(dependsOnMethods = {"verifyWebAutomationBasicFormPage"})
    public void fillBasicPracticeForm() throws InterruptedException {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 500);");
        driver.findElement(By.id("name")).sendKeys("John Doe");
        driver.findElement(By.id("email")).sendKeys("johndoe@example.com");
        driver.findElement(By.id("age")).sendKeys("30");
        driver.findElement(By.id("gender")).sendKeys("Male");
        driver.findElement(By.id("country")).sendKeys("South Africa");
        driver.findElement(By.id("experience")).sendKeys("beginner");
        driver.findElement(By.id("skill-javascript")).click();
        driver.findElement(By.id("comments")).sendKeys("This is a test comment.");
        driver.findElement(By.id("terms")).click();
        driver.findElement(By.id("submit-btn")).click();
        Thread.sleep(2000);
    }
    @Test(dependsOnMethods = {"fillBasicPracticeForm"})
    public void verifyFormData() throws InterruptedException {
        driver.findElement(By.xpath("//*[@id=\"practice-form\"]/details/summary")).click();
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 500);");

        // Wait for the success message
        WebDriverWait wait = new WebDriverWait(
                driver, Duration.ofSeconds(10));

        WebElement successMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//*[contains(text(),'successfully')]")));

        // Capture the message
        String actualMessage = successMessage.getText();

        System.out.println("Captured message: " + actualMessage);

        // Verify the message
        Assert.assertTrue(
                actualMessage.toLowerCase().contains("success"),
                "Success message was not displayed!");
        Thread.sleep(2000);
    }
    @Test(dependsOnMethods = {"verifyFormData"})
    public void verifyFormSubmission() throws InterruptedException  {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, -500);");
        driver.findElement(By.id("submissions-toggle-btn")).click();
        driver.findElement(By.id("view-submission-0")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div/div[1]/div[2]")).isDisplayed();

    }
    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}

