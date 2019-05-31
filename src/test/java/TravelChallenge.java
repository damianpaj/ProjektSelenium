
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class TravelChallenge {

    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/home/damianpajonk/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @After
    public void tearDown() {
//       driver.quit();
    }

    @Test
    public void testReservation() throws InterruptedException, IOException {
        driver.get("https://www.phptravels.net/");
        driver.findElements(By.xpath("//a[text()=' My Account ']")).get(1).click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//a[@href='https://www.phptravels.net/login']"), 1));
        driver.findElements(By.xpath("//a[@href='https://www.phptravels.net/login']")).get(1).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Login']")));
        driver.findElement(By.name("username")).sendKeys("damian@damian.com");
        driver.findElement(By.name("password")).sendKeys("damian123");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//a[text()=' Damian ']"), 1));
        driver.findElement(By.xpath("//span[text()='Flights   ']")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElements(By.xpath("//ins")).get(1).click();
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//span[text()='Enter City Or Airport']"),1));
        driver.findElements(By.xpath("//span[text()='Enter City Or Airport']")).get(0).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//input[@class='select2-input select2-focused']")).sendKeys("Warsaw");
        driver.findElements(By.xpath("//span[text()='Warsaw']")).get(0).click();
        driver.findElement(By.xpath("//span[text()='Enter City Or Airport']")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//input[@class='select2-input select2-focused']")).sendKeys("New York");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//div[text()='Monroe Cty Arpt ']")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement firstDate = driver.findElements(By.xpath("//input[@type='date']")).get(0);
        firstDate.click();
        firstDate.click();
        firstDate.sendKeys(Keys.LEFT, Keys.LEFT, "24012019");
        WebElement secondDate = driver.findElements(By.xpath("//input[@type='date']")).get(1);
        secondDate.click();
        secondDate.click();
        secondDate.sendKeys(Keys.LEFT, Keys.LEFT, "31012019");
        driver.findElements(By.xpath("//button[@type='submit']")).get(3).click();
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector(".btn.btn-primary.btn-sm.btn-block.bookbtn.br25"),0));
        driver.findElements(By.cssSelector(".btn.btn-primary.btn-sm.btn-block.bookbtn.br25")).get(0).click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1000)");
        driver.findElement(By.xpath("//button[text()='CONFIRM THIS BOOKING']")).click();
        driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn btn-primary']")));
        js.executeScript("window.scrollBy(0,170)");
       File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
       FileUtils.copyFile(scrFile, new File("/home/damianpajonk/Dokumenty/ProjektSelenium/test.png"));

    }




}


