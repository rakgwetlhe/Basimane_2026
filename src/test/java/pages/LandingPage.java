package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LandingPage {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//h2[.//span[normalize-space()='Monde']]")
    WebElement welcomeMessage;
    @FindBy(xpath = "//button[contains(@class, 'nav-dropdown-trigger') and .//span[normalize-space()='Learn']]")
    WebElement learnButton;
    @FindBy(xpath = "//span[normalize-space()='Learning Materials']")
    WebElement learningMaterials;

    public LandingPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        PageFactory.initElements(driver, this);
    }
    public String getWelcomeMessage() {
        return wait.until(ExpectedConditions.visibilityOf(welcomeMessage)).getText();
    }
    public void clickLearnButton() {
        wait.until(ExpectedConditions.elementToBeClickable(learnButton)).click();
    }
    public void clickLearningMaterials() {
        wait.until(ExpectedConditions.elementToBeClickable(learningMaterials)).click();
    }

}
