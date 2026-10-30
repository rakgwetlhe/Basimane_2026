package tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class LoginTests extends Base {

    @Test
    public void clickLoginButton() throws InterruptedException {
        takeScreenshots.takeSnapShot(driver, "BeforeLogin");
        homepage.clickLoginButton();
        loginPage.enterUsername(readData.username);
        loginPage.enterPassword(readData.password);
        loginPage.clickLoginSubmitButton();
    }

    @Test(dependsOnMethods = "clickLoginButton")
    public void verifyLoginSuccess() {
        takeScreenshots.takeSnapShot(driver, "AfterLogin");
        landingPage.getWelcomeMessage();
        landingPage.clickLearnButton();
        landingPage.clickLearningMaterials();
        takeScreenshots.takeSnapShot(driver, "LearningMaterials");
    }

    @Test(dependsOnMethods = "verifyLoginSuccess")
    public void clickWebAutomationTab() {
        takeScreenshots.takeSnapShot(driver, "LearningMaterialsWebAutomation");
        learningMaterialsPage.clickWebAutomationTab(driver);
    }

    @Test(dependsOnMethods = "clickWebAutomationTab")
    public void purchaseDevice() {
        takeScreenshots.takeSnapShot(driver, "PurchaseDevice");
        learningMaterialsPage.selectDeviceType("Phone");
        learningMaterialsPage.selectBrand("Apple");
        learningMaterialsPage.selectStorage();
        learningMaterialsPage.selectColor("Blue");
        learningMaterialsPage.enterQuantity(2);
        learningMaterialsPage.enterDeliveryAddress("1 Serengeti Estate");
        takeScreenshots.takeSnapShot(driver, "ShippingDetails");
        learningMaterialsPage.selectExtras("SAVE10");
        learningMaterialsPage.confirmPurchase();
        learningMaterialsPage.successfulOrderMessage();
        takeScreenshots.takeSnapShot(driver, "SuccessfulOrder");
        learningMaterialsPage.verifyInvoiceDetails();
        learningMaterialsPage.acceptAlert();
    }
    @AfterClass
    public void tearDown() {
        if (driver != null)
            driver.quit();
    }
}
