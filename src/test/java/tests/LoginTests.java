package tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class LoginTests extends Base {

    @Test
    public void clickLoginButton() throws InterruptedException {
        homepage.clickLoginButton();
        loginPage.enterUsername(readData.username);
        loginPage.enterPassword(readData.password);
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
        learningMaterialsPage.enterDeliveryAddress("1 Serengeti Estate");
        learningMaterialsPage.selectExtras("SAVE10");
        learningMaterialsPage.confirmPurchase();
        learningMaterialsPage.successfulOrderMessage();
        learningMaterialsPage.verifyInvoiceDetails();
        learningMaterialsPage.acceptAlert();
    }
    /*@AfterClass
    public void tearDown() {
        if (driver != null)
            driver.quit();
    }*/
}
