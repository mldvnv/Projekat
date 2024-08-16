package Steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.testng.Reporter;
import Pages.bookingIndexPage;
import Tests.baseTest;
import java.time.Duration;

public class mainSteps extends baseTest {

    String browser =Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("browser");
    String quit = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("quit");
    String wait = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("wait");


    @Before
    @Description("Firing up the browser")
    public void setup() throws Exception{
    init(browser,wait);
    }

    @After
    @Description("Closing up the browser")
    public void tearDown(){
        if(quit.equalsIgnoreCase("yes")){
            quit();
        }
    }

    @Given("Im on booking page")
    public void imOnBookingPage()  {
        driver.get("https://www.booking.com/index.sr.html");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        
        if(driver.findElement(By.cssSelector("[aria-label='Zatvori poruku o logovanju.']")).isDisplayed()){
            driver.findElement(By.cssSelector("[aria-label='Zatvori poruku o logovanju.']")).click();
        }
    }

    @When("I enter location {string} in search field")
    public void iEnterLocationInSearchField(String location) throws Exception {
        new bookingIndexPage(driver).enterLocation(location);
    }

    @And("choose duration of stay {string} {string}")
    public void chooseDurationOfStay(String chekInDate, String checkOutDate) {
        new bookingIndexPage(driver).chooseDates(chekInDate,checkOutDate);
    }

    @And("choose number of guests {string}, and rooms{string}")
    public void chooseNumberOfGuestsAndRooms(String numberOfGuests, String numberOfRooms) {
        new bookingIndexPage(driver).occupancyChoiceMaker(Integer.parseInt(numberOfGuests),Integer.parseInt(numberOfRooms));
    }
    @And("I click on search button")
    public void iClickOnSearchButton() throws Exception {
        new bookingIndexPage(driver).clickSearchButton();
    }

    @Then("appropriate results should be displayed")
    public void appropriateResultsShouldBeDisplayed() throws Exception {
        new bookingIndexPage(driver).resultsDisplayed();
    }

}

