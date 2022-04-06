import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class ImplicitExplicitWait {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/home/semira/IdeaProjects/FinalSelenium/FunctionalTesting/chromedriver");
        WebDriver driver = new ChromeDriver();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));

        driver.get("http://rahulshettyacademy.com/seleniumPractise/#");

        String[] itemsToAdd = {"Cucumber", "Brocolli", "Beetroot", "Tomato"};

        Thread.sleep(3000);
        addItems(driver, itemsToAdd);

        driver.findElement(By.cssSelector("img[alt='Cart']")).click();
        driver.findElement(By.xpath("//button[contains(text(),'PROCEED TO CHECKOUT')]")).click();
        w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input.promoCode")));

        driver.findElement(By.cssSelector("input.promoCode")).sendKeys("rahulshettyacademy");

        driver.findElement(By.cssSelector("button.promoBtn")).click();

        //explicit wait:
        w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.promoInfo")));
        System.out.println(driver.findElement(By.cssSelector("span.promoInfo")).getText());


    }

    public static void addItems(WebDriver driver, String[] itemsToAdd) {
        List<WebElement> products = driver.findElements(By.cssSelector("h4.product-name"));

        int count = 0;
        for (WebElement product : products) {

            //format name to get actual vegetable name   Cucumber - 1 Kg
            String[] name = product.getText().split("-");  //name[0] = Cucumber   name[1]= 1 Kg
            String formattedName = name[0].trim();  //to remove spaces

            //convert array itemsToAdd into ArrayList to check whether the extracted name is present in arrayList
            List<String> itemsToAddList = Arrays.asList(itemsToAdd);

            if (itemsToAddList.contains(formattedName)) {
                count++;
                //click on Add to cart
                driver.findElements(By.xpath("//div[@class='product-action']/button")).get(products.indexOf(product)).click();
                System.out.println(product.getText());
                if (count == itemsToAdd.length) {
                    break;
                }
            }
        }
    }
}



























