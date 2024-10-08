package Pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import Tests.baseTest;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class basePage {
    WebDriver driver;
    int maxRetries = 3;
    public basePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    WebDriverWait webDriverWait;

    Tests.baseTest baseTest = new baseTest();
    public void click(WebElement element, String log) throws Exception {
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        webDriverWait.until(ExpectedConditions.visibilityOf(element));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(element));

        int retryCount = 0;
        while (retryCount<maxRetries){
            try {
                Actions actions = new Actions(driver);
                actions.moveToElement(element).build().perform();
                element.click();
                System.out.println(getCurrentTimeAndDate()+" Clicked: "+log);
                break;
            }catch (Exception e){
                retryCount++;
                System.out.println("Retry: "+retryCount+" to click on"+log);
                if(retryCount==maxRetries){
                    baseTest.reportScreenshot("failedClick","Failed to click");
                    throw new Exception(getCurrentTimeAndDate()+" Failed to click element: "+log);
                }
            }
        }
    }

    public void typeText (WebElement element, String text, String log) throws Exception {
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        int retryCount = 0;
        while (retryCount<maxRetries){
            try {
                webDriverWait.until(ExpectedConditions.visibilityOf(element));
                element.sendKeys(text);
                System.out.println(getCurrentTimeAndDate()+" Typed: "+log);
                break;
            }catch (Exception e){
                retryCount++;
                System.out.println("Retry: "+retryCount+" to type to"+log);
                if(retryCount==maxRetries){
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                    baseTest.reportScreenshot("failedType","Failed to type");
                    throw new Exception(getCurrentTimeAndDate()+" Failed to type to element: "+log);
                }
            }
        }
    }

    public void typeText (WebElement element, Keys key, String log) throws Exception {
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        int retryCount = 0;
        while (retryCount<maxRetries){
            try {
                webDriverWait.until(ExpectedConditions.visibilityOf(element));
                element.sendKeys(key);
                System.out.println(getCurrentTimeAndDate()+" Typed: "+log);
                break;
            }catch (Exception e){
                retryCount++;
                System.out.println("Retry: "+retryCount+" to type to"+log);
                if(retryCount==maxRetries){
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                    baseTest.reportScreenshot("failedType","Failed to type");
                    throw new Exception(getCurrentTimeAndDate()+" Failed to type to element: "+log);
                }
            }
        }
    }

    public void assertEQ(String actual, String expected, String log){
        Assert.assertEquals(actual,expected);
        System.out.println(getCurrentTimeAndDate()+" Verified: "+log);
    }

    public String getCurrentTimeAndDate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
}
