package Tests;

import Pages.WikiPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class goToWikiTest {

    WebDriver driver;
    public static final int MAX_REDIRECTS = 20;
    public static final String wikiUrl = "https://en.wikipedia.org/wiki/Main_Page";

    @BeforeEach
    public void setUp()  {
        System.setProperty("webdriver.chrome.driver", "src/resources/chromedriver");
        System.setProperty("webdriver.firefox.driver", "src/resources/geckodriver");

        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
        driver.manage().window().maximize();

        driver.get(wikiUrl);
    }

    @Test
    public void goToWikiTest() {

        int redirects = 0;

        WikiPage wikiPage = new WikiPage(driver);
        wikiPage.goToRandomPage();

        while ( ! wikiPage.headingPhilosophyExists() && redirects < MAX_REDIRECTS) {
            System.out.println(redirects + ": " + wikiPage.getHeading());

            wikiPage.clickOnFirstLink();

            redirects++;
        }

        System.out.println(redirects + ": " + wikiPage.getHeading());

        if ( wikiPage.headingPhilosophyExists() ) {
            System.out.println("Philosophy heading found!");
        }
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
