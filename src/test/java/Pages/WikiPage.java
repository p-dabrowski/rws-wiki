package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class WikiPage {
    WebDriver driver;

    public WikiPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath="//*[@id='content']")
    WebElement mainPage;

    @FindBy(xpath="//a[@href='/wiki/Special:Random']")
    WebElement goToRandomPage;

    @FindBy(xpath="//div[@class='mw-parser-output']/p//a[contains(@href, '/wiki/')]")
    List<WebElement> links;

    @FindBy(xpath="//*[@id='firstHeading']")
    WebElement heading;

    public String getText() {
        return mainPage.getText();
    }

    public void goToRandomPage() {
        goToRandomPage.click();
    }

    public void enterFirstLink() {
        links.get(0).click();
    }

    public String getHeading() {
        return heading.getText();
    }
}
