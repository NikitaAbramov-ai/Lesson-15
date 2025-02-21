import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MtsOnlinePaymentTest {
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Работа\\chromedriver-win64");
        driver = new ChromeDriver();
        driver.get("https://www.mts.by");
    }

    @Test
    public void testBlockTitle() {
        WebElement blockTitle = driver.findElement(By.xpath("//h2[contains(text(), 'Онлайн пополнение без комиссии')]"));
        Assert.assertEquals(blockTitle.getText(), "Онлайн пополнение без комиссии");
    }

    @Test
    public void testPaymentLogos() {
        WebElement visaLogo = driver.findElement(By.xpath("//img[@alt='Visa']"));
        WebElement mastercardLogo = driver.findElement(By.xpath("//img[@alt='MasterCard']"));
        WebElement belcardLogo = driver.findElement(By.xpath("//img[@alt='Belcard']"));

        Assert.assertTrue(visaLogo.isDisplayed());
        Assert.assertTrue(mastercardLogo.isDisplayed());
        Assert.assertTrue(belcardLogo.isDisplayed());
    }

    @Test
    public void testMoreDetailsLink() {
        WebElement moreDetailsLink = driver.findElement(By.xpath("//a[contains(text(), 'Подробнее о сервисе')]"));
        moreDetailsLink.click();

        String expectedUrl = "https://www.mts.by/help/payments/online-payment/";
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);
    }

    @Test
    public void testContinueButton() {
        WebElement serviceType = driver.findElement(By.xpath("//input[@value='services']"));
        serviceType.click();

        WebElement phoneNumberField = driver.findElement(By.id("phoneNumber"));
        phoneNumberField.sendKeys("297777777");

        WebElement continueButton = driver.findElement(By.xpath("//button[contains(text(), 'Продолжить')]"));
        continueButton.click();

        WebElement paymentPageTitle = driver.findElement(By.xpath("//h1[contains(text(), 'Оплата услуг связи')]"));
        Assert.assertTrue(paymentPageTitle.isDisplayed());
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}