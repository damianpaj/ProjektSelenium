import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FirstChallenge {

    private WebDriver driver;


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
    public void testPageLinks() {
        String url = "https://men-men-s-01.codersguru.pl/";
        driver.get(url);
        List<String> lists = Arrays.asList("/cennik","/#how-it-works","/login", "/regulations");

        for (String listElement : lists) {

            clickLinkAndTest(listElement);
            assertEquals("https://men-men-s-01.codersguru.pl"+listElement, clickLinkAndTest(listElement));
            driver.get(url);

        }

        WebElement facebook = driver.findElement(By.xpath("//a[@href= 'https://www.facebook.com/codersguru/']"));
        String attributeFacebook = facebook.getAttribute("href");
        facebook.click();
        assertEquals("https://www.facebook.com/codersguru/", attributeFacebook);
        driver.get(url);

        WebElement register = driver.findElement(By.cssSelector(".link.main-page-top__email-input-sent"));
        String attributeRegister = register.getAttribute("value");
        register.click();
        assertEquals("Załóż konto", attributeRegister);
        driver.get(url);

    }

    private String clickLinkAndTest(String name) {
        WebElement element = driver.findElement(By.xpath("//a[@href= '" + name + "']"));
        String attribute = element.getAttribute("href");
        element.click();
        return attribute;
    }
}


