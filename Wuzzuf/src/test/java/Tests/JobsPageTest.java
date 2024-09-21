package Tests;

import Pages.JobsPage;
import com.beust.ah.A;
import com.github.javafaker.Faker;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class JobsPageTest extends BaseTest {
    Faker fake = new Faker();
    String name = fake.name().firstName();
    String lastName = fake.name().lastName();
    String email = fake.internet().safeEmailAddress();
    String password = fake.internet().password();
    String salary = String.valueOf(fake.number().numberBetween(10000,15000));

    public WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));    JobsPage page;
    SoftAssert soft = new SoftAssert();

    @Test
    public void TC_1() throws InterruptedException {
        reportTest = report.createTest("WUZZUF Test");

        page = new JobsPage(driver);
        soft.assertEquals(driver.getCurrentUrl(), "https://wuzzuf.net/jobs/egypt");
        reportTest.pass("You have landed on the Home page");

        // Interact with the search box
        page.clickOnSearchBox();
        WebElement searchBox = page.getSearchBox();
        soft.assertTrue(searchBox.isDisplayed(), "Search box is not displayed");
        soft.assertTrue(searchBox.isEnabled(), "Search box is not clickable");
        reportTest.pass("The search box is visible and clickable");

        // Enter job title
        String jobTitle = "Software Testing";
        page.enterJobTitle(jobTitle);
        soft.assertEquals(searchBox.getAttribute("value"), jobTitle, "Job title is not entered correctly");
        reportTest.pass("Job title is successfully input");

        // Verify and click on the search button
        WebElement searchButton = page.getSearchButton();
        soft.assertTrue(searchButton.isDisplayed(), "Search button is not displayed");
        soft.assertTrue(searchButton.isEnabled(), "Search button is not clickable");
        reportTest.pass("The search button is visible and clickable");
        page.clickOnSearchJobsButton();
        reportTest.pass("Search button was clicked successfully");

        // Assert the page URL
        soft.assertEquals(driver.getCurrentUrl(), "https://wuzzuf.net/search/jobs/?q=Software%20Testing&a=hpb");
        reportTest.pass("You have landed on the jobs page");

        // Scroll to the Posted Date element
        WebElement postedDateButton = page.getPostedDateElement();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", postedDateButton);

        // Assert that the button is displayed and clickable
        soft.assertTrue(postedDateButton.isDisplayed(), "Posted Date button is not displayed.");
        soft.assertTrue(postedDateButton.isEnabled(), "Posted Date button is not clickable.");
        reportTest.pass("The Posted Date button is visible and clickable");

        try {
            postedDateButton.click();
            reportTest.pass("Posted Date button clicked successfully.");
        } catch (Exception e) {
            js.executeScript("arguments[0].click();", postedDateButton);
            reportTest.pass("Posted Date button clicked successfully using JavaScript.");
        }

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement pastWeekButton = page.getPastWeek();
        soft.assertTrue(pastWeekButton.isDisplayed(),"Past Week option is not displayed");
        soft.assertTrue(pastWeekButton.isEnabled(),"Past Week option is not clickable");
        page.selectPastWeek();
        reportTest.pass("The past Week option is visible and clickable");
        soft.assertEquals(driver.getCurrentUrl(), "https://wuzzuf.net/search/jobs/?a=hpb&filters%5Bpost_date%5D%5B0%5D=within_1_week&q=Software%20Testing");
        // Get the total number of jobs founded from the page
        Thread.sleep(2000);
        String jobsFounded = page.getJobsFounded();
        System.out.println("Total number of jobs found after filtering: " + jobsFounded);
        reportTest.pass("Total jobs found after filter: " + jobsFounded);

        //-Click on First job found and click Apply.
        WebElement firstJobLink = page.getFirstJob();

        soft.assertTrue(firstJobLink.isEnabled(),"first job is not clickable");
        page.clickOnFirstJob();
        reportTest.pass("First job link clicked successfully");
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        if (tabs.size() > 1) {
            Thread.sleep(2000);
            driver.switchTo().window(tabs.get(1));

            soft.assertTrue(driver.getCurrentUrl().contains("https://wuzzuf.net/jobs/"));
            //soft.assertEquals(driver.getCurrentUrl(), "https://wuzzuf.net/jobs/p/hTJoM2rxSXVV-QA-Test-Engineer-coto-Cairo-Egypt?o=2&l=sp&t=sj&a=Software%20Testing|search-v3|hpb", "The URL is incorrect in the new tab");
            reportTest.pass("The new tab has the correct job URL");
        } else {
            reportTest.fail("No new tab was opened.");
        }
        //click on Apply
        WebElement Apply = page.getApplyButton();
        soft.assertTrue(Apply.isDisplayed(),"the Apply for job Button is not displayed");
        soft.assertTrue(Apply.isEnabled(),"the Apply for job Button is not clickable");
        page.clickOnApply();
        reportTest.pass("Apply for work Button is workable successfully");

        WebElement signUpButton = page.getSignUpButton();
        soft.assertTrue(signUpButton.isDisplayed(), "Sign Up button is not displayed.");
        soft.assertTrue(signUpButton.isEnabled(), "Sign Up button is not clickable.");
        reportTest.pass("signUp Button is visible and clickable");

        page.signUp(name , lastName, email, password);
        reportTest.pass("You signed up successfully");
        wait.until(ExpectedConditions.visibilityOf(page.getRegPage()));


        soft.assertAll();
    }
}