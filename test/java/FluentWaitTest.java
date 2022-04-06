import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.function.Function;

public class FluentWaitTest {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/home/semira/IdeaProjects/FinalSelenium/FunctionalTesting/chromedriver");
        WebDriver driver = new ChromeDriver();

        driver.get("http://the-internet.herokuapp.com/dynamic_loading/1");

        driver.findElement(By.cssSelector("[id='start'] button")).click();

        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(3))
                .ignoring(NoSuchElementException.class);

        WebElement helloWorld = driver.findElement(By.cssSelector("[id='finish'] h4"));

        WebElement element = wait.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver driver) {
                if (helloWorld.isDisplayed()) {
                    return helloWorld;
                } else  {
                    return null;
                }
            }
        });
        System.out.println(helloWorld.isDisplayed());
        System.out.println(helloWorld.getText());

    }
}















