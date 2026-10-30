package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    @FindBy(id = "pricing-breakdown-grid")
    WebElement breakdownSummary;
    @FindBy(id = "breakdown-total-value")
    WebElement totalAmount;
    @FindBy(id = "purchase-device-btn")
    WebElement confirmPurchase;
    @FindBy(xpath = "//h4[contains(text(),'Order Successful! \uD83C\uDF89')]")
    WebElement orderSuccessMessage;
    @FindBy(id = "view-history-btn")
    WebElement viewInvoiceBtn;
    @FindBy(xpath = "//button[contains(@id,'view-invoice-INV-')]")
    WebElement viewInvoiceLink;

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
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 1000);");
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
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(breakdownSummary));

        String summaryText = breakdownSummary.getText();

        double subtotal = 960.00;
        double warranty = 49.00;
        double shipping = 25.00;
        double discount = 103.40;

        double expectedTotal = subtotal + warranty + shipping - discount;

        Pattern pattern = Pattern.compile("R(\\d+\\.\\d{2})");
        Matcher matcher = pattern.matcher(summaryText);

        List<Double> amounts = new ArrayList<>();
        while (matcher.find()) {
            amounts.add(Double.parseDouble(matcher.group(1)));
        }

        //Total extracted from summary text
        double actualTotal = amounts.get(amounts.size() - 1);

        Assert.assertEquals(actualTotal, expectedTotal, 0.01, "Incorrect total amount displayed.");

        wait.until(ExpectedConditions.visibilityOf(totalAmount));

        //Total displayed on screen
        double displayedTotal = Double.parseDouble(totalAmount.getText().replace("R", "").replace(",", "").trim());

        Assert.assertEquals(displayedTotal, expectedTotal, 0.01);

        this.confirmPurchase.click();
    }

    public void successfulOrderMessage() {
        wait.until(ExpectedConditions.visibilityOf(orderSuccessMessage));

        Assert.assertEquals(orderSuccessMessage.getText().trim(), "ORDER SUCCESSFUL! \uD83C\uDF89");

        wait.until(ExpectedConditions.visibilityOf(viewInvoiceBtn));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", viewInvoiceBtn);

        wait.until(ExpectedConditions.elementToBeClickable(viewInvoiceBtn));

        try {
            viewInvoiceBtn.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", viewInvoiceBtn);
        }
    }

    public void verifyInvoiceDetails() {
        String parentWindow = driver.getWindowHandle();
        Set<String> existingWindows = driver.getWindowHandles();
        WebDriverWait invoiceWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        String invoiceId = viewInvoiceLink.getAttribute("id").replaceFirst("^view-invoice-", "");
        String invoiceWindow = null;

        try {
            invoiceWait.until(ExpectedConditions.elementToBeClickable(viewInvoiceLink)).click();
            invoiceWait.until(currentDriver ->
                    currentDriver.getWindowHandles().size() > existingWindows.size());

            for (String windowHandle : driver.getWindowHandles()) {
                if (!existingWindows.contains(windowHandle)) {
                    invoiceWindow = windowHandle;
                    break;
                }
            }

            if (invoiceWindow == null) {
                throw new NoSuchWindowException("Invoice tab opened but its window handle was not found.");
            }

            driver.switchTo().window(invoiceWindow);

            By invoiceTitle = By.xpath(
                    "//div[contains(@class,'section-title') and normalize-space()='Invoice Details:']");

            invoiceWait.until(ExpectedConditions.visibilityOfElementLocated(invoiceTitle));
            invoiceWait.until(currentDriver -> {
                String details = currentDriver.findElement(By.tagName("body")).getText();
                return details.contains(invoiceId) && details.matches("(?is).*\\bpaid\\b.*");
            });

            String details = driver.findElement(By.tagName("body")).getText();
            Assert.assertTrue(details.contains(invoiceId), "Invoice number was not shown.");
            Assert.assertTrue(details.matches("(?is).*\\bpaid\\b.*"), "Paid status was not shown.");

            Thread.sleep(Duration.ofSeconds(2));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Interrupted while keeping the verified invoice visible.", e);
        } finally {
            if (invoiceWindow != null && driver.getWindowHandles().contains(invoiceWindow)) {
                driver.switchTo().window(invoiceWindow);
                driver.close();
            }
            if (driver.getWindowHandles().contains(parentWindow)) {
                driver.switchTo().window(parentWindow);
            }
        }
    }
}
