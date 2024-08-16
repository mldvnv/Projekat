package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import java.util.List;

public class bookingIndexPage extends basePage{
    public bookingIndexPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

   @FindBy(xpath ="//*[@id=':rh:']")
    WebElement locationInput ;

    @FindBy(css="[data-testid='searchbox-dates-container']")
    WebElement datePicker;


    @FindBy(css = "[data-testid='occupancy-config']")
    WebElement occupancy;

    @FindBy(css = ".d1821e6945")
    List<WebElement> choiceMaker;

    public void chooseDates (String startDate, String endDate){
        datePicker.click();
        driver.findElement(By.cssSelector("[data-date='"+startDate+"']")).click();
        driver.findElement(By.cssSelector("[data-date='"+endDate+"']")).click();
    }

    public void occupancyChoiceMaker(int numberOfAdults, int numberOfUnits){
        occupancy.click();
        for(int i=1;i<numberOfAdults;i++){
            choiceMaker.get(0).click();
        }
        for(int i=1;i<numberOfUnits;i++){
            choiceMaker.get(2).click();
        }
    }

    @FindBy(css ="[type='submit']")
    WebElement searchButton ;

    @FindBy(css = ".e037993315.f8da796a11")
    WebElement results;

    
    public void enterLocation(String location) throws Exception{
        typeText(locationInput,location,"Location " + location + " has been entered!");
    }

    public void clickOnDatePicker () throws Exception{
    click(datePicker,"Button" + datePicker + " has been clicked!");
    }


    public void clickOnOccupancyConfig() throws Exception{
        click(occupancy,"Button"+ occupancy + " has been clicked ");
    }

    public void clickSearchButton() throws  Exception{
        click(searchButton,"Button " + searchButton + " has been clicked");
    }

    public void resultsDisplayed() throws Exception{
        Assert.assertTrue(results.isDisplayed());
    }
    }


