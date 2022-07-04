import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Run {
    static WebDriver driver;
    static String url = "https://www.google.co.il/";

    public static void main(String[] args) {
        invokeBrowser();
        googleSearch("sport5", "לוח שידורים");

    }

    public static void invokeBrowser() {
        try {
            driver = WebDriverManager.chromedriver().create();
            driver.manage().window().maximize();
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.get(url);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void googleSearch(String value1, String value2) {
        driver.findElement(By.name("q")).sendKeys(value1);
        WebElement dropdown = driver.findElement(By.cssSelector("ul ul[jsname='bw4e9b']"));
        waitForAllElementToBePresence(dropdown, 5);
        List<WebElement> myResultsList = driver.findElements(By.cssSelector("li.sbct span"));
        for (WebElement element : myResultsList) {
            String str = element.getText();
            if (str.contains(value2)) {
                element.click();
                break;
            }
        }


    }

    public static void waitForAllElementToBePresence(WebElement element, int time) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.visibilityOfAllElements(element));
    }
}
