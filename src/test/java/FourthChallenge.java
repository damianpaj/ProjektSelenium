import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class FourthChallenge {

    private WebDriver driver;
    public String separateAddress[] = null;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/home/damianpajonk/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testReservation() {
        driver.get("https://men-men-s-01.codersguru.pl/search?tag=HTML");
        driver.findElement(By.id("username")).sendKeys("test123@test3.com");
        driver.findElement(By.id("password")).sendKeys("Pass333");
        driver.findElement(By.id("_submit")).click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//button[text() = 'Rezerwuj']"), 0));
        driver.findElements(By.xpath("//button[text() = 'Rezerwuj']")).get(0).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Zamawiam i płacę']")));
        driver.findElement(By.className("reservation__modal-textarea")).sendKeys("This is my problem");
        driver.findElement(By.xpath("//li[text()='31.05']")).click();
        driver.findElement(By.xpath("//li[text()='20:00']")).click();
        driver.findElement(By.xpath("//button[text()='Zamawiam i płacę']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='BLIK']")));
        String title = driver.getTitle();
        assertEquals(title, "PayU");
    }

    @Test
    public void registerNewAccountAndTestReservation() {
        getDataToRegistration();
        driver.findElement(By.className("select-text-desktop")).click();
        driver.findElement(By.xpath("//li[text()='HTML']")).click();
        driver.findElement(By.cssSelector(".link.main-page-top__select-btn")).click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//button[text() = 'Rezerwuj']"), 0));
        driver.findElements(By.xpath("//button[text() = 'Rezerwuj']")).get(0).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Zamawiam i płacę']")));
        driver.findElement(By.className("reservation__modal-textarea")).sendKeys("This is my problem");
        driver.findElement(By.xpath("//li[text()='31.05']")).click();
        driver.findElement(By.xpath("//li[text()='20:00']")).click();
        driver.findElement(By.xpath("//button[text()='Zamawiam i płacę']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='BLIK']")));
        String title = driver.getTitle();
        assertEquals(title, "PayU");

    }


    private void getDataToRegistration() {
        driver.get("https://www.fakenamegenerator.com/gen-random-pl-pl.php");
        String name = driver.findElement(By.xpath("//div[@class='address']/h3")).getText();
        System.out.println(name);
        String separateName[] = name.split(" ");
        System.out.println(separateName[0]);
        System.out.println(separateName[1]);
        String address = driver.findElement(By.xpath("//div[@class='adr']")).getText();
        String emailText = driver.findElements(By.xpath("//dl[@class='dl-horizontal']/dd")).get(8).getText();
        String separateEmail[]= emailText.split("This");
        System.out.println(separateEmail[0]);
//        System.out.println(address);
//        separateAddress = address.split(" ");
//        System.out.println(separateAddress[3]);
        driver.get("https://men-men-s-01.codersguru.pl/");
        List<String> listOfIds = getListOfIds();
        driver.findElement(By.cssSelector(".link.main-page-top__email-input-sent")).click();
        String assertText = driver.findElement(By.className("registration__header")).getText();
        assertEquals(assertText, "Zarejestruj się");
        driver.findElement(By.id("person")).click();
        driver.findElement(By.id(listOfIds.get(0))).sendKeys(separateEmail[0]);
        driver.findElement(By.id(listOfIds.get(1))).sendKeys(separateName[0]);
        driver.findElement(By.id(listOfIds.get(2))).sendKeys(separateName[1]);
        driver.findElement(By.id(listOfIds.get(3))).sendKeys("Pass123");
        driver.findElement(By.id(listOfIds.get(4))).sendKeys("Pass123");
        driver.findElement(By.id(listOfIds.get(5))).sendKeys("Krakow");
        driver.findElement(By.id(listOfIds.get(6))).sendKeys("32-600");
        driver.findElement(By.id(listOfIds.get(7))).sendKeys("Tarnowska");
        driver.findElement(By.id(listOfIds.get(8))).sendKeys("55");
        driver.findElement(By.xpath("//input[@type='checkbox']")).click();
        driver.findElement(By.id("register-submit-btn")).click();

    }

    private List<String> getListOfIds() {
        List<String> listId = new ArrayList<>();
        listId.add("fos_user_registration_form_email");
        listId.add("fos_user_registration_form_name");
        listId.add("fos_user_registration_form_lastname");
        listId.add("fos_user_registration_form_plainPassword_first");
        listId.add("fos_user_registration_form_plainPassword_second");
        listId.add("form_city");
        listId.add("form_postal_code");
        listId.add("form_street");
        listId.add("form_number");
        return listId;
    }
}
