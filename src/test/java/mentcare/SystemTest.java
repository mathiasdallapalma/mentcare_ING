package mentcare;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.apache.commons.lang3.SystemUtils;

import org.openqa.selenium.WebDriver;

import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SystemTest {

    protected WebDriver driver;
    @Before
    public void setUp() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--headless");

        /*
        if(SystemUtils.IS_OS_WINDOWS){
            System.setProperty("webdriver.chrome.driver",
                    Paths.get("src/test/resources/chromedriver_win32_96/chromedriver.exe").toString());
        }
        else if (SystemUtils.IS_OS_MAC){
            System.setProperty("webdriver.chrome.driver",
                    Paths.get("src/test/resources/chromedriver_mac64_96/chromedriver").toString());
        }
        else if (SystemUtils.IS_OS_LINUX){
            System.setProperty("webdriver.chrome.driver",
                    Paths.get("src/test/resources/chromedriver_linux64_96/chromedriver").toString());
        }
        */
        System.setProperty("webdriver.gecko.driver",
                Paths.get("src/test/resources/gekodriver/geckodriver").toString());
        if (driver == null)
            driver = new FirefoxDriver(options);
    }

    @Test
    public void loadApp() {
        driver.get("http://localhost:8080");
        assertEquals("TitlePage", "Home", driver.getTitle());
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }



}
