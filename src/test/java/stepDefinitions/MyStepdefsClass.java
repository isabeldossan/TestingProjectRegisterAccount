package stepDefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import logic.Logic;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.junit.Assert.assertEquals;

public class MyStepdefsClass {

    private WebDriver myDriver;
    private String randomMail;

    @Before
    public void setUp() {
        Logic importedMethods = new Logic();
        importedMethods.setRandomMail();
        randomMail = importedMethods.getRandomMail();
    }

    @Given("I have website for Basketball England membership portal open on {}")
    public void iHaveTheWebsiteBasketballEnglandMembershipPortalOpenOn(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            myDriver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            myDriver = new FirefoxDriver();
        }
        myDriver.get("https://membership.basketballengland.co.uk/");
        myDriver.manage().window().maximize();
    }

    @And("I have clicked to create a new Account")
    public void iHaveClickedToCreateANewAccount() {
        findWaitClick(myDriver, By.linkText("CREATE A NEW ACCOUNT"));
    }

    @And("I have clicked on Supporter Account")
    public void iHaveClickedOnSupporterAccount() {
        findWaitClick(myDriver, By.linkText("CREATE A NEW SUPPORTER ACCOUNT"));
    }

    @And("I have written in {string} for Date of Birth")
    public void iHaveWrittenInForDateOfBirth(String dateOfBirth) {
        findWaitClick(myDriver, By.id("dp"));
        myDriver.findElement(By.id("dp")).sendKeys("07/07/1997");
    }

    @And("I have written in {string} for First Name")
    public void iHaveWrittenInForFirstName(String firstName) {
        findWaitClick(myDriver, By.id("member_firstname"));
        myDriver.findElement(By.id("member_firstname")).sendKeys("Isabel");
    }

    @And("I have written in {string} for Last Name")
    public void iHaveWrittenInForLastName(String lastname) {
        findWaitClick(myDriver, By.id("member_lastname"));
        if (lastname.equalsIgnoreCase("Santoson")) {
            myDriver.findElement(By.id("member_lastname")).sendKeys("Santoson");
        } else if (lastname.equalsIgnoreCase("")) {
            myDriver.findElement(By.id("member_lastname")).sendKeys("");
        }
    }

    @And("I have written in random email for Email Address")
    public void iHaveWrittenInRandomEmailForEmailAddress() {
        findWaitClick(myDriver, By.id("member_emailaddress"));
        myDriver.findElement(By.id("member_emailaddress")).sendKeys(randomMail);
    }

    @And("I have written in random email for Confirm Email Address")
    public void iHaveWrittenInRandomEmailForConfirmEmailAddress() {
        findWaitClick(myDriver, By.id("member_confirmemailaddress"));
        myDriver.findElement(By.id("member_confirmemailaddress")).sendKeys(randomMail);
    }

    @And("I have written in {string} for Choose Your Password")
    public void iHaveWrittenInForChooseYourPassword(String pass) {
        findWaitClick(myDriver, By.id("signupunlicenced_password"));
        myDriver.findElement(By.id("signupunlicenced_password")).sendKeys("santosa");
    }

    @And("I have written in {string} in Retype Your Password")
    public void iHaveWrittenInInRetypeYourPassword(String secondPassword) {
        findWaitClick(myDriver, By.id("signupunlicenced_confirmpassword"));

        if (secondPassword.equalsIgnoreCase("santosa")) {
            myDriver.findElement(By.id("signupunlicenced_confirmpassword")).sendKeys("santosa");
        } else if (secondPassword.equalsIgnoreCase("santosa.santosa")) {
            myDriver.findElement(By.id("signupunlicenced_confirmpassword")).sendKeys("santosa.santosa");
        }
    }

    @And("I have marked the checkmark Fan in Describe your role")
    public void iHaveMarkedTheCheckmarkFanInDescribeYourRole() {
        findWaitClick(myDriver, By.cssSelector(".col-sm-4:nth-child(12) .box"));
    }

    @And("I {} the checkmark for Agreed to Terms & Conditions")
    public void iTheCheckmarkForAgreedToTermsConditions(String haveClicked) {
        if (haveClicked.equalsIgnoreCase("have")) {
            findWaitClick(myDriver, By.cssSelector(".md-checkbox > .md-checkbox:nth-child(1) .box"));
        } else if (haveClicked.equalsIgnoreCase("have not")) {
            //do nothing
        }
    }

    @And("I have marked the checkmark I am aged over Eighteen")
    public void iHaveMarkedTheCheckmarkIAmAgedOverEighteen() {
        findWaitClick(myDriver, By.cssSelector(".md-checkbox:nth-child(2) > label > .box"));
    }

    @And("I have marked the checkmark for I have read the Code of Ethics & Conducts")
    public void iHaveMarkedTheCheckmarkForIHaveReadTheCodeOfEthicsConducts() {
        findWaitClick(myDriver, By.cssSelector(".md-checkbox:nth-child(7) .box"));
    }

    @When("I choose to Confirm & Join by clicking")
    public void iChooseToConfirmJoinByClicking() {
        findWaitClick(myDriver, By.name("join"));
        myDriver.findElement(By.cssSelector(".bold:nth-child(1)")).click();
    }

    @Then("I shall get the result {}")
    public void iShallGetTheResult(String result) {
        if (result.equalsIgnoreCase("Successfully")) {
            WebElement thankYouText = myDriver.findElement(By.xpath("/html/body/div/div[2]/div/h2"));
            String expected = "THANK YOU FOR CREATING AN ACCOUNT WITH BASKETBALL ENGLAND";
            String actual = thankYouText.getText();
            assertEquals(expected, actual);
            myDriver.close();
        } else if (result.equalsIgnoreCase("Missing Lastname")) {
            WebElement warningText = myDriver.findElement(By.cssSelector("span[class='warning field-validation-error']"));
            String expected = "Last Name is required";
            String actual = warningText.getText();
            assertEquals(expected, actual);
            myDriver.close();
        } else if (result.equalsIgnoreCase("Un-matching Password")) {
            WebElement warningText = myDriver.findElement(By.cssSelector("span[for='signupunlicenced_confirmpassword']"));
            String expected = "Password did not match";
            String actual = warningText.getText();
            assertEquals(expected, actual);
            myDriver.close();
        } else if (result.equalsIgnoreCase("Un-marked Terms&Conditions")) {
            WebElement warningText = myDriver.findElement(By.cssSelector("span[data-valmsg-for='TermsAccept']"));
            String expected = "You must confirm that you have read and accepted our Terms and Conditions";
            String actual = warningText.getText();
            assertEquals(expected, actual);
            myDriver.close();
        }
    }

    private static void findWaitClick(WebDriver myDriver, By by) {
        (new WebDriverWait(myDriver, Duration.ofSeconds(1))).until(ExpectedConditions.visibilityOfElementLocated(by));
        myDriver.findElement(by).click();
    }

    @After
    public void tearDown() {
        myDriver.quit();
    }
}

