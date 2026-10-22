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
    @Test(dependsOnMethods = "verifyLoginSuccess")
    public void clickWebAutomationTab() {
        learningMaterialsPage.clickWebAutomationTab(driver);
    }
    @Test(dependsOnMethods = "clickWebAutomationTab")
    public void purchaseDevice() {
        learningMaterialsPage.selectDeviceType("Phone");
        learningMaterialsPage.selectBrand("Apple");
        learningMaterialsPage.selectStorage();
        learningMaterialsPage.selectColor("Blue");
        learningMaterialsPage.enterQuantity(2);
    }
}
