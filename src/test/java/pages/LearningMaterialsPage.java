package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LearningMaterialsPage {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//span[@class='tab-label' and normalize-space()='Web Automation Advance']")
    WebElement webAutomationAdvance;
    @FindBy(id = "deviceType")
    WebElement deviceType;
    @FindBy(id = "brand")
    WebElement brand;
    @FindBy(id = "storage-128GB")
    WebElement storage;
    @FindBy(id = "color")
    WebElement color;
    @FindBy(id = "quantity")
    WebElement quantity;
    @FindBy(id = "address")
    WebElement address;
    @FindBy(id = "inventory-next-btn")
    WebElement nextButton;
    @FindBy(id = "shipping-option-express")
    WebElement shippingOption;
    @FindBy(id = "warranty-1yr")
    WebElement warrantyOption;
    @FindBy(id = "discount-code")
    WebElement discountCode;
    @FindBy(id = "apply-discount-btn")
    WebElement applyButton;
    @FindBy(id = "pricing-breakdown-title")
    WebElement breakdownSummary;

    public void clickWebAutomationTab(WebDriver driver) {
        this.driver = driver;
        webAutomationAdvance.click();
    }
    public void selectDeviceType(String device) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(deviceType));

        Select select = new Select(deviceType);
        select.selectByVisibleText(device);
    }
    public void selectBrand(String brandName) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(brand));

        Select select = new Select(brand);
        select.selectByVisibleText(brandName);
    }
    public void selectStorage() {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(this.storage)).click();
    }
    public void selectColor(String colorName) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(this.color));

        Select select = new Select(color);
        select.selectByVisibleText(colorName);
    }
    public void enterQuantity(int quantity) {
        this.quantity.clear();
        this.quantity.sendKeys(String.valueOf(quantity));
    }
    public void enterDeliveryAddress(String address) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.address.sendKeys(address);
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 500);");
        this.nextButton.click();
    }
    public void selectExtras(String discount) {
        this.shippingOption.click();
        this.warrantyOption.click();
        this.discountCode.sendKeys(discount);
        this.applyButton.click();
    }
    public void confirmPurchase() {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 1000);");
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(breakdownSummary));

    }
}
