package tests;

import org.testng.annotations.Test;

public class LoginTests extends Base{

    @Test
    public void clickLoginButton() throws InterruptedException {
        homepage.clickLoginButton();
        loginPage.enterUsername("monde@gmail.com");
        loginPage.enterPassword("@12345678");
        loginPage.clickLoginSubmitButton();
    }
    @Test(dependsOnMethods = "clickLoginButton")
    public void verifyLoginSuccess() {
        landingPage.getWelcomeMessage();
        landingPage.clickLearnButton();
        landingPage.clickLearningMaterials();
    }
}
