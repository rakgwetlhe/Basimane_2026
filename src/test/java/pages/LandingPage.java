package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LandingPage {

    WebDriver driver;

    @FindBy(xpath = "//*[@id=\"app-main-content\"]/section/div[1]/div[1]/h2")
    WebElement welcomeMessage;
    @FindBy(xpath = "//*[@id=\"app-root\"]/nav/div[1]/div[2]/div[1]/button")
    WebElement learnButton;
    @FindBy(xpath = "//*[@id=\"app-root\"]/nav/div[1]/div[2]/div[1]/div/button[2]/span[2]")
    WebElement clickLearningMaterials;

    public LandingPage(WebDriver driver) {
        this.driver = driver;
    }
    public void getWelcomeMessage() {
        welcomeMessage.getText();
    }
    public void clickLearnButton() {
        learnButton.click();
    }
    public void clickLearningMaterials() {
        clickLearningMaterials.click();
    }

}
