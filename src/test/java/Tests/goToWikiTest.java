package Tests;

import Pages.WikiPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class goToWikiTest {

    WebDriver driver;
    public static final int MAX_REDIRECTS = 13;

    @BeforeEach
    public void setUp() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/resources/chromedriver");
        System.setProperty("webdriver.firefox.driver", "src/resources/geckodriver");

        driver = new FirefoxDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driver.get("https://en.wikipedia.org/wiki/Main_Page");
        Thread.sleep(3000);
    }

    @Test
    public void goToWiki() throws InterruptedException {

        int redirects = 0;

        WikiPage wikiPage = new WikiPage(driver);
        String text = wikiPage.getText();

        wikiPage.goToRandomPage();

        while ( ! wikiPage.getText().contains("philosophy") && redirects < MAX_REDIRECTS) {
            System.out.println(redirects + ": " + wikiPage.getHeading());

            wikiPage.enterFirstLink();

            redirects++;
        }
    }

    @Test
    public void tearDown() {
        driver.quit();
    }
}
