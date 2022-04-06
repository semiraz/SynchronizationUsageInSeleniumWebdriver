import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AssignmentEnd2End {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/home/semira/IdeaProjects/FinalSelenium/FunctionalTesting/chromedriver");
        WebDriver driver = new ChromeDriver();
        WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().window().maximize();

        driver.get("https://rahulshettyacademy.com/loginpagePractise/");

        loginPage(driver, w);
        addItems(driver);
        closeApp(driver);


    }

    public static void loginPage(WebDriver driver,WebDriverWait w) {

        String username = driver.findElement(By.xpath("(//p[contains(@class,'text-center')]/b/i)[1]")).getText();
        String password = driver.findElement(By.xpath("(//p[contains(@class,'text-center')]/b/i)[2]")).getText();
        driver.findElement(By.cssSelector("#username")).sendKeys(username);
        driver.findElement(By.cssSelector("#password")).sendKeys(password);

        WebElement userButton = driver.findElement(By.cssSelector("input[value='user']"));
        userButton.click();

        w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#okayBtn"))).click();

        Select s = new Select(driver.findElement(By.cssSelector("select.form-control")));
        s.selectByValue("consult");

        driver.findElement(By.id("terms")).click();

        driver.findElement(By.id("signInBtn")).click();
    }

    public static void addItems(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        List<WebElement> addButtons = driver.findElements(By.xpath("//button[@class='btn btn-info']"));
        for (WebElement addButton : addButtons) {
            addButton.click();
        }

        driver.findElement(By.xpath("//a[@class='nav-link btn btn-primary']")).click();
//        driver.findElement(By.partialLinkText("Checkout")).click();
    }

    public static void closeApp(WebDriver driver) {
        driver.quit();
    }
}






















