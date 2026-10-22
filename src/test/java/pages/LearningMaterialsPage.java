package pages;

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

}
